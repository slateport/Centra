package dev.conductor.dataservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.github.cloudyrock.spring.v5.EnableMongock;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableMongock
@SpringBootApplication
public class DataServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataServicesApplication.class, args);
	}

	@Configuration
	static class WebConfiguration implements WebMvcConfigurer {

		@Override
		public void addViewControllers(ViewControllerRegistry registry) {
			registry.addViewController("/{spring:\\w+}")
					.setViewName("forward:/");
			registry.addViewController("/**/{spring:\\w+}")
					.setViewName("forward:/");
			registry.addViewController("/{spring:\\w+}/**{spring:?!(\\.js|\\.css)$}")
					.setViewName("forward:/");
		}
	}
}
