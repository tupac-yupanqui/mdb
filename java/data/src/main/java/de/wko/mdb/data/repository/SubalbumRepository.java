package de.wko.mdb.data.repository;

import de.wko.mdb.data.entity.SubalbumEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface SubalbumRepository extends CrudRepository<SubalbumEntity,Long>, SubalbumRepositoryCustom {

    SubalbumEntity findById(long id);

    @Modifying
    List<SubalbumEntity> findTitellistByAlbumId(Long albumId);
}
