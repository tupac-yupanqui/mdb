package de.wko.mdb.cli.cmd;

import de.wko.mdb.cli.CLI;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
@ShellCommandGroup(value="Archivkommandos")
public class ArchiveCommands {

    @ShellMethod(value = "List all known archives", key = "list archives")
    public String listArchives() {
        //System.out.println(CLI.cwd);
        return null;
    }

    @ShellMethod(value = "Erzeugen eines Archives", key = "create archive")
    public void createArchive() {

    }
}
