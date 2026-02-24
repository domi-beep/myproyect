package com.evertecinc.b2c.enex.client.model.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StationsDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2107250090958370211L;
	
	private String  stationCode;
	private String  name;
	private Long    idArea;
	private String clientType;
	private Boolean controlPass;
	private String latitud;
	private String longitud;

}
