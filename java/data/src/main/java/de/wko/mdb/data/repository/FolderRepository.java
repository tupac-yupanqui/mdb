package de.wko.mdb.data.repository;

import de.wko.mdb.data.entity.FolderEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface FolderRepository extends CrudRepository<FolderEntity,Long>, FolderRepositoryCustom {

    FolderEntity findById(long id);

    @Query(name = "FolderEntity.findRootFolder")
    Optional<FolderEntity> getRootFolder(long archiveId);

    @Query(name = "FolderEntity.getFolderByParent")
    List<FolderEntity> getFolderByParent(Long parentId);

    @Query(name = "FolderEntity.getFolderByArchiveAndPath")
    Optional<FolderEntity> getFolderByArchiveAndPath(Long archiveId, String path);

    @Query(name = "FolderEntity.getFolderByParentAndPath")
    Optional<FolderEntity> getFolderByParentAndPath(Long parentId, String path);


}
