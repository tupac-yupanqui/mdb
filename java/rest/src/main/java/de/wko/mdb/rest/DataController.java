package de.wko.mdb.rest;

import de.wko.mdb.data.service.ArtistService;
import de.wko.mdb.types.Artist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DataController {

    @Autowired
    ArtistService service;

    @GetMapping("/data")
    public Artist demo() {
        Artist a = service.getById(1L);
        return a;
    }

    @GetMapping("/all")
    public List<Artist> demoall() {
        return service.getAll();
    }
}
