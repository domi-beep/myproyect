package com.evertecinc.b2c.enex.client.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import com.evertecinc.b2c.enex.client.exceptions.CardException;
import com.evertecinc.b2c.enex.client.exceptions.ClientServiceException;
import com.evertecinc.b2c.enex.client.exceptions.NuevoMontoException;
import com.evertecinc.b2c.enex.client.exceptions.OTException;
import com.evertecinc.b2c.enex.client.exceptions.PriceLiterNotFoundException;
import com.evertecinc.b2c.enex.client.exceptions.VehicleException;
import com.evertecinc.b2c.enex.client.model.dto.CardDTO;
import com.evertecinc.b2c.enex.client.model.dto.CardHistoryDTO;
import com.evertecinc.b2c.enex.client.model.dto.ClientDTO;
import com.evertecinc.b2c.enex.client.model.dto.CriterioOtHeaderDTO;
import com.evertecinc.b2c.enex.client.model.dto.DepartmentDTO;
import com.evertecinc.b2c.enex.client.model.dto.MonitorCardDTO;
import com.evertecinc.b2c.enex.client.model.dto.OtHeaderDTO;
import com.evertecinc.b2c.enex.client.model.dto.OtItemDTO;
import com.evertecinc.b2c.enex.client.model.dto.ProductDepartmentDTO;
import com.evertecinc.b2c.enex.client.model.dto.RegisterClientDTO;
import com.evertecinc.b2c.enex.client.model.dto.StationClientsCriterioDTO;
import com.evertecinc.b2c.enex.client.model.dto.StationsDTO;
import com.evertecinc.b2c.enex.client.model.dto.StatusCardDTO;
import com.evertecinc.b2c.enex.client.model.dto.VehicleDTO;
import com.evertecinc.b2c.enex.client.model.dto2.orpak.RequestBalanceDepartmentDTO;
import com.evertecinc.b2c.enex.client.model.dto2.orpak.ResponseBalanceDepartmentDTO;
import com.evertecinc.b2c.enex.client.model.entities.Client;
import com.evertecinc.b2c.enex.process.jde.dto.CriterioBusquedaCardDTO;


public interface ClientService {

	public ClientDTO getClientById(Long idElement);

	public DepartmentDTO getListDepartmentById(Long idElement);

	public List<CardDTO> getCardsByDepartment(Long idElement, boolean b);

	public void updCardStatus(StatusCardDTO cardStatus);

	public void addCardHistory(CardHistoryDTO cardHistory);

	public List<MonitorCardDTO> getCardVehicleByDepartment(Long idElement);

	public List<ProductDepartmentDTO> doProductsDepartmentsByCriteria(ProductDepartmentDTO criterio);

	public void updDepartmentProductAmount(ProductDepartmentDTO criterio);

	public void addProductDepartmentRel(ProductDepartmentDTO criterio);

	public VehicleDTO getVehicleDeviceById(Long idElement, String data) throws VehicleException;

	public boolean existOTHeader(CriterioOtHeaderDTO criterio);

	public List<OtHeaderDTO> getListOtHeaderByCriterio(CriterioOtHeaderDTO criterio);

	public List<OtItemDTO> getDetalleByidOT(Long idOt, String data);

	public Long addItemOT(OtItemDTO nuevoDetalle) throws OTException;

	public Long addOT(OtHeaderDTO nuevaOT) throws OTException;

	public CardDTO getCardById(Long idElement) throws CardException;

	public VehicleDTO getVehicleByIdCard(Long idCard) throws VehicleException;

	public Long updCardBalance(Long idCard, String cardnum) throws CardException;

	public Boolean stationClientsExist(StationClientsCriterioDTO criterioSC) throws ClientServiceException;

	public List<StationsDTO> getStationClientsByCriterio(StationClientsCriterioDTO criterio) throws ClientServiceException;

	public boolean isEESSPrivadas(Long idClient);

	public List<String> getListStationConstraintByCard(Long idCard);

	public BigDecimal getLitroPrecio(String productCode, BigDecimal restrAmountMax) throws PriceLiterNotFoundException;

	public List<String> getListStationConstraintByDept(Long idDepartment);

	public void updDeparmentsProcessSendClient(DepartmentDTO departamentDto);

	public void updClientByIDProcessSendClient(RegisterClientDTO registerClientdto);

	public void updQuantityCard(Long idCard, String cardnum) throws CardException;

	public VehicleDTO getVehicleById(Long idElement) throws VehicleException;

	public Optional<Client> getClienteById(Long idElement) throws ClientServiceException;

	public Optional<List<ClientDTO>> findByFactDepartment(Integer factDeparment);

	public List<DepartmentDTO> findByIdClient(Long idDepartment);

	public List<MonitorCardDTO> getListCardsByCriterio(CriterioBusquedaCardDTO criterio);

	public void updDepartmentProductAmountSMov(ProductDepartmentDTO dto) throws NuevoMontoException;

	public List<ResponseBalanceDepartmentDTO> getDepartmentBalance(RequestBalanceDepartmentDTO requestBalanceDepartmentDTO);

	public Boolean clienteTieneEstacionesAsignadas(Long idClient);

	public List<CardDTO> getDeviceByVehicle(Long idVehicle, String posicion);

	public List<ClientDTO> getClientPendingOrpak();


}
