package com.evertecinc.b2c.enex.client.model.dto2.requests;

import com.evertecinc.b2c.enex.client.model.dto2.criterio.CriterioBusquedaGenericoDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ListUsuariosBORequestDTO extends CriterioBusquedaGenericoDTO{
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("firstName")
	private String firstName;
	
	@JsonProperty("lastName")
	private String lastName;
	
	@JsonProperty("mail")
	private String mail;
	
	@JsonProperty("rut")
	private String rut;
	
	@JsonProperty("status")
	private String status;
	
	@JsonProperty("role")
	private String role;
}
