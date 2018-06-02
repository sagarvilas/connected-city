package com.challenge.connectedcity.helper;

import java.util.HashMap;
import java.util.Map;

import com.challenge.connectedcity.model.City;

public class MockCityHelper {

	public static Map<String, City> getConnectedCities() {
		Map<String, City> connectedCities = new HashMap();
		City a = new City("a");
		City b = new City("b");
		a.setNeighbours(b);
		b.setNeighbours(a);
		connectedCities.put("a", a);
		connectedCities.put("b", b);
		return connectedCities;
	}
	
	public static Map<String, City> getConnectedCitiesNoNeighbour() {
		Map<String, City> connectedCities = new HashMap();
		City a = new City("a");
		connectedCities.put("a", a);
		return connectedCities;
	}

}
