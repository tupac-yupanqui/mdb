package de.wko.mdb.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.wko.mdb.data.filter.AlbumFilter;
import de.wko.mdb.bl.service.AlbumService;
import de.wko.mdb.bl.service.ArtistService;
import de.wko.mdb.rest.filter.FilterEditor;
import de.wko.mdb.types.Album;
import de.wko.mdb.types.AlbumDetails;
import de.wko.mdb.types.Artist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/dartist")
    public Artist artist() {
        Artist a = service.getById(1L);
        return a;
    }

    @GetMapping("/dartists")
    public List<Artist> artists() {
        return service.getAll();
    }

    @GetMapping("/dalbum")
    public Album album() {
        Album a = albumservice.getById(1L);
        return a;
    }

    @GetMapping("/dalbums")
    @ResponseBody
    public List<Album> albums(@RequestParam(required = false) AlbumFilter filter) {
        if (filter==null) filter = new AlbumFilter();
        return albumservice.getFilteredAlbums(filter);
    }

    @GetMapping("/dcounta")
    public Long counta(@RequestParam(required = false) AlbumFilter filter) {
        if (filter==null) filter = new AlbumFilter();
        return albumservice.countFilteredAlbums(filter);
    }


    @GetMapping("/dalbum/view")
    @ResponseBody
    public AlbumDetails getAlbumDetails(@RequestParam Long id) {
        return albumservice.getAlbumDetails(id);
    }
}
