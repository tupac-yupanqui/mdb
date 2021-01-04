package de.wko.mdb.data.repository;

import de.wko.mdb.data.entity.AlbumEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AlbumRepository extends CrudRepository<AlbumEntity,Long>, AlbumRepositoryCustom {


    @Query(name = "AlbumEntity.findByArtistId")
    List<AlbumEntity> findByArtistId(long artistId);

    @Query(name = "AlbumEntity.findAll")
    List<AlbumEntity> findAll();

    @Query(name = "AlbumEntity.findByArtist")
    List<AlbumEntity> findByArtist(long artistId);

    @Query(name = "AlbumEntity.getCount")
    Integer getCount();

}
