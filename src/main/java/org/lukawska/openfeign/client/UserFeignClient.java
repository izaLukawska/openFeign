package org.lukawska.openfeign.client;

import org.lukawska.openfeign.configuration.FeignConfig;
import org.lukawska.openfeign.dto.UserDto;
import org.lukawska.openfeign.fallback.UserWebClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(
		name = "user-service",
		url = "${app.user-service.url}",
		configuration = FeignConfig.class,
		fallback = UserWebClientFallback.class
)
public interface UserFeignClient {

	@GetMapping("/users/{id}")
	UserDto getUserById(@PathVariable Long id);

	@PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	String uploadFile(@RequestPart("file") MultipartFile file);
}
