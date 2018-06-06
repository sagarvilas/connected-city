package com.challenge.connectedcity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ConnectedCityApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConnectedCityApplication.class, args);
	}

}
