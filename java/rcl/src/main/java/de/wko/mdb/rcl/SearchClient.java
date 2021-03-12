package de.wko.mdb.rcl;

import de.wko.mdb.types.ScoredArtist;
import de.wko.mdb.types.ScoredTitel;
import de.wko.mdb.types.query.SearchArtistBlurQuery;
import de.wko.mdb.types.query.SearchTitelBlurQuery;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class SearchClient extends RestClient {

    public List<ScoredTitel> searchTitelBlur(SearchTitelBlurQuery request) throws MdbRestException {
        ScoredTitel[] list = post(RestConfig.PATH_SEARCH_TITLE_BLUR, request, ScoredTitel[].class);
        return Arrays.asList(list.clone());
    }

    public List<ScoredArtist> searchArtistBlur(SearchArtistBlurQuery request) throws MdbRestException {
        ScoredArtist[] list = post(RestConfig.PATH_SEARCH_ARTIST_BLUR, request, ScoredArtist[].class);
        return Arrays.asList(list.clone());
    }
}
