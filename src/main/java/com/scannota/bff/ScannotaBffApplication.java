package com.scannota.bff;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.scannota.bff")
@EnableFeignClients(basePackages = "com.scannota.bff.clients")
@EnableCaching
public class ScannotaBffApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScannotaBffApplication.class, args);
	}

}
