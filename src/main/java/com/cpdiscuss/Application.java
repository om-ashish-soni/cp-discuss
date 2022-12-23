package com.cpdiscuss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Controller;


@Controller

@SpringBootApplication()
@EnableMongoRepositories("com.cpdiscuss.*")
@EntityScan("com.cpdiscuss.*")
@ComponentScan("com.cpdiscuss.*")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
