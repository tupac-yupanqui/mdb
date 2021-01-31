package de.wko.mdb.cli.cmd;

import de.wko.mdb.cli.CLI;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent()
public class FilesystemCommands {
    @ShellMethod(value = "Print working directory", key = "pwd")
    public String pwd() {
        //System.out.println(CLI.cwd);
        return null;
    }

}
