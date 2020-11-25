package de.wko.mdb.rest;

import de.wko.mdb.data.service.AlbumService;
import de.wko.mdb.data.service.ArtistService;
import de.wko.mdb.types.Album;
import de.wko.mdb.types.Artist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DataController {

    @Autowired
    ArtistService service;
    @Autowired
    AlbumService albumservice;

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
    public List<Album> albums() {
        return albumservice.getByArtistId(1L);
    }

}
