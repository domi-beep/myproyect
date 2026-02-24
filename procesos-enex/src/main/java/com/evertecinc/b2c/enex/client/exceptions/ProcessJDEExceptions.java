package com.evertecinc.b2c.enex.client.exceptions;

@SuppressWarnings("serial")
public class ProcessJDEExceptions extends RuntimeException {

	private static final String ERROR_MESSAGE = "Error al realizar operación";

	public ProcessJDEExceptions() {
		super();
	}

	public ProcessJDEExceptions(Throwable cause) {
		super(ERROR_MESSAGE, cause);
	}
	
	public ProcessJDEExceptions(String string) {
		super(string);
	}

}
