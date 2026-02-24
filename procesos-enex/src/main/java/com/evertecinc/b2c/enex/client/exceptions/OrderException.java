package com.evertecinc.b2c.enex.client.exceptions;

@SuppressWarnings("serial")
public class OrderException extends RuntimeException {

	private static final String ERROR_MESSAGE = "Error al realizar operación";

	public OrderException() {
		super();
	}

	public OrderException(Throwable cause) {
		super(ERROR_MESSAGE, cause);
	}

}
