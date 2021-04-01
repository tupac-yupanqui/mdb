package de.wko.mdb.wrapper;

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

    @Autowired
    AuthClient authClient;
    @Autowired
    HostClient hostClient;

    @Autowired
    RestConfig restConfig;

    private boolean connected = false;

    public boolean connect(DbConnector connector) {
        return connected;
    }

    public boolean connect(RestConnector connector) {
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
            AuthData authData = authClient.signin(request);
            connected=true;

            List<Host> hosts = hostClient.getAllHosts();
            for (Host h: hosts) {
                System.out.println("## "+h.getName());
            }
        } catch (MdbRestException e) {
            System.out.println("############# Exception: "+e.getMessage());
        }
        return connected;
    }
}
