package de.wko.mdb.fs;

public class FileSystemException extends Exception {
    String msg;

    public FileSystemException() {

    }

    public FileSystemException(String m) {
        this.msg = m;
    }

    public String getMessage() {
        return msg;
    }
}
