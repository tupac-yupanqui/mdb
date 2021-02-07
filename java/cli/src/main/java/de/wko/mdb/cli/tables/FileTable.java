package de.wko.mdb.cli.tables;

import de.wko.mdb.types.Host;
import de.wko.mdb.types.MdbFile;

import java.text.SimpleDateFormat;
import java.util.List;

public class FileTable extends DataTable{
    private List<MdbFile> list;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

    public FileTable(List<MdbFile> list) {
        this.list = list;
        columns.add(new Column(50,   "Name",       ALIGN_LEFT));
        columns.add(new Column(20,  "Datum",     ALIGN_LEFT));
        columns.add(new Column(10,  "Größe",  ALIGN_RIGHT));
    }

    public void print() {
        printHeader();
        for (MdbFile f : list) {
            printRow(truncate(f.getName(),50), sdf.format(f.getDate()), f.isDirectory() ? "<DIR>": f.getSize().toString());
        }
        printFooter();
    }

}
