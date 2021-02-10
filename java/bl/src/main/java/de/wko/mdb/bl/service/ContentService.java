package de.wko.mdb.bl.service;

import de.wko.mdb.data.entity.FolderEntity;
import de.wko.mdb.data.repository.FolderRepository;
import de.wko.mdb.data.repository.TrackRepository;
import de.wko.mdb.types.Folder;
import de.wko.mdb.types.FolderContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ContentService {

    @Autowired
    FolderService folderService;

    @Autowired
    TrackService trackService;

    public FolderContent getContentByFolderId(Long folderId) {
        FolderContent content = new FolderContent();
        content.setFolderList(folderService.getFolderByParentId(folderId));
        content.setTrackList(trackService.getTrackByParentId(folderId));
        return content;
    }

    public FolderContent getContentByFolderPath(Long archiveId, String path) {
        Optional<FolderEntity> folderEntity = folderService.getFolderByPath(archiveId, path);
        if (folderEntity.isPresent()) {
            return getContentByFolderId(folderEntity.get().getId());
        }
        System.out.println("Folder not found "+path+" - "+archiveId);
        return null;
    }
}
