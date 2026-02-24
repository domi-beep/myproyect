package com.evertecinc.b2c.enex.process.service.impl;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import com.evertecinc.b2c.constants.AuditoriaConstans;
import com.evertecinc.b2c.enex.audits.model.dto.AuditDTO;
import com.evertecinc.b2c.enex.audits.service.AuditsService;
import com.evertecinc.b2c.enex.client.model.dto.ClientDTO;
import com.evertecinc.b2c.enex.client.model.dto.DepartmentCriterioDTO;
import com.evertecinc.b2c.enex.client.model.dto.DepartmentDTO;
import com.evertecinc.b2c.enex.process.exceptions.ProcessException;
import com.evertecinc.b2c.enex.process.jde.dto.DetalleCuentas;
import com.evertecinc.b2c.enex.process.jde.service.IProcessJdeService;
import com.evertecinc.b2c.enex.process.service.IProcess;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service("ChangeFactDepartmentProcess")
@ConfigurationProperties(prefix = "process.jde.send")
@RequiredArgsConstructor
@Slf4j
public class ChangeFactDepartmentProcess implements IProcess {
	
	@Autowired
	IProcessJdeService procesoJde;
	
	@Autowired
	AuditsService auditsService;
	
	@Value("${process.jde.send.filepath}")
    private String rutaArchivo;

