package com.evertecinc.b2c.enex.client.dao.extended.impl;

import java.util.List;

import org.hibernate.query.Query;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.evertecinc.b2c.enex.classes.utils.db.DbUtils;
import com.evertecinc.b2c.enex.client.dao.extended.IOrdersRepo;
import com.evertecinc.b2c.enex.client.model.dto.OrderItemDTO;
import com.evertecinc.b2c.enex.client.model.dto2.MonitorComprasDTO;
import com.evertecinc.b2c.enex.client.model.dto2.MonitorComprasExpDetalleOrdenes;
import com.evertecinc.b2c.enex.client.model.dto2.MonitorPagoDTO;
import com.evertecinc.b2c.enex.client.model.dto2.ReporteClienteVentaDTO;
import com.evertecinc.b2c.enex.client.model.dto2.ShopCartDTO;
import com.evertecinc.b2c.enex.client.model.dto2.requests.ListShopCartRequestDTO;
import com.evertecinc.b2c.enex.client.model.dto2.requests.MonitorComprasRequestDTO;
import com.evertecinc.b2c.enex.client.model.dto2.requests.MonitorPagoRequestDTO;
import com.evertecinc.b2c.enex.client.model.dto2.requests.ReporteClienteVentaRequestDTO;
import com.evertecinc.b2c.enex.utils.functions.Functions;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Repository
@RequiredArgsConstructor
@Slf4j
public class OrdersRepoImpl implements IOrdersRepo{
	
	
	@PersistenceContext
	private final EntityManager entityManager;

