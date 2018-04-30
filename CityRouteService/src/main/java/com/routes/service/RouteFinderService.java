package com.routes.service;

import java.util.concurrent.ConcurrentHashMap;

import com.routes.bean.CityNode;
import com.routes.constants.IsConnectedEnum;

public interface RouteFinderService {

	IsConnectedEnum isConnected(String city1, String city2, ConcurrentHashMap<String, CityNode> dataMap);

}
