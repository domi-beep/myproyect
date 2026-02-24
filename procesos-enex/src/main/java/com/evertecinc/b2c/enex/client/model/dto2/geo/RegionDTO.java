package com.evertecinc.b2c.enex.client.model.dto2.geo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class RegionDTO {

	@JsonProperty("idRegion")
	private Long idRegion;

	@JsonProperty("idZone")
	private Long idZone;

	@JsonProperty("code")
	private String code;

	@JsonProperty("name")
	private String name;

	@JsonProperty("active")
	private String active;

	@JsonProperty("controlPass")
	private Boolean controlPass;
	
	@JsonProperty(value = "idsZone", access = JsonProperty.Access.WRITE_ONLY)
	private List<Long> idsZone;
	
	public RegionDTO(Long idRegion, Long idZone, String code, String name, String active) {
		super();
		this.idRegion = idRegion;
		this.idZone = idZone;
		this.code = code;
		this.name = name;
		this.active = active;
	}

	/**
	 * Constructor para extended getRegionsByStations
	 */
	public RegionDTO(Long idRegion, Long idZone, String code, String name, String active, Boolean controlPass) {
		this.idRegion = idRegion;
		this.idZone = idZone;
		this.code = code;
		this.name = name;
		this.active = active;
		this.controlPass = controlPass;
	}
	
}
