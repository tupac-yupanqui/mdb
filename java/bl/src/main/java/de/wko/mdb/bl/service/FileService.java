package de.wko.mdb.bl.service;

import de.wko.mdb.data.entity.FileEntity;
import de.wko.mdb.data.entity.FolderEntity;
import de.wko.mdb.data.repository.FileRepository;
import de.wko.mdb.types.FileObject;
import de.wko.mdb.types.Folder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FileService {
    @Autowired
    FileRepository fileRepository;

    public FileObject getFileByFolderIdAndName(Long folderId, String fileName) {
        Optional<FileEntity> entity = fileRepository.getFileByFolderIdAndFilename(folderId, fileName);
        if (entity.isPresent()) return entity.get().getType();
        return null;
    }

    public List<FileObject> getFilesByFolderId(Long folderId) {
        List<FileEntity> list = fileRepository.getFilesByFolderId(folderId);
        List<FileObject> result = new ArrayList<>();
        for (FileEntity fe : list) {
            result.add(fe.getType());
        }
        return result;
    }

    public FileObject save(FileObject file) {
        Optional<FileEntity> fo = fileRepository.findById(file.getId());
        if (fo.isPresent()) {
            FileEntity fe = fo.get();
            fe.fromType(file);
            return fileRepository.save(fe).getType();
        } else if (file.getId()==0){
            FileEntity fe = new FileEntity();
            fe.fromType(file);
            return fileRepository.save(fe).getType();
        }

        return null;
    }

}
