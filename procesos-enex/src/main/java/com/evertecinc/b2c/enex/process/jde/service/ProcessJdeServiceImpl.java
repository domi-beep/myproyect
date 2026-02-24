package com.evertecinc.b2c.enex.process.jde.service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.evertecinc.b2c.constants.Constantes;
import com.evertecinc.b2c.enex.audits.service.AuditsService;
import com.evertecinc.b2c.enex.client.constants.PayChileConstants;
import com.evertecinc.b2c.enex.client.exceptions.CardException;
import com.evertecinc.b2c.enex.client.exceptions.ClientException;
import com.evertecinc.b2c.enex.client.exceptions.OrderException;
import com.evertecinc.b2c.enex.client.exceptions.OrderNotFoundException;
import com.evertecinc.b2c.enex.client.exceptions.ProcessJDEExceptions;
import com.evertecinc.b2c.enex.client.model.dto.ClientDTO;
import com.evertecinc.b2c.enex.client.model.dto.CriterioBusquedaOrderDTO;
import com.evertecinc.b2c.enex.client.model.dto.DepartmentCriterioDTO;
import com.evertecinc.b2c.enex.client.model.dto.DepartmentDTO;
import com.evertecinc.b2c.enex.client.model.dto.InfPaymentBchileDTO;
import com.evertecinc.b2c.enex.client.model.dto.InfPaymentBestadoDTO;
import com.evertecinc.b2c.enex.client.model.dto.InfPaymentBsantanderDTO;
import com.evertecinc.b2c.enex.client.model.dto.InfPaymentDepositoDTO;
import com.evertecinc.b2c.enex.client.model.dto.InfPaymentTransbankDTO;
import com.evertecinc.b2c.enex.client.model.dto.MonitorCardDTO;
import com.evertecinc.b2c.enex.client.model.dto.OrderConfirmedDTO;
import com.evertecinc.b2c.enex.client.model.dto.OrderDTO;
import com.evertecinc.b2c.enex.client.model.dto.OrderItemDTO;
import com.evertecinc.b2c.enex.client.service.CardsService;
import com.evertecinc.b2c.enex.client.service.ClientService;
import com.evertecinc.b2c.enex.client.service.IOrderService;
import com.evertecinc.b2c.enex.integracion.constants.ClientConstants;
import com.evertecinc.b2c.enex.integracion.service.OrpakWSService;
import com.evertecinc.b2c.enex.process.dao.repositories.ParameterRepository;
import com.evertecinc.b2c.enex.process.exceptions.ParameterNotFoundException;
import com.evertecinc.b2c.enex.process.jde.dto.CriterioBusquedaCardDTO;
import com.evertecinc.b2c.enex.process.jde.dto.Reg1DTO;
import com.evertecinc.b2c.enex.process.jde.dto.Reg2DTO;
import com.evertecinc.b2c.enex.process.jde.exceptions.ProcessJDEException;
import com.evertecinc.b2c.enex.process.model.entities.Parameter;
import com.evertecinc.b2c.enex.saf.constants.SafConstants;
import com.evertecinc.b2c.enex.saf.exceptions.SafException;
import com.evertecinc.b2c.enex.saf.model.dto.MessageDTO;
import com.evertecinc.b2c.enex.saf.service.SafService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ProcessJdeServiceImpl implements IProcessJdeService {
	
	 private static final Logger loggerProcessChangeCardStatus 				= LoggerFactory.getLogger("ChangeCardStatusOrpakProcessImpl");
	 private static final Logger loggerProcessChangeClient 					= LoggerFactory.getLogger("ChangeClientOrpakProcessImpl");
	 private static final Logger loggerProcessChangeDeptCardStatus 			= LoggerFactory.getLogger("ChangeDepartmentCardStatusOrpakProcessImpl");
	 private static final Logger loggerProcessChangeTypeBalance1 			= LoggerFactory.getLogger("ChangeTypeBalance1OrpakProcessImpl");
	 private static final Logger loggerProcessChangeTypeBalance2Litros 		= LoggerFactory.getLogger("ChangeTypeBalance2LitrosOrpakProcessImpl");
	 private static final Logger loggerProcessChangeTypeBalance2			= LoggerFactory.getLogger("ChangeTypeBalance2ProcessImpl");
	 private static final Logger loggerProcessCreateUpdateOT             	= LoggerFactory.getLogger("CreateUpdateOTProcessImpl");
	 private static final Logger loggerProcessDeleteDepartmentFleet      	= LoggerFactory.getLogger("DeleteDepartmentFleetOrpakProcessImpl");
	 private static final Logger loggerProcessSendCardBalance            	= LoggerFactory.getLogger("SendCardBalanceOrpakProcessImpl");
	 private static final Logger loggerProcessSendCardConstraint         	= LoggerFactory.getLogger("SendCardConstraintOrpakProcessImpl");
	 private static final Logger loggerProcessSendCustomerBalance        	= LoggerFactory.getLogger("SendCustomerBalanceOrpakProcessImpl");
	 private static final Logger loggerProcessSendClient                 	= LoggerFactory.getLogger("SendClientOrpakProcessImpl");
	 private static final Logger loggerProcessSendDeptBalance            	= LoggerFactory.getLogger("SendDepartmentBalanceOrpakProcessImpl");
	 private static final Logger loggerProcessLoadQuantityCard           	= LoggerFactory.getLogger("LoadQuantityCardProcessImpl");
	 private static final Logger loggerProcessVehicleCard                	= LoggerFactory.getLogger("VehicleCardOrpakProcessImpl");
	 
	 private static final Logger loggerSendSalesNote                		= LoggerFactory.getLogger("SendSalesNoteProcessImpl");
	 
//	 private static final Logger loggerProcessChangeDeptStatus           = LoggerFactory.getLogger("ChangeDepartmentStatusOrpakProcessImpl");
	 


	 
	 
	 
	private final ClientService clientService;
    private final CardsService cardsService;
    private final OrpakWSService orpakWSService;
    private final SafService safService;
    private final AuditsService auditsService;
    private final IOrderService orderService;
    
	private final ParameterRepository parameterRepository;
	private int maximoTarjetasPorLinea = 10;


	@Override
	public Optional<List<ClientDTO>> getClientJdeByFactDepartmentStatus(boolean factDepartmentStatus) {
		return clientService.findByFactDepartment(factDepartmentStatus? 1:0);
	}


	@Override
	public List<DepartmentDTO> getListDepartmentByClient(DepartmentCriterioDTO criteriaDeptos) {
		
		return clientService.findByIdClient(criteriaDeptos.getIdClient());
	}


	@Override
	public Integer getCorrelativo() throws ParameterNotFoundException{

		String correlativo = "correlativo_jde";
		Parameter retorno = parameterRepository.findById(correlativo)
        .orElseThrow(() -> new ParameterNotFoundException("Parameter not found with key: " + correlativo));
		
		try {
            Integer correlativoInteger = Integer.valueOf(retorno.getKeyValue());
            
            correlativoInteger = correlativoInteger+1;
            
            if (correlativoInteger > 999999) {
            	correlativoInteger = 1;
            }
            retorno.setKeyValue(correlativoInteger.toString());
            parameterRepository.save(retorno);
            
            return correlativoInteger;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("El valor de keyvalue no es un número válido: " + retorno.getKeyValue(), e);
        }
	}


	@Override
	public void popOrder(MessageDTO messageDTO, String nombreArchivo, boolean agregar) throws ProcessJDEException{
		
		loggerSendSalesNote.debug("Parametros de entrada: " + messageDTO);
		
		Long idOrder = messageDTO.getIdElement();
		
		/*Declaracion de variables*/
		OrderConfirmedDTO orderConfirmed = null;
		List<Reg1DTO> listadoReg1 = new ArrayList<Reg1DTO>();
		List<Reg2DTO> listadoReg2 = new ArrayList<Reg2DTO>();
		//OrderCsvDTO orderCvs = null;
		ClientDTO cliente = null;
		
		/*Se obtienen datos de la orden*/
		try {
			
			CriterioBusquedaOrderDTO criterio = new CriterioBusquedaOrderDTO();
			criterio.setIdOrder(idOrder);
			/*Se obtiene id de usuario asociado a la orden*/
			loggerSendSalesNote.info("Recuperar datos de la orden " + idOrder);
			List<OrderDTO> datosOrden = this.orderService.getOrdersByCriterio(criterio);
			loggerSendSalesNote.debug(""+datosOrden);
			Long idUser = datosOrden.get(0).getIdUser();
			if(idUser > 0){
				loggerSendSalesNote.info("Recuperar datos de la orden confirmada " + idOrder + " " + idUser );
				orderConfirmed = this.orderService.getOrderConfirmed(idOrder, idUser);
				loggerSendSalesNote.debug(""+orderConfirmed);
			}
			
		} catch (OrderException e) {
			loggerSendSalesNote.error("Ha ocurrido un error al obtener la orden: " + e.getMessage());
			throw new ProcessJDEExceptions("Ha ocurrido un error al obtener la orden: " + e.getMessage());
		} catch (OrderNotFoundException e) {
			loggerSendSalesNote.error("Ha ocurrido un error al obtener la orden: " + e.getMessage());
			throw new ProcessJDEExceptions("Ha ocurrido un error al obtener la orden: " + e.getMessage());
		}
		
		OrderDTO order = orderConfirmed.getOrder();
		List<OrderItemDTO> listadoOrderItem = order.getListOrderItem();
		
		/*Se obtienen datos del cliente*/
		try {
			loggerSendSalesNote.info("Recuperar datos del cliente " + order.getIdClient() );
			cliente = this.clientService.getClientById(order.getIdClient());
			loggerSendSalesNote.debug(""+cliente);
		} catch (ClientException e) {
			loggerSendSalesNote.error("Ha ocurrido un error al obtener los datos del cliente: " + e.getMessage());
			throw new ProcessJDEExceptions("Ha ocurrido un error al obtener los datos del cliente: " + e.getMessage());
		}
		
		/*Se recorre listado de productos asociados a la orden*/
		if(listadoOrderItem.size() > 0){
			for(OrderItemDTO orderItem : listadoOrderItem){
				int popIdVenta = this.popIdVenta();
				loggerSendSalesNote.info("Correlativo de venta asignado " + popIdVenta);
				
				loggerSendSalesNote.info("Procesar orden " + orderItem.getIdOrder() + " item " + orderItem.getIdOrderItem() );
				
				List <MonitorCardDTO> listadoTarjetas = new ArrayList<MonitorCardDTO>();
				
				loggerSendSalesNote.info("Generar registro del tipo 1");
				Reg1DTO reg1 = new Reg1DTO();
				reg1.setTipoReg(Constantes.JDE_TYPE_1);
				reg1.setIdVenta(popIdVenta+"");
				reg1.setIdCompra(idOrder+"");
				reg1.setRutCliente(cliente.getUpi());
				// de acuerdo al tipo de orden se setean valores.
				if(order.getOrderType() == 0){
					reg1.setProducto(orderItem.getProductCode());
					reg1.setDepartamento(orderItem.getNombreDepto());
					reg1.setTipoCompra(cliente.getClientType());
					
					/*Se obtienen las tarjetas asociadas al departamento del item */
					try {
						CriterioBusquedaCardDTO criterio = new CriterioBusquedaCardDTO();
						
						// Si la compra es a departamento se deben enviar todas las tarjetas del departamento e igual tipo de producto
						// Si la compra es a la tarjeta se deben enviar las tarjetas
						// Para saber si la compra es por departamento o tarjeta, se debe revisar si el atributo idCard es nulo => compra por departamento
						if( orderItem.getIdCard() != null ) {
							criterio.setIdCard(orderItem.getIdCard());
						}
						criterio.setCombustible(orderItem.getProductCode());
						criterio.setIdDepartamento(orderItem.getIdDepartment());
						criterio.setTipoDocumento(orderItem.getDocumentType());
						listadoTarjetas = this.clientService.getListCardsByCriterio(criterio);
					} catch (CardException e) {
						loggerSendSalesNote.error("Ha ocurrido un error al obtener los datos de la tarjeta: " + e.getMessage());
						throw new ProcessJDEExceptions("Ha ocurrido un error al obtener los datos de la tarjeta: " + e.getMessage());
					}
					
					
				}else{
					reg1.setTipoCompra(Constantes.JDE_TIPO_COMPRA_POS);
				}
				
				/*Se pregunta por el tipo de documento del producto*/
				if( cliente.isCredit() ) {
					reg1.setCuenta(cliente.getAccountJdeClient());
				} else {
					if(orderItem.getDocumentType().equals(ClientConstants.DOCUMENT_TYPE_INVOICE)){
						reg1.setCuenta(cliente.getAccountJdeInvoice());
					}else{
						reg1.setCuenta(cliente.getAccountJdeTicket());
					}
				}
				reg1.setFechaCompra(new SimpleDateFormat("yyyy/MM/dd").format(order.getCtrDate()));
				reg1.setHoraCompra(new SimpleDateFormat("HH:mm:ss").format(order.getCtrDate()));
				
				reg1.setTotal(orderItem.getAmount().longValue()+"");
				
				if(order.getPayType().equals(PayChileConstants.MEDIO_DE_PAGO_DEPOSITO)){
					reg1.setMedioPago(PayChileConstants.NOMBRE_BANCO_DEPOSITO_JDE);
				}else{
					reg1.setMedioPago(Constantes.JDE_PAGO_LINEA_TF);
				}
				if(order.getPayType().equals(PayChileConstants.MEDIO_DE_PAGO_BANCO_CHILE)){
					InfPaymentBchileDTO infopago = (InfPaymentBchileDTO)orderConfirmed.getInformationPayment();
					reg1.setCodigoAutorizacionPago(infopago.getMpoutNumComp());
					reg1.setBanco(PayChileConstants.NOMBRE_BANCO_CHILE_JDE);
				}
				if(order.getPayType().equals(PayChileConstants.MEDIO_DE_PAGO_BANCO_ESTADO)){
					InfPaymentBestadoDTO infopago = (InfPaymentBestadoDTO)orderConfirmed.getInformationPayment();
					reg1.setCodigoAutorizacionPago(infopago.getResultadoTrxMpago());
					reg1.setBanco(PayChileConstants.NOMBRE_BANCO_ESTADO_JDE);
				}
				if(order.getPayType().equals(PayChileConstants.MEDIO_DE_PAGO_BANCO_SANTANDER)){
					InfPaymentBsantanderDTO infopago = (InfPaymentBsantanderDTO)orderConfirmed.getInformationPayment();
					reg1.setCodigoAutorizacionPago(infopago.getMpoutNumComp());
					reg1.setBanco(PayChileConstants.NOMBRE_BANCO_SANTANDER_JDE);
				}
				if(order.getPayType().equals(PayChileConstants.MEDIO_DE_PAGO_DEPOSITO)){
					InfPaymentDepositoDTO infopago = (InfPaymentDepositoDTO)orderConfirmed.getInformationPayment();
					reg1.setCodigoAutorizacionPago(infopago.getComprobante()); 
					reg1.setBanco(infopago.getBancoOrigen());
				}
				if(order.getPayType().equals(PayChileConstants.MEDIO_DE_PAGO_BANCO_BCI)){
					reg1.setBanco(PayChileConstants.NOMBRE_BANCO_BCI_JDE);
				}
				if(order.getPayType().equals(PayChileConstants.MEDIO_DE_PAGO_BANCO_TBANC)){
					reg1.setBanco(PayChileConstants.NOMBRE_BANCO_TBANC_JDE);
				}
				if(order.getPayType().equals(PayChileConstants.MEDIO_DE_PAGO_WEBPAY)){
					InfPaymentTransbankDTO webpay = (InfPaymentTransbankDTO)orderConfirmed.getInformationPayment();
					reg1.setBanco(PayChileConstants.NOMBRE_BANCO_WEBPAY_JDE);
					reg1.setCodigoAutorizacionPago(webpay.getAuthorizationCode());
				}
				reg1.setFechaPago(new SimpleDateFormat("yyyy/MM/dd").format(order.getPayDate()));
				reg1.setHoraPago(new SimpleDateFormat("HH:mm:ss").format(order.getPayDate()));
				reg1.setMontoTotalProducto(orderItem.getAmount().longValue()+"");
				reg1.setMontoTotalCompra(order.getTotalOrder().longValue()+"");
				
				loggerSendSalesNote.debug("Reg1: " + reg1);
				
				listadoReg1.add(reg1);
				
				List<String> listadoNumTarjetas = new ArrayList<String>();
				loggerSendSalesNote.info("Generar listado de tarjetas");
				if(listadoTarjetas.size() > 0){
					loggerSendSalesNote.debug(""+listadoTarjetas);
					for(MonitorCardDTO card : listadoTarjetas){
						/*Del listado se obtienen las id card que sean distinto de nulo*/
						if(card.getIdCard() != null && card.getIdCard() > 0){
							listadoNumTarjetas.add(card.getNumTarjeta());
						}
					}
				}
				
				loggerSendSalesNote.info("Generar registro del tipo 2");
				while(listadoNumTarjetas.size() > 0){
					List<String> tarjetasReg2 = listadoNumTarjetas.subList(0, listadoNumTarjetas.size() > maximoTarjetasPorLinea? maximoTarjetasPorLinea:listadoNumTarjetas.size());
					Reg2DTO reg2 = new Reg2DTO();
					reg2.setTipoReg(Constantes.JDE_TYPE_2);
					reg2.setIdVenta(popIdVenta+"");
					reg2.setIdCompra(order.getIdOrder()+"");
//					reg2.setTarjetas(StringUtils.join(tarjetasReg2.toArray(), ';'));
					
					loggerSendSalesNote.debug("Reg2: " + reg2);
				//	loggerSendSalesNote.debug("orderCvs:" + orderCvs);
					
					listadoReg2.add(reg2);
					tarjetasReg2.clear();
				}
				
			}
		}
		
		
//		try {
			
//			escribirArchivoCSVOrder(listadoReg1,listadoReg2, nombreArchivo, agregar);
//			logger.info("Archivo escrito correctamente: " + nombreArchivo);
//		} catch (IOException e) {
//			loggerSendSalesNote.error("Ha ocurrido un error al generar las l�neas CSV " + e.getMessage());
//			throw new ProcessJDEExceptions("Ha ocurrido un error al generar las l�neas CSV " + e.getMessage());
//		}
		
		
		// Actualizar estado del mensaje si se grab� exitosamente
		messageDTO.setStatus(SafConstants.SAF_STATUS_SENDED);
		messageDTO.setDateSend(LocalDateTime.now());
		try {
			safService.updateMessage(messageDTO);
		} catch (SafException e) {
			loggerSendSalesNote.error("Ha ocurrido un error al grabar la SAF " + e.getMessage());
			throw new ProcessJDEExceptions("Ha ocurrido un error al grabar la SAF " + e.getMessage());
		}
		loggerSendSalesNote.info("Actualiza mensaje a Enviado.");
		
	}


	private int popIdVenta() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public void popOC(MessageDTO messageDTO, String nombreArchivo, boolean agregar) throws ProcessJDEException{
		// TODO Auto-generated method stub
		
	}


	@Override
	public void sendAlertaProblemaStockDepto() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void sendAlertaProblemaStockTarjeta() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void sendAlertaProblemaStockCliente() {
		// TODO Auto-generated method stub
		
	}
	

	
	

}
