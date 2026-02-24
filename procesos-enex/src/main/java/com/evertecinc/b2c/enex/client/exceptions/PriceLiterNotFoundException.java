package com.evertecinc.b2c.enex.client.exceptions;

@SuppressWarnings("serial")
public class PriceLiterNotFoundException extends RuntimeException {

	private static final String ERROR_MESSAGE = "Error al realizar operación";

	public PriceLiterNotFoundException(String string) {
		super();
	}

	public PriceLiterNotFoundException(Throwable cause) {
		super(ERROR_MESSAGE, cause);
	}

}
