package org.lukawska.openfeign.client;

import org.lukawska.openfeign.dto.CreateUserRequest;
import org.lukawska.openfeign.dto.UserDto;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

@HttpExchange(url = "/users")
public interface UserHttpClient {

	@PostExchange
	UserDto createUser(@RequestBody CreateUserRequest request);
}
