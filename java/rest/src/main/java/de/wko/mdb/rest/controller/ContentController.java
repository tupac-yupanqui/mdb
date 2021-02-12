package de.wko.mdb.rest.controller;

import de.wko.mdb.bl.service.ContentService;
import de.wko.mdb.types.FolderContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContentController {
    @Autowired
    private ContentService contentService;

    @GetMapping("/content/list")
    @ResponseBody
    public FolderContent getById(@RequestParam Long aid, @RequestParam String path) {
        FolderContent result = contentService.getContentByFolderPath(aid, path);
        return result;
    }
}
