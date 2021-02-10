package de.wko.mdb.types;

public class Track {
    private Long id;
    private Long titelId;
    private Long folderId;
    private String filename;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTitelId() {
        return titelId;
    }

    public void setTitelId(Long titelId) {
        this.titelId = titelId;
    }

    public Long getFolderId() {
        return folderId;
    }

    public void setFolderId(Long folderId) {
        this.folderId = folderId;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
