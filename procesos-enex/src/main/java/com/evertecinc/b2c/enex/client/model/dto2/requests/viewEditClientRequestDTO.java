package com.evertecinc.b2c.enex.client.model.dto2.requests;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class viewEditClientRequestDTO {
	
	@JsonProperty("idClient")
	Long idCliente;

}
