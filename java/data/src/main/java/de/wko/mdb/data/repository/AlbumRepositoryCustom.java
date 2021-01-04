package de.wko.mdb.data.repository;

import de.wko.mdb.data.entity.AlbumEntity;
import de.wko.mdb.data.filter.AlbumFilter;

import java.util.List;

public interface AlbumRepositoryCustom {

    List<AlbumEntity> findFilteredAlbums(AlbumFilter filter);

}
