package com.evertecinc.b2c.enex.integracion.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OrpakWSClientExistExceptions extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6664724419773807250L;

	private static final String ERROR_MESSAGE = "Error al realizar operación";
	
	private String uri;
	private String returnCode;

	public OrpakWSClientExistExceptions(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public OrpakWSClientExistExceptions(String message, Throwable cause) {
		super(message, cause);
	}

	public OrpakWSClientExistExceptions(String message) {
		super(message);
	}

	public OrpakWSClientExistExceptions() {
		super();
	}

	public OrpakWSClientExistExceptions(Throwable cause) {
		super(ERROR_MESSAGE, cause);
	}

}
