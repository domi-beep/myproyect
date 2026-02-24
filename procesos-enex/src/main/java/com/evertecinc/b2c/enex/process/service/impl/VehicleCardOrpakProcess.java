package com.evertecinc.b2c.enex.process.service.impl;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import com.evertecinc.b2c.constants.AuditoriaConstans;
import com.evertecinc.b2c.enex.audits.model.dto.AuditDTO;
import com.evertecinc.b2c.enex.audits.service.AuditsService;
import com.evertecinc.b2c.enex.process.exceptions.ProcessException;
import com.evertecinc.b2c.enex.process.orpak.exceptions.ProcessOrpakException;
import com.evertecinc.b2c.enex.process.orpak.service.IProcessOrpakService;
import com.evertecinc.b2c.enex.process.service.IProcess;
import com.evertecinc.b2c.enex.saf.constants.SafConstants;
import com.evertecinc.b2c.enex.saf.model.dto.MessageDTO;
import com.evertecinc.b2c.enex.saf.model.dto.MessageHistoryDTO;
import com.evertecinc.b2c.enex.saf.model.dto.SafCriterioDTO;
import com.evertecinc.b2c.enex.saf.service.SafService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service("VehicleCardOrpakProcess")
@ConfigurationProperties(prefix = "process.jde.send")
@RequiredArgsConstructor
@Slf4j
public class VehicleCardOrpakProcess implements IProcess {

	@Autowired
	IProcessOrpakService procesoOrpak;
	
	@Autowired
	SafService safService;
	
	@Autowired
	AuditsService auditsService;
	
	
	@Value("${process.jde.send.filepath}")
    private String rutaArchivo;

