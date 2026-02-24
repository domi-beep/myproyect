package com.evertecinc.b2c.enex.client.dao.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.evertecinc.b2c.enex.client.model.entities.PriceLitersHistory;


public interface PriceLitersHistoryRepo extends JpaRepository<PriceLitersHistory, Long>{

	List<PriceLitersHistory> findByIdPriceLiterOrderByDateDesc(Long idPriceLiter, Pageable pages);


}
