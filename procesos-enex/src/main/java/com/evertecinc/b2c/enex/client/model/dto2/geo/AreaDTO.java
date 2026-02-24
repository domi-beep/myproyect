package com.evertecinc.b2c.enex.client.model.dto2.geo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class AreaDTO {

	private Long idArea;
	private Long idRegion;
	private String code;
	private String name;
	private String active;
	private Long idCity;
	private Boolean controlPass;

	public AreaDTO(Long idArea, String code, String name, String active) {
		super();
		this.idArea = idArea;
		this.code = code;
		this.name = name;
		this.active = active;
	}
	
	public AreaDTO(Long idArea, Long idRegion, String code, String name, String active) {
		super();
		this.idArea = idArea;
		this.idRegion = idRegion;
		this.code = code;
		this.name = name;
		this.active = active;
	}

	public AreaDTO(Long idArea, Long idRegion, String code, String name, String active, Long idCity,
			Boolean controlPass) {
		super();
		this.idArea = idArea;
		this.idRegion = idRegion;
		this.code = code;
		this.name = name;
		this.active = active;
		this.idCity = idCity;
		this.controlPass = controlPass;
	}
	
	

}
