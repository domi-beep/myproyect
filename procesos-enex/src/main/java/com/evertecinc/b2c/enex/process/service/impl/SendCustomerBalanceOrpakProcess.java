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

@Service("SendCustomerBalanceOrpakProcess")
@ConfigurationProperties(prefix = "process.jde.send")
@RequiredArgsConstructor
@Slf4j
public class SendCustomerBalanceOrpakProcess implements IProcess {

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
	    log.info("Inicio proceso SendCustomerBalanceOrpakProcess");

	    try {
	    	
            AuditDTO auditoria = new AuditDTO();
			auditoria.setInsertLogin(AuditoriaConstans.USUARIO_ORPAK);
			auditoria.setInsertName(AuditoriaConstans.USUARIO_ORPAK);
			auditoria.setAction(AuditoriaConstans.ACTION_ORPAK_SEND_CUSTOMER_BALANCE);
			auditoria.setIdElemento(0L);
			auditoria.setTipoElemento(AuditoriaConstans.TIPO_ELEMENTO_CLIENTE);
			auditoria.setDescription("Inicio proceso SendCustomerBalanceOrpakProcess (UEB)");
			auditoria.setSystem(AuditoriaConstans.USUARIO_PROCESO);

			try {
				this.auditsService.addAudit(auditoria);
			} catch (Exception au) {
				log.warn("Problema al guardar auditoria ", au);
			}

	        log.info("Recuperar registros pendientes");
	        SafCriterioDTO safCriterio = new SafCriterioDTO();
	        safCriterio.setType(SafConstants.SAF_UPDATE_CUSTOMER_BALANCE);
	        safCriterio.setStatus(SafConstants.SAF_STATUS_PENDING);
			List<MessageDTO> pendingMessages = safService.getSafDTO(safCriterio );

	        if (pendingMessages != null && !pendingMessages.isEmpty()) {
	            log.info("Total registros pendientes a procesar: {}", pendingMessages.size());
	            
	            AuditDTO auditoriaDos = new AuditDTO();
	            auditoriaDos.setInsertLogin(AuditoriaConstans.USUARIO_ORPAK);
	            auditoriaDos.setInsertName(AuditoriaConstans.USUARIO_ORPAK);
				auditoriaDos.setAction(AuditoriaConstans.ACTION_ORPAK_SEND_CUSTOMER_BALANCE);
				auditoriaDos.setIdElemento(0L);
				auditoriaDos.setTipoElemento(AuditoriaConstans.TIPO_ELEMENTO_CLIENTE);
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
	                    procesoOrpak.updPendingCustomerBalance(message);
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
				auditoriaDos.setAction(AuditoriaConstans.ACTION_ORPAK_SEND_CUSTOMER_BALANCE);
				auditoriaDos.setIdElemento(0L);
				auditoriaDos.setTipoElemento(AuditoriaConstans.TIPO_ELEMENTO_CLIENTE);
				auditoriaDos.setDescription("No hay registros pendientes");
				auditoriaDos.setSystem(AuditoriaConstans.USUARIO_PROCESO);

				try {
					this.auditsService.addAudit(auditoriaDos);
				} catch (Exception au) {
					log.warn("Problema al guardar auditoria ", au);
				}
	        }

	        endTime = LocalDateTime.now();
;
            AuditDTO auditoriaTres = new AuditDTO();
            auditoriaTres.setInsertLogin(AuditoriaConstans.USUARIO_ORPAK);
            auditoriaTres.setInsertName(AuditoriaConstans.USUARIO_ORPAK);
            auditoriaTres.setAction(AuditoriaConstans.ACTION_ORPAK_SEND_CUSTOMER_BALANCE);
            auditoriaTres.setIdElemento(0L);
            auditoriaTres.setTipoElemento(AuditoriaConstans.TIPO_ELEMENTO_CLIENTE);
            auditoriaTres.setDescription(String.format(
	                "Fin proceso SendCustomerBalanceOrpakProcess, Total registros enviados: %d, tiempo de ejecución: %d ms",successCount, Duration.between(startTime, endTime).toMillis()));
            auditoriaTres.setSystem(AuditoriaConstans.USUARIO_PROCESO);

			try {
				this.auditsService.addAudit(auditoriaTres);
			} catch (Exception au) {
				log.warn("Problema al guardar auditoria ", au);
			}

	    } catch (Exception e) {
	        log.error("Error inesperado en el proceso: {}", e.getMessage(), e);
	        
            AuditDTO auditoriaCuatro = new AuditDTO();
            auditoriaCuatro.setInsertLogin(AuditoriaConstans.USUARIO_ORPAK);
            auditoriaCuatro.setInsertName(AuditoriaConstans.USUARIO_ORPAK);
            auditoriaCuatro.setAction(AuditoriaConstans.ACTION_ORPAK_SEND_CUSTOMER_BALANCE);
            auditoriaCuatro.setIdElemento(0L);
            auditoriaCuatro.setTipoElemento(AuditoriaConstans.TIPO_ELEMENTO_CLIENTE);
            auditoriaCuatro.setDescription(e.getMessage());

			try {
				this.auditsService.addAudit(auditoriaCuatro);
			} catch (Exception au) {
				log.warn("Problema al guardar auditoria ", au);
			}
	    }

	    endTime = LocalDateTime.now();
	    log.info("Total registros enviados: {} Tiempo total de ejecución: {} ms",
	            successCount, Duration.between(startTime, endTime).toMillis());

	}

}
