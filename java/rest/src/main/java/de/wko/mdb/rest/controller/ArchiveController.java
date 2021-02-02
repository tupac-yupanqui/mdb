package de.wko.mdb.rest.controller;

import de.wko.mdb.bl.service.ArchiveService;
import de.wko.mdb.types.Archive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ArchiveController {
    @Autowired
    ArchiveService archiveService;

    @GetMapping("/archives")
    @ResponseBody
    public List<Archive> getByHost(@RequestParam Long hostId) {
        return archiveService.getByHostId(hostId);
    }

}
