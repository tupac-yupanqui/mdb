package de.wko.mdb.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.wko.mdb.data.filter.AlbumFilter;
import de.wko.mdb.data.service.AlbumService;
import de.wko.mdb.data.service.ArtistService;
import de.wko.mdb.rest.filter.FilterEditor;
import de.wko.mdb.types.Album;
import de.wko.mdb.types.Artist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DataController {

    @Autowired
    ArtistService service;
    @Autowired
    AlbumService albumservice;
    @Autowired
    private ObjectMapper jacksonObjectMapper;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(AlbumFilter.class, new FilterEditor(jacksonObjectMapper));
    }

    @GetMapping("/artist")
    public Artist artist() {
        Artist a = service.getById(1L);
        return a;
    }

    @GetMapping("/artists")
    public List<Artist> artists() {
        return service.getAll();
    }

    @GetMapping("/album")
    public Album album() {
        Album a = albumservice.getById(1L);
        return a;
    }

    @GetMapping("/albums")
    @ResponseBody
    public List<Album> albums(@RequestParam AlbumFilter filter) {
        return albumservice.getFilteredAlbums(filter);
    }

}
