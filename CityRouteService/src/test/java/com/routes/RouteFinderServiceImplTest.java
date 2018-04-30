package com.routes;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.ConcurrentHashMap;

import org.junit.Before;
import org.junit.Test;

import com.routes.bean.CityNode;
import com.routes.constants.IsConnectedEnum;
import com.routes.service.RouteFinderService;
import com.routes.service.impl.RouteFinderServiceImpl;
import com.routes.service.impl.RouteUploaderServiceImpl;

public class RouteFinderServiceImplTest {

	private RouteFinderService routeFinderService = new RouteFinderServiceImpl();
	private RouteUploaderServiceImpl routeUploaderService = new RouteUploaderServiceImpl();
	   
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testIsConnectedYes() {
		ConcurrentHashMap<String, CityNode> dataMap = new ConcurrentHashMap<String, CityNode>();
		String city1 = "chicago";
		String city2 = "nyc";
		String[] connectedCities = {city1, city2};
		routeUploaderService.updateConnectionsMap(dataMap, connectedCities);
		
		IsConnectedEnum isConnected = routeFinderService.isConnected(city1, city2, dataMap);
		
		assertEquals(IsConnectedEnum.YES, isConnected);
	}

	@Test
	public void testIsConnectedNo() {
		ConcurrentHashMap<String, CityNode> dataMap = new ConcurrentHashMap<String, CityNode>();
		String city1 = "philadelphia";
		String city2 = "pittsburgh";
		String city3 = "tampa";
		String[] connectedCities = {city1, city2};
		routeUploaderService.updateConnectionsMap(dataMap, connectedCities);
		
		IsConnectedEnum isConnected = routeFinderService.isConnected(city1, city3, dataMap);
		
		assertEquals(IsConnectedEnum.NO, isConnected);
	}

	@Test
	public void testIsConnectedNoEmptyMap() {
		ConcurrentHashMap<String, CityNode> dataMap = new ConcurrentHashMap<String, CityNode>();
		String city1 = "philadelphia";
		String city3 = "tampa";
		
		IsConnectedEnum isConnected = routeFinderService.isConnected(city1, city3, dataMap);
		
		assertEquals(IsConnectedEnum.NO, isConnected);
	}

}
