package de.wko.mdb.types;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Album {
    private Long id;
    private String name;
    private Date release;
    private List<Subalbum> subalbums = null;

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

    public Date getRelease() {
        return release;
    }

    public void setRelease(Date release) {
        this.release = release;
    }

    public List<Subalbum> getSubalbums() {
        return subalbums;
    }

    public void setSubalbums(List<Subalbum> subalbums) {
        this.subalbums = subalbums;
    }

    public void addSubalbum(Subalbum sa) {
        if (subalbums==null) {
            subalbums = new ArrayList<>();
        }
        subalbums.add(sa);
    }
}
