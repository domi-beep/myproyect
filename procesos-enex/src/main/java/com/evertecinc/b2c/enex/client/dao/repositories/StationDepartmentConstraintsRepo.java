package com.evertecinc.b2c.enex.client.dao.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.evertecinc.b2c.enex.client.model.entities.StationDepartmentConstraints;
import com.evertecinc.b2c.enex.client.model.entities.StationDepartmentConstraintsId;


public interface StationDepartmentConstraintsRepo extends JpaRepository<StationDepartmentConstraints, StationDepartmentConstraintsId> {
	
	public void deleteByIdDepartment(Long idDepartment);
	
	public List<StationDepartmentConstraints> getByIdDepartment(Long idDepartment);
	
}
