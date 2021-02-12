package de.wko.mdb.cli.cmd;

import de.wko.mdb.cli.CliContext;
import de.wko.mdb.cli.tables.ArchiveTable;
import de.wko.mdb.cli.tools.ConsoleReader;
import de.wko.mdb.cli.tools.ReaderExitException;
import de.wko.mdb.fs.ArchiveFileSystem;
import de.wko.mdb.fs.AvailabiltyCheck;
import de.wko.mdb.fs.AbstractFileSystem;
import de.wko.mdb.rcl.ArchiveClient;
import de.wko.mdb.rcl.HostClient;
import de.wko.mdb.rcl.MdbRestException;
import de.wko.mdb.types.Archive;
import de.wko.mdb.types.Host;
import de.wko.mdb.types.enums.EArchiveType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.ArrayList;
import java.util.List;

@ShellComponent
@ShellCommandGroup(value="Archivkommandos")
public class ArchiveCommands {

    @Autowired
    CliContext context;

    @Autowired
    ArchiveClient archiveClient;

    @Autowired
    HostClient hostClient;

    @ShellMethod(value = "Alle bekannten Archive anzeigen", key = "list archives")
    public void listArchives() {
        //System.out.println(CLI.cwd);
        return;
    }

    @ShellMethod(value = "Archivinformationen", key = "show archive")
    public void showArchive(
            @ShellOption(defaultValue="0") String aid,
            @ShellOption(value = "-a", help = "alle Archive anzeigen") boolean all,
            @ShellOption(value = "-v", help = "verfügbare Archive anzeigen") boolean available) {
        Long aidl;
        try {
            aidl  = Long.parseLong(aid);
        } catch (NumberFormatException e) {
            System.out.println("Ungültige Archiv ID");
            return;
        }
        try {
            if (all || available) {
                List<Archive> archives = archiveClient.getAllArchives();
                List<Host> hosts = hostClient.getAllHosts();
                if (available) {
                    List<Archive> list2 = AvailabiltyCheck.filterAvailableArchives(archives, AvailabiltyCheck.filterAvailableHosts(hosts));
                    new ArchiveTable(list2, hosts).print();
                } else {
                    new ArchiveTable(archives, hosts).print();
                }
            } else {
                Archive archive;
                if (aidl==0L) {
                    AbstractFileSystem fs =  context.getCurrentFileSystem();
                    if (!(fs instanceof ArchiveFileSystem)) {
                        System.out.println("Kein Archiv geöffnet");
                        return;
                    }
                    ArchiveFileSystem afs = (ArchiveFileSystem)fs;
                    archive = afs.getArchive();
                } else {
                        archive = archiveClient.getArchiveById(aidl);
                        if (archive == null) {
                            System.out.println(String.format("Archive %d nicht gefunden", aidl));
                            return;
                        }
                }
                printArchive(archive);
            }
        } catch (MdbRestException e) {
            System.out.println(String.format("Archive %d nicht gefunden", aidl));
            return;
        }
    }

    @ShellMethod(value = "Archiv bearbeiten", key = "edit archive")
    public void editArchive(
            @ShellOption(defaultValue="0") String aid) {

        Long aidl;
        try {
            aidl  = Long.parseLong(aid);
        } catch (NumberFormatException e) {
            System.out.println("Ungültige Archiv ID");
            return;
        }
        Archive archive;
        if (aidl==0L) {
            AbstractFileSystem fs =  context.getCurrentFileSystem();
            if (!(fs instanceof ArchiveFileSystem)) {
                System.out.println("Kein Archiv geöffnet");
                return;
            }
            ArchiveFileSystem afs = (ArchiveFileSystem)fs;
            archive = afs.getArchive();
        } else {
            try {
                archive = archiveClient.getArchiveById(aidl);
                if (archive == null) {
                    System.out.println(String.format("Archive %d nicht gefunden", aidl));
                    return;
                }
            } catch (MdbRestException e) {
                System.out.println(String.format("Archive %d nicht gefunden", aidl));
                return;
            }
        }
        try {
            editArchive(archive);
            printArchive(archive);
            saveArchive(archive);
        } catch (ReaderExitException e) {
        }
        return;
    }

    @ShellMethod(value = "Neues Archiv erzeugen", key = "create archive")
    public void createArchive() {
        Archive archive = new Archive();
        try {
            editArchive(archive);
            try {
                archive = archiveClient.saveArchive(archive);
                printArchive(archive);
            } catch (MdbRestException e) {
                System.out.println("MdbRestException "+e.getResponse().getMessage());
                e.printStackTrace();
            }
        } catch (ReaderExitException e) {
        }
        return;
    }


    private Archive editArchive(Archive archive) throws ReaderExitException  {
        List<Host> hosts;
        List<String> hostNames = new ArrayList<>();
        String hostName = "";
        try {
             hosts = hostClient.getAllHosts();
            for (Host host : hosts) {
                hostNames.add(host.getName());
                if (host.getId() == archive.getHostId()) {
                    hostName = host.getName();
                }
            }
        } catch (MdbRestException e) {
            System.out.println("Hostliste nicht verfügbar");
            return archive;
        }
        ConsoleReader reader = new ConsoleReader();
        System.out.println("Edit Archive [ID="+archive.getId()+"]");
        archive.setName(reader.readStringRequired("Name", archive.getName()));
        archive.setPath(reader.readStringRequired("Root", archive.getPath()));
        archive.setDescr(reader.readString("Beschreibung", archive.getDescr()));
        archive.setType(EArchiveType.fromString(reader.readFromList("Typ", EArchiveType.getValueList(), archive.getType().getDescr())));
        String selectedHost = reader.readFromList("Host", hostNames, hostName);
        Long selectedHostId = hosts.stream().filter(host -> host.getName().equals(selectedHost)).findAny().orElse(null).getId();
        archive.setHostId(selectedHostId);

        return archive;

    }
    private void printArchive(Archive archive) {
        new ArchiveTable(archive).print();
    }
    private Archive saveArchive(Archive archive) {
        try {
            Archive a = archiveClient.saveArchive(archive);
            return a;
        } catch (MdbRestException e) {
            System.out.println("MdbRestException "+e.getResponse().getMessage());
            return archive;
        }
    }

}