	@Override
	public void run() throws ProcessException {
		LocalDateTime startTime = LocalDateTime.now();
	    LocalDateTime endTime = null;
	    int successCount = 0;

	    log.info("-----------------------------------------------");
	    log.info("Inicio proceso ChangeFactDepartmentProcess");

	    try {
	    	
            AuditDTO auditoria = new AuditDTO();
			auditoria.setInsertLogin(AuditoriaConstans.USUARIO_JDE);
			auditoria.setInsertName(AuditoriaConstans.USUARIO_JDE);
			auditoria.setAction(AuditoriaConstans.ACTION_JDE_CHANGE_FACT_DEPARTAMENTO);
			auditoria.setIdElemento(0L);
			auditoria.setTipoElemento(AuditoriaConstans.TIPO_ELEMENTO_DEPARTAMENTO);
			auditoria.setDescription("Inicio proceso cambio facturacion depto");
			auditoria.setSystem(AuditoriaConstans.USUARIO_PROCESO);

			try {
				this.auditsService.addAudit(auditoria);
			} catch (Exception au) {
				log.warn("Problema al guardar auditoria ", au);
			}

	    	//******* acá el proceso
	    	
	    	// Se define el nombre del archivo con la siguiente estructura: CLIXDEPYYYYMMDDHHMMSS.CSV
	    	String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
	    	String nombreArchivo = rutaArchivo.concat("CLIxDEP").concat(timestamp).concat(".CSV");

	    	log.info("Recuperar clientes con facturación por departamento");

	    	Optional<List<ClientDTO>> optionalClientes = this.procesoJde.getClientJdeByFactDepartmentStatus(true);

	    	// Declaro una lista para guardar las cuentas
	    	List<DetalleCuentas> listaDatos = new ArrayList<>();

	    	// Validar si hay clientes para procesar
	    	if (optionalClientes.isPresent()) {
	    	    List<ClientDTO> clientes = optionalClientes.get();

	    	    if (!clientes.isEmpty()) {
	    	        log.info("Total clientes a procesar: {}", clientes.size());
	    	        log.debug("\n{}", clientes.stream().map(Object::toString).collect(Collectors.joining("\n")));

	    	        
	                AuditDTO auditoriaDos = new AuditDTO();
	                auditoriaDos.setInsertLogin(AuditoriaConstans.USUARIO_JDE);
	    			auditoriaDos.setInsertName(AuditoriaConstans.USUARIO_JDE);
	    			auditoriaDos.setAction(AuditoriaConstans.ACTION_JDE_CHANGE_FACT_DEPARTAMENTO);
	    			auditoriaDos.setIdElemento(0L);
	    			auditoriaDos.setTipoElemento(AuditoriaConstans.TIPO_ELEMENTO_DEPARTAMENTO);
	    			auditoriaDos.setDescription("Total clientes a procesar: " + clientes.size());
	    			auditoriaDos.setSystem(AuditoriaConstans.USUARIO_PROCESO);

	    			try {
	    				this.auditsService.addAudit(auditoriaDos);
	    			} catch (Exception au) {
	    				log.warn("Problema al guardar auditoria ", au);
	    			}

	    	        // Por cada cliente obtengo la cuenta C y la cuenta D
	    	        for (ClientDTO clienteDTO : clientes) {
	    	            DepartmentCriterioDTO criteriaDeptos = new DepartmentCriterioDTO();
	    	            criteriaDeptos.setIdClient(clienteDTO.getIdClient());

	    	            List<DepartmentDTO> listaDeptos = this.procesoJde.getListDepartmentByClient(criteriaDeptos);
	    	            for (DepartmentDTO depto : listaDeptos) {
	    	                DetalleCuentas detalle = new DetalleCuentas();
	    	                detalle.setCodDeptoOrpak(depto.getCodeOrpakClient());
	    	                detalle.setRutCliente(clienteDTO.getUpi());
	    	                detalle.setRazonSocial(clienteDTO.getLegalName());
	    	                detalle.setCuentaC(clienteDTO.getAccountJdeClient());
	    	                detalle.setCuentaD(clienteDTO.getAccountJdeInvoice());
	    	                detalle.setNombreDepto(depto.getName());

	    	                if (clienteDTO.getFactDepartmentDate() != null) {
	    	                    detalle.setFechaFact(new SimpleDateFormat("dd/MM/yyyy").format(clienteDTO.getFactDepartmentDate()));
	    	                } else {
	    	                    log.warn("Fecha de facturación no encontrada para cliente: {}", clienteDTO.getIdClient());
	    	                    detalle.setFechaFact("N/A");
	    	                }

	    	                log.debug("Detalle cuenta cliente: {} {}", clienteDTO.getIdClient(), detalle);
	    	                listaDatos.add(detalle);
	    	            }
	    	        }

	    	        log.debug("Lista de datos: {}", listaDatos);

	    	        // Definir archivo CSV
	    	        Integer contador = 0;
	    	        CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
	    	                .setDelimiter(';')
	    	                .setRecordSeparator('\n')
	    	                .build();

	    	        try (
	    	                FileWriter fileWriter = new FileWriter(nombreArchivo);
	    	                CSVPrinter csvPrinter = new CSVPrinter(fileWriter, csvFormat)
	    	        ) {
	    	            // Agregar una línea en blanco al inicio
	    	            csvPrinter.printRecord(" ");

	    	            // Recuperar listado de cuentaC y cuentaD para escribir en archivo CSV
	    	            for (DetalleCuentas cuentas : listaDatos) {
	    	                csvPrinter.printRecord(cuentas.getRegistro());
	    	                contador++;
	    	                successCount++;
	    	            }
	    	        } catch (IOException e) {
	    	            log.error("Error al generar el archivo CSV: {}", e.getMessage(), e);
	    	            throw new RuntimeException("Error al generar el archivo CSV", e);
	    	        }

	    	        endTime = LocalDateTime.now();
	    	        log.info("Total registros enviados: {}. Tiempo total de ejecución: {} ms",
	    	                contador, Duration.between(startTime, endTime).toMillis());

	    	        log.info("Se genera archivo CSV: {}", nombreArchivo);
	    	    }
	    	} else {
	    	    log.info("No hay registros pendientes");
    	        
                AuditDTO auditoriaDos = new AuditDTO();
                auditoriaDos.setInsertLogin(AuditoriaConstans.USUARIO_JDE);
    			auditoriaDos.setInsertName(AuditoriaConstans.USUARIO_JDE);
    			auditoriaDos.setAction(AuditoriaConstans.ACTION_JDE_CHANGE_FACT_DEPARTAMENTO);
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
		    
	        AuditDTO auditoriaCinco = new AuditDTO();
	        auditoriaCinco.setInsertLogin(AuditoriaConstans.USUARIO_JDE);
	        auditoriaCinco.setInsertName(AuditoriaConstans.USUARIO_JDE);
	        auditoriaCinco.setAction(AuditoriaConstans.ACTION_JDE_CHANGE_FACT_DEPARTAMENTO);
	        auditoriaCinco.setIdElemento(0L);
	        auditoriaCinco.setTipoElemento(AuditoriaConstans.TIPO_ELEMENTO_DEPARTAMENTO);
	        auditoriaCinco.setDescription(String.format("Fin proceso cambio facturacion depto, Total registros enviados: %d, tiempo de ejecución: %d ms",successCount, Duration.between(startTime, endTime).toMillis()));
	        auditoriaCinco.setSystem(AuditoriaConstans.USUARIO_PROCESO);

			try {
				this.auditsService.addAudit(auditoriaCinco);
			} catch (Exception au) {
				log.warn("Problema al guardar auditoria ", au);
			}
	    	

	    } catch (Exception e) {
	        log.error("Error inesperado en el proceso: {}", e.getMessage(), e);
            AuditDTO auditoria = new AuditDTO();
			auditoria.setInsertLogin(AuditoriaConstans.USUARIO_JDE);
			auditoria.setInsertName(AuditoriaConstans.USUARIO_JDE);
			auditoria.setAction(AuditoriaConstans.ACTION_JDE_CHANGE_FACT_DEPARTAMENTO);
			auditoria.setIdElemento(0L);
			auditoria.setTipoElemento(AuditoriaConstans.TIPO_ELEMENTO_DEPARTAMENTO);
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
