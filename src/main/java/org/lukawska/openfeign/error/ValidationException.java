package org.lukawska.openfeign.error;

public class ValidationException extends RuntimeException {

	public ValidationException(String message) {
		super(message);
	}
}
