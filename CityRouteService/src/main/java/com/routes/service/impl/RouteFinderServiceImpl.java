package com.routes.service.impl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.routes.bean.CityNode;
import com.routes.bean.SysStatsBean;
import com.routes.constants.IsConnectedEnum;
import com.routes.service.RouteFinderService;
import com.routes.utils.RoutesUtil;
import com.routes.utils.SysStatsUtil;

/**
 * Finds routes given the data map, and start and end points
 * @author lbo
 *
 */
@Service
public class RouteFinderServiceImpl implements RouteFinderService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * Checks if there is a connection between provided starting point and end
	 * point.
	 * */
	public IsConnectedEnum isConnected(String city1, String city2, ConcurrentHashMap<String, CityNode> dataMap) {
		SysStatsBean stats = SysStatsUtil.getStartStatistics("RouteFinderService.isConnected");

		try {

			if (!RoutesUtil.isNullOrEmpty(city1) && !RoutesUtil.isNullOrEmpty(city2) && !RoutesUtil.isNullOrEmpty(dataMap) && mapHasNodes(dataMap, city1.toLowerCase(), city2.toLowerCase())) {
				return search(dataMap.get(city1.toLowerCase()), dataMap.get(city2.toLowerCase()));
			}

			return IsConnectedEnum.NO;
		} finally {
			SysStatsUtil.logStatistics(stats, logger);
		}
	}

	/**
	 * Search for a connection between a start and end city
	 * @param currentNode
	 * @param endPoint
	 * @return
	 */
	private IsConnectedEnum search(CityNode currentNode, CityNode endPoint) {
		IsConnectedEnum isConnected = IsConnectedEnum.NO;//defaults to no

		if (currentNode != null && endPoint != null && !RoutesUtil.isNullOrEmpty(currentNode.getName())) {

			if (currentNode.getName().equals(endPoint.getName())) {
				isConnected = IsConnectedEnum.YES;
			}

			cleanNodes(currentNode);
			if (!currentNode.isVisited()) {
				currentNode.visited();
			} else {
				isConnected = IsConnectedEnum.NO;
			}

			for (CityNode node : currentNode.getConnections().values()) {
				isConnected = search(node, endPoint);
			}
		}

		return isConnected;
	}

	/**
	 * Removes connections with visited node to prevent circular path
	 * @param city
	 */
	private void cleanNodes(CityNode city) {
		if (city != null && !RoutesUtil.isNullOrEmpty(city.getConnections())) {
			for (CityNode CityNode : city.getConnections().values()) {
				CityNode.getConnections().remove(city.getName());
			}
		}
	}

	/**
	 * Check if both nodes exist in data map
	 * */
	private final boolean mapHasNodes(Map<String, CityNode> data, String city1, String city2) {
		if (data == null || data.isEmpty() || !data.containsKey(city1)
				|| !data.containsKey(city2)) {
			return false;
		}

		return true;
	}

}
