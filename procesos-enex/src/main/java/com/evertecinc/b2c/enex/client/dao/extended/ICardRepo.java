package com.evertecinc.b2c.enex.client.dao.extended;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.evertecinc.b2c.enex.client.model.dto.CardDTO;
import com.evertecinc.b2c.enex.client.model.dto.MonitorCardDTO;
import com.evertecinc.b2c.enex.client.model.dto2.CardMovementDTO;
import com.evertecinc.b2c.enex.client.model.dto2.CardMovementDepartmentAllDTO;
import com.evertecinc.b2c.enex.client.model.dto2.ReporteCargasClientesDTO;
import com.evertecinc.b2c.enex.client.model.dto2.StationConstraintDTO;
import com.evertecinc.b2c.enex.client.model.dto2.requests.GetCardsByDepartmentRequestDTO;
import com.evertecinc.b2c.enex.client.model.dto2.requests.ListCardMovementRequestDTO;
import com.evertecinc.b2c.enex.client.model.dto2.requests.ListCardRequestDTO;
import com.evertecinc.b2c.enex.client.model.dto2.requests.MovimientoTarjetaRequestDTO;
import com.evertecinc.b2c.enex.client.model.dto2.requests.ReporteCargasClientesRequestDTO;

public interface ICardRepo {

	public List<CardMovementDepartmentAllDTO> getListCardMovementDepartmentAll(MovimientoTarjetaRequestDTO params, Pageable pageable);

	/**
	 * Recupera una lista de tarjetas basada en los criterios proporcionados.
	 * 
	 * @param params los parámetros de búsqueda
	 * @param pageable los criterios de paginación
	 * @return una lista de objetos MonitorTarjetaDTO, que representan las tarjetas
	 *         que coinciden con los criterios
	 */
	public List<MonitorCardDTO> getListCardsByCriterio(ListCardRequestDTO params, Pageable pageable);

	/**
	 * Recupera una lista de tarjetas basada en los criterios proporcionados.
	 * 
	 * @param params los parámetros de búsqueda
	 * @param pageable los criterios de paginación
	 * @return una lista de objetos MonitorTarjetaDTO, que representan las tarjetas
	 *         que coinciden con los criterios
	 */
	public Long getCOUNTListCardsByCriterio(ListCardRequestDTO params);
	
	public List<StationConstraintDTO> getConstraintsByCard(Long idCard, String clientType, Long idClient);

	public Long countListCardMovementDepartmentAll(MovimientoTarjetaRequestDTO params);

	public List<CardMovementDTO> getListCardMovement(ListCardMovementRequestDTO params, Pageable pageable);

	public Long countListCardMovement(ListCardMovementRequestDTO params, Pageable pageable);

	public List<ReporteCargasClientesDTO> getListadoCargasClientes(ReporteCargasClientesRequestDTO params, Pageable pageable);

	public Long countListadoCargasClientes(ReporteCargasClientesRequestDTO params, Pageable pageable);
	
	public List<MonitorCardDTO> getCardVehicleByDepartment(Long idDepartment);

	public List<CardDTO> getCardsByDepartment(GetCardsByDepartmentRequestDTO params, Pageable pageable);

	public void updateCardByDepartment(Long idDepartment);
}
