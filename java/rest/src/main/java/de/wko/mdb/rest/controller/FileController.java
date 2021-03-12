package de.wko.mdb.rest.controller;

import de.wko.mdb.bl.service.FileService;
import de.wko.mdb.types.FileObject;
import de.wko.mdb.types.Folder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
public class FileController {

    @Autowired
    private FileService fileService;

    @GetMapping("/file/getbyfolder")
    @ResponseBody
    public FileObject getByPath(@RequestParam Long fid, @RequestParam String name) {
        FileObject file = fileService.getFileByFolderIdAndName(fid, name);
        return file;
    }

    @PostMapping("/file/save")
    public ResponseEntity<?> save(@RequestBody FileObject file) throws AuthenticationException {
        FileObject result = fileService.save(file);
        return ResponseEntity.ok(result);
    }

}
