package com.example.carros.Exception;

import org.springframework.web.bind.annotation.ExceptionHandler;

import java.nio.file.AccessDeniedException;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionConfig {
	
	@ExceptionHandler({
		EmptyResultDataAccessException.class
	})
	public ResponseEntity<?> errorNotFound(Exception ex) {
		return ResponseEntity.notFound().build();
	}
	
	@ExceptionHandler({
		IllegalArgumentException.class
	})
	public ResponseEntity<?> badRequest(Exception ex){
		return ResponseEntity.badRequest().build();
	}
	
	@ExceptionHandler({
		AccessDeniedException.class
	})
	public ResponseEntity<?> accessDenied() {
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new Error("Acesso NEGADO"));
	}
	
	class Error{
		public String error;
		
		public Error(String error) {
			this.error = error;
		}
	}
}
