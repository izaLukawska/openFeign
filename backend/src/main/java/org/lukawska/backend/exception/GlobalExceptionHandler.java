package org.lukawska.backend.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(RestException.class)
	public ResponseEntity<ExceptionResponse>  handleRestException(RestException restException){
		return ResponseEntity
				.status(restException.getExceptionEnum().getHttpStatus())
				.body(new ExceptionResponse(
						restException.getMessage(),
						restException.getExceptionEnum().getHttpStatus().value())
				);
	}
}
