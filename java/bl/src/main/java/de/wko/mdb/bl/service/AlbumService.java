package de.wko.mdb.bl.service;

import de.wko.mdb.data.entity.AlbumEntity;
import de.wko.mdb.data.entity.ArtistEntity;
import de.wko.mdb.data.entity.SubalbumEntity;
import de.wko.mdb.data.entity.TitelEntity;
import de.wko.mdb.data.filter.AlbumFilter;
import de.wko.mdb.data.repository.AlbumRepository;
import de.wko.mdb.data.repository.TitelRepository;
import de.wko.mdb.types.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AlbumService {

    @Autowired
    ArtistService artistService;

    @Autowired
    AlbumRepository albumRepository;

    @Autowired
    TitelRepository titelRepository;

    public Album getById(Long id) {
        Optional<AlbumEntity> optional = albumRepository.findById(id);
        if (!optional.isPresent()) {
            return null;
        }
        return optional.get().getType();
    }

    public Album findByName(String name) {
        AlbumEntity he = albumRepository.findByName(name);
        return he==null ? null : he.getType();
    }

    public List<Album> findByPattern(String pattern) {
        System.out.println("Find2: "+pattern);
        Iterable<AlbumEntity> all = albumRepository.findAlbumByPattern(pattern);
        List<Album> result = new ArrayList<>();
        for (AlbumEntity a : all) {
            result.add(a.getType());
        }
        System.out.println("Find2: "+result.size());
        return result;
    }

    public Album save(Album album) {
        Optional<AlbumEntity> ao = albumRepository.findById(album.getId());
        if (ao.isPresent()) {
            AlbumEntity ae = ao.get();
            ae.fromType(album);
            return albumRepository.save(ae).getType();
        } else if (album.getId()==0){
            AlbumEntity ae = new AlbumEntity();
            ae.fromType(album);
            return albumRepository.save(ae).getType();
        }
        return null;
    }

    public void delete(Long id) {
        albumRepository.deleteById(id);
    }



    public List<Album> getByArtistId(Long artistId) {
        List<AlbumEntity> list = albumRepository.findByArtistId(artistId);
//        List<AlbumEntity> list = new ArrayList<>();
        List<Album> result = new ArrayList<>();
        for (AlbumEntity entity : list) {
            result.add(entity.getType());
        }
        return result;
    }

    public List<Album> getAllAlbums() {
        List<AlbumEntity> list = albumRepository.findAll();
//        List<AlbumEntity> list = new ArrayList<>();
        List<Album> result = new ArrayList<>();
        for (AlbumEntity entity : list) {
            result.add(entity.getType());
        }
        return result;
    }

    public List<Album> getFilteredAlbums(AlbumFilter filter) {
        List<AlbumEntity> list = albumRepository.findFilteredAlbums(filter);

        List<Album> result = new ArrayList<>();
        for (AlbumEntity entity : list) {
            result.add(entity.getType());
        }
        return result;
    }

    public List<Album> getByArtist(Long artistId) {
        List<AlbumEntity> list = albumRepository.findByArtist(artistId);
        List<Album> result = new ArrayList<>();
        for (AlbumEntity entity : list) {
            result.add(entity.getType());
        }
        return result;
    }
    public int getCount() {
        return albumRepository.getCount();
    }
    public Long countFilteredAlbums(AlbumFilter filter) {
        return albumRepository.countFilteredAlbums(filter);
    }

    public AlbumDetails getAlbumDetails(int id) {
        AlbumDetails details = new AlbumDetails();
        AlbumEntity albumEntity = albumRepository.getAlbum(id);
        details.setAlbum(albumEntity.getType());

        List<TitelList> list = new ArrayList<>();
        if (albumEntity.getSubalbums().size()==0) {
            TitelList titellist = new TitelList();
            List<TitelEntity> titels = titelRepository.getByAlbumId(albumEntity.getId());
            titellist.setList(getTitelList(titels));
            list.add(titellist);
        } else {
            for (SubalbumEntity s : albumEntity.getSubalbums()) {
                TitelList titellist = new TitelList();
                titellist.setSubalbum(s.getType());
                titellist.setList(getTitelList(titelRepository.getBySubalbumId(s.getId())));
                list.add(titellist);
            }
        }
        details.setTitels(list);

        return details;
    }

    private List<Titel> getTitelList(List<TitelEntity> titels) {
        List<Titel> titelList = new ArrayList<>();

        for (TitelEntity te : titels) {
            Titel t = te.getType();
            //t.setArtist(artistService.getById(te.getArtistId()));
            titelList.add(t);
        }

        return titelList;
    }
}
