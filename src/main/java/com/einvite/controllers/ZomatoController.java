package com.einvite.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.einvite.configuration.MyConfiguration;
import com.einvite.utils.RestServiceUtils;

@RestController
@RequestMapping("/zomato")
public class ZomatoController {

	private static final Logger LOGGER = LoggerFactory.getLogger(RestServiceUtils.class);

	@Autowired
	RestServiceUtils restServiceUtils;

	@Autowired
	@Qualifier("zomatoHeaders")
	HttpHeaders zomatoHeaders;

	@RequestMapping("/getrestaurants/{locationId}")
	public String getRestaurantsByLocation(@PathVariable String locationId) {
		LOGGER.info("In Restaurants");
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(MyConfiguration.ZOMATO_BASE_URL + "/collections")
				.queryParam("city_id", locationId)
				.queryParam("format", "json");
		String json = restServiceUtils.createNewGetApiRequest(builder.build().encode().toUriString(), String.class, zomatoHeaders);
		LOGGER.info(json);
		return json;	
	}

	@RequestMapping("/categories")
	public String getAllCategories() {
		LOGGER.info("In getAllCategories");
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(MyConfiguration.ZOMATO_BASE_URL + "/categories" ).queryParam("format", "json");
		String json = restServiceUtils.createNewGetApiRequest(builder.build().encode().toUriString(), String.class, zomatoHeaders);
		LOGGER.info(json);
		return json;	
	}

	@RequestMapping("/cities")
	public String getCitiesByQuery(@RequestParam(required=false) String cityname, @RequestParam(required=false) String latitude, 
			@RequestParam(required=false) String longitude,	@RequestParam(required=false) String cityIds, 
			@RequestParam(required=false) String count) {
		LOGGER.info("In getCitiesByQuery");
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(MyConfiguration.ZOMATO_BASE_URL + "/cities").queryParam("format", "json");
		if (cityname != null) {
			builder.queryParam("q", cityname);
		}
		if (latitude != null) {
			builder.queryParam("lat", latitude);
		}
		if (longitude != null) {
			builder.queryParam("long", longitude);
		}
		if (cityIds != null) {
			builder.queryParam("city_ids", cityIds);
		}if (count != null) {
			builder.queryParam("count", count);
		}		
		System.out.println("builder." + builder.build().encode().toUriString());
		String json = restServiceUtils.createNewGetApiRequest(builder.build().encode().toUriString(), String.class, zomatoHeaders);
		LOGGER.info(json);
		return json;	
	}

	@RequestMapping("/cuisines")
	public String getAllCuisinesByLocations(@RequestParam(required=false) String cityId, @RequestParam(required=false) String latitude,
			@RequestParam(required=false) String longitude) {
		LOGGER.info("In getAllCuisinesByLocations");
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(MyConfiguration.ZOMATO_BASE_URL + "/cuisines");
		if (cityId != null) {
			builder.queryParam("city_id", cityId);
		}
		if (latitude != null) {
			builder.queryParam("lat", latitude);
		}
		if (longitude != null) {
			builder.queryParam("long", longitude);
		}		
		System.out.println("builder." + builder.build().encode().toUriString());
		String json = restServiceUtils.createNewGetApiRequest(builder.build().encode().toUriString(), String.class, zomatoHeaders);
		LOGGER.info(json);
		return json;	
	}
}
