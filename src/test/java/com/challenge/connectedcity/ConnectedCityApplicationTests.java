package com.challenge.connectedcity;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.challenge.connectedcity.domain.ServiceConstants;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ConnectedCityApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void connectedCitiesTest() {
		ResponseEntity<String> responseEntity = restTemplate.getForEntity("/connected?origin=Boston&destination=Newark",
				String.class);
		String response = responseEntity.getBody();
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(ServiceConstants.yes.toString(), response);

		responseEntity = restTemplate.getForEntity("/connected?origin=Philadelphia&destination=Albany", String.class);
		response = responseEntity.getBody();
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(ServiceConstants.no.toString(), response);
	}

}
