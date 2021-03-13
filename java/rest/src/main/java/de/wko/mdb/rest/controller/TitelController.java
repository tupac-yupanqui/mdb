package de.wko.mdb.rest.controller;

import de.wko.mdb.bl.service.TitelService;
import de.wko.mdb.types.Artist;
import de.wko.mdb.types.Titel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
public class TitelController {

    @Autowired
    private TitelService titelService;

    @GetMapping("/titel/{id}")
    @ResponseBody
    public Titel getById(@PathVariable Long id) {
        return titelService.getById(id);
    }

    @PostMapping("/titel/save")
    public ResponseEntity<?> save(@RequestBody Titel titel) throws AuthenticationException {
        Titel result = titelService.save(titel);
        return ResponseEntity.ok(result);
    }

}
