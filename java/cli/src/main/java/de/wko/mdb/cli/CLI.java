package de.wko.mdb.cli;

import de.wko.mdb.cli.cmd.FilesystemCommands;
import de.wko.mdb.cli.cmd.FolderCommands;
import de.wko.mdb.cli.cmd.StatusCommands;
import de.wko.mdb.cli.tools.FileSystemManager;
import de.wko.mdb.rcl.AuthClient;
import de.wko.mdb.rcl.MdbRestException;
import de.wko.mdb.types.request.LoginRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.Console;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;


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

    @Autowired
    ApplicationContext appContext;

    @Autowired
    StatusCommands statusCommands;
    @Autowired
    FolderCommands folderCommands;
    @Autowired
    FilesystemCommands filesystemCommands;

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

        statusCommands.openArchive(1L);
        filesystemCommands.cd("Aeternitas");
        filesystemCommands.cd("House of Usher");
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
