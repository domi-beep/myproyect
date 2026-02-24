package com.evertecinc.b2c.enex.client.model.dto2.requests;

import com.evertecinc.b2c.enex.client.model.dto2.criterio.CriterioBusquedaGenericoDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ClientMonitorRequestDTO extends CriterioBusquedaGenericoDTO {

	@JsonProperty("upi")
	private String upi;

	@JsonProperty("clientType")
	private String clientType;
	
	@JsonProperty("legalName")
	private String legalName;
	

	@JsonProperty("clientStatus")
	private String clientStatus;

}