package de.wko.mdb.data.entity;

import de.wko.mdb.types.Artist;
import de.wko.mdb.types.Titel;

import javax.persistence.*;

@Entity
@Table(name="titels")
@NamedQueries({
        @NamedQuery(
                name = "TitelEntity.getByAlbum",
                query = "SELECT t FROM TitelEntity t WHERE t.albumId = ?1 ORDER BY t.trackno"
        ),
        @NamedQuery(
                name = "TitelEntity.getBySubalbum",
                query = "SELECT t FROM TitelEntity t WHERE t.subalbumId = ?1 order by t.trackno"
        ),
})
public class TitelEntity {
    @Id
    private Long id;
    private String name;
    private int length;
    private String year;
    private String genre;
    private String version;
    private String comment;
    private int trackno;
    @Column(name = "subalbum_id")
    private Long subalbumId;
    @Column(name = "album_id")
    private Long albumId;
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

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getTrackno() {
        return trackno;
    }

    public void setTrackno(int trackno) {
        this.trackno = trackno;
    }

    public Long getSubalbumId() {
        return subalbumId;
    }

    public void setSubalbumId(Long subalbumId) {
        this.subalbumId = subalbumId;
    }

    public Long getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Long albumId) {
        this.albumId = albumId;
    }

    public Titel getType() {
        Titel t = new Titel();
        t.setId(this.id);
        t.setName(this.name);
        t.setLength(this.length);
        t.setComment(this.comment);
        t.setTracknr(this.trackno);
        t.setVersion(this.version);
        t.setArtist(this.artist.getType());
        return t;
    }

}
