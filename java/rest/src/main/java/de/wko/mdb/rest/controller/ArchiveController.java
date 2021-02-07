package de.wko.mdb.rest.controller;

import de.wko.mdb.bl.service.ArchiveService;
import de.wko.mdb.types.Archive;
import de.wko.mdb.types.Host;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ArchiveController {
    @Autowired
    ArchiveService archiveService;

    @GetMapping("/archive/{id}")
    @ResponseBody
    public Archive getById(@PathVariable Long id) {
        return archiveService.getArchiveById(id);
    }

    @GetMapping("/archives")
    @ResponseBody
    public List<Archive> getByHost(@RequestParam Long hostId) {
        return archiveService.getByHostId(hostId);
    }

    @GetMapping("/archives/all")
    @ResponseBody
    public List<Archive> getAllArchives() {
        return archiveService.getAllArchives();
    }


    @PostMapping("/archive/save")
    public ResponseEntity<?> save(@RequestBody Archive archive) throws AuthenticationException {
        Archive result = archiveService.save(archive);
        return ResponseEntity.ok(result);
    }

}
