package de.wko.mdb.fs;

import de.wko.mdb.types.Archive;
import de.wko.mdb.types.Host;
import de.wko.mdb.types.Login;
import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FtpFileSystem extends ArchiveFileSystem {
    private Login login;
    private FTPClient client;

    public FtpFileSystem(Host host, Archive archive) throws FileSystemException{
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
                throw new IOException("Exception in connecting to FTP Server");
            }

            if (!client.login(host.getLogin(), host.getPassword())) {
                throw new FileSystemException("Login fehlgeschlagen");
            }
            if (!client.changeWorkingDirectory(archive.getPath())) {
                throw new FileSystemException("Wurzelverzeichnis nicht gefunden");
            }
            currentDir = archive.getPath();
        } catch (IOException e) {
            e.printStackTrace();
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
}
