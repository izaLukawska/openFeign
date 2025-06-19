package org.lukawska.openfeign.client;

import org.lukawska.openfeign.configuration.FeignConfig;
import org.lukawska.openfeign.dto.UserDto;
import org.lukawska.openfeign.fallback.UserWebClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
		name = "user-service",
		url = "${app.user-service.url}",
		configuration = FeignConfig.class,
		fallback = UserWebClientFallback.class
)
public interface UserFeignClient {

	@GetMapping("/users/{id}")
	UserDto getUserById(@PathVariable Long id);
}
