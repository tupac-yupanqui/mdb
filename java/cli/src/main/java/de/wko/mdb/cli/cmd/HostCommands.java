package de.wko.mdb.cli.cmd;

import de.wko.mdb.cli.tables.HostTable;
import de.wko.mdb.rcl.HostClient;
import de.wko.mdb.rcl.MdbRestException;
import de.wko.mdb.types.Host;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

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
            try {
                hostname = InetAddress.getLocalHost().getHostName();
            } catch (UnknownHostException e) {
                System.out.println("Unknown localhost");
            }
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

                    System.out.println(h.getName());

                }
            }
        } catch (MdbRestException e) {
            System.out.println("MdbRestException "+e.getResponse().getMessage());
            e.printStackTrace();
        }

        return null;
    }

}
