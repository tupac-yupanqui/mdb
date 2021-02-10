package de.wko.mdb.data.entity;

import de.wko.mdb.types.Host;
import de.wko.mdb.types.Track;

import javax.persistence.*;

@Entity
@Table(name="tracks")
@NamedQueries({
        @NamedQuery(
                name = "TrackEntity.getTrackByFolderId",
                query = "SELECT t FROM TrackEntity t WHERE t.folderId = ?1 ORDER BY t.filename"
        ),
})
public class TrackEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic(optional = false)
    @Column(nullable = false)
    private String filename;

    @Column(name = "titel_id")
    private Long titelId;

    @Column(name = "folder_id")
    private Long folderId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Long getTitelId() {
        return titelId;
    }

    public void setTitelId(Long titelId) {
        this.titelId = titelId;
    }

    public Long getFolderId() {
        return folderId;
    }

    public void setFolderId(Long folderId) {
        this.folderId = folderId;
    }

    public Track getType() {
        Track t = new Track();
        t.setId(this.id);
        t.setFilename(this.filename);
        t.setFolderId(this.folderId);
        t.setTitelId(this.titelId);
        return t;
    }

    public void fromType(Track track) {
        this.id = track.getId();
        this.filename = track.getFilename();
        this.titelId = track.getTitelId();
        this.folderId = track.getFolderId();
    }

}
