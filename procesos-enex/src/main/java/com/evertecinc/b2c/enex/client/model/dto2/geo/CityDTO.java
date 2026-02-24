package com.evertecinc.b2c.enex.client.model.dto2.geo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class CityDTO {
	
	private Long idCity;
	private Long idRegion;
	private String code;
	private String name;
	private String active;
	
}
