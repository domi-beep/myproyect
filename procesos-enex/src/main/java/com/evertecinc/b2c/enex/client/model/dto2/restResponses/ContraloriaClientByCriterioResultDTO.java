package com.evertecinc.b2c.enex.client.model.dto2.restResponses;

import java.util.List;
import java.util.Optional;

import com.evertecinc.b2c.enex.client.model.dto2.ClientMonitorDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class ContraloriaClientByCriterioResultDTO extends GenericResultDTO {
	
	@JsonProperty("listContraloriaClients")
	private Optional<List<ClientMonitorDTO>> listContraloriaClientes;
	
	@JsonProperty("countListContraloriaClients")
	private Optional<Long> countListContraloriaClients;

}
