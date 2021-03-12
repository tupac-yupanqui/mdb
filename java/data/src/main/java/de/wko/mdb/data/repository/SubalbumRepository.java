package de.wko.mdb.data.repository;

import de.wko.mdb.data.entity.SubalbumEntity;
import org.springframework.data.repository.CrudRepository;

public interface SubalbumRepository extends CrudRepository<SubalbumEntity,Long>, SubalbumRepositoryCustom {

    SubalbumEntity findById(long id);
}
