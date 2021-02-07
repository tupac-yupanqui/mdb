package de.wko.mdb.cli.tools;

import de.wko.mdb.fs.*;
import de.wko.mdb.rcl.ArchiveClient;
import de.wko.mdb.rcl.HostClient;
import de.wko.mdb.rcl.MdbRestException;
import de.wko.mdb.types.Archive;
import de.wko.mdb.types.Host;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FileSystemManager {

    @Autowired
    ArchiveClient archiveClient;
    @Autowired
    HostClient hostClient;

    public AbstractFileSystem createFileSystem(Long archiveId)  {
        AbstractFileSystem result = null;
        if (archiveId==null || archiveId==0) {
            result = new LocalFileSystem();
            return result;
        }
        Archive archive = null;
        Host host = null;
        try {
            archive = archiveClient.getArchiveById(archiveId);
            if (archive == null) {
                System.out.println("Kein Archive mit id " + archiveId + " gefunden");
                return null;
            }
            host = hostClient.getHostById(archive.getHostId());
            switch (host.getType()) {
                case FTP:
                    result = new FtpFileSystem(host, archive);
                    break;
                case HARDDRIVE:
                    result = new ArchiveFileSystem(host, archive);
                    break;
                case MOBILE:
                    result = new ArchiveFileSystem(host, archive);
                    break;
                default:
                    System.out.println("Archivtyp unbekannt");
                    return null;
            }

        } catch (MdbRestException e) {
            e.printStackTrace();
            return null;
        }

        return result;
    }
}
