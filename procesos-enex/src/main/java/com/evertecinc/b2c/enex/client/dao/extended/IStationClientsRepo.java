package com.evertecinc.b2c.enex.client.dao.extended;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.evertecinc.b2c.enex.client.model.dto.StationsDTO;
import com.evertecinc.b2c.enex.client.model.dto2.requests.StationClientsRequestDTO;
import com.evertecinc.b2c.enex.client.model.dto2.requests.StationsRequestDTO;

public interface IStationClientsRepo {
	
	public List<StationsDTO> stationsUnique();
	
	public List<StationsDTO> getStationClientsTerritoryByCriterio(StationsRequestDTO params);


	public List<StationsDTO> getStationClientsByCriterio(StationClientsRequestDTO params);

	public List<StationsDTO> getStationClientsByCriterioPaged(StationClientsRequestDTO params, Pageable pageable);

	public Long getCountStationClientsByCriterioPaged(StationClientsRequestDTO params);

}
