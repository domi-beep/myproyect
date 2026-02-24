package com.evertecinc.b2c.enex.process.service.impl;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.evertecinc.b2c.constants.AuditoriaConstans;
import com.evertecinc.b2c.enex.audits.model.dto.AuditDTO;
import com.evertecinc.b2c.enex.audits.service.AuditsService;
import com.evertecinc.b2c.enex.keycloak.service.IKeycloakService;
import com.evertecinc.b2c.enex.process.exceptions.ProcessException;
import com.evertecinc.b2c.enex.process.service.IProcess;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service("KeycloakAddOrUpdateUsersProcess")
//@ConfigurationProperties(prefix = "process.jde.send")
@RequiredArgsConstructor
@Slf4j
public class KeycloakAddOrUpdateUsersProcess implements IProcess {

	private @Autowired AuditsService auditsService;
	private @Autowired IKeycloakService keycloakService;
	
	@Override
	public void run() throws ProcessException {
		// Para medir tiempos de procesamiento
	    LocalDateTime startTime = LocalDateTime.now();
	    LocalDateTime endTime = null;

	    log.info("-----------------------------------------------");
	    log.info("Inicio proceso envío creación y/o actualización usuarios en keycloak");
	    
        AuditDTO auditoria = new AuditDTO();
		auditoria.setInsertLogin(AuditoriaConstans.USUARIO_KEY_CLOAK);
		auditoria.setInsertName(AuditoriaConstans.USUARIO_KEY_CLOAK);
		auditoria.setAction(AuditoriaConstans.ACTION_KEYCLOAK_ADD_USER);
		auditoria.setIdElemento(0L);
		auditoria.setTipoElemento(AuditoriaConstans.TIPO_ELEMENTO_USUARIO);
		auditoria.setDescription("Inicio proceso KeycloakAddOrUpdateUsersProcess");
		auditoria.setSystem(AuditoriaConstans.USUARIO_PROCESO);

		try {
			this.auditsService.addAudit(auditoria);
		} catch (Exception au) {
			log.warn("Problema al guardar auditoria ", au);
		}

		try {
			this.keycloakService.addOrUpdateUsersKeycloak();
			
			endTime = LocalDateTime.now();
	        AuditDTO auditoriaCinco = new AuditDTO();
	        auditoriaCinco.setInsertLogin(AuditoriaConstans.USUARIO_KEY_CLOAK);
	        auditoriaCinco.setInsertName(AuditoriaConstans.USUARIO_KEY_CLOAK);
	        auditoriaCinco.setAction(AuditoriaConstans.ACTION_KEYCLOAK_ADD_USER);
	        auditoriaCinco.setIdElemento(0L);
	        auditoriaCinco.setTipoElemento(AuditoriaConstans.TIPO_ELEMENTO_USUARIO);
	        auditoriaCinco.setDescription(String.format(
	                "Fin proceso KeycloakAddOrUpdateUsersProcess, tiempo de ejecución: %d ms", Duration.between(startTime, endTime).toMillis()));
	        auditoriaCinco.setSystem(AuditoriaConstans.USUARIO_PROCESO);

			try {
				this.auditsService.addAudit(auditoriaCinco);
			} catch (Exception au) {
				log.warn("Problema al guardar auditoria ", au);
			}
			
		} catch (Exception e) {
			log.error("Error procesando usuarios a KC: "+e.getMessage());
	        AuditDTO auditoriaDos = new AuditDTO();
	        auditoriaDos.setInsertLogin(AuditoriaConstans.USUARIO_KEY_CLOAK);
	        auditoriaDos.setInsertName(AuditoriaConstans.USUARIO_KEY_CLOAK);
	        auditoriaDos.setAction(AuditoriaConstans.ACTION_KEYCLOAK_ADD_USER);
	        auditoriaDos.setIdElemento(0L);
	        auditoriaDos.setTipoElemento(AuditoriaConstans.TIPO_ELEMENTO_USUARIO);
	        auditoriaDos.setDescription(e.getMessage());
	        auditoriaDos.setSystem(AuditoriaConstans.USUARIO_PROCESO);

			try {
				this.auditsService.addAudit(auditoriaDos);
			} catch (Exception au) {
				log.warn("Problema al guardar auditoria ", au);
			}
		}
		endTime = LocalDateTime.now();
	    log.info("Tiempo total de ejecución: {} ms", Duration.between(startTime, endTime).toMillis());
	}

}
