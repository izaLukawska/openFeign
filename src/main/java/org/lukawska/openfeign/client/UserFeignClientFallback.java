package org.lukawska.openfeign.client;

import org.lukawska.openfeign.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserFeignClientFallback implements UserFeignClientWithFallback {

	@Override
	public UserDto getUser(Long id) {
		return UserDto.builder()
				.id(id)
				.username("Fallback User")
				.build();
	}
}
