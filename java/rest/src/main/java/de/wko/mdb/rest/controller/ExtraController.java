package de.wko.mdb.rest.controller;

import de.wko.mdb.bl.service.AlbumService;
import de.wko.mdb.bl.service.ExtraService;
import de.wko.mdb.types.Album;
import de.wko.mdb.types.Extra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExtraController {
    @Autowired
    ExtraService extraService;

    @PostMapping("/extra/save")
    public ResponseEntity<?> save(@RequestBody Extra extra) throws AuthenticationException {
        Extra result = extraService.save(extra);
        return ResponseEntity.ok(result);
    }

}
