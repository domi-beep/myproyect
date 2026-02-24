package com.evertecinc.b2c.enex.process.service;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.evertecinc.b2c.constants.AuditoriaConstans;
import com.evertecinc.b2c.enex.audits.model.dto.AuditDTO;
import com.evertecinc.b2c.enex.audits.service.AuditsService;
import com.evertecinc.b2c.enex.client.model.dto.ClientDTO;
import com.evertecinc.b2c.enex.client.model.dto.DepartmentCriterioDTO;
import com.evertecinc.b2c.enex.client.model.dto.DepartmentDTO;
import com.evertecinc.b2c.enex.process.exceptions.ProcessJdeException;
import com.evertecinc.b2c.enex.process.jde.dto.DetalleCuentas;
import com.evertecinc.b2c.enex.process.jde.service.IProcessJdeService;
import com.evertecinc.b2c.enex.process.orpak.exceptions.ProcessOrpakException;
import com.evertecinc.b2c.enex.process.orpak.service.IProcessOrpakService;
import com.evertecinc.b2c.enex.saf.constants.SafConstants;
import com.evertecinc.b2c.enex.saf.model.dto.MessageDTO;
import com.evertecinc.b2c.enex.saf.model.dto.MessageHistoryDTO;
import com.evertecinc.b2c.enex.saf.model.dto.SafCriterioDTO;
import com.evertecinc.b2c.enex.saf.service.SafService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ProcessServiceImpl implements IProcessService {
	
	//logs
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
	 private static final Logger loggerProcessLoadQuantityCard          	= LoggerFactory.getLogger("LoadQuantityCardProcessImpl");
	 private static final Logger loggerProcessVehicleCard                	= LoggerFactory.getLogger("VehicleCardOrpakProcessImpl");
	
	
	@Autowired
	IProcessOrpakService procesoOrpak;
	
	@Autowired
	IProcessJdeService procesoJde;
	
	@Autowired
	SafService safService;
	
	@Autowired
	AuditsService auditsService;
	
	
	@Value("${process.jde.send.filepath}")
    private String rutaArchivo;
	
	
	
	/**
	 * 
	 */
	@Override
	public void ChangeCardStatusOrpakProcess() throws ProcessJdeException {
		
		// Para medir tiempos de procesamiento
	    LocalDateTime startTime = LocalDateTime.now();
	    LocalDateTime endTime = null;
	    int successCount = 0;

	    log.info("-----------------------------------------------");
	    log.info("Inicio proceso Change Card Status");

	    try {

            AuditDTO auditoria = new AuditDTO();
			auditoria.setInsertLogin(AuditoriaConstans.USUARIO_SISTEMA);
			auditoria.setInsertName(AuditoriaConstans.USUARIO_SISTEMA);
			auditoria.setAction(AuditoriaConstans.ACTION_ORPAK_CHANGE_CARD_STATUS);
			auditoria.setIdElemento(0L);
			auditoria.setTipoElemento(AuditoriaConstans.TIPO_ELEMENTO_TARJETA);
			auditoria.setDescription("Inicio proceso envío de cambios de estados de tarjetas a Orpak");
			auditoria.setSystem(AuditoriaConstans.USUARIO_PROCESO);

			try {
				this.auditsService.addAudit(auditoria);
			} catch (Exception au) {
				log.warn("Problema al guardar auditoria ", au);
			}

	        log.info("Recuperar registros pendientes");
	        SafCriterioDTO safCriterio = new SafCriterioDTO();
	        safCriterio.setType(SafConstants.SAF_UPDATE_CARD_STATUS);
	        safCriterio.setStatus(SafConstants.SAF_STATUS_PENDING);
			List<MessageDTO> pendingMessages = safService.getSafDTO(safCriterio );

	        if (pendingMessages != null && !pendingMessages.isEmpty()) {
	            log.info("Total registros pendientes a procesar: {}", pendingMessages.size());
	            AuditDTO auditoriaDos = new AuditDTO();
	            auditoriaDos.setInsertLogin(AuditoriaConstans.USUARIO_SISTEMA);
	            auditoriaDos.setInsertName(AuditoriaConstans.USUARIO_SISTEMA);
	            auditoriaDos.setAction(AuditoriaConstans.ACTION_ORPAK_CHANGE_CARD_STATUS);
	            auditoriaDos.setIdElemento(0L);
	            auditoriaDos.setTipoElemento(AuditoriaConstans.TIPO_ELEMENTO_TARJETA);
	            auditoriaDos.setDescription("Total registros pendientes a procesar: " + pendingMessages.size());
	            auditoriaDos.setSystem(AuditoriaConstans.USUARIO_PROCESO);

				try {
					this.auditsService.addAudit(auditoriaDos);
				} catch (Exception au) {
					log.warn("Problema al guardar auditoria ", au);
				}

	            for (MessageDTO message : pendingMessages) {
	                log.info("Procesando mensaje {}", message.getIdQueue());
	                log.debug("Mensaje: {}", message);

	                try {
	                    procesoOrpak.updPendingCardStatusChanges(message);
	                    successCount++;
	                } catch (ProcessOrpakException ex) {
	                    MessageHistoryDTO history = new MessageHistoryDTO();
	                    history.setDateIns(LocalDateTime.now());
	                    history.setIdQueue(message.getIdQueue());
	                    history.setReturnCode(ex.getReturnCode());
	                    history.setUri(ex.getUri());
	                    history.setMessage(ex.getMessage());
	                    safService.pushMessageHistory(history);

	                    log.error("Error al procesar el mensaje {}: {}", message.getIdQueue(), ex.getMessage());
	                } catch (Exception ex) {
	                    MessageHistoryDTO history = new MessageHistoryDTO();
	                    history.setDateIns(LocalDateTime.now());
	                    history.setIdQueue(message.getIdQueue());
	                    history.setReturnCode("");
	                    history.setUri("");
	                    history.setMessage(ex.getMessage());
	                    safService.pushMessageHistory(history);

	                    log.error("Error inesperado al procesar el mensaje {}: {}", message.getIdQueue(), ex.getMessage());
	                }

	                log.info("Aumentar número de reintentos para el mensaje {}", message.getIdQueue());
	                safService.updRetriesById(message.getIdQueue());

	                endTime = LocalDateTime.now();
	                log.info("Tiempo de ejecución parcial: {} ms", Duration.between(startTime, endTime).toMillis());
	            }
	        } else {
	            log.info("No hay registros pendientes");
	            AuditDTO auditoriaDos = new AuditDTO();
	            auditoriaDos.setInsertLogin(AuditoriaConstans.USUARIO_SISTEMA);
	            auditoriaDos.setInsertName(AuditoriaConstans.USUARIO_SISTEMA);
	            auditoriaDos.setAction(AuditoriaConstans.ACTION_ORPAK_CHANGE_CARD_STATUS);
	            auditoriaDos.setIdElemento(0L);
	            auditoriaDos.setTipoElemento(AuditoriaConstans.TIPO_ELEMENTO_TARJETA);
	            auditoriaDos.setDescription("No hay registros pendientes");
	            auditoriaDos.setSystem(AuditoriaConstans.USUARIO_PROCESO);

				try {
					this.auditsService.addAudit(auditoriaDos);
				} catch (Exception au) {
					log.warn("Problema al guardar auditoria ", au);
				}
	        }

	        endTime = LocalDateTime.now();
	        
            log.info("No hay registros pendientes");
            AuditDTO auditoriaDos = new AuditDTO();
            auditoriaDos.setInsertLogin(AuditoriaConstans.USUARIO_SISTEMA);
            auditoriaDos.setInsertName(AuditoriaConstans.USUARIO_SISTEMA);
            auditoriaDos.setAction(AuditoriaConstans.ACTION_ORPAK_CHANGE_CARD_STATUS);
            auditoriaDos.setIdElemento(0L);
            auditoriaDos.setTipoElemento(AuditoriaConstans.TIPO_ELEMENTO_TARJETA);
            auditoriaDos.setDescription(String.format("Fin proceso envío de cambios de estados de tarjetas a Orpak, Total registros enviados: %d, tiempo de ejecución: %d ms",successCount, Duration.between(startTime, endTime).toMillis()));
            auditoriaDos.setSystem(AuditoriaConstans.USUARIO_PROCESO);

			try {
				this.auditsService.addAudit(auditoriaDos);
			} catch (Exception au) {
				log.warn("Problema al guardar auditoria ", au);
			}

	    } catch (Exception e) {
	        log.error("Error inesperado en el proceso: {}", e.getMessage(), e);
	    }

	    endTime = LocalDateTime.now();
	    log.info("Total registros enviados: {} Tiempo total de ejecución: {} ms",
	            successCount, Duration.between(startTime, endTime).toMillis());
	}

	@Override
	public void ChangeClientOrpakProcess() throws ProcessJdeException {
	    // TODO Auto-generated method stub
	    log.info("ChangeClientOrpakProcess no implementado aun!!");
	}

	@Override
	public void ChangeDepartmentCardStatusProcess() throws ProcessJdeException {
	    // TODO Auto-generated method stub
	    log.info("ChangeDepartmentCardStatusProcess no implementado aun!!");
	}

	@Override
	public void ChangeDepartmentStatusCondProcess() throws ProcessJdeException {
	    // TODO Auto-generated method stub
	    log.info("ChangeDepartmentStatusCondProcess no implementado aun!!");
	}

	@Override
	public void ChangeDepartmentStatusOrpakProcess() throws ProcessJdeException {
	    // TODO Auto-generated method stub
	    log.info("ChangeDepartmentStatusOrpakProcess no implementado aun!!");
	}

	@Override
	public void ChangeTypeBalance1LitrosProcess() throws ProcessJdeException {
	    // TODO Auto-generated method stub
	    log.info("ChangeTypeBalance1LitrosProcess no implementado aun!!");
	}

	@Override
	public void ChangeTypeBalance1Process() throws ProcessJdeException {
	    // TODO Auto-generated method stub
	    log.info("ChangeTypeBalance1Process no implementado aun!!");
	}

	@Override
	public void ChangeTypeBalance2LitrosProcess() throws ProcessJdeException {
		LocalDateTime startTime = LocalDateTime.now();
	    LocalDateTime endTime = null;
	    int successCount = 0;

	    log.info("-----------------------------------------------");
	    log.info("Inicio proceso ChangeTypeBalance2Process");

	    try {
            AuditDTO auditoria = new AuditDTO();
			auditoria.setInsertLogin(AuditoriaConstans.USUARIO_SISTEMA);
			auditoria.setInsertName(AuditoriaConstans.USUARIO_SISTEMA);
			auditoria.setAction(AuditoriaConstans.ACTION_ORPAK_CHANGE_TYPE_BALANCE_2);
			auditoria.setIdElemento(0L);
			auditoria.setTipoElemento(AuditoriaConstans.TIPO_ELEMENTO_DEPARTAMENTO);
			auditoria.setDescription("Inicio proceso ChangeTypeBalance2Process (UTB2)");
			auditoria.setSystem(AuditoriaConstans.USUARIO_PROCESO);

			try {
				this.auditsService.addAudit(auditoria);
			} catch (Exception au) {
				log.warn("Problema al guardar auditoria ", au);
			}

	        log.info("Recuperar registros pendientes");
	        SafCriterioDTO safCriterio = new SafCriterioDTO();
	        //safCriterio.setType(SafConstants.SAF_UPDATE_CARD_STATUS);
	        safCriterio.setType(SafConstants.SAF_CONDITIONAL_2);
	        safCriterio.setStatus(SafConstants.SAF_STATUS_PENDING);
			List<MessageDTO> pendingMessages = safService.getSafDTO(safCriterio );

	        if (pendingMessages != null && !pendingMessages.isEmpty()) {
	            log.info("Total registros pendientes a procesar: {}", pendingMessages.size());
	            
	            AuditDTO auditoriaDos = new AuditDTO();
	            auditoriaDos.setInsertLogin(AuditoriaConstans.USUARIO_SISTEMA);
	            auditoriaDos.setInsertName(AuditoriaConstans.USUARIO_SISTEMA);
	            auditoriaDos.setAction(AuditoriaConstans.ACTION_ORPAK_CHANGE_TYPE_BALANCE_2);
	            auditoriaDos.setIdElemento(0L);
	            auditoriaDos.setTipoElemento(AuditoriaConstans.TIPO_ELEMENTO_DEPARTAMENTO);
	            auditoriaDos.setDescription("Total registros pendientes a procesar: " + pendingMessages.size());
	            auditoriaDos.setSystem(AuditoriaConstans.USUARIO_PROCESO);

				try {
					this.auditsService.addAudit(auditoriaDos);
				} catch (Exception au) {
					log.warn("Problema al guardar auditoria ", au);
				}

	            for (MessageDTO message : pendingMessages) {
	                log.info("Procesando mensaje {}", message.getIdQueue());
	                log.debug("Mensaje: {}", message);

	                try {
	                    procesoOrpak.updTypeBalance2(message);
	                    successCount++;
	                } catch (ProcessOrpakException ex) {
	                    MessageHistoryDTO history = new MessageHistoryDTO();
	                    history.setDateIns(LocalDateTime.now());
	                    history.setIdQueue(message.getIdQueue());
	                    history.setReturnCode(ex.getReturnCode());
	                    history.setUri(ex.getUri());
	                    history.setMessage(ex.getMessage());
	                    safService.pushMessageHistory(history);

	                    log.error("Error al procesar el mensaje {}: {}", message.getIdQueue(), ex.getMessage());
	                } catch (Exception ex) {
	                    MessageHistoryDTO history = new MessageHistoryDTO();
	                    history.setDateIns(LocalDateTime.now());
	                    history.setIdQueue(message.getIdQueue());
	                    history.setReturnCode("");
	                    history.setUri("");
	                    history.setMessage(ex.getMessage());
	                    safService.pushMessageHistory(history);

	                    log.error("Error inesperado al procesar el mensaje {}: {}", message.getIdQueue(), ex.getMessage());
	                }

	                log.info("Aumentar número de reintentos para el mensaje {}", message.getIdQueue());
	                safService.updRetriesById(message.getIdQueue());

	                endTime = LocalDateTime.now();
	                log.info("Tiempo de ejecución parcial: {} ms", Duration.between(startTime, endTime).toMillis());
	            }
	        } else {
	            log.info("No hay registros pendientes");
	            AuditDTO auditoriaDos = new AuditDTO();
	            auditoriaDos.setInsertLogin(AuditoriaConstans.USUARIO_SISTEMA);
	            auditoriaDos.setInsertName(AuditoriaConstans.USUARIO_SISTEMA);
	            auditoriaDos.setAction(AuditoriaConstans.ACTION_ORPAK_CHANGE_TYPE_BALANCE_2);
	            auditoriaDos.setIdElemento(0L);
	            auditoriaDos.setTipoElemento(AuditoriaConstans.TIPO_ELEMENTO_DEPARTAMENTO);
	            auditoriaDos.setDescription("No hay registros pendientes");
	            auditoriaDos.setSystem(AuditoriaConstans.USUARIO_PROCESO);

				try {
					this.auditsService.addAudit(auditoriaDos);
				} catch (Exception au) {
					log.warn("Problema al guardar auditoria ", au);
				}
	        }

		    endTime = LocalDateTime.now();
		    
	        AuditDTO auditoriaCinco = new AuditDTO();
	        auditoriaCinco.setInsertLogin(AuditoriaConstans.USUARIO_SISTEMA);
	        auditoriaCinco.setInsertName(AuditoriaConstans.USUARIO_SISTEMA);
	        auditoriaCinco.setAction(AuditoriaConstans.ACTION_ORPAK_CHANGE_TYPE_BALANCE_2);
	        auditoriaCinco.setIdElemento(0L);
	        auditoriaCinco.setTipoElemento(AuditoriaConstans.TIPO_ELEMENTO_DEPARTAMENTO);
	        auditoriaCinco.setDescription(String.format("Fin proceso ChangeTypeBalance2Process, Total registros enviados: %d, tiempo de ejecución: %d ms",successCount, Duration.between(startTime, endTime).toMillis()));
	        auditoriaCinco.setSystem(AuditoriaConstans.USUARIO_PROCESO);

			try {
				this.auditsService.addAudit(auditoriaCinco);
			} catch (Exception au) {
				log.warn("Problema al guardar auditoria ", au);
			}
	        

	    } catch (Exception e) {
	        log.error("Error inesperado en el proceso: {}", e.getMessage(), e);
            AuditDTO auditoriaDos = new AuditDTO();
            auditoriaDos.setInsertLogin(AuditoriaConstans.USUARIO_SISTEMA);
            auditoriaDos.setInsertName(AuditoriaConstans.USUARIO_SISTEMA);
            auditoriaDos.setAction(AuditoriaConstans.ACTION_ORPAK_CHANGE_TYPE_BALANCE_2);
            auditoriaDos.setIdElemento(0L);
            auditoriaDos.setTipoElemento(AuditoriaConstans.TIPO_ELEMENTO_DEPARTAMENTO);
            auditoriaDos.setDescription(e.getMessage());
            auditoriaDos.setSystem(AuditoriaConstans.USUARIO_PROCESO);

			try {
				this.auditsService.addAudit(auditoriaDos);
			} catch (Exception au) {
				log.warn("Problema al guardar auditoria ", au);
			}
	    }

	    endTime = LocalDateTime.now();
	    log.info("Total registros enviados: {} Tiempo total de ejecución: {} ms",
	            successCount, Duration.between(startTime, endTime).toMillis());
	}

	@Override
	public void ChangeTypeBalance2Process() throws ProcessJdeException {
	    // TODO Auto-generated method stub
	    log.info("ChangeTypeBalance2Process no implementado aun!!");
	}

	@Override
	public void CreateUpdateOTProcess() throws ProcessJdeException {
	    // TODO Auto-generated method stub
	    log.info("CreateUpdateOTProcess no implementado aun!!");
	}

	@Override
	public void DeleteDepartmentFleetOrpakProcess() throws ProcessJdeException {
	    // TODO Auto-generated method stub
	    log.info("DeleteDepartmentFleetOrpakProcess no implementado aun!!");
	}

	@Override
	public void SendCardBalanceOrpakProcess() throws ProcessJdeException {
	    // TODO Auto-generated method stub
	    log.info("SendCardBalanceOrpakProcess no implementado aun!!");
	}

	@Override
	public void SendCardConstraintOrpakProcess() throws ProcessJdeException {
	    // TODO Auto-generated method stub
	    log.info("SendCardConstraintOrpakProcess no implementado aun!!");
	}

	@Override
	public void SendClientOrpakProcess() throws ProcessJdeException {
	    // TODO Auto-generated method stub
	    log.info("SendClientOrpakProcess no implementado aun!!");
	}

	@Override
	public void SendCustomerBalanceOrpakProcess() throws ProcessJdeException {
	    // TODO Auto-generated method stub
	    log.info("SendCustomerBalanceOrpakProcess no implementado aun!!");
	}

	@Override
	public void SendDepartmentBalanceOrpakProcess() throws ProcessJdeException {
	    // TODO Auto-generated method stub
	    log.info("SendDepartmentBalanceOrpakProcess no implementado aun!!");
	}

	@Override
	public void SendQuantityCardOrpakProcess() throws ProcessJdeException {
	    // TODO Auto-generated method stub
	    log.info("SendQuantityCardOrpakProcess no implementado aun!!");
	}

	@Override
	public void SendUpdateCardGPSOrpakProcess() throws ProcessJdeException {
	    // TODO Auto-generated method stub
	    log.info("SendUpdateCardGPSOrpakProcess no implementado aun!!");
	}

	@Override
	public void VehicleCardOrpakProcess() throws ProcessJdeException {
	    // TODO Auto-generated method stub
	    log.info("VehicleCardOrpakProcess no implementado aun!!");
	}

	@Override
	public void ChangeFactDepartmentProcess() throws ProcessJdeException {
		LocalDateTime startTime = LocalDateTime.now();
	    LocalDateTime endTime = null;
	    int successCount = 0;

	    log.info("-----------------------------------------------");
	    log.info("Inicio proceso ChangeFactDepartmentProcess");

	    try {
	    	
            AuditDTO auditoria = new AuditDTO();
			auditoria.setInsertLogin(AuditoriaConstans.USUARIO_SISTEMA);
			auditoria.setInsertName(AuditoriaConstans.USUARIO_SISTEMA);
			auditoria.setAction(AuditoriaConstans.ACTION_JDE_CREATE_FILE);
			auditoria.setIdElemento(0L);
			auditoria.setTipoElemento(AuditoriaConstans.TIPO_ELEMENTO_DEPARTAMENTO);
			auditoria.setDescription("Inicio proceso cambio facturacion departamento");
			auditoria.setSystem(AuditoriaConstans.USUARIO_PROCESO);

			try {
				this.auditsService.addAudit(auditoria);
			} catch (Exception au) {
				log.warn("Problema al guardar auditoria ", au);
			}
	    	
	    	
	    	// Se define el nombre del archivo con la siguiente estructura: CLIXDEPYYYYMMDDHHMMSS.CSV
	    	String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
	    	String nombreArchivo = rutaArchivo.concat("CLIxDEP").concat(timestamp).concat(".CSV");

	    	log.info("Recuperar clientes con facturación por departamento");

	    	Optional<List<ClientDTO>> optionalClientes = this.procesoJde.getClientJdeByFactDepartmentStatus(true);

	    	// Declaro una lista para guardar las cuentas
	    	List<DetalleCuentas> listaDatos = new ArrayList<>();

	    	// Validar si hay clientes para procesar
	    	if (optionalClientes.isPresent()) {
	    	    List<ClientDTO> clientes = optionalClientes.get();

	    	    if (!clientes.isEmpty()) {
	    	        log.info("Total clientes a procesar: {}", clientes.size());
	    	        log.debug("\n{}", clientes.stream().map(Object::toString).collect(Collectors.joining("\n")));

	    	        
	                AuditDTO auditoriaDos = new AuditDTO();
	                auditoriaDos.setInsertLogin(AuditoriaConstans.USUARIO_SISTEMA);
	                auditoriaDos.setInsertName(AuditoriaConstans.USUARIO_SISTEMA);
	                auditoriaDos.setAction(AuditoriaConstans.ACTION_JDE_CREATE_FILE);
	                auditoriaDos.setIdElemento(0L);
	                auditoriaDos.setTipoElemento(AuditoriaConstans.TIPO_ELEMENTO_DEPARTAMENTO);
	                auditoriaDos.setDescription("Total clientes a procesar: " + clientes.size());
	                auditoriaDos.setSystem(AuditoriaConstans.USUARIO_PROCESO);

	    			try {
	    				this.auditsService.addAudit(auditoriaDos);
	    			} catch (Exception au) {
	    				log.warn("Problema al guardar auditoria ", au);
	    			}

	    	        // Por cada cliente obtengo la cuenta C y la cuenta D
	    	        for (ClientDTO clienteDTO : clientes) {
	    	            DepartmentCriterioDTO criteriaDeptos = new DepartmentCriterioDTO();
	    	            criteriaDeptos.setIdClient(clienteDTO.getIdClient());

	    	            List<DepartmentDTO> listaDeptos = this.procesoJde.getListDepartmentByClient(criteriaDeptos);
	    	            for (DepartmentDTO depto : listaDeptos) {
	    	                DetalleCuentas detalle = new DetalleCuentas();
	    	                detalle.setCodDeptoOrpak(depto.getCodeOrpakClient());
	    	                detalle.setRutCliente(clienteDTO.getUpi());
	    	                detalle.setRazonSocial(clienteDTO.getLegalName());
	    	                detalle.setCuentaC(clienteDTO.getAccountJdeClient());
	    	                detalle.setCuentaD(clienteDTO.getAccountJdeInvoice());
	    	                detalle.setNombreDepto(depto.getName());

	    	                if (clienteDTO.getFactDepartmentDate() != null) {
	    	                    detalle.setFechaFact(new SimpleDateFormat("dd/MM/yyyy").format(clienteDTO.getFactDepartmentDate()));
	    	                } else {
	    	                    log.warn("Fecha de facturación no encontrada para cliente: {}", clienteDTO.getIdClient());
	    	                    detalle.setFechaFact("N/A");
	    	                }

	    	                log.debug("Detalle cuenta cliente: {} {}", clienteDTO.getIdClient(), detalle);
	    	                listaDatos.add(detalle);
	    	            }
	    	        }

	    	        log.debug("Lista de datos: {}", listaDatos);

	    	        // Definir archivo CSV
	    	        Integer contador = 0;
	    	        CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
	    	                .setDelimiter(';')
	    	                .setRecordSeparator('\n')
	    	                .build();

	    	        try (
	    	                FileWriter fileWriter = new FileWriter(nombreArchivo);
	    	                CSVPrinter csvPrinter = new CSVPrinter(fileWriter, csvFormat)
	    	        ) {
	    	            // Agregar una línea en blanco al inicio
	    	            csvPrinter.printRecord(" ");

	    	            // Recuperar listado de cuentaC y cuentaD para escribir en archivo CSV
	    	            for (DetalleCuentas cuentas : listaDatos) {
	    	                csvPrinter.printRecord(cuentas.getRegistro());
	    	                contador++;
	    	                successCount++;
	    	            }
	    	        } catch (IOException e) {
	    	            log.error("Error al generar el archivo CSV: {}", e.getMessage(), e);
	    	            throw new RuntimeException("Error al generar el archivo CSV", e);
	    	        }

	    	        endTime = LocalDateTime.now();
	    	        log.info("Total registros enviados: {}. Tiempo total de ejecución: {} ms",
	    	                contador, Duration.between(startTime, endTime).toMillis());

	    	        log.info("Se genera archivo CSV: {}", nombreArchivo);
	    	    }
	    	} else {
	    	    log.info("No hay registros pendientes");
	    	    
                AuditDTO auditoriaDos = new AuditDTO();
                auditoriaDos.setInsertLogin(AuditoriaConstans.USUARIO_SISTEMA);
                auditoriaDos.setInsertName(AuditoriaConstans.USUARIO_SISTEMA);
                auditoriaDos.setAction(AuditoriaConstans.ACTION_JDE_CREATE_FILE);
                auditoriaDos.setIdElemento(0L);
                auditoriaDos.setTipoElemento(AuditoriaConstans.TIPO_ELEMENTO_DEPARTAMENTO);
                auditoriaDos.setDescription("No hay registros pendientes");
                auditoriaDos.setSystem(AuditoriaConstans.USUARIO_PROCESO);
                
    			try {
    				this.auditsService.addAudit(auditoriaDos);
    			} catch (Exception au) {
    				log.warn("Problema al guardar auditoria ", au);
    			}
	    	}
	    	
	    	//*********
	        endTime = LocalDateTime.now();


	    } catch (Exception e) {
	        log.error("Error inesperado en el proceso: {}", e.getMessage(), e);
	    }

	    endTime = LocalDateTime.now();
	    log.info("Total registros enviados: {} Tiempo total de ejecución: {} ms",
	            successCount, Duration.between(startTime, endTime).toMillis());
	    
        AuditDTO auditoriaTres = new AuditDTO();
        auditoriaTres.setInsertLogin(AuditoriaConstans.USUARIO_SISTEMA);
        auditoriaTres.setInsertName(AuditoriaConstans.USUARIO_SISTEMA);
        auditoriaTres.setAction(AuditoriaConstans.ACTION_JDE_CREATE_FILE);
        auditoriaTres.setIdElemento(0L);
        auditoriaTres.setTipoElemento(AuditoriaConstans.TIPO_ELEMENTO_DEPARTAMENTO);
        auditoriaTres.setDescription(String.format("Fin proceso cambio facturacion departamento, Total registros enviados: %d, tiempo de ejecución: %d ms",successCount, Duration.between(startTime, endTime).toMillis()));
        auditoriaTres.setSystem(AuditoriaConstans.USUARIO_PROCESO);
        
		try {
			this.auditsService.addAudit(auditoriaTres);
		} catch (Exception au) {
			log.warn("Problema al guardar auditoria ", au);
		}
	}

	@Override
	public void SearchDocumentsJDEProcess() throws ProcessJdeException {
		// Para medir tiempos de procesamiento
	    LocalDateTime startTime = LocalDateTime.now();
	    LocalDateTime endTime = null;
	    int successCount = 0;

	    log.info("-----------------------------------------------");
	    log.info("Inicio proceso Change Card Status");

	    try {
	    	
            AuditDTO auditoria = new AuditDTO();
			auditoria.setInsertLogin(AuditoriaConstans.USUARIO_SISTEMA);
			auditoria.setInsertName(AuditoriaConstans.USUARIO_SISTEMA);
			auditoria.setAction(AuditoriaConstans.ACTION_JDE_SEARCH_DOCUMENT);
			auditoria.setIdElemento(0L);
			auditoria.setTipoElemento(AuditoriaConstans.TIPO_ELEMENTO_JDE);
			auditoria.setDescription("Inicio proceso documentos JDE");
			auditoria.setSystem(AuditoriaConstans.USUARIO_PROCESO);

			try {
				this.auditsService.addAudit(auditoria);
			} catch (Exception au) {
				log.warn("Problema al guardar auditoria ", au);
			}

	        log.info("Recuperar registros pendientes");
	        SafCriterioDTO safCriterio = new SafCriterioDTO();
	        safCriterio.setType(SafConstants.SAF_UPDATE_CARD_STATUS);
	        safCriterio.setStatus(SafConstants.SAF_STATUS_PENDING);
			List<MessageDTO> pendingMessages = safService.getSafDTO(safCriterio );

	        if (pendingMessages != null && !pendingMessages.isEmpty()) {
	            log.info("Total registros pendientes a procesar: {}", pendingMessages.size());
	            
	            AuditDTO auditoriaDos = new AuditDTO();
	            auditoriaDos.setInsertLogin(AuditoriaConstans.USUARIO_SISTEMA);
				auditoriaDos.setInsertName(AuditoriaConstans.USUARIO_SISTEMA);
				auditoriaDos.setAction(AuditoriaConstans.ACTION_JDE_SEARCH_DOCUMENT);
				auditoriaDos.setIdElemento(0L);
				auditoriaDos.setTipoElemento(AuditoriaConstans.TIPO_ELEMENTO_JDE);
				auditoriaDos.setDescription("Total registros pendientes a procesar: " + pendingMessages.size());
				auditoriaDos.setSystem(AuditoriaConstans.USUARIO_PROCESO);

				try {
					this.auditsService.addAudit(auditoriaDos);
				} catch (Exception au) {
					log.warn("Problema al guardar auditoria ", au);
				}

	            for (MessageDTO message : pendingMessages) {
	                log.info("Procesando mensaje {}", message.getIdQueue());
	                log.debug("Mensaje: {}", message);

	                try {
	                    procesoOrpak.updPendingCardStatusChanges(message);
	                    successCount++;
	                } catch (ProcessOrpakException ex) {
	                    MessageHistoryDTO history = new MessageHistoryDTO();
	                    history.setDateIns(LocalDateTime.now());
	                    history.setIdQueue(message.getIdQueue());
	                    history.setReturnCode(ex.getReturnCode());
	                    history.setUri(ex.getUri());
	                    history.setMessage(ex.getMessage());
	                    safService.pushMessageHistory(history);

	                    log.error("Error al procesar el mensaje {}: {}", message.getIdQueue(), ex.getMessage());
	                } catch (Exception ex) {
	                    MessageHistoryDTO history = new MessageHistoryDTO();
	                    history.setDateIns(LocalDateTime.now());
	                    history.setIdQueue(message.getIdQueue());
	                    history.setReturnCode("");
	                    history.setUri("");
	                    history.setMessage(ex.getMessage());
	                    safService.pushMessageHistory(history);

	                    log.error("Error inesperado al procesar el mensaje {}: {}", message.getIdQueue(), ex.getMessage());
	                }

	                log.info("Aumentar número de reintentos para el mensaje {}", message.getIdQueue());
	                safService.updRetriesById(message.getIdQueue());

	                endTime = LocalDateTime.now();
	                log.info("Tiempo de ejecución parcial: {} ms", Duration.between(startTime, endTime).toMillis());
	            }
	        } else {
	            log.info("No hay registros pendientes");
	            AuditDTO auditoriaTres = new AuditDTO();
	            auditoriaTres.setInsertLogin(AuditoriaConstans.USUARIO_SISTEMA);
				auditoriaTres.setInsertName(AuditoriaConstans.USUARIO_SISTEMA);
				auditoriaTres.setAction(AuditoriaConstans.ACTION_JDE_SEARCH_DOCUMENT);
				auditoriaTres.setIdElemento(0L);
				auditoriaTres.setTipoElemento(AuditoriaConstans.TIPO_ELEMENTO_JDE);
				auditoriaTres.setDescription("No hay registros pendientes");
				auditoriaTres.setSystem(AuditoriaConstans.USUARIO_PROCESO);

				try {
					this.auditsService.addAudit(auditoriaTres);
				} catch (Exception au) {
					log.warn("Problema al guardar auditoria ", au);
				}
	        }

	        endTime = LocalDateTime.now();
	        
            AuditDTO auditoriaCuatro = new AuditDTO();
            auditoriaCuatro.setInsertLogin(AuditoriaConstans.USUARIO_SISTEMA);
            auditoriaCuatro.setInsertName(AuditoriaConstans.USUARIO_SISTEMA);
            auditoriaCuatro.setAction(AuditoriaConstans.ACTION_JDE_SEARCH_DOCUMENT);
            auditoriaCuatro.setIdElemento(0L);
            auditoriaCuatro.setTipoElemento(AuditoriaConstans.TIPO_ELEMENTO_JDE);
            auditoriaCuatro.setDescription(String.format("Fin proceso documentos JDE, Total registros enviados: %d, tiempo de ejecución: %d ms",successCount, Duration.between(startTime, endTime).toMillis()));
            auditoriaCuatro.setSystem(AuditoriaConstans.USUARIO_PROCESO);

			try {
				this.auditsService.addAudit(auditoriaCuatro);
			} catch (Exception au) {
				log.warn("Problema al guardar auditoria ", au);
			}

	    } catch (Exception e) {
	        log.error("Error inesperado en el proceso: {}", e.getMessage(), e);
	    }

	    endTime = LocalDateTime.now();
	    log.info("Total registros enviados: {} Tiempo total de ejecución: {} ms", successCount, Duration.between(startTime, endTime).toMillis());
	}

	@Override
	public void SendNotificationsDailyProcess() throws ProcessJdeException {
	    // TODO Auto-generated method stub
	    log.info("SendNotificationsDailyProcess no implementado aun!!");
	}

	@Override
	public void SendNotificationsProcess() throws ProcessJdeException {
	    // TODO Auto-generated method stub
	    log.info("SendNotificationsProcess no implementado aun!!");
	}

	@Override
	public void SendSalesNoteToJDEProcess() throws ProcessJdeException {
	    // TODO Auto-generated method stub
	    log.info("SendSalesNoteToJDEProcess no implementado aun!!");
	}

	@Override
	public void CargaPrecioPizarraProcess() throws ProcessJdeException {
	    // TODO Auto-generated method stub
	    log.info("CargaPrecioPizarraProcess no implementado aun!!");
	}


}
