package com.evertecinc.b2c.enex.client.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.evertecinc.b2c.enex.client.dao.repositories.VariablesRepo;
import com.evertecinc.b2c.enex.client.exceptions.VariableException;
import com.evertecinc.b2c.enex.client.model.entities.Variables;
import com.evertecinc.b2c.enex.utils.functions.Functions;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class VariableServiceImpl implements IVariableService{
	
	private final VariablesRepo variableRepoJPA;
	
	private String getVariable(String codigoPortal, String nombreVariable) throws VariableException{
		
		if(codigoPortal == null || Functions.hasEmptyOrNull(nombreVariable)) {
			log.error("Codigo portal no puede ser nulo, nombre de variable no puede ser nulo o vacio");
			throw new VariableException("Codigo portal no puede ser nulo, nombre de variable no puede ser nulo o vacio");
		}
		
		List<Variables> listaVariable = variableRepoJPA.findByCodigoPortalAndNombre(codigoPortal, nombreVariable);

		if (listaVariable == null || listaVariable.isEmpty()) {
		    throw new VariableException("Variable no encontrada: Nombre Variable: " + nombreVariable + " CodigoPortal: "+codigoPortal);
		} else if (listaVariable.size() > 1) {
		    throw new VariableException("Se encontró más de una variable con los parámetros: " + codigoPortal + ", " + nombreVariable);
		}
	
		return listaVariable.get(0).getValor();
	}

	@Override
	public String getVariableAsString(String codigoPortal, String nombreVariable) throws VariableException {
		return getVariable(codigoPortal, nombreVariable);
	}

	@Override
	public Long getVariableAsLong(String codigoPortal, String nombreVariable) throws VariableException {
		String value = getVariable(codigoPortal, nombreVariable);
        try {
            return Long.parseLong(value);
        } catch (NumberFormatException e) {
            throw new VariableException("Error al formatear la variable a Long: " + nombreVariable, e);
        }
	}

	@Override
	public Integer getVariableAsInteger(String codigoPortal, String nombreVariable) throws VariableException {
		String value = getVariable(codigoPortal, nombreVariable);
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new VariableException("Error al formatear la variable a Long: " + nombreVariable, e);
        }
	}

}
