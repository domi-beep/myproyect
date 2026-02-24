package com.evertecinc.b2c.enex.integracion.model.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChangeCardStatusResponse implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3204993734111878533L;
	private int returnCode;
	private String message;
	private String uri;

}
