package de.wko.mdb.bl.service;

import de.wko.mdb.data.entity.ArtistEntity;
import de.wko.mdb.data.entity.HostEntity;
import de.wko.mdb.data.repository.ArtistRepository;
import de.wko.mdb.types.Artist;
import de.wko.mdb.types.Host;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public List<Artist> findByPattern(String pattern) {
        System.out.println("Find2: "+pattern);
        Iterable<ArtistEntity> all = repo.findArtistByPattern(pattern);
        List<Artist> result = new ArrayList<>();
        for (ArtistEntity a : all) {
            result.add(a.getType());
        }
        System.out.println("Find2: "+result.size());
        return result;
    }

    public Artist findByName(String name) {
        ArtistEntity he = repo.findByName(name);
        return he==null ? null : he.getType();
    }

    public Artist save(Artist artist) {
        Optional<ArtistEntity> ao = repo.findById(artist.getId());
        if (ao.isPresent()) {
            ArtistEntity ae = ao.get();
            ae.fromType(artist);
            return repo.save(ae).getType();
        } else if (artist.getId()==0){
            ArtistEntity ae = new ArtistEntity();
            ae.fromType(artist);
            return repo.save(ae).getType();
        }
        return null;
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

}
