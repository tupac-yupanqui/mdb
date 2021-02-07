package de.wko.mdb.fs;

import de.wko.mdb.types.Archive;
import de.wko.mdb.types.Host;
import de.wko.mdb.types.Login;
import de.wko.mdb.types.MdbFile;
import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.util.ArrayList;
import java.util.List;

public class FtpFileSystem extends ArchiveFileSystem {
    private Login login;
    private FTPClient client;

    public FtpFileSystem(Host host, Archive archive) {
        super(host, archive);

        this.login = new Login(host.getLogin(), host.getPassword());
        try {
            client = new FTPClient();
            FileWriter ftpProtocol = new FileWriter("mdbcliftp.log", true);
            client.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(ftpProtocol)));
            client.connect(host.getAddress());

            int reply = client.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                client.disconnect();
                connectionStatus = "Exception in connecting to FTP Server";
                return;
            }

            if (!client.login(host.getLogin(), host.getPassword())) {
                client.disconnect();
                connectionStatus = "Login fehlgeschlagen";
                return;
            }
            if (!client.changeWorkingDirectory(archive.getPath())) {
                client.disconnect();
                connectionStatus = "Wurzelverzeichnis nicht gefunden";
                return;
            }
            currentDir = "/";
            filesystemAvailable = true;
        } catch (ConnectException e) {
            connectionStatus = "Verbindung fehlgeschlagen";
        } catch (IOException e) {
            connectionStatus = "Fehler beim Verbindungsaufbau "+e.getMessage();
        }
    }

    @Override
    public void close() {
        try {
            client.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<MdbFile> listDir(String d) throws FileSystemException {
        List<MdbFile> result = new ArrayList<>();
        try {
            FTPFile[] files = client.listFiles();

            for (FTPFile ftpFile : files) {
                MdbFile file = new MdbFile();
                file.setName(ftpFile.getName());
                file.setSize(ftpFile.getSize());
                file.setDate(ftpFile.getTimestamp().getTime());
                file.setDirectory(ftpFile.isDirectory());
                result.add(file);
            }
        } catch (IOException e) {
            throw new FileSystemException(e.getMessage());
        }
        return result;
    }

    @Override
    public void changeCurrentDir(String d) throws FileSystemException {
        String path = getCurrentDirStr();
        if (!getCurrentDir().endsWith("/")) path += "/";
        d = replace(d);
        if (d.equals("~")) {
            path = host.getDrive()+archive.getPath();
        } else if (d.equals(".")) {
            return;
        } else {
            path +=d;
        }
        try {
            if (!client.changeWorkingDirectory(path)) {
                throw new FileSystemException("unbekanntes Directory");
            }
            this.currentDir = normalizePath(path);
        } catch (IOException e) {
            throw new FileSystemException("unbekanntes Directory");
        }
    }


    @Override
    public void makeDir(String d) throws FileSystemException {
        try {
            if (!client.makeDirectory(archive.getPath()+currentDir+"/"+d)) {
                throw new FileSystemException("Verzeichnis kann nicht angelegt werden");
            }
        } catch (IOException e) {
            throw new FileSystemException("Verzeichnis kann nicht angelegt werden: "+e.getMessage());
        }
    }

    @Override
    public void removeDir(String d) throws FileSystemException {
        try {
            if (!client.removeDirectory(archive.getPath()+currentDir+"/"+d)) {
                throw new FileSystemException("Verzeichnis kann nicht gelöscht werden");
            }
        } catch (IOException e) {
            throw new FileSystemException("Verzeichnis kann nicht gelöscht werden: "+e.getMessage());
        }
    }

    public static boolean isAvailable(Host host) {
        FTPClient client = new FTPClient();
        try {
            client.connect(host.getAddress());
            int reply = client.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                client.disconnect();
                return false;
            }
            client.disconnect();
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    private String normalizePath(String s) {
        String r = s.substring(archive.getPath().length());
        String splittedPath[] = r.split("/");

        List<String> pathElements = new ArrayList();

        for (String pathElement : splittedPath) {
            if (pathElement.equals("..")) {
                pathElements.remove(pathElements.size()-1);
            } else {
                pathElements.add(pathElement);
            }
        }
        r="";
        for (String x : pathElements) {
            if (x.length()>0) {
                r+="/";
                r+=x;
            }
        }
        return r.length()==0 ? "/" : r;
    }
}
