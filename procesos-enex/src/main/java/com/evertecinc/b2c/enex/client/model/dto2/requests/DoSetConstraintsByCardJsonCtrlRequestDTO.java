package com.evertecinc.b2c.enex.client.model.dto2.requests;

import com.evertecinc.b2c.enex.client.model.dto2.criterio.CriterioBusquedaGenericoDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class DoSetConstraintsByCardJsonCtrlRequestDTO extends CriterioBusquedaGenericoDTO {
	@JsonProperty(value = "mode")
	private String mode;

	@JsonProperty(value = "idCard")
	private Long idCard;

	@JsonProperty(value = "stationCode")
	private String stationCode;
}
