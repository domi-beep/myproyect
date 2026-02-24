package com.evertecinc.b2c.enex.client.model.dto2.requests;

import com.evertecinc.b2c.enex.client.model.dto2.criterio.CriterioBusquedaGenericoDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class LiberarCardRequestDTO extends CriterioBusquedaGenericoDTO {
	@JsonProperty("upi")
	private String upi;

	@JsonProperty("rutUsuario")
	private String rutUsuario;

	@JsonProperty("clientType")
	private String clientType;

	@JsonProperty("portal")
	private String portal;

	@JsonProperty("idVehicle")
	private Long idVehicle;

	@JsonProperty("idCard")
	private Long idCard;
}
