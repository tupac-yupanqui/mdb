package de.wko.mdb.bl.service;

import de.wko.mdb.data.entity.FolderEntity;
import de.wko.mdb.data.repository.FolderRepository;
import de.wko.mdb.types.Folder;
import de.wko.mdb.types.enums.EFolderType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FolderService {

    @Autowired
    FolderRepository folderRepository;

    public Folder getById(Long id) {
        Folder f = folderRepository.findById(id).get().getType();
        return f;
    }

    public Folder getRootFolder(Long archiveId) {
        Optional<FolderEntity> root = folderRepository.getRootFolder(archiveId);
        if (root.isPresent()) return root.get().getType();
        return null;
    }

    public List<Folder> getFoldersByParentId(Long parentId) {
        List<FolderEntity> list = folderRepository.getFoldersByParent(parentId);
        List<Folder> result = new ArrayList<>();
        for (FolderEntity fe : list) {
            result.add(fe.getType());
        }
        return result;
    }

    public Folder createRootFolder(Long archiveId) {
        Folder root = new Folder();
        root.setName("/");
        root.setParentId(0L);
        root.setArchiveId(archiveId);
        root.setObjectId(archiveId);
        root.setType(EFolderType.ROOT);
        return save(root);
    }

    public Folder save(Folder folder) {
        Optional<FolderEntity> fo = folderRepository.findById(folder.getId());
        if (fo.isPresent()) {
            FolderEntity fe = fo.get();
            fe.fromType(folder);
            return folderRepository.save(fe).getType();
        } else if (folder.getId()==0){
            FolderEntity fe = new FolderEntity();
            fe.fromType(folder);
            return folderRepository.save(fe).getType();
        }

        return null;
    }

    public Folder getFolderByPath(Long archiveId, String path) {
        Optional<FolderEntity> entity = getFolderEntityByPath(archiveId, path);
        if (entity.isPresent()) return entity.get().getType();
        return null;
    }

    public Optional<FolderEntity> getFolderEntityByPath(Long archiveId, String path) {
        String pathElements[] = path.split("/");

        Optional<FolderEntity> folder = folderRepository.getFolderByArchiveAndPath(archiveId, "");

        if (!folder.isPresent() || pathElements.length<2) return folder;

        for (int i=1; i<pathElements.length; i++) {
            folder = folderRepository.getFolderByParentAndPath(folder.get().getId(), pathElements[i]);
            if (!folder.isPresent()) return folder;
        }
        return folder;
    }
}
