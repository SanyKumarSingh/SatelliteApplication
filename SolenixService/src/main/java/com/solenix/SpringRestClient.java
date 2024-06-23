package com.solenix;

import java.util.Arrays;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * @author solenix
 * Main Program to Test the Feature
 */
public class SpringRestClient {
	private static final String GET_EVENTS_ENDPOINT_URL = "http://localhost:8282/api/events/list";

	private static RestTemplate restTemplate = new RestTemplate();

	public static void main(String[] args) {
		SpringRestClient springRestClient = new SpringRestClient();

		// Process the default file
		springRestClient.processFile();

	}

	private void processFile() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		ResponseEntity<String> result = restTemplate.exchange(GET_EVENTS_ENDPOINT_URL, HttpMethod.GET, entity,
				String.class);
		System.out.println(result);
	}
}
