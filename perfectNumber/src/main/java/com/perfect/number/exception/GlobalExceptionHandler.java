package com.perfect.number.exception;

import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<?> handleValidationLogicException(ValidationException ve, WebRequest wr) {
		return new ResponseEntity<>(ve.getMessage(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleValidationLogicException(MethodArgumentNotValidException ve, WebRequest wr) {
		return new ResponseEntity<>(ve.getBindingResult().getFieldErrors().stream()
				.map(err -> err.getDefaultMessage()).collect(Collectors.joining(", ")), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<?> handleConstraintViolationExceptionn(ConstraintViolationException cve, WebRequest wr) {
		return new ResponseEntity<>(cve.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<?> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException matme, WebRequest wr) {
		return new ResponseEntity<>(matme.getMessage(), HttpStatus.BAD_REQUEST);
	}
}
