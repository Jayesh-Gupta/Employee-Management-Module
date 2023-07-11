package com.example;

import com.example.model.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
@EnableConfigurationProperties({
		FileStorageProperties.class
})
public class ManagementApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ManagementApiApplication.class, args);
	}

}
