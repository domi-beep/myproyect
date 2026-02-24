package com.evertecinc.b2c.enex.client.model.dto2;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class StationConstraintDTO {

	private Long idZone;
	private String zone;
	private Long idRegion;
	private String region;
	private Long idArea;
	private String area;
	private String stationCode;
	private String station;

	public StationConstraintDTO(Long idRegion, String region, Long idArea, String area, String stationCode,
			String station) {
		super();
		this.idRegion = idRegion;
		this.region = region;
		this.idArea = idArea;
		this.area = area;
		this.stationCode = stationCode;
		this.station = station;
	}

}
