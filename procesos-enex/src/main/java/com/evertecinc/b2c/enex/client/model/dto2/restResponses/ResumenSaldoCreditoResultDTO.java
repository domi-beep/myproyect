package com.evertecinc.b2c.enex.client.model.dto2.restResponses;

import java.util.Optional;

import com.evertecinc.b2c.enex.client.model.dto2.ResumenSaldoCreditoClienteDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ResumenSaldoCreditoResultDTO extends GenericResultDTO {

	@JsonProperty("resumenCliente")
	private Optional<ResumenSaldoCreditoClienteDTO> resumenCliente;

//	@JsonProperty("countListProductsDTO")
//	private Optional<Long> countListProductsDTO;

}
