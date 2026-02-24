package com.evertecinc.b2c.enex.client.exceptions;

@SuppressWarnings("serial")
public class BaseEnexException extends RuntimeException {
	
	private static final String ERROR_MESSAGE = "Error al realizar operación";
	
	public BaseEnexException() {
		super();
	}
	
	public BaseEnexException(String message) {
		super(message);
	}

	public BaseEnexException(Throwable cause) {
		super(ERROR_MESSAGE, cause);
	}

}
