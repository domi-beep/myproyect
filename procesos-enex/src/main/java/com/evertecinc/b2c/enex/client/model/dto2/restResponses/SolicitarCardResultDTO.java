package com.evertecinc.b2c.enex.client.model.dto2.restResponses;

import java.util.Map;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SolicitarCardResultDTO extends GenericResultDTO{
	
	@JsonProperty("model")
	private Optional<Map<String, Object>> model;

}
