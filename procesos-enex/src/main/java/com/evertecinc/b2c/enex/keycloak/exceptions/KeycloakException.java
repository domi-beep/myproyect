package com.evertecinc.b2c.enex.keycloak.exceptions;

public class KeycloakException extends Exception{
	
	/**
	 * Serializable
	 */
	private static final long serialVersionUID = -6340607844054298787L;

	public KeycloakException() {
	}

	public KeycloakException(String arg0) {
		super(arg0);
	}

	public KeycloakException(Throwable arg0) {
		super(arg0);
	}

	public KeycloakException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

}
