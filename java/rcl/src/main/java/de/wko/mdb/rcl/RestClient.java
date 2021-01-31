package de.wko.mdb.rcl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.wko.mdb.types.AuthData;
import de.wko.mdb.types.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

public class RestClient {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    RestConfig config;

    private RestTokenInterceptor interceptor;

    protected <T,U> T post(String path, U request, Class<T> response) throws MdbRestException{
        try {
            return restTemplate.postForObject(
                    config.getUrl(path),
                    request,
                    response);
        } catch (Exception e) {
            throw prepareMdbRestException(e);
        }
    }

    protected <T> T get(String path, Class<T> response, Map<String,?> params) throws MdbRestException{
        try {
            return restTemplate.getForObject(
                    config.getUrl(path),
                    response,
                    params);
        } catch (Exception e) {
            throw prepareMdbRestException(e);
        }
    }

    protected <T> T get(String path, Class<T> response) throws MdbRestException{
        try {
            return restTemplate.getForObject(
                    config.getUrl(path),
                    response);
        } catch (Exception e) {
            throw prepareMdbRestException(e);
        }
    }

    protected <T> T[] getList(String path, Class<T[]> resultClass) throws MdbRestException{
        try {
            ResponseEntity<T[]> response = restTemplate.getForEntity(
                    config.getUrl(path), resultClass
            );
            return response.getBody();
        } catch (Exception e) {
            throw prepareMdbRestException(e);
        }
    }

    public void addToken(String token) {
        interceptor = new RestTokenInterceptor(token);
        restTemplate.getInterceptors().add(interceptor);
    }

    public void removeToken() {
        restTemplate.getInterceptors().remove(interceptor);
    }

    private MdbRestException prepareMdbRestException(Exception e) {
        MdbRestException re = new MdbRestException();
        ErrorResponse errorResponse = null;
        if (e instanceof HttpClientErrorException) {
            e.printStackTrace();
            HttpClientErrorException ei = (HttpClientErrorException)e;
            String r = ei.getResponseBodyAsString();
            ObjectMapper om = new ObjectMapper();
            try {
                errorResponse = om.readValue(r, ErrorResponse.class);
            } catch (JsonProcessingException jsonProcessingException) {
                errorResponse.setMessage("JSON parse error");
                jsonProcessingException.printStackTrace();
            }
        } else {
            e.printStackTrace();

        }
        re.setResponse(errorResponse==null? new ErrorResponse() : errorResponse);
        return re;
    }

}
