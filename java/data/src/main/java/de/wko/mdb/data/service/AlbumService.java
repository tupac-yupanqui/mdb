package de.wko.mdb.data.service;

import de.wko.mdb.data.entity.AlbumEntity;
import de.wko.mdb.data.entity.SubalbumEntity;
import de.wko.mdb.data.entity.TitelEntity;
import de.wko.mdb.data.filter.AlbumFilter;
import de.wko.mdb.data.repository.AlbumRepository;
import de.wko.mdb.data.repository.TitelRepository;
import de.wko.mdb.types.Album;
import de.wko.mdb.types.AlbumDetails;
import de.wko.mdb.types.Titel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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

        if (albumEntity.getSubalbums().size()==0) {
            List<TitelEntity> titels = titelRepository.getByAlbumId(albumEntity.getId());
            Map<String, List<Titel>> map = new HashMap<>();
            map.put("", getTitelList(titels));
            details.setTitels(map);
        } else {
            Map<String, List<Titel>> map = new TreeMap<>();
            for (SubalbumEntity s : albumEntity.getSubalbums()) {
                List<TitelEntity> titels = titelRepository.getBySubalbumId(s.getId());
                map.put(s.getName(), getTitelList(titels));
                details.setTitels(map);
            }
        }

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
