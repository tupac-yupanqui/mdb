package de.wko.mdb.data.service;

import de.wko.mdb.data.entity.ArtistEntity;
import de.wko.mdb.data.repository.ArtistRepository;
import de.wko.mdb.types.Artist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArtistService {

    @Autowired
    ArtistRepository repo;

    public Artist getById(Long id) {
        Artist a = repo.findById(id).get().getType();
        return a;
    }
    public List<Artist> getAll() {
        Iterable<ArtistEntity> all = repo.findAll(Sort.by(Sort.Direction.ASC, "name"));
        List<Artist> result = new ArrayList<>();
        for (ArtistEntity a : all) {
            result.add(a.getType());
        }
        return result;
    }
}
