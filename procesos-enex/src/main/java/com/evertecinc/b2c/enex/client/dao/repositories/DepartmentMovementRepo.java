package com.evertecinc.b2c.enex.client.dao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.evertecinc.b2c.enex.client.model.entities.DepartmentMovement;

public interface DepartmentMovementRepo extends JpaRepository<DepartmentMovement, Long> {
}
