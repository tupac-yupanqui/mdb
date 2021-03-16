package de.wko.mdb.bl.service;

import de.wko.mdb.data.entity.ArtistEntity;
import de.wko.mdb.data.entity.FolderEntity;
import de.wko.mdb.data.entity.TitelEntity;
import de.wko.mdb.data.repository.AlbumRepository;
import de.wko.mdb.data.repository.ArtistRepository;
import de.wko.mdb.data.repository.TitelRepository;
import de.wko.mdb.types.*;
import de.wko.mdb.types.query.SearchAlbumBlurQuery;
import de.wko.mdb.types.query.SearchArtistBlurQuery;
import de.wko.mdb.types.query.SearchTitelBlurQuery;
import de.wko.mdb.types.query.SearchTitelQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ContentService {

    @Autowired
    FolderService folderService;

    @Autowired
    FileService fileService;

    @Autowired
    TitelRepository titelRepository;

    @Autowired
    ArtistRepository artistRepository;

    @Autowired
    AlbumRepository albumRepository;

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

    public List<Titel> searchTitel(SearchTitelQuery searchQuery) {
        List<Titel> result = new ArrayList<>();
        ArtistEntity artist = artistRepository.findByName(searchQuery.getArtist());
        if (artist==null) return result;
        List<TitelEntity> titelList = titelRepository.findTitelByNameAndArtist(searchQuery.getTitel(), artist.getId());
        for (TitelEntity te: titelList) {
            result.add(te.getType());
        }
        return result;
    }

    public List<ScoredTitel> searchTitelBlur(SearchTitelBlurQuery searchQuery) {
        return titelRepository.searchTitelBlur(searchQuery);
    }

    public List<ScoredArtist> searchArtistBlur(SearchArtistBlurQuery searchQuery) {
        return artistRepository.searchArtistBlur(searchQuery);
    }

    public List<ScoredAlbum> searchAlbumBlur(SearchAlbumBlurQuery searchQuery) {
        return albumRepository.searchAlbumBlur(searchQuery);
    }
}
