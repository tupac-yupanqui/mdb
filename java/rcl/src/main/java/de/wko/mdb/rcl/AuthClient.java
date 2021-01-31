package de.wko.mdb.rcl;

import de.wko.mdb.types.AuthData;
import de.wko.mdb.types.request.LoginRequest;
import org.springframework.stereotype.Component;

@Component
public class AuthClient extends RestClient {

    public AuthData signin(LoginRequest request) throws MdbRestException {
        return post(RestConfig.PATH_LOGIN, request, AuthData.class);
    }
}
