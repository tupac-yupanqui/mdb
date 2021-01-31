package de.wko.mdb.data.repository;

import de.wko.mdb.data.entity.HostEntity;
import org.springframework.data.repository.CrudRepository;

public interface HostRepository extends CrudRepository<HostEntity,Long> {
    HostEntity findById(long id);

    HostEntity findByName(String name);

    Iterable<HostEntity> findAll();

}
