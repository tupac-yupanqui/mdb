package de.wko.mdb.data.repository;

import de.wko.mdb.data.entity.ArchiveEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ArchiveRepository extends CrudRepository<ArchiveEntity,Long>, ArchiveRepositoryCustom {

    ArchiveEntity findById(long id);

    Iterable<ArchiveEntity> findAll(Sort by);

    @Query(name = "ArchiveEntity.findByHost")
    List<ArchiveEntity> findByHost(Long hostId);

}
