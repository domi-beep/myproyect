package com.evertecinc.b2c.enex.client.exceptions;

public class OrderNotAcceptedException extends Exception{

	public OrderNotAcceptedException() {
		super();
	}

	public OrderNotAcceptedException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public OrderNotAcceptedException(String message, Throwable cause) {
		super(message, cause);
	}

	public OrderNotAcceptedException(String message) {
		super(message);
	}

	public OrderNotAcceptedException(Throwable cause) {
		super(cause);
	}

}
