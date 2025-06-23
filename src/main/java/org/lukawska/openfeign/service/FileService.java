package org.lukawska.openfeign.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.lukawska.openfeign.client.UserFeignClient;
import org.lukawska.openfeign.error.ValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@Slf4j
@RequiredArgsConstructor
public class FileService {

	private final UserFeignClient userFeignClient;

	public ResponseEntity<String> handleFileUpload(MultipartFile file) {
		if (file == null || file.isEmpty()) {
			throw new ValidationException("File is empty or missing");
		}

		return ResponseEntity.ok(userFeignClient.uploadFile(file));

	}
}
