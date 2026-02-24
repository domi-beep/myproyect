package com.evertecinc.b2c.enex.client.dao.extended;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.evertecinc.b2c.enex.client.model.dto2.MonitorAdBlueDTO;
import com.evertecinc.b2c.enex.client.model.dto2.MonitorImpresionDTO;
import com.evertecinc.b2c.enex.client.model.dto2.MonitorTipoSafDTO;
import com.evertecinc.b2c.enex.client.model.dto2.criterio.CriterioMonitorTipoSafDTO;
import com.evertecinc.b2c.enex.client.model.dto2.requests.ListMonitorImpresionJsonRequestDTO;
import com.evertecinc.b2c.enex.client.model.dto2.requests.MonitorSolicitudesRequestDTO;

public interface ISafRepo {
	
	public List<MonitorAdBlueDTO> getListServices(MonitorSolicitudesRequestDTO params, Pageable pages);

	public List<MonitorTipoSafDTO> getListTipoSaf(CriterioMonitorTipoSafDTO params, Pageable pageable);
	
	public Long getListTipoSafCount(CriterioMonitorTipoSafDTO params);

	public List<MonitorImpresionDTO> getListPendingPrintSaf(ListMonitorImpresionJsonRequestDTO params, Pageable paging);

	public Long getListPendingPrintSafCount(ListMonitorImpresionJsonRequestDTO params);

	public Long getListTipoSafCount(CriterioMonitorTipoSafDTO params, Pageable pageable);

}
