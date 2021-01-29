package de.wko.mdb.cli;

import org.apache.commons.cli.HelpFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;


@SpringBootApplication
public class CLI implements CommandLineRunner {
    private static Logger LOG = LoggerFactory
            .getLogger(CLI.class);

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication((CLI.class));
        app.setBannerMode(Banner.Mode.OFF);
        app.run(CLI.class, args);
    }

    @Override
    public void run(String... args) {

    }

}
