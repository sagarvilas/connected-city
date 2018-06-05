package com.challenge.connectedcity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.connectedcity.api.PathFinder;
import com.challenge.connectedcity.domain.ServiceConstants;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class ConnectedCityController {

	PathFinder pathFinder;

	@Autowired
	public ConnectedCityController(PathFinder pathFinder) {
		this.pathFinder = pathFinder;
	}

	@ApiOperation(value = "Check if path exists between two cities", response = String.class)
	@RequestMapping(value = "/connected", method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> connected(@RequestParam("origin") String origin,
			@RequestParam("destination") String destination) {
		return new ResponseEntity<>(
				pathFinder.isNodeConnected(origin.trim().toLowerCase(), destination.trim().toLowerCase()) ? ServiceConstants.yes.toString()
						: ServiceConstants.no.toString(),
				HttpStatus.OK);
	}
}
