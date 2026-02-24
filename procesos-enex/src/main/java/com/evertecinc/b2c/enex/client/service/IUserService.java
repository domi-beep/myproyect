package com.evertecinc.b2c.enex.client.service;

import java.util.List;

import com.evertecinc.b2c.enex.client.model.entities.Users;


public interface IUserService {

	public List<Users> obtenerUsuariosInactivos(Integer fechaHoraMaximaInactividad);

	
}
