package com.evertecinc.b2c.enex.client.dao.extended;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.evertecinc.b2c.enex.client.model.dto2.MonitorSafHistoryDTO;
import com.evertecinc.b2c.enex.client.model.dto2.criterio.CriterioMonitorTipoSafDTO;

public interface ISafHistoryRepo {

	public List<MonitorSafHistoryDTO> getListSafHistory(CriterioMonitorTipoSafDTO params, Pageable paging);

	public Long getListSafHistoryCount(CriterioMonitorTipoSafDTO params);

}