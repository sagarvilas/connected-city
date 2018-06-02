package com.challenge.connectedcity.impl;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.challenge.connectedcity.api.PathFinder;
import com.challenge.connectedcity.domain.City;
import com.challenge.connectedcity.domain.CityGraph;

/**
 * 
 * @author sagar 
 * 		   This class does a breadth first search of the graph of
 *         connected cities to check if a path exists between the source and
 *         destination
 */
@Service
public class BFSPathFinder implements PathFinder {
	private static final Logger LOGGER = LoggerFactory.getLogger(PathFinder.class);

	@Autowired
	CityGraph cityGraph;

	@Override
	public boolean isCityConnected(String source, String destination) {
		List<String> visited = new LinkedList();
		LinkedList<String> queue = new LinkedList<String>();
		visited.add(source);
		queue.add(source);
		Iterator<City> neighbourIterator;
		while (!queue.isEmpty()) {
			source = queue.poll();
			//if a city does not have neighbour continue with other cities
			if (cityGraph.getConnectedCities().get(source) == null) {
				LOGGER.info("{} city has no neighbours",source);
				visited.add(source);
				continue;
			}
			neighbourIterator = cityGraph.getConnectedCities().get(source).getNeighbours().iterator();
			City currentCity;
			while (neighbourIterator.hasNext()) {
				currentCity = neighbourIterator.next();
				if (currentCity.getName().equalsIgnoreCase(destination))
					return true;
				if (!visited.contains(currentCity.getName())) {
					visited.add(currentCity.getName());
					queue.add(currentCity.getName());
				}
			}
		}
		return false;
	}

}
