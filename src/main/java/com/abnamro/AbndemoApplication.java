package com.abnamro;

import java.util.Random;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
@EnableGlobalMethodSecurity(securedEnabled=true)
public class AbndemoApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(AbndemoApplication.class, "--debug");
    }
    
    
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(applicationClass);
	}

	private static Class<AbndemoApplication> applicationClass = AbndemoApplication.class;
	
	@Bean
	public HealthIndicator transactionServicesIdicator() {
		final Random random = new Random();
		return new HealthIndicator() {

			@Override
			public Health health() {
				if (random.nextBoolean()) {
					return Health.up().build();
				}
				return Health.down().build();
			}

		};

	}
}
