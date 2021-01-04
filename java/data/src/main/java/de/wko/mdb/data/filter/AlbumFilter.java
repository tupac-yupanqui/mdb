package de.wko.mdb.data.filter;

import de.wko.mdb.data.enums.AlbumSort;
import de.wko.mdb.data.enums.SortOrder;

public class AlbumFilter {
    private String album;
    private String artist;
    private String year;
    private AlbumSort sort;
    private SortOrder order;

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

    public AlbumSort getSort() {
        return sort;
    }

    public void setSort(AlbumSort sort) {
        this.sort = sort;
    }

    public SortOrder getOrder() {
        return order;
    }

    public void setOrder(SortOrder order) {
        this.order = order;
    }
}
