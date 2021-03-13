package de.wko.mdb.types;

public class Titel {
    Long id = 0L;
    private String name;
    private String version;
    private String comment;
    private Artist artist;
    private int tracknr;
    private int length;
    private String genre;
    private String year;
    private Long albumId = 0L;
    private Long subalbumId = 0L;

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

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public int getTracknr() {
        return tracknr;
    }

    public void setTracknr(int tracknr) {
        this.tracknr = tracknr;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Long getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Long albumId) {
        this.albumId = albumId;
    }

    public Long getSubalbumId() {
        return subalbumId;
    }

    public void setSubalbumId(Long subalbumId) {
        this.subalbumId = subalbumId;
    }
}
