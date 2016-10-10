package com.einvite.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

@Configuration
public class MyConfiguration {

	public static final String ZOMATO_BASE_URL = "https://developers.zomato.com/api/v2.1";
	public static final String ZOMATO_USERKEY = "fbe213d1034b7748f97638f8e2cb70b4";
	
	public static final String EVENT_BRITE_BASEURL = "https://www.eventbriteapi.com/v3";
	public static final String EVENTBRITE_USERKEY = "LFGGOFQDUU3JZBILDAX4";
	
	public static final String YELLOW_PAGES_BASEURL = "http://api.sandbox.yellowapi.com";
	public static final String YELLOW_PAGES_USERKEY = "su32w9u9j2y8vwapp6jz5peb";
	public static final String YELLOW_PAGES_APIKEY = "metx4k29k4pmb69yzemd43z2";
	
	public static final String MEETUP_APIKEY = "5f5f65c2b1d291a654b347e701d5936";
	public static final String MEETUP_BASEURL = "https://api.meetup.com/";
	
	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	@Bean("zomatoHeaders")
	HttpHeaders getZomatoHttpHeader() {
		HttpHeaders zomatoHeaders = new HttpHeaders();
		zomatoHeaders.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		zomatoHeaders.set("user-key", ZOMATO_USERKEY);
		zomatoHeaders.setContentType(MediaType.APPLICATION_JSON);
		return zomatoHeaders;
	}
	
	@Bean("eventBriteHeaders")
	HttpHeaders getEventBriteHttpHeader() {
		HttpHeaders eventBriteHeaders = new HttpHeaders();
		eventBriteHeaders.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		//eventBriteHeaders.set("user-key", EVENTBRITE_USERKEY);
		eventBriteHeaders.setContentType(MediaType.APPLICATION_JSON);
		return eventBriteHeaders;
	}
	
	@Bean("yellowPagesHeaders")
	HttpHeaders getYellowPagesHttpHeader() {
		HttpHeaders yellowPagesHeaders = new HttpHeaders();
		yellowPagesHeaders.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		//eventBriteHeaders.set("user-key", EVENTBRITE_USERKEY);
		yellowPagesHeaders.setContentType(MediaType.APPLICATION_JSON);
		return yellowPagesHeaders;
	}
	
	@Bean("meetupHeaders")
	HttpHeaders getMeetupHttpHeader() {
		HttpHeaders meetupHeaders = new HttpHeaders();
		meetupHeaders.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		//eventBriteHeaders.set("user-key", EVENTBRITE_USERKEY);
		meetupHeaders.setContentType(MediaType.APPLICATION_JSON);
		return meetupHeaders;
	}
}
