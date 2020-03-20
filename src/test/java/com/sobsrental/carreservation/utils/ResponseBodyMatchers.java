package com.sobsrental.carreservation.utils;

import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.test.web.servlet.ResultMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ResponseBodyMatchers {
	
	private ObjectMapper objectMapper = new ObjectMapper();
	
	public <T> ResultMatcher containsObjectJson(Object expectedObject, Class<T> targetClass) {
		return result -> {
			String json = result.getResponse().getContentAsString();
			T actualObject = objectMapper.readValue(json, targetClass);
			assertThat(expectedObject).isEqualToComparingFieldByField(actualObject);
		};
	}
	
	public static ResponseBodyMatchers responseBody() {
		return new ResponseBodyMatchers();
	}
}
