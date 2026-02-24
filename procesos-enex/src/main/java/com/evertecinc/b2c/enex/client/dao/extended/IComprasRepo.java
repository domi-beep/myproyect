package com.evertecinc.b2c.enex.client.dao.extended;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.evertecinc.b2c.enex.client.model.dto2.MonitorComprasDTO;
import com.evertecinc.b2c.enex.client.model.dto2.requests.ComprasDetallePagoRequestDTO;

public interface IComprasRepo {
	
	public List<MonitorComprasDTO> getMonitorComprasByCriterio(ComprasDetallePagoRequestDTO params, Pageable paging);
	public Long countMonitorComprasByCriterio(ComprasDetallePagoRequestDTO params, Pageable paging);
	
	
	
}
