package de.wko.mdb.fs;

import de.wko.mdb.types.Archive;
import de.wko.mdb.types.Host;

public class ArchiveFileSystem extends FileSystem {
    private Host host;
    private Archive archive;
    protected String currentDir;

    public ArchiveFileSystem(Host host, Archive archive) {
        super();
        this.host = host;
        this.archive = archive;
        this.currentDir = archive.getPath();
    }

    @Override
    public void close() {
    }

    public void changeCurrentDir(String d) {

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
}
