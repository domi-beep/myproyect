package com.evertecinc.b2c.enex.client.model.dto2.restResponses;

import java.util.List;
import java.util.Optional;

import com.evertecinc.b2c.enex.client.model.dto2.VehiclesJsonDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ListVehiclesJsonResultDTO extends GenericResultDTO {
	
	@JsonProperty("listVehiclesJson")
	private Optional<List<VehiclesJsonDTO>> listVehiclesJson;

	@JsonProperty("countListVehiclesJson")
	private Optional<Long> countListVehiclesJson;

}
