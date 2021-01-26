package de.wko.mdb.types;

import java.util.List;

public class TitelList {
    String name;
    List<Titel> list;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Titel> getList() {
        return list;
    }

    public void setList(List<Titel> list) {
        this.list = list;
    }
}
