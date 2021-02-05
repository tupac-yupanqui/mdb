package de.wko.mdb.rcl;

import de.wko.mdb.types.Archive;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ArchiveClient extends RestClient {

    public Archive getArchiveById(Long id) throws MdbRestException {
        Map params = new HashMap();
        params.put("id", id);
        return get(RestConfig.PATH_GET_ARCHIVE, Archive.class, params);
    }

    public List<Archive> getArchivesByHostId(Long hostId) throws MdbRestException {
        Map params = new HashMap();
        params.put("id", hostId);
        Archive[] list =  getList(RestConfig.PATH_GET_ARCHIVES_BY_HOST, Archive[].class, params);
        return Arrays.asList(list.clone());
    }

}
