package de.wko.mdb.data.entity;

import de.wko.mdb.types.Artist;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="artists")
public class ArtistEntity {
    @Id
    private Long id;
    private String name;

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

    public Artist getType() {
        Artist a = new Artist();
        a.setId(this.id);
        a.setName(this.name);
        return a;
    }
}
