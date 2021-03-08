package de.wko.mdb.data.entity;

import de.wko.mdb.types.FileObject;
import de.wko.mdb.types.enums.EFileType;

import javax.persistence.*;

@Entity
@Table(name="files")
@NamedQueries({
        @NamedQuery(
                name = "FileEntity.getFilesByFolderId",
                query = "SELECT f FROM FileEntity f WHERE f.folderId = ?1 ORDER BY f.filename"
        ),

})
public class FileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic(optional = false)
    @Column(nullable = false)
    private String filename;

    @Column(name = "object_id")
    private Long objectId;

    @Column(name = "folder_id")
    private Long folderId;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private EFileType fileType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Long getObjectId() {
        return objectId;
    }

    public void setObjectId(Long titelId) {
        this.objectId = titelId;
    }

    public Long getFolderId() {
        return folderId;
    }

    public void setFolderId(Long folderId) {
        this.folderId = folderId;
    }

    public EFileType getFileType() {
        return fileType;
    }

    public void setFileType(EFileType fileType) {
        this.fileType = fileType;
    }

    public FileObject getType() {
        FileObject t = new FileObject();
        t.setId(this.id);
        t.setFilename(this.filename);
        t.setFolderId(this.folderId);
        t.setObjectId(this.objectId);
        t.setType(this.getFileType());
        return t;
    }

    public void fromType(FileObject file) {
        this.id = file.getId();
        this.filename = file.getFilename();
        this.objectId = file.getObjectId();
        this.folderId = file.getFolderId();
        this.fileType = file.getType();
    }

}
