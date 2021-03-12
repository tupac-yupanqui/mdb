package de.wko.mdb.rest.controller;

import de.wko.mdb.bl.service.ContentService;
import de.wko.mdb.types.ScoredArtist;
import de.wko.mdb.types.ScoredTitel;
import de.wko.mdb.types.query.SearchArtistBlurQuery;
import de.wko.mdb.types.query.SearchTitelBlurQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SearchController {

    @Autowired
    private ContentService contentService;

    @PostMapping("/search/title/blur")
    public List<ScoredTitel> searchTitelBlur(@RequestBody SearchTitelBlurQuery query) {
        return contentService.searchTitelBlur(query);
    }

    @PostMapping("/search/artist/blur")
    public List<ScoredArtist> searchArtistBlur(@RequestBody SearchArtistBlurQuery query) {
        return contentService.searchArtistBlur(query);
    }

}
