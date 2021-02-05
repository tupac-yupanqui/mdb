package de.wko.mdb.data.entity;

import de.wko.mdb.types.Artist;
import de.wko.mdb.types.Host;
import de.wko.mdb.types.enums.EArchiveType;
import de.wko.mdb.types.enums.EHostType;

import javax.persistence.*;

@Entity
@Table(name="hosts")
public class HostEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Basic(optional = false)
    @Column(nullable = false)
    private String name;

    private String address;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private EHostType hostType;

    private String login;
    private String password;

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

    public EHostType getHostType() {
        return hostType;
    }

    public void setHostType(EHostType hostType) {
        this.hostType = hostType;
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

    public Host getType() {
        Host h = new Host();
        h.setId(this.id);
        h.setName(this.name);
        h.setAddress(this.address);
        h.setType(this.hostType);
        h.setLogin(this.login);
        h.setPassword(this.password);
        return h;
    }

    public void fromType(Host host) {
        this.id = host.getId();
        this.name = host.getName();
        this.address = host.getAddress();
        this.hostType = host.getType();
        this.login = host.getLogin();
        this.password = host.getPassword();
    }
}
