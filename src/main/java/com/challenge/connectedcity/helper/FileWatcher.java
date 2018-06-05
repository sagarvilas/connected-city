package com.challenge.connectedcity.helper;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.challenge.connectedcity.domain.City;
import com.challenge.connectedcity.domain.CityGraph;

@Service
public class FileWatcher {
	private static final Logger LOGGER = LoggerFactory.getLogger(FileWatcher.class);
	// initialize to classpath resource if path is not specified
	@Value("${file.path:classpath:city.txt}")
	private String filePath;
	
	@Autowired
	FileReader fileReader;
	
	@Autowired
	private CityGraph cityGraph;

	@Async
	public void watchForFileChange(String filePath) throws IOException, InterruptedException {
		Path path = Paths.get(filePath.replaceFirst("file:", ""));
		WatchService watchService = FileSystems.getDefault().newWatchService();
		path.getParent().register(watchService, StandardWatchEventKinds.ENTRY_MODIFY);

		while ( watchService.take() != null) {
			for (WatchEvent<?> event : watchService.take().pollEvents()) {
				if (event.kind().equals(StandardWatchEventKinds.ENTRY_MODIFY)
						&& event.context().toString().equals(path.getFileName().toString())) {
					LOGGER.info("{} modified, recreating graph",event.context().toString());
					fileReader.readFile(filePath).forEach((K) -> cityGraph.addPath(new City(K.split(",")[0].trim().toLowerCase()),
							new City(K.split(",")[1].trim().toLowerCase())));
				}
			}
			watchService.take().reset();
		}
	}

}
