package de.wko.mdb.bl.service;

import de.wko.mdb.data.entity.FolderEntity;
import de.wko.mdb.data.entity.TrackEntity;
import de.wko.mdb.data.repository.FolderRepository;
import de.wko.mdb.data.repository.TrackRepository;
import de.wko.mdb.types.Folder;
import de.wko.mdb.types.Track;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TrackService {

    @Autowired
    TrackRepository trackRepository;

    public List<Track> getTrackByParentId(Long parentId) {
        List<TrackEntity> list = trackRepository.getTrackByFolderId(parentId);
        System.out.println("get content "+parentId);
        List<Track> result = new ArrayList<>();
        for (TrackEntity te : list) {
            result.add(te.getType());
        }
        return result;
    }

}
