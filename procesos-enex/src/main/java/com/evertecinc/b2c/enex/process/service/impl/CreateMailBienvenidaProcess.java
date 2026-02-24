package com.evertecinc.b2c.enex.process.service.impl;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.evertecinc.b2c.constants.AuditoriaConstans;
import com.evertecinc.b2c.enex.audits.model.dto.AuditDTO;
import com.evertecinc.b2c.enex.audits.service.AuditsService;
import com.evertecinc.b2c.enex.client.dao.repositories.SafQueueRepo;
import com.evertecinc.b2c.enex.email.model.dto.SafCorreoBienvenida;
import com.evertecinc.b2c.enex.email.service.EmailService;
import com.evertecinc.b2c.enex.process.exceptions.ProcessException;
import com.evertecinc.b2c.enex.process.service.IProcess;
import com.evertecinc.b2c.enex.saf.constants.SafConstants;
import com.evertecinc.b2c.enex.saf.model.dto.MessageHistoryDTO;
import com.evertecinc.b2c.enex.saf.model.dto.SafCriterioDTO;
import com.evertecinc.b2c.enex.saf.model.entities.SafQueue;
import com.evertecinc.b2c.enex.saf.service.SafService;
import com.google.gson.Gson;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service("CreateMailBienvenidaProcess")
//@ConfigurationProperties(prefix = "process.jde.send")
@RequiredArgsConstructor
@Slf4j
public class CreateMailBienvenidaProcess implements IProcess{
	
	@Autowired
	private SafService safService;
	
	@Autowired
	private SafQueueRepo safRepoJPA;

	@Autowired
	private EmailService emailService;
	
	@Autowired
	AuditsService auditsService;
	
	@Override
	public void run() throws ProcessException {
		// Para medir tiempos de procesamiento
	    LocalDateTime startTime = LocalDateTime.now();
	    LocalDateTime endTime = null;
	    int successCount = 0;

	    log.info("-----------------------------------------------");
	    log.info("Inicio proceso CreateMailBienvenidaProcess");

	    try {
	    	log.info("Recuperar registros pendientes SMBK (Send Mail Bienvenida Keycloak)");
	    	
	    	SafCriterioDTO safCriterio = new SafCriterioDTO();
	    	safCriterio.setType(SafConstants.SAF_SEND_MAIL_BIENVENIDA_KEYCLOAK);
	        safCriterio.setStatus(SafConstants.SAF_STATUS_PENDING);
	    	
	        List<SafQueue> safRecuperada = this.safService.getSaf(safCriterio);
	    	
	        if(safRecuperada == null || safRecuperada.size() <= 0) {
	        	log.info("No hay saf a recuperar para el proceso CreateMailBienvenidaProcess");
	        	return;
	        }
	        
	        log.info("cantidad de correos de bienvenida a procesar: "+safRecuperada.size());
	        
            AuditDTO auditoria = new AuditDTO();
			auditoria.setInsertLogin(AuditoriaConstans.USUARIO_KEY_CLOAK);
			auditoria.setInsertName(AuditoriaConstans.USUARIO_KEY_CLOAK);
			auditoria.setAction(AuditoriaConstans.ACTION_SEND_MAIL_BIENVENIDA);
			auditoria.setIdElemento(0L);
			auditoria.setTipoElemento(AuditoriaConstans.TIPO_ELEMENTO_MAIL);
			auditoria.setDescription("Se comienzan a procesar correos de bienvenida "+safRecuperada.size());
			auditoria.setSystem(AuditoriaConstans.USUARIO_PROCESO);

			try {
				this.auditsService.addAudit(auditoria);
			} catch (Exception au) {
				log.warn("Problema al guardar auditoria ", au);
			}
	        Gson gson = new Gson();
	        
	        for(SafQueue saf : safRecuperada) {
	        	
	        	try {
	        		log.info("Saf a procesar: "+saf.toString());
	        		
	        		if(saf == null || saf.getData() == null) {
	        			log.error("Argumento Data de la saf llega null: ");
			        	throw new IllegalArgumentException("Columna data llego null");
	        		}
	        		
	        		SafCorreoBienvenida safData = new SafCorreoBienvenida();
			        
			        safData = gson.fromJson(saf.getData(), SafCorreoBienvenida.class);
			        
			        if(safData == null) {
			        	log.error("Transformacion de Gson a Object retorno null");
			        	throw new IllegalArgumentException("Transformacion de Gson a Object retorno null");
			        }
			        
			        
			        this.emailService.SendMailBienvenidaKeycloak(safData);
			        
			        saf.setDateSend(LocalDateTime.now());
			        saf.setStatus(SafConstants.SAF_STATUS_SENDED);
			        
			        this.safRepoJPA.save(saf);
			        successCount++;
	        		
	        	}catch(Exception e) {
	        		MessageHistoryDTO history = new MessageHistoryDTO();
                    history.setDateIns(LocalDateTime.now());
                    history.setIdQueue(saf.getIdQueue());
                    history.setReturnCode("");
                    history.setUri("");
                    history.setMessage(e.getMessage());
                    safService.pushMessageHistory(history);
                    
                    saf.setNumRetries(saf.getNumRetries()+1);
                    this.safRepoJPA.save(saf);

                    log.error("Error inesperado al procesar el mensaje {}: {}", saf.getIdQueue(), e.getMessage());
	        		
	        	}
	        	
	        
	        
	        }
	        
		    endTime = LocalDateTime.now();
		    
	        AuditDTO auditoriaCinco = new AuditDTO();
	        auditoriaCinco.setInsertLogin(AuditoriaConstans.USUARIO_KEY_CLOAK);
	        auditoriaCinco.setInsertName(AuditoriaConstans.USUARIO_KEY_CLOAK);
	        auditoriaCinco.setAction(AuditoriaConstans.ACTION_SEND_MAIL_BIENVENIDA);
	        auditoriaCinco.setIdElemento(0L);
	        auditoriaCinco.setTipoElemento(AuditoriaConstans.TIPO_ELEMENTO_MAIL);
	        auditoriaCinco.setDescription(String.format("Fin proceso correos de bienvenida, Total registros enviados: %d, tiempo de ejecución: %d ms",successCount, Duration.between(startTime, endTime).toMillis()));
	        auditoriaCinco.setSystem(AuditoriaConstans.USUARIO_PROCESO);
	        
			try {
				this.auditsService.addAudit(auditoriaCinco);
			} catch (Exception au) {
				log.warn("Problema al guardar auditoria ", au);
			}
	    	
	    } catch (Exception e) {
	        log.error("Error inesperado en el proceso: {}", e.getMessage(), e);
            AuditDTO auditoria = new AuditDTO();
			auditoria.setInsertLogin(AuditoriaConstans.USUARIO_KEY_CLOAK);
			auditoria.setInsertName(AuditoriaConstans.USUARIO_KEY_CLOAK);
			auditoria.setAction(AuditoriaConstans.ACTION_SEND_MAIL_BIENVENIDA);
			auditoria.setIdElemento(0L);
			auditoria.setTipoElemento(AuditoriaConstans.TIPO_ELEMENTO_MAIL);
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
