package de.wko.mdb.fs;

import de.wko.mdb.types.MdbFile;

import java.util.List;

public abstract class AbstractFileSystem {
    protected boolean filesystemAvailable = false;
    protected String connectionStatus = "";

    abstract public void close();
    abstract public String getCurrentDir();
    abstract public void changeCurrentDir(String d) throws FileSystemException;
    abstract public List<MdbFile> listDir(String d) throws FileSystemException;
    abstract public void makeDir(String d) throws FileSystemException;
    abstract public void removeDir(String d) throws FileSystemException;

    public boolean isFilesystemAvailable() {
        return filesystemAvailable;
    }

    public void setFilesystemAvailable(boolean filesystemAvailable) {
        this.filesystemAvailable = filesystemAvailable;
    }

    public String getConnectionStatus() {
        return connectionStatus;
    }

    public void setConnectionStatus(String connectionStatus) {
        this.connectionStatus = connectionStatus;
    }

    public String replace(String p) {
        return p.replace('\\','/');
    }
}
