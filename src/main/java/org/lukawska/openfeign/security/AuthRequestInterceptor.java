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
		requestTemplate.header("Authorization", "Bearer " + token);

		requestTemplate.header("X-Request-ID", UUID.randomUUID().toString());

		requestTemplate.header("X-Timestamp", Instant.now().toString());
	}
}
