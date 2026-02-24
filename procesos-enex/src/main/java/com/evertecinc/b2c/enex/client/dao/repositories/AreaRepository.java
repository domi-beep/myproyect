package com.evertecinc.b2c.enex.client.dao.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.evertecinc.b2c.enex.client.model.entities.Area;

@Repository
public interface AreaRepository extends JpaRepository<Area, Long> {

    List<Area> findByActive(String active);

    List<Area> findByCode(String code);

    List<Area> findByRegionIdRegion(Long idRegion);

    List<Area> findByCityIdCity(Long idCity);
}
