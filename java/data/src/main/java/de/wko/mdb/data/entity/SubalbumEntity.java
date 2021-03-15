package de.wko.mdb.data.entity;

import de.wko.mdb.types.Subalbum;

import javax.persistence.*;

@NamedQueries({

        @NamedQuery(name = "SubalbumEntity.findTitellistByAlbumId",
                query = "SELECT s FROM SubalbumEntity s WHERE s.name='Titelliste' AND s.parent.id = ?1"
        )
})

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

    public AlbumEntity getParent() {
        return parent;
    }

    public void setParent(AlbumEntity parent) {
        this.parent = parent;
    }

    public Subalbum getType() {
        Subalbum a = new Subalbum();
        a.setId(this.id);
        a.setName(this.name);
        a.setParentId(this.parent.getId());
        return a;
    }

    public void fromType(Subalbum subalbum) {
        this.id = subalbum.getId();
        this.name = subalbum.getName();
    }

}
