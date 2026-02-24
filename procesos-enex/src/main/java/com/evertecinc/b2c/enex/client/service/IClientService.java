package com.evertecinc.b2c.enex.client.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.springframework.data.domain.Pageable;

import com.evertecinc.b2c.enex.client.exceptions.CardException;
import com.evertecinc.b2c.enex.client.exceptions.CardnumberMasterException;
import com.evertecinc.b2c.enex.client.exceptions.ClientException;
import com.evertecinc.b2c.enex.client.exceptions.ClientNotFoundException;
import com.evertecinc.b2c.enex.client.exceptions.ClientServiceException;
import com.evertecinc.b2c.enex.client.exceptions.PriceLiterNotFoundException;
import com.evertecinc.b2c.enex.client.exceptions.RegistroNoEncontradoException;
import com.evertecinc.b2c.enex.client.model.dto.AreaDTO;
import com.evertecinc.b2c.enex.client.model.dto.CardDTO;
import com.evertecinc.b2c.enex.client.model.dto.ClientDTO;
import com.evertecinc.b2c.enex.client.model.dto.RegisterClientDTO;
import com.evertecinc.b2c.enex.client.model.dto2.CardExtDTO;
import com.evertecinc.b2c.enex.client.model.dto2.CardExtReqDTO;
import com.evertecinc.b2c.enex.client.model.dto2.CardNumberDTO;
import com.evertecinc.b2c.enex.client.model.dto2.CardSafDTO;
import com.evertecinc.b2c.enex.client.model.dto2.ClientMonitorDTO;
import com.evertecinc.b2c.enex.client.model.dto2.CriterioComRegionDTO;
import com.evertecinc.b2c.enex.client.model.dto2.MonitorColaSafDTO;
import com.evertecinc.b2c.enex.client.model.dto2.MonitorComprasDTO;
import com.evertecinc.b2c.enex.client.model.dto2.MonitorMaestroTarjetasDTO;
import com.evertecinc.b2c.enex.client.model.dto2.MonitorVehiclesDTO;
import com.evertecinc.b2c.enex.client.model.dto2.PreciosLitrosDTO;
import com.evertecinc.b2c.enex.client.model.dto2.RegionAreaDTO;
import com.evertecinc.b2c.enex.client.model.dto2.VehiclesConductoresRelCriterioDTO;
import com.evertecinc.b2c.enex.client.model.dto2.VehiclesConductoresRelDTO;
import com.evertecinc.b2c.enex.client.model.dto2.criterio.CriterioMaestroTarjetasDTO;
import com.evertecinc.b2c.enex.client.model.dto2.criterio.CriterioMonitorTipoSafDTO;
import com.evertecinc.b2c.enex.client.model.dto2.paging.PagingInitDTO;
import com.evertecinc.b2c.enex.client.model.dto2.paging.PagingSortDTO;
import com.evertecinc.b2c.enex.client.model.dto2.requests.ClientMonitorRequestDTO;
import com.evertecinc.b2c.enex.client.model.dto2.requests.ContraloriaClientsRequestDTO;
import com.evertecinc.b2c.enex.client.model.dto2.requests.CriterioClienteVentaRequestDTO;
import com.evertecinc.b2c.enex.client.model.dto2.requests.ListCantidadClientesRequestDTO;
import com.evertecinc.b2c.enex.client.model.dto2.requests.ListConfiguracionesJsonRequestDTO;
import com.evertecinc.b2c.enex.client.model.dto2.requests.ListStationJsonRequestDTO;
import com.evertecinc.b2c.enex.client.model.dto2.requests.MisDatosInfoRequestDTO;
import com.evertecinc.b2c.enex.client.model.dto2.requests.MonitorComprasRequestDTO;
import com.evertecinc.b2c.enex.client.model.dto2.requests.MonitorVehiculosRequestDTO;
import com.evertecinc.b2c.enex.client.model.dto2.requests.ResumenSaldoCreditoRequestDTO;
import com.evertecinc.b2c.enex.client.model.dto2.requests.SolicitarServicioRequestDTO;
import com.evertecinc.b2c.enex.client.model.dto2.requests.UpdDatosClienteCtrlRequestDTO;
import com.evertecinc.b2c.enex.client.model.dto2.requests.viewEditClientRequestDTO;
import com.evertecinc.b2c.enex.client.model.dto2.restResponses.ClientEditResultDTO;
import com.evertecinc.b2c.enex.client.model.dto2.restResponses.ClientMonitorResultDTO;
import com.evertecinc.b2c.enex.client.model.dto2.restResponses.ContraloriaClientByCriterioResultDTO;
import com.evertecinc.b2c.enex.client.model.dto2.restResponses.ListCantidadClientesResultDTO;
import com.evertecinc.b2c.enex.client.model.dto2.restResponses.ListConfiguracionesJsonResultDTO;
import com.evertecinc.b2c.enex.client.model.dto2.restResponses.ListDetalleComprasCargasResultDTO;
import com.evertecinc.b2c.enex.client.model.dto2.restResponses.ListReporteCargasDCCPResultDTO;
import com.evertecinc.b2c.enex.client.model.dto2.restResponses.ListReporteCargasUltMesesResultDTO;
import com.evertecinc.b2c.enex.client.model.dto2.restResponses.ListReporteComprasAndCargasResultDTO;
import com.evertecinc.b2c.enex.client.model.dto2.restResponses.ListReporteComprasResultDTO;
import com.evertecinc.b2c.enex.client.model.dto2.restResponses.ListReporteDetalleCargasResultDTO;
import com.evertecinc.b2c.enex.client.model.dto2.restResponses.ListReporteListadoEESSResultDTO;
import com.evertecinc.b2c.enex.client.model.dto2.restResponses.ListReporteUserEESSResultDTO;
import com.evertecinc.b2c.enex.client.model.dto2.restResponses.ListStationJsonResultDTO;
import com.evertecinc.b2c.enex.client.model.dto2.restResponses.ListUltimosClientesResultDTO;
import com.evertecinc.b2c.enex.client.model.dto2.restResponses.ListVentasReporteCompraMensualResultDTO;
import com.evertecinc.b2c.enex.client.model.dto2.restResponses.MisDatosInfoResultDTO;
import com.evertecinc.b2c.enex.client.model.dto2.restResponses.MonitorComprasResultDTO;
import com.evertecinc.b2c.enex.client.model.dto2.restResponses.PlateClientsResultDTO;
import com.evertecinc.b2c.enex.client.model.dto2.restResponses.ResumenSaldoCreditoResultDTO;
import com.evertecinc.b2c.enex.client.model.dto2.restResponses.SolicitarServicioResultDTO;
import com.evertecinc.b2c.enex.client.model.dto2.restResponses.UpdDatosClienteCtrlResultDTO;


