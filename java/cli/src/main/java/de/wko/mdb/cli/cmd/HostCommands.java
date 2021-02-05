package de.wko.mdb.cli.cmd;

import de.wko.mdb.cli.tables.ArchiveTable;
import de.wko.mdb.cli.tables.HostTable;
import de.wko.mdb.cli.tools.ConsoleReader;
import de.wko.mdb.rcl.ArchiveClient;
import de.wko.mdb.rcl.HostClient;
import de.wko.mdb.rcl.MdbRestException;
import de.wko.mdb.types.Archive;
import de.wko.mdb.types.Host;
import de.wko.mdb.types.enums.EHostType;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@ShellComponent
@ShellCommandGroup(value="Host Commands")
public class HostCommands {

    @Autowired
    HostClient hostClient;

    @Autowired
    ArchiveClient archiveClient;

    @ShellMethod(value = "Show host info", key = "show host")
    public String showHost(
            @ShellOption(defaultValue="localhost") String host,
            @ShellOption(value = "-a", help = "show all hosts") boolean all,
            @ShellOption(value = "-r", help = "alle Archive anzeigen") boolean showArchives) {

        String hostname = host;
        if ("localhost".equals(host)) {
            hostname = getLocalHostname();
        }
        try {
            if (all) {
                List<Host> hosts = hostClient.getAllHosts();
                new HostTable(hosts).print();
            } else {
                Host h = hostClient.getHostByName(hostname);
                if (h==null) {
                    System.out.println("Host "+hostname+" nicht gefunden");
                } else {
                    printHost(h);
                    if (showArchives) {
                        List<Archive> archives = archiveClient.getArchivesByHostId(h.getId());
                        new ArchiveTable(archives).print();
                    }
                }
            }
        } catch (MdbRestException e) {
            System.out.println("MdbRestException "+e.getResponse().getMessage());
            e.printStackTrace();
        }

        return null;
    }

    @ShellMethod(value = "Edit host info", key = "edit host")
    public String showHost(
            @ShellOption(defaultValue="0") String hid) {

        Host host = getHost(hid);
        if (host == null) {
            System.out.println("Host "+hid+" nicht gefunden");
            return null;
        }

        try {
            editHost(host);
            printHost(host);
            hostClient.saveHost(host);
        } catch (MdbRestException e) {
            System.out.println("MdbRestException "+e.getResponse().getMessage());
            e.printStackTrace();
        }

        return null;
    }

    @ShellMethod(value = "Create new host", key = "create host")
    public String createHost() {
        Host host = new Host();
        editHost(host);
        printHost(host);
        try {
            hostClient.saveHost(host);
        } catch (MdbRestException e) {
            System.out.println("MdbRestException "+e.getResponse().getMessage());
            e.printStackTrace();
        }
        return null;
    }

    @ShellMethod(value = "Delete host", key = "delete host")
    public String deleteHost(
            @ShellOption(defaultValue="0") String hid) {


        Host host = getHost(hid);
        if (host == null) {
            System.out.println("Host "+hid+" nicht gefunden");
            return null;
        }

        try {
            List<Archive> archives = archiveClient.getArchivesByHostId(host.getId());
            if (archives.size()>0) {
                System.out.println("Host "+host.getName()+" kann nicht gelöscht werden, da folgende Archive existieren:");
                new ArchiveTable(archives).print();
                return null;
            }
        } catch (MdbRestException e) {
            e.printStackTrace();
            return null;
        }

        ConsoleReader reader = new ConsoleReader();
        if (!reader.readBoolean("Host "+host.getName()+" wirklich löschen?", false)) {
            return null;
        }

        try {
            hostClient.deleteHost(host.getId());
        } catch (MdbRestException e) {
            System.out.println("MdbRestException "+e.getResponse().getMessage());
            e.printStackTrace();
        }

        return null;
    }

    private Host editHost(Host host) {
        ConsoleReader reader = new ConsoleReader();
        System.out.println("Edit Host [ID="+host.getId()+"]");
        host.setName(reader.readStringRequired("Name", host.getName()));
        host.setAddress(reader.readString("Adresse", host.getAddress()));
        host.setType(EHostType.fromString(reader.readEnum("Typ", EHostType.getValueList(), host.getType().getDescr())));
        if (host.getType()==EHostType.FTP) {
            host.setLogin(reader.readStringRequired("Login FTP Server", host.getLogin()));
            host.setPassword(reader.readStringRequired("Passwort", host.getPassword()));
        }
        return host;
    }

    private String getLocalHostname() {
        String hostname = null;
        try {
            hostname = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            System.out.println("Unknown localhost");
        }
        return hostname;
    }

    private String getLocalIp() {
        String ip = null;
        try {
            ip = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            System.out.println("Unknown localhost");
        }
        return ip;
    }

    private void printHost(Host host) {
        List<Host> list = new ArrayList<>();
        list.add(host);
        new HostTable(list).print();
    }

    private Host getHost(String hid) {
        Host host = null;
        try {
            try {
                Long hostId = Long.parseLong(hid);
                if (hostId == 0L) {
                    host = hostClient.getHostByName(getLocalHostname());
                    if (Strings.isEmpty(host.getAddress())) {
                        host.setAddress(getLocalIp());
                    }
                } else {
                    host = hostClient.getHostById(hostId);
                }
            } catch (NumberFormatException e) {
                if ("localhost".equals(hid)) {
                    hid = getLocalHostname();
                }
                host = hostClient.getHostByName(hid);
                if (hid.equals(getLocalHostname()) && Strings.isEmpty(host.getAddress())) {
                    host.setAddress(getLocalIp());
                }
            }
        } catch (MdbRestException e) {

        }
        return host;
    }
}
