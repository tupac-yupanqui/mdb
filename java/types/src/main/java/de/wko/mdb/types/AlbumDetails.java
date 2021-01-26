package de.wko.mdb.types;

import java.util.List;
import java.util.Map;

public class AlbumDetails {
    private Album album;

    private List<TitelList> titels;

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public List<TitelList> getTitels() {
        return titels;
    }

    public void setTitels(List<TitelList> titels) {
        this.titels = titels;
    }
}
