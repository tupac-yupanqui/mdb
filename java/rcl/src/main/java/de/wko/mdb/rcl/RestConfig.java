package de.wko.mdb.rcl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class RestConfig {

    public static final String PATH_LOGIN = "PATH_LOGIN";
    public static final String PATH_GET_HOST_BY_NAME = "PATH_GET_HOST_BY_NAME";
    public static final String PATH_GET_HOST_BY_ID = "PATH_GET_HOST_BY_ID";
    public static final String PATH_HOSTS = "PATH_HOSTS";
    public static final String PATH_SAVE_HOST = "PATH_SAVE_HOST";
    public static final String PATH_DELETE_HOST = "PATH_DELETE_HOST";
    public static final String PATH_GET_ARCHIVE = "PATH_GET_ARCHIVE";
    public static final String PATH_GET_ARCHIVES_BY_HOST = "PATH_GET_ARCHIVES_BY_HOST";
    public static final String PATH_GET_ALL_ARCHIVES = "PATH_GET_ALL_ARCHIVES";
    public static final String PATH_SAVE_ARCHIVE = "PATH_SAVE_ARCHIVE";
    public static final String PATH_LIST_CONTENT = "PATH_LIST_CONTENT";
    public static final String PATH_GET_FOLDER_BY_ID = "PATH_GET_FOLDER_BY_ID";
    public static final String PATH_GET_FOLDER_BY_PATH = "PATH_GET_FOLDER_BY_PATH";
    public static final String PATH_GET_FILE_BY_FOLDER_AND_NAME = "PATH_GET_FILE_BY_FOLDER_AND_NAME";
    public static final String PATH_SAVE_FOLDER = "PATH_SAVE_FOLDER";
    public static final String PATH_GET_ARTIST_BY_NAME = "PATH_GET_ARTIST_BY_NAME";
    public static final String PATH_GET_ARTIST_BY_ID = "PATH_GET_ARTIST_BY_ID";
    public static final String PATH_GET_ARTIST_BY_PATTERN = "PATH_GET_ARTIST_BY_PATTERN";
    public static final String PATH_ARTISTS = "PATH_ARTISTS";
    public static final String PATH_SAVE_ARTIST = "PATH_SAVE_ARTIST";
    public static final String PATH_DELETE_ARTIST = "PATH_DELETE_ARTIST";
    public static final String PATH_GET_ALBUM_BY_NAME = "PATH_GET_ALBUM_BY_NAME";
    public static final String PATH_GET_ALBUM_BY_ID = "PATH_GET_ALBUM_BY_ID";
    public static final String PATH_GET_SUBALBUM_BY_ID = "PATH_GET_SUBALBUM_BY_ID";
    public static final String PATH_GET_ALBUM_BY_PATTERN = "PATH_GET_ALBUM_BY_PATTERN";
    public static final String PATH_ALBUMS = "PATH_ALBUMS";
    public static final String PATH_SAVE_ALBUM = "PATH_SAVE_ALBUM";
    public static final String PATH_DELETE_ALBUM = "PATH_DELETE_ALBUM";
    public static final String PATH_SEARCH_TITEL = "PATH_SEARCH_TITEL";
    public static final String PATH_SEARCH_TITLE_BLUR = "PATH_SEARCH_TITLE_BLUR";
    public static final String PATH_SEARCH_ARTIST_BLUR = "PATH_SEARCH_ARTIST_BLUR";
    public static final String PATH_SEARCH_ALBUM_BLUR = "PATH_SEARCH_ALBUM_BLUR";
    public static final String PATH_SAVE_FILE = "PATH_SAVE_FILE";
    public static final String PATH_GET_TITEL_BY_ID = "PATH_GET_TITEL_BY_ID";
    public static final String PATH_SAVE_TITEL = "PATH_SAVE_TITEL";
    public static final String PATH_SAVE_SUBALBUM = "PATH_SAVESUBALBUM";

    public static final Map<String, String> urlMap;

    static {
        urlMap  = new HashMap<>();
        urlMap.put(PATH_LOGIN, "/api/auth/signin");
        urlMap.put(PATH_GET_HOST_BY_NAME, "/host?name={name}");
        urlMap.put(PATH_GET_HOST_BY_ID, "/host/{id}");
        urlMap.put(PATH_HOSTS, "/hosts");
        urlMap.put(PATH_SAVE_HOST, "/host/save");
        urlMap.put(PATH_DELETE_HOST, "/host/delete/{id}");
        urlMap.put(PATH_GET_ARCHIVES_BY_HOST, "/archives?hostId={id}");
        urlMap.put(PATH_GET_ARCHIVE, "/archive/{id}");
        urlMap.put(PATH_GET_ALL_ARCHIVES, "/archives/all");
        urlMap.put(PATH_SAVE_ARCHIVE, "/archive/save");
        urlMap.put(PATH_LIST_CONTENT, "/content/list?aid={aid}&path={path}");
        urlMap.put(PATH_GET_FOLDER_BY_ID, "/folder/{id}");
        urlMap.put(PATH_GET_FOLDER_BY_PATH, "/folder/getbypath?aid={aid}&path={path}");
        urlMap.put(PATH_GET_FILE_BY_FOLDER_AND_NAME, "/file/getbyfolder?fid={fid}&name={name}");
        urlMap.put(PATH_SAVE_FOLDER, "/folder/save");
        urlMap.put(PATH_GET_ARTIST_BY_NAME, "/artist?name={name}");
        urlMap.put(PATH_GET_ARTIST_BY_PATTERN, "/artist/search?pattern={pattern}");
        urlMap.put(PATH_GET_ARTIST_BY_ID, "/artist/{id}");
        urlMap.put(PATH_ARTISTS, "/artists");
        urlMap.put(PATH_SAVE_ARTIST, "/artist/save");
        urlMap.put(PATH_DELETE_ARTIST, "/artist/delete/{id}");
        urlMap.put(PATH_GET_ALBUM_BY_NAME, "/album?name={name}");
        urlMap.put(PATH_GET_ALBUM_BY_PATTERN, "/album/search?pattern={pattern}");
        urlMap.put(PATH_GET_ALBUM_BY_ID, "/album/{id}");
        urlMap.put(PATH_GET_SUBALBUM_BY_ID, "/subalbum/{id}");
        urlMap.put(PATH_ALBUMS, "/albums");
        urlMap.put(PATH_SAVE_ALBUM, "/album/save");
        urlMap.put(PATH_DELETE_ALBUM, "/album/delete/{id}");
        urlMap.put(PATH_SEARCH_TITEL, "/search/titel");
        urlMap.put(PATH_SEARCH_TITLE_BLUR, "/search/title/blur");
        urlMap.put(PATH_SEARCH_ARTIST_BLUR, "/search/artist/blur");
        urlMap.put(PATH_SEARCH_ALBUM_BLUR, "/search/album/blur");
        urlMap.put(PATH_SAVE_FILE, "/file/save");
        urlMap.put(PATH_SAVE_TITEL, "/titel/save");
        urlMap.put(PATH_GET_TITEL_BY_ID, "/titel/{id}");
        urlMap.put(PATH_SAVE_SUBALBUM, "/subalbum/save");
    }

    @Value("${base.url}")
    String baseUrl;

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getUrl(String f) {
        return baseUrl + urlMap.get(f);
    }
}
