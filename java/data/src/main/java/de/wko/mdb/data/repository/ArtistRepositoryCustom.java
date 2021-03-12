package de.wko.mdb.data.repository;

import de.wko.mdb.types.ScoredArtist;
import de.wko.mdb.types.query.SearchArtistBlurQuery;

import java.util.List;

public interface ArtistRepositoryCustom {
    List<ScoredArtist> searchArtistBlur(SearchArtistBlurQuery searchQuery);
}
