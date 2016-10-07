package com.einvite.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.einvite.configuration.MyConfiguration;
import com.einvite.utils.RestServiceUtils;

@RestController
public class ZomatoController {

	private static final Logger LOGGER = LoggerFactory.getLogger(RestServiceUtils.class);
	
	@Autowired
	RestServiceUtils restServiceUtils;
	
	@Autowired
	@Qualifier("zomatoHeaders")
	HttpHeaders zomatoHeaders;
	
	@RequestMapping("/events/{locationId}")
	public String getRestaurantsByLocation(@PathVariable String locationId) {
		LOGGER.debug("In Restaurants");
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(MyConfiguration.ZOMATO_URL)
				.queryParam("city_id", locationId)
				.queryParam("format", "json");
		String json = restServiceUtils.createNewGetApiRequest(builder.build().encode().toUriString(), String.class, zomatoHeaders);
		LOGGER.debug(json);
		return json;	
	}
}
