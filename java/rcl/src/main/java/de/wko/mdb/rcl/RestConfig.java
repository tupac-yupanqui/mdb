package de.wko.mdb.rcl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class RestConfig {

    public static final String PATH_LOGIN = "PATH_LOGIN";
    public static final String PATH_HOST = "PATH_HOST";
    public static final String PATH_HOST_ID = "PATH_HOST_ID";
    public static final String PATH_HOSTS = "PATH_HOSTS";
    public static final String PATH_SAVE_HOST = "PATH_SAVE_HOST";
    public static final String PATH_DELETE_HOST = "PATH_DELETE_HOST";
    public static final String PATH_GET_ARCHIVE = "PATH_GET_ARCHIVE";
    public static final String PATH_GET_ARCHIVES_BY_HOST = "PATH_GET_ARCHIVES_BY_HOST";
    public static final String PATH_GET_ALL_ARCHIVES = "PATH_GET_ALL_ARCHIVES";
    public static final String PATH_SAVE_ARCHIVE = "PATH_SAVE_ARCHIVE";
    public static final String PATH_LIST_CONTENT = "PATH_LIST_CONTENT";

    public static final Map<String, String> urlMap;

    static {
        urlMap  = new HashMap<>();
        urlMap.put(PATH_LOGIN, "/api/auth/signin");
        urlMap.put(PATH_HOST, "/host?name={name}");
        urlMap.put(PATH_HOST_ID, "/host/{id}");
        urlMap.put(PATH_HOSTS, "/hosts");
        urlMap.put(PATH_SAVE_HOST, "/host/save");
        urlMap.put(PATH_DELETE_HOST, "/host/delete/{id}");
        urlMap.put(PATH_GET_ARCHIVES_BY_HOST, "/archives?hostId={id}");
        urlMap.put(PATH_GET_ARCHIVE, "/archive/{id}");
        urlMap.put(PATH_GET_ALL_ARCHIVES, "/archives/all");
        urlMap.put(PATH_SAVE_ARCHIVE, "/archive/save");
        urlMap.put(PATH_LIST_CONTENT, "/content/list?aid={aid}&path={path}");
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
