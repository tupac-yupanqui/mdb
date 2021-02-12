package de.wko.mdb.rcl;

import de.wko.mdb.types.Artist;
import de.wko.mdb.types.Host;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ArtistClient extends RestClient {

    public Artist getArtistByName(String name) throws MdbRestException {
        Map params = new HashMap();
        params.put("name", name);
        return get(RestConfig.PATH_GET_ARTIST_BY_NAME, Artist.class, params);
    }

    public Artist getArtistById(Long id) throws MdbRestException {
        Map params = new HashMap();
        params.put("id", id);
        return get(RestConfig.PATH_GET_ARTIST_BY_ID, Artist.class, params);
    }

    public List<Artist> getAllArtists() throws MdbRestException {
        Artist[] list =  getList(RestConfig.PATH_ARTISTS, Artist[].class);
        return Arrays.asList(list.clone());
    }

    public List<Artist> getArtistByPattern(String pattern) throws MdbRestException {
        Map params = new HashMap();
        params.put("pattern", pattern);
        Artist[] list =  getList(RestConfig.PATH_GET_ARTIST_BY_PATTERN, Artist[].class, params);
        return Arrays.asList(list.clone());
    }

    public Artist saveArtist(Artist artist) throws MdbRestException {
        Artist a = post(RestConfig.PATH_SAVE_ARTIST, artist, Artist.class);
        return a;
    }

    public void deleteArtist(Long id) throws MdbRestException {
        Map params = new HashMap();
        params.put("id", id);
        delete(RestConfig.PATH_DELETE_ARTIST, params);
    }
}
