package de.wko.mdb.cli;

import de.wko.mdb.cli.tools.FileSystemManager;
import de.wko.mdb.rcl.AuthClient;
import de.wko.mdb.rcl.MdbRestException;
import de.wko.mdb.types.AuthData;
import de.wko.mdb.types.Host;
import de.wko.mdb.types.enums.EHostType;
import de.wko.mdb.types.request.LoginRequest;
import org.jline.utils.AttributedStringBuilder;
import org.jline.utils.AttributedStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestClientException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.Console;
import java.io.PrintStream;
import java.util.EnumSet;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;


@SpringBootApplication
@ComponentScan(basePackages = {"de.wko.mdb"})
public class CLI implements CommandLineRunner {
    private static Logger LOG = LoggerFactory
            .getLogger(CLI.class);

    @Autowired
    CliContext context;

    @Autowired
    FileSystemManager fsManager;

    @Autowired
    AuthClient client;

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(CLI.class);
        app.setBanner(new Banner() {
            @Override
            public void printBanner(Environment environment, Class<?> sourceClass, PrintStream out) {
                System.out.println("----------");
                System.out.println("MDB CLI");
                System.out.println("----------");
            }
        });
        //app.setBannerMode(Banner.Mode.OFF);
        app.setWebApplicationType(WebApplicationType.NONE);
        app.run(args);
    }

    @Override
    public void run(String... args) {
    }

    @PostConstruct
    public void postMapping() {
        for (int i=0; i<3; i++) {
            LoginRequest lr = null;
            if (i==0) {
                lr = context.getLoginRequest();
            }
            if (lr==null) {
                lr = getLoginRequest();
            }
            try {
                context.setAuthData(client.signin(lr));
                context.setLoginRequest(lr);
                client.addToken(context.getAuthData().getToken());
                break;
            } catch (MdbRestException e) {
                System.out.println(e.getError());
                if (i==2) System.exit(0);
                continue;
            }
        }
        System.out.println("Login:  "+context.getAuthData().getUsername());

        context.setLocalFileSystem(fsManager.createFileSystem(0L));
        context.setCurrentFileSystem(context.getLocalFileSystem());
    }

    @PreDestroy
    public void finish() {
        context.saveSettings();
    }

    private LoginRequest getLoginRequest() {
        Console console = System.console();
        String username;
        String password;
        if (console==null) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Login: ");
            username = scanner.nextLine();
            System.out.print("Password: ");
            password = scanner.nextLine();
        } else {
            System.out.print("Login: ");
            username = System.console().readLine();
            System.out.print("Password: ");
            password = String.valueOf(System.console().readPassword());
        }
        LoginRequest r = new LoginRequest();
        r.setUsername(username);
        r.setPassword(password);
        return r;
    }
}
