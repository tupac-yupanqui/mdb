package de.wko.mdb.data.entity;

import de.wko.mdb.types.Archive;
import de.wko.mdb.types.Folder;
import de.wko.mdb.types.enums.EFolderType;

import javax.persistence.*;

@Entity
@Table(name="folders")
@NamedQueries({
        @NamedQuery(
                name = "FolderEntity.findRootFolder",
                query = "SELECT f FROM FolderEntity f WHERE f.archiveId = ?1 AND f.parentId = 0"
        ),
        @NamedQuery(
                name = "FolderEntity.getFolderByArchiveAndPath",
                query = "SELECT f FROM FolderEntity f WHERE f.archiveId = ?1 AND f.name= ?2"
        ),
        @NamedQuery(
                name = "FolderEntity.getFolderByParent",
                query = "SELECT f FROM FolderEntity f WHERE f.parentId = ?1 ORDER by f.name"
        ),
        @NamedQuery(
                name = "FolderEntity.getFolderByParentAndPath",
                query = "SELECT f FROM FolderEntity f WHERE f.parentId = ?1 AND f.name = ?2"
        ),
})

public class FolderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Basic(optional = false)
    @Column(nullable = false)
    private String name;

    @Column(name = "parent_id")
    private Long parentId;

    @Column(name = "archive_id")
    private Long archiveId;

    @Column(name = "object_id")
    private Long objectId;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private EFolderType folderType;

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

    public EFolderType getFolderType() {
        return folderType;
    }

    public void setFolderType(EFolderType type) {
        this.folderType = type;
    }

    public Folder getType() {
        Folder f = new Folder();
        f.setId(this.id);
        f.setName(this.name);
        f.setParentId(this.parentId);
        f.setArchiveId(this.archiveId);
        f.setObjectId(this.objectId);
        f.setType(this.folderType);
        return f;
    }

    public void fromType(Folder folder) {
        this.id = folder.getId();
        this.name = folder.getName();
        this.parentId = folder.getParentId();
        this.archiveId = folder.getArchiveId();
        this.objectId = folder.getObjectId();
        this.folderType = folder.getType();
    }

}
