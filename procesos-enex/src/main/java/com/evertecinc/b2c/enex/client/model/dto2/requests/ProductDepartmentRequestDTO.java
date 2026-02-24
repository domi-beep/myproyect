package com.evertecinc.b2c.enex.client.model.dto2.requests;

import com.evertecinc.b2c.enex.client.model.dto2.criterio.CriterioBusquedaGenericoDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductDepartmentRequestDTO extends CriterioBusquedaGenericoDTO{

	@JsonProperty("idClient")
	private Long idClient;
	
	@JsonProperty("idDepartment")
	private Long idDepartment;
	
	@JsonProperty("productCode")
	private Long productCode;
	
	@JsonProperty("documentType")
	private Long documentType;
	
	@JsonProperty("isPadre")
	private Boolean isPadre;
	
}
