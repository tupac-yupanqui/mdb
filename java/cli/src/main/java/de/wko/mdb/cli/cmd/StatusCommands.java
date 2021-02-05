package de.wko.mdb.cli.cmd;

import de.wko.mdb.cli.CliContext;
import de.wko.mdb.cli.tools.FileSystemManager;
import de.wko.mdb.fs.FileSystem;
import de.wko.mdb.rcl.ArchiveClient;
import de.wko.mdb.rcl.HostClient;
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
        FileSystem fs = fsmanager.createFileSystem(archiveId);
        if (fs==null) {
            System.out.println("File system kann nicht geöffnet werden.");
            return;
        }
        context.setCurrentFileSystem(fs);
        return;
    }

    @ShellMethod(value = "Schließen des aktuellen Archivs", key = "close archive")
    public void closeArchive() {
        System.out.println("Archiv geschlossen");
        FileSystem fs = context.getCurrentFileSystem();
        if (fs!=null) fs.close();
        context.setCurrentFileSystem(context.getLocalFileSystem());
    }
}