package de.wko.mdb.data.repository;

import de.wko.mdb.data.entity.ArchiveEntity;
import de.wko.mdb.data.entity.ArtistEntity;
import de.wko.mdb.data.entity.FolderEntity;
import de.wko.mdb.data.entity.HostEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ArtistRepository extends CrudRepository<ArtistEntity,Long>, ArtistRepositoryCustom {

        ArtistEntity findById(long id);

        ArtistEntity findByName(String name);

        Iterable<ArtistEntity> findAll(Sort by);

        ArtistEntity save(ArtistEntity archive);

        @Query(name = "ArtistEntity.findArtistByPattern")
        List<ArtistEntity> findArtistByPattern(String pattern);

}
