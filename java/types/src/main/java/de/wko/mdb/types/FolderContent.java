package de.wko.mdb.types;

import java.util.List;

public class FolderContent {
    private List<Folder> folderList;
    private List<FileObject> trackList;

    public List<Folder> getFolderList() {
        return folderList;
    }

    public void setFolderList(List<Folder> folderList) {
        this.folderList = folderList;
    }

    public List<FileObject> getTrackList() {
        return trackList;
    }

    public void setTrackList(List<FileObject> trackList) {
        this.trackList = trackList;
    }

}
