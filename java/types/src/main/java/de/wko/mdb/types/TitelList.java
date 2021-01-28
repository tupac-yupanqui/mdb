package de.wko.mdb.types;

import java.util.List;

public class TitelList {
    Subalbum subalbum;
    List<Titel> list;

    public Subalbum getSubalbum() {
        return subalbum;
    }

    public void setSubalbum(Subalbum subalbum) {
        this.subalbum = subalbum;
    }

    public List<Titel> getList() {
        return list;
    }

    public void setList(List<Titel> list) {
        this.list = list;
    }
}
