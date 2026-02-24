package com.evertecinc.b2c.enex.client.dao.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.evertecinc.b2c.enex.client.model.entities.StationCardConstraint;
import com.evertecinc.b2c.enex.client.model.entities.StationCardConstraintId;

public interface StationCardConstraintRepo extends JpaRepository<StationCardConstraint, StationCardConstraintId> {
	
	public void deleteByCardIdCard(Long idCard);
	
	public List<StationCardConstraint> findByCardIdCard(Long idCard);

}
