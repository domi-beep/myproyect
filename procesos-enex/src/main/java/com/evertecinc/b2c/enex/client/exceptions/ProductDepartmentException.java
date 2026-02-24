package com.evertecinc.b2c.enex.client.exceptions;

@SuppressWarnings("serial")
public class ProductDepartmentException extends RuntimeException {

	private static final String ERROR_MESSAGE = "Error al realizar operación";

	public ProductDepartmentException() {
		super();
	}

	public ProductDepartmentException(Throwable cause) {
		super(ERROR_MESSAGE, cause);
	}

	public ProductDepartmentException(String string) {
		super(string);
	}

}