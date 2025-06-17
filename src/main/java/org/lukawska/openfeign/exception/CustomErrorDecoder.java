package org.lukawska.openfeign.exception;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;

public class CustomErrorDecoder implements ErrorDecoder {

	private final ErrorDecoder defaultErrorDecoder = new Default();

	@Override
	public Exception decode(String methodKey, Response response) {
		HttpStatus status = HttpStatus.valueOf(response.status());

		switch (status){
			case NOT_FOUND -> {
				return new RestException(ExceptionEnum.USER_NOT_FOUND);
			}
			case CONFLICT -> {
				return new RestException(ExceptionEnum.USER_ALREADY_EXISTS);
			}
			default -> {
				return defaultErrorDecoder.decode(methodKey, response);
			}
		}
	}
}
