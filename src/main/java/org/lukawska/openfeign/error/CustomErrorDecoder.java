package org.lukawska.openfeign.error;

import feign.Response;
import feign.codec.ErrorDecoder;


import io.micrometer.core.instrument.util.IOUtils;
import lombok.RequiredArgsConstructor;


import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;


import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
@RequiredArgsConstructor
public class CustomErrorDecoder implements ErrorDecoder {

	private final ErrorDecoder defaultErrorDecoder = new Default();

	@Override
	public Exception decode(String methodKey, Response response) {
		HttpStatus httpStatus = HttpStatus.valueOf(response.status());

		return switch (httpStatus) {
			case NOT_FOUND -> new UserNotFoundException("User not found");
			case BAD_REQUEST -> new ValidationException(extractErrorMessage(response));
			case UNAUTHORIZED -> new AuthenticationException("Unauthorized");
			default -> defaultErrorDecoder.decode(methodKey, response);
		};
	}

	private String extractErrorMessage(Response response) {
		try {
			return IOUtils.toString(response.body().asInputStream(), StandardCharsets.UTF_8);
		} catch (IOException e) {
			return "Validation error";
		}
	}
}
