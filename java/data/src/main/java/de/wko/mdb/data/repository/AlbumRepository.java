package de.wko.mdb.data.repository;

import de.wko.mdb.data.entity.AlbumEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AlbumRepository extends CrudRepository<AlbumEntity,Long> {

    @Query(name = "AlbumEntity.findByArtistId")
    List<AlbumEntity> findByArtistId(long artistId);

    @Query(name = "AlbumEntity.findByArtist")
    List<AlbumEntity> findByArtist(long artistId);

    @Query(name = "AlbumEntity.getCount")
    Integer getCount();
}
