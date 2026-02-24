package com.evertecinc.b2c.enex.integracion.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.evertecinc.b2c.enex.client.exceptions.CardException;
import com.evertecinc.b2c.enex.client.exceptions.DepartmentException;
import com.evertecinc.b2c.enex.client.exceptions.NuevoMontoException;
import com.evertecinc.b2c.enex.client.exceptions.PriceLiterNotFoundException;
import com.evertecinc.b2c.enex.client.exceptions.ProductDepartmentException;
import com.evertecinc.b2c.enex.client.model.dto.CardDTO;
import com.evertecinc.b2c.enex.client.model.dto.ClientDTO;
import com.evertecinc.b2c.enex.client.model.dto.DepartmentDTO;
import com.evertecinc.b2c.enex.client.model.dto.MonitorCardDTO;
import com.evertecinc.b2c.enex.client.model.dto.ProductDepartmentDTO;
import com.evertecinc.b2c.enex.client.model.dto.StatusCardDTO;
import com.evertecinc.b2c.enex.client.model.dto.VehicleDTO;
import com.evertecinc.b2c.enex.client.model.dto2.orpak.CardConstraintRequest;
import com.evertecinc.b2c.enex.client.model.dto2.orpak.CardConstraintResponse;
import com.evertecinc.b2c.enex.client.model.dto2.orpak.CreateDepartmentRequest;
import com.evertecinc.b2c.enex.client.model.dto2.orpak.CreateDepartmentResponse;
import com.evertecinc.b2c.enex.client.model.dto2.orpak.Limit;
import com.evertecinc.b2c.enex.client.model.dto2.orpak.RequestBalanceDepartmentDTO;
import com.evertecinc.b2c.enex.client.model.dto2.orpak.ResponseBalanceDepartmentDTO;
import com.evertecinc.b2c.enex.client.model.dto2.orpak.Store;
import com.evertecinc.b2c.enex.client.model.dto2.orpak.TimeRestiction;
import com.evertecinc.b2c.enex.client.model.dto2.orpakInterface.CreateVehicleCardRequest;
import com.evertecinc.b2c.enex.client.model.dto2.orpakInterface.CreateVehicleCardResponse;
import com.evertecinc.b2c.enex.client.model.dto2.restResponses.UpdateCustomerBalanceResponse;
import com.evertecinc.b2c.enex.client.service.ClientService;
import com.evertecinc.b2c.enex.integracion.constants.ClientConstants;
import com.evertecinc.b2c.enex.integracion.constants.OrpakWSConstants;
import com.evertecinc.b2c.enex.integracion.constants.OrpakWSResponseConstants;
import com.evertecinc.b2c.enex.integracion.exception.OrpakException;
import com.evertecinc.b2c.enex.integracion.exception.OrpakWSCardConstraintExistExceptions;
import com.evertecinc.b2c.enex.integracion.exception.OrpakWSClientExistExceptions;
import com.evertecinc.b2c.enex.integracion.exception.OrpakWSExceptions;
import com.evertecinc.b2c.enex.integracion.exception.OrpakWSTrxCardBalanceExistExceptions;
import com.evertecinc.b2c.enex.integracion.exception.OrpakWSTrxCustomerBalanceExistExceptions;
import com.evertecinc.b2c.enex.integracion.exception.OrpakWSTrxDepartmentBalanceExistExceptions;
import com.evertecinc.b2c.enex.integracion.exception.OrpakWSVehicleCardExistExceptions;
import com.evertecinc.b2c.enex.integracion.model.dto.CardConstraintDTO;
import com.evertecinc.b2c.enex.integracion.model.dto.ChangeCardStatusRequest;
import com.evertecinc.b2c.enex.integracion.model.dto.ChangeCardStatusResponse;
import com.evertecinc.b2c.enex.integracion.model.dto.ChangeDepartmentStatusDTO;
import com.evertecinc.b2c.enex.integracion.model.dto.ChangeDepartmentStatusRequest;
import com.evertecinc.b2c.enex.integracion.model.dto.ChangeDepartmentStatusResponse;
import com.evertecinc.b2c.enex.integracion.model.dto.CreateClientDTO;
import com.evertecinc.b2c.enex.integracion.model.dto.CreateCustomerContractRequest;
import com.evertecinc.b2c.enex.integracion.model.dto.CreateCustomerContractResponse;
import com.evertecinc.b2c.enex.integracion.model.dto.CreateDepartmentDTO;
import com.evertecinc.b2c.enex.integracion.model.dto.CreateDepartmentOfFleetDTO;
import com.evertecinc.b2c.enex.integracion.model.dto.LoadQuantityCardDTO;
import com.evertecinc.b2c.enex.integracion.model.dto.ResponseDTO;
import com.evertecinc.b2c.enex.integracion.model.dto.UpdCardBalanceDTO;
import com.evertecinc.b2c.enex.integracion.model.dto.UpdCustomerBalanceDTO;
import com.evertecinc.b2c.enex.integracion.model.dto.UpdDepartmentBalanceDTO;
import com.evertecinc.b2c.enex.model.dto.request.UpdateCustomerBalanceRequest;
import com.evertecinc.b2c.enex.process.orpak.exceptions.ProcessOrpakException;
import com.evertecinc.b2c.enex.saf.constants.SafConstants;
import com.evertecinc.b2c.enex.saf.exceptions.SafException;
import com.evertecinc.b2c.enex.saf.model.dto.MessageDTO;
import com.evertecinc.b2c.enex.saf.model.dto.MessageHistoryDTO;
import com.evertecinc.b2c.enex.saf.service.SafService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class OrpakWSServiceImpl implements OrpakWSService {
	
	private static final Logger loggerProcessChangeCardStatus 			= LoggerFactory.getLogger("ChangeCardStatusOrpakProcessImpl");
	private static final Logger loggerProcessChangeClient 				= LoggerFactory.getLogger("ChangeClientOrpakWSImpl");
	private static final Logger loggerProcessChangeDeptStatus 			= LoggerFactory.getLogger("ChangeDeptStatusOrpakWSImpl");
	private static final Logger loggerProcessChangeDeptStatusCond 		= LoggerFactory.getLogger("ChangeDeptStatusCondWSImpl");
	private static final Logger loggerProcessChangeTypeBalance1Litros 	= LoggerFactory.getLogger("ChangeTypeBalance1LitrosWSImpl");
	
	private static final Logger loggerProcessSendClient                 = LoggerFactory.getLogger("SendClientOrpakProcessImpl");
	private static final Logger loggerProcessVehicleCard				= LoggerFactory.getLogger("VehicleCardProcessImpl");
	
	private static final Logger loggerProcessChangeCustomerBalance		= LoggerFactory.getLogger("ChangeCustomerBalanceProcessImpl");
	
	
	
	
	
	@Autowired
	@Qualifier("OrpakRestClientServiceImpl") // ojo OrpakRestClientServiceImpl
	OrpakRestClientService orpakAdapter;
	
	@Autowired
	@Qualifier("SafServiceImpl")
	private SafService safService;
	
	@Autowired
	private ClientService clientService;

	@Override
	public ResponseDTO updateCardStatus(StatusCardDTO statusCard, String sender) throws OrpakWSExceptions {
		
		log.debug("Ejecutar interfaz changeCardStatus.");

		loggerProcessChangeCardStatus.info("Ejecutar interfaz changeCardStatus.");
		
		ChangeCardStatusRequest request = new ChangeCardStatusRequest();
		request.setCardnumber(statusCard.getCardnum());
		request.setSender(sender);
		if( "A".equals(statusCard.getCardStatus()) ) {
			request.setStatus("A");
		} else {
			request.setStatus("C");
		}
		
		ResponseDTO response = new ResponseDTO();
		try {
			ChangeCardStatusResponse resp = orpakAdapter.changeCardStatus(request);
			loggerProcessChangeCardStatus.info("Cod Respuesta: " + resp.getReturnCode() + " " + resp.getMessage()  + " " + resp.getUri());
			
			response.setReturnCode(String.valueOf(resp.getReturnCode()));
			response.setUri(resp.getUri());

			// Si hay error en el retorno se debe enviar la excepcion
			String returnCode = response.getReturnCode();
			if( returnCode == null || Integer.parseInt(returnCode) < 0){
				loggerProcessChangeCardStatus.warn("Ha ocurrido un error " + returnCode);
				
				String message;
				if(OrpakWSConstants.RETORNO_UNO.equals(returnCode)){
					message = OrpakWSResponseConstants.PROCESO_USC_1;
				}else if(OrpakWSConstants.RETORNO_DOS.equals(returnCode)){
					message = OrpakWSResponseConstants.PROCESO_USC_2;
				}else if(OrpakWSConstants.RETORNO_TRES.equals(returnCode)){
					message = OrpakWSResponseConstants.PROCESO_USC_3;
				}else{
					message = OrpakWSConstants.RETORNO_DESCONOCIDO;
				}					
				
				OrpakWSExceptions exc = new OrpakWSExceptions(message + " (" + returnCode + ")");
				exc.setUri(response.getUri());
				exc.setReturnCode(returnCode);
				throw exc;
			} else {
				response.setMessage(OrpakWSResponseConstants.PROCESO_USC_0 + " (" + returnCode + ")");
			}
			
		} catch (OrpakException e) {
			loggerProcessChangeCardStatus.error("Ha ocurrido un error. " + e.getMessage());
			OrpakWSExceptions exc = new OrpakWSExceptions(e);			
			if(e.toString().toLowerCase().contains("timeout")){
				exc.setReturnCode(OrpakWSConstants.RETORNO_TIME_OUT);
			}
			exc.setUri(response.getUri());
			throw exc;				
		}			
			
		return response;
	}

	@Override
	public ResponseDTO updateClient(CreateClientDTO clientWs) {
		loggerProcessChangeClient.debug("Iniciando invocacion updateClient");
		
		CreateCustomerContractRequest req = new CreateCustomerContractRequest();
		req.setAddress1(clientWs.getAddress1());
		req.setAddress2(clientWs.getAddress2());
		req.setAlternativecode(clientWs.getAlternativecode());
		req.setCity(clientWs.getCity());
		req.setContactname(clientWs.getContactname());
		req.setCustomercode(clientWs.getCustomercode());
		req.setCustomerdescription(clientWs.getCustomerdescription());
		req.setDisctrict(clientWs.getDisctrict());
		req.setEmail(clientWs.getEmail());
		req.setFax(clientWs.getFax());
		req.setGiro(clientWs.getGiro());
		req.setOperationtype(OrpakWSConstants.ORPAK_WS_CREATE_CLIENT_CONTRACT_TYPE_UPDATE);
		req.setPhone(clientWs.getPhone());
		req.setRut(clientWs.getRut());
		req.setStatus(clientWs.getStatus());
		req.setZip(clientWs.getZip());
		req.setCreditlimit(clientWs.getCreditlimit());
		req.setSecurity(clientWs.getSecurity());
		req.setWarninglevel(clientWs.getWarninglevel());
		req.setContractstartdate(clientWs.getContractstartdate());
		req.setContractenddate(clientWs.getContractenddate());
		
		ResponseDTO response = new ResponseDTO();
		
		try {
			CreateCustomerContractResponse resp = orpakAdapter.createClientContract(req);
			loggerProcessChangeClient.info("createClientContract Codido Respuesta: " + resp.getReturnCode() + " " + resp.getMessage() + " " + resp.getUri());
			
			response.setReturnCode(String.valueOf(resp.getReturnCode()));
			response.setUri(resp.getUri());
			
			// Si hay error en el retorno se debe enviar la excepcion
			String returnCode = response.getReturnCode();
			if( returnCode == null || Integer.parseInt(returnCode) < 0){
				loggerProcessChangeClient.warn("Ha ocurrido un error " + returnCode);
				
				String message;
				if(OrpakWSConstants.RETORNO_UNO.equals(returnCode)){
					message = OrpakWSResponseConstants.PROCESO_UCO_1;
				}else if(OrpakWSConstants.RETORNO_DOS.equals(returnCode)){
					message = OrpakWSResponseConstants.PROCESO_UCO_2;
				}else if(OrpakWSConstants.RETORNO_TRES.equals(returnCode)){
					message = OrpakWSResponseConstants.PROCESO_UCO_3;
				}else if(OrpakWSConstants.RETORNO_CUATRO.equals(returnCode)){
					message = OrpakWSResponseConstants.PROCESO_UCO_4;
				}else if(OrpakWSConstants.RETORNO_CINCO.equals(returnCode)){
					message = OrpakWSResponseConstants.PROCESO_UCO_5;
				}else if(OrpakWSConstants.RETORNO_CINCO.equals(returnCode)){
					message = OrpakWSResponseConstants.PROCESO_UCO_6;
				}else{
					message = OrpakWSConstants.RETORNO_DESCONOCIDO;
				}					
				
				OrpakWSExceptions exc = new OrpakWSExceptions(message + " (" + returnCode + ")");
				exc.setUri(response.getUri());
				exc.setReturnCode(returnCode);
				throw exc;
			} else {
				response.setMessage(OrpakWSResponseConstants.PROCESO_UCO_0 + " (" + returnCode + ")");
			}
			
		} catch (OrpakException e) {
			loggerProcessChangeClient.error("Ha ocurrido un error. " + e.getMessage());
			OrpakWSExceptions exc = new OrpakWSExceptions(e);			
			if(e.toString().toLowerCase().contains("timeout")){
				exc.setReturnCode(OrpakWSConstants.RETORNO_TIME_OUT);
			}
			exc.setUri(response.getUri());
			throw exc;			
		}
		
		return response;
	}

	@Override
	public ResponseDTO updateDepartmentStatus(ChangeDepartmentStatusDTO changeDepartmentStatus)	throws OrpakWSExceptions {
		
		loggerProcessChangeDeptStatus.debug("Iniciando proceso changeDepartmentStatus");
		
		ChangeDepartmentStatusRequest request = new ChangeDepartmentStatusRequest();
		request.setCustomercode(changeDepartmentStatus.getCustomerCode());
		request.setFleetdepartment(changeDepartmentStatus.getFleetDepartment());
		request.setStatus(changeDepartmentStatus.getStatus());

		ResponseDTO response = new ResponseDTO();
		try {
			loggerProcessChangeDeptStatus.info("Ejecutar interfaz con Orpak " + request.getCustomercode() + " " + request.getFleetdepartment() + " " + request.getStatus());
			ChangeDepartmentStatusResponse resp = orpakAdapter.changeDepartmentStatus(request);
			loggerProcessChangeDeptStatus.info("changeDepartmentStatus Codido Respuesta: " + resp.getReturnCode() + " " + resp.getMessage()  + " " + resp.getUri());
			
			response.setReturnCode(String.valueOf(resp.getReturnCode()));
			response.setUri(resp.getUri());
			
			// Si hay error en el retorno se debe enviar la excepcion
			String returnCode = response.getReturnCode();
			if( returnCode == null || Integer.parseInt(returnCode) < 0){
				loggerProcessChangeDeptStatus.warn("Ha ocurrido un error " + returnCode);
				
				String message;
				if(OrpakWSConstants.RETORNO_UNO.equals(returnCode)){
					message = OrpakWSResponseConstants.PROCESO_USD_1;    //this.usdRetornoUno;
				}else if(OrpakWSConstants.RETORNO_DOS.equals(returnCode)){
					message = OrpakWSResponseConstants.PROCESO_USD_2;
				}else if(OrpakWSConstants.RETORNO_TRES.equals(returnCode)){
					message = OrpakWSResponseConstants.PROCESO_USD_3;
				}else if(OrpakWSConstants.RETORNO_CUATRO.equals(returnCode)){
					message = OrpakWSResponseConstants.PROCESO_USD_4;					
				}else{
					message = OrpakWSConstants.RETORNO_DESCONOCIDO;
				}					
				
				OrpakWSExceptions exc = new OrpakWSExceptions(message + " (" + returnCode + ")");
				exc.setUri(response.getUri());
				exc.setReturnCode(returnCode);
				throw exc;
			} else {
				response.setMessage(OrpakWSResponseConstants.PROCESO_USD_0 + " (" + returnCode + ")");
			}			
			
		} catch (OrpakException e) {
			loggerProcessChangeDeptStatus.error("Ha ocurrido un error. " + e.getMessage());
			OrpakWSExceptions exc = new OrpakWSExceptions(e);			
			if(e.toString().toLowerCase().contains("timeout")){
				exc.setReturnCode(OrpakWSConstants.RETORNO_TIME_OUT);
			}
			exc.setUri(response.getUri());
			throw exc;	
		}
		
		return response;
	}
	
	@Override
	public void updChangeDepartmentStatusCond(MessageDTO msg) throws ProcessOrpakException {
		
		loggerProcessChangeDeptStatusCond.debug("SAF UTB3 id: " + msg.getIdQueue());
		
		try {

			int total = this.safService.getTotalSafConditionalsPendings(SafConstants.SAF_UPDATE_CARD_NUMBER, msg.getIdQueue());
			loggerProcessChangeDeptStatusCond.info("Existen " + total + " SAF UCB pendientes para el proceso " + msg.getIdQueue() );
			
			if(total > 0){
				loggerProcessChangeDeptStatusCond.debug("No se ejecuta el proceso " + msg.getIdQueue() + ". Hay " + total+ " saf pendientes del tipo: "+ SafConstants.SAF_UPDATE_CARD_BALANCE);
				throw new SafException("No se ejecuta el proceso " + msg.getIdQueue() + ". Hay " + total+ " saf pendientes del tipo: "+ SafConstants.SAF_UPDATE_CARD_NUMBER);
			}
			
			loggerProcessChangeDeptStatusCond.debug("Todas las SAF UCN procesadas. Se ejecuta el siguiente proceso.");

			//A proceso UTB3 se le actualiza estado a S.
			msg.setDateSend(LocalDateTime.now());
			msg.setStatus(SafConstants.SAF_STATUS_SENDED);
			safService.updateMessage(msg);
			loggerProcessChangeDeptStatusCond.info("Sacar mensaje " + msg.getIdQueue() + " de cola SAF");
			
			//Se crea saf de tipo UpdateCardBalance(UCB)
			loggerProcessChangeDeptStatusCond.info("Grabar SAF para actualizar estado de departamento: " + msg.getIdElement());
			this.safService.pushMessageUpdateDepartmentStatus(msg.getIdElement());
			
			List<MonitorCardDTO> listadoTarjetas = new ArrayList<MonitorCardDTO>();
			loggerProcessChangeDeptStatusCond.info("Se recorren las tarjetas para grabar SAF para actualizar estado");
			log.info("Se recuperan las tarjetas asociadas al departamento " + msg.getIdElement());
			listadoTarjetas = this.clientService.getCardVehicleByDepartment(msg.getIdElement());
			log.debug(""+listadoTarjetas);
			
			for(MonitorCardDTO card : listadoTarjetas){
				log.debug("tarjeta detalles: "+card.toString());
				//Se bloquea la tarjeta
				
				StatusCardDTO datos = new StatusCardDTO();
				datos.setCardnum(card.getNumTarjeta());
				datos.setCardStatus(ClientConstants.CARD_STATUS_ACTIVE);
				datos.setIdCard(card.getIdCard());
				this.clientService.updCardStatus(datos );
			}
			
			
			//Se inserta registro en saf history
			MessageHistoryDTO messageHistory = new MessageHistoryDTO();
			messageHistory.setDateIns(LocalDateTime.now());
			messageHistory.setIdQueue(msg.getIdQueue());
			messageHistory.setReturnCode("");
			messageHistory.setUri("");
			messageHistory.setMessage("OK");
			this.safService.pushMessageHistory(messageHistory);
			loggerProcessChangeDeptStatusCond.info("Se Graba SAF History para id queue: "+ msg.getIdQueue());
		
		} catch( SafException e) {
			loggerProcessChangeDeptStatusCond.warn("Ha ocurrido un error en el proceso. " + e.getMessage());
			throw new ProcessOrpakException( e.getMessage() );
		} catch (CardException e) {
			loggerProcessChangeDeptStatusCond.warn("Ha ocurrido un error en el proceso. " + e.getMessage());
			throw new ProcessOrpakException( e.getMessage() );
		}
	}
	
	
	@Override
	public void updPendingDepartmentStatusChange( MessageDTO msg ) throws ProcessOrpakException {
		
//		try {
//
//			// consultamos por el departamento que viene en el mensaje
//			DepartmentDTO departamentos = this.clientService.getListDepartmentById(msg.getIdElement());
//			loggerProcessChangeDeptStatus.info("Consultar department " + msg.getIdElement());
//			loggerProcessChangeDeptStatus.debug(""+departamentos);
//			
//			// consultamos por el cliente
//			ClientDTO cliente = clientService.getClientById(departamentos.getIdClient());
//			loggerProcessChangeDeptStatus.info("Consultar client " + departamentos.getIdClient());
//			loggerProcessChangeDeptStatus.debug(""+cliente);
//			
//			ChangeDepartmentStatusDTO dto = new ChangeDepartmentStatusDTO();
//			if( departamentos.getDepartmentStatus().equals("A") ) {
//				dto.setStatus("A");
//			} else {
//				dto.setStatus("X");
//			}
//			
//			// actualizamos estado de los dos registros de departamento en orpak
//			
//			ResponseDTO retorno = new ResponseDTO();
//			
//				if( cliente.isCredit() && !ClientConstants.CLIENT_SCI.equals(cliente.getClientType())) {
//					dto.setCustomerCode(cliente.getCustomerCodeClient());
//					dto.setFleetDepartment(departamentos.getCodeOrpakClient());
//					loggerProcessChangeDeptStatus.debug("Actualizar departamento cr�dito " + dto.toString());
//					retorno = orpakAdapter.updateDepartmentStatus(dto);
//					loggerProcessChangeDeptStatus.debug(""+retorno);
//					
//					MessageHistoryDTO message = new MessageHistoryDTO();
//					message.setDateIns(LocalDateTime.now());
//					message.setIdQueue(msg.getIdQueue());
//					message.setReturnCode(retorno.getReturnCode());
//					message.setUri(retorno.getUri());
//					message.setMessage(retorno.getMessage());
//					this.safService.pushMessageHistory(message);
//					
//				} else {
//					if (ClientConstants.CLIENT_SCI.equals(cliente.getClientType())){
//						if (departamentos.getCodeOrpakInvoice()!= null){
//							dto.setCustomerCode(cliente.getCustomerCodeClient());
//							dto.setFleetDepartment(departamentos.getCodeOrpakInvoice());
//							log.info("Ejecutar interfaz Orpak " + dto.toString());
//						}
//						if (departamentos.getCodeOrpakTicket()!= null){
//							dto.setCustomerCode(cliente.getCustomerCodeClient());
//							dto.setFleetDepartment(departamentos.getCodeOrpakTicket());
//							log.info("Ejecutar interfaz Orpak " + dto.toString());
//						}
//						retorno = orpakAdapter.updateDepartmentStatus(dto);
//						loggerProcessChangeDeptStatus.debug(""+retorno);
//						
//						MessageHistoryDTO message = new MessageHistoryDTO();
//						message.setDateIns(LocalDateTime.now());
//						message.setIdQueue(msg.getIdQueue());
//						message.setReturnCode(retorno.getReturnCode());
//						message.setUri(retorno.getUri());
//						message.setMessage(retorno.getMessage());
//						this.safService.pushMessageHistory(message);
//					}
//					else{
//				
//						// primer env�o Prepago
//						if( cliente.getCustomerCodeTicket() != null ) {
//							dto.setCustomerCode(cliente.getCustomerCodeTicket());
//							dto.setFleetDepartment(departamentos.getCodeOrpakTicket());
//							loggerProcessChangeDeptStatus.debug("Actualizar departamento boleta " + dto.toString());
//							retorno = orpakAdapter.updateDepartmentStatus(dto);
//							loggerProcessChangeDeptStatus.debug(""+retorno);
//							
//							MessageHistoryDTO message = new MessageHistoryDTO();
//							message.setDateIns(LocalDateTime.now());
//							message.setIdQueue(msg.getIdQueue());
//							message.setReturnCode(retorno.getReturnCode());
//							message.setUri(retorno.getUri());
//							message.setMessage(retorno.getMessage());
//							this.safService.pushMessageHistory(message);
//						}
//						// segundo env�o
//						if( cliente.getCustomerCodeInvoice() != null ) {
//							dto.setCustomerCode(cliente.getCustomerCodeInvoice());
//							dto.setFleetDepartment(departamentos.getCodeOrpakInvoice());				
//							loggerProcessChangeDeptStatus.debug("Actualizar departamento factura " + dto.toString());
//							retorno = orpakAdapter.updateDepartmentStatus(dto);
//							loggerProcessChangeDeptStatus.debug(""+retorno);
//							
//							MessageHistoryDTO message = new MessageHistoryDTO();
//							message.setDateIns(LocalDateTime.now());
//							message.setIdQueue(msg.getIdQueue());
//							message.setReturnCode(retorno.getReturnCode());
//							message.setUri(retorno.getUri());
//							message.setMessage(retorno.getMessage());
//							this.safService.pushMessageHistory(message);
//						}
//					}
//				}
////			}
//			// actualizamos la cola con status OK
//			msg.setDateSend(LocalDateTime.now());
//			msg.setStatus(SafConstants.SAF_STATUS_SENDED);
//			safService.updateMessage(msg);
//			
//		
//		} catch (OrpakWSExceptions e) {
//			loggerProcessChangeCardStatus.error("Ha ocurrido un error en el proceso. " + e.getMessage());
//			loggerProcessChangeCardStatus.error(""+e.getUri());			
//			ProcessOrpakException exc = new ProcessOrpakException( e.getMessage() );
//			exc.setReturnCode(e.getReturnCode());
//			exc.setUri(e.getUri());
//			throw exc;
//		} catch(ClientException | ClientNotFoundException | SafException e) {
//			// silent catch, actualizamos la cola con status PENDING y reintentos +1, y sigue procesando 
//			loggerProcessChangeCardStatus.warn("Tarjeta no pudo ser actualizada. " + e.getMessage());
//			throw new ProcessOrpakException( e.getMessage() );
//		} 
		
	}
	
	
	@Override
	public void updTypeBalance1Litros(MessageDTO msg) throws ProcessOrpakException {
		
		loggerProcessChangeTypeBalance1Litros.info("Type balance condicional 1 LT " + msg.getIdElement());
		
		try {

			int total = this.safService.getTotalSafConditionalsPendings(SafConstants.SAF_LOAD_QUANTITY_CARD, msg.getIdQueue());
			loggerProcessChangeTypeBalance1Litros.debug("Existen " + total + " SAF LQC pendientes para el proceso " + msg.getIdQueue() );

			if(total > 0){
				loggerProcessChangeTypeBalance1Litros.debug("No se ejecuta el proceso " + msg.getIdQueue() + ". Hay " + total+ " saf pendientes del tipo: "+ SafConstants.SAF_LOAD_QUANTITY_CARD);
				throw new SafException("No se ejecuta el proceso " + msg.getIdQueue() + ". Hay " + total+ " saf pendientes del tipo: "+ SafConstants.SAF_LOAD_QUANTITY_CARD);
			}
			
			loggerProcessChangeTypeBalance1Litros.debug("Todas las SAF LQC procesadas. Se ejecuta el proceso.");
			
			//Se obtienen saldos, tipo de documento y producto desde mensaje saf.
			String data = msg.getData();
			loggerProcessChangeTypeBalance1Litros.info("Se obtienen litros, tipo de documento y producto desde mensaje SAF: "+ data);
			
			if(data == null || data.equals("")){
				loggerProcessChangeTypeBalance1Litros.error("No existe informaci�n para actualizar litros de departamento "+ msg.getIdElement());
				throw new SafException("No existe informaci�n para actualizar litros de departamento "+ msg.getIdElement());
			}

			//A proceso UTB1 se le actualiza estado a S.
			msg.setDateSend(LocalDateTime.now());
			msg.setStatus(SafConstants.SAF_STATUS_SENDED);
			safService.updateMessage(msg);
			loggerProcessChangeTypeBalance1Litros.info("Sacar mensaje " + msg.getIdQueue() + " de cola SAF");
				
			//Se llama al siguiente proceso: UTB2
			MessageDTO message = new MessageDTO();
			message.setType(SafConstants.SAF_CONDITIONAL_LT_2);
			message.setIdElement(msg.getIdElement());
			Long idUtb2 = this.safService.pushMessageSafConditional(message);
			loggerProcessChangeTypeBalance1Litros.info("Se crea saf UTBL2 con id: "+ idUtb2);
			
			List<String> listData = List.of(data.split(","));
			
			for (String stringData : listData) {
					
				ProductDepartmentDTO criterio = new ProductDepartmentDTO();
				String objeto = stringData;
				String[] elementos = objeto.split(";");
				String tipoDoc = elementos[0];
				String producto =  elementos[1];
				String saldo = elementos[2];
				loggerProcessChangeTypeBalance1Litros.debug("Los valores obtenidos de data son: Tipo documento: "+tipoDoc+", Producto: "+ producto+", Litros: "+saldo);
					
				//Se setean valores en criterio
				criterio.setDocumentType(tipoDoc);
				criterio.setProductCode(producto);
				criterio.setIdDepartment(msg.getIdElement());
				loggerProcessChangeTypeBalance1Litros.debug("Departamento con id: "+ msg.getIdElement());
					
				//Se consulta si existe producto y tipo de documento en departamento.
				List<ProductDepartmentDTO> listadoProdDep = this.clientService.doProductsDepartmentsByCriteria(criterio);
				loggerProcessChangeTypeBalance1Litros.debug(""+listadoProdDep);
				
				BigDecimal saldoDepto = BigDecimal.ZERO;
				
				if(listadoProdDep.size()>0){
					loggerProcessChangeTypeBalance1Litros.debug("Litros actuales de departamento: "+ listadoProdDep.get(0).getRemainingAmount());
						
					//Se actualiza saldo
					//saldoDepto = listadoProdDep.get(0).getRemainingAmount().add(new BigDecimal(saldo));
					saldoDepto = new BigDecimal(saldo);
					criterio.setRemainingAmount(saldoDepto);
					this.clientService.updDepartmentProductAmount(criterio);
					loggerProcessChangeTypeBalance1Litros.debug("Producto Departamento ha sido actualizado con nueva cantidad de litros: "+saldoDepto);
						
				}else{
					loggerProcessChangeTypeBalance1Litros.info("No existe el tipo de documento: "+ tipoDoc+" y producto: "+ producto+" para el depto "+ msg.getIdElement()+" por lo que se inserta.");
					saldoDepto = new BigDecimal(saldo);
					criterio.setRemainingAmount(saldoDepto);
					this.clientService.addProductDepartmentRel(criterio);
					loggerProcessChangeTypeBalance1Litros.debug("Producto Departamento ha sido creado con litros: "+saldo);
				}
				
				//Se crea saf para actualizar litros del departamento (UDB).
				loggerProcessChangeTypeBalance1Litros.info("Se Graba SAF para actualizar litros del departamento: "+ msg.getIdElement());
				if (saldoDepto.compareTo(BigDecimal.ZERO) != 0){
					this.safService.pushMessageUpdateDepartmentBalanceConditional(msg.getIdElement(), saldoDepto, producto, tipoDoc, idUtb2);
				}
				
			}
			
			//Se inserta registro en saf history
			MessageHistoryDTO messageHistory = new MessageHistoryDTO();
			messageHistory.setDateIns(LocalDateTime.now());
			messageHistory.setIdQueue(msg.getIdQueue());
			messageHistory.setReturnCode("");
			messageHistory.setUri("");
			messageHistory.setMessage("OK");
			this.safService.pushMessageHistory(messageHistory);
			loggerProcessChangeTypeBalance1Litros.info("Se Graba SAF History para id queue: "+ msg.getIdQueue());
			
		} catch( SafException | ProductDepartmentException | NuevoMontoException | DepartmentException e) {
			loggerProcessChangeTypeBalance1Litros.warn("Ha ocurrido un error en el proceso. " + e.getMessage());
			throw new ProcessOrpakException( e.getMessage() );
		}
		
		
	}

	@Override
	public ResponseDTO deleteDepartmentOfFleet(CreateDepartmentOfFleetDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseDTO updateCardBalance(UpdCardBalanceDTO dto) throws OrpakWSTrxCardBalanceExistExceptions {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseDTO updateCardConstraint(CardConstraintDTO cardConstraint) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseDTO updateCustomerBalance(UpdCustomerBalanceDTO dto) throws OrpakWSTrxCustomerBalanceExistExceptions {
		
		loggerProcessChangeCustomerBalance.debug("Iniciando consulta updateCustomerBalance");
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		
		UpdateCustomerBalanceRequest req = new UpdateCustomerBalanceRequest();
		req.setAmount(dto.getAmount());
		req.setCustomerCode(dto.getCustomerCode());
		String formattedDate = dto.getDate().format(formatter);
		req.setDate(formattedDate);
		req.setTransactionId(dto.getTransactionId());
		req.setRemarks( dto.getRemarks());
		
		ResponseDTO response = new ResponseDTO();
		
		try {
			UpdateCustomerBalanceResponse resp = orpakAdapter.updateCustomerBalance(req);
			loggerProcessChangeCustomerBalance.info("Cod Respuesta: " + resp.getReturnCode() + " " + resp.getMessage() + " " + resp.getUri());
			
			response.setReturnCode(String.valueOf(resp.getReturnCode()));
			response.setUri(resp.getUri());

			// si el codigo de retorno no es exitoso, se lanza excepcion
			String returnCode = response.getReturnCode();
			if( "-".equals(resp.getReturnCode().substring(0, 1)) ) {
				String message = "";
			    switch (returnCode) {
			        case "-1":
			            message = OrpakWSResponseConstants.PROCESO_UUB_1;
			            break;
			        case "-2":
			            message = OrpakWSResponseConstants.PROCESO_UUB_2;
			            break;
			        case "-3":
			            message = OrpakWSResponseConstants.PROCESO_UUB_3;
			            break;
			        case "-4":
			            message = OrpakWSResponseConstants.PROCESO_UUB_4;
			            break;
			        case "-5":
			            message = OrpakWSResponseConstants.PROCESO_UUB_5;
			            break;
			        default:
			            message = "Código desconocido";
			            break;
			    }
				
				if(OrpakWSConstants.RETORNO_TRES.equals(returnCode)){
					loggerProcessChangeCustomerBalance.warn("Trx ya existe en Orpak");
					OrpakWSTrxCustomerBalanceExistExceptions exc = new OrpakWSTrxCustomerBalanceExistExceptions( message + " (" + response.getReturnCode() + ")" );
					exc.setReturnCode(returnCode);
					exc.setUri(resp.getUri());
					throw exc;
				}
				
				loggerProcessChangeCustomerBalance.error("Trx sin exito " + message + " (" + returnCode + ")");
				OrpakWSExceptions exc = new OrpakWSExceptions(message + " (" + returnCode + ")");
				exc.setUri(response.getUri());
				exc.setReturnCode(returnCode);
				throw exc;
				
			}else{
				response.setMessage( "OK " + " (" + returnCode + ")");
			}			
			
		} catch (OrpakException e) {
			loggerProcessChangeCustomerBalance.error("Ha ocurrido un error. " + e.getMessage());
			OrpakWSExceptions exc = new OrpakWSExceptions(e);
			if(e.toString().toLowerCase().contains("timeout")){
				exc.setReturnCode(OrpakWSConstants.RETORNO_TIME_OUT);
			}
			exc.setUri(response.getUri());
			throw exc;
		}
		
		return response;
	}

	
	@Override
	public String createClient(CreateClientDTO dto) throws OrpakWSExceptions, OrpakWSClientExistExceptions {
		
		loggerProcessSendClient.debug("Iniciando invocacion createClientContract");
		
		String codCliente;
		
		CreateCustomerContractRequest req = new CreateCustomerContractRequest();
		req.setAddress1(dto.getAddress1());
		req.setAddress2(dto.getAddress2());
		req.setAlternativecode(dto.getAlternativecode());
		req.setCity(dto.getCity());
		req.setContactname(dto.getContactname());
		req.setCustomercode(dto.getCustomercode());
		req.setCustomerdescription(dto.getCustomerdescription());
		req.setDisctrict(dto.getDisctrict());
		req.setEmail(dto.getEmail());
		req.setFax(dto.getFax());
		req.setGiro(dto.getGiro());
		req.setOperationtype(OrpakWSConstants.ORPAK_WS_CREATE_CLIENT_CONTRACT_TYPE_CREATE);
		req.setPhone(dto.getPhone());
		req.setRut(dto.getRut());
		req.setStatus(dto.getStatus());
		req.setZip(dto.getZip());
		req.setCreditlimit(dto.getCreditlimit());
		req.setSecurity(dto.getSecurity());
		req.setWarninglevel(dto.getWarninglevel());
		req.setContractstartdate(dto.getContractstartdate());
		req.setContractenddate(dto.getContractenddate());
		
		try {
			CreateCustomerContractResponse resp = orpakAdapter.createClientContract(req);
			loggerProcessSendClient.info("Cod Respuesta: " + resp.getReturnCode() + " " + resp.getMessage() + " " + resp.getUri());
			
			
			// si el codigo de retorno no es exitoso, es decir menor que cero, se lanza excepcion
			if(resp.getReturnCode() == OrpakWSConstants.ORPAK_WS_CREATE_CLIENT_CONTRACT_STATUS_EXIST){
				loggerProcessSendClient.warn("Cliente ya exite en Orpak, se continua con proceso.");
				throw new OrpakWSClientExistExceptions("Cliente ya exite en Orpak, se continua con proceso " + resp.getReturnCode());
			} else if(resp.getReturnCode() < OrpakWSConstants.ORPAK_WS_CREATE_CLIENT_CONTRACT_STATUS_OK){
				throw new OrpakWSExceptions(resp.getMessage());
			}

			codCliente = resp.getReturnCode()+"";
			
		} catch (OrpakException e) {
			loggerProcessSendClient.error("Ha ocurrido un error. " + e.getMessage());
			throw new OrpakWSExceptions(e);
		} catch (OrpakWSClientExistExceptions e) {
			loggerProcessSendClient.error("Ha ocurrido un error. " + e.getMessage());
			throw e;
		} catch (Exception e) {
		    loggerProcessSendClient.error("Error general: " + e.getMessage());
		    throw new OrpakWSExceptions(e);
		}
		
		return codCliente;
		
	}

	@Override
	public String createDepartment(CreateDepartmentDTO dto) throws OrpakWSTrxCustomerBalanceExistExceptions {
		
		loggerProcessSendClient.debug("Iniciando invocacion createDepartment");

	    String codDepartment;

	    CreateDepartmentRequest req = new CreateDepartmentRequest();
	    req.setCustomercode(dto.getCustomerCode());
	    req.setDepartmentdescription(dto.getDepartmentDescription());
	    req.setNewdepartmentdescription(dto.getDepartmentDescription());
	    req.setOperationtype(OrpakWSConstants.ORPAK_WS_CREATE_DEPARTMENT_TYPE_CREATE);

	    try {
	        CreateDepartmentResponse resp = orpakAdapter.createDepartment(req);
	        loggerProcessSendClient.info("Cod Respuesta: " + resp.getReturnCode() + " " + resp.getMessage() + " " + resp.getUri());

	        // si el codigo de retorno indica que ya existe, continuar proceso
	        if (resp.getReturnCode() == OrpakWSConstants.ORPAK_WS_CREATE_DEPARTMENT_STATUS_EXIST) {
	            loggerProcessSendClient.warn("Departamento ya existe en Orpak, se continua con proceso.");
	        } else if (resp.getReturnCode() < OrpakWSConstants.ORPAK_WS_CREATE_DEPARTMENT_STATUS_OK) {
	            throw new OrpakWSExceptions(resp.getMessage());
	        }

	        codDepartment = resp.getReturnCode() + "";

	    } catch (OrpakException e) {
	        loggerProcessSendClient.error("Ha ocurrido un error. " + e.getMessage());
	        throw new OrpakWSExceptions(e);
	    }

	    return codDepartment;
	}

	@Override
	public ResponseDTO updateDepartmentBalance(UpdDepartmentBalanceDTO dto)
			throws OrpakWSTrxDepartmentBalanceExistExceptions {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseDTO loadQuantityCard(LoadQuantityCardDTO dto)
			throws OrpakWSExceptions, OrpakWSTrxCardBalanceExistExceptions {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseDTO createCardConstraint(CardConstraintDTO cardConstraint)throws OrpakWSCardConstraintExistExceptions {

		loggerProcessVehicleCard.info("Iniciando peticion createCardConstraint");
		return trxCardConstraint( cardConstraint, OrpakWSConstants.ORPAK_WS_CREATE_CONSTRAINT_TYPE_CREATE, loggerProcessVehicleCard );

	}
	
	private ResponseDTO trxCardConstraint(CardConstraintDTO cardConstraint, int operationType,	Logger loggerTrx) throws OrpakWSCardConstraintExistExceptions {
		
		loggerTrx.info("Iniciando peticion trxCardConstraint");
		loggerTrx.debug("" + cardConstraint.toString());
		
		ArrayList<Limit> limitList = new ArrayList<Limit>();
		
		// L�mite de cantidad por per�odo
		Limit limitFrecuency = new Limit();
		limitFrecuency.setDuration(1);
		if( "D".equals(cardConstraint.getRestrType()) ) {
			limitFrecuency.setDurationtype(3);
		} else if( "S".equals(cardConstraint.getRestrType()) ) {
			limitFrecuency.setDurationtype(4);
		} else if( "M".equals(cardConstraint.getRestrType()) ) {
			limitFrecuency.setDurationtype(6);
		} else if( "L".equals(cardConstraint.getRestrType()) ) {
			limitFrecuency.setDurationtype(5);
		}else {
			limitFrecuency.setDurationtype(3);			
			loggerTrx.debug("Restricci�n type no identificado se deja como D");
		}
		limitFrecuency.setValue(cardConstraint.getRestrDailyMaxLoads().intValue()); // Se debe obtener de las restricciones definidas 
		limitFrecuency.setValuetype(0); // 0 por frecuencia
		limitList.add(limitFrecuency);

		// L�mite de pesos por per�odo 
		Limit limitAmount = new Limit();
		limitAmount.setDuration(1);
		if( "D".equals(cardConstraint.getRestrType()) ) {
			limitAmount.setDurationtype(3);
		} else if( "S".equals(cardConstraint.getRestrType()) ) {
			limitAmount.setDurationtype(4);
		} else if( "M".equals(cardConstraint.getRestrType()) ) {
			limitAmount.setDurationtype(6);
		} else if( "L".equals(cardConstraint.getRestrType()) ) {
			limitAmount.setDurationtype(5);
		} else {
			limitAmount.setDurationtype(3);			
			loggerTrx.debug("Restricci�n type no identificado se deja como D");
		}		
		limitAmount.setValue(cardConstraint.getRestrAmountMax().intValue()); // Se debe obtener de las restricciones definidas 
		//TODO: Revisar $$$$
		if (cardConstraint.getAmount()){
			limitAmount.setValuetype(2); // 2 por monto
		}else{
			limitAmount.setValuetype(1); // 1 por cantidad (litros)
		}
		limitList.add(limitAmount); 
		
		CardConstraintRequest req = new CardConstraintRequest();
		req.setOperationtype(operationType);
		req.setCustomercode(cardConstraint.getCustomerCode());
		req.setLimit(limitList);
		req.setPlate(cardConstraint.getPlate());

		// TODO estafciones definidas por TARJETA $$$$$
		ArrayList<Store> storeList = new ArrayList<Store>();
		List<String> lista = null;
		Boolean flagVieneAsignacionEspecial = false;
		
		if(cardConstraint.getStationsCodePersonalizadas() != null && cardConstraint.getStationsCodePersonalizadas().size() > 0) {
			flagVieneAsignacionEspecial = true;
			 for(String stationCodePersonalizada : cardConstraint.getStationsCodePersonalizadas()) {
				 Store store = new Store();
				 store.setStoreCode(stationCodePersonalizada);
				 storeList.add(store);
			 }
		} else if(cardConstraint.getTieneEstaciones() != null && !cardConstraint.getTieneEstaciones()) {
			flagVieneAsignacionEspecial = true;
			req.setStorelist(ClientConstants.CLIENT_SCS);
		} else {
			lista = cardConstraint.getListaCardStationConstraint();
			for (String storeId : lista) {
				Store store = new Store();
				store.setStoreCode(storeId);
				storeList.add(store);
			}
		}
		req.setStoreList(storeList);
		
		ArrayList<TimeRestiction> timeList = new ArrayList<TimeRestiction>();
		
		TimeRestiction timeRestiction = null;
		
		if( cardConstraint.getRestrL() ) {
			timeRestiction = new TimeRestiction();
			timeRestiction.setDay(1);
			timeRestiction.setFromtime(String.format("%02d", cardConstraint.getRestrHini())+"-00"); 
			timeRestiction.setTotime(String.format("%02d", cardConstraint.getRestrHend())+"-00");
			timeList.add(timeRestiction);
		}
		
		if( cardConstraint.getRestrM() ) {
			timeRestiction = new TimeRestiction();
			timeRestiction.setDay(2);
			timeRestiction.setFromtime(String.format("%02d", cardConstraint.getRestrHini()) + "-00"); 
			timeRestiction.setTotime(String.format("%02d", cardConstraint.getRestrHend()) + "-00");
			timeList.add(timeRestiction);
		}

		if( cardConstraint.getRestrX() ) {
			timeRestiction = new TimeRestiction();
			timeRestiction.setDay(3);
			timeRestiction.setFromtime(String.format("%02d", cardConstraint.getRestrHini()) + "-00"); 
			timeRestiction.setTotime(String.format("%02d", cardConstraint.getRestrHend()) + "-00");
			timeList.add(timeRestiction);
		}

		if( cardConstraint.getRestrJ() ) {
			timeRestiction = new TimeRestiction();
			timeRestiction.setDay(4);
			timeRestiction.setFromtime(String.format("%02d", cardConstraint.getRestrHini()) + "-00"); 
			timeRestiction.setTotime(String.format("%02d", cardConstraint.getRestrHend()) + "-00");
			timeList.add(timeRestiction);
		}

		if( cardConstraint.getRestrV() ) {
			timeRestiction = new TimeRestiction();
			timeRestiction.setDay(5);
			timeRestiction.setFromtime(String.format("%02d", cardConstraint.getRestrHini()) + "-00"); 
			timeRestiction.setTotime(String.format("%02d", cardConstraint.getRestrHend()) + "-00");
			timeList.add(timeRestiction);
		}

		if( cardConstraint.getRestrS() ) {
			timeRestiction = new TimeRestiction();
			timeRestiction.setDay(6);
			timeRestiction.setFromtime(String.format("%02d", cardConstraint.getRestrHini()) + "-00"); 
			timeRestiction.setTotime(String.format("%02d", cardConstraint.getRestrHend()) + "-00");
			timeList.add(timeRestiction);
		}

		if( cardConstraint.getRestrD() ) {
			timeRestiction = new TimeRestiction();
			timeRestiction.setDay(7);
			timeRestiction.setFromtime(String.format("%02d", cardConstraint.getRestrHini()) + "-00"); 
			timeRestiction.setTotime(String.format("%02d", cardConstraint.getRestrHend()) + "-00");
			timeList.add(timeRestiction);
		}
		
		req.setTimeList(timeList);
		req.setItemlist( cardConstraint.getItemList() );
		if(!flagVieneAsignacionEspecial) {
			if( lista != null && lista.size() <= 0 ) {
				req.setStorelist( cardConstraint.getStoreList() );
			} else {
				req.setStorelist( "" );
			}
		}
		
		if(cardConstraint.getGps()!= null){
			if(cardConstraint.getGps()){
				req.setStorelist("");
			}
		}
		
		loggerTrx.info("Data cargada correctamente.");
		
		CardConstraintResponse resp = null;
		ResponseDTO response = new ResponseDTO();
		try {
			resp = orpakAdapter.cardConstraint(req); // ENVIA LA URI
			loggerTrx.info("Respuesta desde Orpak " + resp.getReturnCode() + " " + resp.getUri());
			
			response.setReturnCode(String.valueOf(resp.getReturnCode()));
			response.setUri(resp.getUri());
						
			// Si hay error en el retorno se debe enviar la excepcion
			String returnCode = response.getReturnCode();
			if( returnCode == null || Integer.parseInt(returnCode) < 0){
				loggerTrx.warn("Ha ocurrido un error " + returnCode);
			
				String message;
				if(OrpakWSConstants.RETORNO_UNO.equals(returnCode)){
					message = OrpakWSResponseConstants.PROCESO_UCC_1;
				}else if(OrpakWSConstants.RETORNO_DOS.equals(returnCode)){
					message = OrpakWSResponseConstants.PROCESO_UCC_2;
				}else if(OrpakWSConstants.RETORNO_TRES.equals(returnCode)){
					message = OrpakWSResponseConstants.PROCESO_UCC_3;
				}else if(OrpakWSConstants.RETORNO_TRES.equals(returnCode)){
					message = OrpakWSResponseConstants.PROCESO_UCC_3;
				}else if(OrpakWSConstants.RETORNO_CUATRO.equals(returnCode)){
					message = OrpakWSResponseConstants.PROCESO_UCC_4;
				}else if(OrpakWSConstants.RETORNO_CINCO.equals(returnCode)){
					message = OrpakWSResponseConstants.PROCESO_UCC_5;
				}else if(OrpakWSConstants.RETORNO_SEIS.equals(returnCode)){
					message = OrpakWSResponseConstants.PROCESO_UCC_6;
				}else if(OrpakWSConstants.RETORNO_SIETE.equals(returnCode)){
					loggerTrx.warn("Restricci�n de tarjeta ya existe en Orpak.");
					OrpakWSCardConstraintExistExceptions exc = new OrpakWSCardConstraintExistExceptions( OrpakWSResponseConstants.PROCESO_UCC_7 + " (" + response.getReturnCode() + ")" );
					exc.setReturnCode(returnCode);
					exc.setUri(resp.getUri());
					throw exc;
				}else{
					message = OrpakWSConstants.RETORNO_DESCONOCIDO;
				}					
				
				OrpakWSExceptions exc = new OrpakWSExceptions(message + " (" + returnCode + ")");
				exc.setUri(response.getUri());
				exc.setReturnCode(returnCode);
				throw exc;
			} else {
				response.setMessage(OrpakWSResponseConstants.PROCESO_UCC_0 + " (" + returnCode + ")");
			}
			
		} catch (OrpakException e) {
			loggerTrx.error("Ha ocurrido un error. " + e.getMessage());
			OrpakWSExceptions exc = new OrpakWSExceptions(e);			
			if(e.toString().toLowerCase().contains("timeout")){
				exc.setReturnCode(OrpakWSConstants.RETORNO_TIME_OUT);
			}
			exc.setUri(response.getUri());
			throw exc;	
		}

		return response;
	}

	/**
	 * M�todo para unificar upd y add
	 * @param vehicle
	 * @param card
	 * @throws OrpakWSExceptions
	 * @throws OrpakWSVehicleCardExistExceptions 
	 */
	private ResponseDTO trxVehicleCard(VehicleDTO vehicle, CardDTO card, DepartmentDTO department, int operationType, ClientDTO client ) throws OrpakWSExceptions, OrpakWSVehicleCardExistExceptions {
		CreateVehicleCardRequest req = new CreateVehicleCardRequest();
		if( card == null ) {
			loggerProcessVehicleCard.error( "Ha ocurrido un error, no se puede enviar una tarjeta sin n�mero.");
			throw new OrpakWSExceptions("Ha ocurrido un error, no se puede enviar una tarjeta sin n�mero.");
		} else {
			req.setCardnumber( card.getCardnum() );
		}

		/*
		 * Si el vehiculo tiene documento tipo factura => se env�a DGG
		 * Si el vehiculo tiene documento tipo boleta => se env�a BOLETA
		 */
		if( ClientConstants.DOCUMENT_TYPE_INVOICE.equals(vehicle.getDocumentType()) ) {
			req.setDoctype("1");
		} else {
			req.setDoctype("2");
		}
		
		// El c�digo de departamento de orpak depende del tipo de documento a emitir
		if( client.isCredit() ) {
			if( ClientConstants.CLIENT_SCI.equals(client.getClientType()) ) {
				if( ClientConstants.DOCUMENT_TYPE_INVOICE.equals(vehicle.getDocumentType()) ) {
					req.setDepatmentcode( department.getCodeOrpakInvoice() );
				} else {
					req.setDepatmentcode( department.getCodeOrpakTicket() );
				}
				if( ClientConstants.TYPE_BALANCE_CARD.equals(department.getTypeBalance()) ) {
					req.setBalancetype( "0" ); // type balance por tarjeta
				} else {
					req.setBalancetype( "1" ); // type balance por departamento
				}
			} else if ( ClientConstants.CLIENT_SCS.equals(client.getClientType()) ){
				req.setDepatmentcode( department.getCodeOrpakClient() );
				if( ClientConstants.TYPE_BALANCE_CARD.equals(department.getTypeBalance()) ) {
					req.setBalancetype( "0" ); // type balance por tarjeta
				} else {
					req.setBalancetype( "1" ); // type balance por departamento
				}
			}else{
				req.setDepatmentcode( department.getCodeOrpakClient() );
				req.setBalancetype( "" ); // type balance por cr�dito es vac�o si no es SCI
			}
			//Si el tipo de clientes cr�dito se debe enviar la cuenta D factura
			if( ClientConstants.DOCUMENT_TYPE_INVOICE.equals(vehicle.getDocumentType()) ) {
				req.setSurname(client.getAccountJdeInvoice());				
			} else {
				req.setSurname(client.getAccountJdeTicket());
			}
			req.setCustomercode(client.getCustomerCodeClient()); // c�digo del cliente
		} else {
			if( ClientConstants.DOCUMENT_TYPE_INVOICE.equals(vehicle.getDocumentType()) ) {
				req.setDepatmentcode( department.getCodeOrpakInvoice() );
				req.setCustomercode(client.getCustomerCodeInvoice()); // c�digo del cliente
			} else {
				req.setDepatmentcode( department.getCodeOrpakTicket() );
				req.setCustomercode(client.getCustomerCodeTicket()); // c�digo del cliente
			}
			
			if( ClientConstants.TYPE_BALANCE_CARD.equals(department.getTypeBalance()) ) {
				req.setBalancetype( "0" ); // type balance por tarjeta
			} else {
				req.setBalancetype( "1" ); // type balance por departamento
			}
			req.setSurname("");
		}
		
		req.setFuelcode(vehicle.getProductCode());
		/*  
		 * Fueltype
		 * 0 un item 
		 * 1 una lista de item
		 */
		req.setFueltype("1");
		req.setOperationtype(operationType);
		req.setPlate(vehicle.getPlate());
		req.setName(""); // no existe valor para este campo
		// Si la restricci�n esta dada por departamento o veh�culo
		try {		
			if( ClientConstants.RESTRICTION_TYPE_CARD.equals(card.getRestrictionType()) ) {
				if( client.isCredit() && !ClientConstants.CLIENT_SCI.equals(client.getClientType()) && !ClientConstants.CLIENT_SCS.equals(client.getClientType()) ) {
					req.setPlafond( this.clientService.getLitroPrecio( vehicle.getProductCode(), BigDecimal.valueOf(card.getRestrDailyMaxQuantity()) ).intValue() );
				} else {
					req.setPlafond( card.getRestrDailyMaxQuantity().intValue() ); // Valor m�ximo por transacci�n
				}
			} else {
				if( client.isCredit() && !ClientConstants.CLIENT_SCI.equals(client.getClientType()) && !ClientConstants.CLIENT_SCS.equals(client.getClientType()) ) {
					req.setPlafond( this.clientService.getLitroPrecio( vehicle.getProductCode(), BigDecimal.valueOf(department.getRestrDailyMaxQuantity()) ).intValue() ); // Valor m�ximo por transacci�n
				} else {
					req.setPlafond( department.getRestrDailyMaxQuantity().intValue() ); // Valor m�ximo por transacci�n
				}
			}
		} catch (PriceLiterNotFoundException e) {
			loggerProcessVehicleCard.error( "Ha ocurrido un error, no se ha podido convertir a pesos.");
			throw new OrpakWSExceptions("Ha ocurrido un error, no se ha podido convertir a pesos.");
		}

		if (card.getCtPosition() != null){
			req.setCardtype(card.getCtPosition());
		}else{
			req.setCardtype(client.getClientType());
		}
		
		if (vehicle.getControlTotal() != null && vehicle.getControlTotal()){
			req.setVehiclerule("1");
			
			if (vehicle.getDatachip()){
				req.setDeviceusage("1");
			}else{
				req.setDeviceusage("3");
			}
			
		} else{
			req.setVehiclerule("0");
			req.setDeviceusage("1");
		}
		
		loggerProcessVehicleCard.info("Crear/Actualizar vehiculo/card en Orpak.");
		loggerProcessVehicleCard.debug(""+req);
		
		CreateVehicleCardResponse resp = null;
		ResponseDTO response = new ResponseDTO();
		try {
			resp = orpakAdapter.createVehicleCard(req);
			loggerProcessVehicleCard.info("createVehicleCard Codigo Respuesta: " + resp.getReturnCode() + " " + resp.getMessage() + " " + resp.getUri());
			
			response.setReturnCode(String.valueOf(resp.getReturnCode()));
			response.setUri(resp.getUri());
			
			// Si hay error en el retorno se debe enviar la excepcion
			String returnCode = response.getReturnCode();
			if( returnCode == null || Integer.parseInt(returnCode) < 0){
				loggerProcessVehicleCard.warn("Ha ocurrido un error " + returnCode);
			
				String message;
				if(OrpakWSConstants.RETORNO_UNO.equals(returnCode)){
					message = OrpakWSResponseConstants.PROCESO_CCN_1;
				}else if(OrpakWSConstants.RETORNO_DOS.equals(returnCode)){
					message = OrpakWSResponseConstants.PROCESO_CCN_2;
				}else if(OrpakWSConstants.RETORNO_TRES.equals(returnCode)){
					message = OrpakWSResponseConstants.PROCESO_CCN_3;
				}else if(OrpakWSConstants.RETORNO_TRES.equals(returnCode)){
					message = OrpakWSResponseConstants.PROCESO_CCN_3;
				}else if(OrpakWSConstants.RETORNO_CUATRO.equals(returnCode)){
					loggerProcessVehicleCard.warn("Veh�culo/tarjeta ya existe en Orpak.");
					OrpakWSVehicleCardExistExceptions exc = new OrpakWSVehicleCardExistExceptions( OrpakWSResponseConstants.PROCESO_CCN_4 + " (" + response.getReturnCode() + ")" );
					exc.setReturnCode(returnCode);
					exc.setUri(resp.getUri());
					throw exc;					
				}else if(OrpakWSConstants.RETORNO_CINCO.equals(returnCode)){
					message = OrpakWSResponseConstants.PROCESO_CCN_5;
				}else if(OrpakWSConstants.RETORNO_SEIS.equals(returnCode)){
					message = OrpakWSResponseConstants.PROCESO_CCN_6;
				}else if(OrpakWSConstants.RETORNO_SIETE.equals(returnCode)){
					message = OrpakWSResponseConstants.PROCESO_CCN_7;
				}else if(OrpakWSConstants.RETORNO_OCHO.equals(returnCode)){
					message = OrpakWSResponseConstants.PROCESO_CCN_8;
				}else if(OrpakWSConstants.RETORNO_NUEVE.equals(returnCode)){
					message = OrpakWSResponseConstants.PROCESO_CCN_9;
				}else if(OrpakWSConstants.RETORNO_DIEZ.equals(returnCode)){
					message = OrpakWSResponseConstants.PROCESO_CCN_10;
				}else{
					message = OrpakWSConstants.RETORNO_DESCONOCIDO;
				}					
				
				OrpakWSExceptions exc = new OrpakWSExceptions(message + " (" + returnCode + ")");
				exc.setUri(response.getUri());
				exc.setReturnCode(returnCode);
				throw exc;
			} else {
				response.setMessage(OrpakWSResponseConstants.PROCESO_CCN_0 + " (" + returnCode + ")");
			}
			
		} catch (OrpakException e) {
			loggerProcessVehicleCard.error("Ha ocurrido un error. " + e.getMessage());
			OrpakWSExceptions exc = new OrpakWSExceptions(e);			
			if(e.toString().toLowerCase().contains("timeout")){
				exc.setReturnCode(OrpakWSConstants.RETORNO_TIME_OUT);
			}
			exc.setUri(response.getUri());
			throw exc;	
		}			
			
		return response;
	}

	@Override
	public ResponseDTO createVehicleCard(VehicleDTO vehicle, CardDTO card, DepartmentDTO department, ClientDTO client) throws OrpakWSExceptions, OrpakWSVehicleCardExistExceptions {

		loggerProcessVehicleCard.info("Crear vehicle/card para patente " + vehicle.getPlate());
		return this.trxVehicleCard(vehicle, card, department, OrpakWSConstants.ORPAK_WS_CREATE_VEHICLE_CARD_TYPE_CREATE, client);
		
	}

	@Override
	public ResponseDTO updateVehicleCard(VehicleDTO vehicle, CardDTO card, DepartmentDTO department, ClientDTO client) throws OrpakWSExceptions, OrpakWSVehicleCardExistExceptions {

		loggerProcessVehicleCard.info("Actualizar vehicle/card para patente " + vehicle.getPlate());
		return this.trxVehicleCard(vehicle, card, department, OrpakWSConstants.ORPAK_WS_CREATE_VEHICLE_CARD_TYPE_UPDATE, client);
		
	}

	@Override
	public ResponseDTO renameVehicleCard(VehicleDTO vehicle, CardDTO card, DepartmentDTO department, ClientDTO client) throws OrpakWSExceptions, OrpakWSVehicleCardExistExceptions {

		
		loggerProcessVehicleCard.info("Renombrar vehicle/card a la patente " + vehicle.getPlate());
		return this.trxVehicleCard(vehicle, card, department, OrpakWSConstants.ORPAK_WS_CREATE_VEHICLE_CARD_TYPE_RENAME, client);
	}

	@Override
	public List<ResponseBalanceDepartmentDTO> getDepartmentBalance(
			RequestBalanceDepartmentDTO requestBalanceDepartmentDTO) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
