package com.evertecinc.b2c.enex.process.orpak.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.evertecinc.b2c.constants.AuditoriaConstans;
import com.evertecinc.b2c.enex.audits.model.dto.AuditDTO;
import com.evertecinc.b2c.enex.audits.service.AuditsService;
import com.evertecinc.b2c.enex.client.exceptions.CardException;
import com.evertecinc.b2c.enex.client.exceptions.ClientException;
import com.evertecinc.b2c.enex.client.exceptions.ClientNotFoundException;
import com.evertecinc.b2c.enex.client.exceptions.ClientServiceException;
import com.evertecinc.b2c.enex.client.exceptions.DepartmentException;
import com.evertecinc.b2c.enex.client.exceptions.NuevoMontoException;
import com.evertecinc.b2c.enex.client.exceptions.OTException;
import com.evertecinc.b2c.enex.client.exceptions.PriceLiterNotFoundException;
import com.evertecinc.b2c.enex.client.exceptions.ProductDepartmentException;
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
import com.evertecinc.b2c.enex.client.service.CardsService;
import com.evertecinc.b2c.enex.client.service.ClientService;
import com.evertecinc.b2c.enex.integracion.constants.ClientConstants;
import com.evertecinc.b2c.enex.integracion.constants.OrpakWSConstants;
import com.evertecinc.b2c.enex.integracion.exception.OrpakWSCardConstraintExistExceptions;
import com.evertecinc.b2c.enex.integracion.exception.OrpakWSClientExistExceptions;
import com.evertecinc.b2c.enex.integracion.exception.OrpakWSExceptions;
import com.evertecinc.b2c.enex.integracion.exception.OrpakWSTrxCardBalanceExistExceptions;
import com.evertecinc.b2c.enex.integracion.exception.OrpakWSTrxCustomerBalanceExistExceptions;
import com.evertecinc.b2c.enex.integracion.exception.OrpakWSTrxDepartmentBalanceExistExceptions;
import com.evertecinc.b2c.enex.integracion.exception.OrpakWSVehicleCardExistExceptions;
import com.evertecinc.b2c.enex.integracion.model.dto.CardConstraintDTO;
import com.evertecinc.b2c.enex.integracion.model.dto.ChangeDepartmentStatusDTO;
import com.evertecinc.b2c.enex.integracion.model.dto.CreateClientDTO;
import com.evertecinc.b2c.enex.integracion.model.dto.CreateDepartmentDTO;
import com.evertecinc.b2c.enex.integracion.model.dto.CreateDepartmentOfFleetDTO;
import com.evertecinc.b2c.enex.integracion.model.dto.LoadQuantityCardDTO;
import com.evertecinc.b2c.enex.integracion.model.dto.ResponseDTO;
import com.evertecinc.b2c.enex.integracion.model.dto.UpdCardBalanceDTO;
import com.evertecinc.b2c.enex.integracion.model.dto.UpdCustomerBalanceDTO;
import com.evertecinc.b2c.enex.integracion.model.dto.UpdDepartmentBalanceDTO;
import com.evertecinc.b2c.enex.integracion.service.OrpakWSService;
import com.evertecinc.b2c.enex.process.orpak.constants.OrpakProcessConstants;
import com.evertecinc.b2c.enex.process.orpak.exceptions.ProcessOrpakException;
import com.evertecinc.b2c.enex.saf.constants.SafConstants;
import com.evertecinc.b2c.enex.saf.exceptions.SafException;
import com.evertecinc.b2c.enex.saf.model.dto.MessageDTO;
import com.evertecinc.b2c.enex.saf.model.dto.MessageHistoryDTO;
import com.evertecinc.b2c.enex.saf.service.SafService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProcessOrpakServiceImpl implements IProcessOrpakService {

	private static final Logger loggerProcessChangeCardStatus = LoggerFactory
			.getLogger("ChangeCardStatusOrpakProcessImpl");
	private static final Logger loggerProcessChangeClient = LoggerFactory.getLogger("ChangeClientOrpakProcessImpl");
	private static final Logger loggerProcessChangeDeptCardStatus = LoggerFactory
			.getLogger("ChangeDepartmentCardStatusOrpakProcessImpl");
	private static final Logger loggerProcessChangeTypeBalance1 = LoggerFactory
			.getLogger("ChangeTypeBalance1OrpakProcessImpl");
	private static final Logger loggerProcessChangeTypeBalance2Litros = LoggerFactory
			.getLogger("ChangeTypeBalance2LitrosOrpakProcessImpl");
	private static final Logger loggerProcessChangeTypeBalance2 = LoggerFactory
			.getLogger("ChangeTypeBalance2ProcessImpl");
	private static final Logger loggerProcessCreateUpdateOT = LoggerFactory.getLogger("CreateUpdateOTProcessImpl");
	private static final Logger loggerProcessDeleteDepartmentFleet = LoggerFactory
			.getLogger("DeleteDepartmentFleetOrpakProcessImpl");
	private static final Logger loggerProcessSendCardBalance = LoggerFactory
			.getLogger("SendCardBalanceOrpakProcessImpl");
	private static final Logger loggerProcessSendCardConstraint = LoggerFactory
			.getLogger("SendCardConstraintOrpakProcessImpl");
	private static final Logger loggerProcessSendCustomerBalance = LoggerFactory
			.getLogger("SendCustomerBalanceOrpakProcessImpl");
	private static final Logger loggerProcessSendClient = LoggerFactory.getLogger("SendClientOrpakProcessImpl");
	private static final Logger loggerProcessSendDeptBalance = LoggerFactory
			.getLogger("SendDepartmentBalanceOrpakProcessImpl");
	private static final Logger loggerProcessLoadQuantityCard = LoggerFactory.getLogger("LoadQuantityCardProcessImpl");
	private static final Logger loggerProcessVehicleCard = LoggerFactory.getLogger("VehicleCardOrpakProcessImpl");

	private static final Logger loggerProcessChangeDeptStatusCond = LoggerFactory
			.getLogger("ChangeDeptStatusCondProcessImpl");
	private static final Logger loggerProcessChangeDeptStatus = LoggerFactory.getLogger("ChangeDeptStatusImpl");
	private static final Logger loggerProcessChangeTypeBalance1Litros = LoggerFactory
			.getLogger("ChangeTypeBalance1LitrosImpl");

	@Autowired
	ClientService clientService;

	@Autowired
	CardsService cardsService;

	@Autowired
	OrpakWSService orpakWSService;

	@Autowired
	SafService safService;

	@Autowired
	AuditsService auditsService;


	@Override
	public void updPendingCardStatusChanges(MessageDTO msg) throws ProcessOrpakException {

		try {

			// consultamos por la tarjeta que viene en el mensaje
			CardDTO card = this.cardsService.getCardById(msg.getIdElement());

			if (card == null)
				throw new CardException("tarjeta con id " + msg.getIdElement() + " no encontrada!");

			loggerProcessChangeCardStatus.info("Recuperar datos de la tarjeta " + card.getIdCard());
			loggerProcessChangeCardStatus.debug("" + card.toString());

			StatusCardDTO dto = new StatusCardDTO();
			dto.setIdCard(card.getIdCard());
			dto.setCardnum(card.getCardnum());
			dto.setCardStatus(card.getCardStatus());

			loggerProcessChangeCardStatus.info("Ejecutar interfaz Orpak " + dto.toString());

			// actualizamos estado de la tarjeta
			ResponseDTO retorno = orpakWSService.updateCardStatus(dto, "PTSF");
			loggerProcessChangeCardStatus.debug("" + retorno);

			loggerProcessChangeCardStatus.info("Ingresar mensaje al historia de la SAF");
			MessageHistoryDTO message = new MessageHistoryDTO();
			message.setDateIns(LocalDateTime.now());
			message.setIdQueue(msg.getIdQueue());
			message.setReturnCode(retorno.getReturnCode());
			message.setUri(retorno.getUri());
			message.setMessage(retorno.getMessage());

			this.safService.pushMessageHistory(message);

			loggerProcessChangeCardStatus.info("Tarjeta actualizada " + card.getIdCard());

			// actualizamos la cola con status OK
			msg.setDateSend(LocalDateTime.now());
			msg.setStatus(SafConstants.SAF_STATUS_SENDED);
			safService.updateMessage(msg);
			loggerProcessChangeCardStatus.info("Actualizar mensaje SAF para tarjeta " + card.getIdCard());

		} catch (OrpakWSExceptions e) {
			loggerProcessChangeCardStatus.error("Ha ocurrido un error en el proceso. " + e.getMessage());
			loggerProcessChangeCardStatus.error("" + e.getUri());
			ProcessOrpakException exc = new ProcessOrpakException(e.getMessage());
			exc.setReturnCode(e.getReturnCode());
			exc.setUri(e.getUri());
			throw exc;
		} catch (CardException | SafException e) {
			// silent catch, actualizamos la cola con status PENDING y reintentos +1, y
			// sigue procesando
			loggerProcessChangeCardStatus.warn("Tarjeta no pudo ser actualizada. " + e.getMessage());
			throw new ProcessOrpakException(e.getMessage());
		}

	}

	@Override
	public void updateClient(MessageDTO msg) throws ProcessOrpakException {
		try {
			if (msg == null) {
				loggerProcessChangeClient.error("Mensaje no puede ser nulo");
				throw new IllegalArgumentException("Mensaje no puede ser nulo.");
			}


			ClientDTO cliente = this.clientService.getClientById(msg.getIdElement());

			if (cliente == null) {
				loggerProcessChangeClient.error("Cliente ID " + msg.getIdElement() + " no encontrado");
				throw new ProcessOrpakException("Cliente ID " + msg.getIdElement() + " no encontrado");
			}

			loggerProcessChangeClient.info("Cliente ID " + msg.getIdElement() + " encontrado.");

			// Se llama al metodo que envia los clientes a la fachada de enex-orpak-ws
			loggerProcessChangeClient.info("Envia Cliente ID " + cliente.getIdClient() + " a Orpak (SAF).");
			loggerProcessChangeClient.debug(cliente.toString());

			CreateClientDTO clientWs = new CreateClientDTO();
			clientWs.setCustomerdescription(cliente.getName());
			if (ClientConstants.CLIENT_LOCK_LOCKED.equals(cliente.getLocked())) {
				clientWs.setStatus(ClientConstants.CLIENT_STATUS_LOCKED);
			} else {
				clientWs.setStatus(ClientConstants.CLIENT_STATUS_ACTIVE.equals(cliente.getClientStatus()) ? cliente.getClientStatus() : ClientConstants.CLIENT_STATUS_PASIVE);
			}
			clientWs.setAddress1(cliente.getAddressStreetName() + " " + cliente.getAddressStreetNumber() + " " + cliente.getAddressDoorNumber());
			clientWs.setAddress2(cliente.getAddressDisStreetName() + " " + cliente.getAddressDisStreetNumber() + " " + cliente.getAddressDisDoorNumber());
			clientWs.setDisctrict(cliente.getAreaDTO().getName());
			clientWs.setCity(cliente.getCityDTO().getName());
			if (cliente.getContactPhone() != null) {
				if (cliente.getContactPhone().contains(";")) {
					String[] fonos = cliente.getContactPhone().split(";");
					clientWs.setPhone(fonos[0]);
					clientWs.setFax(fonos[0]); // no existe definicion para el fax en el proyecto, por ahora se envia el telefono
				} else {
					clientWs.setPhone(cliente.getContactPhone());
					clientWs.setFax(cliente.getContactPhone()); // no existe definicion para el fax en el proyecto, por  ahora se envia el telefono
				}
			}
			// clientWs.setPhone(cliente.getContactPhone());
			if (cliente.getContactEmail() != null) {
				if (cliente.getContactEmail().contains(";")) {
					String[] mails = cliente.getContactEmail().split(";");
					clientWs.setEmail(mails[0]);
				} else {
					clientWs.setEmail(cliente.getContactEmail());
				}
			}
			// clientWs.setEmail(cliente.getContactEmail());
			clientWs.setRut(cliente.getUpi());
			clientWs.setContactname(cliente.getContactName());
			clientWs.setZip("12345"); // Constante no tenemos valor para este campo
			// clientWs.setFax(cliente.getContactPhone());
			clientWs.setGiro(cliente.getCommercialType());

			if (cliente.isCredit()) {

				if (cliente.getCreditLimit().compareTo(BigDecimal.ZERO) > 0) {
					clientWs.setCreditlimit(cliente.getCreditLimit().longValue() + "");
				} else {
					clientWs.setCreditlimit("0");
				}

				clientWs.setCustomercode(cliente.getCustomerCodeClient());
				clientWs.setAlternativecode(cliente.getCustomerCodeClient());
				loggerProcessChangeClient.info("Se env�a cliente a Orpak Boleta.");
				loggerProcessChangeClient.debug(clientWs.toString());

				ResponseDTO retorno = this.orpakWSService.updateClient(clientWs);
				loggerProcessChangeClient.debug("" + retorno);
				loggerProcessChangeClient.info("Cliente ID " + cliente.getIdClient() + " boleta actualizado.");

				MessageHistoryDTO message = new MessageHistoryDTO();
				message.setDateIns(LocalDateTime.now());
				message.setIdQueue(msg.getIdQueue());
				message.setReturnCode(retorno.getReturnCode());
				message.setUri(retorno.getUri());
				message.setMessage(retorno.getMessage());
				this.safService.pushMessageHistory(message);

			} else {
				// boleta
				if (cliente.getAccountJdeTicket() != null) {
					clientWs.setCustomercode(cliente.getCustomerCodeTicket());
					clientWs.setAlternativecode(cliente.getAccountJdeTicket()); // En el codigo alternativo va la cuenta JDE
					loggerProcessChangeClient.info("Se envia cliente a Orpak Boleta.");
					loggerProcessChangeClient.debug(clientWs.toString());
					ResponseDTO retorno = this.orpakWSService.updateClient(clientWs);
					loggerProcessChangeClient.debug("" + retorno);
					loggerProcessChangeClient.info("Cliente ID " + cliente.getIdClient() + " boleta actualizado.");

					MessageHistoryDTO message = new MessageHistoryDTO();
					message.setDateIns(LocalDateTime.now());
					message.setIdQueue(msg.getIdQueue());
					message.setReturnCode(retorno.getReturnCode());
					message.setUri(retorno.getUri());
					message.setMessage(retorno.getMessage());
					this.safService.pushMessageHistory(message);

				} else {
					loggerProcessChangeClient.info("Cliente no tiene cuenta JDE para boleta.");
				}

				// factura
				if (cliente.getAccountJdeInvoice() != null) {
					clientWs.setCustomercode(cliente.getCustomerCodeInvoice());
					clientWs.setAlternativecode(cliente.getAccountJdeInvoice()); // En el c�digo alternativo va la
																					// cuenta JDE
					loggerProcessChangeClient.info("Se env�a cliente a Orpak Factura.");
					loggerProcessChangeClient.debug(" " + clientWs.toString());
					ResponseDTO retorno = this.orpakWSService.updateClient(clientWs);
					loggerProcessChangeClient.debug("" + retorno);
					loggerProcessChangeClient.info("Cliente ID " + cliente.getIdClient() + " factura actualizado.");

					MessageHistoryDTO message = new MessageHistoryDTO();
					message.setDateIns(LocalDateTime.now());
					message.setIdQueue(msg.getIdQueue());
					message.setReturnCode(retorno.getReturnCode());
					message.setUri(retorno.getUri());
					message.setMessage(retorno.getMessage());
					this.safService.pushMessageHistory(message);

				} else {
					loggerProcessChangeClient.info("Cliente no tiene cuenta JDE para factura.");
				}
			}

			// actualizamos la cola con status OK
			msg.setDateSend(LocalDateTime.now());
			msg.setStatus(SafConstants.SAF_STATUS_SENDED);
			safService.updateMessage(msg);

		} catch (OrpakWSExceptions e) {
			loggerProcessChangeClient.error("Ha ocurrido un error en el proceso. " + e.getMessage());
			loggerProcessChangeClient.error("" + e.getUri());
			ProcessOrpakException exc = new ProcessOrpakException(e.getMessage());
			exc.setReturnCode(e.getReturnCode());
			exc.setUri(e.getUri());
			throw exc;
		} catch (IllegalArgumentException | ClientException | SafException e) {
			loggerProcessChangeClient.error("Ha ocurrido un error en el proceso. " + e.getMessage());
			throw new ProcessOrpakException(e.getMessage());
		}
	}

	@Override
	public void updPendingDepartmentCardStatusChange(MessageDTO msg) throws ProcessOrpakException {
		try {

			// consultamos por el departamento que viene en el mensaje
			DepartmentDTO departamento = this.clientService.getListDepartmentById(msg.getIdElement());
			loggerProcessChangeDeptCardStatus.info("Consultar department " + msg.getIdElement());
			loggerProcessChangeDeptCardStatus.debug("" + departamento);

			// consultamos por el cliente
			ClientDTO cliente = clientService.getClientById(departamento.getIdClient());
			loggerProcessChangeDeptCardStatus.info("Consultar client " + departamento.getIdClient());
			loggerProcessChangeDeptCardStatus.debug("" + cliente);

			ChangeDepartmentStatusDTO changeDepartmentStatus = new ChangeDepartmentStatusDTO();
			if (departamento.getDepartmentStatus().equals("A")) {
				changeDepartmentStatus.setStatus("A");
			} else {
				changeDepartmentStatus.setStatus("X");
			}

			// actualizamos estado de los dos registros de departamento en orpak

			if (cliente.isCredit() && !ClientConstants.CLIENT_SCI.equals(cliente.getClientType())) {

				changeDepartmentStatus.setCustomerCode(cliente.getCustomerCodeClient());
				changeDepartmentStatus.setFleetDepartment(departamento.getCodeOrpakClient());
				loggerProcessChangeDeptCardStatus
						.debug("Actualizar departamento credito " + changeDepartmentStatus.toString());
				ResponseDTO retorno = orpakWSService.updateDepartmentStatus(changeDepartmentStatus);
				loggerProcessChangeDeptCardStatus.debug("" + retorno);

				MessageHistoryDTO message = new MessageHistoryDTO();
				message.setDateIns(LocalDateTime.now());
				message.setIdQueue(msg.getIdQueue());
				message.setReturnCode(retorno.getReturnCode());
				message.setUri(retorno.getUri());
				message.setMessage(retorno.getMessage());
				this.safService.pushMessageHistory(message);

			} else {
				if (ClientConstants.CLIENT_SCI.equals(cliente.getClientType())) {
					if (departamento.getCodeOrpakInvoice() != null) {
						changeDepartmentStatus.setCustomerCode(cliente.getCustomerCodeClient());
						changeDepartmentStatus.setFleetDepartment(departamento.getCodeOrpakInvoice());
						log.info("Ejecutar interfaz Orpak " + changeDepartmentStatus.toString());
					}
					if (departamento.getCodeOrpakTicket() != null) {
						changeDepartmentStatus.setCustomerCode(cliente.getCustomerCodeClient());
						changeDepartmentStatus.setFleetDepartment(departamento.getCodeOrpakTicket());
						log.info("Ejecutar interfaz Orpak " + changeDepartmentStatus.toString());
					}
					ResponseDTO retorno = orpakWSService.updateDepartmentStatus(changeDepartmentStatus);
					loggerProcessChangeDeptCardStatus.debug("" + retorno);

					MessageHistoryDTO message = new MessageHistoryDTO();
					message.setDateIns(LocalDateTime.now());
					message.setIdQueue(msg.getIdQueue());
					message.setReturnCode(retorno.getReturnCode());
					message.setUri(retorno.getUri());
					message.setMessage(retorno.getMessage());
					this.safService.pushMessageHistory(message);
				} else {

					// primer envio Prepago
					if (cliente.getCustomerCodeTicket() != null) {
						changeDepartmentStatus.setCustomerCode(cliente.getCustomerCodeTicket());
						changeDepartmentStatus.setFleetDepartment(departamento.getCodeOrpakTicket());
						loggerProcessChangeDeptCardStatus
								.debug("Actualizar departamento boleta " + changeDepartmentStatus.toString());
						ResponseDTO retorno = orpakWSService.updateDepartmentStatus(changeDepartmentStatus);
						loggerProcessChangeDeptCardStatus.debug("" + retorno);

						MessageHistoryDTO message = new MessageHistoryDTO();
						message.setDateIns(LocalDateTime.now());
						message.setIdQueue(msg.getIdQueue());
						message.setReturnCode(retorno.getReturnCode());
						message.setUri(retorno.getUri());
						message.setMessage(retorno.getMessage());
						this.safService.pushMessageHistory(message);
					}
					// segundo envio
					if (cliente.getCustomerCodeInvoice() != null) {
						changeDepartmentStatus.setCustomerCode(cliente.getCustomerCodeInvoice());
						changeDepartmentStatus.setFleetDepartment(departamento.getCodeOrpakInvoice());
						loggerProcessChangeDeptCardStatus
								.debug("Actualizar departamento factura " + changeDepartmentStatus.toString());
						ResponseDTO retorno = orpakWSService.updateDepartmentStatus(changeDepartmentStatus);
						loggerProcessChangeDeptCardStatus.debug("" + retorno);

						MessageHistoryDTO message = new MessageHistoryDTO();
						message.setDateIns(LocalDateTime.now());
						message.setIdQueue(msg.getIdQueue());
						message.setReturnCode(retorno.getReturnCode());
						message.setUri(retorno.getUri());
						message.setMessage(retorno.getMessage());
						this.safService.pushMessageHistory(message);
					}
				}
			}

			// Se actualiza el estado de las tarjetas del departamento.

			List<CardDTO> cards = new ArrayList<>();
			cards = this.clientService.getCardsByDepartment(msg.getIdElement(), true);

			for (CardDTO card : cards) {
				StatusCardDTO cardStatus = new StatusCardDTO();
				cardStatus.setCardnum(card.getCardnum());
				if (ClientConstants.CARD_DAFAULT_STATUS.equals(card.getCardStatus())) {
					// No toca la tarjeta en estado Pendiente.
				} else {
					cardStatus.setCardStatus(
							changeDepartmentStatus.getStatus() == "X" ? ClientConstants.CARD_STATUS_INACTIVE
									: ClientConstants.CARD_STATUS_ACTIVE);

					cardStatus.setIdCard(card.getIdCard());
					this.clientService.updCardStatus(cardStatus);

					CardHistoryDTO cardHistory = new CardHistoryDTO();
					if (cardStatus.getCardStatus().equals(ClientConstants.CARD_STATUS_INACTIVE)) {
						cardHistory.setAction("Cambio de estado : Inactivo");
					} else {
						cardHistory.setAction("Cambio de estado : Activo");
					}
					cardHistory.setActionType(ClientConstants.CAMBIO_STATUS);
					cardHistory.setDate(LocalDateTime.now());
					cardHistory.setIdCard(card.getIdCard());
					cardHistory.setIdUser(0L);
					cardHistory.setUsername("System");
					this.clientService.addCardHistory(cardHistory);
				}

			}
//			}
			// actualizamos la cola con status OK
			msg.setDateSend(LocalDateTime.now());
			msg.setStatus(SafConstants.SAF_STATUS_SENDED);
			safService.updateMessage(msg);

		} catch (OrpakWSExceptions e) {
			loggerProcessChangeDeptCardStatus.error("Ha ocurrido un error en el proceso. " + e.getMessage());
			loggerProcessChangeDeptCardStatus.error("" + e.getUri());
			ProcessOrpakException exc = new ProcessOrpakException(e.getMessage());
			exc.setReturnCode(e.getReturnCode());
			exc.setUri(e.getUri());
			throw exc;
		} catch (ClientException | ClientNotFoundException | SafException e) {
			// silent catch, actualizamos la cola con status PENDING y reintentos +1, y
			// sigue procesando
			loggerProcessChangeDeptCardStatus.warn("Departamento no pudo ser actualizado. " + e.getMessage());
			throw new ProcessOrpakException(e.getMessage());
		} catch (DepartmentException e) {
			loggerProcessChangeDeptCardStatus.warn("Departamento no pudo ser actualizado. " + e.getMessage());
			throw new ProcessOrpakException(e.getMessage());
		} catch (CardException e) {
			loggerProcessChangeDeptCardStatus.warn("Tarjeta no pudo ser actualizada. " + e.getMessage());
			throw new ProcessOrpakException(e.getMessage());
		}
//		catch (ParseException e) {
//			loggerProcessChangeDeptCardStatus.warn("Tarjeta no pudo ser actualizada. La fecha no pudo convertirse " + e.getMessage());
//			throw new ProcessOrpakException( e.getMessage() );
//		} 

	}

	@Override
	public void updChangeDepartmentStatusCond(MessageDTO msg) throws ProcessOrpakException {
		loggerProcessChangeDeptStatusCond.debug("SAF UTB3 id: " + msg.getIdQueue());

		try {

			int total = this.safService.getTotalSafConditionalsPendings(SafConstants.SAF_UPDATE_CARD_NUMBER,
					msg.getIdQueue());
			loggerProcessChangeDeptStatusCond
					.info("Existen " + total + " SAF UCB pendientes para el proceso " + msg.getIdQueue());

			if (total > 0) {
				loggerProcessChangeDeptStatusCond.debug("No se ejecuta el proceso " + msg.getIdQueue() + ". Hay "
						+ total + " saf pendientes del tipo: " + SafConstants.SAF_UPDATE_CARD_BALANCE);
				throw new SafException("No se ejecuta el proceso " + msg.getIdQueue() + ". Hay " + total
						+ " saf pendientes del tipo: " + SafConstants.SAF_UPDATE_CARD_NUMBER);
			}

			loggerProcessChangeDeptStatusCond.debug("Todas las SAF UCN procesadas. Se ejecuta el siguiente proceso.");

			// A proceso UTB3 se le actualiza estado a S.
			msg.setDateSend(LocalDateTime.now());
			msg.setStatus(SafConstants.SAF_STATUS_SENDED);
			safService.updateMessage(msg);
			loggerProcessChangeDeptStatusCond.info("Sacar mensaje " + msg.getIdQueue() + " de cola SAF");

			// Se crea saf de tipo UpdateCardBalance(UCB)
			loggerProcessChangeDeptStatusCond
					.info("Grabar SAF para actualizar estado de departamento: " + msg.getIdElement());
			this.safService.pushMessageUpdateDepartmentStatus(msg.getIdElement());

			List<MonitorCardDTO> listadoTarjetas = new ArrayList<MonitorCardDTO>();
			loggerProcessChangeDeptStatusCond.info("Se recorren las tarjetas para grabar SAF para actualizar estado");
			log.info("Se recuperan las tarjetas asociadas al departamento " + msg.getIdElement());
			listadoTarjetas = this.clientService.getCardVehicleByDepartment(msg.getIdElement());
			log.debug("" + listadoTarjetas);

			for (MonitorCardDTO card : listadoTarjetas) {
				log.debug("tarjeta detalles: " + card.toString());
				// Se bloquea la tarjeta

				StatusCardDTO datos = new StatusCardDTO();
				datos.setCardnum(card.getNumTarjeta());
				datos.setCardStatus(ClientConstants.CARD_STATUS_ACTIVE);
				datos.setIdCard(card.getIdCard());
				this.clientService.updCardStatus(datos);
			}

			// Se inserta registro en saf history
			MessageHistoryDTO messageHistory = new MessageHistoryDTO();
			messageHistory.setDateIns(LocalDateTime.now());
			messageHistory.setIdQueue(msg.getIdQueue());
			messageHistory.setReturnCode("");
			messageHistory.setUri("");
			messageHistory.setMessage("OK");
			this.safService.pushMessageHistory(messageHistory);
			loggerProcessChangeDeptStatusCond.info("Se Graba SAF History para id queue: " + msg.getIdQueue());

		} catch (SafException e) {
			loggerProcessChangeDeptStatusCond.warn("Ha ocurrido un error en el proceso. " + e.getMessage());
			throw new ProcessOrpakException(e.getMessage());
		} catch (CardException e) {
			loggerProcessChangeDeptStatusCond.warn("Ha ocurrido un error en el proceso. " + e.getMessage());
			throw new ProcessOrpakException(e.getMessage());
		}

	}

	@Override
	public void updPendingDepartmentStatusChange(MessageDTO msg) throws ProcessOrpakException {
		try {

			// consultamos por el departamento que viene en el mensaje
			DepartmentDTO dep = this.clientService.getListDepartmentById(msg.getIdElement());
			loggerProcessChangeDeptStatus.info("Consultar department " + msg.getIdElement());
			loggerProcessChangeDeptStatus.debug("" + dep);

			// consultamos por el cliente
			ClientDTO cliente = clientService.getClientById(dep.getIdClient());
			loggerProcessChangeDeptStatus.info("Consultar client " + dep.getIdClient());
			loggerProcessChangeDeptStatus.debug("" + cliente);

			ChangeDepartmentStatusDTO dto = new ChangeDepartmentStatusDTO();
			if (dep.getDepartmentStatus().equals("A")) {
				dto.setStatus("A");
			} else {
				dto.setStatus("X");
			}

			// actualizamos estado de los dos registros de departamento en orpak

			ResponseDTO retorno = new ResponseDTO();

			if (cliente.isCredit() && !ClientConstants.CLIENT_SCI.equals(cliente.getClientType())) {
				dto.setCustomerCode(cliente.getCustomerCodeClient());
				dto.setFleetDepartment(dep.getCodeOrpakClient());
				loggerProcessChangeDeptStatus.debug("Actualizar departamento cr�dito " + dto.toString());
				retorno = orpakWSService.updateDepartmentStatus(dto);
				loggerProcessChangeDeptStatus.debug("" + retorno);

				MessageHistoryDTO message = new MessageHistoryDTO();
				message.setDateIns(LocalDateTime.now());
				message.setIdQueue(msg.getIdQueue());
				message.setReturnCode(retorno.getReturnCode());
				message.setUri(retorno.getUri());
				message.setMessage(retorno.getMessage());
				this.safService.pushMessageHistory(message);

			} else {
				if (ClientConstants.CLIENT_SCI.equals(cliente.getClientType())) {
					if (dep.getCodeOrpakInvoice() != null) {
						dto.setCustomerCode(cliente.getCustomerCodeClient());
						dto.setFleetDepartment(dep.getCodeOrpakInvoice());
						log.info("Ejecutar interfaz Orpak " + dto.toString());
					}
					if (dep.getCodeOrpakTicket() != null) {
						dto.setCustomerCode(cliente.getCustomerCodeClient());
						dto.setFleetDepartment(dep.getCodeOrpakTicket());
						log.info("Ejecutar interfaz Orpak " + dto.toString());
					}
					retorno = orpakWSService.updateDepartmentStatus(dto);
					loggerProcessChangeDeptStatus.debug("" + retorno);

					MessageHistoryDTO message = new MessageHistoryDTO();
					message.setDateIns(LocalDateTime.now());
					message.setIdQueue(msg.getIdQueue());
					message.setReturnCode(retorno.getReturnCode());
					message.setUri(retorno.getUri());
					message.setMessage(retorno.getMessage());
					this.safService.pushMessageHistory(message);
				} else {

					// primer env�o Prepago
					if (cliente.getCustomerCodeTicket() != null) {
						dto.setCustomerCode(cliente.getCustomerCodeTicket());
						dto.setFleetDepartment(dep.getCodeOrpakTicket());
						loggerProcessChangeDeptStatus.debug("Actualizar departamento boleta " + dto.toString());
						retorno = orpakWSService.updateDepartmentStatus(dto);
						loggerProcessChangeDeptStatus.debug("" + retorno);

						MessageHistoryDTO message = new MessageHistoryDTO();
						message.setDateIns(LocalDateTime.now());
						message.setIdQueue(msg.getIdQueue());
						message.setReturnCode(retorno.getReturnCode());
						message.setUri(retorno.getUri());
						message.setMessage(retorno.getMessage());
						this.safService.pushMessageHistory(message);
					}
					// segundo env�o
					if (cliente.getCustomerCodeInvoice() != null) {
						dto.setCustomerCode(cliente.getCustomerCodeInvoice());
						dto.setFleetDepartment(dep.getCodeOrpakInvoice());
						loggerProcessChangeDeptStatus.debug("Actualizar departamento factura " + dto.toString());
						retorno = orpakWSService.updateDepartmentStatus(dto);
						loggerProcessChangeDeptStatus.debug("" + retorno);

						MessageHistoryDTO message = new MessageHistoryDTO();
						message.setDateIns(LocalDateTime.now());
						message.setIdQueue(msg.getIdQueue());
						message.setReturnCode(retorno.getReturnCode());
						message.setUri(retorno.getUri());
						message.setMessage(retorno.getMessage());
						this.safService.pushMessageHistory(message);
					}
				}
			}
//			}
			// actualizamos la cola con status OK
			msg.setDateSend(LocalDateTime.now());
			msg.setStatus(SafConstants.SAF_STATUS_SENDED);
			safService.updateMessage(msg);

		} catch (OrpakWSExceptions e) {
			loggerProcessChangeCardStatus.error("Ha ocurrido un error en el proceso. " + e.getMessage());
			loggerProcessChangeCardStatus.error("" + e.getUri());
			ProcessOrpakException exc = new ProcessOrpakException(e.getMessage());
			exc.setReturnCode(e.getReturnCode());
			exc.setUri(e.getUri());
			throw exc;
		} catch (ClientException | ClientNotFoundException | SafException e) {
			// silent catch, actualizamos la cola con status PENDING y reintentos +1, y
			// sigue procesando
			loggerProcessChangeCardStatus.warn("Tarjeta no pudo ser actualizada. " + e.getMessage());
			throw new ProcessOrpakException(e.getMessage());
		}
	}

	public void updTypeBalance1Litros(MessageDTO msg) throws ProcessOrpakException {
		loggerProcessChangeTypeBalance1Litros.info("Type balance condicional 1 LT " + msg.getIdElement());

		try {

			int total = this.safService.getTotalSafConditionalsPendings(SafConstants.SAF_LOAD_QUANTITY_CARD,
					msg.getIdQueue());
			loggerProcessChangeTypeBalance1Litros
					.debug("Existen " + total + " SAF LQC pendientes para el proceso " + msg.getIdQueue());

			if (total > 0) {
				loggerProcessChangeTypeBalance1Litros.debug("No se ejecuta el proceso " + msg.getIdQueue() + ". Hay "
						+ total + " saf pendientes del tipo: " + SafConstants.SAF_LOAD_QUANTITY_CARD);
				throw new SafException("No se ejecuta el proceso " + msg.getIdQueue() + ". Hay " + total
						+ " saf pendientes del tipo: " + SafConstants.SAF_LOAD_QUANTITY_CARD);
			}

			loggerProcessChangeTypeBalance1Litros.debug("Todas las SAF LQC procesadas. Se ejecuta el proceso.");

			// Se obtienen saldos, tipo de documento y producto desde mensaje saf.
			String data = msg.getData();
			loggerProcessChangeTypeBalance1Litros
					.info("Se obtienen litros, tipo de documento y producto desde mensaje SAF: " + data);

			if (data == null || data.equals("")) {
				loggerProcessChangeTypeBalance1Litros
						.error("No existe informacion para actualizar litros de departamento " + msg.getIdElement());
				throw new SafException(
						"No existe informaci�n para actualizar litros de departamento " + msg.getIdElement());
			}

			// A proceso UTB1 se le actualiza estado a S.
			msg.setDateSend(LocalDateTime.now());
			msg.setStatus(SafConstants.SAF_STATUS_SENDED);
			safService.updateMessage(msg);
			loggerProcessChangeTypeBalance1.info("Sacar mensaje " + msg.getIdQueue() + " de cola SAF");

			// Se llama al siguiente proceso: UTB2
			MessageDTO message = new MessageDTO();
			message.setType(SafConstants.SAF_CONDITIONAL_LT_2);
			message.setIdElement(msg.getIdElement());
			Long idUtb2 = this.safService.pushMessageSafConditional(message);
			loggerProcessChangeTypeBalance1Litros.info("Se crea saf UTBL2 con id: " + idUtb2);

			String[] listData = data.split(",");
			for (int i = 0; i < listData.length; i++) {

				ProductDepartmentDTO criterio = new ProductDepartmentDTO();
				String objeto = listData[i];
				String[] elementos = objeto.split(";");
				String tipoDoc = elementos[0];
				String producto = elementos[1];
				String saldo = elementos[2];
				loggerProcessChangeTypeBalance1Litros.debug("Los valores obtenidos de data son: Tipo documento: "
						+ tipoDoc + ", Producto: " + producto + ", Litros: " + saldo);

				// Se setean valores en criterio
				criterio.setDocumentType(tipoDoc);
				criterio.setProductCode(producto);
				criterio.setIdDepartment(msg.getIdElement());
				loggerProcessChangeTypeBalance1Litros.debug("Departamento con id: " + msg.getIdElement());

				// Se consulta si existe producto y tipo de documento en departamento.
				List<ProductDepartmentDTO> listadoProdDep = this.clientService
						.doProductsDepartmentsByCriteria(criterio);
				loggerProcessChangeTypeBalance1Litros.debug("" + listadoProdDep);

				BigDecimal saldoDepto = BigDecimal.ZERO;

				if (listadoProdDep.size() > 0) {
					loggerProcessChangeTypeBalance1Litros
							.debug("Litros actuales de departamento: " + listadoProdDep.get(0).getRemainingAmount());

					// Se actualiza saldo
					saldoDepto = new BigDecimal(saldo);
					criterio.setRemainingAmount(saldoDepto);
					this.clientService.updDepartmentProductAmount(criterio);
					loggerProcessChangeTypeBalance1Litros.debug(
							"Producto Departamento ha sido actualizado con nueva cantidad de litros: " + saldoDepto);

				} else {
					loggerProcessChangeTypeBalance1Litros
							.info("No existe el tipo de documento: " + tipoDoc + " y producto: " + producto
									+ " para el depto " + msg.getIdElement() + " por lo que se inserta.");
					saldoDepto = new BigDecimal(saldo);
					criterio.setRemainingAmount(saldoDepto);
					this.clientService.addProductDepartmentRel(criterio);
					loggerProcessChangeTypeBalance1Litros
							.debug("Producto Departamento ha sido creado con litros: " + saldo);
				}

				// Se crea saf para actualizar litros del departamento (UDB).
				loggerProcessChangeTypeBalance1Litros
						.info("Se Graba SAF para actualizar litros del departamento: " + msg.getIdElement());
				if (saldoDepto.compareTo(BigDecimal.ZERO) != 0) {
					this.safService.pushMessageUpdateDepartmentBalanceConditional(msg.getIdElement(), saldoDepto,
							producto, tipoDoc, idUtb2);
				}

			}

			// Se inserta registro en saf history
			MessageHistoryDTO messageHistory = new MessageHistoryDTO();
			messageHistory.setDateIns(LocalDateTime.now());
			messageHistory.setIdQueue(msg.getIdQueue());
			messageHistory.setReturnCode("");
			messageHistory.setUri("");
			messageHistory.setMessage("OK");
			this.safService.pushMessageHistory(messageHistory);
			loggerProcessChangeTypeBalance1Litros.info("Se Graba SAF History para id queue: " + msg.getIdQueue());

		} catch (SafException | ProductDepartmentException | NuevoMontoException | DepartmentException e) {
			loggerProcessChangeTypeBalance1Litros.warn("Ha ocurrido un error en el proceso. " + e.getMessage());
			throw new ProcessOrpakException(e.getMessage());
		}
	}

	@Override
	public void updTypeBalance1(MessageDTO msg) throws ProcessOrpakException {

		loggerProcessChangeTypeBalance1.info("Type balance condicional 1 " + msg.getIdElement());

		try {

			int total = this.safService.getTotalSafConditionalsPendings(SafConstants.SAF_UPDATE_CARD_BALANCE,
					msg.getIdQueue());
			loggerProcessChangeTypeBalance1
					.debug("Existen " + total + " SAF UCB pendientes para el proceso " + msg.getIdQueue());

			if (total > 0) {
				loggerProcessChangeTypeBalance1.debug("No se ejecuta el proceso " + msg.getIdQueue() + ". Hay " + total
						+ " saf pendientes del tipo: " + SafConstants.SAF_UPDATE_CARD_BALANCE);
				throw new SafException("No se ejecuta el proceso " + msg.getIdQueue() + ". Hay " + total
						+ " saf pendientes del tipo: " + SafConstants.SAF_UPDATE_CARD_BALANCE);
			}

			loggerProcessChangeTypeBalance1.debug("Todas las SAF UCB procesadas. Se ejecuta el proceso.");

			// Se obtienen saldos, tipo de documento y producto desde mensaje saf.
			String data = msg.getData();
			loggerProcessChangeTypeBalance1
					.info("Se obtienen saldos, tipo de documento y producto desde mensaje SAF: " + data);

			if (data == null || data.equals("")) {
				loggerProcessChangeTypeBalance1
						.error("No existe informaci�n para actualizar saldo de departamento " + msg.getIdElement());
				throw new SafException(
						"No existe informaci�n para actualizar saldo de departamento " + msg.getIdElement());
			}

			// A proceso UTB1 se le actualiza estado a S.
			msg.setDateSend(LocalDateTime.now());
			msg.setStatus(SafConstants.SAF_STATUS_SENDED);
			safService.updateMessage(msg);
			loggerProcessChangeTypeBalance1.info("Sacar mensaje " + msg.getIdQueue() + " de cola SAF");

			// Se llama al siguiente proceso: UTB2
			MessageDTO message = new MessageDTO();
			message.setType(SafConstants.SAF_CONDITIONAL_2);
			message.setIdElement(msg.getIdElement());
			Long idUtb2 = this.safService.pushMessageSafConditional(message);
			loggerProcessChangeTypeBalance1.info("Se crea saf UTB2 con id: " + idUtb2);

			String[] listData = data.split(",");
			for (int i = 0; i < listData.length; i++) {

				ProductDepartmentDTO criterio = new ProductDepartmentDTO();
				String objeto = listData[i];
				String[] elementos = objeto.split(";");
				String tipoDoc = elementos[0];
				String producto = elementos[1];
				String saldo = elementos[2];
				loggerProcessChangeTypeBalance1.debug("Los valores obtenidos de data son: Tipo documento: " + tipoDoc
						+ ", Producto: " + producto + ", Saldo: " + saldo);

				// Se setean valores en criterio
				criterio.setDocumentType(tipoDoc);
				criterio.setProductCode(producto);
				criterio.setIdDepartment(msg.getIdElement());
				loggerProcessChangeTypeBalance1.debug("Departamento con id: " + msg.getIdElement());

				// Se consulta si existe producto y tipo de documento en departamento.
				List<ProductDepartmentDTO> listadoProdDep = this.clientService
						.doProductsDepartmentsByCriteria(criterio);
				loggerProcessChangeTypeBalance1.debug("" + listadoProdDep);

				BigDecimal saldoDepto = BigDecimal.ZERO;

				if (listadoProdDep.size() > 0) {
					loggerProcessChangeTypeBalance1
							.debug("Saldo actual de departamento: $ " + listadoProdDep.get(0).getRemainingAmount());

					// Se actualiza saldo
					// saldoDepto = listadoProdDep.get(0).getRemainingAmount().add(new
					// BigDecimal(saldo));
					saldoDepto = new BigDecimal(saldo);
					criterio.setRemainingAmount(saldoDepto);
					this.clientService.updDepartmentProductAmount(criterio);
					loggerProcessChangeTypeBalance1
							.debug("Producto Departamento ha sido actualizado con nuevo saldo: $" + saldoDepto);

				} else {
					loggerProcessChangeTypeBalance1.info("No existe el tipo de documento: " + tipoDoc + " y producto: "
							+ producto + " para el depto " + msg.getIdElement() + " por lo que se inserta.");
					saldoDepto = new BigDecimal(saldo);
					criterio.setRemainingAmount(saldoDepto);
					this.clientService.addProductDepartmentRel(criterio);
					loggerProcessChangeTypeBalance1.debug("Producto Departamento ha sido creado con saldo: $" + saldo);
				}

				// Se crea saf para actualizar saldo del departamento (UDB).
				loggerProcessChangeTypeBalance1
						.info("Se Graba SAF para actualizar saldo del departamento: " + msg.getIdElement());
				if (saldoDepto.compareTo(BigDecimal.ZERO) != 0) {
					this.safService.pushMessageUpdateDepartmentBalanceConditional(msg.getIdElement(), saldoDepto,
							producto, tipoDoc, idUtb2);
				}

			}

			// Se inserta registro en saf history
			MessageHistoryDTO messageHistory = new MessageHistoryDTO();
			messageHistory.setDateIns(LocalDateTime.now());
			messageHistory.setIdQueue(msg.getIdQueue());
			messageHistory.setReturnCode("");
			messageHistory.setUri("");
			messageHistory.setMessage("OK");
			this.safService.pushMessageHistory(messageHistory);
			loggerProcessChangeTypeBalance1.info("Se Graba SAF History para id queue: " + msg.getIdQueue());

		} catch (SafException | ProductDepartmentException | NuevoMontoException | DepartmentException e) {
			loggerProcessChangeTypeBalance1.warn("Ha ocurrido un error en el proceso. " + e.getMessage());
			throw new ProcessOrpakException(e.getMessage());
		}

	}

	@Override
	public void updTypeBalance2Litros(MessageDTO msg) throws ProcessOrpakException {

		loggerProcessChangeTypeBalance2Litros.info("Type balance condicional 2 Litros" + msg.getIdElement());

		try {

			int total = this.safService.getTotalSafConditionalsPendings(SafConstants.SAF_UPDATE_DEPARTMENT_BALANCE,
					msg.getIdQueue());
			loggerProcessChangeTypeBalance2Litros
					.info("Existen " + total + " SAF UDB pendientes para el proceso " + msg.getIdQueue());

			if (total > 0) {
				loggerProcessChangeTypeBalance2Litros.info("No se ejecuta el proceso " + msg.getIdQueue() + ". Hay "
						+ total + " saf pendientes del tipo: " + SafConstants.SAF_UPDATE_DEPARTMENT_BALANCE);
				throw new SafException("No se ejecuta el proceso " + msg.getIdQueue() + ". Hay " + total
						+ " saf pendientes del tipo: " + SafConstants.SAF_UPDATE_DEPARTMENT_BALANCE);
			}

			loggerProcessChangeTypeBalance2Litros.info("Todas las SAF UDB procesadas. Se ejecuta el proceso.");

			// A proceso UTB2 se actualiza estado a S.
			msg.setDateSend(LocalDateTime.now());
			msg.setStatus(SafConstants.SAF_STATUS_SENDED);
			safService.updateMessage(msg);
			loggerProcessChangeTypeBalance2Litros.info("Sacar mensaje " + msg.getIdQueue() + " de cola SAF");

			List<MonitorCardDTO> listadoTarjetas = new ArrayList<MonitorCardDTO>();

			// Se obtienen tarjetas asociadas al departamento
			try {
				loggerProcessChangeTypeBalance2Litros.info("Obtener listado de tarjetas");
				listadoTarjetas = this.clientService.getCardVehicleByDepartment(msg.getIdElement());
				loggerProcessChangeTypeBalance2Litros.debug("" + listadoTarjetas);
			} catch (CardException e) {
				loggerProcessChangeTypeBalance2Litros
						.error("Ha ocurrido un error al obtener listado: " + e.getMessage());
				throw new ClientException(e.getMessage());
			}

			if (listadoTarjetas.size() <= 0) {
				loggerProcessChangeTypeBalance2Litros.warn("Departamento no tiene tarjetas asociadas.");
			}

			// Se llama al siguiente proceso: UTBL3
			MessageDTO message = new MessageDTO();
			message.setType(SafConstants.SAF_CONDITIONAL_LT_3);
			message.setIdElement(msg.getIdElement());
			Long idUtb3 = this.safService.pushMessageSafConditional(message);
			loggerProcessChangeTypeBalance2Litros.info("Se crea saf UTBL3 con id: " + idUtb3);

			for (MonitorCardDTO card : listadoTarjetas) {
				// Se obtiene id de vehiculo
				Long idVehicle = card.getIdVehicle();
				loggerProcessChangeTypeBalance2Litros.info("Grabar SAF para actualizar vehiculo: " + idVehicle);
				this.safService.pushMessageUpdateCardNumberConditional(idVehicle, idUtb3);
			}

			// Se inserta registro en saf history
			MessageHistoryDTO messageHistory = new MessageHistoryDTO();
			messageHistory.setDateIns(LocalDateTime.now());
			messageHistory.setIdQueue(msg.getIdQueue());
			messageHistory.setReturnCode("");
			messageHistory.setUri("");
			messageHistory.setMessage("OK");
			this.safService.pushMessageHistory(messageHistory);
			loggerProcessChangeTypeBalance2Litros.info("Se Graba SAF History para id queue: " + msg.getIdQueue());

		} catch (SafException | ClientException e) {
			loggerProcessChangeTypeBalance2Litros.warn("Ha ocurrido un error en el proceso. " + e.getMessage());
			throw new ProcessOrpakException(e.getMessage());
		}

	}

	@Override
	public void updTypeBalance2(MessageDTO msg) throws ProcessOrpakException {

		loggerProcessChangeTypeBalance2.info("Type balance condicional 2 " + msg.getIdElement());

		try {

			int total = this.safService.getTotalSafConditionalsPendings(SafConstants.SAF_UPDATE_DEPARTMENT_BALANCE,
					msg.getIdQueue());
			loggerProcessChangeTypeBalance2
					.info("Existen " + total + " SAF UCB pendientes para el proceso " + msg.getIdQueue());

			if (total > 0) {
				loggerProcessChangeTypeBalance2.info("No se ejecuta el proceso " + msg.getIdQueue() + ". Hay " + total
						+ " saf pendientes del tipo: " + SafConstants.SAF_UPDATE_DEPARTMENT_BALANCE);
				throw new SafException("No se ejecuta el proceso " + msg.getIdQueue() + ". Hay " + total
						+ " saf pendientes del tipo: " + SafConstants.SAF_UPDATE_DEPARTMENT_BALANCE);
			}

			loggerProcessChangeTypeBalance2.info("Todas las SAF UDB procesadas. Se ejecuta el proceso.");

			// A proceso UTB2 se actualiza estado a S.
			msg.setDateSend(LocalDateTime.now());
			msg.setStatus(SafConstants.SAF_STATUS_SENDED);
			safService.updateMessage(msg);
			loggerProcessChangeTypeBalance2.info("Sacar mensaje " + msg.getIdQueue() + " de cola SAF");

			List<MonitorCardDTO> listadoTarjetas = new ArrayList<MonitorCardDTO>();

			// Se obtienen tarjetas asociadas al departamento
			try {
				loggerProcessChangeTypeBalance2.info("Obtener listado de tarjetas");
				listadoTarjetas = this.clientService.getCardVehicleByDepartment(msg.getIdElement());
				loggerProcessChangeTypeBalance2.debug("" + listadoTarjetas);
			} catch (CardException e) {
				loggerProcessChangeTypeBalance2.error("Ha ocurrido un error al obtener listado: " + e.getMessage());
				throw new ClientException(e.getMessage());
			}

			if (listadoTarjetas.size() <= 0) {
				loggerProcessChangeTypeBalance2.warn("Departamento no tiene tarjetas asociadas.");
			}

			// Se llama al siguiente proceso: UTB3
			MessageDTO message = new MessageDTO();
			message.setType(SafConstants.SAF_CONDITIONAL_3);
			message.setIdElement(msg.getIdElement());
			Long idUtb3 = this.safService.pushMessageSafConditional(message);
			loggerProcessChangeTypeBalance2.info("Se crea saf UTB3 con id: " + idUtb3);

			for (MonitorCardDTO card : listadoTarjetas) {
				// Se obtiene id de vehiculo
				Long idVehicle = card.getIdVehicle();
				loggerProcessChangeTypeBalance2.info("Grabar SAF para actualizar vehiculo: " + idVehicle);
				this.safService.pushMessageUpdateCardNumberConditional(idVehicle, idUtb3);
			}

			// Se inserta registro en saf history
			MessageHistoryDTO messageHistory = new MessageHistoryDTO();
			messageHistory.setDateIns(LocalDateTime.now());
			messageHistory.setIdQueue(msg.getIdQueue());
			messageHistory.setReturnCode("");
			messageHistory.setUri("");
			messageHistory.setMessage("OK");
			this.safService.pushMessageHistory(messageHistory);
			loggerProcessChangeTypeBalance2.info("Se Graba SAF History para id queue: " + msg.getIdQueue());

		} catch (SafException | ClientException e) {
			loggerProcessChangeTypeBalance2.warn("Ha ocurrido un error en el proceso. " + e.getMessage());
			throw new ProcessOrpakException(e.getMessage());
		}

	}

	@Override
	public void updOT(MessageDTO msg) throws ProcessOrpakException {

		if (msg == null || msg.getIdElement() == null || msg.getData() == null) {
			loggerProcessCreateUpdateOT.error("Error en par�metros de entrada " + msg);
			throw new ProcessOrpakException("Error en par�metros de entrada.");
		}

		loggerProcessCreateUpdateOT.info("Mensaje a procesar " + msg);
		try {
			CriterioOtHeaderDTO criterio = new CriterioOtHeaderDTO();
			criterio.setIdVehicle(msg.getIdElement());
			criterio.setStatus("C");

			CriterioOtHeaderDTO criterio2 = new CriterioOtHeaderDTO();
			criterio2.setIdVehicle(msg.getIdElement());
			criterio2.setStatus("P");

			CriterioOtHeaderDTO criterio3 = new CriterioOtHeaderDTO();
			criterio2.setIdVehicle(msg.getIdElement());
			criterio2.setStatus("X");

			try {
				// Obtener vehiculo
				VehicleDTO vehiculo = this.clientService.getVehicleDeviceById(msg.getIdElement(), msg.getData());
				// Obtener cliente
				ClientDTO cliente = this.clientService.getClientById(vehiculo.getIdClient());

				loggerProcessCreateUpdateOT
						.info("Se verifica si existe la OT: " + criterio + " y si no hay una orden en Trabajo");
				// Verificar si existe una OTHeader con criterio.
				if (this.clientService.existOTHeader(criterio)) {

					// Ya existe una OT habilitada para el vehiculo
					loggerProcessCreateUpdateOT
							.info("Existe una OT para el vehiculo " + msg.getIdElement() + " se procede a actualizar");
					// Obtengo la OT que cumple con el criterio.
					OtHeaderDTO otHeader = new OtHeaderDTO();
					otHeader = this.clientService.getListOtHeaderByCriterio(criterio).get(0);
					loggerProcessCreateUpdateOT.info("OT existente para el vehiculo " + otHeader);
					// Verificar que no exista un detalle para esa posicion
					List<OtItemDTO> item = this.clientService.getDetalleByidOT(otHeader.getIdOt(), msg.getData());
					if (item.size() > 0) {
						loggerProcessCreateUpdateOT.debug("Ya existe un item de la OT " + otHeader.getIdOt()
								+ " para la posicion " + msg.getData());
						throw new ProcessOrpakException("Ya existe un item de la OT " + otHeader.getIdOt()
								+ " para la posicion " + msg.getData());
					} else {
						// Crear un nuevo detalle de OT
						OtItemDTO nuevoDetalle = new OtItemDTO();
						nuevoDetalle.setIdOt(otHeader.getIdOt());
						nuevoDetalle.setPosicion(msg.getData());
						nuevoDetalle.setNumDispositivo(vehiculo.getCard().get(0).getCardnum());
						// dependiendo del tipo del mensaje se debe setear ins_type (tipo de
						// instalaci�n)
						if (SafConstants.SAF_CREATE_DEVICE_NUMBER.equals(msg.getType())) {
							nuevoDetalle.setTipoInstalacion("I");
						} else {
							nuevoDetalle.setTipoInstalacion("R");
						}

						loggerProcessCreateUpdateOT.info("Se guarda el nuevo item de la OT " + nuevoDetalle.toString());
						// Guardar detalleOT creado
						Long idNuevoItem = this.clientService.addItemOT(nuevoDetalle);
						loggerProcessCreateUpdateOT.info("Se ha ingresado el nuevo item de la OT " + idNuevoItem);
					}

				} else if (!this.clientService.existOTHeader(criterio) || this.clientService.existOTHeader(criterio2)
						|| this.clientService.existOTHeader(criterio3)) {

					// Si no existe, creo la OT

					loggerProcessCreateUpdateOT.info("Se ingresa una nueva OT para el veh�culo " + msg.getIdElement());

					// Crear nuevo DTO para la OT
					OtHeaderDTO nuevaOT = new OtHeaderDTO();
					nuevaOT.setIdClient(cliente.getIdClient());
					nuevaOT.setIdVehicle(vehiculo.getIdVehicle());
					nuevaOT.setDateIns(LocalDateTime.now());
					nuevaOT.setStatus("C");

					// Guardar OT creada
					loggerProcessCreateUpdateOT.info("Se ingresa nueva OT: " + nuevaOT);
					Long idNuevaOT = this.clientService.addOT(nuevaOT);

					// Crear nuevo DTO para detalleOT
					OtItemDTO nuevoDetalle = new OtItemDTO();
					nuevoDetalle.setIdOt(idNuevaOT);
					nuevoDetalle.setPosicion(msg.getData());
					nuevoDetalle.setNumDispositivo(vehiculo.getCard().get(0).getCardnum());
					// dependiendo del tipo del mensaje se debe setear ins_type (tipo de
					// instalaci�n)
					if (SafConstants.SAF_CREATE_DEVICE_NUMBER.equals(msg.getType())) {
						nuevoDetalle.setTipoInstalacion("I");
					} else {
						nuevoDetalle.setTipoInstalacion("R");
					}

					// Guardar detalleOT creado
					loggerProcessCreateUpdateOT.info("Se ingresa una item OT " + nuevoDetalle);
					Long idNuevoItem = this.clientService.addItemOT(nuevoDetalle);

				}
			} catch (OTException e) {
				loggerProcessCreateUpdateOT.debug("Error al obtener OT " + e);
				throw new ProcessOrpakException("Error al obtener la OT " + e);
			} catch (VehicleException e) {
				loggerProcessCreateUpdateOT.error("Error al obtener el vehiculo " + e);
				throw new ProcessOrpakException("Error al obtener el vehiculo " + e);
			} catch (ClientException e) {
				loggerProcessCreateUpdateOT.error("Error al obtener el cliente " + e);
				throw new ProcessOrpakException("Error al obtener el cliente " + e);
			}

			loggerProcessCreateUpdateOT.info("Ingresar mensaje al historia de la SAF");
			MessageHistoryDTO message = new MessageHistoryDTO();
			message.setDateIns(LocalDateTime.now());
			message.setIdQueue(msg.getIdQueue());
			message.setMessage("Mensaje");
			this.safService.pushMessageHistory(message);

			// actualizamos la cola con status OK
			msg.setDateSend(LocalDateTime.now());
			msg.setStatus(SafConstants.SAF_STATUS_SENDED);
			safService.updateMessage(msg);
			loggerProcessCreateUpdateOT.info("Sacar mensaje " + msg.getIdQueue() + " de cola SAF");

		} catch (SafException e) {
			loggerProcessCreateUpdateOT.warn("OT no pudo ser creada/modificada. " + e.getMessage());
			throw new ProcessOrpakException(e.getMessage());
		}

	}

	@Override
	public void deleteDepartmentOfFleet(MessageDTO msg) throws ProcessOrpakException {

		loggerProcessDeleteDepartmentFleet.info("Actualizar saldo de la tarjeta " + msg.getIdElement());

		try {

			// Obtenemos el departamento indicado en el mensaje
			DepartmentDTO department = clientService.getListDepartmentById(msg.getIdElement());
			loggerProcessDeleteDepartmentFleet.info("Recuperar departamento " + msg.getIdElement());
			loggerProcessDeleteDepartmentFleet.debug("" + department.toString());

			// consultar cliente
			ClientDTO client = clientService.getClientById(department.getIdClient());
			loggerProcessDeleteDepartmentFleet.info("Recuperar cliente " + department.getIdClient());
			loggerProcessDeleteDepartmentFleet.debug("" + client.toString());

			// dto para consultar servicio
			CreateDepartmentOfFleetDTO dto = new CreateDepartmentOfFleetDTO();
			dto.setBalancetype(OrpakWSConstants.ORPAK_WS_CREATE_DEPARTMENT_OFFLEET_BALANCETYPE);
			dto.setCustomercode(client.getCustomerCodeClient());
			dto.setDeptcode("" + department.getIdDepartment());
			dto.setStockcode(ClientConstants.PRODUCT_CODE_DEFAULT);
			dto.setStocktype(OrpakWSConstants.ORPAK_WS_CREATE_DEPARTMENT_OFFLEET_STOCKTYPE);

			loggerProcessDeleteDepartmentFleet.info("Aumentar saldo de la tarjeta en Orpak.");
			loggerProcessDeleteDepartmentFleet.debug("" + dto.toString());

			// actualizamos estado del departamento
			ResponseDTO retorno = new ResponseDTO();
			retorno = orpakWSService.deleteDepartmentOfFleet(dto);
			loggerProcessDeleteDepartmentFleet.info("" + retorno);

			loggerProcessDeleteDepartmentFleet.info("Ingresar mensaje al historia de la SAF");
			MessageHistoryDTO message = new MessageHistoryDTO();
			message.setDateIns(LocalDateTime.now());
			message.setIdQueue(msg.getIdQueue());
			message.setReturnCode(retorno.getReturnCode());
			message.setUri(retorno.getUri());
			message.setMessage(retorno.getMessage());
			this.safService.pushMessageHistory(message);

			// actualizamos la cola con status OK
			msg.setDateSend(LocalDateTime.now());
			msg.setStatus(SafConstants.SAF_STATUS_SENDED);
			safService.updateMessage(msg);
			loggerProcessDeleteDepartmentFleet.info("Sacar mensaje " + msg.getIdQueue() + " de cola SAF");

		} catch (OrpakWSExceptions e) {
			loggerProcessDeleteDepartmentFleet.error("Ha ocurrido un error en el proceso. " + e.getMessage());
			loggerProcessDeleteDepartmentFleet.error("" + e.getUri());
			ProcessOrpakException exc = new ProcessOrpakException(e.getMessage());
			exc.setReturnCode(e.getReturnCode());
			exc.setUri(e.getUri());
			throw exc;
		} catch (ClientException | SafException e) {
			// silent catch, actualizamos la cola con status PENDING y reintentos +1, y
			// sigue procesando
			loggerProcessDeleteDepartmentFleet.warn("Departamento no pudo ser actualizado. " + e.getMessage());
			throw new ProcessOrpakException(e.getMessage());
		} catch (ClientNotFoundException e) {
			loggerProcessDeleteDepartmentFleet
					.warn("Departamento no pudo ser actualizado, no se encontro el cliente. " + e.getMessage());
			throw new ProcessOrpakException(e.getMessage());
		}

	}

	@Override
	public void updPendingCardBalance(MessageDTO msg) throws ProcessOrpakException {

		loggerProcessSendCardBalance.info("Actualizar saldo de la tarjeta " + msg.getIdElement());

		CardDTO card = null;

		try {

			// consultamos por la tarjeta que viene en el mensaje
			card = this.clientService.getCardById(msg.getIdElement());
			loggerProcessSendCardBalance.info("Recuperar tarjeta " + msg.getIdElement());
			loggerProcessSendCardBalance.debug("" + card.toString());

			// consultar cliente
			ClientDTO client = clientService.getClientById(card.getIdClient());
			loggerProcessSendCardBalance.info("Recuperar cliente " + card.getIdClient());
			loggerProcessSendCardBalance.debug("" + client.toString());

			// consultamos el veh�culo asociado a la tarjeta
			VehicleDTO vehicle = clientService.getVehicleByIdCard(card.getIdCard());
			loggerProcessSendCardBalance.info("Recuperar vehiculo " + card.getIdCard());
			loggerProcessSendCardBalance.debug("" + vehicle.toString());

			// extraemos la data del mensaje
			Double monto = Double.parseDouble(msg.getData()); // en la data va el valor cargado

			loggerProcessSendCardBalance.info("Cargar a tarjeta " + card.getCardnum() + " monto " + monto);

			// dto para consultar servicio
			UpdCardBalanceDTO dto = new UpdCardBalanceDTO();
			dto.setAmount(monto);
			dto.setCardNumber(card.getCardnum());

			// Si el cliente es credito y es SCI se debe enviar la cuenta C como
			// customercode
			// Se obtienen datos del cliente.
			if ((client.isCredit() && ClientConstants.CLIENT_SCI.equals(client.getClientType()))
					|| ClientConstants.CLIENT_SCS.equals(client.getClientType())) {
				dto.setCustomerCode(client.getCustomerCodeClient());
				loggerProcessSendCardBalance.info("Tomar customerCode de cliente.");
			} else {
				if (vehicle.getDocumentType().equals(ClientConstants.DOCUMENT_TYPE_TICKET)) {
					dto.setCustomerCode(client.getCustomerCodeTicket());
					loggerProcessSendCardBalance.info("Tomar customerCode de boleta.");
				} else {
					dto.setCustomerCode(client.getCustomerCodeInvoice());
					loggerProcessSendCardBalance.info("Tomar customerCode de factura.");
				}
			}
			dto.setFuelCode(vehicle.getProductCode());

			if (msg.getTask() == null) {
				dto.setTransactionId("" + msg.getIdQueue()); // el idTransaction ser� el idQueue
			} else {
				dto.setTransactionId("" + msg.getTask());
			}

			loggerProcessSendCardBalance.info("Aumentar saldo de la tarjeta en Orpak.");
			loggerProcessSendCardBalance.debug("" + dto.toString());

			if (monto.doubleValue() != 0) {

				// actualizamos estado del departamento
				ResponseDTO retorno = new ResponseDTO();
				try {
					retorno = orpakWSService.updateCardBalance(dto);
				} catch (OrpakWSTrxCardBalanceExistExceptions e) {
					loggerProcessSendCardBalance.info("Trx ya existe en Orpak. Se debe continuar. " + e.getMessage());
					retorno.setMessage(e.getMessage());
					retorno.setReturnCode(e.getReturnCode());
					retorno.setUri(e.getUri());
				}
				loggerProcessSendCardBalance.info("" + retorno);

				loggerProcessSendCardBalance.info("Ingresar mensaje al historia de la SAF");
				MessageHistoryDTO message = new MessageHistoryDTO();
				message.setDateIns(LocalDateTime.now());
				message.setIdQueue(msg.getIdQueue());
				message.setReturnCode(retorno.getReturnCode());
				message.setUri(retorno.getUri());
				message.setMessage(retorno.getMessage());
				this.safService.pushMessageHistory(message);

			} else {
				loggerProcessSendCardBalance.info("No se env�a a Orpak ya que el monto a enviar es cero. " + dto);
			}

			// actualizamos la cola con status OK
			msg.setDateSend(LocalDateTime.now());
			msg.setStatus(SafConstants.SAF_STATUS_SENDED);
			safService.updateMessage(msg);
			loggerProcessSendCardBalance.info("Sacar mensaje " + msg.getIdQueue() + " de cola SAF");

			// Actualizar saldo de tarjeta
			this.clientService.updCardBalance(card.getIdCard(), card.getCardnum());

		} catch (OrpakWSExceptions e) {
			loggerProcessSendCardBalance.error("Ha ocurrido un error en el proceso. " + e.getMessage());
			loggerProcessSendCardBalance.error("" + e.getUri());
			ProcessOrpakException exc = new ProcessOrpakException(e.getMessage());
			exc.setReturnCode(e.getReturnCode());
			exc.setUri(e.getUri());
			throw exc;
		} catch (ClientException | CardException | SafException e) {
			// silent catch, actualizamos la cola con status PENDING y reintentos +1, y
			// sigue procesando
			loggerProcessSendCardBalance.warn("Tarjeta no pudo ser actualizada. " + e.getMessage());
			throw new ProcessOrpakException(e.getMessage());
		} catch (VehicleException e) {
			loggerProcessSendCardBalance.warn("Error al recuperar veh�culo. " + e.getMessage());
			loggerProcessSendCardBalance.info("Validar si es una tarjeta eliminada.");
			if (ClientConstants.CARD_STATUS_ELIMINADO.equals(card.getCardStatus())) {
				loggerProcessSendCardBalance.info("Tarjeta eliminada se saca la SAF");

				try {
					loggerProcessSendCardBalance.info("Ingresar mensaje al historia de la SAF");
					MessageHistoryDTO message = new MessageHistoryDTO();
					message.setDateIns(LocalDateTime.now());
					message.setIdQueue(msg.getIdQueue());
					message.setReturnCode("");
					message.setUri("");
					message.setMessage("Tarjeta eliminada se saca la SAF.");
					this.safService.pushMessageHistory(message);

					// actualizamos la cola con status OK
					msg.setDateSend(LocalDateTime.now());
					msg.setStatus(SafConstants.SAF_STATUS_DELETED);
					safService.updateMessage(msg);
					loggerProcessSendCardBalance.info("Sacar mensaje " + msg.getIdQueue() + " de cola SAF");
				} catch (SafException e1) {
					loggerProcessSendCardBalance.warn("No se pudo modificar estado al mensaje SAF. " + e.getMessage());
				}

			}
		}

	}

	@Override
	public void updCardConstraint(MessageDTO msg) throws ProcessOrpakException {
		loggerProcessSendCardConstraint.info("Actualizar restricciones de la tarjeta " + msg.getIdElement());

		try {

			// consultamos por la tarjeta que viene en el mensaje
			CardDTO card = this.clientService.getCardById(msg.getIdElement());
			loggerProcessSendCardConstraint.info("Recuperar tarjeta " + msg.getIdElement());
			loggerProcessSendCardConstraint.debug("" + card.toString());

			// consultar cliente
			ClientDTO client = clientService.getClientById(card.getIdClient());
			loggerProcessSendCardConstraint.info("Recuperar cliente " + card.getIdClient());
			loggerProcessSendCardConstraint.debug("" + client.toString());

			// consultamos el veh�culo asociado a la tarjeta
			VehicleDTO vehicle = clientService.getVehicleByIdCard(card.getIdCard());
			loggerProcessSendCardConstraint.info("Recuperar vehiculo " + card.getIdCard());
			loggerProcessSendCardConstraint.debug("" + vehicle.toString());

			// Consultamos el departamento asociado a la tarjeta
			DepartmentDTO depto = clientService.getListDepartmentById(vehicle.getIdDepartment());
			loggerProcessSendCardConstraint.info("Recuperar departamento " + vehicle.getIdDepartment());
			loggerProcessSendCardConstraint.debug("" + depto.toString());

			CardConstraintDTO cardConstraint = new CardConstraintDTO();
			cardConstraint.setItemList(vehicle.getProductCode());
			// Si el campo data viene con datos, se separan los datos de la estaci�n
			// file o 0000 para cardcontraint ; file registrado en entrada/salida
			String fileCCN = "";
			String fileGPS = "";
			/// Agregar validaci�n de vehiculo validacionGps para ver que storelist mandar,
			/// utilizar el campo data del mensaje
			if (vehicle.getGps() != null) {
				if ("1".equals(vehicle.getGps().substring(0, 1)) && msg.getData() != null) {
					String contenido[] = msg.getData().split(";");
					fileCCN = contenido[0];
					fileGPS = contenido[1];

					List<String> listaCardStationConstraint = new ArrayList<>();
					listaCardStationConstraint.add(fileCCN);
					cardConstraint.setListaCardStationConstraint(listaCardStationConstraint);
					cardConstraint.setGps(true);
				} else {
					cardConstraint.setGps(false);
				}
			} else {
				// Si el cliente tiene estaciones privadas, se utiliza el nombre del store que
				// est� en el dto cliente

				if (ClientConstants.CLIENT_SCS.equals(client.getClientType())) {
					Boolean tieneStationClients = null;

					try {
						StationClientsCriterioDTO criterioSC = new StationClientsCriterioDTO();
						criterioSC.setIdClient(client.getIdClient().intValue());
						tieneStationClients = this.clientService.stationClientsExist(criterioSC);
					} catch (ClientServiceException e) {
						loggerProcessSendCardConstraint
								.error("Error al validar registros en STATION_CLIENTS. ERROR: " + e.getMessage());
					}

					if (tieneStationClients != null && tieneStationClients) {
						cardConstraint.setTieneEstaciones(true);
						try {
							loggerProcessSendCardConstraint
									.info("Recuperar estaciones segun cliente id " + client.getIdClient());
							StationClientsCriterioDTO criterio = new StationClientsCriterioDTO();
							criterio.setIdClient(client.getIdClient().intValue());
							criterio.setTipoCliente(client.getClientType());
							List<StationsDTO> listado = this.clientService.getStationClientsByCriterio(criterio);
							loggerProcessSendCardConstraint.info("Cantidad estaciones recuperadas " + listado.size());

							if (listado != null && listado.size() > 0) {
								List<String> storelist = new ArrayList<>();
								for (StationsDTO station : listado) {
									storelist.add(station.getStationCode());
								}

								if (storelist != null && storelist.size() > 0) {
//									cardConstraint.setStoreList(String.join("*", storelist));
									cardConstraint.setStationsCodePersonalizadas(storelist);
								}
							}
						} catch (ClientServiceException e) {
							loggerProcessSendCardConstraint
									.info("Error al recuperar registros de STATION_CLIENTS. ERROR: " + e.getMessage());
						}
					} else {
						cardConstraint.setTieneEstaciones(false);
					}
				} else {

					if (this.clientService.isEESSPrivadas(client.getIdClient()) && client.getStorelistorpak() != null) {
						cardConstraint.setStoreList(client.getStorelistorpak());
					} else {
						cardConstraint.setStoreList(client.getClientType()); // valor que depende de la lista por tipo
																				// de cliente
					}
				}
			}
			cardConstraint.setPlate(vehicle.getPlate());
			if (client.isCredit()) {
				cardConstraint.setCustomerCode(client.getCustomerCodeClient());
			} else {
				if (ClientConstants.DOCUMENT_TYPE_INVOICE.equals(vehicle.getDocumentType())) {
					cardConstraint.setCustomerCode(client.getCustomerCodeInvoice());
				} else {
					cardConstraint.setCustomerCode(client.getCustomerCodeTicket());
				}
			}

			if (ClientConstants.TYPE_BALANCE_CARD.equals(card.getRestrictionType())) {
				loggerProcessSendCardConstraint
						.info("Agregar el listado de estaciones con restricci�n para tarjeta " + card.getIdCard());
				List<String> listado = this.clientService.getListStationConstraintByCard(card.getIdCard());
				loggerProcessSendCardConstraint.debug("" + listado);
				if (vehicle.getGps() != null) {
					if ("0".equals(
							vehicle.getGps().substring(0, ClientConstants.SERVICES_GPS_POSITION_STRING_VALID + 1))) {
						cardConstraint.setListaCardStationConstraint(listado);
					}
				} else {
					cardConstraint.setListaCardStationConstraint(listado);
				}

				cardConstraint.setRestrL(card.getRestrL());
				cardConstraint.setRestrM(card.getRestrM());
				cardConstraint.setRestrX(card.getRestrX());
				cardConstraint.setRestrJ(card.getRestrJ());
				cardConstraint.setRestrV(card.getRestrV());
				cardConstraint.setRestrS(card.getRestrS());
				cardConstraint.setRestrD(card.getRestrD());
				cardConstraint.setRestrHend(card.getRestrHend());
				cardConstraint.setRestrHini(card.getRestrHini());
				cardConstraint.setRestrDailyMaxLoads(card.getRestrDailyMaxLoads());
				cardConstraint.setRestrType(card.getRestrType());
				if (client.isCredit() && !ClientConstants.CLIENT_SCI.equals(client.getClientType())
						&& !ClientConstants.CLIENT_SCS.equals(client.getClientType())) {
					cardConstraint.setRestrAmountMax(
							this.clientService.getLitroPrecio(vehicle.getProductCode(), card.getRestrAmountMax()));
					cardConstraint.setRestrDailyMaxQuantity(this.clientService.getLitroPrecio(vehicle.getProductCode(),
							BigDecimal.valueOf(card.getRestrDailyMaxQuantity())).longValue());
				} else {
					cardConstraint.setRestrAmountMax(card.getRestrAmountMax());
					cardConstraint.setRestrDailyMaxQuantity(card.getRestrDailyMaxQuantity());
				}
			} else {
				loggerProcessSendCardConstraint
						.info("Agregar el listado de estaciones con restricci�n para el departamento "
								+ depto.getIdDepartment());
				List<String> listado = this.clientService.getListStationConstraintByDept(depto.getIdDepartment());
				loggerProcessSendCardConstraint.debug("" + listado);
				if (vehicle.getGps() == null) {
					cardConstraint.setListaCardStationConstraint(listado);
				} else if ("0".equals(vehicle.getGps().substring(0, 1))) {
					cardConstraint.setListaCardStationConstraint(listado);
				}

				cardConstraint.setRestrL(depto.getRestrL());
				cardConstraint.setRestrM(depto.getRestrM());
				cardConstraint.setRestrX(depto.getRestrX());
				cardConstraint.setRestrJ(depto.getRestrJ());
				cardConstraint.setRestrV(depto.getRestrV());
				cardConstraint.setRestrS(depto.getRestrS());
				cardConstraint.setRestrD(depto.getRestrD());
				cardConstraint.setRestrHend(depto.getRestrHend());
				cardConstraint.setRestrHini(depto.getRestrHinit());
				cardConstraint.setRestrDailyMaxLoads(depto.getRestrDailyMaxLoads());
				cardConstraint.setRestrType(depto.getRestrType());
				if (client.isCredit() && !ClientConstants.CLIENT_SCI.equals(client.getClientType())
						&& !ClientConstants.CLIENT_SCS.equals(client.getClientType())) {
					cardConstraint.setRestrAmountMax(
							this.clientService.getLitroPrecio(vehicle.getProductCode(), depto.getRestrAmountMax()));
					cardConstraint.setRestrDailyMaxQuantity(this.clientService.getLitroPrecio(vehicle.getProductCode(),
							BigDecimal.valueOf(depto.getRestrDailyMaxQuantity())).longValue());
				} else {
					cardConstraint.setRestrAmountMax(depto.getRestrAmountMax());
					cardConstraint.setRestrDailyMaxQuantity(depto.getRestrDailyMaxQuantity());
				}
			}

			// Defino si la constraint ser� por amount o quantity dependiendo del tipo de
			// cliente.
			if (ClientConstants.CLIENT_SCS.equals(client.getClientType())) {
				cardConstraint.setAmount(false);
			} else {
				cardConstraint.setAmount(true);
			}

			loggerProcessSendCardConstraint.info("Crear restricciones para patente " + cardConstraint.getPlate());
			loggerProcessSendCardConstraint.debug("" + cardConstraint);
			ResponseDTO retorno = this.orpakWSService.updateCardConstraint(cardConstraint);
			loggerProcessSendCardConstraint.debug("" + retorno);

			loggerProcessSendCardConstraint.info("Ingresar mensaje al historia de la SAF");
			MessageHistoryDTO message = new MessageHistoryDTO();
			message.setDateIns(LocalDateTime.now());
			message.setIdQueue(msg.getIdQueue());
			message.setReturnCode(retorno.getReturnCode());
			message.setUri(retorno.getUri());
			message.setMessage(retorno.getMessage());
			this.safService.pushMessageHistory(message);

			// actualizamos la cola con status OK
			msg.setDateSend(LocalDateTime.now());
			msg.setStatus(SafConstants.SAF_STATUS_SENDED);
			safService.updateMessage(msg);
			loggerProcessSendCardConstraint.info("Sacar mensaje " + msg.getIdQueue() + " de cola SAF");

			if (vehicle.getGps() != null) {
				if (cardConstraint.getGps()) {
					if (OrpakWSConstants.ORPAK_WS_CARD_CONSTRAINT_NULL_STATIONS.equals(fileCCN)) {
						// Grabar history de salida
						loggerProcessSendCardConstraint.info("Registro en card history de salida de geozona");
						// Registrar card history
						CardHistoryDTO cardHistory = new CardHistoryDTO();
						cardHistory.setAction("Salida: cambio de configuraci�n GPS (File " + fileGPS + ")");
						cardHistory.setDate(LocalDateTime.now());
						cardHistory.setActionType(ClientConstants.CAMBIO_CONFIGURACION_SALIDA_GPS);
						cardHistory.setIdCard(card.getIdCard());
						cardHistory.setUsername("GPS");
						cardHistory.setIdUser(0L);
						try {
							this.clientService.addCardHistory(cardHistory);
						} catch (CardException e) {
							loggerProcessSendCardConstraint
									.warn("(in) Problema al guardar registro card history: " + e.getMessage());
						}
					} else {
						loggerProcessSendCardConstraint.info("Registro en card history del ingreso a geozona");
						// Registrar card history
						CardHistoryDTO cardHistory = new CardHistoryDTO();
						cardHistory.setAction("Entrada: cambio de configuraci�n GPS (File " + fileGPS + ")");
						cardHistory.setDate(LocalDateTime.now());
						cardHistory.setActionType(ClientConstants.CAMBIO_CONFIGURACION_ENTRADA_GPS);
						cardHistory.setIdCard(card.getIdCard());
						cardHistory.setUsername("GPS");
						cardHistory.setIdUser(0L);
						try {
							this.clientService.addCardHistory(cardHistory);
						} catch (CardException e) {
							loggerProcessSendCardConstraint
									.warn("(in) Problema al guardar registro card history: " + e.getMessage());
						}
					}
				}
			}

		} catch (OrpakWSExceptions e) {
			loggerProcessSendCardConstraint.error("Ha ocurrido un error en el proceso. " + e.getMessage());
			loggerProcessSendCardConstraint.error("" + e.getUri());
			ProcessOrpakException exc = new ProcessOrpakException(e.getMessage());
			exc.setReturnCode(e.getReturnCode());
			exc.setUri(e.getUri());
			throw exc;
		} catch (ClientNotFoundException | VehicleException | ClientException | CardException | SafException e) {
			loggerProcessSendCardConstraint.warn("Tarjeta no pudo ser actualizada. " + e.getMessage());
			throw new ProcessOrpakException(e.getMessage());
		} catch (PriceLiterNotFoundException e) {
			loggerProcessSendCardConstraint.warn("Tarjeta no pudo ser actualizada. " + e.getMessage());
			throw new ProcessOrpakException(e.getMessage());
		}
	}

	@Override
	public void sendClient(ClientDTO cliente) throws ProcessOrpakException {

		try {
			// Validaciones
			if (cliente == null) {
				loggerProcessSendClient.error("Cliente no puede ser nulo");
				throw new IllegalArgumentException("Cliente no puede ser nulo.");
			}
			if (cliente.getAccountJdeInvoice() == null && cliente.getAccountJdeTicket() == null) {
				loggerProcessSendClient.error("Cliente no puede tener ambas cuentas de JDE nulas.");
				throw new IllegalArgumentException("Cliente no puede tener ambas cuentas de JDE nulas.");
			}

			loggerProcessSendClient.info("Validaciones de cliente OK");

			// Se llama al metodo que envia los clientes a la fachada de enex-orpak-ws
			loggerProcessSendClient.info("Envia Cliente ID " + cliente.getIdClient() + " a Orpak (SAF).");
			loggerProcessSendClient.debug(cliente.toString());

			CreateClientDTO clientWs = new CreateClientDTO();
			if (cliente.getName() != null) {
				clientWs.setCustomerdescription(cliente.getName());
			} else {
				loggerProcessSendClient
						.error("El campo name del cliente :" + cliente.getIdClient() + ",no puede ser nulo");
				throw new IllegalArgumentException(
						"El campo name del cliente :" + cliente.getIdClient() + ",no puede ser nulo");
			}

			clientWs.setStatus(ClientConstants.CLIENT_STATUS_ACTIVE);
			if (cliente.getAddressStreetName() != null && cliente.getAddressStreetNumber() != null) {
				clientWs.setAddress1(cliente.getAddressStreetName() + " " + cliente.getAddressStreetNumber() + " "
						+ cliente.getAddressDoorNumber());
				clientWs.setAddress2(cliente.getAddressDisStreetName() + " " + cliente.getAddressDisStreetNumber() + " "
						+ cliente.getAddressDisDoorNumber());
			} else {
				loggerProcessSendClient
						.error("El campo Address del cliente :" + cliente.getIdClient() + ",no puede ser nulo");
				throw new IllegalArgumentException("El campo name del cliente :" + cliente.getIdClient() + ",no puede ser nulo");
			}

			if (cliente.getAreaDTO().getName() != null) {
				clientWs.setDisctrict(cliente.getAreaDTO().getName());
			} else {
				loggerProcessSendClient
						.error("El campo Disctrict del cliente :" + cliente.getIdClient() + ",no puede ser nulo");
				throw new IllegalArgumentException(
						"El campo Disctrict del cliente :" + cliente.getIdClient() + ",no puede ser nulo");
			}

			if (cliente.getCityDTO().getName() != null) {
				clientWs.setCity(cliente.getCityDTO().getName());
			} else {
				loggerProcessSendClient
						.error("El campo city del cliente :" + cliente.getIdClient() + ",no puede ser nulo");
				throw new IllegalArgumentException(
						"El campo city del cliente :" + cliente.getIdClient() + ",no puede ser nulo");
			}

			if (cliente.getContactPhone() != null) {
				if (cliente.getContactPhone().contains(";")) {
					String[] fonos = cliente.getContactPhone().split(";");
					clientWs.setPhone(fonos[0]);
					clientWs.setFax(fonos[0]); // no existe definici�n para el fax en el proyecto, por ahora se env�a el
												// tel�fono
				} else {
					clientWs.setPhone(cliente.getContactPhone());
					clientWs.setFax(cliente.getContactPhone()); // no existe definici�n para el fax en el proyecto, por
																// ahora se env�a el tel�fono
				}
			} else {
				loggerProcessSendClient.error(
						"El campo n�mero de contacto del cliente :" + cliente.getIdClient() + ",no puede ser nulo");
				throw new IllegalArgumentException(
						"El campo n�mero de contacto del cliente :" + cliente.getIdClient() + ",no puede ser nulo");
			}
			if (cliente.getContactEmail() != null) {
				if (cliente.getContactEmail().contains(";")) {
					String[] mails = cliente.getContactEmail().split(";");
					clientWs.setEmail(mails[0]);
				} else {
					clientWs.setEmail(cliente.getContactEmail());
				}
			} else {
				loggerProcessSendClient
						.error("El campo E- mail del cliente :" + cliente.getIdClient() + ",no puede ser nulo");
				throw new IllegalArgumentException(
						"El campo E-mail del cliente :" + cliente.getIdClient() + ",no puede ser nulo");
			}

			if (cliente.getUpi() != null) {
				clientWs.setRut(cliente.getUpi());
			} else {
				loggerProcessSendClient
						.error("El campo rut del cliente :" + cliente.getIdClient() + ",no puede ser nulo");
				throw new IllegalArgumentException(
						"El campo rut del cliente :" + cliente.getIdClient() + ",no puede ser nulo");
			}

			if (cliente.getContactName() != null) {
				clientWs.setContactname(cliente.getContactName());
			} else {
				loggerProcessSendClient
						.error("El campo nombre contacto del cliente :" + cliente.getIdClient() + ",no puede ser nulo");
				throw new IllegalArgumentException(
						"El campo nombre contacto del cliente :" + cliente.getIdClient() + ",no puede ser nulo");
			}
			clientWs.setZip("12345"); // zipcode no se encuentra en los requerimientos se deja fijo hasta nuevo
										// requerimiento

			if (cliente.getCommercialType() != null) {
				clientWs.setGiro(cliente.getCommercialType());
			} else {
				loggerProcessSendClient
						.error("El campo commercialType del cliente :" + cliente.getIdClient() + ",no puede ser nulo");
				throw new IllegalArgumentException(
						"El campo commercialType del cliente :" + cliente.getIdClient() + ",no puede ser nulo");
			}

			String codeOrpakTicket = null;
			String codeOrpakInvoice = null;
			String codeOrpakclient = null;

			/*
			 * Si cliente es del tipo cr�dito se debe enviar la cuenta C como customer code
			 * y alternative code adem�s de fijar el l�mite de cr�dito Si el cliente es del
			 * tipo SCI se debe enviar como si fuera un cliente prepago, es decir, sin
			 * cr�dito
			 */
			if (cliente.isCredit()) {
				loggerProcessSendClient.info("Cliente es credito");
				if (cliente.getCreditLimit()!= null && BigDecimal.ZERO.compareTo(cliente.getCreditLimit()) < 0) {
					clientWs.setCreditlimit(cliente.getCreditLimit().toString());
				}
				if (cliente.getCustomerCodeClient() != null) {
					clientWs.setCustomercode(cliente.getCustomerCodeClient());
				} else {
					loggerProcessSendClient.error(
							"El campo accountjdeclient del cliente :" + cliente.getIdClient() + ",no puede ser nulo");
					throw new IllegalArgumentException(
							"El campo accountjdeclient del cliente :" + cliente.getIdClient() + ",no puede ser nulo");
				}
				clientWs.setAlternativecode(cliente.getCustomerCodeClient());

				// En el c�digo alternativo va la cuenta JDE
				loggerProcessSendClient.info("Se env�a cliente " + clientWs.getCustomercode() + "  a Orpak cr�dito.");
				loggerProcessSendClient.debug(clientWs.toString());
				try {
					codeOrpakclient = this.orpakWSService.createClient(clientWs);
				} catch (OrpakWSClientExistExceptions e) {
					loggerProcessSendClient.warn("Cliente existe se debe agregar SAF para actualizarlo.");
					this.safService.pushMessageUpdateClient(cliente.getIdClient());
				}
				loggerProcessSendClient.info("Se ha recibido codigo orpak: " + codeOrpakclient
						+ " para cliente cr�dito " + clientWs.getCustomercode());
			} else {
				loggerProcessSendClient.info("Cliente es prepago");

				// boleta
				if (cliente.getAccountJdeTicket() != null && !"".equals(cliente.getAccountJdeTicket())) {
					if (cliente.getCustomerCodeTicket() != null && !"".equals(cliente.getCustomerCodeTicket())) {
						clientWs.setCustomercode(cliente.getCustomerCodeTicket());
					} else {
						loggerProcessSendClient.error("El campo customerCodeTicket del cliente :"
								+ cliente.getIdClient() + ",no puede ser nulo");
						throw new IllegalArgumentException("El campo customerCodeTicket del cliente :"
								+ cliente.getIdClient() + ",no puede ser nulo");
					}
					clientWs.setAlternativecode(cliente.getAccountJdeTicket());

					// En el codigo alternativo va la cuenta JDE
					loggerProcessSendClient
							.info("Se envia cliente " + clientWs.getCustomercode() + "  a Orpak Boleta.");
					loggerProcessSendClient.debug(clientWs.toString());
					try {
						codeOrpakTicket = this.orpakWSService.createClient(clientWs);
					} catch (Exception e) {
						loggerProcessSendClient.warn("Cliente existe se debe agregar SAF para actualizarlo.");
						this.safService.pushMessageUpdateClient(cliente.getIdClient());
					}
					loggerProcessSendClient.info("Se ha recibido c�digo orpak: " + codeOrpakTicket
							+ " para cliente Boleta " + clientWs.getCustomercode());
				} else {
					loggerProcessSendClient.info("Cliente no tiene cuenta JDE para boleta.");
				}

				// factura
				if (cliente.getAccountJdeInvoice() != null && !"".equals(cliente.getAccountJdeInvoice())) {
					if (cliente.getCustomerCodeInvoice() != null && !"".equals(cliente.getCustomerCodeInvoice())) {
						clientWs.setCustomercode(cliente.getCustomerCodeInvoice());
					} else {
						loggerProcessSendClient.error("El campo CustomerCodeInvoice del cliente :"
								+ cliente.getIdClient() + ",no puede ser nulo");
						throw new IllegalArgumentException("El campo CustomerCodeInvoice del cliente :"
								+ cliente.getIdClient() + ",no puede ser nulo");
					}
					clientWs.setAlternativecode(cliente.getAccountJdeInvoice()); // En el c�digo alternativo va la
																					// cuenta JDE
					loggerProcessSendClient
							.info("Se env�a cliente " + clientWs.getCustomercode() + " a Orpak Factura.");
					loggerProcessSendClient.debug(" " + clientWs.toString());
					try {
						codeOrpakInvoice = this.orpakWSService.createClient(clientWs);
					} catch (OrpakWSClientExistExceptions e) {
						loggerProcessSendClient.warn("Cliente existe se debe agregar SAF para actualizarlo.");
						this.safService.pushMessageUpdateClient(cliente.getIdClient());
					}
					loggerProcessSendClient.info("Se ha recibido c�digo orpak: " + codeOrpakInvoice
							+ " para cliente Factura " + clientWs.getCustomercode());
				} else {
					loggerProcessSendClient.info("Cliente no tiene cuenta JDE para factura.");
				}

			}

			String codOrpakDeptoTicket = null;
			String codOrpakDeptoInvoice = null;
			String codOrpakDeptoClient = null;
			// Crear departamentos del cliente en orpak
			for (DepartmentDTO departamento : cliente.getListDepartments()) {
				CreateDepartmentDTO dto = new CreateDepartmentDTO();

				// Si el cliente es cr�dito se debe crear un s�lo departamento
				// si el cliente es prepago se deben crear dos departamentos uno para factura y
				// otro para boleta
				// si el cliente es del tipo SCI se deben crear dos departamentos en credito
				if (cliente.isCredit()) {
					dto.setCustomerCode(cliente.getCustomerCodeClient());
					if (ClientConstants.CLIENT_SCI.equals(cliente.getClientType())) {
						dto.setDepartmentDescription(departamento.getCodeUniqueInvoice());
						loggerProcessSendClient.info("Se env�a Departamento a Orpak Client/factura: " + dto);
						codOrpakDeptoInvoice = this.orpakWSService.createDepartment(dto);
						loggerProcessSendClient.info("Se ha recibido c�digo orpak: " + codOrpakDeptoClient
								+ " para depto " + dto.getDepartmentDescription());

						dto.setDepartmentDescription(departamento.getCodeUniqueTicket());
						loggerProcessSendClient.info("Se env�a Departamento a Orpak Client/boleta: " + dto);
						codOrpakDeptoTicket = this.orpakWSService.createDepartment(dto);
						loggerProcessSendClient.info("Se ha recibido c�digo orpak: " + codOrpakDeptoClient
								+ " para depto " + dto.getDepartmentDescription());
					} else {
						dto.setDepartmentDescription(departamento.getCodeUniqueClient());
						loggerProcessSendClient.info("Se env�a Departamento a Orpak Client: " + dto);
						codOrpakDeptoClient = this.orpakWSService.createDepartment(dto);
						loggerProcessSendClient.info("Se ha recibido c�digo orpak: " + codOrpakDeptoClient
								+ " para depto " + dto.getDepartmentDescription());
					}
				} else {
					// Boleta
					if (cliente.getAccountJdeTicket() != null) {
						dto.setCustomerCode(cliente.getCustomerCodeTicket());
						dto.setDepartmentDescription(departamento.getCodeUniqueTicket());
						loggerProcessSendClient.info("Se env�a Departamento a Orpak Boleta: " + dto);
						codOrpakDeptoTicket = this.orpakWSService.createDepartment(dto);
						loggerProcessSendClient.info("Se ha recibido c�digo orpak: " + codOrpakDeptoTicket
								+ " para depto " + dto.getDepartmentDescription());
					}

					// Factura
					if (cliente.getAccountJdeInvoice() != null) {
						dto.setCustomerCode(cliente.getCustomerCodeInvoice());
						dto.setDepartmentDescription(departamento.getCodeUniqueInvoice());
						loggerProcessSendClient.info("Se env�a Departamento a Orpak Factura: " + dto);
						codOrpakDeptoInvoice = this.orpakWSService.createDepartment(dto);
						loggerProcessSendClient.info("Se ha recibido c�digo orpak: " + codOrpakDeptoInvoice
								+ " para depto " + dto.getDepartmentDescription());
					}
				}
				// Actualizar departamento con el c�digo devuelto por orpak
				DepartmentDTO departamentDto = new DepartmentDTO();
				departamentDto.setIdDepartment(departamento.getIdDepartment());
				departamentDto.setIdClient(departamento.getIdClient());
				departamentDto.setCodeOrpakTicket(codOrpakDeptoTicket);
				departamentDto.setCodeOrpakInvoice(codOrpakDeptoInvoice);
				departamentDto.setCodeOrpakClient(codOrpakDeptoClient);
				loggerProcessSendClient
						.info("Se actualiza departamento " + departamento.getIdDepartment() + " con c�digos de Orpak.");
				this.clientService.updDeparmentsProcessSendClient(departamentDto);

			}

            AuditDTO auditoria = new AuditDTO();
			auditoria.setInsertLogin(AuditoriaConstans.USUARIO_PROCESO);
			auditoria.setInsertName(AuditoriaConstans.USUARIO_PROCESO);
			auditoria.setAction(AuditoriaConstans.ACCION_ENVIAR_CLIENTE);
			auditoria.setIdElemento(cliente.getIdClient());
			auditoria.setTipoElemento(AuditoriaConstans.TIPO_ELEMENTO_CLIENTE);
			auditoria.setDescription("Se envia cliente a Orpak (SAF)");
			auditoria.setSystem(AuditoriaConstans.USUARIO_PROCESO);

			try {
				this.auditsService.addAudit(auditoria);
			} catch (Exception au) {
				log.warn("Problema al guardar auditoria ", au);
			}

			// Actualizar cliente indicando el codido orpak recibido
			RegisterClientDTO registerClientdto = new RegisterClientDTO();
			registerClientdto.setIdClient(cliente.getIdClient());
			if (codeOrpakclient != null && Integer
					.parseInt(codeOrpakclient) >= OrpakWSConstants.ORPAK_WS_CREATE_CLIENT_CONTRACT_STATUS_OK) {
				registerClientdto.setCodeOrpakClient(codeOrpakclient);
			}
			if (codeOrpakTicket != null && Integer
					.parseInt(codeOrpakTicket) >= OrpakWSConstants.ORPAK_WS_CREATE_CLIENT_CONTRACT_STATUS_OK) {
				registerClientdto.setCodeOrpakTicket(codeOrpakTicket);
			}
			if (codeOrpakInvoice != null && Integer
					.parseInt(codeOrpakInvoice) >= OrpakWSConstants.ORPAK_WS_CREATE_CLIENT_CONTRACT_STATUS_OK) {
				registerClientdto.setCodeOrpakInvoice(codeOrpakInvoice);
			}
			registerClientdto.setClientStatus(ClientConstants.CLIENT_STATUS_NEW);

			loggerProcessSendClient.info("Se actualiza cliente " + cliente.getIdClient()
					+ " con c�digos orpak recibidos y estado nuevo (N).");
			loggerProcessSendClient.debug("" + registerClientdto.toString());
			this.clientService.updClientByIDProcessSendClient(registerClientdto);
			
            AuditDTO auditoriaDos = new AuditDTO();
            auditoriaDos.setInsertLogin(AuditoriaConstans.USUARIO_PROCESO);
            auditoriaDos.setInsertName(AuditoriaConstans.USUARIO_PROCESO);
            auditoriaDos.setAction(AuditoriaConstans.ACCION_ENVIAR_CLIENTE);
            auditoriaDos.setIdElemento(cliente.getIdClient());
            auditoriaDos.setTipoElemento(AuditoriaConstans.TIPO_ELEMENTO_CLIENTE);
            auditoriaDos.setDescription("Actualiza codigos Orpak y estado del cliente a nuevo (N): "+registerClientdto.toStringCodeOrpak());
            auditoriaDos.setSystem(AuditoriaConstans.USUARIO_PROCESO);

			try {
				this.auditsService.addAudit(auditoriaDos);
			} catch (Exception au) {
				log.warn("Problema al guardar auditoria ", au);
			}
			
		} catch (OrpakWSExceptions | DepartmentException | ClientException | SafException e) {
			loggerProcessSendClient.error("Ha ocurrido un error en el proceso. " + e.getMessage());
			throw new ProcessOrpakException("Ha ocurrido un error en el proceso. ", e);
		}catch(Exception e) {
			loggerProcessSendClient.error("Error inesperado" + e.getMessage());
			throw new ProcessOrpakException(e);
		}

	}

	@Override
	public void updPendingCustomerBalance(MessageDTO msg) throws ProcessOrpakException {
		loggerProcessSendCustomerBalance.info("Actualizar saldo del cliente " + msg.getIdElement());

//		if (msg == null || msg.getIdElement() == null || msg.getData() == null) {
//			loggerProcessSendCustomerBalance.error("Error en par�metros de entrada " + msg);
//			throw new ProcessOrpakException("Error en par�metros de entrada.");
//		}

		try {

			// idElement : idcliente
			// data : monto a transferir

			// Recuperar informaci�n de la trx jde
			ClientDTO cliente = this.clientService.getClientById(msg.getIdElement());

			UpdCustomerBalanceDTO dto = new UpdCustomerBalanceDTO();
			dto.setCustomerCode(cliente.getAccountJdeClient());
			dto.setAmount(new BigDecimal(msg.getData()));
			dto.setDate(LocalDateTime.now());
			dto.setTransactionId("" + msg.getIdQueue());
			dto.setRemarks(OrpakProcessConstants.MENSAJE_CUSTOMERPAYMENT);

			loggerProcessSendCustomerBalance.info("Aumentar saldo del cliente en Orpak.");
			loggerProcessSendCustomerBalance.debug("" + dto.toString());

			// actualizamos estado del cliente
			ResponseDTO retorno = new ResponseDTO();
			try {
				retorno = orpakWSService.updateCustomerBalance(dto);
			} catch (OrpakWSTrxCustomerBalanceExistExceptions e) {
				loggerProcessSendCustomerBalance.info("Trx ya existe en Orpak. Se debe continuar. " + e.getMessage());
				retorno = new ResponseDTO();
				retorno.setMessage(e.getMessage());
				retorno.setReturnCode(e.getReturnCode());
				retorno.setUri(e.getUri());
			}
			loggerProcessSendCustomerBalance.info("Retorno: " + retorno.getReturnCode());

			loggerProcessSendCustomerBalance.info("Ingresar mensaje al historia de la SAF");
			MessageHistoryDTO message = new MessageHistoryDTO();
			message.setDateIns(LocalDateTime.now());
			message.setIdQueue(msg.getIdQueue());
			message.setReturnCode(retorno.getReturnCode());
			message.setUri(retorno.getUri());
			message.setMessage(retorno.getMessage());
			this.safService.pushMessageHistory(message);

			// actualizamos la cola con status OK
			msg.setDateSend(LocalDateTime.now());
			msg.setStatus(SafConstants.SAF_STATUS_SENDED);
			safService.updateMessage(msg);
			loggerProcessSendCustomerBalance.info("Sacar mensaje " + msg.getIdQueue() + " de cola SAF");

		} catch (OrpakWSExceptions e) {
			loggerProcessSendCustomerBalance.error("Ha ocurrido un error en el proceso. " + e.getMessage());
			loggerProcessSendCustomerBalance.error("" + e.getUri());
			ProcessOrpakException exc = new ProcessOrpakException(e.getMessage());
			exc.setReturnCode(e.getReturnCode());
			exc.setUri(e.getUri());
			throw exc;

		} catch (SafException e) {
			loggerProcessSendCustomerBalance.warn("Cliente no pudo ser actualizado. " + e.getMessage());
			throw new ProcessOrpakException(e.getMessage());
		} catch (ClientException e1) {
			loggerProcessSendCustomerBalance.warn("Cliente no pudo ser actualizado. " + e1.getMessage());
			throw new ProcessOrpakException(e1.getMessage());
		}

	}

	@Override
	public void updPendingDepartmentBalance(MessageDTO msg) throws ProcessOrpakException {

		loggerProcessSendDeptBalance.info("Actualizar saldo del departamento " + msg.getIdElement());

		try {

			// parseamos la data del mensaje, el cual trae dos valores separados por ;
			// (punto y coma)
			String[] datos = msg.getData().split(";");
			// datos[0]: monto
			// datos[1]: codProducto
			// datos[2]: document type (I: factura T: boleta)
			loggerProcessSendDeptBalance.info("Datos " + datos.toString());
			Double monto = Double.parseDouble(datos[0]);
			String codProducto = datos[1];
			String documentType = datos[2];

			if (datos.length != 3) {
				loggerProcessSendDeptBalance.warn("Problemas con formato.");
				throw new IllegalArgumentException(
						"Se espera un mensaje con el siguiente formato <monto;codProducto;idTransaccion> "
								+ ", revise el mensaje Id " + msg.getIdQueue());
			}

			// Recuperar el departamento
			DepartmentDTO depto = this.clientService.getListDepartmentById(msg.getIdElement());
			loggerProcessSendDeptBalance.debug("" + depto.toString());

			// dto para consultar servicio
			UpdDepartmentBalanceDTO dto = new UpdDepartmentBalanceDTO();
			dto.setAmount(monto);
			// revisar seg�n tipo de documento
			if (ClientConstants.DOCUMENT_TYPE_TICKET.equals(documentType)) {
				dto.setDepartmentId("" + depto.getCodeOrpakTicket());
			} else {
				dto.setDepartmentId("" + depto.getCodeOrpakInvoice());
			}
			dto.setFuelCode(codProducto);
			dto.setTransactionId("" + msg.getIdQueue()); // el idTransaction ser� el idQueue

			ClientDTO cliente = this.clientService.getClientById(depto.getIdClient());

			if (ClientConstants.CLIENT_SCS.equals(cliente.getClientType())) {
				dto.setDepartmentId("" + depto.getCodeOrpakClient());
				dto.setBalanceType(OrpakWSConstants.ORPAK_WS_DEPARTMENT_BALANCE_QUANTITY);
			} else {
				dto.setBalanceType(OrpakWSConstants.ORPAK_WS_DEPARTMENT_BALANCE_AMOUNT);
			}

			loggerProcessSendDeptBalance.info("Aumentar saldo del departamento en Orpak.");
			loggerProcessSendDeptBalance.debug("" + dto.toString());

			// actualizamos estado del departamento
			ResponseDTO retorno = new ResponseDTO();
			try {
				retorno = orpakWSService.updateDepartmentBalance(dto);
			} catch (OrpakWSTrxDepartmentBalanceExistExceptions e) {
				loggerProcessSendDeptBalance.info("Trx ya existe en Orpak. Se debe continuar. " + e.getMessage());
				retorno = new ResponseDTO();
				retorno.setMessage(e.getMessage());
				retorno.setReturnCode(e.getReturnCode());
				retorno.setUri(e.getUri());
			}
			loggerProcessSendDeptBalance.info("Retorno: " + retorno.getReturnCode());

			loggerProcessSendDeptBalance.info("Ingresar mensaje al historia de la SAF");
			MessageHistoryDTO message = new MessageHistoryDTO();
			message.setDateIns(LocalDateTime.now());
			message.setIdQueue(msg.getIdQueue());
			message.setReturnCode(retorno.getReturnCode());
			message.setUri(retorno.getUri());
			message.setMessage(retorno.getMessage());
			this.safService.pushMessageHistory(message);

			// actualizamos la cola con status OK
			msg.setDateSend(LocalDateTime.now());
			msg.setStatus(SafConstants.SAF_STATUS_SENDED);
			safService.updateMessage(msg);
			loggerProcessSendDeptBalance.info("Sacar mensaje " + msg.getIdQueue() + " de cola SAF");

		} catch (OrpakWSExceptions e) {
			loggerProcessSendDeptBalance.error("Ha ocurrido un error en el proceso. " + e.getMessage());
			loggerProcessSendDeptBalance.error("" + e.getUri());
			ProcessOrpakException exc = new ProcessOrpakException(e.getMessage());
			exc.setReturnCode(e.getReturnCode());
			exc.setUri(e.getUri());
			throw exc;

		} catch (SafException | ClientNotFoundException | ClientException e) {
			loggerProcessSendDeptBalance.warn("Departamento no pudo ser actualizado. " + e.getMessage());
			throw new ProcessOrpakException(e.getMessage());
		}

	}

	@Override
	public void updQuantityCard(MessageDTO msg) throws ProcessOrpakException {
		loggerProcessLoadQuantityCard.info("Actualizar litros de la tarjeta " + msg.getIdElement());

		CardDTO card = null;

		try {

			// consultamos por la tarjeta que viene en el mensaje
			card = this.clientService.getCardById(msg.getIdElement());
			loggerProcessLoadQuantityCard.info("Recuperar tarjeta " + msg.getIdElement());
			loggerProcessLoadQuantityCard.debug("" + card.toString());

			// consultar cliente
			ClientDTO client = clientService.getClientById(card.getIdClient());
			loggerProcessLoadQuantityCard.info("Recuperar cliente " + card.getIdClient());
			loggerProcessLoadQuantityCard.debug("" + client.toString());

			// consultamos el veh�culo asociado a la tarjeta
			VehicleDTO vehicle = clientService.getVehicleByIdCard(card.getIdCard());
			loggerProcessLoadQuantityCard.info("Recuperar vehiculo " + card.getIdCard());
			loggerProcessLoadQuantityCard.debug("" + vehicle.toString());

			// extraemos la data del mensaje
			Double quantity = Double.parseDouble(msg.getData()); // en la data va el valor cargado

			loggerProcessLoadQuantityCard.info("Cargar a tarjeta " + card.getCardnum() + " quantity " + quantity);

			// dto para consultar servicio
			LoadQuantityCardDTO dto = new LoadQuantityCardDTO();
			dto.setQuantity(quantity);
			dto.setCardNo(card.getCardnum());

			// Se obtienen datos del cliente.
			dto.setCustomerCode(client.getCustomerCodeClient());
			loggerProcessLoadQuantityCard.info("Tomar customerCode de cliente.");

			dto.setFuelCode(vehicle.getProductCode());
			dto.setFuelType("0");

			if (msg.getTask() == null) {
				dto.setTransactionId("" + msg.getIdQueue()); // el idTransaction ser� el idQueue
			} else {
				dto.setTransactionId("" + msg.getTask());
			}

			loggerProcessLoadQuantityCard.info("Aumentar litros de la tarjeta en Orpak.");
			loggerProcessLoadQuantityCard.debug("" + dto.toString());

			if (quantity.doubleValue() != 0) {

				// actualizamos estado del departamento
				ResponseDTO retorno = new ResponseDTO();

				try {
					retorno = orpakWSService.loadQuantityCard(dto);
				} catch (OrpakWSTrxCardBalanceExistExceptions e) {
					loggerProcessLoadQuantityCard.info("Trx ya existe en Orpak. Se debe continuar. " + e.getMessage());
					retorno.setMessage(e.getMessage());
					retorno.setReturnCode(e.getReturnCode());
					retorno.setUri(e.getUri());
				}
				loggerProcessLoadQuantityCard.info("" + retorno);

				loggerProcessLoadQuantityCard.info("Ingresar mensaje al historia de la SAF");
				MessageHistoryDTO message = new MessageHistoryDTO();
				message.setDateIns(LocalDateTime.now());
				message.setIdQueue(msg.getIdQueue());
				message.setReturnCode(retorno.getReturnCode());
				message.setUri(retorno.getUri());
				message.setMessage(retorno.getMessage());
				this.safService.pushMessageHistory(message);

			} else {
				loggerProcessLoadQuantityCard.info("No se env�a a Orpak ya que el monto a enviar es cero. " + dto);
			}

			// actualizamos la cola con status OK
			msg.setDateSend(LocalDateTime.now());
			msg.setStatus(SafConstants.SAF_STATUS_SENDED);
			safService.updateMessage(msg);
			loggerProcessLoadQuantityCard.info("Sacar mensaje " + msg.getIdQueue() + " de cola SAF");

			// Actualizar saldo de tarjeta
			this.clientService.updQuantityCard(card.getIdCard(), card.getCardnum());

		} catch (OrpakWSExceptions e) {
			loggerProcessLoadQuantityCard.error("Ha ocurrido un error en el proceso. " + e.getMessage());
			loggerProcessLoadQuantityCard.error("" + e.getUri());
			ProcessOrpakException exc = new ProcessOrpakException(e.getMessage());
			exc.setReturnCode(e.getReturnCode());
			exc.setUri(e.getUri());
			throw exc;
		} catch (ClientException | CardException | SafException e) {
			// silent catch, actualizamos la cola con status PENDING y reintentos +1, y
			// sigue procesando
			loggerProcessLoadQuantityCard.warn("Tarjeta no pudo ser actualizada. " + e.getMessage());
			throw new ProcessOrpakException(e.getMessage());
		} catch (VehicleException e) {
			loggerProcessLoadQuantityCard.warn("Error al recuperar veh�culo. " + e.getMessage());
			loggerProcessLoadQuantityCard.info("Validar si es una tarjeta eliminada.");
			if (ClientConstants.CARD_STATUS_ELIMINADO.equals(card.getCardStatus())) {
				loggerProcessLoadQuantityCard.info("Tarjeta eliminada se saca la SAF");

				try {
					loggerProcessLoadQuantityCard.info("Ingresar mensaje al historia de la SAF");
					MessageHistoryDTO message = new MessageHistoryDTO();
					message.setDateIns(LocalDateTime.now());
					message.setIdQueue(msg.getIdQueue());
					message.setReturnCode("");
					message.setUri("");
					message.setMessage("Tarjeta eliminada se saca la SAF.");
					this.safService.pushMessageHistory(message);

					// actualizamos la cola con status OK
					msg.setDateSend(LocalDateTime.now());
					msg.setStatus(SafConstants.SAF_STATUS_DELETED);
					safService.updateMessage(msg);
					loggerProcessLoadQuantityCard.info("Sacar mensaje " + msg.getIdQueue() + " de cola SAF");
				} catch (SafException e1) {
					loggerProcessLoadQuantityCard.warn("No se pudo modificar estado al mensaje SAF. " + e.getMessage());
				}

			}
		}

	}

	@Override
	public void addPendingVehiceCardChange(MessageDTO msg) throws ProcessOrpakException {

		try {
			// consultamos el veh�culo asociado a la tarjeta
			VehicleDTO vehicle = this.clientService.getVehicleById(msg.getIdElement());

			ClientDTO client = this.clientService.getClientById(vehicle.getIdClient());

			DepartmentDTO depto = this.clientService.getListDepartmentById(vehicle.getIdDepartment());

			loggerProcessVehicleCard.info("Datos vehiculo " + vehicle.getIdVehicle());
			loggerProcessVehicleCard.info("Datos cliente " + client.getIdClient());
			loggerProcessVehicleCard.info("Datos departamento " + depto.getIdDepartment());
			loggerProcessVehicleCard.debug("" + vehicle);
			loggerProcessVehicleCard.debug("" + client);
			loggerProcessVehicleCard.debug("" + depto);

			loggerProcessVehicleCard.info("Crear cardConstraint");

			// Por cada tarjeta asociada al vehiculo se debe enviar a Orpak
			List<CardDTO> lista = vehicle.getCard();
			for (CardDTO card : lista) {

				if (card == null) {
					loggerProcessVehicleCard.warn("No existe tarjeta activa asociado el Veh�culo " + vehicle.getPlate()
							+ " se salta el registro.");
					throw new OrpakWSExceptions("No existe tarjeta activa asociado el Veh�culo " + vehicle.getPlate()
							+ " se salta el registro.");
				}

				CardConstraintDTO cardConstraint = new CardConstraintDTO();
				cardConstraint.setItemList(vehicle.getProductCode());
				cardConstraint.setStoreList(client.getClientType()); // valor que depende de la lista por tipo de
																		// cliente
				cardConstraint.setPlate(vehicle.getPlate());
				if (client.isCredit()) {
					cardConstraint.setCustomerCode(client.getCustomerCodeClient());
				} else {
					if (ClientConstants.DOCUMENT_TYPE_INVOICE.equals(vehicle.getDocumentType())) {
						cardConstraint.setCustomerCode(client.getCustomerCodeInvoice());
					} else {
						cardConstraint.setCustomerCode(client.getCustomerCodeTicket());
					}
				}

				if (ClientConstants.TYPE_BALANCE_CARD.equals(card.getRestrictionType())) {
					loggerProcessVehicleCard
							.info("Agregar el listado de estaciones con restricci�n para tarjeta " + card.getIdCard());
					List<String> listado = this.clientService.getListStationConstraintByCard(card.getIdCard());
					loggerProcessVehicleCard.debug("" + listado);
					cardConstraint.setListaCardStationConstraint(listado);

					cardConstraint.setRestrL(card.getRestrL());
					cardConstraint.setRestrM(card.getRestrM());
					cardConstraint.setRestrX(card.getRestrX());
					cardConstraint.setRestrJ(card.getRestrJ());
					cardConstraint.setRestrV(card.getRestrV());
					cardConstraint.setRestrS(card.getRestrS());
					cardConstraint.setRestrD(card.getRestrD());
					cardConstraint.setRestrHend(card.getRestrHend());
					cardConstraint.setRestrHini(card.getRestrHini());
					cardConstraint.setRestrDailyMaxLoads(card.getRestrDailyMaxLoads());
					cardConstraint.setRestrType(card.getRestrType());
					if (client.isCredit() && !ClientConstants.CLIENT_SCI.equals(client.getClientType())
							&& !ClientConstants.CLIENT_SCS.equals(client.getClientType())) {
						cardConstraint.setRestrAmountMax(
								this.clientService.getLitroPrecio(vehicle.getProductCode(), card.getRestrAmountMax()));
						cardConstraint
								.setRestrDailyMaxQuantity(
										this.clientService
												.getLitroPrecio(vehicle.getProductCode(),
														BigDecimal.valueOf(card.getRestrDailyMaxQuantity()))
												.longValue());
						card.setRestrAmountMax(cardConstraint.getRestrAmountMax());
						depto.setRestrAmountMax(cardConstraint.getRestrAmountMax());
					} else {
						cardConstraint.setRestrAmountMax(card.getRestrAmountMax());
						cardConstraint.setRestrDailyMaxQuantity(card.getRestrDailyMaxQuantity());
					}
				} else {
					loggerProcessVehicleCard
							.info("Agregar el listado de estaciones con restricci�n para el departamento "
									+ depto.getIdDepartment());
					List<String> listado = this.clientService.getListStationConstraintByDept(depto.getIdDepartment());
					loggerProcessVehicleCard.debug("" + listado);
					cardConstraint.setListaCardStationConstraint(listado);

					cardConstraint.setRestrL(depto.getRestrL());
					cardConstraint.setRestrM(depto.getRestrM());
					cardConstraint.setRestrX(depto.getRestrX());
					cardConstraint.setRestrJ(depto.getRestrJ());
					cardConstraint.setRestrV(depto.getRestrV());
					cardConstraint.setRestrS(depto.getRestrS());
					cardConstraint.setRestrD(depto.getRestrD());
					cardConstraint.setRestrHend(depto.getRestrHend());
					cardConstraint.setRestrHini(depto.getRestrHinit());
					cardConstraint.setRestrDailyMaxLoads(depto.getRestrDailyMaxLoads());
					cardConstraint.setRestrType(depto.getRestrType());
					if (client.isCredit() && !ClientConstants.CLIENT_SCI.equals(client.getClientType())
							&& !ClientConstants.CLIENT_SCS.equals(client.getClientType())) {
						cardConstraint.setRestrAmountMax(
								this.clientService.getLitroPrecio(vehicle.getProductCode(), depto.getRestrAmountMax()));
						cardConstraint
								.setRestrDailyMaxQuantity(
										this.clientService
												.getLitroPrecio(vehicle.getProductCode(),
														BigDecimal.valueOf(depto.getRestrDailyMaxQuantity()))
												.longValue());
						card.setRestrAmountMax(cardConstraint.getRestrAmountMax());
						depto.setRestrAmountMax(cardConstraint.getRestrAmountMax());
					} else {
						cardConstraint.setRestrAmountMax(depto.getRestrAmountMax());
						cardConstraint.setRestrDailyMaxQuantity(depto.getRestrDailyMaxQuantity());
					}
				}

				// Defino si la constraint ser� por amount o quantity dependiendo del tipo de
				// cliente.
				if (ClientConstants.CLIENT_SCS.equals(client.getClientType())) {
					cardConstraint.setAmount(false);
				} else {
					cardConstraint.setAmount(true);
				}

				loggerProcessVehicleCard.info("Crear restricciones para patente " + cardConstraint.getPlate());
				loggerProcessVehicleCard.debug("" + cardConstraint.toString());
				ResponseDTO retornoCCC = new ResponseDTO();
				try {
					retornoCCC = orpakWSService.createCardConstraint(cardConstraint);
					loggerProcessVehicleCard.debug("" + retornoCCC);
				} catch (OrpakWSCardConstraintExistExceptions e) {
					loggerProcessVehicleCard.warn("La restricciones para patente " + cardConstraint.getPlate()
							+ " ya existe, se debe agregar una SAF para actualizarla.");
					loggerProcessVehicleCard
							.info("Se agrega SAF para actualizar cardConstraint para tarjeta " + card.getIdCard());
					this.safService.pushMessageUpdateCardConstraint(card.getIdCard(), null);
					retornoCCC.setMessage(e.getMessage());
					retornoCCC.setUri(e.getUri());
					retornoCCC.setReturnCode(e.getReturnCode());
				}
				ResponseDTO retorno = new ResponseDTO();
				try {
					retorno = orpakWSService.createVehicleCard(vehicle, card, depto, client);
					loggerProcessVehicleCard.debug("" + retorno);
				} catch (OrpakWSVehicleCardExistExceptions e) {
					loggerProcessVehicleCard
							.info("Veh�culo existen en Orpak. Se continua con el proceso. " + e.getMessage());
					retorno.setReturnCode(e.getReturnCode());
					retorno.setUri(e.getUri());
					retorno.setMessage(e.getMessage());
				}

				loggerProcessVehicleCard.info("Ingresar mensaje al historia de la SAF");
				MessageHistoryDTO message = new MessageHistoryDTO();
				message.setDateIns(LocalDateTime.now());
				message.setIdQueue(msg.getIdQueue());
				message.setReturnCode(retornoCCC.getReturnCode());
				message.setUri(retornoCCC.getUri());
				message.setMessage(retornoCCC.getMessage());
				this.safService.pushMessageHistory(message);

				loggerProcessVehicleCard.info("Ingresar mensaje al historia de la SAF");
				MessageHistoryDTO messageC = new MessageHistoryDTO();
				messageC.setDateIns(LocalDateTime.now());
				messageC.setIdQueue(msg.getIdQueue());
				messageC.setReturnCode(retorno.getReturnCode());
				messageC.setUri(retorno.getUri());
				messageC.setMessage(retorno.getMessage());
				this.safService.pushMessageHistory(messageC);

				// actualizamos el estado de la tarjeta para quedar con I
				StatusCardDTO statusCardDTO = new StatusCardDTO();
				statusCardDTO.setIdCard(card.getIdCard());
				statusCardDTO.setCardStatus(ClientConstants.CARD_STATUS_INACTIVE);
				statusCardDTO.setCardnum(card.getCardnum());
				this.clientService.updCardStatus(statusCardDTO);

			}

			// actualizamos la cola con status OK
			msg.setDateSend(LocalDateTime.now());
			msg.setStatus(SafConstants.SAF_STATUS_SENDED);
			safService.updateMessage(msg);
			loggerProcessVehicleCard.info("Actualizar mensaje SAF para vehiculo " + vehicle.getIdVehicle());

		} catch (OrpakWSExceptions e) {
			loggerProcessVehicleCard.error("Ha ocurrido un error en el proceso. " + e.getMessage());
			loggerProcessVehicleCard.error("URI: " + e.getUri());
			ProcessOrpakException exc = new ProcessOrpakException(e.getMessage());
			exc.setReturnCode(e.getReturnCode());
			exc.setUri(e.getUri());
			throw exc;
		} catch (ClientNotFoundException | VehicleException | ClientException | SafException | CardException e) {
			loggerProcessVehicleCard.warn("Tarjeta no pudo ser actualizada. " + e.getMessage());
			throw new ProcessOrpakException(e.getMessage());
		} catch (PriceLiterNotFoundException e) {
			loggerProcessVehicleCard.warn("Tarjeta no pudo ser actualizada. " + e.getMessage());
			throw new ProcessOrpakException(e.getMessage());
		}

	}

	@Override
	public void updPendingVehiceCardChange(MessageDTO msg) throws ProcessOrpakException {

		try {

			// consultamos el veh�culo asociado a la tarjeta
			VehicleDTO vehicle = clientService.getVehicleById(msg.getIdElement());

			ClientDTO client = this.clientService.getClientById(vehicle.getIdClient());

			DepartmentDTO department = this.clientService.getListDepartmentById(vehicle.getIdDepartment());

			loggerProcessVehicleCard.info("Datos vehiculo " + vehicle.getIdVehicle());
			loggerProcessVehicleCard.info("Datos cliente " + client.getIdClient());
			loggerProcessVehicleCard.info("Datos departamento " + department.getIdDepartment());
			loggerProcessVehicleCard.debug("" + vehicle);
			loggerProcessVehicleCard.debug("" + client);
			loggerProcessVehicleCard.debug("" + department);

			// TODO SKP ntarjetas...
			// Por cada tarjeta asociada al vehiculo se debe enviar a Orpak
			List<CardDTO> lista = vehicle.getCard();
			for (CardDTO cardDTO : lista) {

				// Se salta el vehiculo si se encuenta Pendiente y es producto de una
				// reimpresi�n
				// S�lo se deben dejar pasar vehiculos Pendientes cuando se est� incorporando
				// una nueva tarjeta y esta se encuentra Pendiente
				if (ClientConstants.CARD_STATUS_PENDING.equals(cardDTO.getCardStatus())
						&& ClientConstants.VEHICLE_REQCARDNUMBER_STATUS_CREATED.equals(cardDTO.getReqcardStatus())
						&& cardDTO.getReqCardReprint()) {
					continue;
				}

				// actualizamos datos del vehiculo
				ResponseDTO retorno = new ResponseDTO();
				try {
					retorno = orpakWSService.updateVehicleCard(vehicle, cardDTO, department, client);
					loggerProcessVehicleCard.debug("" + retorno);
				} catch (OrpakWSVehicleCardExistExceptions e) {
					loggerProcessVehicleCard
							.info("Veh�culo existen en Orpak. Se continua con el proceso. " + e.getMessage());
					retorno.setReturnCode(e.getReturnCode());
					retorno.setUri(e.getUri());
					retorno.setMessage(e.getMessage());
				}

				loggerProcessVehicleCard.info("Ingresar mensaje al historia de la SAF");
				MessageHistoryDTO message = new MessageHistoryDTO();
				message.setDateIns(LocalDateTime.now());
				message.setIdQueue(msg.getIdQueue());
				message.setReturnCode(retorno.getReturnCode());
				message.setUri(retorno.getUri());
				message.setMessage(retorno.getMessage());
				this.safService.pushMessageHistory(message);

				// Si la tarjeta se encuentra en estado Pendiente ya que es una nueva tarjeta
				// para un vehiculo que existe se debe procesar
				if (ClientConstants.CARD_STATUS_PENDING.equals(cardDTO.getCardStatus())
						&& ClientConstants.VEHICLE_REQCARDNUMBER_STATUS_CREATED.equals(cardDTO.getReqcardStatus())
						&& !cardDTO.getReqCardReprint()) {
					loggerProcessVehicleCard
							.info("Se desactiva la tarjeta ya que es una tarjeta nueva producto de una reimpresi�n");
					StatusCardDTO statusCardDTO = new StatusCardDTO();
					statusCardDTO.setIdCard(cardDTO.getIdCard());
					statusCardDTO.setCardStatus(ClientConstants.CARD_STATUS_INACTIVE);
					statusCardDTO.setCardnum(cardDTO.getCardnum());
					this.clientService.updCardStatus(statusCardDTO);

//					logger.info("Actualiza tarjeta para eliminar la reimpresi�n");
//					CardDTO card = new CardDTO();
//					card.setIdCard(cardDTO.getIdCard());
//					card.setReqCardReprint(Boolean.FALSE);
//					this.clientService.updCard(card);
				}

			}

			// actualizamos la cola con status OK
			msg.setDateSend(LocalDateTime.now());
			msg.setStatus(SafConstants.SAF_STATUS_SENDED);
			safService.updateMessage(msg);
			loggerProcessVehicleCard.info("Actualizar mensaje SAF para vehiculo " + vehicle.getIdVehicle());

		} catch (OrpakWSExceptions e) {
			loggerProcessVehicleCard.error("Ha ocurrido un error en el proceso. " + e.getMessage());
			loggerProcessVehicleCard.error("" + e.getUri());
			ProcessOrpakException exc = new ProcessOrpakException(e.getMessage());
			exc.setReturnCode(e.getReturnCode());
			exc.setUri(e.getUri());
			throw exc;
		} catch (ClientNotFoundException | VehicleException | ClientException | SafException | CardException e) {
			loggerProcessVehicleCard.warn("Tarjeta no pudo ser actualizada. " + e.getMessage());
			throw new ProcessOrpakException(e.getMessage());
		}

	}

	@Override
	public void renamePendingVehicleCardChange(MessageDTO msg) throws ProcessOrpakException {

		try {

			// consultamos el veh�culo asociado a la tarjeta
			VehicleDTO vehicle = clientService.getVehicleById(msg.getIdElement());

			ClientDTO client = this.clientService.getClientById(vehicle.getIdClient());

			DepartmentDTO department = this.clientService.getListDepartmentById(vehicle.getIdDepartment());

			loggerProcessVehicleCard.info("Datos vehiculo " + vehicle.getIdVehicle());
			loggerProcessVehicleCard.info("Datos cliente " + client.getIdClient());
			loggerProcessVehicleCard.info("Datos departamento " + department.getIdDepartment());
			loggerProcessVehicleCard.debug("" + vehicle);
			loggerProcessVehicleCard.debug("" + client);
			loggerProcessVehicleCard.debug("" + department);

			// TODO SKP ntarjetas...
			// Por cada tarjeta asociada al vehiculo se debe enviar a Orpak
			List<CardDTO> lista = vehicle.getCard();
			for (CardDTO cardDTO : lista) {

				// Se salta el vehiculo si se encuenta Pendiente y es producto de una
				// reimpresi�n
				// S�lo se deben dejar pasar vehiculos Pendientes cuando se est� incorporando
				// una nueva tarjeta y esta se encuentra Pendiente
				if (ClientConstants.CARD_STATUS_PENDING.equals(cardDTO.getCardStatus())
						&& ClientConstants.VEHICLE_REQCARDNUMBER_STATUS_CREATED.equals(cardDTO.getReqcardStatus())
						&& cardDTO.getReqCardReprint()) {
					continue;
				}

				// actualizamos datos del vehiculo
				ResponseDTO retorno = new ResponseDTO();
				try {
					retorno = orpakWSService.renameVehicleCard(vehicle, cardDTO, department, client);
					loggerProcessVehicleCard.debug("" + retorno);
				} catch (OrpakWSVehicleCardExistExceptions e) {
					loggerProcessVehicleCard
							.info("Veh�culo existen en Orpak. Se continua con el proceso. " + e.getMessage());
					retorno.setReturnCode(e.getReturnCode());
					retorno.setUri(e.getUri());
					retorno.setMessage(e.getMessage());
				}

				loggerProcessVehicleCard.info("Ingresar mensaje al historia de la SAF");
				MessageHistoryDTO message = new MessageHistoryDTO();
				message.setDateIns(LocalDateTime.now());
				message.setIdQueue(msg.getIdQueue());
				message.setReturnCode(retorno.getReturnCode());
				message.setUri(retorno.getUri());
				message.setMessage(retorno.getMessage());
				this.safService.pushMessageHistory(message);

				// Se marca como estado renombrada para seguir visualizando en BackOffice.
				loggerProcessVehicleCard.info("Se elimina la tarjeta " + cardDTO);
				if (!ClientConstants.CARD_STATUS_ELIMINADO.equals(cardDTO.getCardStatus())) {
					StatusCardDTO statusCardDTO = new StatusCardDTO();
					statusCardDTO.setIdCard(cardDTO.getIdCard());
					statusCardDTO.setCardStatus(ClientConstants.CARD_STATUS_RENAME);
					statusCardDTO.setCardnum(cardDTO.getCardnum());
					loggerProcessVehicleCard.info("Actualiza estado de la tarjeta y deja SAF");
					this.clientService.updCardStatus(statusCardDTO);
				}

			}

			// actualizamos la cola con status OK
			msg.setDateSend(LocalDateTime.now());
			msg.setStatus(SafConstants.SAF_STATUS_SENDED);
			safService.updateMessage(msg);
			loggerProcessVehicleCard.info("Actualizar mensaje SAF para vehiculo " + vehicle.getIdVehicle());

		} catch (OrpakWSExceptions e) {
			loggerProcessVehicleCard.error("Ha ocurrido un error en el proceso. " + e.getMessage());
			loggerProcessVehicleCard.error("" + e.getUri());
			ProcessOrpakException exc = new ProcessOrpakException(e.getMessage());
			exc.setReturnCode(e.getReturnCode());
			exc.setUri(e.getUri());
			throw exc;
		} catch (ClientNotFoundException | VehicleException | ClientException | SafException | CardException e) {
			loggerProcessVehicleCard.warn("Tarjeta no pudo ser actualizada. " + e.getMessage());
			throw new ProcessOrpakException(e.getMessage());
		}

	}

}
