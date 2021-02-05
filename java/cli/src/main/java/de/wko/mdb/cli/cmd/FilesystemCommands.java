package de.wko.mdb.cli.cmd;

import de.wko.mdb.cli.CLI;
import de.wko.mdb.cli.CliContext;
import de.wko.mdb.fs.FileSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent()
@ShellCommandGroup(value = "Dateikommandos")
public class FilesystemCommands {

    @Autowired
    CliContext context;

    @ShellMethod(value = "Zeigt aktuelles Verzeichnis", key = "pwd")
    public void pwd() {
        FileSystem fs = context.getCurrentFileSystem();
        System.out.println(fs.getCurrentDir());
        return;
    }

    private FileSystem getFileSystem() {
        FileSystem fs = context.getCurrentFileSystem();
        if (fs==null) {
            //context.setCurrentFileSystem();
        }
        return fs;
    }
}
