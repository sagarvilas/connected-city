package com.challenge.connectedcity.api;

import java.io.IOException;

public interface FileWatcher {

	void watchForFileChange(String filePath) throws IOException, InterruptedException;

}