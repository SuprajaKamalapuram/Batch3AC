package com.spicy.kitchen.exception;


@SuppressWarnings("serial")
public class IdNotFoundException extends RuntimeException{
	
	String message;
	
	

	@Override
	public String toString() {
		return "IdNotFoundException [message=" + message + "]";
	}

	public IdNotFoundException() {
		super();
	}

	public IdNotFoundException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	
}
