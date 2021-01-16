package de.wko.mdb.data.repository;

import de.wko.mdb.data.entity.AlbumEntity;
import de.wko.mdb.data.filter.AlbumFilter;
import de.wko.mdb.types.AlbumDetails;

import java.util.List;

public interface AlbumRepositoryCustom {

    List<AlbumEntity> findFilteredAlbums(AlbumFilter filter);

    Long countFilteredAlbums(AlbumFilter filter);

    AlbumEntity getAlbum(int id);
}
