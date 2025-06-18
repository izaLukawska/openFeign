package org.lukawska.openfeign.service;

import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import org.lukawska.openfeign.client.UserFeignClient;
import org.lukawska.openfeign.dto.UserDto;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserProxyService {

	private final UserFeignClient userFeignClient;

	@Retry(name = "userServiceRetry", fallbackMethod = "getUserFallback")
	@CircuitBreaker(name = "userServiceCB", fallbackMethod = "getUserFallback")
	public UserDto getUser(Long id) {
		return userFeignClient.getUser(id);
	}

	public UserDto getUserFallback(Long id, Throwable throwable) {
		if (throwable instanceof CallNotPermittedException) {
			System.out.println("CircuitBreaker is OPEN – fallback triggered for user " + id);
		} else {
			System.out.println("Fallback triggered due to: " + throwable.getClass().getSimpleName());
		}

		return new UserDto(id, "Fallback User");
	}
}
