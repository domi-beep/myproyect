package com.evertecinc.b2c.enex.client.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.InvalidParameterException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.evertecinc.b2c.constants.Constantes;
import com.evertecinc.b2c.enex.audits.model.entities.Audits;
import com.evertecinc.b2c.enex.client.dao.extended.ICardRepo;
import com.evertecinc.b2c.enex.client.dao.extended.IDepartmentsRepo;
import com.evertecinc.b2c.enex.client.dao.extended.IStationClientsRepo;
import com.evertecinc.b2c.enex.client.dao.extended.IStationDepartmentsRepo;
import com.evertecinc.b2c.enex.client.dao.extended.impl.StationDepartmentsRepoImpl;
import com.evertecinc.b2c.enex.client.dao.repositories.AuditsRepo;
import com.evertecinc.b2c.enex.client.dao.repositories.CardsHistoryRepo;
import com.evertecinc.b2c.enex.client.dao.repositories.CardsRepo;
import com.evertecinc.b2c.enex.client.dao.repositories.ClientRepo;
import com.evertecinc.b2c.enex.client.dao.repositories.ClientsUsersRelRepo;
import com.evertecinc.b2c.enex.client.dao.repositories.DepartmentHistoryRepo;
import com.evertecinc.b2c.enex.client.dao.repositories.DepartmentMovementRepo;
import com.evertecinc.b2c.enex.client.dao.repositories.DepartmentRepo;
import com.evertecinc.b2c.enex.client.dao.repositories.PriceLitersRepo;
import com.evertecinc.b2c.enex.client.dao.repositories.ProductsDepartmentsRelRepo;
import com.evertecinc.b2c.enex.client.dao.repositories.ProductsRepo;
import com.evertecinc.b2c.enex.client.dao.repositories.SafQueueRepo;
import com.evertecinc.b2c.enex.client.dao.repositories.StationCardConstraintRepo;
import com.evertecinc.b2c.enex.client.dao.repositories.StationDepartmentConstraintsRepo;
import com.evertecinc.b2c.enex.client.dao.repositories.UsersRepo;
import com.evertecinc.b2c.enex.client.dao.repositories.VehicleCardRepo;
import com.evertecinc.b2c.enex.client.dao.repositories.VehiclesRepo;
import com.evertecinc.b2c.enex.client.dao.repositories.ZoneRepo;
import com.evertecinc.b2c.enex.client.exceptions.CardException;
import com.evertecinc.b2c.enex.client.exceptions.ClientException;
import com.evertecinc.b2c.enex.client.exceptions.ClientNotFoundException;
import com.evertecinc.b2c.enex.client.exceptions.ClientServiceException;
import com.evertecinc.b2c.enex.client.exceptions.CreditCardException;
import com.evertecinc.b2c.enex.client.exceptions.DepartmentException;
import com.evertecinc.b2c.enex.client.exceptions.DepartmentoUnicoException;
import com.evertecinc.b2c.enex.client.exceptions.DeptoTieneSaldoException;
import com.evertecinc.b2c.enex.client.exceptions.NuevoMontoException;
import com.evertecinc.b2c.enex.client.exceptions.PriceLiterNotFoundException;
import com.evertecinc.b2c.enex.client.exceptions.ProductDepartmentException;
import com.evertecinc.b2c.enex.client.exceptions.VehicleException;
import com.evertecinc.b2c.enex.client.model.dto.AreaDTO;
import com.evertecinc.b2c.enex.client.model.dto.CardDTO;
import com.evertecinc.b2c.enex.client.model.dto.ClientDTO;
import com.evertecinc.b2c.enex.client.model.dto.DepartmentDTO;
import com.evertecinc.b2c.enex.client.model.dto.ProductDepartmentDTO;
import com.evertecinc.b2c.enex.client.model.dto.RegionDTO;
import com.evertecinc.b2c.enex.client.model.dto.RegisterClientDTO;
import com.evertecinc.b2c.enex.client.model.dto.StationsDTO;
import com.evertecinc.b2c.enex.client.model.dto.ZoneDTO;
import com.evertecinc.b2c.enex.client.model.dto2.DepartmentHistoryDTO;
import com.evertecinc.b2c.enex.client.model.dto2.DepartmentMovementDTO;
import com.evertecinc.b2c.enex.client.model.dto2.DepartmentProductFinderDTO;
import com.evertecinc.b2c.enex.client.model.dto2.ReasignarMontoDeptDTO;
import com.evertecinc.b2c.enex.client.model.dto2.SafReportDTO;
import com.evertecinc.b2c.enex.client.model.dto2.orpak.RequestBalanceCardDTO;
import com.evertecinc.b2c.enex.client.model.dto2.orpak.RequestBalanceDepartmentDTO;
import com.evertecinc.b2c.enex.client.model.dto2.orpak.ResponseBalanceCardDTO;
import com.evertecinc.b2c.enex.client.model.dto2.orpakInterface.ResponseBalanceDepartmentOrpakDTO;
import com.evertecinc.b2c.enex.client.model.dto2.paging.PagingDataDTO;
import com.evertecinc.b2c.enex.client.model.dto2.paging.PagingInitDTO;
import com.evertecinc.b2c.enex.client.model.dto2.paging.PagingSortDTO;
import com.evertecinc.b2c.enex.client.model.dto2.requests.AddDepartamentoJsonCtrlRequestDTO;
import com.evertecinc.b2c.enex.client.model.dto2.requests.AreaByStationsDepartmentRequestDTO;
import com.evertecinc.b2c.enex.client.model.dto2.requests.DeleteDeptoClientJsonRequestDTO;
import com.evertecinc.b2c.enex.client.model.dto2.requests.DepartmentByClientRequestDTO;
import com.evertecinc.b2c.enex.client.model.dto2.requests.DepartmentRequestDTO;
import com.evertecinc.b2c.enex.client.model.dto2.requests.DoSetConstraintsByDepartmentRequestDTO;
import com.evertecinc.b2c.enex.client.model.dto2.requests.GetCardsByDepartmentRequestDTO;
import com.evertecinc.b2c.enex.client.model.dto2.requests.GetConstraintsByDepartmentRequestDTO;
import com.evertecinc.b2c.enex.client.model.dto2.requests.ListConstraintsByDepartmentJsonCtrlRequestDTO;
import com.evertecinc.b2c.enex.client.model.dto2.requests.ListDepartmentsByClient2JsonCtrlRequestDTO;
import com.evertecinc.b2c.enex.client.model.dto2.requests.ListDeptHistoryJsonCtrlRequestDTO;
import com.evertecinc.b2c.enex.client.model.dto2.requests.ListMovimientosDeptoCtrlRequestDTO;
import com.evertecinc.b2c.enex.client.model.dto2.requests.ProductsDepartmentsByCriteriaRequestDTO;
import com.evertecinc.b2c.enex.client.model.dto2.requests.RegionsByStationDepartmentRequestDTO;
import com.evertecinc.b2c.enex.client.model.dto2.requests.StationClientsRequestDTO;
import com.evertecinc.b2c.enex.client.model.dto2.requests.StationsByCriteriaForDepartmentRequestDTO;
import com.evertecinc.b2c.enex.client.model.dto2.requests.StationsRequestDTO;
import com.evertecinc.b2c.enex.client.model.dto2.requests.UpdAlertDepartmentJsonCtrlRequestDTO;
import com.evertecinc.b2c.enex.client.model.dto2.requests.UpdConfigDepartmentJsonRequestDTO;
import com.evertecinc.b2c.enex.client.model.dto2.requests.UpdDepartmentCtrltRequestDTO;
import com.evertecinc.b2c.enex.client.model.dto2.requests.VehiclesRequestDTO;
import com.evertecinc.b2c.enex.client.model.dto2.requests.ViewEditDepartamentoCtrlRequestDTO;
import com.evertecinc.b2c.enex.client.model.dto2.restResponses.AddDepartamentoJsonCtrlResultDTO;
import com.evertecinc.b2c.enex.client.model.dto2.restResponses.DeleteDeptoClientJsonResultDTO;
import com.evertecinc.b2c.enex.client.model.dto2.restResponses.DepartmentsResultDTO;
import com.evertecinc.b2c.enex.client.model.dto2.restResponses.DoSetConstraintsByDepartmentResultDTO;
import com.evertecinc.b2c.enex.client.model.dto2.restResponses.GetConstraintsByDepartmentResultDTO;
import com.evertecinc.b2c.enex.client.model.dto2.restResponses.ListConstraintsByDepartmentJsonCtrlResultDTO;
import com.evertecinc.b2c.enex.client.model.dto2.restResponses.ListDepartmentsByClient2JsonCtrlResultDTO;
import com.evertecinc.b2c.enex.client.model.dto2.restResponses.ListDeptHistoryJsonCtrlResultDTO;
import com.evertecinc.b2c.enex.client.model.dto2.restResponses.ListMovimientosDeptoCtrlResultDTO;
import com.evertecinc.b2c.enex.client.model.dto2.restResponses.UpdAlertDepartmentJsonCtrlResultDTO;
import com.evertecinc.b2c.enex.client.model.dto2.restResponses.UpdConfigDepartmentJsonResultDTO;
import com.evertecinc.b2c.enex.client.model.dto2.restResponses.UpdDepartmentCtrlResultDTO;
import com.evertecinc.b2c.enex.client.model.dto2.restResponses.ViewEditDepartamentoCtrlResultDTO;
import com.evertecinc.b2c.enex.client.model.entities.Card;
import com.evertecinc.b2c.enex.client.model.entities.CardsHistory;
import com.evertecinc.b2c.enex.client.model.entities.Clients;
import com.evertecinc.b2c.enex.client.model.entities.ClientsUsersRel;
import com.evertecinc.b2c.enex.client.model.entities.DepartmentHistory;
import com.evertecinc.b2c.enex.client.model.entities.DepartmentMovement;
import com.evertecinc.b2c.enex.client.model.entities.Departments;
import com.evertecinc.b2c.enex.client.model.entities.PriceLiters;
import com.evertecinc.b2c.enex.client.model.entities.Products;
import com.evertecinc.b2c.enex.client.model.entities.ProductsDepartmentsRel;
import com.evertecinc.b2c.enex.client.model.entities.StationDepartmentConstraints;
import com.evertecinc.b2c.enex.client.model.entities.StationDepartmentConstraintsId;
import com.evertecinc.b2c.enex.client.model.entities.Users;
import com.evertecinc.b2c.enex.client.model.entities.VehicleCard;
import com.evertecinc.b2c.enex.client.model.entities.Vehicles;
import com.evertecinc.b2c.enex.client.model.entities.Zone;
import com.evertecinc.b2c.enex.client.model.mapper.ClientMapper;
import com.evertecinc.b2c.enex.client.model.mapper.DepartmentsMapper;
import com.evertecinc.b2c.enex.client.model.mapper.UserMapper;
import com.evertecinc.b2c.enex.integracion.constants.ClientConstants;
import com.evertecinc.b2c.enex.integracion.constants.OrpakWSConstants;
import com.evertecinc.b2c.enex.integracion.exception.OrpakWSExceptions;
import com.evertecinc.b2c.enex.saf.constants.SafConstants;
import com.evertecinc.b2c.enex.saf.exceptions.SafException;
import com.evertecinc.b2c.enex.saf.model.entities.SafQueue;
import com.evertecinc.b2c.enex.utils.functions.Functions;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class DepartmentServiceImpl implements IDepartmentService {

	private @Autowired IDepartmentsRepo departmentsRepoExtended;
	private @Autowired DepartmentRepo departmentRepoJPA;
	private @Autowired VehiclesRepo vehiclesRepoJPA;
	private @Autowired ClientService clientService;
	private @Autowired ClientRepo clientRepoJPA;
	private @Autowired AuditsRepo auditsRepoJPA;
	private @Autowired DepartmentsMapper departmentsMapper;
	private @Autowired IStationDepartmentsRepo stationDepartmentsRepo;
	private @Autowired ClientMapper clientMapper;
	private @Autowired SafQueueRepo safQueueRepoJPA;
	private @Autowired UsersRepo usersRepoJPA;
	private @Autowired DepartmentHistoryRepo departmentHistoryRepoJPA;
	private @Autowired ProductsDepartmentsRelRepo productsDepartmentsRelRepoJPA;
	private @Autowired UserMapper userMapper;
//	private @Autowired IOrpakIntegracionService orpakIntegracionService;
	
	private final ClientsUsersRelRepo clientsUsersRelRepoJPA;
	private final StationDepartmentsRepoImpl stationDepartmentsRepoExtend;
	private final StationDepartmentConstraintsRepo stationDepartmentConstraintsRepoJPA;
	private final IStationClientsRepo stationClientsRepoExtend;
	private final ICardRepo cardRepoExtended;
//	private final IStationDepartmentsService stationDepartmentsService;
	private final CardsRepo cardRepoJPA;
	private final StationCardConstraintRepo stationCardConstraintRepoJPA;
	private final CardsHistoryRepo cardsHistoryRepoJPA;
//	private final IVehicleService vehicleService;
	private final VehicleCardRepo vehicleCardRepoJPA;
	private final ProductsRepo productsRepoJPA;
	private final DepartmentMovementRepo departmentMovementRepoJPA;
	private final PriceLitersRepo priceLitersRepoJPA;
	private final ZoneRepo zoneRepoJPA;
	private final StationDepartmentConstraintsRepo stationDeparmentConstraintRepoJPA;
	
//	@Autowired
//	@Lazy
//	private ITaskService taskService;

//	@Autowired
//	@Lazy
//	private ICardService cardService;
	
	@Override
	public Optional<DepartmentsResultDTO> getDepartmentsByClient(DepartmentByClientRequestDTO params, PagingSortDTO sort,
			PagingInitDTO paging, Locale locale) throws Exception {
		log.info("************************* GETDEPARTMENTSBYCLIENT *************************");

		if (params == null) {
			log.error("El criterio no puede ser nulo o vacio.");
			throw new IllegalArgumentException("El criterio no puede ser nulo o vacio.");
		}

		DepartmentsResultDTO retorno = new DepartmentsResultDTO();
		List<DepartmentDTO> departmentDTOList = null;
		Long totalListDepartmentsSinPaginacion = 0L;

		try {
			Pageable pages = Functions.getPageable(sort, paging);

			departmentDTOList = this.departmentsRepoExtended.getDepartmentsByClient(params, pages);
			totalListDepartmentsSinPaginacion = this.departmentsRepoExtended.getCOUNTDepartmentsByClient(params);

			if (departmentDTOList != null && !departmentDTOList.isEmpty())
				retorno.setListDepartments(Optional.of(departmentDTOList));

			retorno.setCountListDepartments(Optional.of(totalListDepartmentsSinPaginacion));

			retorno.setPagingData(Optional.of(PagingDataDTO.fromPagingInit(paging)));
			retorno.setSort(sort != null ? Optional.of(sort) : null);

			retorno.setPagingData(Optional.of(PagingDataDTO.fromPagingInit(paging)));
			retorno.setSort(sort != null ? Optional.of(sort) : null);

		} catch (Exception e) {
			log.error("Ocurrio un error al obtener el listado de departamentos por cliente. " + e.getMessage());
			throw new Exception(
					"Ocurrio un error al obtener el listado de departamentos por cliente. " + e.getMessage());
		}

		log.info("************************* GETDEPARTMENTSBYCLIENT FIN *************************");

		return Optional.of(retorno);
	}

	@Override
	public Optional<DepartmentsResultDTO> getDepartmentByIdUser(DepartmentByClientRequestDTO params, PagingSortDTO sort,
			PagingInitDTO paging, Locale locale) throws Exception {
		log.info("************************* GETDEPARTMENTBYIDUSER *************************");

		if (params == null) {
			log.error("El criterio no puede ser nulo o vacio.");
			throw new IllegalArgumentException("El criterio no puede ser nulo o vacio.");
		}

//		if(params.getIdCliente() == null || params.getIdUser() == null) {
//			log.error("El idCliente y el idUser son necesarios y no pueden ser nulos o vacios.");
//			throw new IllegalArgumentException("El idCliente y el idUser son necesarios y no pueden ser nulos o vacios.");
//		}

		DepartmentsResultDTO retorno = new DepartmentsResultDTO();
		List<DepartmentDTO> departmentDTOList = null;
		Long totalListDepartmentsSinPaginacion = 0L;

		try {
			Pageable pages = Functions.getPageable(sort, paging);

			departmentDTOList = this.departmentsRepoExtended.getDepartmentsByIdUser(params, pages);
			totalListDepartmentsSinPaginacion = this.departmentsRepoExtended.getCOUNTDepartmentsByIdUser(params);

			if (departmentDTOList != null && !departmentDTOList.isEmpty())
				retorno.setListDepartments(Optional.of(departmentDTOList));

			retorno.setCountListDepartments(Optional.of(totalListDepartmentsSinPaginacion));

			retorno.setPagingData(Optional.of(PagingDataDTO.fromPagingInit(paging)));
			retorno.setSort(sort != null ? Optional.of(sort) : null);

		} catch (Exception e) {
			log.error("Ocurrio un error al obtener el listado de departamentos por cliente. " + e.getMessage());
			throw new Exception(
					"Ocurrio un error al obtener el listado de departamentos por cliente. " + e.getMessage());
		}

		log.info("************************* GETDEPARTMENTBYIDUSER FIN *************************");

		return Optional.of(retorno);
	}

	@Override
	public Optional<DepartmentsResultDTO> getDeptoHijos(DepartmentRequestDTO params) throws Exception {
		log.info("************************* GETDEPARTMENTBYIDUSER *************************");

		if (params == null) {
			log.error("El criterio no puede ser nulo o vacio.");
			throw new IllegalArgumentException("El criterio no puede ser nulo o vacio.");
		}

		DepartmentsResultDTO retorno = new DepartmentsResultDTO();
		List<DepartmentDTO> listDepartments = null;
		Long totalListDepartmentsSinPaginacion = 0L;

		try {
			listDepartments = this.departmentsRepoExtended.getDeptoHijosRolUser(params);
			totalListDepartmentsSinPaginacion = this.departmentsRepoExtended.getCOUNTDeptoHijosRolUser(params);

			retorno.setCountListDepartments(Optional.of(totalListDepartmentsSinPaginacion));

			List<DepartmentDTO> deptoHijos = new ArrayList<DepartmentDTO>();

			for (DepartmentDTO depto : listDepartments) {

				DepartmentDTO elemento = new DepartmentDTO();
				elemento.setIdDepartment(depto.getIdDepartment());
				elemento.setName(depto.getName());
				elemento.setIdClient(depto.getIdClient());
				elemento.setDepartmentStatus(depto.getDepartmentStatus());
				elemento.setTypeBalance(depto.getTypeBalance());

				Pageable pages = PageRequest.of(0, 3);

				VehiclesRequestDTO paramsVehicles = new VehiclesRequestDTO();
				paramsVehicles.setIdDepartment(depto.getIdDepartment());
				paramsVehicles.setVehicleStatus("A");
				List<Vehicles> vehiculos = this.vehiclesRepoJPA.getVehiclesByCriterio(paramsVehicles, pages);

				if (vehiculos.size() > 0)
					elemento.setDepartmentLeaf(true);

				deptoHijos.add(elemento);
			}

			if (deptoHijos != null && !deptoHijos.isEmpty())
				retorno.setListDepartments(Optional.of(deptoHijos));

		} catch (Exception e) {
			log.error("Ocurrio un error al obtener el listado de departamentos hijos. " + e.getMessage());
			throw new Exception("Ocurrio un error al obtener el listado de departamentos hijos. " + e.getMessage());
		}

		return Optional.of(retorno);
	}

	@Override
	public Optional<AddDepartamentoJsonCtrlResultDTO> AddDepartamentoJsonCtrlDTO(
			AddDepartamentoJsonCtrlRequestDTO params, PagingSortDTO sort, PagingInitDTO paging, Locale locale)
			throws Exception {

		// ################################################################################################

		AddDepartamentoJsonCtrlResultDTO response = new AddDepartamentoJsonCtrlResultDTO();

		DepartmentDTO dto = new DepartmentDTO();

		if (!Functions.hasEmptyOrNull(params.getName())) {
			dto.setName(params.getName());
		}
		if (!Functions.hasEmptyOrNull(params.getTypeBalance())) {
			dto.setTypeBalance(params.getTypeBalance());
		}

		try {

			if (Functions.hasEmptyOrNull(params.getIdClient())) {
				throw new Exception("ID Cliente no puede ser null");
			}

			dto.setIdClient(params.getIdClient());
			// #######################################################################

			/* Envio de nuevo departamento a orpak */
			ClientDTO clientDTO = this.clientService.getClientById(dto.getIdClient());
			Optional<Clients> ResponseEntityClient = this.clientRepoJPA.findById(dto.getIdClient());

			if (ResponseEntityClient.isEmpty()) {
				log.error("Error al recuperar al cliente con id: " + params.getIdClient());
				throw new Exception("Error al recuperar el cliente");
			}

			Clients entityClient = ResponseEntityClient.get();

			log.info("Recuperar client: " + dto.getIdClient());
			log.debug("" + entityClient.toString());

			log.info("Agregar departamento: " + dto.getName());

			if (clientDTO.isCredit()) {

				if (ClientConstants.CLIENT_SCE.equals(entityClient.getClientType())) {
					dto.setTypeBalance(ClientConstants.TYPE_BALANCE_CLIENT);
				}
				if (ClientConstants.CLIENT_SCT.equals(entityClient.getClientType())) {
					dto.setTypeBalance(ClientConstants.TYPE_BALANCE_CLIENT);
					dto.setRestrAmountMax(ClientConstants.DEPTO_DAFAULT_RESTR_AMOUNT_MAX_SCT);
					dto.setRestrDailyMaxLoads(ClientConstants.DEPTO_DAFAULT_RESTR_MAX_LOAD_SCT);
					dto.setRestrDailyMaxQuantity(ClientConstants.DEPTO_DAFAULT_RESTR_MAX_QUANTITY_SCT);
				} else if (ClientConstants.CLIENT_SCI.equals(entityClient.getClientType())) {
					dto.setRestrAmountMax(ClientConstants.CARD_DAFAULT_RESTR_AMOUNT_MAX_SCI);
					dto.setRestrDailyMaxLoads(ClientConstants.CARD_DAFAULT_RESTR_MAX_LOAD_SCI);
					dto.setRestrDailyMaxQuantity(ClientConstants.CARD_DAFAULT_RESTR_MAX_QUANTITY_SCI);
				} else {
					dto.setRestrAmountMax(ClientConstants.DEPTO_DAFAULT_RESTR_AMOUNT_MAX_CRE);
					dto.setRestrDailyMaxLoads(ClientConstants.DEPTO_DAFAULT_RESTR_MAX_LOAD_CRE);
					dto.setRestrDailyMaxQuantity(ClientConstants.DEPTO_DAFAULT_RESTR_MAX_QUANTITY_CRE);
				}
			} else {
				dto.setRestrAmountMax(ClientConstants.DEPTO_DAFAULT_RESTR_AMOUNT_MAX);
				dto.setRestrDailyMaxLoads(ClientConstants.DEPTO_DAFAULT_RESTR_MAX_LOAD);
				dto.setRestrDailyMaxQuantity(ClientConstants.DEPTO_DAFAULT_RESTR_MAX_QUANTITY);
			}

			log.debug("" + dto.toString());

			// Grabar depto en base de datos
//				idDept=this.clientsMgr.addDepartmentSelective(dto);
			// ###################################

			Departments entityDepartments = new Departments();
			// dep.setIdDepartment(dto.getIdDepartment());
			entityDepartments.setTypeBalance(dto.getTypeBalance());
			entityDepartments.setClient(entityClient);
			entityDepartments.setDepartmentStatus(dto.getDepartmentStatus());
			entityDepartments.setDepartmentStatus(dto.getDepartmentStatus());
			entityDepartments.setName(dto.getName());
			entityDepartments.setRestrAmountMax(dto.getRestrAmountMax());
			entityDepartments.setRestrD(Functions.convertBooleanToInteger(dto.getRestrD()));
			entityDepartments.setRestrJ(Functions.convertBooleanToInteger(dto.getRestrJ()));
			entityDepartments.setRestrL(Functions.convertBooleanToInteger(dto.getRestrL()));
			entityDepartments.setRestrM(Functions.convertBooleanToInteger(dto.getRestrM()));
			entityDepartments.setRestrS(Functions.convertBooleanToInteger(dto.getRestrS()));
			entityDepartments.setRestrV(Functions.convertBooleanToInteger(dto.getRestrV()));
			entityDepartments.setRestrX(Functions.convertBooleanToInteger(dto.getRestrX()));
			entityDepartments.setRestrHend(dto.getRestrHend() == null ? null : dto.getRestrHend().intValue());
			entityDepartments.setRestrHinit(dto.getRestrHinit() == null ? null : dto.getRestrHinit().intValue());
			entityDepartments.setRestrDailyMaxLoads(
					dto.getRestrDailyMaxLoads() == null ? null : dto.getRestrDailyMaxLoads().intValue());
			entityDepartments.setWarningLoadCelular(dto.getWarningLoadCelular());
			entityDepartments.setWarningLoadEmail(dto.getWarningLoadMail());
			entityDepartments.setWarningLoadFailCelular(dto.getWarningLoadFailCelular());
			entityDepartments.setWarningLoadFailEmail(dto.getWarningLoadFailEmail());
			entityDepartments.setWarningLoadChannel(dto.getWarningLoadChannel());
			entityDepartments.setWarningLoadFailChannel(dto.getWarningLoadFailChannel());
			entityDepartments.setWarningStock(dto.getWarningStock());
			entityDepartments.setWarningStockCelular(dto.getWarningStockCelular());
			entityDepartments.setWarningStockEmail(dto.getWarningStockMail());
			entityDepartments.setWarningStockChannel(dto.getWarningStockChannel());
//					dep.setCodeOrpakInvoice(dto.getCodeOrpakInvoice());
//					dep.setCodeOrpakTicket(dto.getCodeOrpakTicket());
//					dep.setCodeOrpakClient(dto.getCodeOrpakClient());
			entityDepartments.setRestrType(dto.getRestrType());
			entityDepartments.setRestrDailyMaxQuantity(
					dto.getRestrDailyMaxQuantity() == null ? null : dto.getRestrDailyMaxQuantity().intValue());
			if (dto.getIdPadre() != null) {
				entityDepartments.setIdPadre(dto.getIdPadre().intValue());
			}

			entityDepartments = this.departmentRepoJPA.save(entityDepartments);

			// ###################################

////			dto.setIdDepartment(entityDepartments.getIdDepartment());
////			log.info("Agregar departamento: " + entityDepartments.getIdDepartment());
////			log.debug("" + dto.toString());
////
////			// Si el cliente es crédito se debe crear departamento con datos del credito
////			// de lo contario con prepago
////			if (!ClientConstants.CLIENT_EPR.equals(clientDTO.getClientType())) {
////				if (clientDTO.isCredit()) {
////					if (ClientConstants.CLIENT_SCI.equals(clientDTO.getClientType())) {
//
////							CreateDepartmentDTO deptodto = new CreateDepartmentDTO();
////							deptodto.setCustomerCode(clientDTO.getCustomerCodeClient());
////							deptodto.setDepartmentDescription(dto.getCodeUniqueInvoice());
////							String codOrpakCliente = this.orpakWSService.createDepartment(deptodto);
////							dto.setCodeOrpakInvoice(codOrpakCliente);
////							log.info("Enviar departamento a Orpak (Credito/Factura): " + deptodto + " codOrpak: " + codOrpakCliente);
////							
////							deptodto.setCustomerCode(clientDTO.getCustomerCodeClient());
////							deptodto.setDepartmentDescription(dto.getCodeUniqueTicket());
////							codOrpakCliente = this.orpakWSService.createDepartment(deptodto);
////							dto.setCodeOrpakTicket(codOrpakCliente);
////							log.info("Enviar departamento a Orpak (Credito/Boleta): " + deptodto + " codOrpak: " + codOrpakCliente);				
//					} else {
//						if (ClientConstants.CLIENT_SCS.equals(clientDTO.getClientType())) {
////								CreateDepartmentOfFleetDTO deptodto = new CreateDepartmentOfFleetDTO();
////								deptodto.setCustomercode(clientDTO.getCustomerCodeClient());
////								deptodto.setDeptcode(""+idDept);
////								deptodto.setStockcode(ClientConstants.PRODUCT_CODE_DEFAULT);
////								deptodto.setStocktype(OrpakWSConstants.ORPAK_WS_CREATE_DEPARTMENT_OFFLEET_STOCKTYPE);
////								deptodto.setBalancetype(OrpakWSConstants.ORPAK_WS_CREATE_DEPARTMENT_OFFLEET_BALANCETYPE);
////			
////								String codOrpakCliente = this.orpakWSService.createDepartmentOfFleet(deptodto);
////								dto.setCodeOrpakClient(codOrpakCliente);
////								log.info("Enviar departamento a Orpak (Credito) SCS: " + deptodto + " codOrpak: " + codOrpakCliente);
//						} else {
////								CreateDepartmentDTO deptodto = new CreateDepartmentDTO();
////								deptodto.setCustomerCode(clientDTO.getCustomerCodeClient());
////								deptodto.setDepartmentDescription(dto.getCodeUniqueClient());
////								String codOrpakCliente = this.orpakWSService.createDepartment(deptodto);
////								dto.setCodeOrpakClient(codOrpakCliente);
////								log.info("Enviar departamento a Orpak (Credito): " + deptodto + " codOrpak: " + codOrpakCliente);
//						}
//					}
//				} else {
//					// Enviar departamento a orpak boleta
//					if (clientDTO.getAccountJdeTicket() != null) {
////							CreateDepartmentDTO deptodto = new CreateDepartmentDTO();
////							deptodto.setCustomerCode(clientDTO.getCustomerCodeTicket());
////							deptodto.setDepartmentDescription(dto.getCodeUniqueTicket());
////							String codOrpakBoleta = this.orpakWSService.createDepartment(deptodto);
////							dto.setCodeOrpakTicket(codOrpakBoleta);
////							log.info("Enviar departamento a Orpak (Boleta): " + deptodto + " codOrpak: " + codOrpakBoleta);
//					}
//					// Enviar departamento a orpak factura
//					if (clientDTO.getAccountJdeInvoice() != null) {
////							CreateDepartmentDTO deptodto = new CreateDepartmentDTO();
////							deptodto.setCustomerCode(clientDTO.getCustomerCodeInvoice());
////							deptodto.setDepartmentDescription(dto.getCodeUniqueInvoice());
////							String codOrpakFactura = this.orpakWSService.createDepartment(deptodto);
////							dto.setCodeOrpakInvoice(codOrpakFactura);
////							log.info("Enviar departamento a Orpak (Factura): " + deptodto + " codOrpak: " + codOrpakFactura);
//					}
//
//				}
//
////					this.clientsMgr.updDeparments(dto);
//			}

//			return idDept;

			// #######################################################################
			log.info("El departamento " + entityDepartments.getIdDepartment() + " se inserto correctamente");
//			model.put("success", true);

			/* Se agrega registro de auditoría para creación de departamento */
//			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//			UsuarioAcegi usuarioSession = (UsuarioAcegi) auth.getPrincipal();

			Audits record = new Audits();
			record.setInsertLogin(params.getCorreoJWT());
			record.setAction("Ins");
			record.setInsertName(params.getUsernameJWT());
			record.setDate(LocalDateTime.now());
			record.setDescription("Creación departamento ID " + entityDepartments.getIdDepartment());
			record.setSystem("BOPTSF");

			this.auditsRepoJPA.save(record);

		} catch (Exception e) {
			log.error(e.getMessage());
			throw new Exception("Ha ocurrido un error al tratar de crear el departamento: " + e.getMessage());
		}

		// ################################################################################################

		response.setObservation(Optional.of("Flujo completado con exito"));

		return Optional.of(response);
	}

	@Override
	public Optional<ViewEditDepartamentoCtrlResultDTO> viewEditDepartamentoCtrl(
			ViewEditDepartamentoCtrlRequestDTO params, PagingSortDTO sort, PagingInitDTO paging, Locale locale) {
		
		ViewEditDepartamentoCtrlResultDTO responseService = new ViewEditDepartamentoCtrlResultDTO();
		Map<String, Object> model = new HashMap<String, Object>();
		
		
		
		log.info("Inicio ViewEditDepartamentoCtrl");
		
		if(Functions.hasEmptyOrNull(params,params.getIdClient(),params.getIdDepartment())) {
			log.error("Error idCliente y idDepartment deben ser validos: "+params);
			throw new IllegalArgumentException("Parametros no pueden ser null");
		}
		
		
		try{
			Long idDepartamento = params.getIdDepartment();
			//monto Distribuir
			String tabTarjeta = params.getTabTarjeta();
			
			if(tabTarjeta!=null && tabTarjeta !=""){
				model.put("tabTarjeta", tabTarjeta);
			}
			
			log.debug("parametros: " + params.toString());
			
			Optional<Clients> optionalEntityClient = this.clientRepoJPA.findById(params.getIdClient());
			
			if(optionalEntityClient.isEmpty()) {
				log.error("Cliente no encontrado, parametros: "+params);
				throw new ClientException("Cliente con id: "+params.getIdClient()+" No encontrado");
			}
			
			Clients entityClient = optionalEntityClient.get();
			
			Departments entityDepartments = this.departmentRepoJPA.findByIdDepartment(idDepartamento);
			
			if(entityDepartments == null) {
				log.error("Departamento no encontrado, parametros: "+params);
				throw new ClientException("Departamento con id: "+params.getIdDepartment()+" No encontrado");
			}
			
			DepartmentDTO departmentDTO = this.departmentsMapper.toDTO(entityDepartments);
			
			responseService.setDepartamento(departmentDTO);
			
			StationClientsRequestDTO paramsRepoConstraintsByDepartment = new StationClientsRequestDTO();
			paramsRepoConstraintsByDepartment.setIdClient(entityClient.getIdClient());
			paramsRepoConstraintsByDepartment.setIdDepartment(idDepartamento);
			paramsRepoConstraintsByDepartment.setClientType(entityClient.getClientType());
				
			Integer contieneConstraints = this.stationDepartmentsRepo.getConstraintsByDepartment(paramsRepoConstraintsByDepartment).size() > 0 ? 1:0;
			
			model.put("constraints_personalizados", contieneConstraints);
				
				if (entityDepartments.getWarningLoadEmail() != null) {
					String[] loadMails = entityDepartments.getWarningLoadEmail().split(",");
					if (loadMails.length > 0) {
						model.put("loadMail1", loadMails[0]);
						try {
							model.put("loadMail2", loadMails[1]);
						} catch (IndexOutOfBoundsException e) {
							model.put("loadMail2", "");
						}
					} else {
						model.put("loadMail1", "");
						model.put("loadMail2", "");
					}
				}
				
				if (entityDepartments.getWarningStockEmail() != null) {
					String[] stockMails = entityDepartments.getWarningStockEmail().split(",");
					if (stockMails.length > 0) {
						model.put("stockMail1", stockMails[0]);
						try {
							model.put("stockMail2", stockMails[1]);
						} catch (IndexOutOfBoundsException e) {
							model.put("stockMail2", "");
						}
					} else {
						model.put("stockMail1", "");
						model.put("stockMail2", "");
					}
				}
				
//				responseService.setClient(this.clientMapper.toDto(entityClient));
				
//				TODO ABILBAO ORPAK
//				ProductDepartmentDTO example = new ProductDepartmentDTO();
//				example.setIdDepartment(departamento.getIdDepartment());
//				model.put("listaSaldos", JSONArray.fromObject(clientService.doProductDepartmentTransf(example)));
			
				
			if (entityClient.getGps()!= null && entityDepartments.getGps()!= null){
				String [] servicios = entityClient.getGps().split(" ");
				String [] serviciosDepto = entityDepartments.getGps().split(" ");
				if ("1".equals(servicios[ClientConstants.SERVICES_GPS_POSITION_VALID])){
					log.info("El cliente tiene habilitado validacion gps: " + entityClient.getIdClient());
					model.put("clientegps", "true");
					if("1".equals(serviciosDepto[ClientConstants.SERVICES_GPS_POSITION_VALID])){
						model.put("validaciongpsChecked", "true");
						log.info("El departamento tiene habilitado validacion gps: " + idDepartamento);
					}
					model.put("validaciongpsDisabled", "false");
				}else{
					model.put("validaciongpsChecked", "false");
					model.put("validaciongpsDisabled", "true");
				}
			}
			
			Boolean resultado;
			if (ClientConstants.CLIENT_SCS.equals(entityClient.getClientType())){
				//Se pregunta si hay saf pendientes del tipo utb para el depto que viene por parametro.
				resultado = this.doBloqueoTypeBalanceLitros(idDepartamento);
			}else{
				//Se pregunta si hay saf pendientes del tipo utb para el depto que viene por parametro.
				resultado = this.doBloqueoTypeBalance(idDepartamento);
			}
			
			log.debug("Resultado bloqueo de type balance por saf pendientes: "+resultado);
			model.put("typeBalanceBloqueado", resultado);
			
		} catch(Exception e){
			model.put("mensajeError", "Error al cargar datos del departamento: " + e.getMessage());
		}
		log.info("Termino ViewEditDepartamentoCtrl");
		
		
		responseService.setModel(model);
		
		return Optional.of(responseService);
	}
	
	@Override
	public Boolean doBloqueoTypeBalanceLitros(Long idDepartment) throws SafException {
		
		if(idDepartment == null){
			log.error("Parametro de entrada idDepartment es nulo");
			throw new IllegalArgumentException("Parametro de entrada idDepartment es nulo");
		}
		
//		List<SafQueue> totalUbt4 = this.safQueueRepoJPA.findByIdElementAndTypeAndStatus(idDepartment+"", SafConstants.SAF_CONDITIONAL_LT_4, SafConstants.SAF_STATUS_PENDING);
//		log.debug("Hay un total de : "+ totalUbt4+" saf pendientes del tipo: "+ SafConstants.SAF_CONDITIONAL_LT_4);
//		if(totalUbt4.size() > 0){
//			return true;
//		}else{
//			List<SafQueue> totalUtb1 = this.safQueueRepoJPA.findByIdElementAndTypeAndStatus(idDepartment+"", SafConstants.SAF_CONDITIONAL_LT_1, SafConstants.SAF_STATUS_PENDING);
//			log.debug("Hay un total de : "+ totalUtb1+" saf pendientes del tipo: "+ SafConstants.SAF_CONDITIONAL_LT_1);
//			if(totalUtb1.size() > 0){
//				return true;
//			}else{
//				List<SafQueue> totalUtb2 = this.safQueueRepoJPA.findByIdElementAndTypeAndStatus(idDepartment+"", SafConstants.SAF_CONDITIONAL_LT_2, SafConstants.SAF_STATUS_PENDING);
//				log.debug("Hay un total de : "+ totalUtb2+" saf pendientes del tipo: "+ SafConstants.SAF_CONDITIONAL_LT_2);
//				if(totalUtb2.size() > 0){
//					return true;
//				}else{
//					List<SafQueue> totalUtb3 = this.safQueueRepoJPA.findByIdElementAndTypeAndStatus(idDepartment+"", SafConstants.SAF_CONDITIONAL_LT_3, SafConstants.SAF_STATUS_PENDING);
//
//					log.debug("Hay un total de : "+ totalUtb3+" saf pendientes del tipo: "+ SafConstants.SAF_CONDITIONAL_LT_3);
//					if(totalUtb3.size() > 0){
//						return true;
//					}
//				}
//			}
//		}
		return false;
	}
	
	@Override
	public Boolean doBloqueoTypeBalance(Long idDepartment) throws SafException {
		
		if(idDepartment == null){
			log.error("Parametro de entrada idDepartment es nulo");
			throw new IllegalArgumentException("Parametro de entrada idDepartment es nulo");
		}
		
		//Se pregunta si hay saf pendientes del tipo utb para el depto que viene por parametro.
//		int totalUbt4 = this.safService.getTotalSafTaskPendings(SafConstants.SAF_CONDITIONAL_4, idDepartment);
//		List<SafQueue> totalUbt4 = this.safQueueRepoJPA.findByIdElementAndTypeAndStatus(idDepartment+"", SafConstants.SAF_CONDITIONAL_4, SafConstants.SAF_STATUS_PENDING);
//
//		log.debug("Hay un total de : "+ totalUbt4+" saf pendientes del tipo: "+ SafConstants.SAF_CONDITIONAL_4);
//		if(totalUbt4.size() > 0){
//			return true;
//		}else{
////			int totalUtb1 = this.safService.getTotalSafTaskPendings(SafConstants.SAF_CONDITIONAL_1, idDepartment);
//			List<SafQueue> totalUtb1 = this.safQueueRepoJPA.findByIdElementAndTypeAndStatus(idDepartment+"", SafConstants.SAF_CONDITIONAL_1, SafConstants.SAF_STATUS_PENDING);
//
//			log.debug("Hay un total de : "+ totalUtb1+" saf pendientes del tipo: "+ SafConstants.SAF_CONDITIONAL_1);
//			if(totalUtb1.size() > 0){
//				return true;
//			}else{
////				int totalUtb2 = this.safService.getTotalSafTaskPendings(SafConstants.SAF_CONDITIONAL_2, idDepartment);
//				List<SafQueue> totalUtb2 = this.safQueueRepoJPA.findByIdElementAndTypeAndStatus(idDepartment+"", SafConstants.SAF_CONDITIONAL_2, SafConstants.SAF_STATUS_PENDING);
//
//				log.debug("Hay un total de : "+ totalUtb2+" saf pendientes del tipo: "+ SafConstants.SAF_CONDITIONAL_2);
//				if(totalUtb2.size() > 0){
//					return true;
//				}else{
////					int totalUtb3 = this.safService.getTotalSafTaskPendings(SafConstants.SAF_CONDITIONAL_3, idDepartment);
//					List<SafQueue> totalUtb3 = this.safQueueRepoJPA.findByIdElementAndTypeAndStatus(idDepartment+"", SafConstants.SAF_CONDITIONAL_3, SafConstants.SAF_STATUS_PENDING);
//
//					log.debug("Hay un total de : "+ totalUtb3+" saf pendientes del tipo: "+ SafConstants.SAF_CONDITIONAL_3);
//					if(totalUtb3.size() > 0){
//						return true;
//					}
//				}
//			}
//		}
		return false;
	}

	@Override
	public Optional<UpdDepartmentCtrlResultDTO> UpdDepartmentCtrl(UpdDepartmentCtrltRequestDTO params, PagingSortDTO sort,
			PagingInitDTO paging, Locale locale) { // TODO ABILBAO completar flujo
		
		UpdDepartmentCtrlResultDTO serviceResponse = new UpdDepartmentCtrlResultDTO();
		Map<String, Object> model = new HashMap<String, Object>();
		
		Departments entityDepartamento = new Departments();
		boolean cambioManejoSaldo = false;
		boolean cambioNombreDepto = false;
		boolean cambioStatusActual = false;

		try {
			
			if (Functions.hasEmptyOrNull(params.getRutUsuarioBO())) {
				serviceResponse.setObservation(Optional.of("Usuario del BO es obligatorio"));
				throw new IllegalArgumentException("Usuario del BO es obligatorio");
			}
			
			if (!Functions.hasEmptyOrNull(params.getIdDepartamento())) {
				entityDepartamento.setIdDepartment(params.getIdDepartamento());
			}else {
				serviceResponse.setObservation(Optional.of("ID departamento es obligatorio"));
				throw new IllegalArgumentException("Id departamento es obligatorio");
			}

			if (!Functions.hasEmptyOrNull(params.getManejoSaldo())) {
				entityDepartamento.setTypeBalance(params.getManejoSaldo());
				cambioManejoSaldo = true;
			}

			if (!Functions.hasEmptyOrNull(params.getNameDepartment())) {
				entityDepartamento.setName(params.getNameDepartment());
				cambioNombreDepto = true;
			}

			if (!Functions.hasEmptyOrNull(params.getStatusActual())) {
				entityDepartamento.setDepartmentStatus(params.getStatusActual());
				cambioStatusActual = true;
			}
			
			
//			DeptUserDTO departmentUserDto=new DeptUserDTO();
//			departmentUserDto.setDepto(departamento);
//			UserDTO user= new UserDTO();
//			user.setIdUser(usuario.getIdUsuario());
//			user.setName(usuario.getName());
//			user.setFirstName(usuario.getApellidoPaterno());
//			departmentUserDto.setUser(user);
//			this.serviceClient.updDepartmentMS(departmentUserDto);
			
			//##################################### updDepartmentMS ###############################################
			
			//se obtiene el departamento 
//			DepartmentDTO depto= new DepartmentDTO();
//			try {
//				depto =this.getListDepartmentById(duDTO.getDepto().getIdDepartment());
//			}catch(ClientNotFoundException | ClientException e1) {
//				log.error("Problemas al obtener departamento ["+duDTO.getDepto().getIdDepartment()+"]: "+e1.getMessage());
//				throw new DepartmentException("Problemas al obtener departamento ["+duDTO.getDepto().getIdDepartment()+"]");
//			}
//				
			Optional<Departments> optionalEntityDepartment = this.departmentRepoJPA.findById(params.getIdDepartamento());
			
			if(optionalEntityDepartment.isEmpty()) {
				log.error("ID no encontrada");
				serviceResponse.setObservation(Optional.of("ID departamento no existe"));
				throw new IllegalArgumentException("ID no encontrada");
			}
			
			Departments entityDepartment = optionalEntityDepartment.get();
//			Departments depto = optionalEntityDepartment.get();
			
			Users EntityUserBO = this.usersRepoJPA.findUserByRutAndStatus(params.getRutUsuarioBO(), "A");
			
			if(Functions.hasEmptyOrNull(EntityUserBO)) {
				log.error("Usuario con rut "+params.getRutUsuarioBO()+" no encontrado");
				serviceResponse.setObservation(Optional.of("Usuario de BackOffice no encontrado"));
				throw new IllegalArgumentException("Usuario de BackOffice no encontrado");
			}
			
			//historial departamento cambio nombre
			DepartmentHistory entityDepartmentHistory= new DepartmentHistory();
			
			//Se valida si se cambio el nombre en un departamento.
			if(entityDepartment.getName()!=null && !entityDepartment.getName().equals("")){
				if(cambioNombreDepto) {
					//Se guarda en el historial del departamento, el cambio de nombre.
					entityDepartmentHistory.setAction("Cambio de Nombre: " + entityDepartment.getName());
					entityDepartmentHistory.setActionType(ClientConstants.CAMBIO_NOMBRE);
					entityDepartmentHistory.setDate(LocalDateTime.now());
					entityDepartmentHistory.setDepartment(entityDepartment);
					entityDepartmentHistory.setUser(EntityUserBO);
					entityDepartmentHistory.setUsername(EntityUserBO.getName()+' '+EntityUserBO.getFirstName());
//					this.addDepartmentHistory(entityDepartmentHistory);
					this.departmentHistoryRepoJPA.save(entityDepartmentHistory);
					
				}

			}
			
//			UserDTO userBoDTO = this.userMapper.toDTO(EntityUserBO);
//			DepartmentDTO departmentDTO = this.departmentsMapper.toDTO(entityDepartment);
			
			
	        // se valida si se cambia el typeBalance en un departamento.
//			if(cambioManejoSaldo){
//				
//				if (this.validacionSafTB(entityDepartment.getIdDepartment())){
//					log.info("Existe un proceso de cambio de type balance en proceso.");
//					throw new CambioTypeBalanceException("Existe un proceso de cambio de type balance en proceso.");
//				}
//				
//				if(entityDepartment.getTypeBalance().equals(ClientConstants.TYPE_BALANCE_DEPTO) && cambioManejoSaldo){
//					log.debug("El departamento con id: "+entityDepartment.getIdDepartment()+" cambia manejo de saldo a departamento.");
//
//					try{
//						boolean resultado = this.taskService.doChangeTypeBalanceToDeptoTask(departmentDTO,userBoDTO);
//						if(resultado == false){
//							log.error("Ha ocurrido un error al cambiar type balance.");
//							throw new CambioTypeBalanceException("Ha ocurrido un error al cambiar manejo de saldo.");
//						}
//					}catch(ClientException e){
//						log.error("Ha ocurrido un error al realizar el cambio de type balance: "+ e.getMessage());
//						throw new CambioTypeBalanceException("Ha ocurrido un error al realizar el cambio de manejo de saldo: "+ e.getMessage());
//					}
//						
//				}else if (!depto.getTypeBalance().equals(entityDepartment.getTypeBalance())){
//					log.debug("El departamento con id: "+entityDepartment.getIdDepartment()+" cambia manejo de saldo a tarjeta.");
//						
//					try{
//						boolean resultado = this.taskService.doChangeTypeBalanceToCardTask(depto);
//						if(resultado == false){
//							log.error("Ha ocurrido un error al cambiar type balance.");
//							throw new CambioTypeBalanceException("Ha ocurrido un error al cambiar manejo de saldo.");
//						}
//						
//						
//					}catch(ClientException e){
//						log.error("Ha ocurrido un error al realizar el cambio de manejo de saldo: "+ e.getMessage());
//						throw new CambioTypeBalanceException("Ha ocurrido un error al realizar el cambio de manejo de saldo: "+ e.getMessage());
//					}
//				}
//					
//				//Se guarda en el historial del departamento el cambio de typeBalance.
//				entityDepartmentHistory.setActionType(ClientConstants.CAMBIO_TYPE_BALANCE);
//				if(entityDepartment.getTypeBalance().equals(ClientConstants.TYPE_BALANCE_CARD)){
//					entityDepartmentHistory.setAction("Cambio manejo de saldo: Por Tarjeta");
//				}else{
//					entityDepartmentHistory.setAction("Cambio manejo de saldo: Por Departamento");
//				}
//				entityDepartmentHistory.setDate(GeneralFunctions.convertDateToMysql(new Date()));
//				entityDepartmentHistory.setIdDepartment(entityDepartment.getIdDepartment());
//				entityDepartmentHistory.setIdUser(duDTO.getUser().getIdUser());
//				entityDepartmentHistory.setUsername(duDTO.getUser().getName()+' '+duDTO.getUser().getFirstName());
//
//				
//				this.addDepartmentHistory(entityDepartmentHistory);
//			}
//			// se valida si se cambia el status de un departamento.
//			if(entityDepartment.getDepartmentStatus() != null && !entityDepartment.getDepartmentStatus().equals(depto.getDepartmentStatus())){
//				//se guarda el cambio de status en el historial.
//				if(entityDepartment.getDepartmentStatus().equals(ClientConstants.DEPARMENT_STATUS_INACTIVE)){
//					entityDepartmentHistory.setAction("Cambio de estado : Inactivo" );
//				}else{
//					entityDepartmentHistory.setAction("Cambio de estado : Activo" );
//				}
//				entityDepartmentHistory.setActionType(ClientConstants.CAMBIO_STATUS);
//				entityDepartmentHistory.setDate(GeneralFunctions.convertDateToMysql(new Date()));
//				entityDepartmentHistory.setIdDepartment(entityDepartment.getIdDepartment());
//				entityDepartmentHistory.setIdUser(duDTO.getUser().getIdUser());
//				entityDepartmentHistory.setUsername(duDTO.getUser().getName()+' '+duDTO.getUser().getFirstName());
//				this.addDepartmentHistory(entityDepartmentHistory);
//					
//				//se registra el cambio de status del departamento y tarjetas a SAF.
//				try {
//					this.safService.pushMessageUpdateDepartmentCardStatus(entityDepartment.getIdDepartment());
//				}catch (SafException e) {
//					log.error("Error al enviar mensaje a la cola saf de actualización status department : ["+entityDepartment.getIdDepartment()+"]");
//					throw new DepartmentException("Error al enviar mensaje a la cola saf de actualización status department : ["+entityDepartment.getIdDepartment()+"]");
//				}
//			}
			
//			try{
//				log.info("Actualizar datos del departamento");
//				log.debug(""+entityDepartment);
//				this.clientsMgr.updDeparments(entityDepartment);
//			}catch(Exception e){
//				log.error("Problemas al actualizar depto ["+entityDepartment.getIdDepartment()+"]: "+e.getMessage());
//				throw new DepartmentException("Problemas al actualizar depto ["+entityDepartment.getIdDepartment()+"]");
//			}
			
			
			//##################################### updDepartmentMS ###############################################
			
			/*Se agrega registro de auditoría para actualización de manejo de saldo departamento*/
//			AuditDTO record= new AuditDTO();
//			record.setInsertLogin(usuario.getUsername());
//			record.setAction("Upd");
//			record.setInsertName(usuario.getName());
//			record.setDate( LocalDateTime.now());
//			record.setDescription("Actualización manejo de saldo departamento ID" + idDepartamento);
//			record.setSystem("BOPTSF");
			
//			try {
//				this.auditService.addAudits(record);
//			} catch (AuditsException e) {
//				log.error("Ha ocurrido un error al ingresar el registro de auditoría para actualización de manejo de saldo departamento", e);
//			}
			
			if(cambioManejoSaldo == true){
				model.put("cambioManejoSaldo", true);
			}else{
				model.put("cambioManejoSaldo", false);
			}
			model.put("success", true);


//		}catch (ServletRequestBindingException e) {
//			log.error("Error: "+e.getMessage());
//			model.put("msg","Error al obtener los parámetros de la request.");
//		} catch (DepartmentException e) {
//			log.error("Error: "+e.getMessage());
//			model.put("msg","Error al actualizar al departamento.");
//			model.put("failure", true);
//		}catch (CambioTypeBalanceException e) {
//			log.error("Error: "+e.getMessage());
//			model.put("msg","Ha ocurrido un error al cambiar type balance.");
//			model.put("failure", true);
		} catch(Exception e){
			log.error("Error: "+e.getMessage());
			model.put("msg","Error al actualizar al departamento.");
			model.put("failure", true);
		}
		
//		return new ModelAndView(view, model);
		//################################################################################################################
		return Optional.empty();
	}
	
	public Boolean validacionSafTB(Long idDepartment) throws SafException{
		List<SafReportDTO> resultadoUTB1 = this.safQueueRepoJPA.getSafPendingReport(idDepartment.toString(),SafConstants.SAF_CONDITIONAL_1, "P");
		Boolean utb1 = resultadoUTB1.size()>0?true:false;
		List<SafReportDTO> resultadoUTB2 = this.safQueueRepoJPA.getSafPendingReport(idDepartment.toString(),SafConstants.SAF_CONDITIONAL_2, "P");
		Boolean utb2 = resultadoUTB2.size()>0?true:false;
		List<SafReportDTO> resultadoUTB3 = this.safQueueRepoJPA.getSafPendingReport(idDepartment.toString(),SafConstants.SAF_CONDITIONAL_3, "P");
		Boolean utb3 = resultadoUTB3.size()>0?true:false;
		List<SafReportDTO> resultadoUTB4 = this.safQueueRepoJPA.getSafPendingReport(idDepartment.toString(),SafConstants.SAF_CONDITIONAL_4, "P");
		Boolean utb4 = resultadoUTB4.size()>0?true:false;
		List<SafReportDTO> resultadoUTBL1 = this.safQueueRepoJPA.getSafPendingReport(idDepartment.toString(),SafConstants.SAF_CONDITIONAL_LT_1, "P");
		Boolean utbl1 = resultadoUTBL1.size()>0?true:false;
		List<SafReportDTO> resultadoUTBL2 = this.safQueueRepoJPA.getSafPendingReport(idDepartment.toString(),SafConstants.SAF_CONDITIONAL_LT_2, "P");
		Boolean utbl2 = resultadoUTBL2.size()>0?true:false;
		List<SafReportDTO> resultadoUTBL3 = this.safQueueRepoJPA.getSafPendingReport(idDepartment.toString(),SafConstants.SAF_CONDITIONAL_LT_3, "P");
		Boolean utbl3 = resultadoUTBL3.size()>0?true:false;
		List<SafReportDTO> resultadoUTBL4 = this.safQueueRepoJPA.getSafPendingReport(idDepartment.toString(),SafConstants.SAF_CONDITIONAL_LT_4, "P");
		Boolean utbl4 = resultadoUTBL4.size()>0?true:false;
		
		if (utb1 == true || utb2 == true || utb3 == true || utb4 == true || utbl1 == true || utbl2 == true || utbl3 == true ||utbl4 == true ){
			return true;
		}
		return false;
	}
	
	@Override
	public List<ProductDepartmentDTO> doListProductDepartmentById(Long idDepartment) throws ProductDepartmentException {
		if(idDepartment == null){
			log.error("Parametro de entrada idDepartment es nulo");
			throw new IllegalArgumentException("Parametro de entrada idDepartment es nulo");
		}
		
		List<ProductDepartmentDTO> lista = this.getListProductDepartmentById(idDepartment);
		for (ProductDepartmentDTO departmentProductDTO : lista) {
//			try {
//				this.updDepartmentBalance( departmentProductDTO.getIdDepartment() );
//			} catch (DepartmentException e) {
//				log.error("Ha ocurrido un error. " + e.getMessage());
//				throw new ProductDepartmentException( "Ha ocurrido un error al recuperar saldos desde Orpak." );
//			}
		}
		
//		return this.clientsMgr.getListProductDepartmentById(idDepartment);
		return null;
	}
	
	@Override
	public List<ProductDepartmentDTO> getListProductDepartmentById(Long idDepartment) throws ProductDepartmentException {
		
		List<ProductsDepartmentsRel> listEntityProductDepartmentsRel = new ArrayList<ProductsDepartmentsRel>();
		List<ProductDepartmentDTO> listadoProductDepartmentDTO = new ArrayList<ProductDepartmentDTO>();
		
//		try{
//			listEntityProductDepartmentsRel = this.productsDepartmentsRelRepoJPA.findByIdDepartment(idDepartment);
//			
//			listadoProductDepartmentDTO = ProductDepartmentMapper.toDtoList(listEntityProductDepartmentsRel);
//			
//		}catch(Exception e){
//			log.error("Ha ocurrido un error al obtener listado: " + e.getMessage());
//			throw new ProductDepartmentException("Ha ocurrido un error al obtener listado. " + e.getMessage());
//		}
		
		
		
		return listadoProductDepartmentDTO;
	}

	@Override
	public Optional<UpdAlertDepartmentJsonCtrlResultDTO> UpdDepartmentJsonCtrl(UpdAlertDepartmentJsonCtrlRequestDTO params,
			PagingSortDTO sort, PagingInitDTO paging, Locale locale) {
		
		
		log.info("UpdAlertDepartmentJsonCtrl");
		
		Long idDepto= params.getIdDepartment();
		UpdAlertDepartmentJsonCtrlResultDTO responseService = new UpdAlertDepartmentJsonCtrlResultDTO();

		if(Functions.hasEmptyOrNull(params,params.getIdDepartment(),params.getRutUsuarioSession())){
			log.error("Id departamento no puede ser null");
			responseService.setObservation(Optional.of("Parametros invalidos"));
			return Optional.of(responseService);
		}
		
		Users usuarioSession = this.usersRepoJPA.findUserByRutAndRoleAndStatus(params.getRutUsuarioSession(), 1, "A");
		
		Departments dto = this.departmentRepoJPA.findByIdDepartment(params.getIdDepartment());
		
		String stockChannel= params.getWarningStockChannel();
		if(stockChannel!=null && stockChannel!=""){
			dto.setWarningStockChannel(stockChannel);
		}

		String stockCelular= params.getWarningStockCelular();
		if(stockCelular!=null && stockCelular!=""){
			dto.setWarningStockCelular(stockCelular);
		}
		String stockmail= params.getWarningStockMail();
		if(stockmail!=null && stockmail!=""){
			dto.setWarningStockEmail(stockmail);
		}
		
		String stock= params.getWarningStock();
		if(stock!=null && stock!=""){
			String str=stock.replaceAll(",","");
			BigDecimal monto=new BigDecimal(str);
			dto.setWarningStock(monto);
		}
		String loadChannel= params.getWarningLoadChannel();
		if(loadChannel!=null && loadChannel!=""){
			dto.setWarningLoadChannel(loadChannel);
		}
		
		String warningLoadCelular= params.getWarningLoadCelular();
		if(warningLoadCelular!=null && warningLoadCelular!=""){
			dto.setWarningLoadCelular(warningLoadCelular);
		}
		String warningLoadMail= params.getWarningLoadMail();
		if(warningLoadMail!=null && warningLoadMail!=""){
			dto.setWarningLoadEmail(warningLoadMail);
		}
		String warningLoadFailChannel= params.getWarningLoadFailChannel();
		if(warningLoadFailChannel!=null && warningLoadFailChannel!=""){
			dto.setWarningLoadFailChannel(warningLoadFailChannel);
		}
		
		String warningLoadFailCelular= params.getWarningLoadFailCelular();
		if(warningLoadFailCelular!= null && warningLoadFailCelular!=""){
			dto.setWarningLoadFailCelular(warningLoadFailCelular);
		}
		
		String warningLoadFailEmail= params.getWarningLoadFailEmail();
		if(warningLoadFailEmail!=null && warningLoadFailEmail!= ""){
			dto.setWarningLoadFailEmail(warningLoadFailEmail);
		}
		try{
			this.departmentRepoJPA.save(dto);
			
			//Se agrega actualización de alertas a historial
			DepartmentHistory dHistory = new DepartmentHistory();
			dHistory.setIdDepartment(idDepto);
			dHistory.setIdUser(usuarioSession.getIdUser());
			dHistory.setUsername(usuarioSession.getName()+" "+usuarioSession.getLastName());
			dHistory.setDate(LocalDateTime.now());
			dHistory.setAction("Cambio de alertas");
			dHistory.setActionType(ClientConstants.CAMBIO_ALERTAS);
			this.departmentHistoryRepoJPA.save(dHistory);
//			this.clientService.addDepartmentHistory(dHistory);
			
			/*Se agrega registro de auditoría para actualización configuración departamento*/
			Audits record = new Audits();
//			AuditDTO record= new AuditDTO();
			record.setInsertLogin(usuarioSession.getUsername());
			record.setAction("Upd");
			record.setInsertName(usuarioSession.getName());
			record.setDate( LocalDateTime.now());
			record.setDescription("Actualización configuración departamento ID " + idDepto);
			record.setSystem(params.getPortalSession());
			
			try {
				this.auditsRepoJPA.save(record);
			} catch (Exception e) {
				log.error("Ha ocurrido un error al ingresar el registro de auditoría para actualización configuración departamento", e);
			}
		}catch(Exception e){
			log.error("Error al actualizar alerta departamento "+ e.getMessage());
			responseService.setObservation(Optional.of("Ha ocurrido un error al actualizar las alertas del departamento "+ idDepto));
			return Optional.of(responseService);
			
		}
		
		responseService.setObservation(Optional.of("Departamento actualizado con exito"));
		return Optional.of(responseService);
	}

	@Override
	public Optional<UpdConfigDepartmentJsonResultDTO> UpdConfigDepartmentJson(UpdConfigDepartmentJsonRequestDTO params,
			PagingSortDTO sort, PagingInitDTO paging, Locale locale) {
		
		UpdConfigDepartmentJsonResultDTO response = new UpdConfigDepartmentJsonResultDTO();
		response.setStatusCode(Optional.of(HttpStatus.INTERNAL_SERVER_ERROR.value()));
		
		log.info("UpdConfigDepartmentJsonCtrl");
		Map<String, Object> model = new HashMap<String, Object>();
		
		if (Functions.hasEmptyOrNull(params, params.getIdClient(), params.getIdDepartment(), params.getRutUsuarioSession(), params.getPortalSession())) {
			log.error("Los parametros idClient, idDepartment, rutUsuarioSession, portalSession no pueden ser nulos o vacios.");
			response.setObservation(Optional.of("Los parametros idClient, idDepartment, rutUsuarioSession, portalSession no pueden ser nulos o vacios."));
			response.setStatusCode(Optional.of(HttpStatus.BAD_REQUEST.value()));
			return Optional.of(response);
		}
		
//		ClientDTO cliente = (ClientDTO) session.getAttribute("cliente");
		Optional<Clients> optionalEntityCliente = this.clientRepoJPA.findById(params.getIdClient());
		
		if(optionalEntityCliente.isEmpty()) {
			log.error("ID Cliente no existe o no es correcta");
			response.setObservation(Optional.of("No se encuentra cliente con id enviado"));
			return Optional.of(response);
		}
		
		Clients entityCliente = optionalEntityCliente.get();
		
//		DepartmentDTO deptoToUpdate = new DepartmentDTO();
		Departments deptoToUpdate = this.departmentRepoJPA.findByIdDepartment(params.getIdDepartment());
		
		if(Functions.hasEmptyOrNull(deptoToUpdate)) {
			log.error("No se encontro departamento con id " + params.getIdDepartment());
			response.setObservation(Optional.of("No se encontro departamento con id " + params.getIdDepartment()));
			response.setStatusCode(Optional.of(HttpStatus.BAD_REQUEST.value()));
			return Optional.of(response);
		}

//		Boolean restrictionTypeCard = params.getRestrC();
//		deptoToUpdate.setRestrictionTypeCard(restrictionTypeCard);
		
		Long idDepto= params.getIdDepartment();
		if(idDepto!=null){
			deptoToUpdate.setIdDepartment(idDepto);
		}
		
		String restrType = params.getRestrType();
		if(restrType != null && !restrType.equals("")){
			deptoToUpdate.setRestrType(restrType);
		}

		Boolean restL=params.getDiaLunes();
		deptoToUpdate.setRestrL(restL == true ? 1 : 0);
		Boolean restM=params.getDiaMartes();
		deptoToUpdate.setRestrM(restM == true ? 1 : 0);
		Boolean restrX = params.getDiaMiercoles();
		deptoToUpdate.setRestrX(restrX == true ? 1 : 0);
		Boolean restrJ = params.getDiaJueves();
		deptoToUpdate.setRestrJ(restrJ == true ? 1 : 0);
		Boolean restrV = params.getDiaViernes();
		deptoToUpdate.setRestrV(restrV == true ? 1 : 0);
		Boolean restrS = params.getDiaSabado();
		deptoToUpdate.setRestrS(restrS == true ? 1 : 0);
		Boolean restrD = params.getDiaDomingo();
		deptoToUpdate.setRestrD(restrD == true ? 1 : 0);
		
		String listaIdCard ="";
		if(!Functions.hasEmptyOrNull(params.getListadoIdCard()))
			listaIdCard = params.getListadoIdCard();
		
		if(!Functions.hasEmptyOrNull(params.getRestHini()))
			deptoToUpdate.setRestrHinit(params.getRestHini());

		if(!Functions.hasEmptyOrNull(params.getRestHend()))
			deptoToUpdate.setRestrHend(params.getRestHend());

		if(!Functions.hasEmptyOrNull(params.getRestAmountMax()))
			deptoToUpdate.setRestrAmountMax(new BigDecimal(params.getRestAmountMax()));
		
		if (!Functions.hasEmptyOrNull(params.getRestDailyMax()))
			deptoToUpdate.setRestrDailyMaxLoads(params.getRestDailyMax());

		if (!Functions.hasEmptyOrNull(params.getRestDailyMaxQuan()))
			deptoToUpdate.setRestrDailyMaxQuantity(params.getRestDailyMaxQuan());

		StationClientsRequestDTO paramsConstraintsByDepartment = new StationClientsRequestDTO(entityCliente.getIdClient(), idDepto,entityCliente.getClientType());
		
		String radio= params.getEstacion();
		Boolean hayRegistros = this.stationDepartmentsRepoExtend.getConstraintsByDepartment(paramsConstraintsByDepartment).size()>0;
		if("T".equals(radio)){
			this.stationDepartmentConstraintsRepoJPA.deleteByIdDepartment(idDepto);
			/* TODO si el cliente no tiene estaciones se eliminan las relaciones de la tabla station_department_constraints
			 * si el cliente tiene estaciones se eliminan las que existan en station_department_constraints y se agregan las del cliente
			 */
			Boolean tieneEstacion = false;
			
			if (ClientConstants.CLIENT_SCS.equalsIgnoreCase(entityCliente.getClientType())) {
				try {
					tieneEstacion =  this.clientService.clienteTieneEstacionesAsignadas(entityCliente.getIdClient());
				} catch (ClientServiceException e) {
					
					log.error("Ocurrio un error al buscar si el cliente tiene estaciones asignadas: "+e.getMessage());
				}
			}
			
			if(tieneEstacion) {
				
				StationsRequestDTO criterioStation = new StationsRequestDTO();
				criterioStation.setIdClient(entityCliente.getIdClient());
				criterioStation.setClientType(entityCliente.getClientType());
				List<StationsDTO> estacionesCliente = this.stationClientsRepoExtend.getStationClientsTerritoryByCriterio(criterioStation);
				log.info("Estaciones encontradas para el cliente " + entityCliente.getIdClient() + ": " + estacionesCliente.size());
				
				Long registros = 0L;
				for(StationsDTO estacion : estacionesCliente) {
//					registros += this.clientService.deleteStationDepartmentConstraint(idDepto, estacion.getStationCode());
					StationDepartmentConstraintsId id = new StationDepartmentConstraintsId(estacion.getStationCode(),idDepto);
					this.stationDepartmentConstraintsRepoJPA.deleteById(id);
					registros++;
					log.info("Se agrega estacion codigo " + estacion.getStationCode() + " al depto " + idDepto);
				}
				log.info("Registros afectados: " + registros);
			}
		}
			
		try{
			
//			UsuarioAcegi usuarioSession = (UsuarioAcegi) auth.getPrincipal();
			
			Users usuarioSession = this.usersRepoJPA.findUserByRutAndRoleAndStatus(params.getRutUsuarioSession(), 1, "A");
			
			Boolean modificadoGps = false;
			String [] servicios = new String [ClientConstants.SERVICES_GPS_CANTIDAD];
			String validacionGps= params.getValidacionGPS();
			if (validacionGps != null){
				if ("true".equals(validacionGps)){
					servicios[ClientConstants.SERVICES_GPS_POSITION_VALID] = "1";
				}else{
					servicios[ClientConstants.SERVICES_GPS_POSITION_VALID] = "0";
				}
				
				if (servicios.length > 0){
					modificadoGps = this.updateServicesGpsDepartment(idDepto, servicios);
					
					String serviciosString = "";
					for(String strElement : servicios){
						if (strElement != null){
							serviciosString = serviciosString + strElement + " ";
						}else{
							serviciosString = serviciosString + "0 ";
						}
					}
					if (serviciosString.charAt(serviciosString.length()-1) == ' '){
						serviciosString = serviciosString.substring(0, serviciosString.length() - 1);
					}
					
					deptoToUpdate.setGps(serviciosString);
				}
			}
			
			CardsHistory cardHistory = new CardsHistory();
//			cardHistory.setUser(usuarioSession);
			cardHistory.setUsername(usuarioSession.getName()+' '+usuarioSession.getLastName());
			cardHistory.setDate(LocalDateTime.now());
			
			log.debug("DepartmentDTO en updConfigDepartment: "+deptoToUpdate.toString());
			
//			Departments entityDepartment = this.departmentsMapper.toEntity(deptoToUpdate);
			
//			this.updDeparmentsConstraints(entityDepartment,listaIdCard, modificadoGps, cardHistory);
			this.updDeparmentsConstraints(deptoToUpdate, listaIdCard, modificadoGps, cardHistory);
			model.put("success", true);
			
			
			log.info("Registro en historial departamento de cambio de configuracion de departamento y/o vehiculos.");
			DepartmentHistory dHistory= new DepartmentHistory();
			if ("true".equals(validacionGps) && modificadoGps == true){
				if (modificadoGps){
					dHistory.setAction("Cambio de configuración: Activación Gps");
					dHistory.setActionType(ClientConstants.ACTIVACION_GPS);
				}
			}else{
				if ("false".equals(validacionGps) && modificadoGps == false){
					dHistory.setAction("Cambio de configuración");
					dHistory.setActionType(ClientConstants.CAMBIO_CONFIGURACION);
				}else if ("false".equals(validacionGps) && modificadoGps == true){
					dHistory.setAction("Cambio de configuración: Desactivación Gps");
					dHistory.setActionType(ClientConstants.CAMBIO_CONFIGURACION);
				}else{
					dHistory.setAction("Cambio de configuración");
					dHistory.setActionType(ClientConstants.CAMBIO_CONFIGURACION);
				}
			}
			dHistory.setIdDepartment(deptoToUpdate.getIdDepartment());
			dHistory.setIdUser(usuarioSession.getIdUser());
			dHistory.setUsername(usuarioSession.getName()+' '+usuarioSession.getLastName());
			dHistory.setDate(LocalDateTime.now());
			this.departmentHistoryRepoJPA.save(dHistory);
				
			/*Se agrega registro de auditoría para actualización configuración departamento*/
			
			log.info("Registro en auditoria de cambio de configuracion de departamento y/o vehiculos.");
			Audits record= new Audits();
			record.setInsertLogin(usuarioSession.getUsername());
			record.setAction("Upd");
			record.setInsertName(usuarioSession.getName());
			record.setDate(LocalDateTime.now());

			if(!"".equals(listaIdCard)) {
				record.setDescription("Actualización configuración departamento ID " + idDepto+" y la(s) tarjeta(s) id: "+listaIdCard);
			}else {
				record.setDescription("Actualización configuración departamento ID " + idDepto);
			}

			record.setSystem(params.getPortalSession());
				
			try {
				this.auditsRepoJPA.save(record);
			} catch (Exception e) {
				log.error("Ha ocurrido un error al ingresar el registro de auditoría para actualización configuración departamento", e);
			}
				
		} catch(Exception e){
			log.error("Error al actualizar configuracion departamento"+ e.getMessage());
			response.setObservation(Optional.of("Ocurrio un error al ejecutar el servicio"));
			return Optional.of(response);
		}

		response.setObservation(Optional.of("Actualizacion realizada con exito"));
		response.setStatusCode(Optional.of(HttpStatus.OK.value()));
		return Optional.of(response);

	}

	@Override
	public Boolean updateServicesGpsDepartment(Long idDepto, String[] servicio)
			throws DepartmentException, ClientException, VehicleException {
		Departments entityDepartment = new Departments();
		Boolean modificado = false;
			entityDepartment = this.departmentRepoJPA.findByIdDepartment(idDepto); 
			
			
			String serviciosString = "";
			for(String strElement : servicio){
				if (strElement != null){
					serviciosString = serviciosString + strElement + " ";
				}else{
					serviciosString = serviciosString + "0 ";
				}
			}
			if (serviciosString.charAt(serviciosString.length()-1) == ' '){
				serviciosString = serviciosString.substring(0, serviciosString.length() - 1);
			}
			
			if (!entityDepartment.getGps().equals(serviciosString)){
				entityDepartment.setGps(serviciosString);
				try {
					this.departmentRepoJPA.save(entityDepartment);
				} catch (Exception e) {
					log.error("Ha ocurrido un error al actualizar el departamento " + e);
					throw new DepartmentException("Ha ocurrido un error al actualizar el departamento " + e.getMessage());
				}
				List<String> estados = new ArrayList<>();
				estados.add("A");
				estados.add("I");
				List<Vehicles> listaVehiculos = this.vehiclesRepoJPA.findByDepartmentIdDepartmentAndVehicleStatusIn(idDepto,estados);
				
				for(Vehicles vehiculo: listaVehiculos){
					vehiculo.setGps(serviciosString);
					this.vehiclesRepoJPA.save(vehiculo);
				}
				
				
				modificado = true;
			}
			
			return modificado;
	}

	@Override
	public void updDeparmentsConstraints(Departments dto, String tarjetas, Boolean modificadoGps, CardsHistory cardHistory) throws Exception {
		if(dto==null || tarjetas==null){
			log.error("Parametro de entrada objeto departamento es nulo");
			throw new IllegalArgumentException("Parametro de entrada objeto departamento es nulo");
		}

		try{
			// Eliminar las restricciones por estación si la opción es Todas
			if( ClientConstants.DOCUMENT_TYPE_TICKET.equals(dto.getDepartmentStatus()) ) {
				log.info("Eliminar todas las restricciones de estaciones ya que se selecciona opción Todas");
				//				this.deleteStationcustomization(dto.getIdDepartment());
				this.stationDepartmentConstraintsRepoJPA.deleteByIdDepartment(dto.getIdDepartment());
			}

			// Validar si se ha modificado el plafond de la tarjeta para dejar las SAF respectivas
			Departments depto = this.departmentRepoJPA.findByIdDepartment( dto.getIdDepartment() );
			boolean enviarSafVehicleCard = false;

			if( !Functions.hasEmptyOrNull(depto) && !depto.getRestrDailyMaxQuantity().equals(dto.getRestrDailyMaxQuantity()) )
				enviarSafVehicleCard = true;

			// 1. Actualizar configuraciones en la base de dato
			log.info("Actualizar departamento con restricciones " + dto.getIdDepartment());
			log.debug(""+dto);
			dto.setDepartmentStatus(null); //Borrammos el contenido temporal para que no se modifique el real
			this.departmentRepoJPA.save(dto);

			log.info("Recuperar las tarjetas para el departamento " + dto.getIdDepartment());
			GetCardsByDepartmentRequestDTO criterioCardsByDepartment = new GetCardsByDepartmentRequestDTO();
			criterioCardsByDepartment.setIdDepartment(dto.getIdDepartment());
			//			List<CardDTO> lista = this.clientsMgr.getCardsByDepartment(dto.getIdDepartment(), false);
			List<CardDTO> lista = this.cardRepoExtended.getCardsByDepartment(criterioCardsByDepartment, null);
			log.debug(""+lista);

			log.info("Se deben actualizar los registros de las tarjetas del departamento que tengan tipo de restriccion D y enviar a Orpak.");
			/*primero se actualiza la configuración de las tarjetas que ya tienen  restricción por departamento*/
			List<CardDTO> listaCardDept = this.cardRepoExtended.getCardsByDepartment(criterioCardsByDepartment, null);
			//Busco las estaciones del departamento
			List<String> listado = new ArrayList<>();
//			listado = this.stationDepartmentsService.getListStationConstraintByDept(dto.getIdDepartment());

//			for(CardDTO bucleCard : listaCardDept) {
////				Card entityCard = this.cardRepoJPA.findByIdCard(bucleCard.getIdCard());
//
//				if( ClientConstants.CARD_STATUS_PENDING.equals(entityCard.getCardStatus()) ) {
//					continue;
//				}
//
//				if(ClientConstants.RESTRICTION_TYPE_DEPTO.equals(entityCard.getRestrictionType())){
//					entityCard.setRestrType(dto.getRestrType());
//					entityCard.setRestrDailyMaxLoads(dto.getRestrDailyMaxLoads());
//					entityCard.setRestrDailyMaxQuantity(dto.getRestrDailyMaxQuantity());
//					entityCard.setRestrL(dto.getRestrL());
//					entityCard.setRestrM(dto.getRestrM());
//					entityCard.setRestrX(dto.getRestrX());
//					entityCard.setRestrJ(dto.getRestrJ());
//					entityCard.setRestrV(dto.getRestrV());
//					entityCard.setRestrS(dto.getRestrS());
//					entityCard.setRestrD(dto.getRestrD());
//					entityCard.setRestrAmountMax(dto.getRestrAmountMax());
//					entityCard.setRestrDailyMaxLoads(dto.getRestrDailyMaxLoads());
//					entityCard.setRestrHend(dto.getRestrHend());
//					entityCard.setRestrHini(dto.getRestrHinit());
//					entityCard.setUpddate((LocalDateTime.now()));
//					this.cardRepoJPA.save(entityCard);
//					//					this.deleteStationCardConstraintsCustomization(entityCard.getIdCard());
//					this.stationCardConstraintRepoJPA.deleteByCardIdCard(entityCard.getIdCard());
//
//					for(String station: listado){
//						StationCardConstraintId id = new StationCardConstraintId(entityCard.getIdCard(),station);
//						StationCardConstraint entityStationCard = new StationCardConstraint(id,entityCard,station);
//						this.stationCardConstraintRepoJPA.save(entityStationCard);
//					}
//
//					cardHistory.setCard(entityCard);
//					cardHistory.setAction("Cambio de configuración");
//					cardHistory.setActionType(ClientConstants.CAMBIO_CONFIGURACION);
//					this.cardsHistoryRepoJPA.save(cardHistory);
//
//					// Enviar configuraciones a orpak.
//					// guardar n mensaje en SAF para actualizar todas las restricciones de las tarjetas
//					if (!Functions.hasEmptyOrNull(dto.getGps())) {
//						Vehicles vehiculos = this.vehicleService.getVehiclesByIdCard(entityCard.getIdCard());
//						String serviciosGps = vehiculos.getGps();
//						String gpsFraccionado = serviciosGps.substring(ClientConstants.SERVICES_GPS_POSITION_VALID+1, serviciosGps.length());
//						vehiculos.setGps(dto.getGps().substring(ClientConstants.SERVICES_GPS_POSITION_VALID, ClientConstants.SERVICES_GPS_POSITION_VALID+1)+gpsFraccionado );
//						this.vehiclesRepoJPA.save(vehiculos);
//						List<String> listadocardcontraints = this.clientService.getListStationConstraintByCard(entityCard.getIdCard());
//						log.info("Depto " + dto.getClient().getIdClient() + " posee los servicios de gps habilitados");
//
//						if ("1".equals(dto.getGps().substring(ClientConstants.SERVICES_GPS_POSITION_VALID, ClientConstants.SERVICES_GPS_POSITION_VALID + 1))) {
//							log.info("Depto con gps activado, se envia UCN con campo data 0000");
//							SafQueue saf = new SafQueue();
//							saf.setIdElement(entityCard.getIdCard()+"");
//							saf.setType(SafConstants.SAF_UPDATE_CARD_CONSTRAINT);
//							saf.setData(OrpakWSConstants.ORPAK_WS_CARD_CONSTRAINT_NULL_STATIONS);
//							this.safQueueRepoJPA.save(saf);
//						} else {
//							log.info("Depto tuvo habilitado servicios gps, se manda desactivado en la saf - campo data null");
//							SafQueue saf = new SafQueue();
//							saf.setIdElement(entityCard.getIdCard()+"");
//							saf.setType(SafConstants.SAF_UPDATE_CARD_CONSTRAINT);
//							saf.setData(null);
//							this.safQueueRepoJPA.save(saf);
//						}
//
//					} else {
//						SafQueue saf = new SafQueue();
//						saf.setIdElement(entityCard.getIdCard()+"");
//						saf.setType(SafConstants.SAF_UPDATE_CARD_CONSTRAINT);
//						saf.setData(null);
//						this.safQueueRepoJPA.save(saf);
//					}
//
//					if( enviarSafVehicleCard ) {
//						log.info("Recuperar vehiculo para la tarjeta " + entityCard.getIdCard());
//						Optional<Vehicles> optionalEntityVehicle = this.vehiclesRepoJPA.findById(entityCard.getIdCard());
//						if(optionalEntityVehicle.isEmpty())
//							throw new Exception("Vehiculo no encontrado");
//						Vehicles vehicle = optionalEntityVehicle.get();
//						log.debug(""+vehicle);
//						log.info("Se envía SAF para actualizar vehiculo por modificación de plafond " + vehicle.getIdVehicle());
//						SafQueue saf = new SafQueue();
//						saf.setIdElement(vehicle.getIdVehicle()+"");
//						saf.setType(SafConstants.SAF_UPDATE_CARD_NUMBER);
//						saf.setData(null);
//						this.safQueueRepoJPA.save(saf);
//					}
//
//				} else {
//					if(!Functions.hasEmptyOrNull(dto.getGps())) {
//						Vehicles vehiculos = this.vehicleService.getVehiclesByIdCard(entityCard.getIdCard());
//						String serviciosGps = vehiculos.getGps();
//						String gpsFraccionado = serviciosGps.substring(ClientConstants.SERVICES_GPS_POSITION_VALID+1, serviciosGps.length());
//						vehiculos.setGps(dto.getGps().substring(ClientConstants.SERVICES_GPS_POSITION_VALID, ClientConstants.SERVICES_GPS_POSITION_VALID+1)+gpsFraccionado );
//						log.info("Actualizo vehiculo " + vehiculos);
//						this.vehiclesRepoJPA.save(vehiculos);
//
//						if ("1".equals(dto.getGps().substring(ClientConstants.SERVICES_GPS_POSITION_VALID, ClientConstants.SERVICES_GPS_POSITION_VALID+1))) {
//							//Ya tiene las estaciones guardadas al tener type balance por tarjeta, sólo se
//							//envía el cardContraint.
//							log.info("Depto con gps activado, se envia UCN con campo data 0000");
//							SafQueue saf = new SafQueue();
//							saf.setIdElement(entityCard.getIdCard()+"");
//							saf.setType(SafConstants.SAF_UPDATE_CARD_CONSTRAINT);
//							saf.setData(OrpakWSConstants.ORPAK_WS_CARD_CONSTRAINT_NULL_STATIONS);
//							this.safQueueRepoJPA.save(saf);
//						} else {
//							log.info("Depto tuvo habilitado servicios gps, se manda desactivado en la saf - campo data null");
//							SafQueue saf = new SafQueue();
//							saf.setIdElement(entityCard.getIdCard()+"");
//							saf.setType(SafConstants.SAF_UPDATE_CARD_CONSTRAINT);
//							saf.setData(null);
//							this.safQueueRepoJPA.save(saf);
//
//							if (!listado.isEmpty()){
//								log.info("Se elimina la estación 0 de la tarjeta " + entityCard.getIdCard());
//								StationCardConstraintId idStationCardConstraint = new StationCardConstraintId(entityCard.getIdCard(),"0");
//								this.stationCardConstraintRepoJPA.deleteById(idStationCardConstraint);
//							}
//
//						}
//					}
//				}
//			}

			/*Ahora se les cambia la configuración a las tarjetas que el cliente selecciono*/
			/* se separan las tarjetas*/
//			String [] splitTarjetas = tarjetas.split(",");
//			log.debug("Tarjetas seleccionadas para cambiar configuración a nivel de departamento: "+tarjetas);
//
//			for (int i=0; i<splitTarjetas.length; i++) {
//				if(!"".equals(splitTarjetas[i])) {
//					//					CardDTO cardDTO = this.clientsMgr.getCardById(Long.parseLong(splitTarjetas[i]));
//					Card card = this.cardRepoJPA.findByIdCard(Long.parseLong(splitTarjetas[i]));
//
//					if( ClientConstants.CARD_STATUS_PENDING.equals(card.getCardStatus()) ) {
//						continue;
//					}
//
//					card.setRestrictionType(ClientConstants.RESTRICTION_TYPE_DEPTO);
//					card.setRestrType(dto.getRestrType());
//					card.setRestrDailyMaxLoads(dto.getRestrDailyMaxLoads());
//					card.setRestrDailyMaxQuantity(dto.getRestrDailyMaxQuantity());
//					card.setRestrL(dto.getRestrL());
//					card.setRestrM(dto.getRestrM());
//					card.setRestrX(dto.getRestrX());
//					card.setRestrJ(dto.getRestrJ());
//					card.setRestrV(dto.getRestrV());
//					card.setRestrS(dto.getRestrS());
//					card.setRestrD(dto.getRestrD());
//					card.setRestrAmountMax(dto.getRestrAmountMax());
//					card.setRestrDailyMaxLoads(dto.getRestrDailyMaxLoads());
//					card.setRestrHend(dto.getRestrHend());
//					card.setRestrHini(dto.getRestrHinit());
//					card.setUpddate((LocalDateTime.now()));
//					this.cardRepoJPA.save(card);
//					//						this.deleteStationCardConstraintsCustomization(cardDTO.getIdCard());
//					this.stationCardConstraintRepoJPA.deleteByCardIdCard(card.getIdCard());
//
//					for(String station: listado){
//						StationCardConstraintId id = new StationCardConstraintId(card.getIdCard(),station);
//						StationCardConstraint entityStationCard = new StationCardConstraint(id,card,station);
//						this.stationCardConstraintRepoJPA.save(entityStationCard);
//					}
//
//					// Enviar configuraciones a orpak.
//					// guardar n mensaje en SAF para actualizar todas las restricciones de las tarjetas
//					if( enviarSafVehicleCard ) {
//						log.info("Recuperar vehiculo para la tarjeta " + card.getIdCard());
//						Vehicles vehicle = this.vehicleService.getVehiclesByIdCard(card.getIdCard());
//						log.debug(""+vehicle);
//						log.info("Se envía SAF para actualizar vehiculo por modificación de plafond " + vehicle.getIdVehicle());
//						//							this.safService.pushMessageUpdateCardNumber(vehicle.getIdVehicle(), null);
//						SafQueue saf = new SafQueue();
//						saf.setIdElement(vehicle.getIdVehicle()+"");
//						saf.setType(SafConstants.SAF_UPDATE_CARD_NUMBER);
//						saf.setData(null);
//						this.safQueueRepoJPA.save(saf);
//					}
//
//					cardHistory.setCard(card);
//					cardHistory.setAction("Cambio de configuración");
//					cardHistory.setActionType(ClientConstants.CAMBIO_CONFIGURACION);
//					this.cardsHistoryRepoJPA.save(cardHistory);
//					SafQueue saf = new SafQueue();
//					saf.setIdElement(card.getIdCard()+"");
//					saf.setType(SafConstants.SAF_UPDATE_CARD_CONSTRAINT);
//					saf.setData(null);
//					this.safQueueRepoJPA.save(saf);
//				}
//			}
		}catch(Exception e){
			log.error("Error al actualizar las configuraciones para el departamento: " + dto.getIdDepartment()+" mensaje:" + e.getMessage());
			throw new Exception("Problemas al actualizar configuraciones : "+ e.getMessage());
		}
	}

	@Override
	public Optional<ListDeptHistoryJsonCtrlResultDTO> ListDeptHistoryJsonCtrl(ListDeptHistoryJsonCtrlRequestDTO params,
			PagingSortDTO sort, PagingInitDTO paging, Locale locale) {
		
		 	log.info("ListDeptHistoryJsonCtrl");
		 	ListDeptHistoryJsonCtrlResultDTO responseService = new ListDeptHistoryJsonCtrlResultDTO();
		 	List<DepartmentHistory> listaHistoryDepartments = new ArrayList<>();
		 	Long count = 0L;
			
			if (!Functions.hasEmptyOrNull(params.getDateFirst()))
				params.setDateFirstFormatted(params.getDateFirst().atStartOfDay());

			if (!Functions.hasEmptyOrNull(params.getDateEnd()))
				params.setDateEndFormatted(params.getDateEnd().atTime(LocalTime.MAX));
			
			try{
				
				Pageable pages = Functions.getPageable(sort, paging);
				
				listaHistoryDepartments= this.departmentHistoryRepoJPA.getHistoryDepartmentcriterio(params, pages);
				log.info("(BBR) Listado Historia departamento :" + listaHistoryDepartments);
				
				count = this.departmentHistoryRepoJPA.countHistoryDepartmentcriterio(params);
				
			}catch(Exception e){
				log.error("(BBR) Error al obtener listado de la historia del departamento" + e.getMessage());
				responseService.setObservation(Optional.of("Error al tratar de obtener el listado"));
				return Optional.of(responseService);
			}
			
		List<DepartmentHistoryDTO> listaDTO = null;// DepartmentHistoryMapper.toDTOList(listaHistoryDepartments);
			
		responseService.setCountListaDepartmentHistory(Optional.of(count));	
		responseService.setListaDepartmentHistory(Optional.of(listaDTO));
		responseService.setPagingData(Optional.of(PagingDataDTO.fromPagingInit(paging)));
		responseService.setSort(sort != null ? Optional.of(sort) : null);
		
		return Optional.of(responseService);
	}

	@Override
	public Optional<ListMovimientosDeptoCtrlResultDTO> ListMovimientosDeptoCtrl(
			ListMovimientosDeptoCtrlRequestDTO params, PagingSortDTO sort, PagingInitDTO paging, Locale locale) {
		
		log.info("Inicio ListMovimientosDeptoCtrl");
		
		ListMovimientosDeptoCtrlResultDTO responseService = new ListMovimientosDeptoCtrlResultDTO();
		Long totaltmp = 0L;
		List<DepartmentMovementDTO> listadoMovimientosDepto = null;
		
		if (!Functions.hasEmptyOrNull(params.getDateFirst()))
			params.setDateFirstFormatted(params.getDateFirst().atStartOfDay());

		if (!Functions.hasEmptyOrNull(params.getDateEnd()))
			params.setDateEndFormatted(params.getDateEnd().atTime(LocalTime.MAX));
		
		Pageable pages = Functions.getPageable(sort, paging);
		
	   try {
		   log.info("Inicio consulta lista movimientos depto");
		   listadoMovimientosDepto= this.getListDepartmentMovement(params, pages);
		   log.info("Termino consulta lista movimientos depto");
		   log.info(listadoMovimientosDepto.toString());

		   
		   
		   log.info("Inicio consulta lista movimientos depto count");
		   totaltmp = this.departmentsRepoExtended.countListDepartmentMovement(params);
		   log.info("Termino consulta lista movimientos depto count");
	} catch (Exception e) {
		log.error(e.getMessage());
	}
	   log.info("Termino ListMovimientosDeptoCtrl");
	   
	   responseService.setCount(Optional.of(totaltmp));
	   responseService.setListadoMovimientosDepto(Optional.of(listadoMovimientosDepto));
	   responseService.setPagingData(Optional.of(PagingDataDTO.fromPagingInit(paging)));
	   responseService.setSort(sort != null ? Optional.of(sort) : null);
		
		return Optional.of(responseService);
	}

	@Override
	public List<DepartmentMovementDTO> getListDepartmentMovement(ListMovimientosDeptoCtrlRequestDTO params, Pageable pages) throws Exception {

		if(Functions.hasEmptyOrNull(params)){
			log.error("Parametro de entrada DepartmentMovementCriterioDTO es nulo");
			throw new IllegalArgumentException("Parametro de entrada DepartmentMovementCriterioDTO nulo");
		}
		
		List<DepartmentMovementDTO> listaMov = new ArrayList<DepartmentMovementDTO>();
		listaMov = this.departmentsRepoExtended.getListDepartmentMovement(params, pages);
		
		for (DepartmentMovementDTO movimiento: listaMov){

			if ("Transferencia Deptos".equals(movimiento.getMovement()) || "Devolución Deptos".equals(movimiento.getMovement()) || "Transferencia".equals(movimiento.getMovement()) && movimiento.getIdRef() != null){
				try {
					Departments entityDepto = this.departmentRepoJPA.findByIdDepartment(movimiento.getIdRef());
					movimiento.setReferencia(entityDepto.getName());
				} catch (Exception e) {
					log.error("Problemas al obtener el depto para transferencia: "+ movimiento.getIdRef());
					throw new DepartmentException("Problemas al obtener el depto para transferencia: "+ movimiento.getIdRef());
				}
			}
			if ("Devolución".equals(movimiento.getMovement()) && movimiento.getIdRef() != null){
				try {
					//CardDTO tarjeta = this.getCardById(movimiento.getIdRef());
					Optional<Vehicles> optionalVehiculo = this.vehiclesRepoJPA.findById(movimiento.getIdRef());
					Vehicles vehiculo = optionalVehiculo.get();
					movimiento.setReferencia(vehiculo.getPlate());
				} catch (Exception e) {
					log.error("Problemas al obtener la tarjeta para transferencia: "+ movimiento.getIdRef());
					throw new DepartmentException("Problemas al obtener la tarjeta para transferencia: "+ movimiento.getIdRef());
				}
			}
			
//			if ("Asignación".equals(movimiento.getMovement()) && movimiento.getIdRef() != null){
//				try {
//					Card card = this.cardRepoJPA.findByIdCard(movimiento.getIdRef());
//					VehicleCard	entityVehicleCard = this.vehicleCardRepoJPA.findByCardIdCard(card.getIdCard());
//					Optional<Vehicles> vehicle = this.vehiclesRepoJPA.findById(entityVehicleCard.getCard().getIdCard());
//					Vehicles entityVehicle = vehicle.get();
//					movimiento.setReferencia(entityVehicle.getPlate());
//					
//				} catch (Exception e) {
//					log.error("Problemas al obtener la tarjeta para transferencia: "+ movimiento.getIdRef());
//					throw new Exception("Problemas al obtener la tarjeta para transferencia: "+ movimiento.getIdRef());
//				}
//			}
//			
//			if (movimiento.getMovement().contains("Carga") && movimiento.getProductName() != null) {
//				if (ClientConstants.PRODUCT_CODE_DIESELAD.equals(movimiento.getProductName())) {
//					movimiento.setProductCode(ClientConstants.PRODUCT_CODE_DIESELAD);
//				}else if (movimiento.getProductName().contains("Diesel")){
//					movimiento.setProductCode(ClientConstants.PRODUCT_CODE_DEFAULT);
//				}
//			}
			
			
		}
		return listaMov;
	}

	@Override
	public Optional<ListConstraintsByDepartmentJsonCtrlResultDTO> ListConstraintsByDepartmentJsonCtrl(
			ListConstraintsByDepartmentJsonCtrlRequestDTO params, PagingSortDTO sort, PagingInitDTO paging, Locale locale) {
		
		ListConstraintsByDepartmentJsonCtrlResultDTO serviceResponse = new ListConstraintsByDepartmentJsonCtrlResultDTO();
		
		Optional<Clients> optionalCliente = this.clientRepoJPA.findById(params.getIdClient());
		
		if(optionalCliente.isEmpty()) {
			serviceResponse.setObservation(Optional.of("Ingresar un id de cliente valido"));
			return Optional.of(serviceResponse);
		}
		
		Clients cliente = optionalCliente.get();
		
		try {
			Long idDepartment = params.getIdDepartment();
			String zone = params.getZone();
			String region = params.getRegion();
			String area = params.getArea();
			String nivel = params.getNivel();
			Boolean controlPass = params.getControlpass();
			
			if(!"".equals(nivel)) {
				if("actuales".equals(nivel)){
					if(idDepartment == null || idDepartment == 0){
						serviceResponse.setObservation(Optional.of("mensaje Error: Para consultar por las estaciones asignadas a un departamento, debe especificar el ID de ese departamento."));
						return Optional.of(serviceResponse);
					}
//						model.put("ListadoConstraints", clientService.getConstraintsByDepartment(idDepartment,cliente.getClientType(), cliente.getIdClient()));
						StationClientsRequestDTO paramsConstraintsByDepartment = new StationClientsRequestDTO();
						paramsConstraintsByDepartment.setIdDepartment(idDepartment);
						paramsConstraintsByDepartment.setClientType(cliente.getClientType());
						paramsConstraintsByDepartment.setIdClient(cliente.getIdClient());
						List<StationsDTO> listadoConstraints = this.stationDepartmentsRepoExtend.getConstraintsByDepartment(paramsConstraintsByDepartment);
						serviceResponse.setListadoConstraints(Optional.of(listadoConstraints));
						
				}if("zone".equals(nivel)){
//					model.put("listadoZonas",clientService.getAllActiveZones());
					List<Zone> listadoZonasEntities = this.zoneRepoJPA.findByActive("A");
					List<ZoneDTO> listadoZonas = null;
					if(!Functions.hasEmptyOrNull(listadoZonasEntities))
						listadoZonas = listadoZonasEntities.stream().map(this::ZoneEntityToDTO).collect(Collectors.toList());
					serviceResponse.setListadoZonas(Optional.of(listadoZonas));
				}else if("region".equals(nivel)){
//					List<RegionDTO> listadoRegiones = this.clientService.getRegionsByStationsDepartments(idDepartment,cliente.getClientType(),controlPass, cliente.getIdClient());
					RegionsByStationDepartmentRequestDTO paramRegionsStationDepartment= new RegionsByStationDepartmentRequestDTO(idDepartment,cliente.getIdClient(),cliente.getClientType(),controlPass);
					List<RegionDTO> listadoRegiones = this.stationDepartmentsRepoExtend.getRegionsByStationsDepartments(paramRegionsStationDepartment);
					serviceResponse.setListadoRegiones(Optional.of(listadoRegiones));				
				}else if("area".equals(nivel)){
					List<AreaDTO> listadoAreas = new ArrayList<AreaDTO>();
					//si no se pasa una region se envia una lista vacia
					if(region != null && !"".equals(region)){
						List<Long> regiones = new ArrayList<Long>(); 
						
						for(String reg : region.split(",")){
							if(reg.matches("[0-9]+")){
								regiones.add(Long.valueOf(reg));
							}else{
								throw new InvalidParameterException("El código de región debe ser un valor numérico.");
							}
						}
						
						AreaByStationsDepartmentRequestDTO paramsAreaStation = new AreaByStationsDepartmentRequestDTO();
						paramsAreaStation.setRegiones(regiones);
						paramsAreaStation.setIdDepartment(idDepartment);
						paramsAreaStation.setClientType(cliente.getClientType());
						paramsAreaStation.setControlPass(controlPass);
						paramsAreaStation.setIdClient(cliente.getIdClient());
						listadoAreas = this.stationDepartmentsRepoExtend.getAreaByStationsDepartments(paramsAreaStation);
								
						}
					serviceResponse.setListadoAreas(Optional.of(listadoAreas));
				}else if("station".equals(nivel)){
					List<Long> zonas = null; 
					List<Long> regiones = null; 
					List<Long> areas = null; 
					
					if(zone != null && !"".equals(zone)){
						zonas = new ArrayList<Long>();
						for(String zona : zone.split(",")){
							if(zona.matches("[0-9]+")){
								zonas.add(Long.valueOf(zona));
							}else{
								throw new InvalidParameterException("El código de zona debe ser un valor numérico.");
							}
						}
					}
					
					if(region != null && !"".equals(region)){
						regiones = new ArrayList<Long>();
						for(String reg : region.split(",")){
							if(reg.matches("[0-9]+")){
								regiones.add(Long.valueOf(reg));
							}else{
								throw new InvalidParameterException("El código de región debe ser un valor numérico.");
							}
						}
					}
					
					if(area != null && !"".equals(area)){
						areas = new ArrayList<Long>();
						for(String are : area.split(",")){
							if(are.matches("[0-9]+")){
								areas.add(Long.valueOf(are));
							}else{
								throw new InvalidParameterException("El código de area debe ser un valor numérico.");
							}
						}
					}
					
					StationsByCriteriaForDepartmentRequestDTO paramsStationCriteriaDepartment = new StationsByCriteriaForDepartmentRequestDTO();
					paramsStationCriteriaDepartment.setZonas(zonas);
					paramsStationCriteriaDepartment.setRegiones(regiones);
					paramsStationCriteriaDepartment.setAreas(areas);
					paramsStationCriteriaDepartment.setClientType(cliente.getClientType());
					paramsStationCriteriaDepartment.setIdDepartment(idDepartment);
					paramsStationCriteriaDepartment.setControlPass(controlPass);
					paramsStationCriteriaDepartment.setIdClient(cliente.getIdClient());
					
					List<StationsDTO> listadoStations = this.stationDepartmentsRepoExtend.getStationsByCriteriaForDepartment(paramsStationCriteriaDepartment);
					serviceResponse.setListadoStations(Optional.of(listadoStations));
				}
			}
		} catch (Exception e) {
			log.error("Error al obtener los datos: "+ e.getMessage());
		}
		
		return Optional.of(serviceResponse);
	}
	
	private ZoneDTO ZoneEntityToDTO(Zone entity) {
		if(Functions.hasEmptyOrNull(entity)) return null;
		return new ZoneDTO(entity.getIdZone(), entity.getName(), entity.getActive());
	}
	
	@Override
	public Optional<DeleteDeptoClientJsonResultDTO> DeleteDeptoClientJson(DeleteDeptoClientJsonRequestDTO params,
			PagingSortDTO sort, PagingInitDTO paging, Locale locale) throws ClientException {
		DeleteDeptoClientJsonResultDTO response = new DeleteDeptoClientJsonResultDTO();

		log.info("****************** DeleteDeptoClientJson SERVICE ******************".toUpperCase());

//		Map<String, Object> model = new HashMap<String, Object>();

		DepartmentDTO depto = new DepartmentDTO();

		//Se obtiene id de usuario
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		UsuarioAcegi usuario = (UsuarioAcegi) auth.getPrincipal();
		
		if (Functions.hasEmptyOrNull(params.getIdDepto())) {
			log.error("Los parametros idDepto no pueden ser nulos o vacios.");
			throw new IllegalArgumentException("Los parametros idDepto no pueden ser nulos o vacios.");
		}
		
		if (Functions.hasEmptyOrNull(params.getUpiSession(), params.getRutUsuarioSession(), params.getClientType())) {
			log.error("Los parametros upiSession, rutUsuarioSession y clientType no pueden ser nulos o vacios.");
			throw new IllegalArgumentException("Los parametros upiSession, rutUsuarioSession y clientType no pueden ser nulos o vacios.");
		}
		
		// ****** USUARIO Y CLIENTE SESSION ******
		ClientsUsersRel clientUserRel = this.clientsUsersRelRepoJPA.findByClientUpiAndUserRutAndClientClientType(
				params.getUpiSession(), params.getRutUsuarioSession(), params.getClientType());

		if (Functions.hasEmptyOrNull(clientUserRel)) {
			log.error("No se encontro relacion para el usuario " + params.getRutUsuarioSession() + " y cliente "
					+ params.getClientType() + " " + params.getUpiSession());
			throw new ClientException("No se encontro relacion para el usuario " + params.getRutUsuarioSession()
					+ " y cliente " + params.getClientType() + " " + params.getUpiSession());
		}

		Users usuarioSession = clientUserRel.getUser();
		Clients clienteSession = clientUserRel.getClient();
		// ****** USUARIO Y CLIENTE SESSION FIN ******
		
		//Se obtienen variables
//		Long idDepto= ServletRequestUtils.getLongParameter(arg0, "idDepto", 0);
//		if(idDepto != null){
//			depto.setIdDepartment(idDepto);
//			depto.setDepartmentStatus(Constantes.STATUS_ELIMINADO);
//		}

		if (Functions.hasEmptyOrNull(params.getIdDepto())) {
			log.error("Los parametros idDepto no pueden ser nulos o vacios.");
			throw new IllegalArgumentException("Los parametros idDepto no pueden ser nulos o vacios.");
		}

		depto.setIdDepartment(params.getIdDepto());
		depto.setDepartmentStatus(Constantes.STATUS_ELIMINADO);

//		HttpSession session = arg0.getSession();
//		RegisterClientDTO cliente = (RegisterClientDTO) session.getAttribute("cliente");
		depto.setIdClient(clienteSession.getIdClient());

		try{
			//List<MonitorCardDTO> listaTarjetas=this.clientService.getListCardsByCriterio(criterio);
			if(ClientConstants.CLIENT_SCS.equals(params.getClientType())) {
//				this.clientService.delDepartment(depto,tipoCliente);
				this.delDepartment(depto, params.getClientType());
			} else {
//				this.clientService.delDepartment(depto);
				this.delDepartment(depto);
			}

			//model.put("success", true);

			//historial departamento eliminado
			DepartmentHistory dHistory=  new DepartmentHistory();

			dHistory.setAction("Eliminación");
			dHistory.setActionType(ClientConstants.CAMBIO_CONFIGURACION);
			dHistory.setDate(LocalDateTime.now());
			dHistory.setIdDepartment(depto.getIdDepartment());
			dHistory.setIdUser(usuarioSession.getIdUser());
			dHistory.setUsername(usuarioSession.getName() + ' ' + usuarioSession.getFirstName());

//			this.clientService.addDepartmentHistory(dHistory);
			this.departmentHistoryRepoJPA.save(dHistory);

			try {
				//Se agrega registro de auditoría detallando la eliminación del departamento
				Audits record= new Audits();
				record.setInsertLogin(usuarioSession.getUsername());
				record.setAction("Del");
				record.setInsertName(usuarioSession.getName());
				record.setDate(LocalDateTime.now());
				record.setDescription("Eliminación departamento ID " + depto.getIdDepartment() + ", cliente ID " + depto.getIdClient());
				record.setSystem(params.getPortal());
				//				auditService.addAudits(record);
				this.auditsRepoJPA.save(record);
			} catch (Exception e) {
				log.error("Ha ocurrido un error al ingresar el registro de auditoría para la eliminación del departamento.", e);
			}

		}catch(CreditCardException e){
			log.error("Problemas al Eliminar Departamento "+ e.getMessage());
			////model.put("msg", e.getMessage());
			//model.put("failure", true);
		}catch(DepartmentoUnicoException e){
			log.error("(BBR) Problemas al Eliminar Departamento "+ e.getMessage());
			//model.put("msg", e.getMessage());
			//model.put("failure", true);
		}catch(DeptoTieneSaldoException e){
			log.error("(BBR) Problemas al Eliminar Departamento "+ e.getMessage());
			//model.put("msg", e.getMessage());
			//model.put("failure", true);
		}catch(VehicleException vex) {
			log.error("(BBR) Problemas al Eliminar Departamento "+ vex.getMessage());
			//model.put("msg", "El departamento tiene vehículos asociados");
			//model.put("failure", true);
		}catch(Exception e){
			log.error("(BBR) Problemas al Eliminar Departamento "+ e.getMessage());
			//model.put("msg", "No se puede eliminar el Departamento");
			//model.put("failure", true);
		}
//		return new ModelAndView(view, //model);	

		log.info("****************** DeleteDeptoClientJson SERVICE FIN ******************".toUpperCase());

		response.setPagingData(Optional.of(PagingDataDTO.fromPagingInit(paging)));
		response.setSort(sort != null ? Optional.of(sort) : null);

		return Optional.of(response);
	}
	
	@Override
	public void delDepartment(DepartmentDTO depto, String portal)
			throws OrpakWSExceptions, CreditCardException, SafException, ClientNotFoundException,
			DeptoTieneSaldoException, DepartmentException, DepartmentoUnicoException, VehicleException, ClientException {
		
		List<Vehicles> listVehiculos=null;
		
		//validaciones
		if(depto == null) {
			log.warn("El departamento no puede ser nulo");
			throw new IllegalArgumentException("El departamento no puede ser nulo");
		}
		
		if(portal == null) {
			log.warn("El portal no puede ser nulo");
			throw new IllegalArgumentException("El portal no puede ser nulo");
		}
		
		//Si portal SCS, se validan los vehiculos
		if(ClientConstants.CLIENT_SCS.equals(portal)) {
			try {
//				CriterioVehiculoDTO criterioVehiculo = new CriterioVehiculoDTO();
//				criterioVehiculo.setIdDepartment(depto.getIdDepartment());
//				criterioVehiculo.setVehicleStatus(ClientConstants.VEHICLE_STATUS_ACTIVE);
//				listVehiculos = this.getVehiclesByCriterio(criterioVehiculo);

				VehiclesRequestDTO criterioVehiculo = new VehiclesRequestDTO();
				criterioVehiculo.setIdDepartment(depto.getIdDepartment());
				criterioVehiculo.setVehicleStatus("A");

				log.info("Se obtienen los vehiculos del departamento: "+criterioVehiculo);
				
				Pageable pages = PageRequest.of(0, Integer.MAX_VALUE);

				listVehiculos = this.vehiclesRepoJPA.getVehiclesByCriterio(criterioVehiculo, pages);
				
//				if(listVehiculos != null && !listVehiculos.isEmpty()) {
//					log.warn("Existen vehiculos asociados al departamento a eliminar: "+depto);
//					throw new VehicleException("Existen vehiculos asociados al departamento a eliminar");
//				}
			} catch (Exception e) {
				log.error("ERROR al obtener los vehiculos del departamento "+depto.getIdDepartment()+": "+e.getMessage());
//				throw new VehicleException(e);
			}
		}

		//si no hay vehiculos continuar con las demas validaciones
		try {

			log.info("Se procede a eliminar departamento: "+depto);
			this.delDepartment(depto);
			
		} catch(DepartmentoUnicoException duex) {
			log.error("Departamento unico: "+duex.getMessage());
			throw new DepartmentoUnicoException(duex);
		} catch (DeptoTieneSaldoException dtsex) {
			log.error("Departamento tiene saldo: "+dtsex.getMessage());
			throw new DeptoTieneSaldoException(dtsex);
		} catch (ClientNotFoundException cliex) {
			log.error("Cliente no existe: "+cliex.getMessage());
			throw new ClientNotFoundException(cliex);
		} catch (SafException saex) {
			log.error("Error encolamiento SAF: "+saex.getMessage());
			throw new SafException(saex);
		} catch(CreditCardException cex) {
			log.error("Problema saldo tarjeta: "+cex.getMessage());
			throw new CreditCardException(cex);
		} catch (OrpakWSExceptions oex) {
			log.error("Problema con Orpak: "+oex.getMessage());
			throw new OrpakWSExceptions(oex);
		} catch (DepartmentException dex) {
			log.error("Problema en el departamento: "+dex.getMessage());
			throw new DepartmentException(dex);
		} catch (Exception e) {
			log.error("Ocurrio un problema al intentar eliminar el departamento: "+e.getMessage());
			throw new ClientException(e);
		}
		
	}
	
	@Override
	public void delDepartment(DepartmentDTO depto) throws DepartmentException, OrpakWSExceptions, CreditCardException, SafException, ClientNotFoundException, DeptoTieneSaldoException, DepartmentoUnicoException, ClientException, NuevoMontoException {
		
		if(depto == null){
			log.error("Parametro de entrada deparmentDTO es nulo");
			throw new IllegalArgumentException("Parametro de entrada departmentDTO es nulo");
		}
		
		//Se valida que la empresa tenga al menos un departamento asignado antes de eliminar 
//		DepartmentCriteriaDTO dto = new DepartmentCriteriaDTO();
//		dto.setIdClient(depto.getIdClient());
//		List<DepartmentDTO> listaDepartmentClient= this.clientsMgr.getListDepartmentByClient(dto);
		
		List<Departments> listaDepartmentClient = this.departmentRepoJPA.findByClientIdClient(depto.getIdClient());

		if(listaDepartmentClient.size() == 1){
			log.error("El departamento no puede ser eliminado ya que la empresa no tiene mas departamentos asignados.");
			throw new DepartmentoUnicoException("El departamento no puede ser eliminado ya que la empresa no tiene mas departamentos asignados.");
		}
		
		boolean error = false;
		
//		ClientDTO cliente = new ClientDTO();
//		cliente = this.clientsMgr.getClientById(depto.getIdClient());
		Optional<Clients> clienteOpt = this.clientRepoJPA.findById(depto.getIdClient());
		
		if(clienteOpt.isEmpty()) {
			log.error("No se encontro cliente para el departamento.");
			throw new ClientException("No se encontro cliente para el departamento.");
		}
		
		Clients cliente = clienteOpt.get();
		
		//Se obtiene listado de tarjetas asociadas a departamento
//		List<CardDTO> listCard = this.clientsMgr.getCardsByDepartment(new GetCardsByDepartmentRequestDTO(depto.getIdDepartment(), false));
		Pageable pages = PageRequest.of(0, Integer.MAX_VALUE);
		GetCardsByDepartmentRequestDTO paramsCardsByDepartment = new GetCardsByDepartmentRequestDTO();
		paramsCardsByDepartment.setIdDepartment(depto.getIdDepartment());
		paramsCardsByDepartment.setActDepto(false);
		List<CardDTO> listCard = this.cardRepoExtended.getCardsByDepartment(paramsCardsByDepartment, pages);
		
//		if(ClientConstants.CLIENT_SCS.equals(cliente.getClientType())){
//			if(listCard.size() > 0){
//				//Se recorre listado
//				for(CardDTO card : listCard){
//					try {
//						double balance = this.updQuantityCard( card.getIdCard(), card.getCardNum() );
//						card.setRemainingAmount(BigDecimal.valueOf(balance));
//					} catch (CardException e1) {
//						log.warn("Ha ocurrido un error al consultar el saldo de la tarjeta " + card.getCardNum() + ". Se continua con saldo local." );
//					}
//					
//					try{
//						// Si la tarjeta tiene un monto diferente a cero se debe alertar
//						if(card.getRemainingAmount().compareTo(BigDecimal.ZERO)==1){
//							log.warn("La tarjeta " + card.getCardNum() + " aún tiene saldo disponible " + card.getRemainingAmount() );
//							error = true;
//						}
//					}
//					catch(Exception e){
//						log.error("(BBR) Error al obtener saldo de tarjeta " + card.getCardNum() + " desde Orpak. "+ e.getMessage());
//						error = true;
//					}
//				}
//			}
//		}else{
//			
//			if(listCard.size() > 0){
//				//Se recorre listado
//				for(CardDTO card : listCard){
//					
//					try {
//						long balance = this.updCardBalance( card.getIdCard(), card.getCardNum() );
//						card.setRemainingAmount(BigDecimal.valueOf(balance));
//					} catch (CardException e1) {
//						log.warn("Ha ocurrido un error al consultar el saldo de la tarjeta " + card.getCardNum() + ". Se continua con saldo local." );
//					}
//					
//					try{
//						// Si la tarjeta tiene un monto diferente a cero se debe alertar
//						if(card.getRemainingAmount().compareTo(BigDecimal.ZERO)==1){
//							log.warn("La tarjeta " + card.getCardNum() + " aún tiene saldo disponible " + card.getRemainingAmount() );
//							error = true;
//						}
//					}
//					catch(Exception e){
//						log.error("(BBR) Error al obtener saldo de tarjeta " + card.getCardNum() + " desde Orpak. "+ e.getMessage());
//						error = true;
//					}
//				}
//			}
//		}
		
		// si hubo algún error se debe parar el proceso y alertar al usuario
		if(error == true){
			log.error("Existen tarjetas con saldos mayor a cero");
			throw new CreditCardException("Existen tarjetas con saldos mayor a cero. ");
		}
		
		//Se obtiene type balance del departamento
//		DepartmentDTO depa = this.clientsMgr.getdepartment(depto.getIdDepartment());
		Departments depa = this.departmentRepoJPA.findById(depto.getIdDepartment()).get();
		
		//Consultar saldo de departamento a la base de datos en caso de ser type balance por tarjeta o solicitar a Orpak los saldos
		if(depa.getTypeBalance().equals(ClientConstants.TYPE_BALANCE_DEPTO)){
			try {
				this.updDepartmentBalance( depto.getIdDepartment() );
			} catch (DepartmentException e) {
				log.error("Ha ocurrido un error. " + e.getMessage());
				throw new DepartmentException( "Ha ocurrido un error al recuperar saldos desde Orpak." );
			}
		}
		
//		ProductDepartmentDTO productoDep =new ProductDepartmentDTO();
//		productoDep.setIdDepartment(depto.getIdDepartment());
//		List<ProductDepartmentDTO> listadoProdDep = this.clientsMgr.getProductsDepartmentsByCriteria(productoDep);
		
		ProductsDepartmentsByCriteriaRequestDTO productoDep = new ProductsDepartmentsByCriteriaRequestDTO();
		productoDep.setIdDepartment(depto.getIdDepartment());

//		List<ProductsDepartmentsRel> listadoProdDep = this.productsDepartmentsRelRepoJPA.getProductsDepartmentsByCriteria(productoDep);
		
//		if(listadoProdDep.size() > 0){
//			BigDecimal sumaSaldos = BigDecimal.ZERO;
//			for(ProductsDepartmentsRel proDep : listadoProdDep){
//				int saldo = proDep.getRemainingAmount().compareTo(BigDecimal.ZERO);
//				if(saldo == 1){
//					sumaSaldos = sumaSaldos.add(proDep.getRemainingAmount());
//				}
//			}
//			int sumaSal = sumaSaldos.compareTo(BigDecimal.ZERO);
//			if(sumaSal == 1){
//				log.error("El departamento "+depa.getName()+" no puede ser eliminado porque tiene saldo de "+ sumaSaldos.longValue()+" pesos");
//				throw new DeptoTieneSaldoException("El departamento "+depa.getName()+" no puede ser eliminado porque tiene saldo de "+sumaSaldos.longValue() +" pesos");
//			}
//		}
		
		//Si no hay errores se actualiza status departamento a E 
//		this.clientsMgr.updDeparments(depto); // TODO YCORTES - UTILIZAR SERVICIO QUE ANDRES ESTA HACIENDO

		if(ClientConstants.CLIENT_SCS.equals(cliente.getClientType())){
			//Se envia a orpak a través de SAF para eliminar el departamento
			log.info("Se deja SAF DDF para eliminar departamento litros");
//			this.safService.pushMessageDeleteDepartmentOfFleet(depto.getIdDepartment());
			SafQueue message = new SafQueue();
			message.setIdElement(depto.getIdDepartment()+"");
			message.setType(SafConstants.SAF_DELETE_DEPARTMENT_FLEET);
			message.setStatus(SafConstants.SAF_STATUS_PENDING);
//			message.setNumRetries(0L);
			
//			this.safQueueRepoJPA.save(message);
		}else{
			//Se envia a orpak a través de SAF el cambio de status del departamento
			log.info("Se deja SAF USD para eliminar departamento");
//			this.safService.pushMessageUpdateDepartmentStatus(depto.getIdDepartment());
			SafQueue message = new SafQueue();
			message.setIdElement(depto.getIdDepartment()+"");
			message.setType(SafConstants.SAF_UPDATE_DEPARTMENT_STATUS);
			message.setStatus(SafConstants.SAF_STATUS_PENDING);
//			message.setNumRetries(0L);
//
//			this.safQueueRepoJPA.save(message);
		}
		
		
		if(listCard.size() > 0){
			//Se actualizan las tarjetas asociadas al departamento
//			this.clientsMgr.updateCardByDepartment(depto.getIdDepartment());
			this.cardRepoExtended.updateCardByDepartment(depto.getIdDepartment());

			//Se envia a orpak a través de SAF el cambio de status de cada tarjeta asociada al departamento.
			for(CardDTO card : listCard){
//				this.safService.pushMessageUpdateCardStatus(card.getIdCard());
				SafQueue message = new SafQueue();
				message.setIdElement(card.getIdCard()+"");
				message.setType(SafConstants.SAF_UPDATE_CARD_STATUS);
				message.setStatus(SafConstants.SAF_STATUS_PENDING);
//				message.setNumRetries(0L);
//
//				this.safQueueRepoJPA.save(message);
			}
		}
	}
	
	@Override
	public double updQuantityCard(Long idCard, String cardNumber) throws CardException {

		if( idCard == null && cardNumber == null ) {
			log.error("Parametro de entrada criterio de busqueda es nulo");
			throw new IllegalArgumentException("Parametro de entrada criterio de busqueda es nulo");
		}

//		Optional<Card> cardOpt = this.cardRepoJPA.findById(idCard);
//		if(cardOpt.isEmpty()) {
//			log.error("No se encontro Card para id " + idCard);
//			throw new CardException("No se encontro Card para id " + idCard);
//		}
//		Card card = cardOpt.get();
//
//		GetDebitQuantityCardRequest  requestBalanceCardDTO = new GetDebitQuantityCardRequest();
//		requestBalanceCardDTO.setCardno(cardNumber);
//		GetDebitQuantityCardResponse respuesta = null;
//
//		try {
//			log.info("Consultar saldo a Orpak para tarjeta " + requestBalanceCardDTO);
//			respuesta = this.orpakIntegracionService.getDebitQuantityCardBalance(requestBalanceCardDTO);
//		} catch (OrpakWSExceptions e) {
//			log.error("Problemas al obtener litros tarjeta desde orpak. " + e.getMessage());
//			return card.getRemainingAmount().longValue();
//		}
//
//		log.info("Recuperar tarjeta " + idCard);
////		CardDTO card = this.clientsMgr.getCardById(idCard);
////		log.debug(""+card);
//
//		log.info("Recuperar vehiculo para tarjeta" + idCard);
//		Vehicles vehicle;
//
//		try {
////			vehicle = this.getVehicleByIdCard(idCard);
//			VehicleCard vehicleCard = this.vehicleCardRepoJPA.findByCardIdCard(idCard);
//			vehicle = vehicleCard.getVehicle();
//		} catch (Exception e) {
//			log.error("Ha ocurrido un error al recuperar vehiculo");
//			throw new CardException("Ha ocurrido un error al recuperar vehiculo");
//		}
//
//		log.debug(""+vehicle);
//
//		/*
//		 *  Si el tipo de balance de la tarjeta es a nivel de tarjeta se debe actualizar el saldo a la tarjeta
//		 *  Si el tipo de balance de la tarjeta es a nivel de departament se debe actualizar el saldo al departamento
//		 *  Si el tipo de balance de la tarjeta es a nivel de cliente se debe actualizar el saldo al cliente
//		 *  Además siempre se debe actualizar el saldo por período para el saldo y la cantidad de transacciones de la tarjeta
//		 *  El type balance se encuentra en el depto
//		 */
//		log.info("Recuperar departamento para la tarjeta " + idCard);
//		Departments depto;
//
//		try {
////			depto = this.clientsMgr.getdepartment(vehicle.getIdDepartment() );
//			depto = vehicle.getDepartment();
//		} catch (Exception e) {
//			log.error("Ha ocurrido un error al recuperar departamento");
//			throw new CardException("Ha ocurrido un error al recuperar departamento");
//		}
//
//		// recuperar cliente para saber si es crédito o no 
////		log.info("Recuperar cliente " + card.getIdClient());
////		ClientDTO cliente = this.clientsMgr.getClientById( card.getIdClient() );
////		log.debug( cliente );
//		Clients cliente = card.getClient();
//
//		float remainingAmountAux = respuesta.getFuelQuantity();
//
//		log.info("Type balance para departamento " + depto.getIdDepartment() + "=" + depto.getTypeBalance());
//
//		if( ClientConstants.TYPE_BALANCE_CARD.equals(depto.getTypeBalance()) ) {
//			log.info("typebalance tarjeta -> actualizar saldo de tarjeta");
////			CardDTO cardUpd = new CardDTO();
////			cardUpd.setIdCard(card.getIdCard());
//
////			cardUpd.setRemainingPeriodAmount(BigDecimal.valueOf(remainingAmountAux));
////			cardUpd.setRemainingAmount(BigDecimal.valueOf(remainingAmountAux));
////			cardUpd.setRemainingTrxLoad(Integer.getInteger(String.valueOf(remainingAmountAux)));
////			cardUpd.setUpdDate(LocalDateTime.now());
//
//			card.setRemainingPeriodAmount(BigDecimal.valueOf(remainingAmountAux));
//			card.setRemainingAmount(BigDecimal.valueOf(remainingAmountAux));
//			card.setRemainingTrxLoad(Long.getLong(String.valueOf(remainingAmountAux)));
//			card.setUpddate(LocalDateTime.now());
//
//			log.info("Actualizar tarjeta " + card);
//
////			this.clientsMgr.updCard(cardUpd );
//			if(!Functions.hasEmptyOrNull(card.getIdCard()))
//				this.cardRepoJPA.save(card);
//
//		} else if( ClientConstants.TYPE_BALANCE_DEPTO.equals(depto.getTypeBalance()) ) {
//			log.info("typebalance Depto -> actualizar saldo de tarjeta y departamento");
////			CardDTO cardUpd = new CardDTO();
////			cardUpd.setIdCard(card.getIdCard());
//
////			cardUpd.setRemainingPeriodAmount(BigDecimal.valueOf(remainingAmountAux));
////			cardUpd.setRemainingAmount(BigDecimal.ZERO);
////			cardUpd.setRemainingTrxLoad(BigDecimal.valueOf(remainingAmountAux).longValue());
////			cardUpd.setUpddate(new Date());
//
//			card.setRemainingPeriodAmount(BigDecimal.valueOf(remainingAmountAux));
//			card.setRemainingAmount(BigDecimal.ZERO);
//			card.setRemainingTrxLoad(BigDecimal.valueOf(remainingAmountAux).longValue());
//			card.setUpddate(LocalDateTime.now());
//
////			log.info("Actualizar tarjeta " + cardUpd);
////			this.clientsMgr.updCard(cardUpd );					
//
//			if(!Functions.hasEmptyOrNull(card.getIdCard()))
//				this.cardRepoJPA.save(card);
//
//			ProductDepartmentDTO deptodto = new ProductDepartmentDTO();
//			deptodto.setIdDepartment(depto.getIdDepartment());
//			deptodto.setProductCode(vehicle.getProduct().getProductCode());
//			deptodto.setRemainingAmount(BigDecimal.valueOf(remainingAmountAux));
//			deptodto.setUsername(ClientConstants.USUARIO_CARD_MOVEMENT_CARGA);
//
//			deptodto.setDocumentType(vehicle.getDocumentType());
//
//			try {
//				this.updDepartmentProductAmount(deptodto);
//			} catch (NuevoMontoException e) {
//				log.error("Ha ocurrido un error al grabar departmento");
//				throw new CardException("Ha ocurrido un error al grabar departmento");
//			}
//
//		} else {
//			log.warn("typebalance no identificado.");
//		}

		return (double) 0;//(respuesta.getFuelQuantity());
	}
	
	/*
	 * (non-Javadoc)
	 * @see cl.bbr.enex.client.service.ClientService#updCardBalance(java.lang.Long, java.lang.String)
	 */
	@Override
	public long updCardBalance( Long idCard, String cardNumber )  throws CardException {
		if( idCard == null && cardNumber == null ) {
			log.error("Parametro de entrada criterio de busqueda es nulo");
			throw new IllegalArgumentException("Parametro de entrada criterio de busqueda es nulo");
		}

		RequestBalanceCardDTO  requestBalanceCardDTO= new RequestBalanceCardDTO();
		requestBalanceCardDTO.setIdCard(idCard);
		requestBalanceCardDTO.setCardNumber(cardNumber);
		ResponseBalanceCardDTO respuesta = null;

		// TODO YCORTES - Implementar con Diego
//		try {
//			log.info("Consultar saldo a Orpak para tarjeta " + requestBalanceCardDTO );
//			respuesta = this.orpakWSService.getCardBalance(requestBalanceCardDTO);
//		} catch (OrpakWSExceptions e) {
//			log.error("Problemas al obtener saldo tarjeta desde orpak. " + e.getMessage());
//			//throw new CardException("Problemas al obtener saldo tarjeta desde orpak.", e);
//			CardDTO card = this.getCardById(idCard);
//			return card.getRemainingAmount().longValue();
//
//		} catch (OrpakWSCardInUseExceptions e) {
//			log.error("La tarjeta esta en uso idcard " + idCard);
//			throw new CardException("La tarjeta esta en uso idcard " + idCard);
//		}

		log.info("Recuperar tarjeta " + idCard);
//		CardDTO card = this.clientsMgr.getCardById(idCard);
		Card card = null;// this.cardRepoJPA.findById(idCard).get();
		log.debug(""+card);

		log.info("Recuperar vehiculo para tarjeta" + idCard);
		Vehicles vehicle;

		try {
//			vehicle = this.getVehicleByIdCard(idCard);
			VehicleCard vehicleCard = this.vehicleCardRepoJPA.findByCardIdCard(idCard);
			vehicle = vehicleCard.getVehicle();
		} catch (Exception e1) {
			log.error("Ha ocurrido un error al recuperar vehiculo");
			throw new CardException("Ha ocurrido un error al recuperar vehiculo");
		}

		log.debug(""+vehicle);

		/*
		 *  Si el tipo de balance de la tarjeta es a nivel de tarjeta se debe actualizar el saldo a la tarjeta
		 *  Si el tipo de balance de la tarjeta es a nivel de departament se debe actualizar el saldo al departamento
		 *  Si el tipo de balance de la tarjeta es a nivel de cliente se debe actualizar el saldo al cliente
		 *  Además siempre se debe actualizar el saldo por período para el saldo y la cantidad de transacciones de la tarjeta
		 *  El type balance se encuentra en el depto
		 */
		log.info("Recuperar departamento para la tarjeta " + idCard);
		Departments depto;

		try {
//			depto = this.clientsMgr.getdepartment(vehicle.getIdDepartment() );
			depto = vehicle.getDepartment();
		} catch (Exception e1) {
			log.error("Ha ocurrido un error al recuperar departamento");
			throw new CardException("Ha ocurrido un error al recuperar departamento");
		}

		log.debug(""+depto);		

		// recuperar cliente para saber si es crédito o no 
		log.info("Recuperar cliente " + card.getClient().getIdClient());
//		ClientDTO cliente = this.clientsMgr.getClientById( card.getIdClient() );
		Clients cliente = card.getClient();
		log.info("" + cliente.getIdClient());

		BigDecimal remainingAmountAux = respuesta.getRemainingPeriodAmount();
		if( "T".equals(cliente.getIsCredit()) ) {
			// Realizar la conversón para precio
			try {
				remainingAmountAux = this.getPrecioLitro(vehicle.getProduct().getProductCode(), respuesta.getRemainingPeriodAmount());
			} catch (PriceLiterNotFoundException e) {
				log.warn("Error al convertir cantidad, se continua y se pone valor cero");
				remainingAmountAux = BigDecimal.ZERO;
			}
			log.debug("Cliente crédito saldo en litros " + remainingAmountAux);
		} else {
			log.debug("Cliente prepago saldo en pesos " + remainingAmountAux);
		}

		log.info("Type balance para departamento " + depto.getIdDepartment() + "=" + depto.getTypeBalance());

		if( ClientConstants.TYPE_BALANCE_CARD.equals(depto.getTypeBalance()) ) {
			log.info("typebalance tarjeta -> actualizar saldo de tarjeta");
//			CardDTO cardUpd = new CardDTO();
//			cardUpd.setIdCard(card.getIdCard());
//			cardUpd.setRemainingPeriodAmount(remainingAmountAux);
//			cardUpd.setRemainingAmount(respuesta.getRemainingAmount());
//			cardUpd.setRemainingTrxLoad(respuesta.getRemainingTrxLoad().intValue());
//			cardUpd.setUpdDate(LocalDateTime.now());
//			log.info("Actualizar tarjeta " + cardUpd);

			card.setRemainingPeriodAmount(remainingAmountAux);
			card.setRemainingAmount(respuesta.getRemainingAmount());
			card.setRemainingTrxLoad(respuesta.getRemainingTrxLoad().longValue());
			card.setUpddate(LocalDateTime.now());
			log.info("Actualizar tarjeta " + card);

//			this.clientsMgr.updCard(cardUpd );
//			if(!Functions.hasEmptyOrNull(card.getIdCard()))
				//this.cardRepoJPA.save(card);

		} else if( ClientConstants.TYPE_BALANCE_DEPTO.equals(depto.getTypeBalance()) ) {
			log.info("typebalance Depto -> actualizar saldo de tarjeta y departamento");
//			CardDTO cardUpd = new CardDTO();
//			cardUpd.setIdCard(card.getIdCard());
//			cardUpd.setRemainingPeriodAmount(remainingAmountAux);
//			cardUpd.setRemainingAmount(BigDecimal.ZERO);
//			cardUpd.setRemainingTrxLoad(respuesta.getRemainingTrxLoad().longValue());
//			cardUpd.setUpddate(new Date());
//			log.info("Actualizar tarjeta " + cardUpd);
			
			card.setRemainingPeriodAmount(remainingAmountAux);
			card.setRemainingAmount(BigDecimal.ZERO);
			card.setRemainingTrxLoad(respuesta.getRemainingTrxLoad().longValue());
			card.setUpddate(LocalDateTime.now());
			log.info("Actualizar tarjeta " + card);

////			this.clientsMgr.updCard(cardUpd );
//			if(!Functions.hasEmptyOrNull(card.getIdCard()))
//				this.cardRepoJPA.save(card);

			ProductDepartmentDTO deptodto = new ProductDepartmentDTO();
			deptodto.setIdDepartment(depto.getIdDepartment());
			deptodto.setProductCode(vehicle.getProduct().getProductCode());
			deptodto.setRemainingAmount(respuesta.getRemainingAmount());
//			deptodto.setUsername(ClientConstants.USUARIO_CARD_MOVEMENT_CARGA);

			deptodto.setDocumentType(vehicle.getDocumentType());
			try {
//				this.clientsMgr.updDepartmentProductAmount(deptodto);
				this.updDepartmentProductAmount(deptodto);
			} catch (NuevoMontoException e) {
				log.error("Ha ocurrido un error al grabar departmento");
				throw new CardException("Ha ocurrido un error al grabar departmento");
			}

		} else if( ClientConstants.TYPE_BALANCE_CLIENT.equals(depto.getTypeBalance()) ) {
			log.info("typebalance cliente -> actualizar saldo de tarjeta y cliente");

//			CardDTO cardUpd = new CardDTO();
//			cardUpd.setIdCard(card.getIdCard());
//			cardUpd.setRemainingPeriodAmount(remainingAmountAux);
//			cardUpd.setRemainingAmount(BigDecimal.ZERO);
//			cardUpd.setRemainingTrxLoad(respuesta.getRemainingTrxLoad().longValue());
//			cardUpd.setUpddate(new Date());
//			log.info("Actualizar tarjeta " + cardUpd);
//			this.clientsMgr.updCard(cardUpd );

			card.setRemainingPeriodAmount(remainingAmountAux);
			card.setRemainingAmount(BigDecimal.ZERO);
			card.setRemainingTrxLoad(respuesta.getRemainingTrxLoad().longValue());
			card.setUpddate(LocalDateTime.now());
			log.info("Actualizar tarjeta " + card);
//			this.clientsMgr.updCard(cardUpd );
//			if(!Functions.hasEmptyOrNull(card.getIdCard()))
//				this.cardRepoJPA.save(card);

			RegisterClientDTO clientedto = new RegisterClientDTO();
			clientedto.setIdClient(cliente.getIdClient());
			clientedto.setRemainingAmount(respuesta.getRemainingAmount());
//			clientedto.setPutSaf(false);
			log.info("Actualizar saldo del cliente " + clientedto);
			try {
				this.updClientByID(clientedto);
			} catch (ClientException e) {
				log.error("Ha ocurrido un error al grabar cliente");
				throw new CardException("Ha ocurrido un error al grabar cliente");
			}

		} else {
			log.warn("typebalance no identificado.");
		}

		if( "T".equals(cliente.getIsCredit()) && !ClientConstants.CLIENT_SCI.equals(cliente.getClientType())) {
			return respuesta.getRemainingPeriodAmount().longValue();
		} else {
			return respuesta.getRemainingAmount().longValue();
		}
	}
	
	@Override
	public void updDepartmentBalance( Long idDepartment ) throws DepartmentException, NuevoMontoException {
		try {

			log.info("Recuperar departamento " + idDepartment);
//			DepartmentDTO depto = this.clientsMgr.getdepartment(idDepartment);
//			log.debug(""+depto);
			Optional<Departments> deptoOpt = this.departmentRepoJPA.findById(idDepartment);
			
			if (Functions.hasEmptyOrNull(deptoOpt)) {
				log.error("No se encontro depto para id " + idDepartment);
				throw new IllegalArgumentException("No se encontro depto para id " + idDepartment);
			}
			
			Departments depto = deptoOpt.get();

			if( !ClientConstants.TYPE_BALANCE_DEPTO.equals(depto.getTypeBalance()) ) {
				log.warn("El departamento no tiene type balance a nivel de departamento, se sale del método.");
				return;
			}

			log.info("Obtener saldos para id orpak factura " + depto.getCodeorpakinvoice());
			if( depto.getCodeorpakinvoice() != null ) {

				RequestBalanceDepartmentDTO requestBalanceDepartmentDTO = new RequestBalanceDepartmentDTO();
				requestBalanceDepartmentDTO.setIdOrpakDepartment( depto.getCodeorpakinvoice() );
				requestBalanceDepartmentDTO.setIdClient( depto.getClient().getIdClient() );
				requestBalanceDepartmentDTO.setIdDepartment(idDepartment);

//				RegisterClientDTO cliente = this.clientsMgr.getClient(depto.getIdClient());
				Clients cliente = depto.getClient();

				if(ClientConstants.CLIENT_SCS.equals(cliente.getClientType())){
					requestBalanceDepartmentDTO.setTypeBalance(OrpakWSConstants.ORPAK_WS_DEPARTMENT_BALANCE_QUANTITY);
				}else{
					requestBalanceDepartmentDTO.setTypeBalance(OrpakWSConstants.ORPAK_WS_DEPARTMENT_BALANCE_AMOUNT);
				}

				log.info("Recuperar saldos del departamento " + requestBalanceDepartmentDTO);
				List<ResponseBalanceDepartmentOrpakDTO> respuesta = new ArrayList<ResponseBalanceDepartmentOrpakDTO>();

//				try { // TODO YCORTES - Implementar con Diego
////					respuesta = this.orpakWSService.getDepartmentBalance(requestBalanceDepartmentDTO);
//				} catch (OrpakWSExceptions e2) {
//					log.warn("Ha ocurrido un error al obtener los datos pero continua el proceso: " + e2.getMessage());
//				}

				for (ResponseBalanceDepartmentOrpakDTO responseBalanceDepartmentDTO : respuesta) {
					log.info("Actualizar saldo para " + responseBalanceDepartmentDTO);

					// Se Actualiza posteriormente el Monto del departamento de origen
//					ProductDepartmentDTO productDepartmentDTO = new ProductDepartmentDTO();
					ProductsDepartmentsByCriteriaRequestDTO productDepartmentDTO = new ProductsDepartmentsByCriteriaRequestDTO();
//					productDepartmentDTO.setIdDepartment( responseBalanceDepartmentDTO.getIdDepartment() );
//					productDepartmentDTO.setProductCode( responseBalanceDepartmentDTO.getCode() );
					productDepartmentDTO.setDocumentType( ClientConstants.DOCUMENT_TYPE_INVOICE );

					log.debug("Procesar: "+productDepartmentDTO);

					// Se valida que el producto exista en ese departamento y de no existir se debe crear la relación
//					List<ProductDepartmentDTO> listaProd = new ArrayList<ProductDepartmentDTO>();
					List<ProductsDepartmentsRel> listaProd = new ArrayList<>();

//					try {
////						listaProd = this.clientsMgr.getProductsDepartmentsByCriteria(productDepartmentDTO);
//						listaProd = this.productsDepartmentsRelRepoJPA.getProductsDepartmentsByCriteria(productDepartmentDTO);
//					} catch (Exception e1) {
//						log.error("Ha ocurrido un error al obtener los datos: " + e1.getMessage());
//						throw new ProductDepartmentException("Ha ocurrido un error al obtener los datos: " + e1.getMessage());
//					}

//					productDepartmentDTO.setRemainingAmount( responseBalanceDepartmentDTO.getBalance() );

					if(listaProd.size()==0){
						log.info("El producto no existe para el departamento, se debe crear. " + productDepartmentDTO);

//						this.clientsMgr.addProductDepartmentRel(productDepartmentDTO);
						this.addProductDepartmentRel(productDepartmentDTO);

					} else { 
						try {
							log.info("Actualizar saldo del departamento/producto con la respuesta de Orpak.");
							this.updDepartmentProductAmountSMov(productDepartmentDTO.toProductDepartmentDTO());
						} catch (NuevoMontoException e) {
							log.error("(BBR) Error al actualizar el departamento con el Monto de orpak"+ e.getMessage());
//							throw new ProductDepartmentException("(BBR) Error al actualizar el departamento con el Monto de orpak"+ e.getMessage());
						}
					}

				}				

			}

			log.info("Obtener saldos para id orpak boleta " + depto.getCodeorpakticket());
			if( depto.getCodeorpakticket() != null ) {

				RequestBalanceDepartmentDTO requestBalanceDepartmentDTO = new RequestBalanceDepartmentDTO();
				requestBalanceDepartmentDTO.setIdOrpakDepartment( depto.getCodeorpakticket() );
				requestBalanceDepartmentDTO.setIdClient( depto.getClient().getIdClient() );
				requestBalanceDepartmentDTO.setIdDepartment(idDepartment);

//				RegisterClientDTO cliente = this.clientsMgr.getClient(depto.getIdClient());
				Clients cliente = depto.getClient();

				if(ClientConstants.CLIENT_SCS.equals(cliente.getClientType())){
					requestBalanceDepartmentDTO.setTypeBalance(OrpakWSConstants.ORPAK_WS_DEPARTMENT_BALANCE_QUANTITY);
				}else{
					requestBalanceDepartmentDTO.setTypeBalance(OrpakWSConstants.ORPAK_WS_DEPARTMENT_BALANCE_AMOUNT);
				}

				log.info("Recuperar saldos del departamento " + requestBalanceDepartmentDTO);
				List<ResponseBalanceDepartmentOrpakDTO> respuesta = new ArrayList<ResponseBalanceDepartmentOrpakDTO>();

//				try { // TODO YCORTES - implementar con Diego
//					respuesta = this.orpakWSService.getDepartmentBalance(requestBalanceDepartmentDTO);
//				} catch (OrpakWSExceptions e2) {
//					log.warn("Ha ocurrido un error al obtener los datos pero continua el proceso: " + e2.getMessage());
//				}

				for (ResponseBalanceDepartmentOrpakDTO responseBalanceDepartmentDTO : respuesta) {
					log.info("Actualizar saldo para " + responseBalanceDepartmentDTO);

					// Se Actualiza posteriormente el Monto del departamento de origen
					ProductDepartmentDTO productDepartmentDTO = new ProductDepartmentDTO();
//					productDepartmentDTO.setIdDepartment( responseBalanceDepartmentDTO.getIdDepartment() );
//					productDepartmentDTO.setProductCode( responseBalanceDepartmentDTO.getCode() );
					productDepartmentDTO.setDocumentType( ClientConstants.DOCUMENT_TYPE_TICKET );

					log.debug("Procesar: " + productDepartmentDTO);

					// Se valida que el producto exista en ese departamento y de no existir se debe crear la relación
					List<ProductsDepartmentsRel> listaProd = new ArrayList<>();
					try {
//						listaProd = this.clientsMgr.getProductsDepartmentsByCriteria(productDepartmentDTO);
//						listaProd = this.productsDepartmentsRelRepoJPA.getProductsDepartmentsByCriteria(productDepartmentDTO.toProductsDepartmentsByCriteriaRequestDTO());
					} catch (Exception e1) {
						log.error("Ha ocurrido un error al obtener los datos: " + e1.getMessage());
//						throw new ProductDepartmentException("Ha ocurrido un error al obtener los datos: " + e1.getMessage());
					}

//					productDepartmentDTO.setRemainingAmount( responseBalanceDepartmentDTO.getBalance() );

					if(listaProd.size()==0){
						log.info("El producto no existe para el departamento, se debe crear. " + productDepartmentDTO);
//						this.clientsMgr.addProductDepartmentRel(productDepartmentDTO);
//						this.addProductDepartmentRel(productDepartmentDTO.toProductsDepartmentsByCriteriaRequestDTO());
					} else {
						try {
							log.info("Actualizar saldo del departamento/producto con la respuesta de Orpak.");
							this.updDepartmentProductAmountSMov(productDepartmentDTO);
						} catch (NuevoMontoException e) {
//							log.error("(BBR) Error al actualizar el departamento con el Monto de orpak"+ e.getMessage());
//							throw new ProductDepartmentException("(BBR) Error al actualizar el departamento con el Monto de orpak"+ e.getMessage());
						}
					}

				}	

			}			

			log.info("Obtener saldos para id orpak cliente " + depto.getCodeorpakclient());
			if( depto.getCodeorpakclient() != null ) {
				Clients cliente;
				try {
//					cliente = this.getClientById(depto.getIdClient());
					cliente = depto.getClient();

					RequestBalanceDepartmentDTO requestBalanceDepartmentDTO = new RequestBalanceDepartmentDTO();
					requestBalanceDepartmentDTO.setIdOrpakDepartment( depto.getCodeorpakclient() );
					requestBalanceDepartmentDTO.setIdClient( depto.getClient().getIdClient() );
					requestBalanceDepartmentDTO.setIdDepartment(idDepartment);
					if (ClientConstants.CLIENT_SCS.equals(cliente.getClientType())){
						requestBalanceDepartmentDTO.setTypeBalance(OrpakWSConstants.ORPAK_WS_DEPARTMENT_BALANCE_QUANTITY);
					}else{
						requestBalanceDepartmentDTO.setTypeBalance(OrpakWSConstants.ORPAK_WS_DEPARTMENT_BALANCE_AMOUNT);
					}

					log.info("Recuperar saldos del departamento " + requestBalanceDepartmentDTO);
					List<ResponseBalanceDepartmentOrpakDTO> respuesta = new ArrayList<>();

//					try { // TODO YCORTES - implementar con Diego
//						respuesta = this.orpakWSService.getDepartmentBalance(requestBalanceDepartmentDTO);
//					} catch (OrpakWSExceptions e2) {
//						log.warn("Ha ocurrido un error al obtener los datos pero continua el proceso: " + e2.getMessage());
//					}

					for (ResponseBalanceDepartmentOrpakDTO responseBalanceDepartmentDTO : respuesta) {
						log.info("Actualizar saldo para " + responseBalanceDepartmentDTO);

						// Se Actualiza posteriormente el Monto del departamento de origen
						ProductDepartmentDTO productDepartmentDTO = new ProductDepartmentDTO();
//						productDepartmentDTO.setIdDepartment( responseBalanceDepartmentDTO.getIdDepartment() );
//						productDepartmentDTO.setProductCode( responseBalanceDepartmentDTO.getCode() );
						productDepartmentDTO.setDocumentType( ClientConstants.DOCUMENT_TYPE_INVOICE );

						log.debug("Procesar: "+productDepartmentDTO);

						// Se valida que el producto exista en ese departamento y de no existir se debe crear la relación
//						List<ProductDepartmentDTO> listaProd = new ArrayList<ProductDepartmentDTO>();
						List<ProductsDepartmentsRel> listaProd = new ArrayList<>();

						try {
//							listaProd = this.clientsMgr.getProductsDepartmentsByCriteria(productDepartmentDTO);
//							listaProd = this.productsDepartmentsRelRepoJPA.getProductsDepartmentsByCriteria(productDepartmentDTO.toProductsDepartmentsByCriteriaRequestDTO());
						} catch (Exception e1) {
							log.error("Ha ocurrido un error al obtener los datos: " + e1.getMessage());
//							throw new ProductDepartmentException("Ha ocurrido un error al obtener los datos: " + e1.getMessage());
						}

//						productDepartmentDTO.setRemainingAmount( responseBalanceDepartmentDTO.getBalance() );

						if(listaProd.size()==0){
							log.info("El producto no existe para el departamento, se debe crear. " + productDepartmentDTO);
//							this.clientsMgr.addProductDepartmentRel(productDepartmentDTO);
//							this.addProductDepartmentRel(productDepartmentDTO.toProductsDepartmentsByCriteriaRequestDTO());
						} else {		
							try {
								log.info("Actualizar saldo del departamento/producto con la respuesta de Orpak.");
								this.updDepartmentProductAmountSMov(productDepartmentDTO);
							} catch (NuevoMontoException e) {
								log.error("(BBR) Error al actualizar el departamento con el Monto de orpak"+ e.getMessage());
//								throw new ProductDepartmentException("(BBR) Error al actualizar el departamento con el Monto de orpak"+ e.getMessage());
							}
						}

					}	
				} catch (Exception e3) {
					log.error("Problemas al obtener saldo departamento desde orpak. " + e3.getMessage());
					throw new DepartmentException( "Ha ocurrido un problema al actualizar los saldos del departamento." );
				}

			}	

		} catch ( Exception e) {
			log.error("Problemas al obtener saldo departamento desde orpak. " + e.getMessage());
			throw new DepartmentException( "Ha ocurrido un problema al actualizar los saldos del departamento." );
		}

	}

	/**
	 * @param dto
	 * @throws NuevoMontoException
	 */
	@Override
	public void addProductDepartmentRel(ProductsDepartmentsByCriteriaRequestDTO dto) throws NuevoMontoException {
		ProductsDepartmentsRel record = new ProductsDepartmentsRel();
		record.setIdDepartment(dto.getIdDepartment());
		record.setDocumentType(dto.getDocumentType());
		record.setProductCode(dto.getProductCode());
		record.setRemainingAmount(dto.getRemainingAmount());

		try {
			this.productsDepartmentsRelRepoJPA.save(record);
		} catch (Exception e) {
			log.error("No se pudo crear un nuevo registro de productsDepartmentsRel ", e);
//			throw new NuevoMontoException("No se pudo crear un nuevo registro del producto para el departamento. ", e);
		}
	}
	
	@Override
	public void updDepartmentProductAmount(ProductDepartmentDTO dto) throws NuevoMontoException {
		try{
			//verifico si el saldo fue modificado
//			ProductsDepartmentsRelExample example = new ProductsDepartmentsRelExample();
//			ProductsDepartmentsRelExample.Criteria criteria = example.createCriteria();
//			criteria.andIdDepartmentEqualTo(dto.getIdDepartment());
//			criteria.andDocumentTypeEqualTo(dto.getDocumentType());
//			criteria.andProductCodeEqualTo(dto.getProductCode());
//
//			List<ProductsDepartmentsRel> pdeps = productsDepartments.selectByExample(example);
			
			ProductsDepartmentsByCriteriaRequestDTO example = new ProductsDepartmentsByCriteriaRequestDTO();
			example.setIdDepartment(dto.getIdDepartment());
			example.setDocumentType(dto.getDocumentType());
			example.setProductCode(dto.getProductCode());

//			List<ProductsDepartmentsRel> pdeps = this.productsDepartmentsRelRepoJPA.getProductsDepartmentsByCriteria(example);
//
//			if(pdeps.size() >= 1){
//				ProductsDepartmentsRel pdep = pdeps.get(0);
//
//				if(pdep.getRemainingAmount().compareTo(dto.getRemainingAmount()) != 0){
//					//si el saldo es distinto, inserto un registro de movimiento departamento por la diferencia
//					BigDecimal dif = dto.getRemainingAmount().subtract(pdep.getRemainingAmount());
//
////					DepartmentMovementDTO movimiento = new DepartmentMovementDTO();
////					movimiento.setIdDepartment(dto.getIdDepartment());
////					movimiento.setMovement(dto.getMovimiento()!=null?dto.getMovimiento():"");
////					movimiento.setDateIn(LocalDateTime.now());
////					movimiento.setDocumentType(dto.getDocumentType());
////					movimiento.setProductCode(dto.getProductCode());
////					movimiento.setMonto(dif);
////					movimiento.setUsername(dto.getUsername());
////
////					this.addDepartmentMovement(movimiento);
//				}
//
//				// **********************************
////				this.finderProductDepartment.updDepartmentProductAmount(dto);
//
//				this.updDepartmentProductAmountFinder(dto);
//				// **********************************
//
//			} else {
//				log.debug("El departamento no tiene este tipo de producto, se crea nuevo.");
//				ProductsDepartmentsRel record = new ProductsDepartmentsRel();
//				record.setIdDepartment(dto.getIdDepartment());
//				record.setDocumentType(dto.getDocumentType());
//				record.setProductCode(dto.getProductCode());
//				record.setRemainingAmount(dto.getRemainingAmount());
//
//				try {
////					this.productsDepartments.insertSelective(record);
//					this.productsDepartmentsRelRepoJPA.save(record);
//				} catch (Exception e) {
//					log.error("No se pudo crear un nuevo registro de productsDepartmentsRel ", e);
////					throw new NuevoMontoException("No se pudo crear un nuevo registro del producto para el departamento. ", e);
//				}
//			}

			if( dto.getRemainingAmount() != null ) {
//				this.finderCardUserMapper.addAuditsChangeBalance("DepartmentBalance Departamento " + dto.getIdDepartment() + " Producto " + dto.getProductCode() + " Monto " + dto.getRemainingAmount() );
				Audits audit = new Audits();
				audit.setInsertLogin("SYSTEM");
				audit.setInsertName("SYSTEM");
				audit.setDate(LocalDateTime.now());
				audit.setAction("ChangeBalance");
				audit.setDescription("DepartmentBalance Departamento " + dto.getIdDepartment() + " Producto " + dto.getProductCode() + " Monto " + dto.getRemainingAmount());
				audit.setSystem("PTSF");
				
				this.auditsRepoJPA.save(audit);
			}

		}catch(Exception e){
			log.error("(BBR) Error al actualizar el monto del departamento que viene desde orpak", e);
//			throw new NuevoMontoException("(BBR) Error al actualizarel monto del departamento que viene desde orpak", e);
		}
	}

	/**
	 * Es el EX finderProductDepartment.updDepartmentProductAmount(dto)
	 * @param dto
	 */
	public void updDepartmentProductAmountFinder(ProductDepartmentDTO dto) {
		ProductsDepartmentsByCriteriaRequestDTO params = new ProductsDepartmentsByCriteriaRequestDTO();
		params.setIdDepartment(dto.getIdDepartment());
		params.setProductCode(dto.getProductCode());
		params.setDocumentType(dto.getDocumentType());

//		List<ProductsDepartmentsRel> product = this.productsDepartmentsRelRepoJPA.getProductsDepartmentsByCriteria(params);
//		
//		if(!Functions.hasEmptyOrNull(product)) {
//			for(ProductsDepartmentsRel pro : product) {
//				pro.setRemainingAmount(dto.getRemainingAmount()); // Nuevo valor
//				this.productsDepartmentsRelRepoJPA.save(pro);
//			}
//		}
	}
	
	@Override
	public void addDepartmentMovement(DepartmentMovementDTO dto) throws DepartmentException {
		try {
			//si no se especifica nombre de movimiento no se inserta nada
			//se asume que no se quiere insertar registro (en caso de reajustes por ej.)
			if(dto.getMovement() == null || "".equals(dto.getMovement())){
				return;
			}

//			DepartmentDTO depto = new DepartmentDTO();
//			depto = this.getdepartment(dto.getIdDepartment());
			
			Optional<Departments> deptoOpt = this.departmentRepoJPA.findById(dto.getIdDepartment());
			if(deptoOpt.isEmpty()) {
				log.error("No se encontro departamento con id " + dto.getIdDepartment());
				throw new DepartmentException("No se encontro departamento con id " + dto.getIdDepartment());
			}
			
			Departments depto = deptoOpt.get();


			DepartmentMovement record = new DepartmentMovement();

			record.setDateIn(dto.getDateIn());
			record.setDocumentType(dto.getDocumentType());
			record.setIdDepartment(dto.getIdDepartment());
			record.setMonto(dto.getMonto());
			record.setMovement(dto.getMovement());
			record.setIdRef(dto.getIdRef());
			record.setTypeBalance(depto.getTypeBalance());

			//como se pide el product name en la BD, en vez de el codigo, lo busco con el codigo de producto
//			Products prod = daoProduct.selectByPrimaryKey(dto.getProductCode());
			Products prod = this.productsRepoJPA.findByProductStatusAndProductCode("A", dto.getProductCode());

			if(prod != null){
				record.setProductName(prod.getName());
			}

			record.setUsername(dto.getUsername());
//			this.daoDeptoMovement.insertSelective(record);
			departmentMovementRepoJPA.save(record);

		} catch (Exception e) {
			log.error("Error al insertar movimiento departamento, correspondiente al departamento: "+dto.getIdDepartment());
//			throw new DepartmentException("Error al insertar movimiento departamento, correspondiente al departamento: "+dto.getIdDepartment(), e);
		}
	}
	
	@Override
	public BigDecimal getPrecioLitro(String productCode, BigDecimal amount) throws PriceLiterNotFoundException {

		log.info("convertir precio " + productCode + " cantidad " + amount);

//		List<PreciosLitrosDTO> lista = this.clientsMgr.getPriceLiters(productCode);
		List<PriceLiters> lista = this.priceLitersRepoJPA.findByProductCode(productCode);

		if( lista.size() > 0 ) {

			PriceLiters preciosLitrosDTO = lista.get(0);

			log.info("convertir precio a litros " + productCode + " cantidad " + amount + " precio " + preciosLitrosDTO.getPrice());

			BigDecimal result = amount.divide(preciosLitrosDTO.getPrice(), 0, RoundingMode.HALF_UP);

			log.info("resultado conversión : " + result);

			return result;
		} else {
			log.warn("No existe conversión");
			throw new PriceLiterNotFoundException("No existe conversión.");
		}

	}
	
	@Override
	public void updClientByID(RegisterClientDTO dto) throws ClientException {
		if(dto == null){
			log.error("Parametro de entrada objeto cliente es nulo");
			throw new IllegalArgumentException("Parametro de entrada objeto cliente es nulo");
		}

		if(dto.getIdClient() == null){
			log.error("Parametro de entrada id cliente es nulo");
			throw new IllegalArgumentException("Parametro de entrada id cliente es nulo");
		}
		
		Optional<Clients> clientOpt = this.clientRepoJPA.findById(dto.getIdClient());
		
		if(Functions.hasEmptyOrNull(clientOpt)) {
			log.error("No se encontro cliente para id " + dto.getIdClient());
			throw new IllegalArgumentException("No se encontro cliente para id " + dto.getIdClient());
		}
		
		Clients cliente = clientOpt.get();
		
		if(!Functions.hasEmptyOrNull(dto.getRemainingAmount()))
			cliente.setRemainingAmount(dto.getRemainingAmount());

//		this.clientsMgr.updClientByID(dto);

		//se informa a orpak de la modificacion al cliente
//		if( dto.isPutSaf() ) {
//			try {
//				SafQueue message = new SafQueue();
//				message.setIdElement(cliente.getIdClient()+"");
//				message.setType(SafConstants.SAF_UPDATE_CLIENT_ORPAK);
//				message.setStatus(SafConstants.SAF_STATUS_PENDING);
//				message.setNumRetries(0L);
//
////				this.safService.pushMessageUpdateClient(dto.getIdClient());
//				this.safQueueRepoJPA.save(message);
//			} catch (Exception e) {
//				log.debug("Ha ocurrido un error al enviar mensaje de actualizacion de cliente a la cola SAF: " + e.getMessage());
//				throw new ClientException("Ha ocurrido un error al enviar mensaje de actualizacion de cliente a la cola SAF: " + e.getMessage()); 
//			}
//		}
	}
	
	@Override
	public void updDepartmentProductAmountSMov(ProductDepartmentDTO dto) throws NuevoMontoException {
		try{
			//verifico si el saldo fue modificado
//			ProductsDepartmentsRelExample example = new ProductsDepartmentsRelExample();
//			ProductsDepartmentsRelExample.Criteria criteria = example.createCriteria();
//			criteria.andIdDepartmentEqualTo(dto.getIdDepartment());
//			criteria.andDocumentTypeEqualTo(dto.getDocumentType());
//			criteria.andProductCodeEqualTo(dto.getProductCode());
//
//			List<ProductsDepartmentsRel> pdeps = productsDepartments.selectByExample(example);

			ProductsDepartmentsByCriteriaRequestDTO example = new ProductsDepartmentsByCriteriaRequestDTO();
			example.setIdDepartment(dto.getIdDepartment());
			example.setDocumentType(dto.getDocumentType());
			example.setProductCode(dto.getProductCode());

//			List<ProductsDepartmentsRel> pdeps = this.productsDepartmentsRelRepoJPA.getProductsDepartmentsByCriteria(example);
//
//			if(pdeps.size() >= 1) {
//				ProductsDepartmentsRel pdep = pdeps.get(0);
////				this.finderProductDepartment.updDepartmentProductAmount(dto);
//				this.updDepartmentProductAmountFinder(dto);
//			} else {
//				log.debug("El departamento no tiene este tipo de producto, se crea nuevo.");
//				ProductsDepartmentsRel record = new ProductsDepartmentsRel();
//				record.setIdDepartment(dto.getIdDepartment());
//				record.setDocumentType(dto.getDocumentType());
//				record.setProductCode(dto.getProductCode());
//				record.setRemainingAmount(dto.getRemainingAmount());
//
//				try {
////					this.productsDepartments.insertSelective(record);
//					this.productsDepartmentsRelRepoJPA.save(record);
//				} catch (Exception e) {
//					log.error("No se pudo crear un nuevo registro de productsDepartmentsRel ", e);
////					throw new NuevoMontoException("No se pudo crear un nuevo registro del producto para el departamento. ", e);
//				}
//			}
			
			if( dto.getRemainingAmount() != null ) {
//				this.finderCardUserMapper.addAuditsChangeBalance("DepartmentBalance Departamento " + dto.getIdDepartment() + " Producto " + dto.getProductCode() + " Monto " + dto.getRemainingAmount() );
				Audits audit = new Audits();
				audit.setInsertLogin("SYSTEM");
				audit.setInsertName("SYSTEM");
				audit.setDate(LocalDateTime.now());
				audit.setAction("ChangeBalance");
				audit.setDescription("DepartmentBalance Departamento " + dto.getIdDepartment() + " Producto " + dto.getProductCode() + " Monto " + dto.getRemainingAmount());
				audit.setSystem("PTSF");
				
				this.auditsRepoJPA.save(audit);
			}
			
		} catch(Exception e){
			log.error("(BBR) Error al actualizar el monto del departamento que viene desde orpak", e);
//			throw new NuevoMontoException("(BBR) Error al actualizarel monto del departamento que viene desde orpak", e);
		}
	}

	@Override
	public Optional<ListDepartmentsByClient2JsonCtrlResultDTO> ListDepartmentsByClient2JsonCtrl(
			ListDepartmentsByClient2JsonCtrlRequestDTO params, PagingSortDTO sort, PagingInitDTO paging, Locale locale) {
		
		ListDepartmentsByClient2JsonCtrlResultDTO response = new ListDepartmentsByClient2JsonCtrlResultDTO();
		
		String name = params.getName();
		String plate = params.getPlate();
		String tipoCliente = params.getTipoCliente();
		
		try{
			DepartmentByClientRequestDTO dto = new DepartmentByClientRequestDTO();
			Optional<Clients> optionalCliente = this.clientRepoJPA.findById(params.getIdClient());
			List<DepartmentDTO> listadoDepartamentos = new ArrayList<DepartmentDTO>();
			Long total = 0L;
			
			Pageable pages = PageRequest.of(0, Integer.MAX_VALUE);
			
			if(optionalCliente.isPresent()){
				
				Clients cliente = optionalCliente.get();			
				
				dto.setIdCliente(cliente.getIdClient());
		
				if(name!=null && name!=""){
					dto.setName(name);
				}
				if(plate!=null && plate!=""){
					dto.setPlate(plate);
				}
				if(tipoCliente!=null && tipoCliente!=""){
					dto.setClientType(tipoCliente);
				}
				
				listadoDepartamentos = this.departmentsRepoExtended.getDepartmentsByClient(dto, pages);
				
				total = this.departmentsRepoExtended.getCOUNTDepartmentsByClient(dto);
			}
			
			response.setListaDepartamentos(Optional.of(listadoDepartamentos));
			response.setCountListaDepartamentos(Optional.of(total));
			
		}catch(Exception e){
			log.error(e.getMessage());
			response.setObservation(Optional.of("Error al traer departamentos"));
			return Optional.of(response);
			}
		
		
		
		return Optional.of(response);
	}
	
	@Override
	public Optional<DoSetConstraintsByDepartmentResultDTO> DoSetConstraintsByDepartmentJsonCtrl(
			DoSetConstraintsByDepartmentRequestDTO params) throws DepartmentException {
		DoSetConstraintsByDepartmentResultDTO response = new DoSetConstraintsByDepartmentResultDTO();
		
		if(Functions.hasEmptyOrNull(params.getMode())) {
			log.error("El parametro mode no puede ser nulo o vacio");
			throw new IllegalArgumentException("El parametro mode no puede ser nulo o vacio");
		}
		
		if(Functions.hasEmptyOrNull(params.getIdDepartment())) {
			log.error("El parametro idDepartment no puede ser nulo o vacio");
			throw new IllegalArgumentException("El parametro idDepartment no puede ser nulo o vacio");
		}
		
		if(Functions.hasEmptyOrNull(params.getStationCode())) {
			log.error("El parametro stationCode no puede ser nulo o vacio");
			throw new IllegalArgumentException("El parametro stationCode no puede ser nulo o vacio");
		}
		
		Long registros = 0L;
		for(String sta : params.getStationCode().split(",")){
				if("insert".equals(params.getMode())){
					registros += this.setStationDepartmentConstraint(params.getIdDepartment(), sta);
				}else if("delete".equals(params.getMode())){
					registros += this.deleteStationDepartmentConstraint(params.getIdDepartment(), sta);
				}else{
					throw new DepartmentException("Se especificó una acción desconocida: " + params.getMode());
				}
		}	

		response.setObservation(Optional.of("Registros actualizados con exito"));
		return Optional.of(response);
	}

	private Long deleteStationDepartmentConstraint(Long idDepartment, String sta) {
	    if (Functions.hasEmptyOrNull(idDepartment, sta)) {
	        log.error("Los parametros no pueden ser nulos");
	        throw new IllegalArgumentException("Los parametros no pueden ser nulos");
	    }

	    Long stationRetorno = null;

	    Departments department = this.departmentRepoJPA.findById(idDepartment).orElse(null);

	    if (department != null) {
	        StationDepartmentConstraints entity = new StationDepartmentConstraints();
	        entity.setIdDepartment(idDepartment);
	        entity.setStationCode(sta);

	        entity.setDepartment(department);

	        this.stationDeparmentConstraintRepoJPA.delete(entity);
	        log.info("StationDepartmentConstraint ELIMINADO: " + entity);
	        stationRetorno = idDepartment;
	    }

	    return stationRetorno;
	}


	private Long setStationDepartmentConstraint(Long idDepartment, String sta) {
	    if (Functions.hasEmptyOrNull(idDepartment, sta)) {
	        log.error("Los parametros no pueden ser nulos");
	        throw new IllegalArgumentException("Los parametros no pueden ser nulos");
	    }

	    Long stationRetorno = null;

	    Departments department = this.departmentRepoJPA.findById(idDepartment).orElse(null);

	    if (department != null) {
	        try {
	            StationDepartmentConstraints entity = new StationDepartmentConstraints();
	            
	            entity.setIdDepartment(idDepartment);
	            entity.setStationCode(sta);

	            entity.setDepartment(department);

	            StationDepartmentConstraints registroIngresado = this.stationDeparmentConstraintRepoJPA.save(entity);
	            log.info("StationDepartmentConstraint INGRESADO: " + registroIngresado);
	            stationRetorno = idDepartment;
	        } catch (Exception e) {
	            log.error("Error al agregar registro: " + e.getMessage());
	        }
	    }

	    return stationRetorno;
	}

	@Override
	public List<ProductDepartmentDTO> doProductsDepartmentsByCriteria(ProductDepartmentDTO productDepartmentDTO)
			throws DepartmentException {
		if(productDepartmentDTO == null){
			log.error("Parametro de entrada criteria productsDepartments es nulo");
			throw new IllegalArgumentException("Parametro de entrada criteria productsDepartments es nulo");
		}
    	
		try {
			this.updDepartmentBalance( productDepartmentDTO.getIdDepartment() );
		} catch (DepartmentException  | NuevoMontoException e) {
			log.error("Ha ocurrido un error. " + e.getMessage());
			throw new DepartmentException( "Ha ocurrido un error al recuperar saldos desde Orpak." );
		}
		ProductsDepartmentsByCriteriaRequestDTO request = new ProductsDepartmentsByCriteriaRequestDTO();
		
		request.setIdDepartment(productDepartmentDTO.getIdDepartment());
		request.setProductCode(productDepartmentDTO.getProductCode());
		request.setRemainingAmount(productDepartmentDTO.getRemainingAmount());
		request.setDocumentType(productDepartmentDTO.getDocumentType());
		
//		List<ProductsDepartmentsRel> listEntityProdDeptoRel = this.productsDepartmentsRelRepoJPA.getProductsDepartmentsByCriteria(request);
//		List<ProductDepartmentDTO> listado = new ArrayList<ProductDepartmentDTO>();
//		for(ProductsDepartmentsRel pdr:listEntityProdDeptoRel){
//			ProductDepartmentDTO pdDto = new ProductDepartmentDTO();
//			pdDto.setIdDepartment(pdr.getIdDepartment());
//			pdDto.setProductCode(pdr.getProductCode());
//			pdDto.setDocumentType(pdr.getDocumentType());
//			pdDto.setRemainingAmount(pdr.getRemainingAmount());
//			listado.add(pdDto);
//		}
		
    	return null;//listado;
	}

	@Override
	public DepartmentDTO getListDepartmentById(Long idDepartment) {

		if(Functions.hasEmptyOrNull(idDepartment)) {
			throw new IllegalArgumentException("ID departamento no debe ser null");
		}
		
		Departments entityDepartment = this.departmentRepoJPA.findByIdDepartment(idDepartment);
		
		if(Functions.hasEmptyOrNull(entityDepartment)) {
			throw new IllegalArgumentException("ID departamento no debe ser null");
		}
		
		return this.departmentsMapper.toDTO(entityDepartment);
	}

	@Override
	public void updDepartmentProductAmountSum(ReasignarMontoDeptDTO dto) throws NuevoMontoException {
		try{
			//genero movimiento de "suma" de saldo
			DepartmentMovementDTO movimiento = new DepartmentMovementDTO();
			movimiento.setIdDepartment(dto.getIdDeptoDestino());
			movimiento.setMovement(dto.getMovimiento()!=null?dto.getMovimiento():"");
			movimiento.setDateIn(LocalDateTime.now());
			movimiento.setDocumentType(dto.getDocumentType());
			movimiento.setProductCode(dto.getProdCode());
			movimiento.setMonto(dto.getNuevoMonto());
			movimiento.setUsername(dto.getUsername());
			movimiento.setIdRef(dto.getIdRef()==null?dto.getIdDeptoOrigen():dto.getIdRef());
			
			this.addDepartmentMovement(movimiento);
			
			ProductDepartmentDTO criterio = new ProductDepartmentDTO();
			criterio.setDocumentType(dto.getDocumentType());
			criterio.setIdDepartment(dto.getIdDeptoDestino());
			criterio.setProductCode(dto.getProdCode());
			List<DepartmentProductFinderDTO> productosDepto = new ArrayList<DepartmentProductFinderDTO>();
			productosDepto = this.getProductDepartmentTransf(criterio);
			
			if (productosDepto.size() == 0){
				log.debug("El departamento no tiene este tipo de producto, se crea nuevo.");
				ProductsDepartmentsRel record = new ProductsDepartmentsRel();
				record.setIdDepartment(dto.getIdDeptoDestino());
				record.setDocumentType(dto.getDocumentType());
				record.setProductCode(dto.getProdCode());
				record.setRemainingAmount(dto.getNuevoMonto());
				try {
//					this.productsDepartments.insertSelective(record);
					this.productsDepartmentsRelRepoJPA.save(record);
				} catch (Exception e) {
					log.error("No se pudo crear un nuevo registro de productsDepartmentsRel ", e);
//					throw new NuevoMontoException("No se pudo crear un nuevo registro del producto para el departamento. ", e);
				}
			}else{
				this.departmentRepoJPA.updateDepartmentProductAmountSum(dto.getNuevoMonto(), dto.getIdDeptoDestino(), dto.getProdCode(), dto.getDocumentType());
			}
			
			if( dto.getNuevoMonto() != null ) {
//				this.cardService.addAuditsChangeBalance("DepartmentBalance Departamento " + dto.getIdDeptoDestino() + " Producto " + dto.getProdCode() + " Monto " + dto.getNuevoMonto() , "BOPTSF");
			}
			
		}catch(Exception e){
			log.error("(BBR) Error al actualizar monto departamento destino"+ e.getMessage());
//			throw new NuevoMontoException("(BBR) Error al actualizar monto departamento destino" + e.getMessage());
		}
		
	}

	@Override
	public List<DepartmentProductFinderDTO> getProductDepartmentTransf(ProductDepartmentDTO dto) {
		
		if(Functions.hasEmptyOrNull(dto)){
			throw new IllegalArgumentException("ProductDepartmentDTO no puede ser null");
		}
		
		List<DepartmentProductFinderDTO> listProd=new ArrayList<DepartmentProductFinderDTO>();
		
		listProd = this.departmentsRepoExtended.getProductDepartmentTransf(dto);
		
		return listProd;
	}

	public Optional<GetConstraintsByDepartmentResultDTO> getConstraintsByDepartment(
			GetConstraintsByDepartmentRequestDTO params, PagingSortDTO sort, PagingInitDTO paging, Locale locale) throws DepartmentException {
		GetConstraintsByDepartmentResultDTO response = new GetConstraintsByDepartmentResultDTO();

		log.info("****************** getConstraintsByDepartment SERVICE ******************".toUpperCase());

		if (Functions.hasEmptyOrNull(params.getIdDepartment(), params.getClientType())) {
			log.error("Los parametros idDepartment, clientType no pueden ser nulos o vacios.");
			throw new IllegalArgumentException("Los parametros idDepartment, clientType no pueden ser nulos o vacios.");
		}

		try {
			StationClientsRequestDTO params2 = new StationClientsRequestDTO();
			params2.setClientType(params.getClientType());
			params2.setIdDepartment(params.getIdDepartment());
			List<StationsDTO> constraints = this.stationDepartmentsRepoExtend.getConstraintsByDepartment(params2);
			response.setConstraints(Optional.of(constraints));

			response.setTodos(Functions.hasEmptyOrNull(constraints));
			response.setPersonalizado(!Functions.hasEmptyOrNull(constraints));
		} catch (Exception e) {
			log.error("Ocurrio un error al obtener constraint por card: " + e.getMessage());
			throw new DepartmentException("Ocurrio un error al obtener constraint por card");
		}

		log.info("****************** getConstraintsByDepartment SERVICE FIN ******************".toUpperCase());

//		response.setPagingData(Optional.of(PagingDataDTO.fromPagingInit(paging)));
//		response.setSort(sort != null ? Optional.of(sort) : null);

		return Optional.of(response);
	}
	
}

