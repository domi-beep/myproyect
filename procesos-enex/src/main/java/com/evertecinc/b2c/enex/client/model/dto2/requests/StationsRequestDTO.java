package com.evertecinc.b2c.enex.client.model.dto2.requests;

import java.util.List;

import lombok.Data;

@Data
public class StationsRequestDTO {
	
	private String areaStatus;
	private String regionStatus;
	private String zoneStatus;
	private Long idClient;
	private Long idCard;
	private String clientType;

	private List<Long> idZone;
	private List<Long> idRegion;
	private List<Long> idArea;

	private Boolean contieneEstaciones;

}
