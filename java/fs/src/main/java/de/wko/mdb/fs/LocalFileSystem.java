package de.wko.mdb.fs;

import de.wko.mdb.types.MdbFile;
import org.springframework.util.FileSystemUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LocalFileSystem extends AbstractFileSystem {
    public LocalFileSystem() {
        this.filesystemAvailable = true;
    }

    public String getCurrentDir() {
        return System.getProperty("user.dir").replace('\\','/');
    }

    public void changeCurrentDir(String d) throws FileSystemException {
        String path = getCurrentDir();
        if (!getCurrentDir().endsWith("/")) path += "/";
        d = d.replace('\\','/');
        if (d.equals("~")) {
            path = "/";
        } else if (d.equals(".")) {
            return;
        } else if (d.startsWith("/")) {
            path = path.substring(0,2)+d;
        } else if (pathStartsWithDrive(d)) {
            path = d;
        } else {
            path +=d;
        }
        File f = new File(path).getAbsoluteFile();
        if (f.exists() && f.isDirectory()) {
            try {
                System.setProperty("user.dir", f.getCanonicalPath());
            } catch (IOException e) {
                throw new FileSystemException("unbekanntes Directory");
            }
        } else {
            throw new FileSystemException("unbekanntes Directory");
        }
    }

    private boolean pathStartsWithDrive(String s) {
//        Pattern p = Pattern.compile("^(?!.*[\\\\\\/]\\s+)(?!(?:.*\\s|.*\\.|\\W+)$)(?:[a-zA-Z]:)?(?:(?:[^<>:\"\\|\\?\\*\\n])+(?:\\/\\/|\\/|\\\\\\\\|\\\\)?)+$");
        Pattern p = Pattern.compile("(?:[a-zA-Z]:)");
        Matcher m = p.matcher(s);
        return m.find();
    }

    public List<MdbFile> listDir(String d) throws FileSystemException {
        List<MdbFile> result = new ArrayList<>();
        try {
            File f = new File(System.getProperty("user.dir"));
            File[] files = f.listFiles();

            for (File osFile : files) {
                MdbFile file = new MdbFile();
                file.setName(osFile.getName());
                file.setSize(osFile.length());
                file.setDate(new Date(Files.getLastModifiedTime(Paths.get(osFile.getAbsolutePath())).toMillis()));
                file.setDirectory(osFile.isDirectory());
                result.add(file);
            }
        } catch (IOException e) {
            throw new FileSystemException(e.getMessage());
        }
        return result;

    }

    @Override
    public void makeDir(String d) throws FileSystemException {
        File newDir = new File(System.getProperty("user.dir")+"/"+d);
        if (!newDir.exists()) {
            newDir.mkdirs();
        } else {
            throw new FileSystemException("Verzeichnis existiert bereits");
        }
    }

    @Override
    public void removeDir(String d) throws FileSystemException {
        File dir = new File(System.getProperty("user.dir")+"/"+d);
        if (dir.exists()) {
            FileSystemUtils.deleteRecursively(dir);
        } else {
            throw new FileSystemException("Verzeichnis existiert nicht");
        }
    }

    @Override
    public void rename(String alt, String neu) throws FileSystemException {
        File falt = new File(alt);
        if (!falt.exists()) {
            throw new FileSystemException("Datei kann nicht umbenannt werden");
        }
        falt.renameTo(new File(neu));
    }

    public void close() {
    }
}