public interface IClientService {

	/**
	 * USO INTERNO
	 * @param rutCliente
	 * @param tipoCliente
	 * @return
	 * @throws ClientNotFoundException
	 */
	public Optional<RegisterClientDTO> getClientByUpi(String rutCliente, String tipoCliente) throws ClientNotFoundException;

	public Optional<Object> getListComprasMonitor(MonitorComprasDTO criterio, Pageable paginacion) throws RegistroNoEncontradoException;
	public Optional<Long> countListComprasMonitor(MonitorComprasDTO criterio);

	public Optional<ResumenSaldoCreditoResultDTO> getResumenSaldoCredito(ResumenSaldoCreditoRequestDTO params) throws ClientException, IllegalArgumentException;
	
	public Optional<Long> countListPatentesClientes(MonitorVehiclesDTO criterio);
	public Optional<Long> countListClientByCriteria(ClientMonitorDTO criterio);
	
	public Optional<ContraloriaClientByCriterioResultDTO> getContraloriaClientsByCriterio(ContraloriaClientsRequestDTO params,PagingSortDTO sort, PagingInitDTO paging) throws RegistroNoEncontradoException;
	
	
	
	/* Metodo Base */
	public Optional<ClientMonitorResultDTO> getListClientByCriteria(ClientMonitorRequestDTO params, PagingSortDTO sort,
			PagingInitDTO paging) throws RegistroNoEncontradoException;
	
	public Optional<PlateClientsResultDTO> getListPatentesClientes(MonitorVehiculosRequestDTO params, PagingSortDTO sort,
			PagingInitDTO paging) throws RegistroNoEncontradoException;

	public Optional<MonitorComprasResultDTO> getListComprasMonitor(MonitorComprasRequestDTO criterio, PagingSortDTO sort,
			PagingInitDTO paging) throws RegistroNoEncontradoException;

	public ClientDTO getClientById(Long idClient) throws ClientException;

	public BigDecimal getLitroPrecio(String productCode, BigDecimal amount) throws PriceLiterNotFoundException;

	public List<PreciosLitrosDTO> getPriceLiters(String productCode);

	public List<String> getListStationConstraintByCard(Long idCard);

	public Boolean isEESSPrivadas(Long idClient);
	
	public ClientDTO getClientByUpiandType(String upi, String clientType) throws ClientNotFoundException;

	public ClientEditResultDTO viewEditClient(viewEditClientRequestDTO params);

	public Boolean getControlPassById(Long idClient);
	
	public Optional<UpdDatosClienteCtrlResultDTO> UpdDatosClienteById(UpdDatosClienteCtrlRequestDTO params) throws ClientException;
	
//	public Optional<DoUpdateClientJsonResultDTO> DoUpdateClientJson(DoUpdateClientJsonRequestDTO params);
	
