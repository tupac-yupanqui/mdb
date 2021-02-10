package de.wko.mdb.rest.controller;

import de.wko.mdb.bl.service.FolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FolderController {

    @Autowired
    private FolderService folderService;


}
