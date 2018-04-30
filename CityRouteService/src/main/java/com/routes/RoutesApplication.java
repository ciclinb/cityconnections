package com.routes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.stereotype.Controller;

@EnableAutoConfiguration
@SpringBootApplication
@Controller
public class RoutesApplication extends SpringBootServletInitializer {

	public static void main(String[] args) 	{
		SpringApplication.run(RoutesApplication.class, args);   
	}
}