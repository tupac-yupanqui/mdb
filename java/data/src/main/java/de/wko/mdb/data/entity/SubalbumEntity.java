package de.wko.mdb.data.entity;

import de.wko.mdb.types.Subalbum;

import javax.persistence.*;

@Entity
@Table(name="subalbums")
public class SubalbumEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public void fromType(Subalbum subalbum) {
        this.id = subalbum.getId();
        this.name = subalbum.getName();
    }

}
