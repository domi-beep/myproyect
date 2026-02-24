package com.evertecinc.b2c.enex.client.model.dto2.requests;

import com.evertecinc.b2c.enex.client.model.dto2.criterio.CriterioBusquedaGenericoDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ListStationJsonRequestDTO extends CriterioBusquedaGenericoDTO {

	@JsonProperty("nameStation")
	private String nameStation;

	@JsonProperty("nameArea")
	private String nameArea;

	@JsonProperty("nameRegion")
	private String nameRegion;

	@JsonProperty("nameCity")
	private String nameCity;
	
	@JsonProperty("clientType")
	private String clientType;
    
	@JsonProperty("codeStation")
	private String codeStation;

}
