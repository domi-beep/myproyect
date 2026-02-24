package com.evertecinc.b2c.enex.client.dao.extended.impl;

import java.util.List;

import org.hibernate.query.Query;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.evertecinc.b2c.enex.classes.utils.db.DbUtils;
import com.evertecinc.b2c.enex.client.dao.extended.IPriceLitersRepo;
import com.evertecinc.b2c.enex.client.model.dto2.PreciosLitrosDTO;
import com.evertecinc.b2c.enex.client.model.dto2.requests.ListPreciosLitrosRequestDTO;
import com.evertecinc.b2c.enex.utils.functions.Functions;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Repository
@RequiredArgsConstructor
@Slf4j
public class PriceLitersRepoImpl implements IPriceLitersRepo{
	
	@PersistenceContext
	private final EntityManager entityManager;

	@Override
	@SuppressWarnings("unchecked")
	public List<PreciosLitrosDTO> getListPriceLiters(ListPreciosLitrosRequestDTO params, Pageable paging) {
		TypedQuery<PreciosLitrosDTO> query;
		List<PreciosLitrosDTO> response = null;
		
		try {
			String sqlQuery = new String("""
					SELECT 
						pre.idPriceLiter AS idPriceLiter,
						pro.name AS nameProduct,
						pre.productCode AS productCode,
						pre.price AS price,
						pre.date AS date,
						pre.user AS user
					FROM PriceLiters pre
					JOIN Products pro on pre.productCode = pro.productCode
					WHERE pro.productStatus = 'A'
			""") ;
			
			if(paging.getSort() != null)
				sqlQuery += Functions.getSortString(paging.getSort(), PreciosLitrosDTO.class);
			
			query = entityManager.createQuery(sqlQuery.toString(), PreciosLitrosDTO.class)
			.unwrap(Query.class)
			.setTupleTransformer((tuple, aliases) -> DbUtils.objFromFields(tuple, aliases, PreciosLitrosDTO.class));

			query.setFirstResult(paging.isUnpaged() ? 0 : (int) paging.getOffset());
			query.setMaxResults(paging.isUnpaged() ? Integer.MAX_VALUE : paging.getPageSize());
			
			response  = query.getResultList();
			
		}catch(Exception ex) {
			log.error("ERROR " + ex.getMessage());
			ex.printStackTrace();
		}
		
		log.debug("response: "+response);
		return response;
	}
	
}
