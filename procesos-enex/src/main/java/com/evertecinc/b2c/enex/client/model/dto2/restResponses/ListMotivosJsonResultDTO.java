package com.evertecinc.b2c.enex.client.model.dto2.restResponses;

import java.util.List;
import java.util.Optional;

import com.evertecinc.b2c.enex.email.model.dto.EmailConfigDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ListMotivosJsonResultDTO extends GenericResultDTO {
	
	@JsonProperty("listaEmailConfig")
	private Optional<List<EmailConfigDTO>> listaEmailConfig;

}
