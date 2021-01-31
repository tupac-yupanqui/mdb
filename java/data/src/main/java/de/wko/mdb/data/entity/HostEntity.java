package de.wko.mdb.data.entity;

import de.wko.mdb.types.Artist;
import de.wko.mdb.types.Host;

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
    private boolean ftp;

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

    public Host getType() {
        Host h = new Host();
        h.setId(this.id);
        h.setName(this.name);
        h.setAddress(this.address);
        h.setFtp(this.ftp);
        return h;
    }

}
