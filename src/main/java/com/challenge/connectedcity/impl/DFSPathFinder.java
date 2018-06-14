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

@Service
public class DFSPathFinder implements PathFinder{
	private static final Logger LOGGER = LoggerFactory.getLogger(DFSPathFinder.class);

	CityGraph cityGraph;

	@Autowired
	public DFSPathFinder(CityGraph cityGraph) {
		this.cityGraph = cityGraph;
	}

	@Override
	public boolean isNodeConnected(String source, String destination) {
		LOGGER.debug("Finding path from {} to {}",source,destination);
		List<String> visited = new LinkedList();
		return DFSUtil(source,destination,visited);
	}
	
	private boolean DFSUtil(String source, String destination, List<String> visited) {
		visited.add(source);
		if(source.equalsIgnoreCase(destination))
			return true;
		Iterator<City> neighbourIterator = cityGraph.getConnectedNodes().get(source).getNeighbours().iterator();
		while(neighbourIterator.hasNext()) {
			String next = neighbourIterator.next().getName();
			if(!visited.contains(next))
				if(DFSUtil(next, destination, visited))
					return true;
		}
		return false;
	}
}
