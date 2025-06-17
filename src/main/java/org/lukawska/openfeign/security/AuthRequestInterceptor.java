package org.lukawska.openfeign.security;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.RequiredArgsConstructor;
import org.lukawska.openfeign.service.TokenService;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class AuthRequestInterceptor implements RequestInterceptor {

	private final TokenService tokenService;

	@Override
	public void apply(RequestTemplate requestTemplate) {
		String token = tokenService.getValidToken();
		requestTemplate.header("Authorization", "Bearer" + token);
		requestTemplate.header("Custom-request-ID", UUID.randomUUID().toString());
		requestTemplate.header("Custom-timestamp", Instant.now().toString());
	}
}
