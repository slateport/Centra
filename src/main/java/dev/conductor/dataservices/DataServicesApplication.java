package dev.conductor.dataservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.github.cloudyrock.spring.v5.EnableMongock;

@EnableMongock
@SpringBootApplication
public class DataServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataServicesApplication.class, args);
	}

}
