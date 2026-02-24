package com.evertecinc.b2c.enex.client.dao.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.evertecinc.b2c.enex.client.model.entities.VehicleCard;
import com.evertecinc.b2c.enex.client.model.entities.VehicleCardId;

public interface VehicleCardRepo extends JpaRepository<VehicleCard, VehicleCardId> {

	public List<VehicleCard> findByVehicleIdVehicle(Long idVehicle);
	
	public VehicleCard findByCardIdCard(Long idCard);
	
	public VehicleCard findByCardIdCardAndVehicleIdVehicle(Long idCard, Long idVehicle);
	
	
}
