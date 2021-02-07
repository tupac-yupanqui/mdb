package de.wko.mdb.fs;

import de.wko.mdb.types.Archive;
import de.wko.mdb.types.Host;
import de.wko.mdb.types.MdbFile;
import org.apache.commons.net.ftp.FTPFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ArchiveFileSystem extends AbstractFileSystem {
    protected Host host;
    protected Archive archive;
    protected String currentDir;

    public ArchiveFileSystem(Host host, Archive archive) {
        super();
        this.host = host;
        this.archive = archive;
        this.currentDir = "/";
        filesystemAvailable = AvailabiltyCheck.isArchiveOnline(archive, host);
    }

    @Override
    public void close() {
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
        File f = new File(path).getAbsoluteFile();
        if (f.exists() && f.isDirectory()) {
            try {
                this.currentDir = getCurrentDirShort(f);
            } catch (IOException e) {
                throw new FileSystemException("unbekanntes Directory");
            }
        } else {
            throw new FileSystemException("unbekanntes Directory");
        }

    }

    @Override
    public List<MdbFile> listDir(String d) throws FileSystemException {
        List<MdbFile> result = new ArrayList<>();
        try {
            File f = getCurrentDirFile();
            File[] files = f.listFiles();

            for (File osFile : files) {
                MdbFile file = new MdbFile();
                file.setName(osFile.getName());
                file.setSize(osFile.length());
                file.setDate(new Date(Files.getLastModifiedTime(Paths.get(osFile.getAbsolutePath())).toMillis()));
                file.setDirectory(osFile.isDirectory());
                result.add(file);
            }
        } catch (IOException e) {
            throw new FileSystemException(e.getMessage());
        }
        return result;
    }

    @Override
    public void makeDir(String d) throws FileSystemException {

    }

    @Override
    public void removeDir(String d) throws FileSystemException {

    }

    public Host getHost() {
        return host;
    }

    public void setHost(Host host) {
        this.host = host;
    }

    public Archive getArchive() {
        return archive;
    }

    public void setArchive(Archive archive) {
        this.archive = archive;
    }

    public String getCurrentDir() {
        return currentDir;
    }

    public void setCurrentDir(String currentDir) {
        this.currentDir = currentDir;
    }

    private File getCurrentDirFile() {
        return new File(host.getDrive()+archive.getPath()+currentDir);
    }
    protected String getCurrentDirStr() {
        String s = replace(host.getDrive()+archive.getPath()+currentDir);
        return s.length()==0 ? "/" : s;
    }
    protected String getCurrentDirShort(File d) throws IOException {
        String s = replace(d.getCanonicalPath().substring((host.getDrive()+archive.getPath()).length()));
        return s.length()==0 ? "/" : s;
    }
}
