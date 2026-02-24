package com.evertecinc.b2c.enex.client.exceptions;

@SuppressWarnings("serial")
public class DepartmentException extends RuntimeException {

	private static final String ERROR_MESSAGE = "Error al realizar operación";

	public DepartmentException() {
		super();
	}

	public DepartmentException(Throwable cause) {
		super(ERROR_MESSAGE, cause);
	}

	public DepartmentException(String string) {
		super(string);
	}

}
