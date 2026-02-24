package com.evertecinc.b2c.enex.client.dao.extended;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.evertecinc.b2c.enex.client.model.dto2.MonitorOcHeaderDTO;
import com.evertecinc.b2c.enex.client.model.dto2.requests.MonitorOCRequestDTO;

public interface IOcHeaderRepo {
	
	public List<MonitorOcHeaderDTO> getListOCHeader(MonitorOCRequestDTO params, Pageable paging);
	public Long countGetListOCHeader(MonitorOCRequestDTO params, Pageable paging);

}