	@Override
	public void run() throws ProcessException {
		// Para medir tiempos de procesamiento
	    LocalDateTime startTime = LocalDateTime.now();
	    LocalDateTime endTime = null;
	    int successCount = 0;

	    log.info("-----------------------------------------------");
	    log.info("Inicio proceso VehicleCardOrpakProcess");

	    try {
	    	
            AuditDTO auditoria = new AuditDTO();
			auditoria.setInsertLogin(AuditoriaConstans.USUARIO_ORPAK);
			auditoria.setInsertName(AuditoriaConstans.USUARIO_ORPAK);
			auditoria.setAction(AuditoriaConstans.ACTION_ORPAK_VEHICLE_CARD);
			auditoria.setIdElemento(0L);
			auditoria.setTipoElemento(AuditoriaConstans.TIPO_ELEMENTO_VEHICULO);
			auditoria.setDescription("Inicio proceso VehicleCardOrpakProcess (CCN,UCN,RCN)");
			auditoria.setSystem(AuditoriaConstans.USUARIO_PROCESO);

			try {
				this.auditsService.addAudit(auditoria);
			} catch (Exception au) {
				log.warn("Problema al guardar auditoria ", au);
			}

	        log.info("Recuperar registros pendientes");
	        SafCriterioDTO safCriterio1 = new SafCriterioDTO();
	        safCriterio1.setType(SafConstants.SAF_CREATE_CARD_NUMBER);
	        safCriterio1.setStatus(SafConstants.SAF_STATUS_PENDING);
			List<MessageDTO> mensajesPendientesCreate = safService.getSafDTO(safCriterio1 );
			
			SafCriterioDTO safCriterio2 = new SafCriterioDTO();
	        safCriterio2.setType(SafConstants.SAF_UPDATE_CARD_NUMBER);
	        safCriterio2.setStatus(SafConstants.SAF_STATUS_PENDING);
			List<MessageDTO> mensajesPendientesUpdate = safService.getSafDTO(safCriterio2 );
			
			SafCriterioDTO safCriterio3 = new SafCriterioDTO();
	        safCriterio3.setType(SafConstants.SAF_RENAME_CARD_NUMBER);
	        safCriterio3.setStatus(SafConstants.SAF_STATUS_PENDING);
			List<MessageDTO> mensajesPendientesRename = safService.getSafDTO(safCriterio3 );

	        if (mensajesPendientesCreate != null && !mensajesPendientesCreate.isEmpty()) {
	            log.info("Total registros pendientes a procesar: {}", mensajesPendientesCreate.size());
	            
	            AuditDTO auditoriaDos = new AuditDTO();
	            auditoriaDos.setInsertLogin(AuditoriaConstans.USUARIO_ORPAK);
				auditoriaDos.setInsertName(AuditoriaConstans.USUARIO_ORPAK);
				auditoriaDos.setAction(AuditoriaConstans.ACTION_ORPAK_VEHICLE_CARD);
				auditoriaDos.setIdElemento(0L);
				auditoriaDos.setTipoElemento(AuditoriaConstans.TIPO_ELEMENTO_VEHICULO);
				auditoriaDos.setDescription("Total registros pendientes a procesar (CCN): " + mensajesPendientesCreate.size());
				auditoriaDos.setSystem(AuditoriaConstans.USUARIO_PROCESO);

				try {
					this.auditsService.addAudit(auditoriaDos);
				} catch (Exception au) {
					log.warn("Problema al guardar auditoria ", au);
				}

	            for (MessageDTO message : mensajesPendientesCreate) {
	                log.info("Procesando mensaje {}", message.getIdQueue());
	                log.debug("Mensaje: {}", message);

	                try {
	                    procesoOrpak.addPendingVehiceCardChange(message);
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
	            endTime = LocalDateTime.now();
	            AuditDTO auditoriaSeis = new AuditDTO();
	            auditoriaSeis.setInsertLogin(AuditoriaConstans.USUARIO_ORPAK);
	            auditoriaSeis.setInsertName(AuditoriaConstans.USUARIO_ORPAK);
				auditoriaSeis.setAction(AuditoriaConstans.ACTION_ORPAK_VEHICLE_CARD);
				auditoriaSeis.setIdElemento(0L);
				auditoriaSeis.setTipoElemento(AuditoriaConstans.TIPO_ELEMENTO_VEHICULO);
				auditoriaSeis.setDescription(String.format(
		                "Fin proceso CCN, Total registros enviados: %d, tiempo de ejecución: %d ms",
		                successCount, Duration.between(startTime, endTime).toMillis()));
				auditoriaSeis.setSystem(AuditoriaConstans.USUARIO_PROCESO);

				try {
					this.auditsService.addAudit(auditoriaSeis);
				} catch (Exception au) {
					log.warn("Problema al guardar auditoria ", au);
				}
	            
	            
	        } else {
	            log.info("No hay registros pendientes");
	            
	            AuditDTO auditoriaDos = new AuditDTO();
	            auditoriaDos.setInsertLogin(AuditoriaConstans.USUARIO_ORPAK);
				auditoriaDos.setInsertName(AuditoriaConstans.USUARIO_ORPAK);
				auditoriaDos.setAction(AuditoriaConstans.ACTION_ORPAK_VEHICLE_CARD);
				auditoriaDos.setIdElemento(0L);
				auditoriaDos.setTipoElemento(AuditoriaConstans.TIPO_ELEMENTO_VEHICULO);
				auditoriaDos.setDescription("No hay registros pendientes (CCN)");
				auditoriaDos.setSystem(AuditoriaConstans.USUARIO_PROCESO);

				try {
					this.auditsService.addAudit(auditoriaDos);
				} catch (Exception au) {
					log.warn("Problema al guardar auditoria ", au);
				}
	        }
	        
	        if (mensajesPendientesUpdate != null && !mensajesPendientesUpdate.isEmpty()) {
	        	successCount = 0;
	            log.info("Total registros pendientes a procesar: {}", mensajesPendientesUpdate.size());
	            
	            AuditDTO auditoriaTres = new AuditDTO();
	            auditoriaTres.setInsertLogin(AuditoriaConstans.USUARIO_ORPAK);
	            auditoriaTres.setInsertName(AuditoriaConstans.USUARIO_ORPAK);
				auditoriaTres.setAction(AuditoriaConstans.ACTION_ORPAK_VEHICLE_CARD);
				auditoriaTres.setIdElemento(0L);
				auditoriaTres.setTipoElemento(AuditoriaConstans.TIPO_ELEMENTO_VEHICULO);
				auditoriaTres.setDescription("Total registros pendientes a procesar (UCN): " + mensajesPendientesUpdate.size());
				auditoriaTres.setSystem(AuditoriaConstans.USUARIO_PROCESO);

				try {
					this.auditsService.addAudit(auditoriaTres);
				} catch (Exception au) {
					log.warn("Problema al guardar auditoria ", au);
				}

	            for (MessageDTO message : mensajesPendientesUpdate) {
	                log.info("Procesando mensaje {}", message.getIdQueue());
	                log.debug("Mensaje: {}", message);

	                try {
	                    procesoOrpak.updPendingVehiceCardChange(message);
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
	            
	            endTime = LocalDateTime.now();
	            AuditDTO auditoriaSiete = new AuditDTO();
	            auditoriaSiete.setInsertLogin(AuditoriaConstans.USUARIO_ORPAK);
	            auditoriaSiete.setInsertName(AuditoriaConstans.USUARIO_ORPAK);
	            auditoriaSiete.setAction(AuditoriaConstans.ACTION_ORPAK_VEHICLE_CARD);
				auditoriaSiete.setIdElemento(0L);
				auditoriaSiete.setTipoElemento(AuditoriaConstans.TIPO_ELEMENTO_VEHICULO);
				auditoriaSiete.setDescription(String.format(
		                "Fin proceso UCN, Total registros enviados: %d, tiempo de ejecución: %d ms",
		                successCount, Duration.between(startTime, endTime).toMillis()));
				auditoriaSiete.setSystem(AuditoriaConstans.USUARIO_PROCESO);

				try {
					this.auditsService.addAudit(auditoriaSiete);
				} catch (Exception au) {
					log.warn("Problema al guardar auditoria ", au);
				}
	            
	            
	        } else {
	            log.info("No hay registros pendientes");
	            
	            AuditDTO auditoriaTres = new AuditDTO();
	            auditoriaTres.setInsertLogin(AuditoriaConstans.USUARIO_ORPAK);
	            auditoriaTres.setInsertName(AuditoriaConstans.USUARIO_ORPAK);
				auditoriaTres.setAction(AuditoriaConstans.ACTION_ORPAK_VEHICLE_CARD);
				auditoriaTres.setIdElemento(0L);
				auditoriaTres.setTipoElemento(AuditoriaConstans.TIPO_ELEMENTO_VEHICULO);
				auditoriaTres.setDescription("No hay registros pendientes (UCN)");
				auditoriaTres.setSystem(AuditoriaConstans.USUARIO_PROCESO);

				try {
					this.auditsService.addAudit(auditoriaTres);
				} catch (Exception au) {
					log.warn("Problema al guardar auditoria ", au);
				}
	        }
	        
	        if (mensajesPendientesRename != null && !mensajesPendientesRename.isEmpty()) {
	        	successCount = 0;
	            log.info("Total registros pendientes a procesar: {}", mensajesPendientesRename.size());
	            
	            AuditDTO auditoriaCuatro = new AuditDTO();
	            auditoriaCuatro.setInsertLogin(AuditoriaConstans.USUARIO_ORPAK);
	            auditoriaCuatro.setInsertName(AuditoriaConstans.USUARIO_ORPAK);
	            auditoriaCuatro.setAction(AuditoriaConstans.ACTION_ORPAK_VEHICLE_CARD);
				auditoriaCuatro.setIdElemento(0L);
				auditoriaCuatro.setTipoElemento(AuditoriaConstans.TIPO_ELEMENTO_VEHICULO);
				auditoriaCuatro.setDescription("Total registros pendientes a procesar (RCN): " + mensajesPendientesRename.size());
				auditoriaCuatro.setSystem(AuditoriaConstans.USUARIO_PROCESO);

				try {
					this.auditsService.addAudit(auditoriaCuatro);
				} catch (Exception au) {
					log.warn("Problema al guardar auditoria ", au);
				}

	            for (MessageDTO message : mensajesPendientesRename) {
	                log.info("Procesando mensaje {}", message.getIdQueue());
	                log.debug("Mensaje: {}", message);

	                try {
	                    procesoOrpak.renamePendingVehicleCardChange(message);
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
	            
	            
	            endTime = LocalDateTime.now();
	            AuditDTO auditoriaOcho = new AuditDTO();
	            auditoriaOcho.setInsertLogin(AuditoriaConstans.USUARIO_ORPAK);
	            auditoriaOcho.setInsertName(AuditoriaConstans.USUARIO_ORPAK);
	            auditoriaOcho.setAction(AuditoriaConstans.ACTION_ORPAK_VEHICLE_CARD);
	            auditoriaOcho.setIdElemento(0L);
	            auditoriaOcho.setTipoElemento(AuditoriaConstans.TIPO_ELEMENTO_VEHICULO);
				auditoriaOcho.setDescription(String.format(
		                "Fin proceso RCN, Total registros enviados: %d, tiempo de ejecución: %d ms",
		                successCount, Duration.between(startTime, endTime).toMillis()));
				auditoriaOcho.setSystem(AuditoriaConstans.USUARIO_PROCESO);

				try {
					this.auditsService.addAudit(auditoriaOcho);
				} catch (Exception au) {
					log.warn("Problema al guardar auditoria ", au);
				}
	            
	        } else {
	            log.info("No hay registros pendientes");
	            
	            AuditDTO auditoriaCuatro = new AuditDTO();
	            auditoriaCuatro.setInsertLogin(AuditoriaConstans.USUARIO_ORPAK);
	            auditoriaCuatro.setInsertName(AuditoriaConstans.USUARIO_ORPAK);
	            auditoriaCuatro.setAction(AuditoriaConstans.ACTION_ORPAK_VEHICLE_CARD);
				auditoriaCuatro.setIdElemento(0L);
				auditoriaCuatro.setTipoElemento(AuditoriaConstans.TIPO_ELEMENTO_VEHICULO);
				auditoriaCuatro.setDescription("No hay registros pendientes (RCN)");
				auditoriaCuatro.setSystem(AuditoriaConstans.USUARIO_PROCESO);

				try {
					this.auditsService.addAudit(auditoriaCuatro);
				} catch (Exception au) {
					log.warn("Problema al guardar auditoria ", au);
				}
	        }

	        endTime = LocalDateTime.now();
	        
            AuditDTO auditoriaCinco = new AuditDTO();
            auditoriaCinco.setInsertLogin(AuditoriaConstans.USUARIO_ORPAK);
			auditoriaCinco.setInsertName(AuditoriaConstans.USUARIO_ORPAK);
			auditoriaCinco.setAction(AuditoriaConstans.ACTION_ORPAK_VEHICLE_CARD);
			auditoriaCinco.setIdElemento(0L);
			auditoriaCinco.setTipoElemento(AuditoriaConstans.TIPO_ELEMENTO_VEHICULO);
			auditoriaCinco.setDescription(String.format(
	                "Fin proceso (CCN,UCN,RCN), Tiempo de ejecución: %d ms", Duration.between(startTime, endTime).toMillis()));
			auditoriaCinco.setSystem(AuditoriaConstans.USUARIO_PROCESO);

			try {
				this.auditsService.addAudit(auditoriaCinco);
			} catch (Exception au) {
				log.warn("Problema al guardar auditoria ", au);
			}

	    } catch (Exception e) {
	        log.error("Error inesperado en el proceso: {}", e.getMessage(), e);
            AuditDTO auditoriaCinco = new AuditDTO();
            auditoriaCinco.setInsertLogin(AuditoriaConstans.USUARIO_ORPAK);
			auditoriaCinco.setInsertName(AuditoriaConstans.USUARIO_ORPAK);
			auditoriaCinco.setAction(AuditoriaConstans.ACTION_ORPAK_VEHICLE_CARD);
			auditoriaCinco.setIdElemento(0L);
			auditoriaCinco.setTipoElemento(AuditoriaConstans.TIPO_ELEMENTO_VEHICULO);
			auditoriaCinco.setDescription(e.getMessage());
			auditoriaCinco.setSystem(AuditoriaConstans.USUARIO_PROCESO);

			try {
				this.auditsService.addAudit(auditoriaCinco);
			} catch (Exception au) {
				log.warn("Problema al guardar auditoria ", au);
			}
	    }

	    endTime = LocalDateTime.now();
	    log.info("Total registros enviados: {} Tiempo total de ejecución: {} ms",
	            successCount, Duration.between(startTime, endTime).toMillis());

	}

}
