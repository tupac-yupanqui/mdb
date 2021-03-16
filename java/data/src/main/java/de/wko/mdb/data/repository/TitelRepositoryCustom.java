package de.wko.mdb.data.repository;

import de.wko.mdb.data.entity.TitelEntity;
import de.wko.mdb.types.ScoredTitel;
import de.wko.mdb.types.Titel;
import de.wko.mdb.types.query.SearchTitelBlurQuery;
import de.wko.mdb.types.query.SearchTitelQuery;

import java.util.List;

public interface TitelRepositoryCustom {
    List<ScoredTitel> searchTitelBlur(SearchTitelBlurQuery searchQuery);

    List<TitelEntity> findTitelByNameAndArtist(String name, Long artistId);
}
