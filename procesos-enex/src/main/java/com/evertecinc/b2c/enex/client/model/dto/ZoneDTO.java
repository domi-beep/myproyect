package com.evertecinc.b2c.enex.client.model.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ZoneDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3590779214170703110L;
	
	private Long   idZone;
	private String name;
	private String active;

}
