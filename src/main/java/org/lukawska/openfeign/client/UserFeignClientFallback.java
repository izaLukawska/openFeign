package org.lukawska.openfeign.client;

import org.lukawska.openfeign.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserFeignClientFallback implements UserFeignClient{
	@Override
	public UserDto getUserById(Long id) {
		return UserDto.builder()
				.id(id)
				.username("Fallback user")
				.build();
	}
}
