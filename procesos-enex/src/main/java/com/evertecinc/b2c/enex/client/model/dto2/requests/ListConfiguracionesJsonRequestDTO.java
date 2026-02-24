package com.evertecinc.b2c.enex.client.model.dto2.requests;

import com.evertecinc.b2c.enex.client.model.dto2.criterio.CriterioBusquedaGenericoDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ListConfiguracionesJsonRequestDTO extends CriterioBusquedaGenericoDTO {
	
	@JsonProperty("idDepartment")
	private Long idDepartment;
	
	@JsonProperty("cardStatus")
	private String cardStatus;
	
	@JsonProperty("configuracion")
	private String configuracion;
	
	@JsonProperty("restriccion")
	private String restriccion;

	@JsonIgnore
	private String deptos;

	@JsonIgnore
	private Long idClient;
	
	// ******************* PARA FINES DE SESSION *******************
	@JsonProperty("upiSession")
	private String upiSession;

	@JsonProperty("rutUsuarioSession")
	private String rutUsuarioSession;

	@JsonProperty("portal")
	private String portal;

	@JsonProperty("clientType")
	private String clientType;
	// ******************* PARA FINES DE SESSION FIN *******************
}
