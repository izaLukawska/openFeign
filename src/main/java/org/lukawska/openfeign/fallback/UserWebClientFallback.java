package org.lukawska.openfeign.fallback;

import lombok.extern.slf4j.Slf4j;
import org.lukawska.openfeign.dto.UserDto;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class UserWebClientFallback implements UserWebClientWithFallback {

	@Override
	public Mono<UserDto> getUserById(Long id) {
		log.warn("Fallback activated for user id: {}", id);
		return Mono.just(new UserDto(id, "Fallback user"));
	}
}

