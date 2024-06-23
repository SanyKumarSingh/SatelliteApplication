package com.solenix;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;


/**
* Application class is the main entry point of this SpringBoot Application. 
* Execute the main method to run the application on in-memory Tomcat Server and H2 database.
* 
*/
@SpringBootApplication
@EnableScheduling
public class Application {
	
	private static final Logger logger = LoggerFactory.getLogger(Application.class);
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	public static void main(String[] args) {
		logger.info("Started SpringBoot Application!!!");
		SpringApplication.run(Application.class, args);
	}
	
}
