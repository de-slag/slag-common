package de.slag.common.backendfortesting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "de.slag.basic.backend.simple", "de.slag.common.backendfortesting" })
public class BackendForTestingApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendForTestingApplication.class, args);
	}

}
