package org.lukawska.openfeign.fallback;

import org.lukawska.openfeign.dto.UserDto;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public interface UserWebClientWithFallback {

	Mono<UserDto> getUserById(Long id);

}
