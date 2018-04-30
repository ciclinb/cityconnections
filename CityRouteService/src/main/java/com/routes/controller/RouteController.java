package com.routes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.routes.service.RouteFinderService;
import com.routes.service.RouteUploaderService;

/**
 * API endpoints to determine whether 2 cities are connected 
 * @author lbo
 *
 */
@RestController
public class RouteController {

	@Autowired
	private RouteFinderService routeFinderService;
	
	@Autowired
	private RouteUploaderService routeUploaderService;
	
	// controller class uses constructor injection.
	@Autowired
	public RouteController(RouteFinderService routeFinderService, RouteUploaderService routeUploaderService) {
		this.routeFinderService = routeFinderService;
		this.routeUploaderService = routeUploaderService;
	}
	
	/**
	 * RESTful endpoint for checking if 2 cities are connected
	 * @param city1
	 * @param city2
	 * @return
	 */
	@RequestMapping(value = "/connected", method = RequestMethod.GET, produces = "application/json")
	public String isConnected(@RequestParam(value ="origin", required = true) String city1, @RequestParam(value ="destination", required = true) String city2) {
		return routeFinderService.isConnected(city1, city2, routeUploaderService.buildDataMap()).toString().toLowerCase();
	}


}
