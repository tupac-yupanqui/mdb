package de.wko.mdb.rest.controller;

import de.wko.mdb.bl.service.FolderService;
import de.wko.mdb.types.Artist;
import de.wko.mdb.types.Folder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class FolderController {

    @Autowired
    private FolderService folderService;

    @GetMapping("/folder/getbypath")
    @ResponseBody
    public Folder getByPath(@RequestParam Long aid, @RequestParam String path) {
        Folder folder = folderService.getFolderByPath(aid, path);
        return folder;
    }

    @GetMapping("/folder/{id}")
    @ResponseBody
    public Folder getFolderById(@PathVariable Long id) {
        return folderService.getById(id);
    }

    @PostMapping("/folder/save")
    public ResponseEntity<?> save(@RequestBody Folder folder) throws AuthenticationException {
        Folder result = folderService.save(folder);
        return ResponseEntity.ok(result);
    }

}
