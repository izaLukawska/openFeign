package org.lukawska.openfeign.controller;

import lombok.RequiredArgsConstructor;
import org.lukawska.openfeign.dto.UserDto;
import org.lukawska.openfeign.service.UserProxyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/webclient/users")
public class UserWebClientController {

	private final UserProxyService proxyService;

	@GetMapping("/{id}")
	public Mono<UserDto> getById(@PathVariable Long id) {
		return proxyService.getUserById(id);
	}
}
