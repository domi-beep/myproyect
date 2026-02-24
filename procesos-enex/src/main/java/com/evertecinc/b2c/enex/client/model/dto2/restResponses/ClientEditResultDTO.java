package com.evertecinc.b2c.enex.client.model.dto2.restResponses;

import java.util.List;
import java.util.Map;

import com.evertecinc.b2c.enex.client.model.dto.ClientDTO;
import com.evertecinc.b2c.enex.client.model.dto2.ContratoStorageDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ClientEditResultDTO extends GenericResultDTO {
	
	@JsonProperty("editCliente")
	private ClientDTO cliente;
	
	@JsonProperty("listaContratos")
	private List<ContratoStorageDTO> listaContratos;
	
	@JsonProperty("model")
	private Map<String, Object> model;
	


	
	

}
