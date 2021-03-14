package de.wko.mdb.data.entity;

import de.wko.mdb.types.util.Util;
import de.wko.mdb.types.Album;
import de.wko.mdb.types.Subalbum;

import javax.persistence.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="albums")
@NamedQueries({
        @NamedQuery(
                name = "AlbumEntity.findAlbumByPattern",
                query = "SELECT a FROM AlbumEntity a WHERE a.name LIKE CONCAT('%',?1,'%')"
        ),
        @NamedQuery(
                name = "AlbumEntity.findByArtist",
                query = "SELECT a FROM AlbumEntity a WHERE a.artist.id = ?1"
        ),
        @NamedQuery(
                name = "AlbumEntity.findAll",
                query = "SELECT a FROM AlbumEntity a ORDER BY ?1"
        )
})
@NamedNativeQueries({
        @NamedNativeQuery(
                name = "AlbumEntity.findByArtistId",
                query = "SELECT * FROM albums WHERE artist_id = :artistId ",
                resultClass = AlbumEntity.class
        ),
        @NamedNativeQuery(
                name = "AlbumEntity.getCount",
                query = "SELECT COUNT(*) FROM albums"
        )
})

@SqlResultSetMapping(
        name="BlurAlbumResult",
        entities = { @EntityResult(entityClass = AlbumEntity.class) },
        classes = { @ConstructorResult(targetClass = Integer.class, columns = { @ColumnResult(name = "score", type = Integer.class) })}
)

public class AlbumEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Date released;
    private String cover;
    private String coversmall;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "parent")
    private List<SubalbumEntity> subalbums;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "artist_id")
    private ArtistEntity artist;

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

    public Date getReleased() {
        return released;
    }

    public void setReleased(Date release) {
        this.released = release;
    }

    public List<SubalbumEntity> getSubalbums() {
        return subalbums;
    }

    public void setSubalbums(List<SubalbumEntity> subalbums) {
        this.subalbums = subalbums;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getCoversmall() {
        return coversmall;
    }

    public void setCoversmall(String coversmall) {
        this.coversmall = coversmall;
    }

    public ArtistEntity getArtist() {
        return artist;
    }

    public void setArtist(ArtistEntity artist) {
        this.artist = artist;
    }


    public Album getType() {
        Album a = new Album();
        a.setId(this.id);
        a.setName(this.name);
        a.setCover(this.cover);
        a.setCoversmall(this.coversmall);
        a.setRelease(Util.sdf.format(released));
        for (SubalbumEntity sae : this.getSubalbums()) {
            a.addSubalbum(sae.getType());
        }
        a.setArtist(this.artist.getType());
        return a;
    }

    public void fromType(Album album) {
        this.id = album.getId();
        this.name = album.getName();
        this.cover = album.getCover();
        this.coversmall = album.getCoversmall();
        try {
            this.released = Util.normalizeDate(Util.sdf.parse(album.getRelease()));
        } catch (ParseException e) {
            this.released = null;
        }
        this.subalbums = new ArrayList<>();
        for (Subalbum sa : album.getSubalbums()) {
            SubalbumEntity sae = new SubalbumEntity();
            sae.fromType(sa);
            this.subalbums.add(sae);
        }
        ArtistEntity are = new ArtistEntity();
        are.fromType(album.getArtist());
        this.artist = are;
    }

}
