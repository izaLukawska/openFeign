package org.lukawska.backend.controller;

import lombok.RequiredArgsConstructor;
import org.lukawska.backend.dto.UserDto;
import org.lukawska.backend.entity.User;
import org.lukawska.backend.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

	private final UserService userService;

	@GetMapping("/{id}")
	public UserDto getById(@PathVariable Long id){
		return userService.getUser(id);
	}

	@PostMapping
	public User createUser(@RequestBody User user){
		return userService.createUser(user);
	}
}
