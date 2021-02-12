package de.wko.mdb.rcl;

import de.wko.mdb.types.Host;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class HostClient extends RestClient {

    public Host getHostByName(String name) throws MdbRestException {
        Map params = new HashMap();
        params.put("name", name);
        return get(RestConfig.PATH_GET_HOST_BY_NAME, Host.class, params);
    }

    public Host getHostById(Long id) throws MdbRestException {
        Map params = new HashMap();
        params.put("id", id);
        return get(RestConfig.PATH_GET_HOST_BY_ID, Host.class, params);
    }

    public List<Host> getAllHosts() throws MdbRestException {
        Host[] list =  getList(RestConfig.PATH_HOSTS, Host[].class);
        return Arrays.asList(list.clone());
    }

    public Host saveHost(Host host) throws MdbRestException {
        Host h = post(RestConfig.PATH_SAVE_HOST, host, Host.class);
        return h;
    }

    public void deleteHost(Long id) throws MdbRestException {
        Map params = new HashMap();
        params.put("id", id);
        delete(RestConfig.PATH_DELETE_HOST, params);
    }
}
