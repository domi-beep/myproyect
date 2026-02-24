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

@Service("CreateUpdateOTProcess")
@ConfigurationProperties(prefix = "process.jde.send")
@RequiredArgsConstructor
@Slf4j
public class CreateUpdateOTProcess implements IProcess {

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
	    log.info("Inicio proceso CreateUpdateOTProcess");

	    try {
	    	
            AuditDTO auditoria = new AuditDTO();
			auditoria.setInsertLogin(AuditoriaConstans.USUARIO_ORPAK);
			auditoria.setInsertName(AuditoriaConstans.USUARIO_ORPAK);
			auditoria.setAction(AuditoriaConstans.ACTION_ORPAK_VEHICLE_CARD);
			auditoria.setIdElemento(0L);
			auditoria.setTipoElemento(AuditoriaConstans.TIPO_ELEMENTO_VEHICULO);
			auditoria.setDescription("Inicio proceso CreateUpdateOTProcess (CDN)");
			auditoria.setSystem(AuditoriaConstans.USUARIO_PROCESO);

			try {
				this.auditsService.addAudit(auditoria);
			} catch (Exception au) {
				log.warn("Problema al guardar auditoria ", au);
			}

	        log.info("Recuperar registros pendientes");
	        SafCriterioDTO safCriterio = new SafCriterioDTO();
	        safCriterio.setType(SafConstants.SAF_CREATE_DEVICE_NUMBER);
	        safCriterio.setStatus(SafConstants.SAF_STATUS_PENDING);
			List<MessageDTO> mensajesPendientesEmision = safService.getSafDTO(safCriterio );

	        if (mensajesPendientesEmision != null && !mensajesPendientesEmision.isEmpty()) {
	            log.info("Total registros pendientes a procesar: {}", mensajesPendientesEmision.size());
	            
	            AuditDTO auditoriaDos = new AuditDTO();
	            auditoriaDos.setInsertLogin(AuditoriaConstans.USUARIO_ORPAK);
	            auditoriaDos.setInsertName(AuditoriaConstans.USUARIO_ORPAK);
	            auditoriaDos.setAction(AuditoriaConstans.ACTION_ORPAK_VEHICLE_CARD);
	            auditoriaDos.setIdElemento(0L);
	            auditoriaDos.setTipoElemento(AuditoriaConstans.TIPO_ELEMENTO_VEHICULO);
	            auditoriaDos.setDescription("Total registros pendientes a procesar: " + mensajesPendientesEmision.size());
	            auditoriaDos.setSystem(AuditoriaConstans.USUARIO_PROCESO);

				try {
					this.auditsService.addAudit(auditoriaDos);
				} catch (Exception au) {
					log.warn("Problema al guardar auditoria ", au);
				}

	            for (MessageDTO message : mensajesPendientesEmision) {
	                log.info("Procesando mensaje {}", message.getIdQueue());
	                log.debug("Mensaje: {}", message);

	                try {
	                    procesoOrpak.updOT(message);
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
	            auditoriaDos.setInsertLogin(AuditoriaConstans.USUARIO_ORPAK);
	            auditoriaDos.setInsertName(AuditoriaConstans.USUARIO_ORPAK);
	            auditoriaDos.setAction(AuditoriaConstans.ACTION_ORPAK_VEHICLE_CARD);
	            auditoriaDos.setIdElemento(0L);
	            auditoriaDos.setTipoElemento(AuditoriaConstans.TIPO_ELEMENTO_VEHICULO);
	            auditoriaDos.setDescription("No hay registros pendientes");
	            auditoriaDos.setSystem(AuditoriaConstans.USUARIO_PROCESO);

				try {
					this.auditsService.addAudit(auditoriaDos);
				} catch (Exception au) {
					log.warn("Problema al guardar auditoria ", au);
				}
	        }
	        
	        endTime = LocalDateTime.now();
	        
            AuditDTO auditoriaTres = new AuditDTO();
            auditoriaTres.setInsertLogin(AuditoriaConstans.USUARIO_ORPAK);
            auditoriaTres.setInsertName(AuditoriaConstans.USUARIO_ORPAK);
            auditoriaTres.setAction(AuditoriaConstans.ACTION_ORPAK_VEHICLE_CARD);
            auditoriaTres.setIdElemento(0L);
            auditoriaTres.setTipoElemento(AuditoriaConstans.TIPO_ELEMENTO_VEHICULO);
            auditoriaTres.setDescription(String.format("Fin proceso CreateUpdateOTProcess (CDN), Total registros enviados: %d, tiempo de ejecución: %d ms",successCount, Duration.between(startTime, endTime).toMillis()));
            auditoriaTres.setSystem(AuditoriaConstans.USUARIO_PROCESO);

			try {
				this.auditsService.addAudit(auditoriaTres);
			} catch (Exception au) {
				log.warn("Problema al guardar auditoria ", au);
			}
	        
	        

	    } catch (Exception e) {
	        log.error("Error inesperado en el proceso: {}", e.getMessage(), e);
            AuditDTO auditoriaDos = new AuditDTO();
            auditoriaDos.setInsertLogin(AuditoriaConstans.USUARIO_ORPAK);
            auditoriaDos.setInsertName(AuditoriaConstans.USUARIO_ORPAK);
            auditoriaDos.setAction(AuditoriaConstans.ACTION_ORPAK_VEHICLE_CARD);
            auditoriaDos.setIdElemento(0L);
            auditoriaDos.setTipoElemento(AuditoriaConstans.TIPO_ELEMENTO_VEHICULO);
            auditoriaDos.setDescription(e.getMessage());
            auditoriaDos.setSystem(AuditoriaConstans.USUARIO_PROCESO);

			try {
				this.auditsService.addAudit(auditoriaDos);
			} catch (Exception au) {
				log.warn("Problema al guardar auditoria ", au);
			}
	    }   
	    
	    //--------------------------------------------------------------------------------
	    successCount = 0;
	    
	    try {
            AuditDTO auditoriaCuatro = new AuditDTO();
            auditoriaCuatro.setInsertLogin(AuditoriaConstans.USUARIO_ORPAK);
            auditoriaCuatro.setInsertName(AuditoriaConstans.USUARIO_ORPAK);
            auditoriaCuatro.setAction(AuditoriaConstans.ACTION_ORPAK_VEHICLE_CARD_2);
            auditoriaCuatro.setIdElemento(0L);
            auditoriaCuatro.setTipoElemento(AuditoriaConstans.TIPO_ELEMENTO_VEHICULO);
            auditoriaCuatro.setDescription("Inicio proceso CreateUpdateOTProcess (UDN)");
            auditoriaCuatro.setSystem(AuditoriaConstans.USUARIO_PROCESO);

			try {
				this.auditsService.addAudit(auditoriaCuatro);
			} catch (Exception au) {
				log.warn("Problema al guardar auditoria ", au);
			}
	        
	        
	        SafCriterioDTO safCriterioReemision = new SafCriterioDTO();
	        safCriterioReemision.setType(SafConstants.SAF_UPDATE_DEVICE_NUMBER);
	        safCriterioReemision.setStatus(SafConstants.SAF_STATUS_PENDING);
			List<MessageDTO> mensajesPendientesReemision = safService.getSafDTO(safCriterioReemision );

	        if (mensajesPendientesReemision != null && !mensajesPendientesReemision.isEmpty()) {
	            log.info("Total registros pendientes a procesar: {}", mensajesPendientesReemision.size());
	            
	            AuditDTO auditoriaCinco = new AuditDTO();
	            auditoriaCinco.setInsertLogin(AuditoriaConstans.USUARIO_ORPAK);
	            auditoriaCinco.setInsertName(AuditoriaConstans.USUARIO_ORPAK);
	            auditoriaCinco.setAction(AuditoriaConstans.ACTION_ORPAK_VEHICLE_CARD_2);
	            auditoriaCinco.setIdElemento(0L);
	            auditoriaCinco.setTipoElemento(AuditoriaConstans.TIPO_ELEMENTO_VEHICULO);
	            auditoriaCinco.setDescription("Total registros pendientes a procesar: " + mensajesPendientesReemision.size());
	            auditoriaCinco.setSystem(AuditoriaConstans.USUARIO_PROCESO);

				try {
					this.auditsService.addAudit(auditoriaCinco);
				} catch (Exception au) {
					log.warn("Problema al guardar auditoria ", au);
				}
	            

	            for (MessageDTO message : mensajesPendientesReemision) {
	                log.info("Procesando mensaje {}", message.getIdQueue());
	                log.debug("Mensaje: {}", message);

	                try {
	                    procesoOrpak.updOT(message);
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
	            AuditDTO auditoriaCinco = new AuditDTO();
	            auditoriaCinco.setInsertLogin(AuditoriaConstans.USUARIO_ORPAK);
	            auditoriaCinco.setInsertName(AuditoriaConstans.USUARIO_ORPAK);
	            auditoriaCinco.setAction(AuditoriaConstans.ACTION_ORPAK_VEHICLE_CARD_2);
	            auditoriaCinco.setIdElemento(0L);
	            auditoriaCinco.setTipoElemento(AuditoriaConstans.TIPO_ELEMENTO_VEHICULO);
	            auditoriaCinco.setDescription("No hay registros pendientes");
	            auditoriaCinco.setSystem(AuditoriaConstans.USUARIO_PROCESO);

				try {
					this.auditsService.addAudit(auditoriaCinco);
				} catch (Exception au) {
					log.warn("Problema al guardar auditoria ", au);
				}
	        }
	        endTime = LocalDateTime.now();
	        
            AuditDTO auditoriaCinco = new AuditDTO();
            auditoriaCinco.setInsertLogin(AuditoriaConstans.USUARIO_ORPAK);
            auditoriaCinco.setInsertName(AuditoriaConstans.USUARIO_ORPAK);
            auditoriaCinco.setAction(AuditoriaConstans.ACTION_ORPAK_VEHICLE_CARD_2);
            auditoriaCinco.setIdElemento(0L);
            auditoriaCinco.setTipoElemento(AuditoriaConstans.TIPO_ELEMENTO_VEHICULO);
            auditoriaCinco.setDescription(String.format("Fin proceso CreateUpdateOTProcess (UDN), Total registros enviados: %d, tiempo de ejecución: %d ms",successCount, Duration.between(startTime, endTime).toMillis()));
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
            auditoriaCinco.setAction(AuditoriaConstans.ACTION_ORPAK_VEHICLE_CARD_2);
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
	    log.info("Total registros enviados: {} Tiempo total de ejecución: {} ms", successCount, Duration.between(startTime, endTime).toMillis());

	}

}
