package com.challenge.connectedcity.helper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

@Service
public class FileReader {

	private static final Logger LOGGER = LoggerFactory.getLogger(FileReader.class);

	private ResourceLoader resourceLoader;

	@Autowired
	public FileReader(ResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;
	}

	public List<String> readFile(String filepath) {
		List<String> lines = new LinkedList<String>();
		Resource resource = resourceLoader.getResource(filepath);
		LOGGER.info("Lodding cities from {}", resource.getFilename());
		try (BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {
			String line = null;
			while ((line = br.readLine()) != null) {
				lines.add(line);
			}
		} catch (Exception e) {
			LOGGER.error("Error while reading file, will initialize with empty cities", e);
		}
		return lines;
	}
}
