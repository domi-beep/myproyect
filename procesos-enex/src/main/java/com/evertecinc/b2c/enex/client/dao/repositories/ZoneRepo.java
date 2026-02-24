package com.evertecinc.b2c.enex.client.dao.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.evertecinc.b2c.enex.client.model.entities.Zone;

public interface ZoneRepo extends JpaRepository<Zone, Long> {
	
	List<Zone> findByActive(String active);

}
