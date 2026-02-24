package com.evertecinc.b2c.enex.client.model.dto2;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AreaDTO {

	private Long idArea;
	private Long idRegion;
	private String code;
	private String name;
	private String active;
	private Long idCity;
	
}
