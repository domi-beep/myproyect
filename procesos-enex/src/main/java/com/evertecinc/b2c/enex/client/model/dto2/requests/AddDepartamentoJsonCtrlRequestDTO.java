package com.evertecinc.b2c.enex.client.model.dto2.requests;

import com.evertecinc.b2c.enex.client.model.dto2.criterio.CriterioBusquedaGenericoDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AddDepartamentoJsonCtrlRequestDTO extends CriterioBusquedaGenericoDTO{
	
	private String usernameJWT;
	
	private String correoJWT;
	
	@JsonProperty("idClient")
	private Long idClient;
	
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("typeBalance")
	private String typeBalance;

}
