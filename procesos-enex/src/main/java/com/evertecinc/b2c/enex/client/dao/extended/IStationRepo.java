package com.evertecinc.b2c.enex.client.dao.extended;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.evertecinc.b2c.enex.client.model.dto2.StationAreaDTO;
import com.evertecinc.b2c.enex.client.model.dto2.requests.ListStationJsonRequestDTO;

public interface IStationRepo {
	
	public List<StationAreaDTO> getStationArea(ListStationJsonRequestDTO params, Pageable pageable);

	public Long getCOUNTStationArea(ListStationJsonRequestDTO params);

}
