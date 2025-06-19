package org.lukawska.openfeign.controller;

import lombok.RequiredArgsConstructor;
import org.lukawska.openfeign.client.UserFeignClient;
import org.lukawska.openfeign.dto.UserDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/feign/users")
public class UserFeignController {

	private final UserFeignClient userFeignClient;

	@GetMapping("/{id}")
	public UserDto getById(@PathVariable Long id){
		return userFeignClient.getUserById(id);
	}

}
