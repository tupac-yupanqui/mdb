package de.wko.mdb.rest.controller;

import de.wko.mdb.bl.service.AlbumService;
import de.wko.mdb.bl.service.ArtistService;
import de.wko.mdb.types.Album;
import de.wko.mdb.types.Artist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AlbumController {
    @Autowired
    AlbumService albumService;

    @GetMapping("/album/{id}")
    @ResponseBody
    public Album getById(@PathVariable Long id) {
        return albumService.getById(id);
    }


    @GetMapping("/album")
    @ResponseBody
    public Album getByName(@RequestParam String name) {
        return albumService.findByName(name);
    }

    @GetMapping("/album/search")
    @ResponseBody
    public List<Album> getAlbumByPattern(@RequestParam String pattern) {
        System.out.println("Find1: "+pattern);
        return albumService.findByPattern(pattern);
    }


    @PostMapping("/album/save")
    public ResponseEntity<?> save(@RequestBody Album album) throws AuthenticationException {
        Album result = albumService.save(album);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/album/delete/{id}")
    public void delete(@PathVariable Long id) {
        albumService.delete(id);
        return;
    }

}
