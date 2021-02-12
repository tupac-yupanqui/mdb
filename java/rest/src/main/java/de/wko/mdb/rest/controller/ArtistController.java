package de.wko.mdb.rest.controller;

import de.wko.mdb.bl.service.ArtistService;
import de.wko.mdb.types.Artist;
import de.wko.mdb.types.Host;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ArtistController {
    @Autowired
    ArtistService artistService;

    @GetMapping("/artist/{id}")
    @ResponseBody
    public Artist getById(@PathVariable Long id) {
        return artistService.getById(id);
    }


    @GetMapping("/artist")
    @ResponseBody
    public Artist getByName(@RequestParam String name) {
        return artistService.findByName(name);
    }

    @GetMapping("/artist/search")
    @ResponseBody
    public List<Artist> getArtistByPattern(@RequestParam String pattern) {
        System.out.println("Find1: "+pattern);
        return artistService.findByPattern(pattern);
    }


    @PostMapping("/artist/save")
    public ResponseEntity<?> save(@RequestBody Artist artist) throws AuthenticationException {
        Artist result = artistService.save(artist);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/artist/delete/{id}")
    public void delete(@PathVariable Long id) {
        artistService.delete(id);
        return;
    }
}
