package com.evertecinc.b2c.enex.client.dao.extended;

import java.util.List;

import com.evertecinc.b2c.enex.client.model.dto.AreaDTO;
import com.evertecinc.b2c.enex.client.model.dto.RegionDTO;
import com.evertecinc.b2c.enex.client.model.dto.StationsDTO;
import com.evertecinc.b2c.enex.client.model.dto2.requests.GetAreaByStationsRequestExtendedDTO;
import com.evertecinc.b2c.enex.client.model.dto2.requests.GetRegionsByStationsRequestDTO;
import com.evertecinc.b2c.enex.client.model.dto2.requests.GetStationsByCriteriaForCardRequestDTO;

public interface IGeoRepo {

	public List<AreaDTO> getAreaByStations(GetAreaByStationsRequestExtendedDTO params);

	public List<StationsDTO> getStationsByCriteria(GetStationsByCriteriaForCardRequestDTO params);

	List<RegionDTO> getRegionsByStations(GetRegionsByStationsRequestDTO params);
}
