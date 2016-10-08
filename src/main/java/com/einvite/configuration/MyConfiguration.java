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
}
