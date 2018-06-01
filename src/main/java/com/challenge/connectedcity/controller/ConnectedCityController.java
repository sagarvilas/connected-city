package com.challenge.connectedcity.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConnectedCityController {

	
	@RequestMapping(value="/hello", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> hello(){
		return new ResponseEntity<>("{\"message\":\"hello\"}", HttpStatus.OK);
	}
}
