package de.wko.mdb.bl.service;

import de.wko.mdb.data.entity.AlbumEntity;
import de.wko.mdb.data.entity.ArchiveEntity;
import de.wko.mdb.data.repository.AlbumRepository;
import de.wko.mdb.data.repository.ArchiveRepository;
import de.wko.mdb.types.Album;
import de.wko.mdb.types.Archive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArchiveService {
    @Autowired
    ArchiveRepository archiveRepository;

    public List<Archive> getByHostId(Long hostId) {
        List<ArchiveEntity> list = archiveRepository.findByHost(hostId);
//        List<AlbumEntity> list = new ArrayList<>();
        List<Archive> result = new ArrayList<>();
        for (ArchiveEntity entity : list) {
            result.add(entity.getType());
        }
        return result;
    }

}
