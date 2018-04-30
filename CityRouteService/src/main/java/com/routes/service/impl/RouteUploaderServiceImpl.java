package com.routes.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.routes.bean.CityNode;
import com.routes.constants.Constants;
import com.routes.service.RouteUploaderService;

@Service
public class RouteUploaderServiceImpl implements RouteUploaderService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * Builds the connection graph in memory from a hard coded file location
	 */
	public ConcurrentHashMap<String, CityNode> buildDataMap() {
		final ConcurrentHashMap<String, CityNode> dataMap = new ConcurrentHashMap<String, CityNode>();

		//Get file from resources folder
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(Constants.FILE_NAME).getFile());
		
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {

			String sCurrentLine;
			while ((sCurrentLine = br.readLine()) != null) {
				// Check if direct relation can be found
				String connectedCities[] = getConnectedCityPair(sCurrentLine);
				updateConnectionsMap(dataMap, connectedCities);
			}

		} catch (IOException e) {
			logger.error(e.getLocalizedMessage());
		}
		
		return dataMap;
	}

	/**
	 * Updates the data map with the connections for this current pair of cities
	 * @param dataMap
	 * @param connectedCities
	 */
	public void updateConnectionsMap(final Map<String, CityNode> dataMap,
			String[] connectedCities) {
		if (connectedCities.length == Constants.EXPECTED_NUMBER_OF_NODES_PER_LINE) {

			List<CityNode> cities = new ArrayList<CityNode>();
			CityNode CityNode = null;
			for (String cityName : connectedCities) {
				if ((CityNode = dataMap.get(cityName)) == null) {
					CityNode = new CityNode(cityName);
					dataMap.put(cityName, CityNode);
				}
				cities.add(CityNode);
			}
			if (!cities.get(0).hasConnectionWith(cities.get(1).getName())) {
				cities.get(0).addConnection(cities.get(1).getName(),
						cities.get(1));
			}
			if (!cities.get(1).hasConnectionWith(cities.get(0).getName())) {
				cities.get(1).addConnection(cities.get(0).getName(),
						cities.get(0));
			}
		}
	}

	/**
 	 * Parses the file input line for the expected format of connected cities
	 * @param line
	 * @return
	 */
	private final String[] getConnectedCityPair(final String line) {
		String connectedCities[] = line.trim().toLowerCase().replace(Constants.COMMA_DELIMITER + " ", Constants.COMMA_DELIMITER)
				.split(Constants.COMMA_DELIMITER);
		return connectedCities;
	}
}

