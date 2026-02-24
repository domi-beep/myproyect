package com.evertecinc.b2c.enex.keycloak.service;

import java.time.Instant;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.keycloak.representations.idm.UserRepresentation;

import com.evertecinc.b2c.enex.client.model.entities.Users;

public interface IKeycloakService {

	public void addOrUpdateUsersKeycloak() throws Exception;

	public void removeUsersKeycloak() throws Exception;

	public void disableUsersKeycloak() throws Exception;

	public List<UserRepresentation> obtenerUsuariosKeycloakPorRol(Collection<String> roleNames);
	
	public Optional<Instant> obtenerUltimoLoginPorUsuario(String userIdKeycloak);
	
	public UserRepresentation obtenerUsuarioKeycloakPorRut(String rutUsuario);

	void validarUsuarioLocalContraUsuarioKeycloakParaDeshabilitar(Users usuario, Integer diasInactividadMaximo);
	
	
}