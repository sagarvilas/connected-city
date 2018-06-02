package com.challenge.connectedcity.model;

import java.util.LinkedList;
import java.util.List;

public class City {

	private String name;
	private List<City> neighbours;

	public City() {
	}

	public City(String name) {
		this.name = name;
		this.neighbours = new LinkedList();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<City> getNeighbours() {
		return neighbours;
	}

	public void setNeighbours(City neighbours) {
		this.neighbours.add(neighbours);
	}

	@Override
	public String toString() {
		return String.format("City [name=%s, neighbours=%s]", name, neighbours);
	}
}
