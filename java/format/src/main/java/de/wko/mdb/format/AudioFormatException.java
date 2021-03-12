package de.wko.mdb.format;

public class AudioFormatException extends Exception {
    String msg;

    public AudioFormatException() {

    }

    public AudioFormatException(String m) {
        this.msg = m;
    }

    public String getMessage() {
        return msg;
    }
}
