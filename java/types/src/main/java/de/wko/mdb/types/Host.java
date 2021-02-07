package de.wko.mdb.types;

import de.wko.mdb.types.enums.EHostType;

public class Host {
    Long id = 0L;
    String name;
    String address;
    EHostType type = EHostType.UNKNOWN;
    String login;
    String password;
    String drive;

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

    public EHostType getType() {
        return type;
    }

    public void setType(EHostType type) {
        this.type = type;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDrive() {
        return drive==null?"":drive;
    }

    public void setDrive(String drive) {
        this.drive = drive;
    }
}
