package com.supporthands.supportHands;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SupportHandsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SupportHandsApplication.class, args);
	}

}
