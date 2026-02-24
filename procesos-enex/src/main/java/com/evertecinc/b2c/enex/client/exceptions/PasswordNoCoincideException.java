package com.evertecinc.b2c.enex.client.exceptions;

public class PasswordNoCoincideException extends Exception{
	
	/**
	 * Serializable
	 */
	private static final long serialVersionUID = -6340607844054298787L;

	public PasswordNoCoincideException() {
	}

	public PasswordNoCoincideException(String arg0) {
		super(arg0);
	}

	public PasswordNoCoincideException(Throwable arg0) {
		super(arg0);
	}

	public PasswordNoCoincideException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

}
