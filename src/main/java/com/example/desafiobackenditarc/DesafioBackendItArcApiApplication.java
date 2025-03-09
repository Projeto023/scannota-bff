package com.example.desafiobackenditarc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.desafiobackenditarc.repository")
@EntityScan(basePackages = "com.example.desafiobackenditarc.model")
@ComponentScan(basePackages = "com.example.desafiobackenditarc")
@EnableFeignClients(basePackages = "com.example.desafiobackenditarc.clients")
@EnableCaching
public class DesafioBackendItArcApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesafioBackendItArcApiApplication.class, args);
	}

}
