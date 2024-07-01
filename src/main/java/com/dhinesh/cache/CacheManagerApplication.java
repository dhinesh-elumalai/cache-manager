package com.dhinesh.cache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
public class CacheManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CacheManagerApplication.class, args);
	}

}
