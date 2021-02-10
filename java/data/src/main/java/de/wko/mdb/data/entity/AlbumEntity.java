package de.wko.mdb.data.entity;

import de.wko.mdb.data.Util;
import de.wko.mdb.types.Album;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="albums")
@NamedQueries({
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
public class AlbumEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Date release;
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

    public Date getRelease() {
        return release;
    }

    public void setRelease(Date release) {
        this.release = release;
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
        a.setRelease(Util.sdf.format(release));
        for (SubalbumEntity sae : this.getSubalbums()) {
            a.addSubalbum(sae.getType());
        }
        a.setArtist(this.artist.getType());
        return a;
    }

}
