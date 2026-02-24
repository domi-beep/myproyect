package com.evertecinc.b2c.enex.client.model.dto2.requests;

import java.math.BigDecimal;

import com.evertecinc.b2c.enex.client.model.dto2.criterio.CriterioBusquedaGenericoDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CreateVehiculoJsonCtrlRequestDTO extends CriterioBusquedaGenericoDTO{
	
	private String usernameJwt;
	private String emailJwt;
	
	@JsonProperty("idClient")
	private Long idClient;
	
	@JsonProperty("idDepartment")
	private Long idDepartment;
	
	@JsonProperty("status")
	private String status;
	
	@JsonProperty("plate")
	private String plate;
	
	@JsonProperty("productCode")
	private String productCode;
	
	@JsonProperty("typeCode")
	private String typeCode;
	
	@JsonProperty("docType")
	private String docType;
	
	@JsonProperty("remainingAmount")
	private BigDecimal remainingAmount;
	
	
	
}
