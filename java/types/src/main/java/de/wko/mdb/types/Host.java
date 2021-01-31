package de.wko.mdb.types;

public class Host {
    Long id;
    String name;
    String address;
    boolean ftp = false;

    public Host() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isFtp() {
        return ftp;
    }

    public void setFtp(boolean ftp) {
        this.ftp = ftp;
    }
}
