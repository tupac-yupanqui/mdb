package de.wko.mdb.cli.tables;

import de.wko.mdb.types.Host;

import java.util.List;

public class HostTable extends DataTable{

    private static final char ALIGN_LEFT = 'l';
    private static final char ALIGN_RIGHT = 'r';
    private static final char ALIGN_CENTER = 'c';


    static {
        columns = new Column[]  {
                new Column(3,   "ID",       ALIGN_RIGHT),
                new Column(35,  "Name",     ALIGN_LEFT),
                new Column(35,  "Adresse",  ALIGN_LEFT),
                new Column(3,   "FTP",      ALIGN_CENTER)
        };
    }

    private List<Host> list;

    public HostTable(List<Host> list) {
        this.list = list;
    }

    public void print() {
        printHeader();
        for (Host h : list) {
            printColumn(columns[0], h.getId().toString(), false);
            printColumn(columns[1], h.getName(), false);
            printColumn(columns[2], h.getAddress(), false);
            printColumn(columns[3], h.isFtp()?"X":" ", true);
        }
        printFooter();
    }
}