	@Override
	@SuppressWarnings("unchecked")
	public List<MonitorComprasDTO> getMonitorComprasByCriterio(MonitorComprasRequestDTO params, Pageable paging) {
		
		TypedQuery<MonitorComprasDTO> query;
		List<MonitorComprasDTO> response = null;
		
		try {
			String sqlQuery = new String("""
					    SELECT
					        O.idOrder AS idOrder,
					        O.payDate AS payDate,
					        C.upi AS upi,
					        C.legalName AS legalName,
					        O.totalOrder AS totalOrder,
					        O.orderStatus AS orderStatus,
					        C.idClient AS idClient,
					        O.payType AS payType,

					        CASE O.payType
					            WHEN 'C' THEN CH.mpoutNumcomp
					            WHEN 'E' THEN E.resultadoTrxMpago
					            WHEN 'T' THEN T.authorizationCode
					            WHEN 'S' THEN S.mpoutFechatrx
					            WHEN 'D' THEN D.comprobante
					            WHEN 'I' THEN '0'
					            WHEN 'B' THEN '0'
					        END AS numComprobante,

					        CASE O.payType
					            WHEN 'C' THEN CH.mpoutCodret
					            WHEN 'E' THEN E.resultadoMpago
					            WHEN 'T' THEN T.returnCode
					            WHEN 'S' THEN S.mpoutCodret
					            WHEN 'D' THEN '0'
					            WHEN 'I' THEN B.estado
					            WHEN 'B' THEN B.estado
					        END AS respuesta,
					        O.crtDate AS crtDate,
					        D.banco AS bancoDeposito

					    FROM Orders O
					    JOIN Clients C ON O.idClient = C.idClient
					    LEFT JOIN MpBancoChile CH ON O.idOrder = CH.idOrder
					    LEFT JOIN MpBancoEstado E ON E.idOrder = O.idOrder
					    LEFT JOIN MpBancoSantander S ON S.idOrder = O.idOrder
					    LEFT JOIN MpDeposito D ON D.idOrder = O.idOrder
					    LEFT JOIN MpBancoBci B ON B.idOrder = O.idOrder
					    LEFT JOIN Transbank T ON T.idOrder = O.idOrder

					    WHERE
							    (:upi IS NULL OR C.upi ILIKE :upi) AND
							    (:legalName IS NULL OR C.legalName ILIKE :legalName) AND
							    (:payType IS NULL OR O.payType ILIKE :payType) AND
							    (:totalOrder IS NULL OR O.totalOrder = :totalOrder) AND
							    (:idOrder IS NULL OR O.idOrder = :idOrder) AND
							    (:orderStatus IS NULL OR O.orderStatus ILIKE :orderStatus) AND
							    (CAST(:fechaIn as text) IS NULL or O.crtDate >= CAST(CAST(:fechaIn as text) as date)) AND 
					    		(CAST(:fechaEnd as text) IS NULL or O.crtDate <= CAST(CAST(:fechaEnd as text) as date)) 
					""");
			
		
		if(paging.getSort() != null)
			sqlQuery += Functions.getSortString(paging.getSort(), MonitorComprasDTO.class);
		
		query = entityManager.createQuery(sqlQuery.toString(), MonitorComprasDTO.class)
		.unwrap(Query.class)
		.setTupleTransformer((tuple, aliases) -> DbUtils.objFromFields(tuple, aliases, MonitorComprasDTO.class));
		
		query.setParameter("upi", params.getUpi());
		query.setParameter("legalName", params.getLegalName());
		query.setParameter("payType", params.getPayType());
		query.setParameter("totalOrder", params.getTotalOrder());
		query.setParameter("idOrder", params.getIdOrder());
		query.setParameter("orderStatus", params.getOrderStatus());
		query.setParameter("fechaIn", params.getDateInFormatted());
		query.setParameter("fechaEnd", params.getDateEndFormatted());
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

	@Override
	@SuppressWarnings("unchecked")
	public Long countMonitorComprasByCriterio(MonitorComprasRequestDTO params, Pageable paging) {
		TypedQuery<Long> query;
		Long response = null;
		
		try {
			String sqlQuery = new String("""
			    SELECT COUNT(*) FROM Orders O
			    JOIN Clients C ON O.idClient = C.idClient
			    LEFT JOIN MpBancoChile CH ON O.idOrder = CH.idOrder
			    LEFT JOIN MpBancoEstado E ON E.idOrder = O.idOrder
			    LEFT JOIN MpBancoSantander S ON S.idOrder = O.idOrder
			    LEFT JOIN MpDeposito D ON D.idOrder = O.idOrder
			    LEFT JOIN MpBancoBci B ON B.idOrder = O.idOrder
			    LEFT JOIN Transbank T ON T.idOrder = O.idOrder
			
			    WHERE 
					    (:upi IS NULL OR C.upi ILIKE :upi) AND
					    (:legalName IS NULL OR C.legalName ILIKE :legalName) AND
					    (:payType IS NULL OR O.payType ILIKE :payType) AND
					    (:totalOrder IS NULL OR O.totalOrder = :totalOrder) AND
					    (:idOrder IS NULL OR O.idOrder = :idOrder) AND
					    (:orderStatus IS NULL OR O.orderStatus ILIKE :orderStatus) AND
					    (CAST(:fechaIn as text) IS NULL or O.crtDate >= CAST(CAST(:fechaIn as text) as date)) AND 
					    (CAST(:fechaEnd as text) IS NULL or O.crtDate <= CAST(CAST(:fechaEnd as text) as date))
			""") ;
		
		query = (TypedQuery<Long>) entityManager.createQuery(sqlQuery);
		
		query.setParameter("upi", params.getUpi() == null ? null : '%' + params.getUpi() + '%');
		query.setParameter("legalName", params.getLegalName() == null ? null : '%' + params.getLegalName() + '%');
		query.setParameter("payType", params.getPayType());
		query.setParameter("totalOrder", params.getTotalOrder());
		query.setParameter("idOrder", params.getIdOrder());
		query.setParameter("orderStatus", params.getOrderStatus() == null ? null : '%' + params.getOrderStatus() + '%');
		query.setParameter("fechaIn", params.getDateInFormatted());
		query.setParameter("fechaEnd", params.getDateEndFormatted());
		
		
		response  = query.getSingleResult();
		
	}catch(Exception ex) {
		log.error("ERROR " + ex.getMessage());
		ex.printStackTrace();
	}
		
	log.debug("response: "+response);
	return response;
	
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MonitorComprasExpDetalleOrdenes> getExpMonitorComprasDetalleOrdenes(MonitorComprasRequestDTO params,
			Pageable paging) {
		
		TypedQuery<MonitorComprasExpDetalleOrdenes> query;
		List<MonitorComprasExpDetalleOrdenes> response = null;
		
		try {
			String sqlQuery = new String("""
					SELECT 
						o.idOrder as idOrder,
						c.idClient as idClient,
						o.payDate as payDate,
						c.upi as upi,
						c.legalName as legalName,
						o.totalOrder as totalOrder,
						o.payType as payType,
						vv.nickname as nickname,
						ccc.cardnum as cardnum,
						CASE o.payType
							WHEN 'C' THEN  CH.mpoutNumcomp
							WHEN 'E' THEN  E.resultadoTrxMpago
							WHEN 'T' THEN  T.authorizationCode
							WHEN 'S' THEN  S.mpoutFechatrx
							WHEN 'D' THEN  D.comprobante
							WHEN 'I' THEN  '0'
							WHEN 'B' THEN  '0'
						END AS numComprobante,
						CASE 
							WHEN i.idCard IS NULL THEN 'departamento'
							ELSE 'tarjeta'
						END AS tipoCompra,
						depto.name as nombreDepto,
						i.productCode as producto,
						i.amount as montoPorItem,
						i.documentType as tipoDocumento,
						CASE o.payType
							WHEN 'C' THEN  CH.mpoutCodret
							WHEN 'E' THEN  E.resultadoMpago
							WHEN 'T' THEN  T.returnCode
							WHEN 'S' THEN  S.mpoutCodret
							WHEN 'D' THEN  '0'
							WHEN 'I' THEN  B.estado
							WHEN 'B' THEN  B.estado
						END AS respuesta,
						D.banco as bancoDeposito
							from Orders o
							join OrderItems i on o.idOrder = i.idOrder
							left join Vehicles vv on i.idVehicle = vv.idVehicle
							left join Card ccc on  i.idCard = ccc.idCard
							join Departments depto on i.idDepartment = depto.idDepartment
							join Products p on p.productCode = i.productCode
							join Clients c on o.idClient = c.idClient
							left join MpBancoChile CH on o.idOrder = CH.idOrder
							left join MpBancoEstado E on E.idOrder = o.idOrder
							left join MpBancoSantander S on S.idOrder = o.idOrder
							left join MpDeposito D on D.idOrder = o.idOrder
							left join MpBancoBci B on B.idOrder = o.idOrder
							left join Transbank T on T.idOrder = o.idOrder
							
							WHERE
								(:upi IS NULL OR c.upi ILIKE :upi) AND
								(:legalName IS NULL OR c.legalName ILIKE :legalName) AND
								(:totalOrder IS NULL OR o.totalOrder = :totalOrder) AND
								(CAST(:fechaIn as text) IS NULL or o.crtDate >= CAST(CAST(:fechaIn as text) as date)) AND 
					    		(CAST(:fechaEnd as text) IS NULL or o.crtDate <= CAST(CAST(:fechaEnd as text) as date)) AND
								(:idOrder IS NULL OR o.idOrder = :idOrder) AND
								(:payType IS NULL OR o.payType ILIKE :payType) AND
								(:numComprobante IS NULL OR
									(o.payType = 'C' AND CH.mpoutNumcomp = :numComprobante) OR
									(o.payType = 'E' AND E.resultadoTrxMpago = :numComprobante) OR
									(o.payType = 'T' AND T.authorizationCode = :numComprobante) OR
									(o.payType = 'S' AND S.mpoutFechatrx = :numComprobante) OR
									(o.payType = 'D' AND D.comprobante = :numComprobante) OR
									(o.payType = 'I' AND '0' = :numComprobante) OR
									(o.payType = 'B' AND '0' = :numComprobante)
								)
					""");
			
			 
			
		
		if(paging.getSort() != null)
			sqlQuery += Functions.getSortString(paging.getSort(), MonitorComprasExpDetalleOrdenes.class);
		
		query = entityManager.createQuery(sqlQuery.toString(), MonitorComprasExpDetalleOrdenes.class)
		.unwrap(Query.class)
		.setTupleTransformer((tuple, aliases) -> DbUtils.objFromFields(tuple, aliases, MonitorComprasExpDetalleOrdenes.class));
		
		query.setParameter("upi", params.getUpi() == null ? null : "%"+params.getUpi()+"%");
		query.setParameter("legalName", params.getLegalName() == null ? null : "%"+params.getLegalName()+"%");
		query.setParameter("payType", params.getPayType() == null ? null : "%"+params.getPayType()+"%");
		query.setParameter("totalOrder", params.getTotalOrder());
		query.setParameter("idOrder", params.getIdOrder());
		query.setParameter("fechaIn", params.getDateInFormatted());
		query.setParameter("fechaEnd", params.getDateEndFormatted());
		query.setParameter("numComprobante",
				params.getNumComprobante() == null || params.getPayType() == null ? null : params.getNumComprobante());

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
	public Long countExpMonitorComprasDetalleOrdenes(MonitorComprasRequestDTO params, Pageable paging) {
		TypedQuery<Long> query;
		Long response = null;
		
		try {
			String sqlQuery = new String("""
					SELECT COUNT(*)
							from Orders o
							join OrderItems i on o.idOrder = i.idOrder
							left join Vehicles vv on i.idVehicle = vv.idVehicle
							left join Card ccc on  i.idCard = ccc.idCard
							join Departments depto on i.idDepartment = depto.idDepartment
							join Products p on p.productCode = i.productCode
							join Clients c on o.idClient = c.idClient
							left join MpBancoChile CH on o.idOrder = CH.idOrder
							left join MpBancoEstado E on E.idOrder = o.idOrder
							left join MpBancoSantander S on S.idOrder = o.idOrder
							left join MpDeposito D on D.idOrder = o.idOrder
							left join MpBancoBci B on B.idOrder = o.idOrder
							left join Transbank T on T.idOrder = o.idOrder
							
							WHERE
								(:upi IS NULL OR c.upi ILIKE :upi) AND
								(:legalName IS NULL OR c.legalName ILIKE :legalName) AND
								(:totalOrder IS NULL OR o.totalOrder = :totalOrder) AND
								(CAST(:fechaIn as text) IS NULL or o.crtDate >= CAST(CAST(:fechaIn as text) as date)) AND 
					    		(CAST(:fechaEnd as text) IS NULL or o.crtDate <= CAST(CAST(:fechaEnd as text) as date)) AND
								(:idOrder IS NULL OR o.idOrder = :idOrder) AND
								(:payType IS NULL OR o.payType ILIKE :payType) AND
								(:numComprobante IS NULL OR
									(o.payType = 'C' AND CH.mpoutNumcomp = :numComprobante) OR
									(o.payType = 'E' AND E.resultadoTrxMpago = :numComprobante) OR
									(o.payType = 'T' AND T.authorizationCode = :numComprobante) OR
									(o.payType = 'S' AND S.mpoutFechatrx = :numComprobante) OR
									(o.payType = 'D' AND D.comprobante = :numComprobante) OR
									(o.payType = 'I' AND '0' = :numComprobante) OR
									(o.payType = 'B' AND '0' = :numComprobante)
								)
					""");
			
			 
			
		
			query = (TypedQuery<Long>) entityManager.createQuery(sqlQuery);
			
		query.setParameter("upi", params.getUpi());
		query.setParameter("legalName", params.getLegalName());
		query.setParameter("payType", params.getPayType());
		query.setParameter("totalOrder", params.getTotalOrder());
		query.setParameter("idOrder", params.getIdOrder());
		query.setParameter("fechaIn", params.getDateInFormatted());
		query.setParameter("fechaEnd", params.getDateEndFormatted());
		query.setParameter("numComprobante",
				params.getNumComprobante() == null || params.getPayType() == null ? null : params.getNumComprobante());

		response  = query.getSingleResult();
		
		
	}catch(Exception ex) {
		log.error("ERROR " + ex.getMessage());
		ex.printStackTrace();
	}
	
	log.debug("response: "+response);
	return response;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MonitorPagoDTO> getListPagosMonitor(MonitorPagoRequestDTO params, Pageable paging) {
		TypedQuery<MonitorPagoDTO> query;
		List<MonitorPagoDTO> response = null;
		
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
						O.payType as payType,
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
						D.banco as bancoDeposito
						from Orders O 
						join PayDetail pd on O.idOrder = pd.idOrder and pd.idOrder not in 
						(
						select pay.idOrder from PayDetail pay where pay.status in ('Aprobado','Rechazado','Descartado')
						)
						join Clients C on O.idClient = C.idClient
						left join MpBancoChile CH on O.idOrder = CH.idOrder
						left join MpBancoEstado E on E.idOrder = O.idOrder
						left join MpBancoSantander S on S.idOrder = O.idOrder
						left join MpDeposito D on D.idOrder = O.idOrder
						left join MpBancoBci B on B.idOrder = O.idOrder
						left join Transbank T on T.idOrder = O.idOrder
						
						where
							(:upi IS NULL OR C.upi ILIKE :upi) AND
							(:legalName IS NULL OR C.legalName ILIKE :legalName) AND
							(:payType IS NULL OR O.payType ILIKE :payType) AND
						(:totalOrder IS NULL OR O.totalOrder = :totalOrder) AND
						(:orderStatus IS NULL OR O.orderStatus ILIKE :orderStatus) AND
						(:payType IS NULL OR O.payType ILIKE :payType) AND
						(CAST(:fechaIn as text) IS NULL or O.crtDate >= CAST(CAST(:fechaIn as text) as date)) AND 
					    (CAST(:fechaEnd as text) IS NULL or O.crtDate <= CAST(CAST(:fechaEnd as text) as date)) AND
							(:listaTipoElementos IS NULL 
								OR pd.idOrder NOT IN (
									SELECT pay.idOrder 
									FROM PayDetail pay 
									WHERE pay.status IN(:listaTipoElementos)
								)
							)
							
						GROUP BY 
							O.idOrder, 
							O.orderStatus, 
							C.idClient, 
							O.payDate, 
							C.upi, 
							C.legalName, 
							O.totalOrder, 
							O.payType, 
							CH.mpoutNumcomp, 
							E.resultadoTrxMpago, 
							T.authorizationCode, 
							S.mpoutFechatrx, 
							D.comprobante, 
							B.estado, 
							CH.mpoutCodret, 
							E.resultadoMpago, 
							T.returnCode, 
							S.mpoutCodret, 
							O.crtDate, 
							D.banco
						HAVING
						(:idOrder IS NULL OR O.idOrder = :idOrder) 
						
					""");
		
		if(paging.getSort() != null)
			sqlQuery += Functions.getSortString(paging.getSort(), MonitorPagoDTO.class);
		
		query = entityManager.createQuery(sqlQuery.toString(), MonitorPagoDTO.class)
		.unwrap(Query.class)
		.setTupleTransformer((tuple, aliases) -> DbUtils.objFromFields(tuple, aliases, MonitorPagoDTO.class));
		
		query.setParameter("upi", params.getUpi() == null ? null : "%"+params.getUpi()+"%");
		query.setParameter("legalName", params.getLegalName() == null ? null : "%"+params.getLegalName()+"%");
		query.setParameter("payType", params.getPayType() == null ? null : "%"+params.getPayType()+"%");
		query.setParameter("totalOrder", params.getTotalOrder());
		query.setParameter("idOrder", params.getIdOrder());
		query.setParameter("orderStatus", params.getOrderStatus());
		query.setParameter("fechaIn", params.getDateFirst());
		query.setParameter("fechaEnd", params.getDateEnd());
		query.setParameter("listaTipoElementos", params.getListaTipoElementos());

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
	public Long countListPagosMonitor(MonitorPagoRequestDTO params, Pageable paging) {
		TypedQuery<Long> query;
		Long response = null;
		
		try {
			String sqlQuery = new String("""
				SELECT COUNT(*) FROM
				(
					SELECT   
						O.idOrder as idOrder, 
						O.orderStatus as orderStatus,
						C.idClient as idClient,
						O.payDate  as payDate, 
						C.upi  as upi, 
						C.legalName as legalName, 
						O.totalOrder as totalOrder, 
						O.payType as payType,
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
						D.banco as bancoDeposito
						from Orders O 
						join PayDetail pd on O.idOrder = pd.idOrder and pd.idOrder not in 
						(
						select pay.idOrder from PayDetail pay where pay.status in ('Aprobado','Rechazado','Descartado')
						)
						join Clients C on O.idClient = C.idClient
						left join MpBancoChile CH on O.idOrder = CH.idOrder
						left join MpBancoEstado E on E.idOrder = O.idOrder
						left join MpBancoSantander S on S.idOrder = O.idOrder
						left join MpDeposito D on D.idOrder = O.idOrder
						left join MpBancoBci B on B.idOrder = O.idOrder
						left join Transbank T on T.idOrder = O.idOrder
						
						where
							(:upi IS NULL OR C.upi ILIKE :upi) AND
							(:legalName IS NULL OR C.legalName ILIKE :legalName) AND
							(:payType IS NULL OR O.payType ILIKE :payType) AND
						(:totalOrder IS NULL OR O.totalOrder = :totalOrder) AND
						(:orderStatus IS NULL OR O.orderStatus ILIKE :orderStatus) AND
						(:payType IS NULL OR O.payType ILIKE :payType) AND
						(CAST(:fechaIn as text) IS NULL or O.crtDate >= CAST(CAST(:fechaIn as text) as date)) AND 
					    (CAST(:fechaEnd as text) IS NULL or O.crtDate <= CAST(CAST(:fechaEnd as text) as date)) AND
							(:listaTipoElementos IS NULL 
								OR pd.idOrder NOT IN (
									SELECT pay.idOrder 
									FROM PayDetail pay 
									WHERE pay.status IN(:listaTipoElementos)
								)
							)
							
						GROUP BY 
							O.idOrder, 
							O.orderStatus, 
							C.idClient, 
							O.payDate, 
							C.upi, 
							C.legalName, 
							O.totalOrder, 
							O.payType, 
							CH.mpoutNumcomp, 
							E.resultadoTrxMpago, 
							T.authorizationCode, 
							S.mpoutFechatrx, 
							D.comprobante, 
							B.estado, 
							CH.mpoutCodret, 
							E.resultadoMpago, 
							T.returnCode, 
							S.mpoutCodret, 
							O.crtDate, 
							D.banco
						HAVING
						(:idOrder IS NULL OR O.idOrder = :idOrder) 
					) AS datosPaginado
					""");
			
			query = (TypedQuery<Long>) entityManager.createQuery(sqlQuery);
			
			query.setParameter("upi", params.getUpi() == null ? null : "%"+params.getUpi()+"%");
			query.setParameter("legalName", params.getLegalName() == null ? null : "%"+params.getLegalName()+"%");
			query.setParameter("payType", params.getPayType() == null ? null : "%"+params.getPayType()+"%");
			query.setParameter("totalOrder", params.getTotalOrder());
			query.setParameter("idOrder", params.getIdOrder());
			query.setParameter("orderStatus", params.getOrderStatus());
			query.setParameter("fechaIn", params.getDateFirst());
			query.setParameter("fechaEnd", params.getDateEnd());
			query.setParameter("listaTipoElementos", params.getListaTipoElementos());

		response  = query.getSingleResult();
		
		
	}catch(Exception ex) {
		log.error("ERROR " + ex.getMessage());
		ex.printStackTrace();
	}
	
	log.debug("response: "+response);
	return response;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ReporteClienteVentaDTO> getListadoClienteVentas(ReporteClienteVentaRequestDTO params,
			Pageable paging) {
		TypedQuery<ReporteClienteVentaDTO> query;
		List<ReporteClienteVentaDTO> response = null;
		
		try {
			String sqlQuery = new String("""
					SELECT 
            c.idClient AS idClient,
            c.upi AS upi,
            c.legalName AS legalName,
            c.dateIns AS fechaCreacionCliente,
            o.totalOrder AS montoUltimaCompra,
            o.crtDate AS fechaUltimaCompra,
            c.clientType AS tipoCliente,
            (select sum(o2.totalOrder) from Orders o2 where o2.idClient = c.idClient and o2.orderStatus ILIKE 'X') AS montoTotalAcumulado,
            (select count(cd.idCard) from Card cd where cd.client.idClient = c.idClient and cd.cardStatus ILIKE 'A') AS tarjetasActivas,
            (select count(cd2.idCard) from Card cd2 where cd2.client.idClient = c.idClient and cd2.cardStatus <> 'E') AS totalTarjetas

        from Clients c
        join Orders o on o.idClient = c.idClient
        where o.orderStatus ILIKE 'X'
        and (:upi IS NULL OR c.upi ILIKE :upi)
        and (:legalName IS NULL OR c.legalName ILIKE :legalName)
        and (:clientType IS NULL OR c.clientType ILIKE :clientType)
					""");
		
		if(paging.getSort() != null)
			sqlQuery += Functions.getSortString(paging.getSort(), ReporteClienteVentaDTO.class);
		
		query = entityManager.createQuery(sqlQuery.toString(), ReporteClienteVentaDTO.class)
		.unwrap(Query.class)
		.setTupleTransformer((tuple, aliases) -> DbUtils.objFromFields(tuple, aliases, ReporteClienteVentaDTO.class));
		
		query.setParameter("upi", params.getUpi() == null ? null : "%"+params.getUpi()+"%");
		query.setParameter("legalName", params.getLegalName() == null ? null : "%"+params.getLegalName()+"%");
		query.setParameter("clientType", params.getClientType() == null ? null : "%"+params.getClientType()+"%");
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
	public Long countListadoClienteVentas(ReporteClienteVentaRequestDTO params, Pageable paging) {
		TypedQuery<Long> query;
		Long response = null;
		
		try {
			String sqlQuery = new String("""
			    SELECT COUNT(*)
			    from Clients c
        join Orders o on o.idClient = c.idClient
        where o.orderStatus ILIKE 'X'
        and (:upi IS NULL OR c.upi ILIKE :upi)
        and (:legalName IS NULL OR c.legalName ILIKE :legalName)
        and (:clientType IS NULL OR c.clientType ILIKE :clientType)
			""") ;
		
		query = (TypedQuery<Long>) entityManager.createQuery(sqlQuery);
		
		query.setParameter("upi", params.getUpi() == null ? null : "%"+params.getUpi()+"%");
		query.setParameter("legalName", params.getLegalName() == null ? null : "%"+params.getLegalName()+"%");
		query.setParameter("clientType", params.getClientType() == null ? null : "%"+params.getClientType()+"%");
		
		response  = query.getSingleResult();
		
	}catch(Exception ex) {
		log.error("ERROR " + ex.getMessage());
		ex.printStackTrace();
	}
		
	log.debug("response: "+response);
	return response;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderItemDTO> getListOrderItemConfirm(Long idOrder, Pageable paging) {
		TypedQuery<OrderItemDTO> query;
		List<OrderItemDTO> response = null;
		
		try {
			String sqlQuery = new String("""
					SELECT
						i.idOrderItem as idOrderItem,
						i.idOrder as idOrder,
						i.idClient as idClient,
						i.idClientUser as idUser,
						i.amount as amount,
						i.documentType as documentType,
						i.idCard as idCard,
						i.idDepartment as idDepartment,
						d.name as nombreDepto,
						i.productCode as productCode,
						p.name as nombreProducto,
						i.idVehicle as idVehicle,
						v.nickname as nickname,
						v.plate as patente,
						u.name as name,
						u.firstName as firstName,
						i.idDocumento as idDocumento,
						i.numeroDocumento as numeroDocumento,
						i.tipoDocumento as tipoDocumento
					  FROM
						OrderItems i
					  LEFT JOIN Departments d ON i.idDepartment = d.idDepartment
					  LEFT JOIN Products p ON i.productCode = p.productCode
					  JOIN Orders   o ON o.idOrder= i.idOrder
					  JOIN Users u ON u.idUser = o.idUser
					  LEFT JOIN VehicleCard vc ON i.idCard = vc.vehicle.idCard
					  LEFT JOIN Vehicles v ON i.idVehicle = v.idVehicle
					  WHERE 
					  	(:idOrder IS NULL OR i.idOrder = :idOrder)
					  AND  o.order_status= "X"
					  ORDER BY i.documentType
					""");
		
		if(paging.getSort() != null)
			sqlQuery += Functions.getSortString(paging.getSort(), OrderItemDTO.class);
		
		query = entityManager.createQuery(sqlQuery.toString(), OrderItemDTO.class)
		.unwrap(Query.class)
		.setTupleTransformer((tuple, aliases) -> DbUtils.objFromFields(tuple, aliases, OrderItemDTO.class));
		
		query.setParameter("idOrder", idOrder == null ? null : idOrder);
		
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
	public List<ShopCartDTO> getListShopCart(ListShopCartRequestDTO params, Pageable paging) {
		TypedQuery<ShopCartDTO> query;
		List<ShopCartDTO> response = null;
		
		try {
			String sqlQuery = new String("""
					SELECT
						S.idShopCart 				as idShopCart,
						S.client.idClient 			as idClient,
						S.user.idUser 				as idUser,
						S.product.productCode 		as productCode,
						P.name 						as nombreProducto,
						S.department.idDepartment 	as idDepartment,
						D.name  					as nombreDepto,
						S.card.idCard 				as idCard,
						S.amount 					as amount,
						S.documentType 				as documentType,
						S.idDocumento 				as idDocumento,
						S.numeroDocumento 			as numDocumento,
						S.tipoDocumento 			as tipoDocumento,
						V.nickname 					as nickname,
						V.plate 					as patente
					FROM ShopCart S
					LEFT JOIN Departments D ON S.department.idDepartment = D.idDepartment
					LEFT JOIN Products P ON S.product.productCode = P.productCode
					LEFT JOIN VehicleCard VC ON S.card.idCard = VC.card.idCard
					LEFT JOIN Vehicles V ON VC.vehicle.idVehicle = V.idVehicle
					WHERE 
						S.user.idUser = :idUser
						AND S.client.idClient = :idClient
					ORDER BY S.documentType
					
					
					""");
			
		
		if(paging.getSort() != null)
			sqlQuery += Functions.getSortString(paging.getSort(), ShopCartDTO.class);
		
		query = entityManager.createQuery(sqlQuery.toString(), ShopCartDTO.class)
		.unwrap(Query.class)
		.setTupleTransformer((tuple, aliases) -> DbUtils.objFromFields(tuple, aliases, ShopCartDTO.class));
		
		query.setParameter("idUser", params.getIdUser());
		query.setParameter("idClient", params.getIdClient());
		
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
	public List<OrderItemDTO> getListOrderItem(Long idOrder) {
		TypedQuery<OrderItemDTO> query;
		List<OrderItemDTO> response = null;
		
		try {
			String sqlQuery = new String("""
					SELECT
						i.idOrderItem as idOrderItem,
						i.idOrder as idOrder,
						i.idClient as idClient,
						i.idClientUser as idUser,
						i.amount as amount,
						i.documentType as documentType,
						i.idCard as idCard,
						i.idDepartment as idDepartment,
						d.name as nombreDepto,
						i.productCode as productCode,
						p.name as nombreProducto,
						i.idVehicle as idVehicle,
						v.nickname as nickname,
						v.plate as patente,
						i.idDocumento as idDocumento,
						i.numeroDocumento as numeroDocumento,
						i.tipoDocumento as tipoDocumento
					  FROM
						OrderItems i
					  LEFT JOIN Departments d ON i.idDepartment = d.idDepartment
					  LEFT JOIN Products p ON i.productCode = p.productCode
					  LEFT JOIN VehicleCard vc ON i.idCard = vc.vehicle.idCard
					  LEFT JOIN Vehicles v ON i.idVehicle = v.idVehicle
					  WHERE i.idOrder= :idOrder
					  ORDER BY i.documentType
					
					""");
			
		
		
		query = entityManager.createQuery(sqlQuery.toString(), OrderItemDTO.class)
		.unwrap(Query.class)
		.setTupleTransformer((tuple, aliases) -> DbUtils.objFromFields(tuple, aliases, OrderItemDTO.class));
		
		query.setParameter("idOrder", idOrder);
		
		query.setFirstResult(0);
		query.setMaxResults(Integer.MAX_VALUE);
		
		response  = query.getResultList();
		
	}catch(Exception ex) {
		log.error("ERROR " + ex.getMessage());
		ex.printStackTrace();
	}
	
	log.debug("response: "+response);
	return response;
	}
	
}
