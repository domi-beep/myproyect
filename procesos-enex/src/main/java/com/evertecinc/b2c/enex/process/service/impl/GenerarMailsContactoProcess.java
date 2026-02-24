package com.evertecinc.b2c.enex.process.service.impl;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.evertecinc.b2c.constants.AuditoriaConstans;
import com.evertecinc.b2c.constants.Constantes;
import com.evertecinc.b2c.enex.audits.model.dto.AuditDTO;
import com.evertecinc.b2c.enex.audits.service.AuditsService;
import com.evertecinc.b2c.enex.client.model.dto2.EnvoltorioMailContactDTO;
import com.evertecinc.b2c.enex.email.service.EmailService;
import com.evertecinc.b2c.enex.process.exceptions.ProcessException;
import com.evertecinc.b2c.enex.process.service.IProcess;
import com.evertecinc.b2c.enex.saf.model.dto.MessageDTO;
import com.evertecinc.b2c.enex.saf.model.dto.SafCriterioDTO;
import com.evertecinc.b2c.enex.saf.service.SafService;
import com.google.gson.Gson;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Service("GenerarMailsContactoProcess")
@RequiredArgsConstructor
@Slf4j
@Data
public class GenerarMailsContactoProcess implements IProcess {
	
	private static final Logger loggerProcess = LoggerFactory.getLogger("GenerarMailsContactoProcessImpl");
	
	private final AuditsService auditsService;
	
	private final SafService safService;
	
	
	private final  EmailService emailService;

