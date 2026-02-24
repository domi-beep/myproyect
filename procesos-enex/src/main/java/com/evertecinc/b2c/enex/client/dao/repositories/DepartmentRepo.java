package com.evertecinc.b2c.enex.client.dao.repositories;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.evertecinc.b2c.enex.client.model.entities.Departments;

import jakarta.transaction.Transactional;

public interface DepartmentRepo extends JpaRepository<Departments, Long> {

	public Departments findByIdDepartment(Long idDepartment);

	public List<Departments> findByClientIdClient(Long idClient);
	
	public Departments findByClientIdClientAndIdPadreIsNullAndDepartmentStatusNot(
            Long idClient, 
            String departmentStatus
    );
	
	@Modifying
	@Transactional
	@Query("UPDATE ProductsDepartmentsRel p " +
	        "SET p.remainingAmount = p.remainingAmount + :nuevoMonto " +
	        "WHERE (:idDeptoDestino IS NULL OR p.idDepartment = :idDeptoDestino) " +
	        "AND (:prodCode IS NULL OR p.productCode = :prodCode) " +
	        "AND (:documentType IS NULL OR p.documentType = :documentType)")
	public int updateDepartmentProductAmountSum(
	         @Param("nuevoMonto") BigDecimal nuevoMonto,
	         @Param("idDeptoDestino") Long idDeptoDestino,
	         @Param("prodCode") String prodCode,
	         @Param("documentType") String documentType
	);
	
}
