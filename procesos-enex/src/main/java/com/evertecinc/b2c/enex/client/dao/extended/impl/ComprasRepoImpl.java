package com.evertecinc.b2c.enex.client.dao.extended.impl;

import java.util.List;

import org.hibernate.query.Query;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.evertecinc.b2c.enex.classes.utils.db.DbUtils;
import com.evertecinc.b2c.enex.client.dao.extended.IComprasRepo;
import com.evertecinc.b2c.enex.client.model.dto2.MonitorComprasDTO;
import com.evertecinc.b2c.enex.client.model.dto2.requests.ComprasDetallePagoRequestDTO;
import com.evertecinc.b2c.enex.utils.functions.Functions;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ComprasRepoImpl implements IComprasRepo{
	
	
	@PersistenceContext
	private final EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<MonitorComprasDTO> getMonitorComprasByCriterio(ComprasDetallePagoRequestDTO params, Pageable paging) {
	
		TypedQuery<MonitorComprasDTO> query;
		List<MonitorComprasDTO> response = null;
		
		try {
			
			String sqlQuery = new String("""
				SELECT   
						O.idOrder as idOrder, 
						O.orderStatus as orderStatus,
						C.idClient as idClient,
						O.payDate  as payDate, 
						C.upi  as upi, 
						C.legalName as legalName, 
						O.totalOrder as totalOrder, 
						O.payType  as payType,
				CASE O.payType
				        WHEN 'C' THEN  CH.mpoutNumcomp
						WHEN 'E' THEN  E.resultadoTrxMpago
						WHEN 'T' THEN  T.authorizationCode
						WHEN 'S' THEN  S.mpoutFechatrx
						WHEN 'D' THEN  D.comprobante
						WHEN 'I' THEN  '0'
						WHEN 'B' THEN  '0'
				END AS numComprobante,
				CASE O.payType
				        WHEN 'C' THEN  CH.mpoutCodret
						WHEN 'E' THEN  E.resultadoMpago
						WHEN 'T' THEN  T.returnCode
						WHEN 'S' THEN  S.mpoutCodret
						WHEN 'D' THEN  '0'
						WHEN 'I' THEN  B.estado
						WHEN 'B' THEN  B.estado
				END AS respuesta,
				O.crtDate as crtDate,
				a.description as etapaPago
					from Orders O 
					join Clients C on O.idClient = C.idClient
					left join MpBancoChile CH on O.idOrder = CH.idOrder
					left join MpBancoEstado E on E.idOrder = O.idOrder
					left join MpBancoSantander S on S.idOrder = O.idOrder
					left join MpDeposito D on D.idOrder = O.idOrder
					left join MpBancoBci B on B.idOrder = O.idOrder
					left join Transbank T on T.idOrder = O.idOrder
					left join Audits a on a.modifyLogin = cast(O.idOrder as text)
					WHERE
								(:upi IS NULL OR C.upi ILIKE :upi) AND
								(:legalName IS NULL OR C.legalName ILIKE :legalName) AND
								(:payType IS NULL OR O.payType ILIKE :payType) AND
								(:totalOrder IS NULL OR O.totalOrder = :totalOrder) AND
								(CAST(:fechaIn as text) IS NULL or O.crtDate >= CAST(CAST(:fechaIn as text) as date)) AND 
								(CAST(:fechaEnd as text) IS NULL or O.crtDate <= CAST(CAST(:fechaEnd as text) as date)) AND
								(:idOrder IS NULL OR O.idOrder = :idOrder) AND
								(:orderStatus IS NULL OR O.orderStatus = :orderStatus)
					""");
		
		if(paging.getSort() != null)
			sqlQuery += Functions.getSortString(paging.getSort(), MonitorComprasDTO.class);
		
		query = entityManager.createQuery(sqlQuery.toString(), MonitorComprasDTO.class)
		.unwrap(Query.class)
		.setTupleTransformer((tuple, aliases) -> DbUtils.objFromFields(tuple, aliases, MonitorComprasDTO.class));
		
		
		query.setParameter("upi", params.getUpi() == null ? null : "%"+params.getUpi()+"%");
		query.setParameter("legalName", params.getLegalName() == null ? null : "%"+params.getUpi()+"%");
		query.setParameter("payType", params.getPayType() == null ? null : "%"+params.getPayType()+"%");
		query.setParameter("totalOrder", params.getTotalOrder());
		query.setParameter("fechaIn", params.getDateInFormatted());
		query.setParameter("fechaEnd", params.getDateEndFormatted());
		query.setParameter("idOrder", params.getIdOrder());
		query.setParameter("orderStatus", params.getOrderStatus());

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

	@SuppressWarnings("unchecked")
	@Override
	public Long countMonitorComprasByCriterio(ComprasDetallePagoRequestDTO params, Pageable paging) {
		TypedQuery<Long> query;
		Long response = null;
		
		try {
			
			String sqlQuery = new String("""
				SELECT COUNT(*)
					from Orders O 
					join Clients C on O.idClient = C.idClient
					left join MpBancoChile CH on O.idOrder = CH.idOrder
					left join MpBancoEstado E on E.idOrder = O.idOrder
					left join MpBancoSantander S on S.idOrder = O.idOrder
					left join MpDeposito D on D.idOrder = O.idOrder
					left join MpBancoBci B on B.idOrder = O.idOrder
					left join Transbank T on T.idOrder = O.idOrder
					left join Audits a on a.modifyLogin = cast(O.idOrder as text)
					WHERE
								(:upi IS NULL OR C.upi ILIKE :upi) AND
								(:legalName IS NULL OR C.legalName ILIKE :legalName) AND
								(:payType IS NULL OR O.payType ILIKE :payType) AND
								(:totalOrder IS NULL OR O.totalOrder = :totalOrder) AND
								(CAST(:fechaIn as text) IS NULL or O.crtDate >= CAST(CAST(:fechaIn as text) as date)) AND 
								(CAST(:fechaEnd as text) IS NULL or O.crtDate <= CAST(CAST(:fechaEnd as text) as date)) AND
								(:idOrder IS NULL OR O.idOrder = :idOrder) AND
								(:orderStatus IS NULL OR O.orderStatus = :orderStatus)
					""");
		
		if(paging.getSort() != null)
			sqlQuery += Functions.getSortString(paging.getSort(), MonitorComprasDTO.class);
		
		query = (TypedQuery<Long>) entityManager.createQuery(sqlQuery);
		
		
		query.setParameter("upi", params.getUpi() == null ? null : "%"+params.getUpi()+"%");
		query.setParameter("legalName", params.getLegalName() == null ? null : "%"+params.getUpi()+"%");
		query.setParameter("payType", params.getPayType() == null ? null : "%"+params.getPayType()+"%");
		query.setParameter("totalOrder", params.getTotalOrder());
		query.setParameter("fechaIn", params.getDateInFormatted());
		query.setParameter("fechaEnd", params.getDateEndFormatted());
		query.setParameter("idOrder", params.getIdOrder());
		query.setParameter("orderStatus", params.getOrderStatus());

		query.setFirstResult(paging.isUnpaged() ? 0 : (int) paging.getOffset());
		query.setMaxResults(paging.isUnpaged() ? Integer.MAX_VALUE : paging.getPageSize());
		
		response  = query.getSingleResult();
			
		}catch(Exception ex) {
			log.error("ERROR " + ex.getMessage());
			ex.printStackTrace();
		}
			
		log.debug("response: "+response);
		return response;
	}


	
	
	
	
	
	
	

}
