package com.evertecinc.b2c.enex.client.model.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegionDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5401901695070986001L;

	private Long   idRegion;
	private Long   idZone;
	private String code;
	private String name;
	private String active;
	private Boolean controlPass;
	
    
}
