package com.challenge.connectedcity.domain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

@Component
public class CityGraph {

	private ResourceLoader resourceLoader;

	@Autowired
	public CityGraph(ResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;
	}

	private Map<String, City> connectedCities = new HashMap();// adjacency matrix of connected cities

	public Map<String, City> getConnectedCities() {
		return connectedCities;
	}

	public void addPath(City source, City destination) {
		if (connectedCities.containsKey(source.getName())) {
			connectedCities.get(source.getName()).setNeighbours(destination);
		} else {
			source.setNeighbours(destination);
			connectedCities.put(source.getName(), source);
		}
		if (connectedCities.containsKey(destination.getName())) {
			connectedCities.get(destination.getName()).setNeighbours(destination);
		} else {
			destination.setNeighbours(source);
			connectedCities.put(destination.getName(), destination);
		}
	}
	
	public boolean isCityConnected(String source, String destination) {
		List<String> visited = new LinkedList();
		LinkedList<String> queue = new LinkedList<String>();
		visited.add(source);
		queue.add(source);
		Iterator<City> i;
		while(!queue.isEmpty()) {
			source = queue.poll();
			i = connectedCities.get(source).getNeighbours().iterator();
			City temp;
			while(i.hasNext()) {
				temp = i.next();
				if(temp.getName().equals(destination))
					return true;
				if(!visited.contains(source))
				{
					visited.add(source);
					queue.add(source);
				}
			}
		}
		return false;
	}

	@PostConstruct
	public void initalizeCityGraph() {
		Resource resource = resourceLoader.getResource("classpath:cities.txt");
		try (BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {
			String line;
			while ((line = br.readLine()) != null) {
				City source = new City(line.split(",")[0]);
				City destination = new City(line.split(",")[1]);
				addPath(source, destination);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
