package com.evertecinc.b2c.enex.process.service.impl;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import com.evertecinc.b2c.constants.AuditoriaConstans;
import com.evertecinc.b2c.enex.audits.model.dto.AuditDTO;
import com.evertecinc.b2c.enex.audits.service.AuditsService;
import com.evertecinc.b2c.enex.process.exceptions.ProcessException;
import com.evertecinc.b2c.enex.process.jde.service.IProcessJdeService;
import com.evertecinc.b2c.enex.process.service.IProcess;
import com.evertecinc.b2c.enex.saf.service.SafService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service("SendNotificationsDailyProcess")
@ConfigurationProperties(prefix = "process.jde.send")
@RequiredArgsConstructor
@Slf4j
public class SendNotificationsDailyProcess implements IProcess {

	@Autowired
	IProcessJdeService processJDEService;
	
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
	    log.info("Inicio procesoSendNotificationsDailyProcess");

	    try {
	    	
            AuditDTO auditoria = new AuditDTO();
			auditoria.setInsertLogin(AuditoriaConstans.USUARIO_SISTEMA);
			auditoria.setInsertName(AuditoriaConstans.USUARIO_SISTEMA);
			auditoria.setAction(AuditoriaConstans.ACTION_ORPAK_ACTION_SEND_NOTIFICATION_DAILY);
			auditoria.setIdElemento(0L);
			auditoria.setTipoElemento(AuditoriaConstans.TIPO_ELEMENTO_CLIENTE);
			auditoria.setDescription("Inicio proceso SendNotificationsDailyProcess");
			auditoria.setSystem(AuditoriaConstans.USUARIO_PROCESO);

			try {
				this.auditsService.addAudit(auditoria);
			} catch (Exception au) {
				log.warn("Problema al guardar auditoria ", au);
			}

			log.info("Enviar alertas por problemas de stock a deptartamentos");
			this.processJDEService.sendAlertaProblemaStockDepto();
			
            AuditDTO auditoriaDos = new AuditDTO();
            auditoriaDos.setInsertLogin(AuditoriaConstans.USUARIO_SISTEMA);
            auditoriaDos.setInsertName(AuditoriaConstans.USUARIO_SISTEMA);
            auditoriaDos.setAction(AuditoriaConstans.ACTION_ORPAK_ACTION_SEND_NOTIFICATION_DAILY);
            auditoriaDos.setIdElemento(0L);
            auditoriaDos.setTipoElemento(AuditoriaConstans.TIPO_ELEMENTO_CLIENTE);
            auditoriaDos.setDescription("Se completa la notificacion sendAlertaProblemaStockDepto");
            auditoriaDos.setSystem(AuditoriaConstans.USUARIO_PROCESO);

			try {
				this.auditsService.addAudit(auditoriaDos);
			} catch (Exception au) {
				log.warn("Problema al guardar auditoria ", au);
			}
			
			log.info("Enviar alertas por problemas de stock a tarjetas");
			this.processJDEService.sendAlertaProblemaStockTarjeta();
			
            AuditDTO auditoriaTres = new AuditDTO();
            auditoriaTres.setInsertLogin(AuditoriaConstans.USUARIO_SISTEMA);
            auditoriaTres.setInsertName(AuditoriaConstans.USUARIO_SISTEMA);
            auditoriaTres.setAction(AuditoriaConstans.ACTION_ORPAK_ACTION_SEND_NOTIFICATION_DAILY);
            auditoriaTres.setIdElemento(0L);
            auditoriaTres.setTipoElemento(AuditoriaConstans.TIPO_ELEMENTO_CLIENTE);
            auditoriaTres.setDescription("Se completa la notificacion sendAlertaProblemaStockTarjeta");
            auditoriaTres.setSystem(AuditoriaConstans.USUARIO_PROCESO);

			try {
				this.auditsService.addAudit(auditoriaTres);
			} catch (Exception au) {
				log.warn("Problema al guardar auditoria ", au);
			}
			
			log.info("Enviar alertas por problemas de stock a clientes");
			this.processJDEService.sendAlertaProblemaStockCliente();
			
            AuditDTO auditoriaCuatro = new AuditDTO();
            auditoriaCuatro.setInsertLogin(AuditoriaConstans.USUARIO_SISTEMA);
            auditoriaCuatro.setInsertName(AuditoriaConstans.USUARIO_SISTEMA);
            auditoriaCuatro.setAction(AuditoriaConstans.ACTION_ORPAK_ACTION_SEND_NOTIFICATION_DAILY);
            auditoriaCuatro.setIdElemento(0L);
            auditoriaCuatro.setTipoElemento(AuditoriaConstans.TIPO_ELEMENTO_CLIENTE);
            auditoriaCuatro.setDescription("Se completa la notificacion sendAlertaProblemaStockCliente");
            auditoriaCuatro.setSystem(AuditoriaConstans.USUARIO_PROCESO);

			try {
				this.auditsService.addAudit(auditoriaCuatro);
			} catch (Exception au) {
				log.warn("Problema al guardar auditoria ", au);
			}

	        endTime = LocalDateTime.now();
	        
            AuditDTO auditoriaCinco = new AuditDTO();
            auditoriaCinco.setInsertLogin(AuditoriaConstans.USUARIO_SISTEMA);
            auditoriaCinco.setInsertName(AuditoriaConstans.USUARIO_SISTEMA);
            auditoriaCinco.setAction(AuditoriaConstans.ACTION_ORPAK_ACTION_SEND_NOTIFICATION_DAILY);
            auditoriaCinco.setIdElemento(0L);
            auditoriaCinco.setTipoElemento(AuditoriaConstans.TIPO_ELEMENTO_CLIENTE);
            auditoriaCinco.setDescription(String.format(
	                "Fin proceso SendNotificationsDailyProcess, Tiempo de ejecución: %d ms", Duration.between(startTime, endTime).toMillis()));
            auditoriaCinco.setSystem(AuditoriaConstans.USUARIO_PROCESO);

			try {
				this.auditsService.addAudit(auditoriaCinco);
			} catch (Exception au) {
				log.warn("Problema al guardar auditoria ", au);
			}

	    } catch (Exception e) {
	        log.error("Error inesperado en el proceso: {}", e.getMessage(), e);
	        
            AuditDTO auditoriaSeis = new AuditDTO();
            auditoriaSeis.setInsertLogin(AuditoriaConstans.USUARIO_SISTEMA);
            auditoriaSeis.setInsertName(AuditoriaConstans.USUARIO_SISTEMA);
            auditoriaSeis.setAction(AuditoriaConstans.ACTION_ORPAK_ACTION_SEND_NOTIFICATION_DAILY);
            auditoriaSeis.setIdElemento(0L);
            auditoriaSeis.setTipoElemento(AuditoriaConstans.TIPO_ELEMENTO_CLIENTE);
            auditoriaSeis.setDescription(e.getMessage());
            auditoriaSeis.setSystem(AuditoriaConstans.USUARIO_PROCESO);

			try {
				this.auditsService.addAudit(auditoriaSeis);
			} catch (Exception au) {
				log.warn("Problema al guardar auditoria ", au);
			}
	    }

	    endTime = LocalDateTime.now();
	    log.info("Total registros enviados: {} Tiempo total de ejecución: {} ms",
	            successCount, Duration.between(startTime, endTime).toMillis());

	}

}
