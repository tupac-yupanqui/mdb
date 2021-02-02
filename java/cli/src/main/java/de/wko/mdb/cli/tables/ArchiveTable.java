package de.wko.mdb.cli.tables;

import de.wko.mdb.types.Archive;
import de.wko.mdb.types.Host;

import java.util.List;

public class ArchiveTable extends DataTable {

    private List<Archive> list;

    public ArchiveTable(List<Archive> list) {
        this.list = list;
        columns = new Column[]  {
                new Column(3,   "ID",               ALIGN_RIGHT),
                new Column(35,  "Pfad",             ALIGN_LEFT),
                new Column(20,  "Typ",              ALIGN_LEFT),
                new Column(40,  "Description",      ALIGN_LEFT)
        };
    }

    public void print() {
        printHeader();
        for (Archive a : list) {
            printRow(a.getId().toString(), a.getPath(), a.getType().getDescr(), a.getDescr());
        }
        printFooter();
    }

}
