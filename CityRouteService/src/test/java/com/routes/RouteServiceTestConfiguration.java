package com.routes;


import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import com.routes.controller.RouteController;
import com.routes.service.RouteFinderService;

@Profile("test")
@Configuration
public class RouteServiceTestConfiguration {

	@Bean
	@Primary
	public RouteController routeController() {
		return Mockito.mock(RouteController.class);
	}
	
	@Bean
	@Primary
	public RouteFinderService routeFinderService() {
		return Mockito.mock(RouteFinderService.class);
	}

}
