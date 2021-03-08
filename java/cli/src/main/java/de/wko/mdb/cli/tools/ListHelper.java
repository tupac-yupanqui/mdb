package de.wko.mdb.cli.tools;

import de.wko.mdb.types.Folder;
import de.wko.mdb.types.FolderContent;
import de.wko.mdb.types.MdbFile;
import de.wko.mdb.types.FileObject;

import java.util.ArrayList;
import java.util.List;

public class ListHelper {

    public static boolean isFileInTrackList(MdbFile file, List<FileObject> tracklist) {
        for (FileObject t : tracklist) {
            if (t.getFilename().equals(file.getName())) return true;
        }
        return false;
    }

    public static boolean isFileInFolderList(MdbFile file, List<Folder> folderlist) {
        for (Folder f : folderlist) {
            if (f.getName().equals(file.getName())) return true;
        }
        return false;
    }

    public static FolderContent mergeContent(FolderContent fc1, FolderContent fc2) {
        FolderContent result = new FolderContent();
        ArrayList<Folder> fl = new ArrayList<>();
        fl.addAll(fc1.getFolderList());
        fl.addAll(fc2.getFolderList());
        result.setFolderList(fl);
        ArrayList<FileObject> tl = new ArrayList<>();
        tl.addAll(fc1.getTrackList());
        tl.addAll(fc2.getTrackList());
        result.setTrackList(tl);
        return result;
    }
}
