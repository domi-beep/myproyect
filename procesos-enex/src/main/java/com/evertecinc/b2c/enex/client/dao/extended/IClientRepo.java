package com.evertecinc.b2c.enex.client.dao.extended;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.evertecinc.b2c.enex.client.model.dto2.ClientMonitorDTO;
import com.evertecinc.b2c.enex.client.model.dto2.ReporteCargasUltMesesDTO;
import com.evertecinc.b2c.enex.client.model.dto2.ReporteComprasAndCargasDTO;
import com.evertecinc.b2c.enex.client.model.dto2.ReporteDetalleCargasDTO;
import com.evertecinc.b2c.enex.client.model.dto2.ReporteDetalleComprasCargasDTO;
import com.evertecinc.b2c.enex.client.model.dto2.ReporteListadoUsoEESSDTO;
import com.evertecinc.b2c.enex.client.model.dto2.ReporteUsoEESSDTO;
import com.evertecinc.b2c.enex.client.model.dto2.requests.ClientMonitorRequestDTO;
import com.evertecinc.b2c.enex.client.model.dto2.requests.ContraloriaClientsRequestDTO;
import com.evertecinc.b2c.enex.client.model.dto2.requests.ListCantidadClientesRequestDTO;

public interface IClientRepo {
	

//	public List<ClientMonitorDTO> getListClientByCriteria(ClientMonitorDTO criterio, Pageable pageable);
	public Long countListClientByCriteria(ClientMonitorDTO criterio);
	
	public List<ClientMonitorDTO> getContraloriaClientsByCriterio(ContraloriaClientsRequestDTO params, Pageable pageable);
	public Long countContraloriaClientsByCriterio(ContraloriaClientsRequestDTO params, Pageable pageable);
	public Long getCountListClientByCriteria(ClientMonitorRequestDTO params, Pageable paging);
	public List<ClientMonitorDTO> getListClientByCriteria(ClientMonitorRequestDTO params, Pageable paging);

	public List<ReporteUsoEESSDTO> getEESSUtilizadas(ListCantidadClientesRequestDTO params, Pageable paging);

	public List<ReporteComprasAndCargasDTO> getComprasAndCargas(ListCantidadClientesRequestDTO params, Pageable paging);

	public List<ReporteCargasUltMesesDTO> getCargasUltimosMeses(ListCantidadClientesRequestDTO params, Pageable paging);

	public List<ReporteListadoUsoEESSDTO> getListadoEESSUtilizadas(ListCantidadClientesRequestDTO params, Pageable paging);

	public List<ReporteDetalleComprasCargasDTO> getDetalleComprasAndCargas(ListCantidadClientesRequestDTO params,
			Pageable paging);

	public List<ReporteDetalleCargasDTO> getDetalleCargas(ListCantidadClientesRequestDTO params, Pageable paging);

}

