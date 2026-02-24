package com.evertecinc.b2c.enex.client.model.dto2.restResponses;

import java.util.List;
import java.util.Optional;

import lombok.Data;

@Data
public class LiberarCardResultDTO extends GenericResultDTO {
	
	public Optional<List<Long>> idVehiclesNoReprint;
	public Optional<List<Long>> vehiclesNotFound;

}
