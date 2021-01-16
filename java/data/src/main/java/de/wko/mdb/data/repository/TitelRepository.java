package de.wko.mdb.data.repository;

import de.wko.mdb.data.entity.TitelEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TitelRepository extends CrudRepository<TitelEntity,Long>, TitelRepositoryCustom {
    @Query(name = "TitelEntity.getByAlbum")
    List<TitelEntity> getByAlbumId(long albumId);

    @Query(name = "TitelEntity.getBySubalbum")
    List<TitelEntity> getBySubalbumId(long subalbumId);

}
