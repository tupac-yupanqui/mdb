package de.wko.mdb.data.repository;

import de.wko.mdb.data.entity.FileEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface FileRepository extends CrudRepository<FileEntity,Long>, FileRepositoryCustom {
    FileEntity findById(long id);

    @Query(name = "FileEntity.getFileByFolderIdAndFilename")
    Optional<FileEntity> getFileByFolderIdAndFilename(Long folderId, String filename);

    @Query(name = "FileEntity.getFilesByFolderId")
    List<FileEntity> getFilesByFolderId(Long folderId);


}
