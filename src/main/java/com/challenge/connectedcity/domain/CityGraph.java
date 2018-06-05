package com.challenge.connectedcity.domain;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class CityGraph implements Graph<String, City> {

	private Map<String, City> connectedNodes = new HashMap();// adjacency matrix of connected cities

	public Map<String, City> getConnectedNodes() {
		return connectedNodes;
	}

	/**
	 * populates adjacency matrix for source and destination city
	 * 
	 * @param source
	 * @param destination
	 */
	public void addPath(City source, City destination) {
		if (connectedNodes.containsKey(source.getName())) {
			connectedNodes.get(source.getName()).setNeighbours(destination);
		} else {
			source.setNeighbours(destination);
			connectedNodes.put(source.getName(), source);
		}
		// if there is a path from source to destination then there is a path from
		// destination to source
		if (connectedNodes.containsKey(destination.getName())) {
			connectedNodes.get(destination.getName()).setNeighbours(source);
		} else {
			destination.setNeighbours(source);
			connectedNodes.put(destination.getName(), destination);
		}
	}
}
