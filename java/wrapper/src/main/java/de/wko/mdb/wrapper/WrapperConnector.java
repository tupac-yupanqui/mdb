package de.wko.mdb.wrapper;

import de.wko.mdb.bl.service.HostService;
import de.wko.mdb.rcl.*;
import de.wko.mdb.types.AuthData;
import de.wko.mdb.types.DbConnector;
import de.wko.mdb.types.Host;
import de.wko.mdb.types.RestConnector;
import de.wko.mdb.types.request.LoginRequest;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WrapperConnector {

    public static final String CONNECTION_TYPE_NONE = "none";
    public static final String CONNECTION_TYPE_DB = "db";
    public static final String CONNECTION_TYPE_REST = "rest";

    @Autowired
    AuthClient authClient;

    @Autowired
    RestConfig restConfig;

    private boolean connected = false;
    private String connectionType = CONNECTION_TYPE_NONE;

    private AuthData restAuthData;

    public boolean connect(DbConnector connector) {
        connected = true;
        connectionType = CONNECTION_TYPE_DB;
        return connected;
    }

    public boolean connect(RestConnector connector) {
        System.out.println("### CONNECT");
        if (connector==null) return false;
        if (Strings.isEmpty(connector.getHost())) return false;
        if (Strings.isEmpty(connector.getPort())) connector.setPort("8080");
        if (Strings.isEmpty(connector.getUser())) return false;
        if (Strings.isEmpty(connector.getPassword())) connector.setPassword("");

        String baseUrl = "http://"+connector.getHost()+":"+connector.getPort();
        restConfig.setBaseUrl(baseUrl);
        LoginRequest request = new LoginRequest();
        request.setUsername(connector.getUser());
        request.setPassword(connector.getPassword());
        try {
            authClient.removeToken();
            restAuthData = authClient.signin(request);
            authClient.addToken(restAuthData.getToken());
            connected=true;
            connectionType = CONNECTION_TYPE_REST;
        } catch (MdbRestException e) {
            System.out.println("############# Exception: "+e.getMessage());
            System.out.println("## return "+connected);
        }
        return connected;
    }

    public String getConnectionType() {
        return connectionType;
    }
}
