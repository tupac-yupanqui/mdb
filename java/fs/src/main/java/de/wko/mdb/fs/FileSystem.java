package de.wko.mdb.fs;

public abstract class FileSystem {
    abstract public void close();
    abstract public String getCurrentDir();
    abstract public void changeCurrentDir(String d);

}
