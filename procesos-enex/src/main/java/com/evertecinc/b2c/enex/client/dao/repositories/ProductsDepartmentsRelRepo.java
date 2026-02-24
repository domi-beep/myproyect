package com.evertecinc.b2c.enex.client.dao.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.evertecinc.b2c.enex.client.model.dto.ProductDepartmentDTO;
import com.evertecinc.b2c.enex.client.model.entities.ProductsDepartmentsRel;
import com.evertecinc.b2c.enex.client.model.entities.ProductsDepartmentsRel.ProductsDepartmentsRelId;

public interface ProductsDepartmentsRelRepo extends JpaRepository<ProductsDepartmentsRel, ProductsDepartmentsRelId> {

	public List<ProductsDepartmentsRel> findByDepartmentIdDepartmentAndProductProductCodeAndDocumentType(Long idDepartment, String productCode, String documentType);

	@Query("""
			SELECT
				pdr
			FROM
				ProductsDepartmentsRel pdr
			WHERE
				(:#{#params.idDepartment} is null or pdr.id.idDepartment = :#{#params.idDepartment}) AND
				(:#{#params.productCode} is null or pdr.productCode ILIKE :#{#params.productCode}) AND
				(:#{#params.documentType} is null or pdr.documentType ILIKE :#{#params.documentType}) AND
				(:#{#params.remainingAmount} is null or pdr.remainingAmount = :#{#params.remainingAmount})
			""")
	public List<ProductsDepartmentsRel> getProductsDepartmentsByCriteria(@Param("params") ProductDepartmentDTO params);
	
	public List<ProductsDepartmentsRel> findByIdDepartment(Long idDepartment);
}
