package com.evertecinc.b2c.enex.client.model.dto2;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RegionAreaDTO {

	private String regionNombre;
	private Long regionId;
	private Long areaId;
	private String areaNombre;
	private Long ciudadId;
	private String ciudadNombre;
	
	public RegionAreaDTO(String regionNombre, Long regionId, Long areaId, String areaNombre, Long ciudadId,
			String ciudadNombre) {
		super();
		this.regionNombre = regionNombre;
		this.regionId = regionId;
		this.areaId = areaId;
		this.areaNombre = areaNombre;
		this.ciudadId = ciudadId;
		this.ciudadNombre = ciudadNombre;
	}
	
	
}
