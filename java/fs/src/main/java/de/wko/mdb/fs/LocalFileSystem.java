package de.wko.mdb.fs;

public class LocalFileSystem extends FileSystem {
    public LocalFileSystem() {
    }

    public String getCurrentDir() {
        return System.getProperty("user.dir");
    }

    public void changeCurrentDir(String d) {

    }

    public void close() {

    }
}
