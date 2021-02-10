package de.wko.mdb.types;

import de.wko.mdb.types.enums.EFolderType;

public class Folder {
    private Long id = 0L;
    private String name;
    private Long parentId;
    private Long archiveId;
    private Long objectId;
    private EFolderType type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getArchiveId() {
        return archiveId;
    }

    public void setArchiveId(Long archiveId) {
        this.archiveId = archiveId;
    }

    public Long getObjectId() {
        return objectId;
    }

    public void setObjectId(Long objectId) {
        this.objectId = objectId;
    }

    public EFolderType getType() {
        return type;
    }

    public void setType(EFolderType type) {
        this.type = type;
    }
}
