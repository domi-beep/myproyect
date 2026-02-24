package com.evertecinc.b2c.enex.client.exceptions;

public class ArchivoException extends Exception {

	/**
	 * Serializable
	 */
	private static final long serialVersionUID = 5698255678606708916L;
	private String uri;
	private String returnCode; 

	public ArchivoException() {
		super();
	}

	public ArchivoException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public ArchivoException(String arg0) {
		super(arg0);
	}

	public ArchivoException(Throwable arg0) {
		super(arg0);
	}

	public void setUri(String uri) {
		this.uri = uri;
	}
	
	public String getUri() {
		return uri;
	}

	public String getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}
	
}
