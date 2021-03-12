package de.wko.mdb.data.repository;

import de.wko.mdb.types.ScoredTitel;
import de.wko.mdb.types.query.SearchTitelBlurQuery;

import java.util.List;

public interface TitelRepositoryCustom {
    List<ScoredTitel> searchTitelBlur(SearchTitelBlurQuery searchQuery);
}
