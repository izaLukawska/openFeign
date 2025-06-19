package org.lukawska.openfeign.controller;

import lombok.RequiredArgsConstructor;
import org.lukawska.openfeign.client.UserFeignClient;
import org.lukawska.openfeign.dto.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("/feign/users")
public class UserFeignController {

	private final UserFeignClient userFeignClient;

	@GetMapping("/{id}")
	public UserDto getById(@PathVariable Long id) {
		return userFeignClient.getUserById(id);
	}

	@PostMapping("/upload")
	public ResponseEntity<String> uploadFile(@RequestPart("file") MultipartFile file) {
		if (file == null || file.isEmpty()) {
			return ResponseEntity.badRequest().body("File is empty or missing");
		}

		try {
			Path uploadPath = Paths.get("uploads");
			if (Files.notExists(uploadPath)) {
				Files.createDirectories(uploadPath);
			}

			String originalFileName = Path.of(Objects.requireNonNull(file.getOriginalFilename())).getFileName().toString();
			Path filePath = uploadPath.resolve(originalFileName);

			file.transferTo(filePath);

			return ResponseEntity.ok("File " + originalFileName + " is saved locally");
		} catch (IOException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error occurred while saving the file: " + e.getMessage());
		}
	}
}
