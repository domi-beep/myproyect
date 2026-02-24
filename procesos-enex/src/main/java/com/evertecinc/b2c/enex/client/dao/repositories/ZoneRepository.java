package com.evertecinc.b2c.enex.client.dao.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.evertecinc.b2c.enex.client.model.entities.Zone;

@Repository
public interface ZoneRepository extends JpaRepository<Zone, Long> {

    List<Zone> findByActive(String active);

    List<Zone> findByName(String name);
}
