package com.evertecinc.b2c.enex.client.dao.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.evertecinc.b2c.enex.client.model.entities.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    // Custom query based on criteria (to be implemented in service layer)
    List<Department> findByIdClient(Long idClient);
}