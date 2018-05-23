package com.cg.aieecosystemapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
public class AieEcosystemAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(AieEcosystemAppApplication.class, args);
	}
}
