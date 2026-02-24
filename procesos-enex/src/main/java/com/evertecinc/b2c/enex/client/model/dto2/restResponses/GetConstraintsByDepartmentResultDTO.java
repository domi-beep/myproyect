package com.evertecinc.b2c.enex.client.model.dto2.restResponses;

import java.util.List;
import java.util.Optional;

import com.evertecinc.b2c.enex.client.model.dto.StationsDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class GetConstraintsByDepartmentResultDTO extends GenericResultDTO {

	@JsonProperty("constraints")
	private Optional<List<StationsDTO>> constraints;

	@JsonProperty("personalizado")
	private Boolean personalizado;

	@JsonProperty("todos")
	private Boolean todos;

}
