package com.evertecinc.b2c.enex.client.model.dto2.restResponses;

import java.util.List;
import java.util.Optional;

import com.evertecinc.b2c.enex.client.model.dto2.StationConstraintDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ConstraintsByCardResultDTO extends GenericResultDTO {

	@JsonProperty("constraints")
	private Optional<List<StationConstraintDTO>> constraints;

	@JsonProperty("personalizado")
	private Boolean personalizado;

	@JsonProperty("todos")
	private Boolean todos;

}
