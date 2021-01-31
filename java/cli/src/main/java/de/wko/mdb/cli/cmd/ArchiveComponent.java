package de.wko.mdb.cli.cmd;

import de.wko.mdb.cli.CLI;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
@ShellCommandGroup(value="Archive Commands")
public class ArchiveComponent {

    @ShellMethod(value = "List all known archives", key = "list archives")
    public String listArchives() {
        //System.out.println(CLI.cwd);
        return null;
    }

}
