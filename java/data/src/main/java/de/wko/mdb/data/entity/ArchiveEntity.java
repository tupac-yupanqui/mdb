package de.wko.mdb.data.entity;

import de.wko.mdb.types.Archive;
import de.wko.mdb.types.enums.EArchiveType;

import javax.persistence.*;

@Entity
@Table(name="archives")
@NamedQueries({
        @NamedQuery(
                name = "ArchiveEntity.findByHost",
                query = "SELECT a FROM ArchiveEntity a WHERE a.hostId = ?1"
        ),
})
public class ArchiveEntity {
    @Id
    @GeneratedValue
    private Long id;
    @Basic(optional = false)
    @Column(nullable = false)
    private String path;

    private String descr;
    private String name;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private EArchiveType archType;

    @Column(name = "host_id")
    private Long hostId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public EArchiveType getArchType() {
        return archType;
    }

    public void setArchType(EArchiveType archType) {
        this.archType = archType;
    }

    public Long getHostId() {
        return hostId;
    }

    public void setHostId(Long hostId) {
        this.hostId = hostId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Archive getType() {
        Archive a = new Archive();
        a.setId(this.id);
        a.setName(this.name);
        a.setDescr(this.descr);
        a.setPath(this.path);
        a.setType(this.archType);
        a.setHostId(this.hostId);
        return a;
    }

    public void fromType(Archive archive) {
        this.id = archive.getId();
        this.name = archive.getName();
        this.descr = archive.getDescr();
        this.path = archive.getPath();
        this.archType = archive.getType();
        this.hostId = archive.getHostId();
    }

}
