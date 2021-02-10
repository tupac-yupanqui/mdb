package de.wko.mdb.rcl;

import de.wko.mdb.types.FolderContent;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ContentClient extends RestClient {

    public FolderContent getFoldersByArchiveId(Long archiveId, String path) throws MdbRestException {
        Map params = new HashMap();
        params.put("aid", archiveId);
        params.put("path", path);
        FolderContent content = get(RestConfig.PATH_LIST_CONTENT, FolderContent.class, params);
        return content;
    }
}
