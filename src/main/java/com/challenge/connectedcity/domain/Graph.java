package com.challenge.connectedcity.domain;

import java.util.Map;

public interface Graph<K, V> {
	public Map<K, V> getConnectedNodes();
		
	public void addPath(V source, V destination);
}
