package com.evertecinc.b2c.enex.client.model.dto2;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CriterioComRegionDTO {

	private Long idArea;
	private Long idRegion;
	private String code;
	private String nameArea;
	private String active;
	private String nameRegion;
}
