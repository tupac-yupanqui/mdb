package de.wko.mdb.rest.controller;

import de.wko.mdb.bl.service.ArchiveService;
import de.wko.mdb.types.Archive;
import de.wko.mdb.types.Host;
import org.springframework.beans.factory.annotation.Autowired;
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


}
