package de.wko.mdb.cli.tables;

import de.wko.mdb.types.Archive;
import de.wko.mdb.types.Host;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArchiveTable extends DataTable {

    private List<Archive> list;
    private List<Host> hosts = null;

    public ArchiveTable(Archive a) {
        list = new ArrayList<>();
        list.add(a);
        createColumns();
    }

    public ArchiveTable(List<Archive> list) {
        this.list = list;
        createColumns();
    }

    public ArchiveTable(List<Archive> list, List<Host> hosts) {
        this.list = list;
        this.hosts = hosts;
        createColumns();
    }

    private void createColumns() {
        columns.add(new Column(3,   "ID",               ALIGN_RIGHT));
        columns.add(new Column(20,  "Name",             ALIGN_LEFT));
        columns.add(new Column(25,  "Pfad",             ALIGN_LEFT));
        columns.add(new Column(20,  "Typ",              ALIGN_LEFT));
        if (hosts!=null) {
            columns.add(new Column(20,  "Host",              ALIGN_LEFT));
        }
        columns.add(new Column(25,  "Descr",            ALIGN_LEFT));
    }

    public void print() {
        printHeader();
        for (Archive a : list) {
            if (hosts==null) {
                printRow(a.getId().toString(), a.getName(), a.getPath(), a.getType().getDescr(), a.getDescr());
            } else {
                printRow(a.getId().toString(), a.getName(), getDrive(a.getHostId())+a.getPath(), a.getType().getDescr(), getHostName(a.getHostId()), a.getDescr());
            }
        }
        printFooter();
    }

    private String getHostName(Long id) {
        for (Host h : hosts) {
            if (h.getId()==id) {
                return h.getName();
            }
        }
        return "";
    }
    private String getDrive(Long id) {
        for (Host h : hosts) {
            if (h.getId()==id && h.getDrive()!=null) {
                return h.getDrive();
            }
        }
        return "";
    }
}
