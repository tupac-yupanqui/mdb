package de.wko.mdb.data.entity;

import de.wko.mdb.types.Subalbum;

import javax.persistence.*;

@Entity
@Table(name="subalbums")
public class SubalbumEntity {
    @Id
    private Long id;
    private String name;
    @ManyToOne
    @JoinColumn(name="album_id", nullable=false)
    private AlbumEntity parent;


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

    public Subalbum getType() {
        Subalbum a = new Subalbum();
        a.setId(this.id);
        a.setName(this.name);
        return a;
    }

}