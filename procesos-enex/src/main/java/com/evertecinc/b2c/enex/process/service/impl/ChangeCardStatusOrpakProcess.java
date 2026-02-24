package com.evertecinc.b2c.enex.process.service.impl;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

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

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Service("ChangeCardStatusOrpakProcess")
@ConfigurationProperties(prefix = "process.jde.send")
@RequiredArgsConstructor
@Slf4j
@Data
public class ChangeCardStatusOrpakProcess implements IProcess {
	
//	private static final Logger loggerProcessChangeCardStatus = LoggerFactory.getLogger("ChangeCardStatusOrpakProcessImpl");
	
	private final IProcessOrpakService procesoOrpak;
	private final AuditsService auditsService;
	private final SafService safService;

	@Override
	public void run() throws ProcessException {
		// Para medir tiempos de procesamiento
	    LocalDateTime startTime = LocalDateTime.now();
	    LocalDateTime endTime = null;
	    int successCount = 0;

	    log.info("-----------------------------------------------");
	    log.info("Inicio proceso Change Card Status");

	    try {
	    	
            AuditDTO auditoria = new AuditDTO();
			auditoria.setInsertLogin(AuditoriaConstans.USUARIO_ORPAK);
			auditoria.setInsertName(AuditoriaConstans.USUARIO_ORPAK);
			auditoria.setAction(AuditoriaConstans.ACTION_ORPAK_CHANGE_CARD_STATUS);
			auditoria.setIdElemento(0L);
			auditoria.setTipoElemento(AuditoriaConstans.TIPO_ELEMENTO_TARJETA);
			auditoria.setDescription("Inicio proceso envío de cambios de estados de tarjetas a Orpak (USC)");
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
	            auditoriaDos.setInsertLogin(AuditoriaConstans.USUARIO_ORPAK);
				auditoriaDos.setInsertName(AuditoriaConstans.USUARIO_ORPAK);
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
	            log.warn("No hay registros pendientes");
	            AuditDTO auditoriaDos = new AuditDTO();
	            auditoriaDos.setInsertLogin(AuditoriaConstans.USUARIO_ORPAK);
				auditoriaDos.setInsertName(AuditoriaConstans.USUARIO_ORPAK);
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
		    
	        AuditDTO auditoriaTres = new AuditDTO();
	        auditoriaTres.setInsertLogin(AuditoriaConstans.USUARIO_ORPAK);
	        auditoriaTres.setInsertName(AuditoriaConstans.USUARIO_ORPAK);
			auditoriaTres.setAction(AuditoriaConstans.ACTION_ORPAK_CHANGE_CARD_STATUS);
			auditoriaTres.setIdElemento(0L);
			auditoriaTres.setTipoElemento(AuditoriaConstans.TIPO_ELEMENTO_TARJETA);
			auditoriaTres.setDescription(String.format("Fin proceso envío de cambios de estados de tarjetas a Orpak, Total registros enviados: %d, tiempo de ejecución: %d ms",successCount, Duration.between(startTime, endTime).toMillis()));
			auditoriaTres.setSystem(AuditoriaConstans.USUARIO_PROCESO);

			try {
				this.auditsService.addAudit(auditoriaTres);
			} catch (Exception au) {
				log.warn("Problema al guardar auditoria ", au);
			}
	        

	    } catch (Exception e) {
	        log.error("Error inesperado en el proceso: {}", e.getMessage(), e);
            AuditDTO auditoria = new AuditDTO();
			auditoria.setInsertLogin(AuditoriaConstans.USUARIO_ORPAK);
			auditoria.setInsertName(AuditoriaConstans.USUARIO_ORPAK);
			auditoria.setAction(AuditoriaConstans.ACTION_ORPAK_CHANGE_CARD_STATUS);
			auditoria.setIdElemento(0L);
			auditoria.setTipoElemento(AuditoriaConstans.TIPO_ELEMENTO_TARJETA);
			auditoria.setDescription(e.getMessage());
			auditoria.setSystem(AuditoriaConstans.USUARIO_PROCESO);

			try {
				this.auditsService.addAudit(auditoria);
			} catch (Exception au) {
				log.warn("Problema al guardar auditoria ", au);
			}
	    }
	    endTime = LocalDateTime.now();
	    log.info("Total registros enviados: {} Tiempo total de ejecución: {} ms",
	            successCount, Duration.between(startTime, endTime).toMillis());

	}

}
