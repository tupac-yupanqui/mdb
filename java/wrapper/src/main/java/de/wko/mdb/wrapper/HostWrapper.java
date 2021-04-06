package de.wko.mdb.wrapper;

import de.wko.mdb.bl.service.HostService;
import de.wko.mdb.rcl.HostClient;
import de.wko.mdb.rcl.MdbRestException;
import de.wko.mdb.types.Host;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HostWrapper {

    @Autowired
    WrapperConnector connector;

    @Autowired
    HostClient hostClient;
    @Autowired
    HostService hostService;

    public List<Host> getAllHosts() throws WrapperException {
        System.out.println("############ "+connector.getConnectionType());
        if (connector.getConnectionType().equals(WrapperConnector.CONNECTION_TYPE_DB)) {
            System.out.println("********** get hosts from db");
            return hostService.getAllHosts();
        }
        if (connector.getConnectionType().equals(WrapperConnector.CONNECTION_TYPE_REST)) {
            try {
                System.out.println("********** get hosts from rest");
                return hostClient.getAllHosts();
            } catch (MdbRestException e) {
                throw new WrapperException(e.getError());
            }
        }
        throw new WrapperException("no active connection");
    }
}
