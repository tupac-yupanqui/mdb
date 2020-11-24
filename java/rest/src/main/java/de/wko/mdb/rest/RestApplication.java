package de.wko.mdb.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"de.wko.mdb"})
public class RestApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(RestApplication.class, args);
	}

}
