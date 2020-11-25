package de.wko.mdb.data.service;

import de.wko.mdb.data.entity.AlbumEntity;
import de.wko.mdb.data.repository.AlbumCustomRepository;
import de.wko.mdb.data.repository.AlbumRepository;
import de.wko.mdb.types.Album;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AlbumService {

    @Autowired
    AlbumRepository repo;

    @Autowired
    AlbumCustomRepository customRepo;

    public Album getById(Long id) {
        Optional<AlbumEntity> optional = repo.findById(id);
        if (!optional.isPresent()) {
            return null;
        }
        return optional.get().getType();
    }

    public List<Album> getByArtistId(Long artistId) {
        List<AlbumEntity> list = customRepo.findByArtistId(artistId);
        List<Album> result = new ArrayList<>();
        for (AlbumEntity entity : list) {
            result.add(entity.getType());
        }
        return result;
    }
}
