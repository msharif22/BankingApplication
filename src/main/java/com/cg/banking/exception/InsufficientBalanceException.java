package com.cg.banking.exception;

public class InsufficientBalanceException  extends RuntimeException{

	public InsufficientBalanceException() {
		super("Insufficient Balance Exception");
	}
	
	public InsufficientBalanceException(String message) {
		super(message);
	}
}
