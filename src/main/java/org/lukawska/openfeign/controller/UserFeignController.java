package org.lukawska.openfeign.controller;

import lombok.RequiredArgsConstructor;
import org.lukawska.openfeign.client.UserFeignClient;
import org.lukawska.openfeign.dto.UserDto;
import org.lukawska.openfeign.service.FileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/feign/users")
public class UserFeignController {

	private final UserFeignClient userFeignClient;

	private final FileService fileService;

	@GetMapping("/{id}")
	public UserDto getById(@PathVariable Long id) {
		return userFeignClient.getUserById(id);
	}

	@PostMapping("/upload")
	public ResponseEntity<String> uploadFile(@RequestPart("file") MultipartFile file) {
		return fileService.handleFileUpload(file);
	}
}
