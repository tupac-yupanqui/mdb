package de.wko.mdb.rcl;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;


public class RestTokenInterceptor implements ClientHttpRequestInterceptor {

    private String token;

    public RestTokenInterceptor(String t) {
        super();
        this.token = t;
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest httpRequest, byte[] bytes, ClientHttpRequestExecution clientHttpRequestExecution) throws IOException {
        httpRequest.getHeaders().set("Authorization","Bearer "+token);
        return clientHttpRequestExecution.execute(httpRequest, bytes);
    }
}
