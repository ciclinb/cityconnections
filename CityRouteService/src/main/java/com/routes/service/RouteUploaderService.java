package com.routes.service;

import java.util.concurrent.ConcurrentHashMap;

import com.routes.bean.CityNode;

public interface RouteUploaderService {

	ConcurrentHashMap<String, CityNode> buildDataMap();

}
