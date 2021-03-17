package de.wko.mdb.rcl;

import de.wko.mdb.types.Extra;
import org.springframework.stereotype.Component;

@Component
public class ExtraClient extends RestClient {

    public Extra saveExtra(Extra extra) throws MdbRestException {
        Extra e = post(RestConfig.PATH_SAVE_EXTRA, extra, Extra.class);
        return e;
    }

}
