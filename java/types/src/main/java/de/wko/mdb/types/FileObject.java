package de.wko.mdb.types;

import de.wko.mdb.types.enums.EFileType;

public class FileObject {
    private Long id = 0L;
    private Long objectId;
    private String filename;
    private Long folderId;
    private EFileType type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getObjectId() {
        return objectId;
    }

    public void setObjectId(Long objectId) {
        this.objectId = objectId;
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

    public EFileType getType() {
        return type;
    }

    public void setType(EFileType type) {
        this.type = type;
    }
}
