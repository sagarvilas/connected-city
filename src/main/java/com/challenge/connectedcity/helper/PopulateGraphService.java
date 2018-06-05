package com.challenge.connectedcity.helper;

import java.io.IOException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.challenge.connectedcity.api.FileReader;
import com.challenge.connectedcity.api.FileWatcher;
import com.challenge.connectedcity.domain.City;
import com.challenge.connectedcity.domain.CityGraph;

@Service
public class PopulateGraphService {
	// initialize to classpath resource if path is not specified
	@Value("${file.path:classpath:city.txt}")
	private String filePath;

	@Autowired
	private FileWatcher fileWatcher;

	private FileReader fileReader;

	private CityGraph cityGraph;

	@Autowired
	public PopulateGraphService(CityGraph cityGraph, FileReader fileReader) {
		this.cityGraph = cityGraph;
		this.fileReader = fileReader;
	}

	@PostConstruct
	public void populateGraph() throws IOException, InterruptedException {
		fileReader.read(filePath)
				.forEach((K) -> cityGraph.addPath(new City(K.split(",")[0].trim().toLowerCase()), new City(K.split(",")[1].trim().toLowerCase())));
		fileWatcher.watchForFileChange(filePath);
	}
}
