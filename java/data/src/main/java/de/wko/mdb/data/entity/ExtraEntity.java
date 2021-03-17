package de.wko.mdb.data.entity;

import de.wko.mdb.types.Album;
import de.wko.mdb.types.Extra;
import de.wko.mdb.types.Subalbum;
import de.wko.mdb.types.enums.EExtraType;
import de.wko.mdb.types.util.Util;

import javax.persistence.*;
import java.text.ParseException;
import java.util.ArrayList;

@Entity
@Table(name="extras")
public class ExtraEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Long albumId;
    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    EExtraType extraTtype;
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

    public EExtraType getExtraType() {
        return extraTtype;
    }

    public void setExtraType(EExtraType extraTtype) {
        this.extraTtype = extraTtype;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public Extra getType() {
        Extra e = new Extra();
        e.setId(this.id);
        e.setAlbumId(this.albumId);
        e.setType(this.extraTtype);
        e.setDescription(this.description);
        return e;
    }

    public void fromType(Extra extra) {
        this.id = extra.getId();
        this.albumId = extra.getAlbumId();
        this.extraTtype = extra.getType();
        this.description = extra.getDescription();
    }

}
