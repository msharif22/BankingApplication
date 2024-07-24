package com.cg.banking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cg.banking.dto.ResponseDto;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ResponseDto> handleResourceNotFoundException(ResourceNotFoundException notFoundException){
		
		String message = notFoundException.getMessage();
		
		ResponseDto responseDto = new ResponseDto(message, false);
		
		return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(InsufficientBalanceException.class)
	public ResponseEntity<ResponseDto> handleInsufficientBalanceException(InsufficientBalanceException insufficientBalanceException){
		
		String message = insufficientBalanceException.getMessage();
		
		ResponseDto responseDto = new ResponseDto(message,false);
		
		return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.BAD_REQUEST);
	}
}
