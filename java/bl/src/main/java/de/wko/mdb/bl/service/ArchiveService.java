package de.wko.mdb.bl.service;

import de.wko.mdb.data.entity.AlbumEntity;
import de.wko.mdb.data.entity.ArchiveEntity;
import de.wko.mdb.data.entity.HostEntity;
import de.wko.mdb.data.repository.ArchiveRepository;
import de.wko.mdb.types.Album;
import de.wko.mdb.types.Archive;
import de.wko.mdb.types.Host;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ArchiveService {
    @Autowired
    ArchiveRepository archiveRepository;

    public Archive getArchiveById(Long id) {
        Optional<ArchiveEntity> ho = archiveRepository.findById(id);
        return ho.isPresent() ? ho.get().getType() : null;
    }

    public List<Archive> getByHostId(Long hostId) {
        List<ArchiveEntity> list = archiveRepository.findByHost(hostId);
//        List<AlbumEntity> list = new ArrayList<>();
        List<Archive> result = new ArrayList<>();
        for (ArchiveEntity entity : list) {
            result.add(entity.getType());
        }
        return result;
    }

    public List<Archive> getAllArchives() {
        Iterable<ArchiveEntity> list = archiveRepository.findAll();

        List<Archive> result = new ArrayList<>();
        for (ArchiveEntity entity : list) {
            result.add(entity.getType());
        }
        return result;
    }

    public Archive save(Archive archive) {
        Optional<ArchiveEntity> ao = archiveRepository.findById(archive.getId());
        if (ao.isPresent()) {
            ArchiveEntity ae = ao.get();
            ae.fromType(archive);
            return archiveRepository.save(ae).getType();
        } else if (archive.getId()==0){
            ArchiveEntity ae = new ArchiveEntity();
            ae.fromType(archive);
            System.out.println("Archiv ID: "+archive.getId());
            return archiveRepository.save(ae).getType();
        }
        return null;
    }

}