	public Optional<MisDatosInfoResultDTO> MisDatosInfo(MisDatosInfoRequestDTO params);

	public Optional<SolicitarServicioResultDTO> SolicitarServicio(SolicitarServicioRequestDTO params) throws ClientException;

	public Long addAdBlueRequest(Long idClient, String data) throws Exception;
	
	public Boolean clienteTieneEstacionesAsignadas(Long idClient) throws ClientServiceException;

	public Optional<ListConfiguracionesJsonResultDTO> ListConfiguracionesJson(ListConfiguracionesJsonRequestDTO params,
			PagingSortDTO sort, PagingInitDTO paging, Locale locale) throws ClientException;
	
	public Optional<ListCantidadClientesResultDTO> getCantidadClientes(ListCantidadClientesRequestDTO params,
			PagingSortDTO sort, PagingInitDTO paging) throws RegistroNoEncontradoException;

	public Optional<ListUltimosClientesResultDTO> getUltimosClientes(ListCantidadClientesRequestDTO params, PagingSortDTO sort,
			PagingInitDTO paging) throws RegistroNoEncontradoException;

	public Optional<ListReporteComprasResultDTO> getVentasPorMes(ListCantidadClientesRequestDTO params, PagingSortDTO sort,
			PagingInitDTO paging) throws RegistroNoEncontradoException;

	public Optional<ListReporteUserEESSResultDTO> getEESSUtilizadas(ListCantidadClientesRequestDTO params, PagingSortDTO sort,
			PagingInitDTO paging) throws RegistroNoEncontradoException;

	public Optional<ListReporteComprasAndCargasResultDTO> getComprasAndCargas(ListCantidadClientesRequestDTO params,
			PagingSortDTO sort, PagingInitDTO paging) throws RegistroNoEncontradoException;

	public Optional<ListReporteCargasUltMesesResultDTO> getCargasUltimosMeses(ListCantidadClientesRequestDTO params,
			PagingSortDTO sort, PagingInitDTO paging) throws RegistroNoEncontradoException;

	public Optional<ListReporteCargasDCCPResultDTO> getContraloriaClients(CriterioClienteVentaRequestDTO params, PagingSortDTO sort,
			PagingInitDTO paging) throws RegistroNoEncontradoException;

	public Optional<ListVentasReporteCompraMensualResultDTO> getListadoVentas(ListCantidadClientesRequestDTO params,
			PagingSortDTO sort, PagingInitDTO paging) throws RegistroNoEncontradoException;

	public Optional<ListReporteListadoEESSResultDTO> getListadoEESSUtilizadas(ListCantidadClientesRequestDTO params,
			PagingSortDTO sort, PagingInitDTO paging) throws RegistroNoEncontradoException;

	public Optional<ListDetalleComprasCargasResultDTO> getDetalleComprasAndCargas(ListCantidadClientesRequestDTO params,
			PagingSortDTO sort, PagingInitDTO paging) throws RegistroNoEncontradoException;

	public Optional<ListReporteDetalleCargasResultDTO> getDetalleCargas(ListCantidadClientesRequestDTO params, PagingSortDTO sort,
			PagingInitDTO paging) throws RegistroNoEncontradoException;



	void addCardVehicle(CardDTO cardDTO, Long idVehicle) throws CardException;

	CardSafDTO updCardInformationSAF(String idVehicle, String cardPlate, String idMifare) throws CardException;

	Boolean validatePreamble(String idVehicle, String cardnumber, String plate, String idMifare) throws Exception;

	public void updReqCardExt(CardExtReqDTO cardExt);

	public CardExtDTO getInformationCardExt(String idElement);

	public Boolean validacionPatenteMifare(String patente, String mifare);

	public CardSafDTO getCardInformationSAF(String string);

	public AreaDTO getAreaByID(long longValue);
	
	public RegionAreaDTO getRegionByCriterioComRegionDTO(CriterioComRegionDTO criterio);
	
	public List<MonitorMaestroTarjetasDTO> getListCardMaster(
			CriterioMaestroTarjetasDTO criterio);

	

	public List<MonitorColaSafDTO> getMonitorTipoSaf(CriterioMonitorTipoSafDTO criterio) throws ClientException;

	public Long getMonitorTipoSafCount(CriterioMonitorTipoSafDTO criterio) throws ClientException;

	public Optional<ListStationJsonResultDTO> ListStationJsonREST(ListStationJsonRequestDTO params, PagingSortDTO sort,
			PagingInitDTO paging, Locale locale) throws ClientException;

	List<VehiclesConductoresRelDTO> getConductorVehicleByCriterio(
			VehiclesConductoresRelCriterioDTO vehiculoConductorCriterio) throws Exception;



	void updCardnumberMaster(CardNumberDTO cardnumberDto) throws CardnumberMasterException;

}
