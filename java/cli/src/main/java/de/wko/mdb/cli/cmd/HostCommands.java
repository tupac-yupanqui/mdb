package de.wko.mdb.cli.cmd;

import de.wko.mdb.cli.tables.HostTable;
import de.wko.mdb.rcl.HostClient;
import de.wko.mdb.rcl.MdbRestException;
import de.wko.mdb.types.Host;
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

    @ShellMethod(value = "Show host info", key = "show host")
    public String showHost(
            @ShellOption(defaultValue="localhost") String host,
            @ShellOption(value = "-a", help = "show all hosts") boolean all) {

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

        Long hostId = 0L;

        Host host = null;
        String ip = null;

        try {
            try {
                hostId = Long.parseLong(hid);
                if (hostId==0L) {
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
            if (host == null) {
                System.out.println("Host "+hid+" nicht gefunden");
                return null;
            }
            Scanner input = new Scanner(System.in);
            System.out.println("Edit Host [ID="+host.getId()+"]");
            System.out.print(String.format("Name [%s]: ", host.getName()));
            String inp = input.nextLine();
            if (Strings.isNotEmpty(inp)) host.setName(inp);
            System.out.print(String.format("Adresse [%s]: ", host.getAddress()));
            inp = input.nextLine();
            if (Strings.isNotEmpty(inp)) host.setAddress(inp);
            System.out.print(String.format("FTP Server [%s]: ", host.isFtp()?"Y/n":"y/N"));
            inp = input.nextLine();
            if (Strings.isNotEmpty(inp)) host.setFtp(inp.equalsIgnoreCase("Y"));
            printHost(host);
        } catch (MdbRestException e) {
            System.out.println("MdbRestException "+e.getResponse().getMessage());
            e.printStackTrace();
        }



        return null;
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
}
