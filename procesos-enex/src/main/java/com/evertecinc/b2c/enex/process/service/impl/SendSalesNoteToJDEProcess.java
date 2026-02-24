package com.evertecinc.b2c.enex.process.service.impl;

import java.io.File;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import com.evertecinc.b2c.constants.AuditoriaConstans;
import com.evertecinc.b2c.constants.Constantes;
import com.evertecinc.b2c.enex.audits.model.dto.AuditDTO;
import com.evertecinc.b2c.enex.audits.service.AuditsService;
import com.evertecinc.b2c.enex.process.exceptions.ProcessException;
import com.evertecinc.b2c.enex.process.jde.service.IProcessJdeService;
import com.evertecinc.b2c.enex.process.service.IProcess;
import com.evertecinc.b2c.enex.saf.constants.SafConstants;
import com.evertecinc.b2c.enex.saf.model.dto.MessageDTO;
import com.evertecinc.b2c.enex.saf.model.dto.MessageHistoryDTO;
import com.evertecinc.b2c.enex.saf.model.dto.SafCriterioDTO;
import com.evertecinc.b2c.enex.saf.service.SafService;
import com.evertecinc.b2c.enex.utils.functions.GeneralFunctions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;


@Service("SendSalesNoteToJDEProcess")
@RequiredArgsConstructor
@Slf4j
@ConfigurationProperties(prefix = "process.jde.send")
@Getter
@Setter
public class SendSalesNoteToJDEProcess implements IProcess {
	
    private final IProcessJdeService procesoJde;
    private final SafService safService;
    private final AuditsService auditsService;
    
    //valores cargados desde properties con el prefijo process.jde.send
	private Boolean preall;
	private Boolean posall;
    private String filepath;
    

