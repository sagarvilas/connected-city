package com.challenge.connectedcity.domain;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

@Component
public class CityGraph {
	private static final Logger LOGGER = LoggerFactory.getLogger(CityGraph.class);
	// initialize to empty if path is not specified
	@Value("${file.path:}")
	private String filepath;

	private ResourceLoader resourceLoader;

	@Autowired
	public CityGraph(ResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;
	}

	private Map<String, City> connectedCities = new HashMap();// adjacency matrix of connected cities

	public Map<String, City> getConnectedCities() {
		return connectedCities;
	}

	/**
	 * populates adjacency matrix for source and destination city
	 * 
	 * @param source
	 * @param destination
	 */
	public void addPath(City source, City destination) {
		if (connectedCities.containsKey(source.getName())) {
			connectedCities.get(source.getName()).setNeighbours(destination);
		} else {
			source.setNeighbours(destination);
			connectedCities.put(source.getName(), source);
		}
		// if there is a path from source to destination then there is a path from
		// destination to source
		if (connectedCities.containsKey(destination.getName())) {
			connectedCities.get(destination.getName()).setNeighbours(destination);
		} else {
			destination.setNeighbours(source);
			connectedCities.put(destination.getName(), destination);
		}
	}

	/**
	 * Reads file from specified location and initializes city graph with neighbours
	 */
	@PostConstruct
	public void initializeCityGraph() {
		Resource resource = resourceLoader.getResource(filepath.isEmpty() ? "classpath:cities.txt" : filepath);
		LOGGER.info("Lodding cities from {}", resource.getFilename());
		try (BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {
			String line;
			while ((line = br.readLine()) != null) {
				City source = new City(line.split(",")[0]);
				City destination = new City(line.split(",")[1]);
				addPath(source, destination);
			}
		} catch (Exception e) {
			LOGGER.error("Error while reading file, will initialize with empty cities", e);
			addPath(new City(""), new City(""));
			e.printStackTrace();
		}

	}
}
