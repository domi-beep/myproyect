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

@Service("DeshabilitarCuentasExpiradasProcess")
@RequiredArgsConstructor
@Slf4j
public class DeshabilitarCuentasExpiradasProcess implements IProcess{
	
	@Autowired
	private IUserService userServiceImpl;
	
	@Autowired
	private IVariableService variableServiceImpl;
	
	@Autowired
	private IKeycloakService keycloakServiceImpl;
	
	@Override
	public void run() throws ProcessException {
		// Para medir tiempos de procesamiento
	    LocalDateTime startTime = LocalDateTime.now();
	    LocalDateTime endTime = null;
	    int successCount = 0;

	    log.info("-----------------------------------------------");
	    log.info("Inicio proceso DeshabilitarCuentasExpiradasProcess");
	    


	    try {
	    	log.info("Recuperar Variable Dias Inactividad");
	    	
	    	Integer variableDiasInactividad = null;
			
			try {
				variableDiasInactividad = this.variableServiceImpl.getVariableAsInteger(
						PropertiesConstants.PROPERTIES_PORTAL_TODOS,
						PropertiesConstants.DIAS_INACTIVO_DESHABILITAR_USUARIO);
			} catch (VariableException e) {
				log.error("Ha ocurrio un error al traer la variable con el portal: {} y el nombre: {} , error: {}",
						PropertiesConstants.PROPERTIES_PORTAL_TODOS, PropertiesConstants.DIAS_INACTIVO_DESHABILITAR_USUARIO,
						e.getMessage());
				variableDiasInactividad = 180;
			} catch (Exception e) {
				log.error("Ha ocurrio un error al traer la variable con el portal: {} y el nombre: {} , error: {}",
						PropertiesConstants.PROPERTIES_PORTAL_TODOS, PropertiesConstants.DIAS_INACTIVO_DESHABILITAR_USUARIO,
						e.getMessage());
				variableDiasInactividad = 180;
			}
			
			log.info("Variable recuperada: "+variableDiasInactividad);
	    	
	    	
	    	List<Users> listaUsuarios = this.userServiceImpl.obtenerUsuariosInactivos(variableDiasInactividad);
	    			
	        if(listaUsuarios == null || listaUsuarios.size() <= 0) {
	        	log.info("No hay usuarios que se tengan que bloquear por antiguedad");
	        	return;
	        }
	        
	        log.info("cantidad de usuarios a procesar: "+listaUsuarios.size());
	        
	        for(Users usuario : listaUsuarios) {
	        	successCount++;
	        	try {
	        		log.info("Usuario a procesar: "+usuario.toString());
	        		this.keycloakServiceImpl.validarUsuarioLocalContraUsuarioKeycloakParaDeshabilitar(usuario,variableDiasInactividad);
	        	}catch(Exception e) {
                    log.error("Error inesperado al procesar el usuario {}: {}", usuario.getIdUser(), e.getMessage());
	        	}
	        }
	    	
	    } catch (Exception e) {
	        log.error("Error inesperado en el proceso: {}", e.getMessage(), e);
	    }

	    endTime = LocalDateTime.now();
	    log.info("Total registros enviados: {} Tiempo total de ejecución: {} ms",
	            successCount, Duration.between(startTime, endTime).toMillis());

	}

}
