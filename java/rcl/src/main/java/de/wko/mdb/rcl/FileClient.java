package de.wko.mdb.rcl;

import de.wko.mdb.types.FileObject;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class FileClient extends RestClient {

    public FileObject getFileByFolderIdAndName(Long folderId, String fileName) throws MdbRestException {
        Map params = new HashMap();
        params.put("fid", folderId);
        params.put("name", fileName);
        FileObject file = get(RestConfig.PATH_GET_FILE_BY_FOLDER_AND_NAME, FileObject.class, params);
        return file;
    }


}
