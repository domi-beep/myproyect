package com.evertecinc.b2c.enex.client.model.dto2.requests;

import java.util.List;

import com.evertecinc.b2c.enex.client.model.dto2.criterio.CriterioBusquedaGenericoDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ListVehiclesJsonRequestDTO extends CriterioBusquedaGenericoDTO {

	@JsonProperty("idClient")
	private Long idClient;

	@JsonProperty("plate")
	private String plate;

	@JsonProperty("nickname")
	private String nickname;

	@JsonProperty("productCode")
	private String productCode;

	@JsonProperty("idDepartment")
	private Long idDepartment;

	@JsonProperty("inicioAdblue")
	private Boolean inicioAdblue;

	@JsonProperty("cardStatus")
	private List<String> cardStatus;

	@JsonProperty("controlTotal")
	private Boolean controlTotal;

	@JsonProperty("vehicleTypeCode")
	private String vehicleTypeCode;

}
