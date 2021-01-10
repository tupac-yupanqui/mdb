package de.wko.mdb.data.service;

import de.wko.mdb.data.entity.AlbumEntity;
import de.wko.mdb.data.filter.AlbumFilter;
import de.wko.mdb.data.repository.AlbumRepository;
import de.wko.mdb.types.Album;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AlbumService {

    @Autowired
    AlbumRepository repo;

    public Album getById(Long id) {
        Optional<AlbumEntity> optional = repo.findById(id);
        if (!optional.isPresent()) {
            return null;
        }
        return optional.get().getType();
    }

    public List<Album> getByArtistId(Long artistId) {
        List<AlbumEntity> list = repo.findByArtistId(artistId);
//        List<AlbumEntity> list = new ArrayList<>();
        List<Album> result = new ArrayList<>();
        for (AlbumEntity entity : list) {
            result.add(entity.getType());
        }
        return result;
    }

    public List<Album> getAllAlbums() {
        List<AlbumEntity> list = repo.findAll();
//        List<AlbumEntity> list = new ArrayList<>();
        List<Album> result = new ArrayList<>();
        for (AlbumEntity entity : list) {
            result.add(entity.getType());
        }
        return result;
    }

    public List<Album> getFilteredAlbums(AlbumFilter filter) {
        List<AlbumEntity> list = repo.findFilteredAlbums(filter);

        List<Album> result = new ArrayList<>();
        for (AlbumEntity entity : list) {
            result.add(entity.getType());
        }
        return result;
    }

    public List<Album> getByArtist(Long artistId) {
        List<AlbumEntity> list = repo.findByArtist(artistId);
//        List<AlbumEntity> list = new ArrayList<>();
        List<Album> result = new ArrayList<>();
        for (AlbumEntity entity : list) {
            result.add(entity.getType());
        }
        return result;
    }
    public int getCount() {
        return repo.getCount();
    }
    public Long countFilteredAlbums(AlbumFilter filter) {
        return repo.countFilteredAlbums(filter);
    }
}
