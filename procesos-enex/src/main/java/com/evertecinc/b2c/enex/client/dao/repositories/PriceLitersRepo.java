package com.evertecinc.b2c.enex.client.dao.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.evertecinc.b2c.enex.client.model.entities.PriceLiters;

public interface PriceLitersRepo extends JpaRepository<PriceLiters, Long> {
	
	List<PriceLiters> findByProductCode(String productCode);
	
	PriceLiters findByIdPriceLiter(Long idPriceLiter);

}
