package de.wko.mdb.data.entity;

import de.wko.mdb.types.Artist;
import de.wko.mdb.types.Host;

import javax.persistence.*;

@Entity
@Table(name="artists")
@NamedQueries({
        @NamedQuery(
                name = "ArtistEntity.findArtistByPattern",
                query = "SELECT a FROM ArtistEntity a WHERE a.name LIKE CONCAT('%',?1,'%')"
        )
})

@SqlResultSetMapping(
        name="BlurArtistResult",
        entities = { @EntityResult(entityClass = ArtistEntity.class) },
        classes = { @ConstructorResult(targetClass = Integer.class, columns = { @ColumnResult(name = "score", type = Integer.class) })}
)

public class ArtistEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Basic(optional = false)
    @Column(nullable = false)
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

    public void fromType(Artist artist) {
        this.id = artist.getId();
        this.name = artist.getName();
    }

}
