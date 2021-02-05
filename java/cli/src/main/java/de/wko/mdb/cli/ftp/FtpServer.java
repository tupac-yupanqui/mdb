package de.wko.mdb.cli.ftp;

import de.wko.mdb.types.Host;
import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.commons.net.ftp.FTPSClient;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

@Component
public class FtpServer {

    public boolean connect(Host host) {
        String ftpUrl = "ftp://admin:Wokoko1.@"+host.getAddress();
        try {
            URLConnection connection = new URL(ftpUrl).openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean checkAvailability(Host host) {
        try {
            FTPClient client = new FTPClient();
            client.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));
            client.connect("localhost");
            int reply = client.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                client.disconnect();
                throw new IOException("Exception in connecting to FTP Server");
            }

            System.out.println("LOGIN: "+client.login("admin", "Wokoko1."));


        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
