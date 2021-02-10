package de.wko.mdb.cli.cmd;

import de.wko.mdb.cli.CliContext;
import de.wko.mdb.cli.tables.FolderTable;
import de.wko.mdb.fs.ArchiveFileSystem;
import de.wko.mdb.fs.LocalFileSystem;
import de.wko.mdb.rcl.ContentClient;
import de.wko.mdb.rcl.FolderClient;
import de.wko.mdb.rcl.HostClient;
import de.wko.mdb.rcl.MdbRestException;
import de.wko.mdb.types.Folder;
import de.wko.mdb.types.FolderContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.List;

@ShellComponent
@ShellCommandGroup(value="Folderkommandos")
public class FolderCommands {

    @Autowired
    ContentClient contentClient;

    @Autowired
    CliContext context;

    @ShellMethod(value = "Liste der Folder", key = "list content")
    public void listFolders() {
        if (!context.getCurrentFileSystem().isFilesystemAvailable()
                || context.getCurrentFileSystem() instanceof LocalFileSystem) {
            System.out.println("kein Archiv geöffnet");
            return;
        }
        ArchiveFileSystem fs = (ArchiveFileSystem) context.getCurrentFileSystem();
        try {
            Long aid = fs.getArchive().getId();
            //aid = 1L;
            FolderContent content = contentClient.getFoldersByArchiveId(aid, fs.getCurrentDir());
            new FolderTable(content).print();
        } catch (MdbRestException e) {
            System.out.println("MdbRestException "+e.getResponse().getMessage());
            e.printStackTrace();
        }

    }
}
