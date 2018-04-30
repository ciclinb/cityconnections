package com.routes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@EnableAutoConfiguration
@SpringBootApplication
public class MocksRoutesApplication extends SpringBootServletInitializer {

	public static void main(String[] args) 	{
		SpringApplication.run(MocksRoutesApplication.class, args);   
	}
}