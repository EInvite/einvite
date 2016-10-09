package com.einvite.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.einvite.configuration.MyConfiguration;
import com.einvite.utils.RestServiceUtils;

@RestController
@RequestMapping("/eventbrite")
public class EventBriteController {

	private static final Logger LOGGER = LoggerFactory.getLogger(EventBriteController.class);
	
	@Autowired
	RestServiceUtils restServiceUtils;

	@Autowired
	@Qualifier("eventBriteHeaders")
	HttpHeaders eventBriteHeaders;
	
	@RequestMapping("/getEventCategories")
	public String getEventCategories() {
		LOGGER.info("In getEventCategories");
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(MyConfiguration.EVENT_BRITE_BASEURL + "/categories")
				.queryParam("token", MyConfiguration.EVENTBRITE_USERKEY);
		String json = restServiceUtils.createNewGetApiRequest(builder.build().encode().toUriString(), String.class, eventBriteHeaders);
		LOGGER.info(json);
		return json;	
	}
}
