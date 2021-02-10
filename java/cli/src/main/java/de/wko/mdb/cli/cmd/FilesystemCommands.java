package de.wko.mdb.cli.cmd;

import de.wko.mdb.cli.CliContext;
import de.wko.mdb.cli.tables.FileTable;
import de.wko.mdb.fs.AbstractFileSystem;
import de.wko.mdb.fs.FileSystemException;
import de.wko.mdb.fs.sort.FileComparator;
import de.wko.mdb.types.MdbFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.util.FileSystemUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@ShellComponent()
@ShellCommandGroup(value = "Dateikommandos")
public class FilesystemCommands {

    @Autowired
    CliContext context;

    @ShellMethod(value = "Zeigt aktuelles Verzeichnis", key = "pwd")
    public void pwd() {
        AbstractFileSystem fs = context.getCurrentFileSystem();
        if (fs.isFilesystemAvailable()) {
            System.out.println(fs.getCurrentDir());
        } else {
            System.out.println("Kein Zugriff auf Verzeichnis");
        }
        return;
    }

    @ShellMethod(value = "Verzeichnis auflisten", key = {"ls","dir"})
    public void ls() {
        AbstractFileSystem fs = context.getCurrentFileSystem();
        try {
            if (fs.isFilesystemAvailable()) {
                List<MdbFile> files = fs.listDir("");
                Collections.sort(files, FileComparator.getFileComparator(FileComparator.SORT_TYPE));
                new FileTable(files).print();
            } else {
                System.out.println("Kein Zugriff auf Verzeichnis");
            }
        } catch (FileSystemException e) {
            System.out.println(e.getMessage());
        }
        return;
    }

    @ShellMethod(value = "Verzeichnis wechseln", key = "cd")
    public void cd(@ShellOption(defaultValue = "") String d) {
        AbstractFileSystem fs = context.getCurrentFileSystem();
        try {
            if (fs.isFilesystemAvailable()) {
                if (d.length()==0) {
                    ls();
                } else {
                    fs.changeCurrentDir(d);
                }
            } else {
                System.out.println("Kein Zugriff auf Verzeichnis");
            }
        } catch (FileSystemException e) {
            System.out.println(e.getMessage());
        }
        return;
    }

    @ShellMethod(value = "Verzeichnis anlegen", key = {"md", "mkdir"})
    public void mkdir(@ShellOption String d) {
        AbstractFileSystem fs = context.getCurrentFileSystem();
        try {
            if (fs.isFilesystemAvailable()) {
                fs.makeDir(d);
            } else {
                System.out.println("Kein Zugriff auf Verzeichnis");
            }
        } catch (FileSystemException e) {
            System.out.println(e.getMessage());
        }
        return;
    }

    @ShellMethod(value = "Verzeichnis löschen", key = {"rd", "rmdir"})
    public void rmdir(@ShellOption String d) {
        AbstractFileSystem fs = context.getCurrentFileSystem();
        try {
            if (fs.isFilesystemAvailable()) {
                fs.removeDir(d);
            } else {
                System.out.println("Kein Zugriff auf Verzeichnis");
            }
        } catch (FileSystemException e) {
            System.out.println(e.getMessage());
        }
        return;
    }

}