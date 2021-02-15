package de.wko.mdb.cli.tables;

import de.wko.mdb.types.Folder;
import de.wko.mdb.types.FolderContent;
import de.wko.mdb.types.Host;
import de.wko.mdb.types.Track;

import java.util.ArrayList;
import java.util.List;

public class FolderTable extends DataTable {
    private List<Folder> folders;
    private List<Track> tracks;

    public FolderTable(List<Folder> list) {
        this.folders = list;
        this.tracks = new ArrayList<>();
        createColumns();
    }

    public FolderTable(FolderContent content) {
        this.folders = content.getFolderList();
        this.tracks = content.getTrackList();
        createColumns();
    }

    private void createColumns()  {
        columns.add(new Column(6,   "ID",       ALIGN_RIGHT));
        columns.add(new Column(55,  "Name",     ALIGN_LEFT));
        columns.add(new Column(20,   "Type",     ALIGN_LEFT));
    }

    public void print() {
        printHeader();
        if (folders!=null) {
            for (Folder f : folders) {
                printRow(f.getId().toString(), f.getName(), f.getType()==null?"NULL":f.getType().getDescr());
            }
        }
        if (tracks!=null) {
            for (Track t : tracks) {
                printRow(t.getId()!=null?t.getId().toString():"", t.getFilename(), t.getId()==null ? "?" : "Track");
            }
        }
        printFooter();
    }

}
