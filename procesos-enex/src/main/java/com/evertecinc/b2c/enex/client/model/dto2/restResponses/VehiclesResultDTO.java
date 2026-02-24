package com.evertecinc.b2c.enex.client.model.dto2.restResponses;

import java.util.List;
import java.util.Optional;

import com.evertecinc.b2c.enex.client.model.dto.VehicleDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class VehiclesResultDTO extends GenericResultDTO {
	
	@JsonProperty("vehicles")
	private Optional<List<VehicleDTO>> vehicles;

	@JsonProperty("countVehicles")
	private Optional<Long> countVehicles;

}
