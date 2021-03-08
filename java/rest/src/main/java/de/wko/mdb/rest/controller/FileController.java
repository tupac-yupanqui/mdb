package de.wko.mdb.rest.controller;

import de.wko.mdb.bl.service.FileService;
import de.wko.mdb.types.FileObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
}
