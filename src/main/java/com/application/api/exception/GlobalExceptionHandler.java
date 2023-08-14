package com.application.api.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.application.api.response.ApiErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFound.class)
	public ResponseEntity<ApiErrorResponse> handleResourceNotFoundException(ResourceNotFound e, WebRequest webRequest) {
		ApiErrorResponse errorResponse = ApiErrorResponse.builder().timestamp(LocalDateTime.now())
				.message(e.getMessage()).errorDetails(e.toString()).path(webRequest.getDescription(false)).build();
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiErrorResponse> handleResourceNotFoundException(Exception e, WebRequest webRequest) {
		ApiErrorResponse errorResponse = ApiErrorResponse.builder().timestamp(LocalDateTime.now())
				.message(e.getMessage()).errorDetails(e.toString()).path(webRequest.getDescription(false)).build();
		return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
