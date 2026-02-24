package com.evertecinc.b2c.enex.client.model.dto2.requests;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ConstraintsByCardRequestDTO {
	@JsonProperty("idCard")
	public Long idCard;
	
	@JsonProperty("clientType")
	public String clientType;
	
	@JsonProperty("idClient")
	public Long idClient;
}