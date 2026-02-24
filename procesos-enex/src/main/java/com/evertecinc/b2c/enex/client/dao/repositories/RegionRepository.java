package com.evertecinc.b2c.enex.client.dao.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.evertecinc.b2c.enex.client.model.entities.Region;

@Repository
public interface RegionRepository extends JpaRepository<Region, Long> {

    List<Region> findByActive(String active);

    List<Region> findByCode(String code);

    List<Region> findByZoneIdZone(Long idZone);
}
