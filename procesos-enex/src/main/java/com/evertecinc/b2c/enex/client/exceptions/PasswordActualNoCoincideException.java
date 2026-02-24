package com.evertecinc.b2c.enex.client.exceptions;

public class PasswordActualNoCoincideException extends Exception{
	
	/**
	 * Serializable
	 */
	private static final long serialVersionUID = -6340607844054298787L;

	public PasswordActualNoCoincideException() {
	}

	public PasswordActualNoCoincideException(String arg0) {
		super(arg0);
	}

	public PasswordActualNoCoincideException(Throwable arg0) {
		super(arg0);
	}

	public PasswordActualNoCoincideException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

}
