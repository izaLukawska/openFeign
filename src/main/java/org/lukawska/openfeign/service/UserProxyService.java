package org.lukawska.openfeign.service;

import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.reactor.circuitbreaker.operator.CircuitBreakerOperator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.lukawska.openfeign.fallback.UserWebClientFallback;
import org.lukawska.openfeign.fallback.UserWebClientWithFallback;
import org.lukawska.openfeign.dto.UserDto;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserProxyService implements UserWebClientWithFallback {

	private final WebClient webClient;

	private final UserWebClientFallback fallback;

	@Override
	public Mono<UserDto> getUserById(Long id) {
		return webClient.get()
				.uri("/users/{id}", id)
				.retrieve()
				.bodyToMono(UserDto.class)
				.transform(CircuitBreakerOperator.of(CircuitBreaker.ofDefaults("userServiceCB")))
				.retryWhen(Retry.backoff(3, Duration.ofSeconds(1))
						.maxBackoff(Duration.ofSeconds(5))
						.doBeforeRetry(signal -> log.warn("Retry attempt {} for user id {}",
								signal.totalRetries() + 1, id)
						))
				.onErrorResume(ex -> {
					if (ex instanceof CallNotPermittedException) {
						log.error("Circuit Breaker is open for user {}", id);
					} else {
						log.error("Error fetching user {}: {}", id, ex.getMessage());
					}
					return fallback.getUserById(id);
				});
	}
}
