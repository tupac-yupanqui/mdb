package de.wko.mdb.data.repository;

import de.wko.mdb.data.entity.ExtraEntity;
import org.springframework.data.repository.CrudRepository;

public interface ExtraRepository extends CrudRepository<ExtraEntity,Long>, ExtraRepositoryCustom {
}
