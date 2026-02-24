package com.evertecinc.b2c.enex.integracion.model.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5238209617995492973L;
	private String returnCode;
	private String uri;
	private String message;

}
