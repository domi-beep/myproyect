package com.evertecinc.b2c.enex.client.exceptions;

public class TrxExistException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4346787590336572931L;

	public TrxExistException() {
	}

	public TrxExistException(String arg0) {
		super(arg0);
	}

	public TrxExistException(Throwable arg0) {
		super(arg0);
	}

	public TrxExistException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

}
