package de.wko.mdb.data.repository;

import de.wko.mdb.data.entity.ExtraEntity;
import de.wko.mdb.data.entity.FileEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ExtraRepository extends CrudRepository<ExtraEntity,Long>, ExtraRepositoryCustom {
}
