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

    public static final Map<String, String> urlMap;

    static {
        urlMap  = new HashMap<>();
        urlMap.put(PATH_LOGIN, "/api/auth/signin");
        urlMap.put(PATH_HOST, "/host?name={name}");
        urlMap.put(PATH_HOST_ID, "/host/{id}");
        urlMap.put(PATH_HOSTS, "/hosts");
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
