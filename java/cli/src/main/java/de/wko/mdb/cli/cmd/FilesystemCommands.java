package de.wko.mdb.cli.cmd;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent()
public class FilesystemCommands {
    @ShellMethod(value = "Print working directory", key = "pwd")
    public String pwd() {
        return null;
    }

}
