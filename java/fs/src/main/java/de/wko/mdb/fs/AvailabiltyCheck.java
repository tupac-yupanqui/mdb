package de.wko.mdb.fs;

import de.wko.mdb.types.Archive;
import de.wko.mdb.types.Host;
import de.wko.mdb.types.Volume;
import de.wko.mdb.types.enums.EHostType;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class AvailabiltyCheck {


    public static List<Host> filterAvailableHosts(List<Host> allHosts) {
        List<Host> result = new ArrayList<>();
        String localHostname = getLocalHostName();

        for (Host h : allHosts) {
            switch (h.getType()) {
                case HARDDRIVE:
                    if (h.getName().equalsIgnoreCase(localHostname)) {
                        result.add(h);
                    }
                    break;
                case FTP:
                    if (FtpFileSystem.isAvailable(h)) {
                        result.add(h);
                    }
                    break;
                case MOBILE:
                    result.add(h);
                    break;
            }

        }
        return result;
    }

    public static List<Archive> filterAvailableArchives(List<Archive> archives, List<Host> availableHosts) {
        List<Archive> result = new ArrayList<>();
        List<Volume> volumes = getVolumes();
        for (Archive a : archives) {
            for (Host h : availableHosts) {
                if (isArchiveOnline(a, h)) {
                    result.add(a);
                }
            }
        }

        return result;
    }

    public static boolean isArchiveOnline(Archive a, Host h) {
        List<Volume> volumes = getVolumes();
        if (a.getHostId()==h.getId()) {
            switch (h.getType()) {
                case HARDDRIVE:
                    if (AvailabiltyCheck.getLocalHostName().equalsIgnoreCase(h.getName())) {
                        return true;
                    }
                    return false;
                case FTP:
                    return FtpFileSystem.isAvailable(h);
                case MOBILE:
                    for (Volume v : volumes) {
                        if (h.getAddress().equalsIgnoreCase(v.getName())) {
                            h.setDrive(v.getDrive());
                            return true;
                        }
                    }
                    break;
            }
        }
        return false;
    }

    public static List<Volume> getVolumes() {
        File[] paths;
        FileSystemView fsv = FileSystemView.getFileSystemView();
        paths = File.listRoots();
        List<Volume> volumes = new ArrayList<>();
        for (File path: paths) {
            Volume v = new Volume();
            String name = fsv.getSystemDisplayName(path);
            v.setName(name.substring(0, name.indexOf("(")-1));
            v.setDrive(path.toString().substring(0,2));
            v.setType(fsv.getSystemTypeDescription(path));
            volumes.add(v);
        }
        return volumes;
    }

    public static String getLocalHostName() {
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            return "";
        }
    }

}
