package de.wko.mdb.rcl;

import de.wko.mdb.types.FileObject;
import de.wko.mdb.types.Folder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class FileClient extends RestClient {

    public FileObject getFileByFolderIdAndName(Long folderId, String fileName) throws MdbRestException {
        Map<String,Object> params = new HashMap();
        params.put("fid", folderId);
        params.put("name", fileName);
        return get(RestConfig.PATH_GET_FILE_BY_FOLDER_AND_NAME, FileObject.class, params);
    }

    public FileObject saveFile(FileObject file) throws MdbRestException {
        return post(RestConfig.PATH_SAVE_FILE, file, FileObject.class);
    }

}
