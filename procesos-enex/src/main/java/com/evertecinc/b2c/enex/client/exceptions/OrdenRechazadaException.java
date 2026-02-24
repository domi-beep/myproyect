package com.evertecinc.b2c.enex.client.exceptions;

import com.evertecinc.b2c.enex.client.model.dto2.TransbankDTO;

public class OrdenRechazadaException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6136540562489461424L;
	
	private TransbankDTO resp;

	public OrdenRechazadaException() {
		super();
	}

	public OrdenRechazadaException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public OrdenRechazadaException(String message, Throwable cause) {
		super(message, cause);
	}

	public OrdenRechazadaException(String message, TransbankDTO dto) {
		super(message);
		this.resp = dto;
	}
	
	public OrdenRechazadaException(String message) {
		super(message);
	}

	public OrdenRechazadaException(Throwable cause) {
		
		super(cause);
		
	}

	public TransbankDTO getResp() {
		return resp;
	}

	public void setResp(TransbankDTO resp) {
		this.resp = resp;
	}

}
