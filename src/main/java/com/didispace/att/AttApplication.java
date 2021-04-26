package com.didispace.att;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AttApplication {

	public static void main(String[] args) {
		SpringApplication.run(AttApplication.class, args);
	}

}
