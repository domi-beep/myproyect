package com.evertecinc.b2c.enex.client.exceptions;

public class OrderIsNotValidMatchException extends Exception{

	public OrderIsNotValidMatchException() {
		super();
	}

	public OrderIsNotValidMatchException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public OrderIsNotValidMatchException(String message, Throwable cause) {
		super(message, cause);
	}

	public OrderIsNotValidMatchException(String message) {
		super(message);
	}

	public OrderIsNotValidMatchException(Throwable cause) {
		super(cause);
	}

}
