package com.evertecinc.b2c.enex.client.dao.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.evertecinc.b2c.enex.client.model.entities.Variables;


public interface VariablesRepo extends JpaRepository<Variables, Long> {

	public List<Variables> findByCodigoPortalAndNombre(String codigoPortal, String nombre);
	public List<Variables> findByCodigoPortal(String codigoPortal);
	public List<Variables> findByNombre(String nombre);
	
	
}
