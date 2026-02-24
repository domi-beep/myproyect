package com.evertecinc.b2c.enex.client.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import org.springframework.stereotype.Service;

import com.evertecinc.b2c.enex.client.constants.PropertiesConstants;
import com.evertecinc.b2c.enex.client.dao.repositories.UsersRepo;
import com.evertecinc.b2c.enex.client.exceptions.VariableException;
import com.evertecinc.b2c.enex.client.model.entities.Users;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {
	
	private final UsersRepo usersRepoJPA;
	
	private final IVariableService variableServiceImpl;
	
	private static final ZoneId ZONE = ZoneId.of("America/Santiago");

	@Override
	public List<Users> obtenerUsuariosInactivos(Integer diasMaximoInactividad) {
		
		if(diasMaximoInactividad == null || diasMaximoInactividad <= 0) {
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
			
			diasMaximoInactividad = variableDiasInactividad;
			
		}
		
        LocalDateTime fechaHoraMaximoInactividad = LocalDateTime.now(ZONE).minusDays(diasMaximoInactividad);
		
        return usersRepoJPA.findInactiveOrNeverLogged(fechaHoraMaximoInactividad);
    }

	
}
