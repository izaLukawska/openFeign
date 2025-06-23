package org.lukawska.openfeign.serviceTest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.lukawska.openfeign.client.UserFeignClient;
import org.lukawska.openfeign.error.ValidationException;
import org.lukawska.openfeign.service.FileService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verifyNoInteractions;

@ExtendWith(MockitoExtension.class)
public class FileServiceTest {

	@Mock
	private UserFeignClient userFeignClient;

	@InjectMocks
	private FileService fileService;

	@Test
	void shouldUploadFileSuccessfully() {
		// given
		MockMultipartFile mockFile = new MockMultipartFile(
				"file",
				"test.txt",
				"text/plain",
				"test content".getBytes()
		);

		Mockito.when(userFeignClient.uploadFile(mockFile))
				.thenReturn("Uploaded!");

		// when
		ResponseEntity<String> response = fileService.handleFileUpload(mockFile);

		// then
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("Uploaded!", response.getBody());
	}

	@Test
	void shouldThrowExceptionForEmptyFile() {
		// given
		MockMultipartFile emptyFile = new MockMultipartFile(
				"file",
				"empty.txt",
				"text/plain",
				new byte[0]
		);

		// when then
		ValidationException exception = assertThrows(ValidationException.class,
				() -> fileService.handleFileUpload(emptyFile));

		assertEquals("File is empty or missing", exception.getMessage());
		verifyNoInteractions(userFeignClient);
	}

	@Test
	void shouldThrowExceptionForNullFile() {
		// when then
		ValidationException exception = assertThrows(ValidationException.class,
				() -> fileService.handleFileUpload(null));

		assertEquals("File is empty or missing", exception.getMessage());
		verifyNoInteractions(userFeignClient);
	}
}
