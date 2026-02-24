package com.evertecinc.b2c.enex.client.exceptions;

public class UserException extends Exception{
	
	/**
	 * Serializable
	 */
	private static final long serialVersionUID = -6340607844054298787L;

	public UserException() {
	}

	public UserException(String arg0) {
		super(arg0);
	}

	public UserException(Throwable arg0) {
		super(arg0);
	}

	public UserException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

}
