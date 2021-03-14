package de.wko.mdb.types;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Album {
    private Long id = 0L;
    private String name;
    private Artist artist;
    private String release;
    private String cover;
    private String coversmall;
    private List<Subalbum> subalbums = new ArrayList<>();

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

    public String getRelease() {
        return release;
    }

    public void setRelease(String release) {
        this.release = release;
    }

    public List<Subalbum> getSubalbums() {
        return subalbums;
    }

    public void setSubalbums(List<Subalbum> subalbums) {
        this.subalbums = subalbums;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getCoversmall() {
        return coversmall;
    }

    public void setCoversmall(String coversmall) {
        this.coversmall = coversmall;
    }

    public void addSubalbum(Subalbum sa) {
        subalbums.add(sa);
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }
}
