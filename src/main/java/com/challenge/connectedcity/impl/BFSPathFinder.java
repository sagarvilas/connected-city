package com.challenge.connectedcity.impl;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.challenge.connectedcity.api.PathFinder;
import com.challenge.connectedcity.domain.City;
import com.challenge.connectedcity.domain.CityGraph;

/**
 * 
 * @author sagar-villas This class does a breadth first search of the graph of
 *         connected cities to check if a path exiists between the source and
 *         destination
 */
@Service
public class BFSPathFinder implements PathFinder {

	@Autowired
	CityGraph cityGraph;

	@Override
	public boolean isCityConnected(String source, String destination) {
		List<String> visited = new LinkedList();
		LinkedList<String> queue = new LinkedList<String>();
		visited.add(source);
		queue.add(source);
		Iterator<City> i;
		while (!queue.isEmpty()) {
			source = queue.poll();
			if (cityGraph.getConnectedCities().get(source) == null)
				return false;
			i = cityGraph.getConnectedCities().get(source).getNeighbours().iterator();
			City temp;
			while (i.hasNext()) {
				temp = i.next();
				if (temp.getName().equalsIgnoreCase(destination))
					return true;
				if (!visited.contains(temp.getName())) {
					visited.add(temp.getName());
					queue.add(temp.getName());
				}
			}
		}
		return false;
	}

}
