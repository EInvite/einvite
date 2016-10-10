package com.einvite.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.einvite.configuration.MyConfiguration;
import com.einvite.utils.RestServiceUtils;

@RestController
@RequestMapping("/meetups")
public class MeetupsController {


	private static final Logger LOGGER = LoggerFactory.getLogger(MeetupsController.class);
	
	@Autowired
	RestServiceUtils restServiceUtils;

	@Autowired
	@Qualifier("meetupHeaders")
	HttpHeaders meetupHeaders;
	
	@RequestMapping("/findGroups")
	public String findGroups(@RequestParam(required=false) String zip, @RequestParam(required=false) String radius,
			@RequestParam(required=false) String category, @RequestParam(required=false) String order) {
		LOGGER.info("In findGroups");
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(MyConfiguration.MEETUP_BASEURL + "/find/groups")
				.queryParam("key", MyConfiguration.MEETUP_APIKEY)
				.queryParam("zip", zip)
				.queryParam("radius", radius)
				.queryParam("category", category)
				.queryParam("order", order);
		LOGGER.info(builder.build().encode().toUriString());
		String json = restServiceUtils.createNewGetApiRequest(builder.build().encode().toUriString(), String.class, meetupHeaders);
		LOGGER.info(json);
		return json;	
	}
}
