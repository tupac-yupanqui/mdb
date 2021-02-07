package de.wko.mdb.types;

import de.wko.mdb.types.enums.EArchiveType;

public class Archive {
    private Long id = 0L;
    private String name;
    private String path;
    private String descr;
    private EArchiveType type = EArchiveType.UNKNOWN;
    private Long hostId;

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

    public EArchiveType getType() {
        return type;
    }

    public void setType(EArchiveType type) {
        this.type = type;
    }

    public Long getHostId() {
        return hostId;
    }

    public void setHostId(Long hostId) {
        this.hostId = hostId;
    }
}
