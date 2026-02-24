package com.evertecinc.b2c.enex.client.model.dto2.requests;

import com.evertecinc.b2c.enex.client.model.dto2.criterio.CriterioBusquedaGenericoDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class DoReimprimirTarjetaRequestDTO extends CriterioBusquedaGenericoDTO {
	
	@JsonProperty(value = "idCard")
	private Long idCard;
	
	@JsonProperty(value = "direccion")
	private String address;
	
	@JsonProperty(value = "zone")
	private String zone;
	
	@JsonProperty(value = "idVehicle")
	private Long idVehicle;
	
	@JsonProperty(value = "nombre")
	private String name;
	
	@JsonProperty(value = "phone")
	private String phone;
	
	@JsonProperty(value = "rutUsuario")
	private String rutUsuario;
	
	@JsonProperty(value = "upi")
	private String upi;
	
	@JsonProperty(value = "portal")
	private String portal;
	
	@JsonProperty(value = "clientType")
	private String clientType;

}
