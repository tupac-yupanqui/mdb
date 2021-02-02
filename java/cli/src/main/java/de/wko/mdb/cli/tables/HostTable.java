package de.wko.mdb.cli.tables;

import de.wko.mdb.types.Host;

import java.util.List;

public class HostTable extends DataTable{

    private List<Host> list;

    public HostTable(List<Host> list) {
        this.list = list;
        columns = new Column[]  {
                new Column(3,   "ID",       ALIGN_RIGHT),
                new Column(35,  "Name",     ALIGN_LEFT),
                new Column(35,  "Adresse",  ALIGN_LEFT),
                new Column(3,   "FTP",      ALIGN_CENTER)
        };
    }

    public void print() {
        printHeader();
        for (Host h : list) {
            printRow(h.getId().toString(), h.getName(), h.getAddress(), h.isFtp()?"X":" ");
        }
        printFooter();
    }
}
