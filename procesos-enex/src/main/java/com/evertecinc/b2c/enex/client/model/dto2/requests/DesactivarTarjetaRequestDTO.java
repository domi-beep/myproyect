package com.evertecinc.b2c.enex.client.model.dto2.requests;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class DesactivarTarjetaRequestDTO {

	@JsonProperty(value = "idVehicle")
	private Long idVehicle;
	
	@JsonProperty(value = "idCard")
	private Long idCard;
	
	@JsonProperty("portal")
	private String portal;

	@JsonProperty(value = "clientType", access = JsonProperty.Access.WRITE_ONLY)
	private String clientType;

	@JsonProperty("upi")
	private String upi;

	@JsonProperty("rutUsuario")
	private String rutUsuario;
}
