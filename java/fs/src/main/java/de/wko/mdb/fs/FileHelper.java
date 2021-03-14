package de.wko.mdb.fs;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class FileHelper {

    public static boolean copyFile(String fileName, String srcPath, String dstPath) {
        File dstDir = new File(dstPath);
        if (!dstDir.exists()) {
            dstDir.mkdirs();
        }
        try {
            FileUtils.copyFile(new File(srcPath+fileName), new File(dstPath+fileName));
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    public static boolean removeFile(String fileName, String path) {
        File file = new File(path+fileName);
        return file.delete();
    }
}
