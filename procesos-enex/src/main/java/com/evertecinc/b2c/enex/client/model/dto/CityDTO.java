package com.evertecinc.b2c.enex.client.model.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CityDTO implements Serializable {


    /**
	 * 
	 */
	private static final long serialVersionUID = -4810293781947903307L;
	private Long idCity;
	private Long idRegion;
	private String code;
	private String name;
	private String active;
	
	
}
