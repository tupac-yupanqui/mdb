package de.wko.mdb.data.repository;

import de.wko.mdb.data.entity.AlbumEntity;
import de.wko.mdb.data.filter.AlbumFilter;
import de.wko.mdb.types.AlbumDetails;
import de.wko.mdb.types.ScoredAlbum;
import de.wko.mdb.types.ScoredArtist;
import de.wko.mdb.types.query.SearchAlbumBlurQuery;
import de.wko.mdb.types.query.SearchArtistBlurQuery;

import java.util.List;

public interface AlbumRepositoryCustom {

    List<AlbumEntity> findFilteredAlbums(AlbumFilter filter);

    Long countFilteredAlbums(AlbumFilter filter);

    AlbumEntity getAlbum(Long id);

    List<ScoredAlbum> searchAlbumBlur(SearchAlbumBlurQuery searchQuery);

}
