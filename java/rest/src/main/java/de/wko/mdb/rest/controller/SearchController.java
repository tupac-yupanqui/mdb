package de.wko.mdb.rest.controller;

import de.wko.mdb.bl.service.ContentService;
import de.wko.mdb.types.ScoredAlbum;
import de.wko.mdb.types.ScoredArtist;
import de.wko.mdb.types.ScoredTitel;
import de.wko.mdb.types.Titel;
import de.wko.mdb.types.query.SearchAlbumBlurQuery;
import de.wko.mdb.types.query.SearchArtistBlurQuery;
import de.wko.mdb.types.query.SearchTitelBlurQuery;
import de.wko.mdb.types.query.SearchTitelQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SearchController {

    @Autowired
    private ContentService contentService;

    @PostMapping("/search/titel")
    public List<Titel> searchTitel(@RequestBody SearchTitelQuery query) {
        return contentService.searchTitel(query);
    }

    @PostMapping("/search/title/blur")
    public List<ScoredTitel> searchTitelBlur(@RequestBody SearchTitelBlurQuery query) {
        return contentService.searchTitelBlur(query);
    }

    @PostMapping("/search/artist/blur")
    public List<ScoredArtist> searchArtistBlur(@RequestBody SearchArtistBlurQuery query) {
        return contentService.searchArtistBlur(query);
    }

    @PostMapping("/search/album/blur")
    public List<ScoredAlbum> searchAlbumBlur(@RequestBody SearchAlbumBlurQuery query) {
        return contentService.searchAlbumBlur(query);
    }

}