	@Override
	public void run() throws ProcessException {
		// Para medir tiempos de procesamiento
	    LocalDateTime startTime = LocalDateTime.now();
	    LocalDateTime endTime = null;
	    int successCount = 0;

	    log.info("-----------------------------------------------");
	    log.info("Inicio proceso SendSalesNoteToJDEProcess");
	    
	    try {
		
	    	
            AuditDTO auditoria = new AuditDTO();
			auditoria.setInsertLogin(AuditoriaConstans.USUARIO_JDE);
			auditoria.setInsertName(AuditoriaConstans.USUARIO_JDE);
			auditoria.setAction(AuditoriaConstans.ACTION_JDE_SALES_NOTE);
			auditoria.setIdElemento(0L);
			auditoria.setTipoElemento(AuditoriaConstans.TIPO_ELEMENTO_DEPARTAMENTO);
			auditoria.setDescription("Inicio proceso SendSalesNoteToJDEProcess (NVS)");
			auditoria.setSystem(AuditoriaConstans.USUARIO_PROCESO);

			try {
				this.auditsService.addAudit(auditoria);
			} catch (Exception au) {
				log.warn("Problema al guardar auditoria ", au);
			}
	    	
	    	
	    	log.info( " Tipo de procesamiento por lote Pre: " + preall);
	    	log.info( " Tipo de procesamiento por lote Pos: " + posall);
	    	log.info( " Ruta del archivo: " + filepath);
	    	
	    	
	    	log.info("Recuperar registros pendientes");
	    	
	    	SafCriterioDTO criterio = new SafCriterioDTO();
	    	criterio.setType(SafConstants.SAF_SEND_NOTA_VENTA_JDE);
			
	    	
	    	List<MessageDTO> mensajesPendientes = safService.getSafDTO(criterio);
	    	
	    	
	    	if(mensajesPendientes==null || mensajesPendientes.isEmpty()) {
	    		log.info("No hay registros pendientes");
	    		
	            AuditDTO auditoriaDos = new AuditDTO();
	            auditoriaDos.setInsertLogin(AuditoriaConstans.USUARIO_JDE);
				auditoriaDos.setInsertName(AuditoriaConstans.USUARIO_JDE);
				auditoriaDos.setAction(AuditoriaConstans.ACTION_JDE_SALES_NOTE);
				auditoriaDos.setIdElemento(0L);
				auditoriaDos.setTipoElemento(AuditoriaConstans.TIPO_ELEMENTO_DEPARTAMENTO);
				auditoriaDos.setDescription("No hay registros pendientes");
				auditoriaDos.setSystem(AuditoriaConstans.USUARIO_PROCESO);

				try {
					this.auditsService.addAudit(auditoriaDos);
				} catch (Exception au) {
					log.warn("Problema al guardar auditoria ", au);
				}
	    		
	    	}else {
	    		
	    		log.info("Total de registros pendientes a procesar: {}", mensajesPendientes.size());
    	        log.debug("\n{}", mensajesPendientes.stream().map(Object::toString).collect(Collectors.joining("\n")));
    	        
    	        
	            AuditDTO auditoriaTres = new AuditDTO();
	            auditoriaTres.setInsertLogin(AuditoriaConstans.USUARIO_JDE);
	            auditoriaTres.setInsertName(AuditoriaConstans.USUARIO_JDE);
				auditoriaTres.setAction(AuditoriaConstans.ACTION_JDE_SALES_NOTE);
				auditoriaTres.setIdElemento(0L);
				auditoriaTres.setTipoElemento(AuditoriaConstans.TIPO_ELEMENTO_DEPARTAMENTO);
				auditoriaTres.setDescription("Total mensajes pendientes: "+mensajesPendientes.size());
				auditoriaTres.setSystem(AuditoriaConstans.USUARIO_PROCESO);

				try {
					this.auditsService.addAudit(auditoriaTres);
				} catch (Exception au) {
					log.warn("Problema al guardar auditoria ", au);
				}
    	        
    	        Integer correlativo = this.procesoJde.getCorrelativo();
    	        
    	        String rutaArchivo = filepath;
				// Aseguramos que la ruta termine con el separador adecuado
    	        if (!rutaArchivo.endsWith("/") && !rutaArchivo.endsWith("\\")) {
    	            rutaArchivo = rutaArchivo.concat(File.separator);
    	        }

    	        // Generamos el nombre del archivo
    	        String nombreArchivo = rutaArchivo
    	            .concat(Constantes.JDE_FILE_PREFIJO)
    	            .concat(GeneralFunctions.nCeros(correlativo, 6))
    	            .concat(".txt");

    	        log.info("Archivo generado: {}", nombreArchivo);
    	        
    	        Long idPrimeroEnCola = mensajesPendientes.get(0).getIdQueue();
    	        
    	        Integer contador = 0;
    	        
    	    	for (MessageDTO messageDTO : mensajesPendientes) {
    	    		
    	    		if (messageDTO == null) {
    	    			log.error("Mensaje no puede ser nulo");
						throw new IllegalArgumentException("Mensaje no puede ser nulo.");
    	    		}
    	    		
    	    		log.info("Procesando mensaje " + messageDTO.getIdQueue());
					log.debug(""+messageDTO);
					
					
					String mensaje = "";
					String retorno = "";
					
					boolean agregar = !idPrimeroEnCola.equals(messageDTO.getIdQueue());
					
					try {
						// recuperar lineas CSV para un mensaje (orden)
						if (!Constantes.ORDEN_COMPRA.equals(messageDTO.getData())){
							this.procesoJde.popOrder( messageDTO, nombreArchivo, agregar );
						}
						else{
							this.procesoJde.popOC(messageDTO, nombreArchivo, agregar);
						}
						
						
						contador++;
						successCount++;
						retorno = "0";
						mensaje = "OK";
					} catch (Exception e) {
						log.error("Ha ocurrido un error en el proceso. " + e.getMessage());
						mensaje = "Ha ocurrido un error en el proceso. " + e.getMessage();
						retorno = "-1";
					}
					
					MessageHistoryDTO message = new MessageHistoryDTO();
					message.setDateIns(LocalDateTime.now());
					message.setIdQueue(messageDTO.getIdQueue());
					message.setReturnCode(retorno);
					message.setMessage(mensaje);
					
					
					this.safService.pushMessageHistory(message);
					
					log.info("Aumentar numero de reintentos " + messageDTO.getIdQueue());
					this.safService.updRetriesById(messageDTO.getIdQueue());
					
					endTime = LocalDateTime.now();
					
					log.info("Tiempo parcial de ejecucion: {} ms", Duration.between(startTime, endTime).toMillis());
    				
    			}
	    		
	    	}
	    	
	    	endTime = LocalDateTime.now();
	    	
            AuditDTO auditoriaCuatro = new AuditDTO();
            auditoriaCuatro.setInsertLogin(AuditoriaConstans.USUARIO_JDE);
            auditoriaCuatro.setInsertName(AuditoriaConstans.USUARIO_JDE);
            auditoriaCuatro.setAction(AuditoriaConstans.ACTION_JDE_SALES_NOTE);
			auditoriaCuatro.setIdElemento(0L);
			auditoriaCuatro.setTipoElemento(AuditoriaConstans.TIPO_ELEMENTO_DEPARTAMENTO);
			auditoriaCuatro.setDescription(String.format(
	                "Fin proceso SendSalesNoteToJDEProcess, Total registros enviados: %d, tiempo de ejecución: %d ms",
	                successCount, Duration.between(startTime, endTime).toMillis()));
			auditoriaCuatro.setSystem(AuditoriaConstans.USUARIO_PROCESO);

			try {
				this.auditsService.addAudit(auditoriaCuatro);
			} catch (Exception au) {
				log.warn("Problema al guardar auditoria ", au);
			}
	    	
	    	
		} catch (Exception e) {
			log.error("Error al procesar NVS "+e.getMessage());
			
            AuditDTO auditoriaCuatro = new AuditDTO();
            auditoriaCuatro.setInsertLogin(AuditoriaConstans.USUARIO_JDE);
            auditoriaCuatro.setInsertName(AuditoriaConstans.USUARIO_JDE);
            auditoriaCuatro.setAction(AuditoriaConstans.ACTION_JDE_SALES_NOTE);
			auditoriaCuatro.setIdElemento(0L);
			auditoriaCuatro.setTipoElemento(AuditoriaConstans.TIPO_ELEMENTO_DEPARTAMENTO);
			auditoriaCuatro.setDescription(e.getMessage());
			auditoriaCuatro.setSystem(AuditoriaConstans.USUARIO_PROCESO);

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
