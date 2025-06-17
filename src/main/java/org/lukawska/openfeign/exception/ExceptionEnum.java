package org.lukawska.openfeign.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ExceptionEnum {

	USER_NOT_FOUND("User is not found", HttpStatus.NOT_FOUND),
	USER_ALREADY_EXISTS("User already exists", HttpStatus.CONFLICT);

	private final String message;
	private final HttpStatus httpStatus;
}
