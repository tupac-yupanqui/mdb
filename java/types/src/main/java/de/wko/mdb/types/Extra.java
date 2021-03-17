package de.wko.mdb.types;

import de.wko.mdb.types.enums.EExtraType;

public class Extra {
    Long id = 0L;
    Long albumId;
    EExtraType type;
    String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Long albumId) {
        this.albumId = albumId;
    }

    public EExtraType getType() {
        return type;
    }

    public void setType(EExtraType type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
