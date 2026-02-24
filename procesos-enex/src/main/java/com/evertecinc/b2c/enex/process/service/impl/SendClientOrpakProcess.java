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
import com.evertecinc.b2c.enex.client.model.dto.ClientDTO;
import com.evertecinc.b2c.enex.client.service.ClientService;
import com.evertecinc.b2c.enex.process.exceptions.ProcessException;
import com.evertecinc.b2c.enex.process.orpak.service.IProcessOrpakService;
import com.evertecinc.b2c.enex.process.service.IProcess;
import com.evertecinc.b2c.enex.saf.service.SafService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service("SendClientOrpakProcess")
@ConfigurationProperties(prefix = "process.jde.send")
@RequiredArgsConstructor
@Slf4j
public class SendClientOrpakProcess implements IProcess {

	@Autowired
	IProcessOrpakService procesoOrpak;
	
	@Autowired
	SafService safService;
	@Autowired
	AuditsService auditsService;
	
	@Autowired
	ClientService clientesService;
	
	
	@Value("${process.jde.send.filepath}")
    private String rutaArchivo;

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
			auditoria.setAction(AuditoriaConstans.ACCION_ORPAK_ENVIAR_CLIENTE);
			auditoria.setIdElemento(0L);
			auditoria.setTipoElemento(AuditoriaConstans.TIPO_ELEMENTO_DEPARTAMENTO);
			auditoria.setDescription("Inicio proceso SendClientOrpakProcess");
			auditoria.setSystem(AuditoriaConstans.USUARIO_PROCESO);

			try {
				this.auditsService.addAudit(auditoria);
			} catch (Exception au) {
				log.warn("Problema al guardar auditoria ", au);
			}
	    	
	    	
	        log.info("Recuperar registros pendientes");
	        
			List<ClientDTO> listadoClientes = clientesService.getClientPendingOrpak();

	        if (listadoClientes != null && !listadoClientes.isEmpty()) {
	            log.info("Total registros pendientes a procesar: {}", listadoClientes.size());
	            
	            AuditDTO auditoriaDos = new AuditDTO();
	            auditoriaDos.setInsertLogin(AuditoriaConstans.USUARIO_ORPAK);
				auditoriaDos.setInsertName(AuditoriaConstans.USUARIO_ORPAK);
				auditoriaDos.setAction(AuditoriaConstans.ACCION_ORPAK_ENVIAR_CLIENTE);
				auditoriaDos.setIdElemento(0L);
				auditoriaDos.setTipoElemento(AuditoriaConstans.TIPO_ELEMENTO_DEPARTAMENTO);
				auditoriaDos.setDescription("Total registros pendientes a procesar: " + listadoClientes.size());
				auditoriaDos.setSystem(AuditoriaConstans.USUARIO_PROCESO);

				try {
					this.auditsService.addAudit(auditoriaDos);
				} catch (Exception au) {
					log.warn("Problema al guardar auditoria ", au);
				}

	            for (ClientDTO cliente : listadoClientes) {
	                log.info("Procesando mensaje {}", cliente.getIdClient());
	                log.debug("Mensaje: {}", cliente);

	                try {
	                    procesoOrpak.sendClient(cliente);
	                    successCount++;
	                } catch (Exception ex) {
	                    log.error("Error al procesar el mensaje {}: {}", cliente.getIdClient(), ex.getMessage());
	                } 

	                endTime = LocalDateTime.now();
	                log.info("Tiempo de ejecución parcial: {} ms", Duration.between(startTime, endTime).toMillis());
	            }
	        } else {
	            log.info("No hay registros pendientes");
	            AuditDTO auditoriaDos = new AuditDTO();
	            auditoriaDos.setInsertLogin(AuditoriaConstans.USUARIO_ORPAK);
				auditoriaDos.setInsertName(AuditoriaConstans.USUARIO_ORPAK);
				auditoriaDos.setAction(AuditoriaConstans.ACCION_ORPAK_ENVIAR_CLIENTE);
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

            AuditDTO auditoriaTres = new AuditDTO();
            auditoriaTres.setInsertLogin(AuditoriaConstans.USUARIO_ORPAK);
            auditoriaTres.setInsertName(AuditoriaConstans.USUARIO_ORPAK);
            auditoriaTres.setAction(AuditoriaConstans.ACCION_ORPAK_ENVIAR_CLIENTE);
            auditoriaTres.setIdElemento(0L);
            auditoriaTres.setTipoElemento(AuditoriaConstans.TIPO_ELEMENTO_DEPARTAMENTO);
            auditoriaTres.setDescription(String.format(
	                "Fin proceso SendClientOrpakProcess, Total registros enviados: %d, tiempo de ejecución: %d ms",
	                successCount, Duration.between(startTime, endTime).toMillis()));
            auditoriaTres.setSystem(AuditoriaConstans.USUARIO_PROCESO);

			try {
				this.auditsService.addAudit(auditoriaTres);
			} catch (Exception au) {
				log.warn("Problema al guardar auditoria ", au);
			}

	    } catch (Exception e) {
	        log.error("Error inesperado en el proceso: {}", e.getMessage(), e);
            AuditDTO auditoriaTres = new AuditDTO();
            auditoriaTres.setInsertLogin(AuditoriaConstans.USUARIO_ORPAK);
            auditoriaTres.setInsertName(AuditoriaConstans.USUARIO_ORPAK);
            auditoriaTres.setAction(AuditoriaConstans.ACCION_ORPAK_ENVIAR_CLIENTE);
            auditoriaTres.setIdElemento(0L);
            auditoriaTres.setTipoElemento(AuditoriaConstans.TIPO_ELEMENTO_DEPARTAMENTO);
            auditoriaTres.setDescription(e.getMessage());
            auditoriaTres.setSystem(AuditoriaConstans.USUARIO_PROCESO);

			try {
				this.auditsService.addAudit(auditoriaTres);
			} catch (Exception au) {
				log.warn("Problema al guardar auditoria ", au);
			}
	    }

	    endTime = LocalDateTime.now();
	    log.info("Total registros enviados: {} Tiempo total de ejecución: {} ms",
	            successCount, Duration.between(startTime, endTime).toMillis());

	}

}
