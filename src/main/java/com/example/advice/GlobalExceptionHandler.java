package com.example.advice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.exception.ProductNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<String> productNotFoundExceptionHandler(ProductNotFoundException e){
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> vadildationExceptionHandler(MethodArgumentNotValidException e){
		Map<String, String> validationErrors = new HashMap<>(); 
		e.getFieldErrors().stream().forEach(error -> validationErrors.put(error.getField(), error.getDefaultMessage()));
		return new ResponseEntity<Map<String,String>>(validationErrors, HttpStatus.BAD_REQUEST);
	}
}
