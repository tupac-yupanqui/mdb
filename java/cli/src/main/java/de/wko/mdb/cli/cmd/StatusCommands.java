package de.wko.mdb.cli.cmd;

import de.wko.mdb.cli.CliContext;
import de.wko.mdb.cli.tables.VolumeTable;
import de.wko.mdb.cli.tools.FileSystemManager;
import de.wko.mdb.fs.ArchiveFileSystem;
import de.wko.mdb.fs.AvailabiltyCheck;
import de.wko.mdb.fs.AbstractFileSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
@ShellCommandGroup(value="Statuskommandos")
public class StatusCommands {

    @Autowired
    FileSystemManager fsmanager;
    @Autowired
    CliContext context;


    @ShellMethod(value = "Öffenen eines Archives", key = "open archive")
    public void openArchive(
            @ShellOption Long archiveId) {
        AbstractFileSystem fs = fsmanager.createFileSystem(archiveId);
        if (fs==null || !(fs instanceof ArchiveFileSystem)) {
            System.out.println("File system kann nicht geöffnet werden.");
            return;
        }
        ArchiveFileSystem afs = (ArchiveFileSystem)fs;
        if (afs.isFilesystemAvailable()) {
            System.out.println("Verzeichnis geöffnet");
        } else {
            System.out.println("Kein Zugriff auf Verzeichnis");
        }
        context.setCurrentFileSystem(fs);
        return;
    }

    @ShellMethod(value = "Schließen des aktuellen Archivs", key = "close archive")
    public void closeArchive() {
        System.out.println("Archiv geschlossen");
        AbstractFileSystem fs = context.getCurrentFileSystem();
        if (fs!=null) fs.close();
        context.setCurrentFileSystem(context.getLocalFileSystem());
    }

    @ShellMethod(value="Anzeigen aller lokalen Laufwerke", key="show volumes")
    public void showVolumes() {
        new VolumeTable(AvailabiltyCheck.getVolumes()).print();
    }
}