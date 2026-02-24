package com.evertecinc.b2c.enex.client.model.dto2.restResponses;

import java.util.List;
import java.util.Optional;

import com.evertecinc.b2c.enex.client.model.dto2.StationAreaDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ListStationJsonResultDTO extends GenericResultDTO {

	@JsonProperty("listStation")
	private Optional<List<StationAreaDTO>> listStation;

	@JsonProperty("countListStation")
	private Optional<Long> countListStation;
}
