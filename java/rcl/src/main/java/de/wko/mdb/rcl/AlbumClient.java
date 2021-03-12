package de.wko.mdb.rcl;

import de.wko.mdb.types.Album;
import de.wko.mdb.types.Subalbum;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AlbumClient extends RestClient {

    public Album getAlbumByName(String name) throws MdbRestException {
        Map params = new HashMap();
        params.put("name", name);
        return get(RestConfig.PATH_GET_ALBUM_BY_NAME, Album.class, params);
    }

    public Album getAlbumById(Long id) throws MdbRestException {
        Map params = new HashMap();
        params.put("id", id);
        return get(RestConfig.PATH_GET_ALBUM_BY_ID, Album.class, params);
    }

    public Subalbum getSubalbumById(Long id) throws MdbRestException {
        Map params = new HashMap();
        params.put("id", id);
        return get(RestConfig.PATH_GET_SUBALBUM_BY_ID, Subalbum.class, params);
    }

    public List<Album> getAllAlbums() throws MdbRestException {
        Album[] list =  getList(RestConfig.PATH_ALBUMS, Album[].class);
        return Arrays.asList(list.clone());
    }

    public List<Album> getAlbumByPattern(String pattern) throws MdbRestException {
        Map params = new HashMap();
        params.put("pattern", pattern);
        Album[] list =  getList(RestConfig.PATH_GET_ALBUM_BY_PATTERN, Album[].class, params);
        return Arrays.asList(list.clone());
    }

    public Album saveAlbum(Album album) throws MdbRestException {
        Album a = post(RestConfig.PATH_SAVE_ALBUM, album, Album.class);
        return a;
    }

    public void deleteAlbum(Long id) throws MdbRestException {
        Map params = new HashMap();
        params.put("id", id);
        delete(RestConfig.PATH_DELETE_ALBUM, params);
    }

}
