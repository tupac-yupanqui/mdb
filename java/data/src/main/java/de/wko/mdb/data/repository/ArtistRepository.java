package de.wko.mdb.data.repository;

import de.wko.mdb.data.entity.ArtistEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

public interface ArtistRepository extends CrudRepository<ArtistEntity,Long> {

        ArtistEntity findById(long id);

        Iterable<ArtistEntity> findAll(Sort by);
}
