package com.evertecinc.b2c.enex.process.service.impl;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.evertecinc.b2c.enex.client.constants.PropertiesConstants;
import com.evertecinc.b2c.enex.client.exceptions.VariableException;
import com.evertecinc.b2c.enex.client.model.entities.Users;
import com.evertecinc.b2c.enex.client.service.IUserService;
import com.evertecinc.b2c.enex.client.service.IVariableService;
import com.evertecinc.b2c.enex.keycloak.service.IKeycloakService;
import com.evertecinc.b2c.enex.process.exceptions.ProcessException;
import com.evertecinc.b2c.enex.process.service.IProcess;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service("DeshabilitarCuentasKeycloakProcess")
@RequiredArgsConstructor
@Slf4j
public class DeshabilitarCuentasKeycloakProcess implements IProcess{
	
	@Autowired
	private IKeycloakService keycloakServiceImpl;
	
	@Override
	public void run() throws ProcessException {
		// Para medir tiempos de procesamiento
	    LocalDateTime startTime = LocalDateTime.now();
	    LocalDateTime endTime = null;

	    log.info("-----------------------------------------------");
	    log.info("Inicio proceso DeshabilitarCuentasExpiradasProcess");
	    


	    try {
	    	this.keycloakServiceImpl.disableUsersKeycloak();
	    	
	    } catch (Exception e) {
	        log.error("Error inesperado en el proceso: {}", e.getMessage(), e);
	    }

	    endTime = LocalDateTime.now();
	    log.info("Tiempo total de ejecución: {} ms",
	            Duration.between(startTime, endTime).toMillis());

	}

}
