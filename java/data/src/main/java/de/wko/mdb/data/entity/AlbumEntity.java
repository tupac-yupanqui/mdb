package de.wko.mdb.data.entity;

import de.wko.mdb.types.Album;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="albums")
@NamedQueries({
        @NamedQuery(
                name = "AlbumEntity.findByArtist",
                query = "SELECT a FROM AlbumEntity a WHERE a.artistId = ?1"
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
public class AlbumEntity {

    @Id
    private Long id;
    private String name;
    @Column(name = "artist_id")
    private Long artistId;
    private Date release;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "parent")
    private List<SubalbumEntity> subalbums;

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

    public Date getRelease() {
        return release;
    }

    public void setRelease(Date release) {
        this.release = release;
    }

    public Long getArtistId() {
        return artistId;
    }

    public void setArtistId(Long artistId) {
        this.artistId = artistId;
    }

    public List<SubalbumEntity> getSubalbums() {
        return subalbums;
    }

    public void setSubalbums(List<SubalbumEntity> subalbums) {
        this.subalbums = subalbums;
    }

    public Album getType() {
        Album a = new Album();
        a.setId(this.id);
        a.setName(this.name);
        for (SubalbumEntity sae : this.getSubalbums()) {
            a.addSubalbum(sae.getType());
        }
        return a;
    }

}
