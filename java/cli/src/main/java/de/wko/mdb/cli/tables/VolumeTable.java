package de.wko.mdb.cli.tables;

import de.wko.mdb.types.Archive;
import de.wko.mdb.types.Host;
import de.wko.mdb.types.Volume;

import java.util.ArrayList;
import java.util.List;

public class VolumeTable extends DataTable {

    private List<Volume> list;

    public VolumeTable(Volume v) {
        list = new ArrayList<>();
        list.add(v);
        createColumns();
    }

    public VolumeTable(List<Volume> list) {
        this.list = list;
        createColumns();
    }

    private void createColumns() {
        columns.add(new Column(10,  "LW",    ALIGN_LEFT));
        columns.add(new Column(30,  "name",  ALIGN_LEFT));
        columns.add(new Column(30,  "Type",  ALIGN_LEFT));
    }

    public void print() {
        printHeader();
        for (Volume v : list) {
            printRow(v.getDrive(), v.getName(), v.getType());
        }
        printFooter();
    }

}
