package de.wko.mdb.data.repository;

import de.wko.mdb.data.entity.TrackEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TrackRepository extends CrudRepository<TrackEntity, Long> {

    TrackEntity findById(long id);

    @Query(name = "TrackEntity.getTrackByFolderId")
    List<TrackEntity> getTrackByFolderId(long folderId);

}
