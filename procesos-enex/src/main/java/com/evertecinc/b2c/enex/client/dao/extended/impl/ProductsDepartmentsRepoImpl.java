package com.evertecinc.b2c.enex.client.dao.extended.impl;

import org.springframework.stereotype.Repository;

import com.evertecinc.b2c.enex.client.dao.extended.IProductsDepartmensRepo;
import com.evertecinc.b2c.enex.client.model.dto.ProductDepartmentDTO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ProductsDepartmentsRepoImpl implements IProductsDepartmensRepo {
	
	@PersistenceContext
	private final EntityManager entityManager;
	
	@Override
	@Transactional
	public void updDepartmentProductAmount(ProductDepartmentDTO dto) {
		try {
			String sqlQuery = """
					UPDATE ProductsDepartmentsRel a  
					SET
					    a.remainingAmount = :remainingAmount
					WHERE 
						(:idDepartment IS NULL OR a.department.idDepartment = :idDepartment) AND
					 	(:productCode IS NULL OR a.product.productCode = :productCode) AND
					 	(:documentType IS NULL OR a.documentType = :documentType)
					""";

			// Crear la consulta
			Query query = entityManager.createNativeQuery(sqlQuery);
			query.setParameter("remainingAmount", dto.getRemainingAmount());
			query.setParameter("idDepartment", dto.getIdDepartment());
			query.setParameter("productCode", dto.getProductCode());
			query.setParameter("documentType", dto.getDocumentType());

			// Ejecutar la actualización
			int updatedCount = query.executeUpdate();
			log.info("Se actualiza la relacion ProductsDepartmentsRel");

		} catch (Exception e) {
			log.error("ERROR " + e.getMessage(), e);
		}
	}
	
}
