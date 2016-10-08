package com.einvite.utils;

import java.io.IOException;
import java.rmi.UnexpectedException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class RestServiceUtils {

	private static final Logger LOGGER = LoggerFactory.getLogger(RestServiceUtils.class);

	private final RestTemplate restTemplate;

	@Autowired
	public RestServiceUtils(final RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
	public <T> T createNewGetApiRequest(String url, Class<T> clazz, HttpHeaders headers) {
		HttpEntity<byte[]> requestEntity = createHttpEntity(headers, null);
		ResponseEntity<T> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, clazz);
		//checkForResponseError(response, url);
		return response.getBody();
	}

	public <T> T createNewApiRequest(String url, String payload, Class<T> clazz, HttpMethod method, Object... uriVariables) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<byte[]> requestEntity = createHttpEntity(headers, payload);
		ResponseEntity<T> response = restTemplate.exchange(url, method, requestEntity, clazz, uriVariables);
		//checkForResponseError(response, url);
		return response.getBody();
	}
	
	public <T> T createNewApiRequest(HttpHeaders headers, String url, String payload, Class<T> clazz, HttpMethod method, Object... uriVariables) {
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<byte[]> requestEntity = createHttpEntity(headers, payload);
		ResponseEntity<T> response = restTemplate.exchange(url, method, requestEntity, clazz, uriVariables);
		//checkForResponseError(response, url);
		return response.getBody();
	}

	public static <T> T deserialiseJsonString(String response, String objectName, Class<T> clazz) throws UnexpectedException {
		try {
			JsonNode jsNode = new ObjectMapper().readValue(response, JsonNode.class);
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
			mapper.configure(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS, true);
			T res = mapper.readValue(jsNode.get(objectName).toString(), clazz);
			LOGGER.debug("returning deserialised json object: " + res);
			return res;
		} catch (IOException e) {
			throw new UnexpectedException("Error converting " + objectName
					+ " response of type " + clazz.getSimpleName() + " to json", e);
		}
	}

	private HttpEntity<byte[]> createHttpEntity(HttpHeaders headers, String payload) {
		HttpEntity<byte[]> requestEntity;
		if (payload == null) {
			requestEntity = new HttpEntity<byte[]>(headers);
		} else {
			requestEntity = new HttpEntity<byte[]>(payload.getBytes(), headers);
		}
		return requestEntity;
	}
}
