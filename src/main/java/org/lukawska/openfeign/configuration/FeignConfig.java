package org.lukawska.openfeign.configuration;

import feign.Logger;
import feign.codec.ErrorDecoder;

import org.lukawska.openfeign.error.CustomErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class FeignConfig {

	@Bean
	public Logger.Level feignLoggerLevel(){
		return Logger.Level.FULL;
	}

	@Bean
	public ErrorDecoder errorDecoder() {
		return new CustomErrorDecoder();
	}

	@Bean
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}
}
