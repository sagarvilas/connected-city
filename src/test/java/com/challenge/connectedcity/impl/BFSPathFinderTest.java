package com.challenge.connectedcity.impl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.challenge.connectedcity.api.PathFinder;
import com.challenge.connectedcity.domain.CityGraph;
import com.challenge.connectedcity.helper.MockCityHelper;

@RunWith(MockitoJUnitRunner.class)
public class BFSPathFinderTest {

	@Mock
	CityGraph mockCityGraph;

	PathFinder pathFinder;
	
	@Before
	public void setup() {
		this.pathFinder = new BFSPathFinder(mockCityGraph);
	}

	@Test
	public void isCityConnectedTrueTest() throws Exception {
		when(mockCityGraph.getConnectedCities()).thenReturn(MockCityHelper.getConnectedCities());
		assertTrue(pathFinder.isCityConnected("a", "b"));
	}
	
	@Test
	public void isCityConnectedFalseTest() throws Exception {
		when(mockCityGraph.getConnectedCities()).thenReturn(MockCityHelper.getConnectedCities());
		assertFalse(pathFinder.isCityConnected("a", "c"));
	}

}
