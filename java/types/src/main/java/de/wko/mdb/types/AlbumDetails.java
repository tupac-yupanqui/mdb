package de.wko.mdb.types;

import java.util.List;
import java.util.Map;

public class AlbumDetails {
    private Album album;

    private Map<String, List<Titel>> titels;

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public Map<String, List<Titel>> getTitels() {
        return titels;
    }

    public void setTitels(Map<String, List<Titel>> titels) {
        this.titels = titels;
    }
}
