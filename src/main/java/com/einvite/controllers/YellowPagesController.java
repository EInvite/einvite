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
@RequestMapping("/yellowpages")
public class YellowPagesController {

	private static final Logger LOGGER = LoggerFactory.getLogger(YellowPagesController.class);

	@Autowired
	RestServiceUtils restServiceUtils;

	@Autowired
	@Qualifier("yellowPagesHeaders")
	HttpHeaders yellowPagesHeaders;
	
	@RequestMapping("/findBusiness")
	public String findBusinessByType(@RequestParam(name="what") String whatBusiness, @RequestParam(name="where") String businessLocation,
			@RequestParam(name="pgLen") String pageLen, @RequestParam(name="pg") String pageNum, 
			@RequestParam(name="dist") String dist, @RequestParam(required=false) String sflag) {
		LOGGER.info("In findBusiness");
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(MyConfiguration.YELLOW_PAGES_BASEURL + "/FindBusiness/")
				.queryParam("UID", MyConfiguration.YELLOW_PAGES_USERKEY)
				.queryParam("apikey", MyConfiguration.YELLOW_PAGES_APIKEY)
				.queryParam("what", whatBusiness)
				.queryParam("where", businessLocation)
				.queryParam("pgLen", pageLen)
				.queryParam("pg", pageNum)
				.queryParam("fmt", "JSON")
				.queryParam("lang", "en")
				.queryParam("dist", dist);
		
		if(sflag != null) {
			builder.queryParam("sflag", sflag);
		}
		
		System.out.println(builder.build().encode().toUriString());
		String json = restServiceUtils.createNewGetApiRequest(builder.build().encode().toUriString(), String.class, yellowPagesHeaders);
		LOGGER.info(json);
		return json;	
	}
	
}
