package com.evertecinc.b2c.enex.process.service.impl;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import com.evertecinc.b2c.constants.AuditoriaConstans;
import com.evertecinc.b2c.constants.Constantes;
import com.evertecinc.b2c.enex.audits.model.dto.AuditDTO;
import com.evertecinc.b2c.enex.audits.service.AuditsService;
import com.evertecinc.b2c.enex.email.model.dto.MailQUeueCriterio;
import com.evertecinc.b2c.enex.email.model.dto.SendMessageDTO;
import com.evertecinc.b2c.enex.email.model.entities.MailQueue;
import com.evertecinc.b2c.enex.email.service.EmailService;
import com.evertecinc.b2c.enex.process.exceptions.ProcessException;
import com.evertecinc.b2c.enex.process.service.IProcess;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Service("EnvioMailsProcess")
@ConfigurationProperties(prefix = "process.jde.send")
@RequiredArgsConstructor
@Slf4j
@Data
public class EnvioMailsProcess implements IProcess {
	
	private static final Logger loggerProcessEnvíoMails = LoggerFactory.getLogger("EnvioMailsProcessImpl");
	
//	private final IProcessOrpakService procesoOrpak;
	private final AuditsService auditsService;
	
	
	private final  EmailService emailService;

	@Override
	public void run() throws ProcessException {
		// Para medir tiempos de procesamiento
	    LocalDateTime startTime = LocalDateTime.now();
	    LocalDateTime endTime = null;
	    int successCount = 0;

	    loggerProcessEnvíoMails.info("-----------------------------------------------");
	    loggerProcessEnvíoMails.info("Inicio proceso Change Card Status");

	    try {
	    	
            AuditDTO auditoria = new AuditDTO();
			auditoria.setInsertLogin(AuditoriaConstans.USUARIO_SISTEMA);
			auditoria.setInsertName(AuditoriaConstans.USUARIO_SISTEMA);
			auditoria.setAction(AuditoriaConstans.ACTION_SEND_MAIL);
			auditoria.setIdElemento(0L);
			auditoria.setTipoElemento(AuditoriaConstans.TIPO_ELEMENTO_MAIL);
			auditoria.setDescription("Inicio proceso EnvioMailsProcess");
			auditoria.setSystem(AuditoriaConstans.USUARIO_PROCESO);

			try {
				this.auditsService.addAudit(auditoria);
			} catch (Exception au) {
				loggerProcessEnvíoMails.warn("Problema al guardar auditoria ", au);
			}

			loggerProcessEnvíoMails.info("Recuperar registros pendientes");
	        MailQUeueCriterio criterio = new MailQUeueCriterio();
	        criterio.setAttempts(100);
	        criterio.setStatus(Constantes.MAILS_STATUS_PENDING);
			 List<MailQueue> pendingMessages = emailService.getMailQueue(criterio);

	        if (pendingMessages != null && !pendingMessages.isEmpty()) {
	            loggerProcessEnvíoMails.info("Total registros pendientes a procesar: {}", pendingMessages.size());
	            AuditDTO auditoriaDos = new AuditDTO();
	            auditoriaDos.setInsertLogin(AuditoriaConstans.USUARIO_SISTEMA);
				auditoriaDos.setInsertName(AuditoriaConstans.USUARIO_SISTEMA);
				auditoriaDos.setAction(AuditoriaConstans.ACTION_SEND_MAIL);
				auditoriaDos.setIdElemento(0L);
				auditoriaDos.setTipoElemento(AuditoriaConstans.TIPO_ELEMENTO_MAIL);
				auditoriaDos.setDescription("Total registros pendientes a procesar: " + pendingMessages.size());
				auditoriaDos.setSystem(AuditoriaConstans.USUARIO_PROCESO);

				try {
					this.auditsService.addAudit(auditoriaDos);
				} catch (Exception au) {
					loggerProcessEnvíoMails.warn("Problema al guardar auditoria ", au);
				}

	            for (MailQueue message : pendingMessages) {
	                loggerProcessEnvíoMails.info("Procesando mensaje {}", message.getId());
	                loggerProcessEnvíoMails.debug("Mensaje: {}", message);

	                try {
	                	SendMessageDTO emailDTO = new SendMessageDTO(
	                	        List.of(message.getRecipient()),  	// Destinatario real
	                	        null,           					// CC (opcional)
	                	        null,          						// BCC (opcional)
	                	        message.getFrom(),                  // From
	                	        message.getFrom(),                          // Reply-To
	                	        message.getSubject(),                           // Asunto
	                	        message.getBody(),                              		  // Cuerpo
	                	        null,                                     // Rutas de adjuntos (si aplica)
	                	        true                                     // Indica si es HTML o no
	                	    );

	                	emailService.sendMessage(emailDTO);
	                    successCount++;
	                    message.setStatus(Constantes.MAILS_STATUS_SEND);
	                    emailService.updMailQueue(message);
	                } catch (Exception ex) {
	                    
	                    message.setStatus(Constantes.MAILS_STATUS_PENDING);
	                    message.setAttempts(message.getAttempts()+1);
	                    emailService.updMailQueue(message);

	                    loggerProcessEnvíoMails.error("Error al procesar el mensaje {}: {}", message.getId(), ex.getMessage());
	                } 

	                loggerProcessEnvíoMails.info("Aumentar número de reintentos para el mensaje {}", message.getId());

	                endTime = LocalDateTime.now();
	                loggerProcessEnvíoMails.info("Tiempo de ejecución parcial: {} ms", Duration.between(startTime, endTime).toMillis());
	            }
	        } else {
	            loggerProcessEnvíoMails.info("No hay registros pendientes");
	            AuditDTO auditoriaDos = new AuditDTO();
	            auditoriaDos.setInsertLogin(AuditoriaConstans.USUARIO_SISTEMA);
				auditoriaDos.setInsertName(AuditoriaConstans.USUARIO_SISTEMA);
				auditoriaDos.setAction(AuditoriaConstans.ACTION_SEND_MAIL);
				auditoriaDos.setIdElemento(0L);
				auditoriaDos.setTipoElemento(AuditoriaConstans.TIPO_ELEMENTO_MAIL);
				auditoriaDos.setDescription("No hay registros pendientes");
				auditoriaDos.setSystem(AuditoriaConstans.USUARIO_PROCESO);
				
				try {
					this.auditsService.addAudit(auditoriaDos);
				} catch (Exception au) {
					loggerProcessEnvíoMails.warn("Problema al guardar auditoria ", au);
				}
	        }

	        endTime = LocalDateTime.now();
	        
            AuditDTO auditoriaCinco = new AuditDTO();
            auditoriaCinco.setInsertLogin(AuditoriaConstans.USUARIO_SISTEMA);
            auditoriaCinco.setInsertName(AuditoriaConstans.USUARIO_SISTEMA);
			auditoriaCinco.setAction(AuditoriaConstans.ACTION_SEND_MAIL);
			auditoriaCinco.setIdElemento(0L);
			auditoriaCinco.setTipoElemento(AuditoriaConstans.TIPO_ELEMENTO_MAIL);
			auditoriaCinco.setDescription(String.format(
	                "Fin proceso EnvioMailsProcess, Total registros enviados: %d, tiempo de ejecución: %d ms",
	                successCount, Duration.between(startTime, endTime).toMillis()));
			auditoriaCinco.setSystem(AuditoriaConstans.USUARIO_PROCESO);
			
			try {
				this.auditsService.addAudit(auditoriaCinco);
			} catch (Exception au) {
				loggerProcessEnvíoMails.warn("Problema al guardar auditoria ", au);
			}


	    } catch (Exception e) {
	        loggerProcessEnvíoMails.error("Error inesperado en el proceso: {}", e.getMessage(), e);
            AuditDTO auditoriaDos = new AuditDTO();
            auditoriaDos.setInsertLogin(AuditoriaConstans.USUARIO_SISTEMA);
			auditoriaDos.setInsertName(AuditoriaConstans.USUARIO_SISTEMA);
			auditoriaDos.setAction(AuditoriaConstans.ACTION_SEND_MAIL);
			auditoriaDos.setIdElemento(0L);
			auditoriaDos.setTipoElemento(AuditoriaConstans.TIPO_ELEMENTO_MAIL);
			auditoriaDos.setDescription(e.getMessage());
			auditoriaDos.setSystem(AuditoriaConstans.USUARIO_PROCESO);
			
			try {
				this.auditsService.addAudit(auditoriaDos);
			} catch (Exception au) {
				loggerProcessEnvíoMails.warn("Problema al guardar auditoria ", au);
			}
	    }

	    endTime = LocalDateTime.now();
	    loggerProcessEnvíoMails.info("Total registros enviados: {} Tiempo total de ejecución: {} ms",
	            successCount, Duration.between(startTime, endTime).toMillis());

	}

}