	@Override
	public void run() throws ProcessException {
		// Para medir tiempos de procesamiento
	    LocalDateTime startTime = LocalDateTime.now();
	    LocalDateTime endTime = null;
	    int successCount = 0;

	    loggerProcess.info("-----------------------------------------------");
	    loggerProcess.info("Inicio proceso GenerarMailsContactoProcess");

	    try {
            AuditDTO auditoria = new AuditDTO();
			auditoria.setInsertLogin(AuditoriaConstans.USUARIO_SISTEMA);
			auditoria.setInsertName(AuditoriaConstans.USUARIO_SISTEMA);
			auditoria.setAction(AuditoriaConstans.ACTION_CREATE_MAIL_CONTACT);
			auditoria.setIdElemento(0L);
			auditoria.setTipoElemento(AuditoriaConstans.TIPO_ELEMENTO_MAIL);
			auditoria.setDescription("Inicio proceso GenerarMailsContactoProcess");
			auditoria.setSystem(AuditoriaConstans.USUARIO_PROCESO);

			try {
				this.auditsService.addAudit(auditoria);
			} catch (Exception au) {
				log.warn("Problema al guardar auditoria ", au);
			}

	        loggerProcess.info("Recuperar registros pendientes");
	        SafCriterioDTO criterio = new SafCriterioDTO();
	        criterio.setType(Constantes.SAF_FORMULARIO_CONTACTANOS );
	        criterio.setStatus(Constantes.ORDER_STATUS_PENDING);
			List<MessageDTO> pendingMessages = safService.getSafDTO(criterio);

	        if (pendingMessages != null && !pendingMessages.isEmpty()) {
	            loggerProcess.info("Total registros pendientes a procesar: {}", pendingMessages.size());
	            
	            AuditDTO auditoriaDos = new AuditDTO();
	            auditoriaDos.setInsertLogin(AuditoriaConstans.USUARIO_SISTEMA);
				auditoriaDos.setInsertName(AuditoriaConstans.USUARIO_SISTEMA);
				auditoriaDos.setAction(AuditoriaConstans.ACTION_CREATE_MAIL_CONTACT);
				auditoriaDos.setIdElemento(0L);
				auditoriaDos.setTipoElemento(AuditoriaConstans.TIPO_ELEMENTO_MAIL);
				auditoriaDos.setDescription("Total registros pendientes a procesar: " + pendingMessages.size());
				auditoriaDos.setSystem(AuditoriaConstans.USUARIO_PROCESO);

				try {
					this.auditsService.addAudit(auditoriaDos);
				} catch (Exception au) {
					log.warn("Problema al guardar auditoria ", au);
				}

	            for (MessageDTO message : pendingMessages) {
	                loggerProcess.info("Procesando mensaje {}", message.getIdQueue());
	                loggerProcess.debug("Mensaje: {}", message);

	                try {
	                	
	                	String dataObj = message.getData();
	                	
	                	Gson gson = new Gson();
	                	EnvoltorioMailContactDTO envoltorio = gson.fromJson(dataObj, EnvoltorioMailContactDTO.class);
	                	
						emailService.sendContact(envoltorio);
						
	                    successCount++;
	                    message.setStatus(Constantes.MAILS_STATUS_SEND);
	                    safService.updateMessage(message);
	                } catch (Exception ex) {
	                    
	                	safService.updRetriesById(message.getIdQueue());

	                    loggerProcess.error("Error al procesar el mensaje {}: {}", message.getIdElement(), ex.getMessage());
	                } 

	                loggerProcess.info("Aumentar número de reintentos para el mensaje {}", message.getIdElement());

	                endTime = LocalDateTime.now();
	                loggerProcess.info("Tiempo de ejecución parcial: {} ms", Duration.between(startTime, endTime).toMillis());
	            }
	        } else {
	            loggerProcess.info("No hay registros pendientes");
	            
	            AuditDTO auditoriaDos = new AuditDTO();
	            auditoriaDos.setInsertLogin(AuditoriaConstans.USUARIO_SISTEMA);
				auditoriaDos.setInsertName(AuditoriaConstans.USUARIO_SISTEMA);
				auditoriaDos.setAction(AuditoriaConstans.ACTION_CREATE_MAIL_CONTACT);
				auditoriaDos.setIdElemento(0L);
				auditoriaDos.setTipoElemento(AuditoriaConstans.TIPO_ELEMENTO_MAIL);
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
			auditoriaCinco.setAction(AuditoriaConstans.ACTION_CREATE_MAIL_CONTACT);
			auditoriaCinco.setIdElemento(0L);
			auditoriaCinco.setTipoElemento(AuditoriaConstans.TIPO_ELEMENTO_MAIL);
			auditoriaCinco.setDescription(String.format(
	                "Fin proceso GenerarMailsContactoProcess, Total registros enviados: %d, tiempo de ejecución: %d ms",
	                successCount, Duration.between(startTime, endTime).toMillis()));
			auditoriaCinco.setSystem(AuditoriaConstans.USUARIO_PROCESO);

			try {
				this.auditsService.addAudit(auditoriaCinco);
			} catch (Exception au) {
				log.warn("Problema al guardar auditoria ", au);
			}

	    } catch (Exception e) {
	        loggerProcess.error("Error inesperado en el proceso: {}", e.getMessage(), e);
            AuditDTO auditoriaDos = new AuditDTO();
            auditoriaDos.setInsertLogin(AuditoriaConstans.USUARIO_SISTEMA);
			auditoriaDos.setInsertName(AuditoriaConstans.USUARIO_SISTEMA);
			auditoriaDos.setAction(AuditoriaConstans.ACTION_CREATE_MAIL_CONTACT);
			auditoriaDos.setIdElemento(0L);
			auditoriaDos.setTipoElemento(AuditoriaConstans.TIPO_ELEMENTO_MAIL);
			auditoriaDos.setDescription(e.getMessage());
			auditoriaDos.setSystem(AuditoriaConstans.USUARIO_PROCESO);

			try {
				this.auditsService.addAudit(auditoriaDos);
			} catch (Exception au) {
				log.warn("Problema al guardar auditoria ", au);
			}
	    }
	    endTime = LocalDateTime.now();
	    loggerProcess.info("Total registros enviados: {} Tiempo total de ejecución: {} ms",
	            successCount, Duration.between(startTime, endTime).toMillis());

	}

}
