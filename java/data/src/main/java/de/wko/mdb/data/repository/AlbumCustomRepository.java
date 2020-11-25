package de.wko.mdb.data.repository;

import de.wko.mdb.data.entity.AlbumEntity;

import java.util.List;

public interface AlbumCustomRepository {
    public List<AlbumEntity> findByArtistId(Long artistId);
}
