package com.routes.utils;

import java.util.List;
import java.util.Map;

public class RoutesUtil {


	/**
	 * Check if a string is null or empty
	 * @param string
	 * @return
	 */
	public static boolean isNullOrEmpty(String string) {
		return string == null || string.isEmpty();
	}

	/**
	 * Checks if a list is a null or empty 
	 * @param list
	 * @return
	 */
	public static boolean isNullOrEmpty(List<?> list) {
		return list == null || list.isEmpty();
	}
	
	/**
	 * Check if a map is null or empty
	 * @param createdElements
	 * @return
	 */
	public static boolean isNullOrEmpty(Map<?, ?> map) {
		return map == null || map.isEmpty();
	}

	
	
}
