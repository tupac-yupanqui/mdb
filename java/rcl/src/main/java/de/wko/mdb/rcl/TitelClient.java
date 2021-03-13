package de.wko.mdb.rcl;

import de.wko.mdb.types.Artist;
import de.wko.mdb.types.FileObject;
import de.wko.mdb.types.Titel;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class TitelClient extends RestClient {

    public Titel getTitelById(Long id) throws MdbRestException {
        Map params = new HashMap();
        params.put("id", id);
        return get(RestConfig.PATH_GET_TITEL_BY_ID, Titel.class, params);
    }

    public Titel saveTitel(Titel titel) throws MdbRestException {
        Titel t = post(RestConfig.PATH_SAVE_TITEL, titel, Titel.class);
        return t;
    }

}
