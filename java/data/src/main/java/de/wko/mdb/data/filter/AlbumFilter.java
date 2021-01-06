package de.wko.mdb.data.filter;

import de.wko.mdb.data.enums.AlbumSort;
import de.wko.mdb.data.enums.SortOrder;

public class AlbumFilter {
    private String album;
    private String artist;
    private String year;
    private AlbumSort sorttype;
    private SortOrder sortorder;

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public AlbumSort getSorttype() {
        return sorttype;
    }

    public void setSorttype(AlbumSort sorttype) {
        this.sorttype = sorttype;
    }

    public SortOrder getSortorder() {
        return sortorder;
    }

    public void setSortorder(SortOrder sortorder) {
        this.sortorder = sortorder;
    }
}
