package org.lukawska.openfeign.client;

import org.lukawska.openfeign.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
		name = "user-service-cb",
		url = "${app.user-service.url}",
		fallback = UserFeignClientFallback.class
)
public interface UserFeignClientWithFallback {

	@GetMapping("/users/{id}")
	UserDto getUser(@PathVariable("id") Long id);
}
