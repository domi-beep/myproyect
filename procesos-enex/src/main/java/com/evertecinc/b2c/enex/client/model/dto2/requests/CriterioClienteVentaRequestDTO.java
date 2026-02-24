package com.evertecinc.b2c.enex.client.model.dto2.requests;

import com.evertecinc.b2c.enex.client.model.dto2.criterio.CriterioBusquedaGenericoDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CriterioClienteVentaRequestDTO extends CriterioBusquedaGenericoDTO{
	@JsonProperty("serialVersionUID")
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("upi")
	private String upi;
	
	@JsonProperty("legalName")
	private String legalName;
	
	@JsonProperty("estado")
	private String estado;
	
	@JsonProperty("clientType")
	private String clientType;
	
	@JsonProperty("repTipo")
	private String repTipo;
	
	@JsonProperty("clientId")
	private Long clientId;
}
