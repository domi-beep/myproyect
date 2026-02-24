package com.evertecinc.b2c.enex.client.model.dto2.restResponses;

import java.util.List;
import java.util.Optional;

import com.evertecinc.b2c.enex.client.model.dto.AreaDTO;
import com.evertecinc.b2c.enex.client.model.dto.RegionDTO;
import com.evertecinc.b2c.enex.client.model.dto.StationsDTO;
import com.evertecinc.b2c.enex.client.model.dto.ZoneDTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ListConstraintsByDepartmentJsonCtrlResultDTO extends GenericResultDTO{
	
	private Optional<List<StationsDTO>> listadoConstraints;
	
	private Optional<List<ZoneDTO>> listadoZonas;

	private Optional<List<RegionDTO>> listadoRegiones;
	
	private Optional<List<AreaDTO>> listadoAreas;
	
	private Optional<List<StationsDTO>> listadoStations;
}
