package com.evertecinc.b2c.enex.client.exceptions;

public class ContentsException extends Exception{

	private static final long serialVersionUID = 6805934818463665585L;
	
	public ContentsException() {
		super();
	}
	
	public ContentsException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ContentsException(String message, Throwable cause) {
		super(message, cause);
	}

	public ContentsException(String message) {
		super(message);
	}

	public ContentsException(Throwable cause) {
		super(cause);
	}
}
