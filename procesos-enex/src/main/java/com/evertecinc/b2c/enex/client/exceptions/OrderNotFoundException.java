package com.evertecinc.b2c.enex.client.exceptions;

@SuppressWarnings("serial")
public class OrderNotFoundException extends RuntimeException {

	private static final String ERROR_MESSAGE = "Error al realizar operación";

	public OrderNotFoundException() {
		super();
	}

	public OrderNotFoundException(Throwable cause) {
		super(ERROR_MESSAGE, cause);
	}

}
