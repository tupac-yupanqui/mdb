package de.wko.mdb.data.repository;

import de.wko.mdb.data.entity.AlbumEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AlbumRepository extends CrudRepository<AlbumEntity,Long>, AlbumCustomRepository {
}
