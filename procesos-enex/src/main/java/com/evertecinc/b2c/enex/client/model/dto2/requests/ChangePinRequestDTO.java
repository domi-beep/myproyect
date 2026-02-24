package com.evertecinc.b2c.enex.client.model.dto2.requests;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ChangePinRequestDTO {

	@JsonProperty("upi")
	private String upi;

	@JsonProperty("rutUsuario")
	private String rutUsuario;

	@JsonProperty("clientType")
	private String clientType;

	@JsonProperty("portal")
	private String portal;

	@JsonProperty("pin")
	private String pin;

	@JsonProperty("restypePin")
	private String restypePin;

	@JsonProperty("idCard")
	private Long idCard;
}
