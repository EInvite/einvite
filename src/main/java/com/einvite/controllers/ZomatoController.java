package com.einvite.controllers;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class ZomatoController {

	private static final String ZOMATO_URL = "https://developers.zomato.com/api/v2.1/collections";
	private static final String USERKEY = "fbe213d1034b7748f97638f8e2cb70b4";
	
	@RequestMapping("/events/{locationId}")
	public String getRestaurantsByLocation(@PathVariable String locationId) {
		System.out.println("In Restaurants");
		HttpHeaders headers = new HttpHeaders();
		RestTemplate restTemplate = new RestTemplate();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		headers.set("user-key", USERKEY);
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(ZOMATO_URL)
				.queryParam("city_id", locationId)
				.queryParam("format", "json");
		
		System.out.println(builder.toUriString());

		HttpEntity<?> entity = new HttpEntity<>(headers);
		
		HttpEntity<String> response = restTemplate.exchange(
				builder.build().encode().toUri(), 
				HttpMethod.POST, 
				entity, 
				String.class);
		
		//TODO Clean this up
		System.out.println(response.getBody().substring(0, response.getBody().length()-1));
		String json = response.getBody().substring(0, response.getBody().length()-1);
		return json;	
	}
}
