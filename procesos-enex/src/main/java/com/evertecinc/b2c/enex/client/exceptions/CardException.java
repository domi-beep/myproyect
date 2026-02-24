package com.evertecinc.b2c.enex.client.exceptions;

@SuppressWarnings("serial")
public class CardException extends RuntimeException {

	private static final String ERROR_MESSAGE = "Error al realizar operación";

	public CardException() {
		super();
	}

	public CardException(Throwable cause) {
		super(ERROR_MESSAGE, cause);
	}

	public CardException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public CardException(String message, Throwable cause) {
		super(message, cause);
	}

	public CardException(String message) {
		super(message);
	}

	
	
}
