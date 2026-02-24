package com.evertecinc.b2c.enex.client.model.dto2.requests;

import com.evertecinc.b2c.enex.client.model.dto2.criterio.CriterioBusquedaGenericoDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ListMotivosJsonRequestDTO extends CriterioBusquedaGenericoDTO {
	
	@JsonProperty("status")
	private String status;

}
