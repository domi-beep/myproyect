package com.evertecinc.b2c.enex.client.model.dto2.requests;

import com.evertecinc.b2c.enex.client.model.dto2.criterio.CriterioBusquedaGenericoSessionDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ListDetalleFacturaJsonRequestDTO extends CriterioBusquedaGenericoSessionDTO {
	
	@JsonProperty("folio")
	private String folio;

	@JsonProperty("clientType")
	private String clientType;

}
