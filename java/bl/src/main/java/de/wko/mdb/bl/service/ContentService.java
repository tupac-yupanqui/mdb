package de.wko.mdb.bl.service;

import de.wko.mdb.data.entity.FolderEntity;
import de.wko.mdb.types.FolderContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ContentService {

    @Autowired
    FolderService folderService;

    @Autowired
    FileService fileService;

    public FolderContent getContentByFolderId(Long folderId) {
        FolderContent content = new FolderContent();
        content.setFolderList(folderService.getFoldersByParentId(folderId));
        content.setTrackList(fileService.getFilesByFolderId(folderId));
        return content;
    }

    public FolderContent getContentByFolderPath(Long archiveId, String path) {
        Optional<FolderEntity> folderEntity = folderService.getFolderEntityByPath(archiveId, path);
        if (folderEntity.isPresent()) {
            return getContentByFolderId(folderEntity.get().getId());
        }
        return null;
    }
}
