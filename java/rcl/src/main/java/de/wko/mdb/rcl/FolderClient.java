package de.wko.mdb.rcl;

import de.wko.mdb.types.Artist;
import de.wko.mdb.types.Folder;
import de.wko.mdb.types.FolderContent;
import de.wko.mdb.types.Host;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class FolderClient extends RestClient {

    public Folder getFolderById(Long id) throws MdbRestException {
        Map params = new HashMap();
        params.put("id", id);
        return get(RestConfig.PATH_GET_FOLDER_BY_ID, Folder.class, params);
    }

    public Folder getFolderByPath(Long archiveId, String path) throws MdbRestException {
        Map params = new HashMap();
        params.put("aid", archiveId);
        params.put("path", path);
        Folder folder = get(RestConfig.PATH_GET_FOLDER_BY_PATH, Folder.class, params);
        return folder;
    }

    public Folder saveFolder(Folder folder) throws MdbRestException {
        Folder f = post(RestConfig.PATH_SAVE_FOLDER, folder, Folder.class);
        return f;
    }


}
