package com.evertecinc.b2c.enex.client.dao.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.evertecinc.b2c.enex.client.model.entities.City;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

    List<City> findByActive(String active);

    List<City> findByCode(String code);

    List<City> findByRegionIdRegion(Long idRegion);
}
