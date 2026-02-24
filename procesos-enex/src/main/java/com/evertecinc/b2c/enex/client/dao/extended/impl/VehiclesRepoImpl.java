package com.evertecinc.b2c.enex.client.dao.extended.impl;

import java.util.List;

import org.hibernate.query.Query;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.evertecinc.b2c.enex.classes.utils.db.DbUtils;
import com.evertecinc.b2c.enex.client.dao.extended.IVehiclesRepo;
import com.evertecinc.b2c.enex.client.model.dto.MonitorCardDTO;
import com.evertecinc.b2c.enex.client.model.dto2.MonitorVehiclesDTO;
import com.evertecinc.b2c.enex.client.model.dto2.VehiclesJsonDTO;
import com.evertecinc.b2c.enex.client.model.dto2.requests.ListVehiclesJsonRequestDTO;
import com.evertecinc.b2c.enex.client.model.dto2.requests.MonitorVehiculosRequestDTO;
import com.evertecinc.b2c.enex.utils.functions.Functions;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Repository
@RequiredArgsConstructor
@Slf4j
public class VehiclesRepoImpl implements IVehiclesRepo{
	
	@PersistenceContext
	private final EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	@Override
	public Long getCountListPatentesClientes(MonitorVehiculosRequestDTO params, Pageable paging) {
		
		TypedQuery<Long> query;
		Long response = null;
		
		
		try {
			
			String sqlQuery = """
					   SELECT COUNT(*)
				    FROM (
				        SELECT plate AS plate,
				               vehicleStatus AS vehicleStatus,
				               name AS name,
				               upi AS upi,
				               clientType AS clientType,
				               nickname AS nickname,
				               idClient AS idClient,
				               idVehicle AS idVehicle,
				               idCard AS idCard,
				               productCode AS productCode,
				               cardnum AS cardnum,
				               cardStatus AS cardStatus,
				               controlTotal AS controlTotal,
				               datapass AS datapass,
				               posicion AS ctPosition,
				               documentType AS documentType,
				               nombreDepto AS nombreDepto,
				               tipoVehiculo AS tipoVehiculo,
				               nombreProducto AS nombreProducto
				        FROM (
				            SELECT DISTINCT V.plate AS plate,
				                   V.vehicleStatus AS vehicleStatus,
				                   C.name AS name,
				                   C.upi AS upi,
				                   C.clientType AS clientType,
				                   V.nickname AS nickname,
				                   C.idClient AS idClient,
				                   V.idVehicle AS idVehicle,
				                   CC.idCard AS idCard,
				                   PP.productCode AS productCode,
				                   CC.cardnum AS cardnum,
				                   CC.cardStatus AS cardStatus,
				                   V.controlTotal AS controlTotal,
				                   V.datapass AS datapass,
				                   CC.ctPosition AS posicion,
				                   V.documentType AS documentType,
				                   D.name AS nombreDepto,
				                   VT.name AS tipoVehiculo,
				                   PP.name AS nombreProducto
				            FROM Vehicles V
				            JOIN Clients C ON V.client.idClient = C.idClient
				            LEFT JOIN Products PP ON V.product.productCode = PP.productCode
				            LEFT JOIN VehicleCard VC ON VC.vehicle.idVehicle = V.idVehicle
				            LEFT JOIN Card CC ON VC.card.idCard = CC.idCard
				            LEFT JOIN Departments D ON D.idDepartment = V.department.idDepartment
				            LEFT JOIN VehicleTypes VT ON VT.vehicleTypeCode = V.vehicleType.vehicleTypeCode
				            WHERE C.clientStatus <> 'E'
				              AND V.vehicleStatus <> 'E'
				              AND CC.cardStatus <> 'E'
				              AND PP.productStatus <> 'E'
				              AND D.departmentStatus <> 'E'
				              AND (:upi IS NULL OR C.upi ILIKE :upi)
				              AND (:idClient IS NULL OR C.idClient = :idClient)
				              AND (:legalName IS NULL OR C.legalName ILIKE :legalName)
				              AND (:plate IS NULL OR V.plate ILIKE :plate)
				              AND (:nickname IS NULL OR V.nickname ILIKE :nickname)
				              AND (:vehicleStatus IS NULL OR V.vehicleStatus ILIKE :vehicleStatus)
				              AND (:clientType IS NULL OR C.clientType ILIKE :clientType)
				              AND (:documentType IS NULL OR V.documentType ILIKE :documentType)
				              AND (:controlTotal IS NULL OR V.controlTotal = :controlTotal)
				              AND (:codeTypeVehicle IS NULL OR V.vehicleType.vehicleTypeCode = :codeTypeVehicle)
				              AND (:idDepartment IS NULL OR D.idDepartment = :idDepartment)
				        ) datos
				        UNION
				        SELECT DISTINCT V.plate AS plate,
				               V.vehicleStatus AS vehicleStatus,
				               C.name AS name,
				               C.upi AS upi,
				               C.clientType AS clientType,
				               V.nickname AS nickname,
				               C.idClient AS idClient,
				               V.idVehicle AS idVehicle,
				               COALESCE(null, 0) AS idCard,
				               PP.productCode AS productCode,
				               NULL AS cardnum,
				               NULL AS cardStatus,
				               V.controlTotal AS controlTotal,
				               V.datapass AS datapass,
				               NULL AS ctPosition,
				               V.documentType AS documentType,
				               D.name AS nombreDepto,
				               VT.name AS tipoVehiculo,
				               PP.name AS nombreProducto
				        FROM Vehicles V
				        JOIN Clients C ON V.client.idClient = C.idClient
				        LEFT JOIN Products PP ON V.product.productCode = PP.productCode
				        LEFT JOIN Departments D ON D.idDepartment = V.department.idDepartment
				        LEFT JOIN VehicleTypes VT ON VT.vehicleTypeCode = V.vehicleType.vehicleTypeCode
				        WHERE C.clientStatus <> 'E'
				          AND V.vehicleStatus <> 'E'
				          AND PP.productStatus <> 'E'
				          AND D.departmentStatus <> 'E'
				          AND V.idVehicle NOT IN (
				              SELECT V2.idVehicle
				              FROM Vehicles V2
				              JOIN Clients C2 ON V2.client.idClient = C2.idClient
				              LEFT JOIN Products PP2 ON V2.product.productCode = PP2.productCode
				              LEFT JOIN VehicleCard VC2 ON VC2.vehicle.idVehicle = V2.idVehicle
				              LEFT JOIN Card CC2 ON VC2.card.idCard = CC2.idCard
				              LEFT JOIN Departments D2 ON D2.idDepartment = V2.department.idDepartment
				              LEFT JOIN VehicleTypes VT2 ON VT2.vehicleTypeCode = V2.vehicleType.vehicleTypeCode
				              WHERE C2.clientStatus <> 'E'
				                AND V2.vehicleStatus <> 'E'
				                AND CC2.cardStatus <> 'E'
				                AND PP2.productStatus <> 'E'
				                AND D2.departmentStatus <> 'E'
				          )
				          AND (:upi IS NULL OR C.upi ILIKE :upi)
				          AND (:idClient IS NULL OR C.idClient = :idClient)
				          AND (:legalName IS NULL OR C.legalName ILIKE :legalName)
				          AND (:plate IS NULL OR V.plate ILIKE :plate)
				          AND (:nickname IS NULL OR V.nickname ILIKE :nickname)
				          AND (:vehicleStatus IS NULL OR V.vehicleStatus ILIKE :vehicleStatus)
				          AND (:clientType IS NULL OR C.clientType ILIKE :clientType)
				          AND (:documentType IS NULL OR V.documentType ILIKE :documentType)
				          AND (:controlTotal IS NULL OR V.controlTotal = :controlTotal)
				          AND (:codeTypeVehicle IS NULL OR V.vehicleType.vehicleTypeCode = :codeTypeVehicle)
				          AND (:idDepartment IS NULL OR D.idDepartment = :idDepartment)
				    ) datosPaginados

							""";
			
			
			if(paging.getSort() != null)
				sqlQuery += Functions.getSortString(paging.getSort(), MonitorVehiclesDTO.class);
			
			query = (TypedQuery<Long>) entityManager.createQuery(sqlQuery);
			
			
			query.setParameter("upi", params.getUpi() == null ? null : "%"+params.getUpi()+"%");
			query.setParameter("idClient",params.getIdClient());
			query.setParameter("legalName",params.getLegalName() == null ? null : "%"+params.getLegalName()+"%");
			query.setParameter("nickname",params.getNickName() == null ? null :"%"+params.getNickName()+"%");
			query.setParameter("plate",params.getPlate() == null ? null :"%"+params.getPlate()+"%");
			query.setParameter("vehicleStatus",params.getVehicleStatus());
			query.setParameter("clientType",params.getClientType());
			query.setParameter("documentType",params.getDocumentType());
			query.setParameter("controlTotal",params.getControlTotal() != null ? (params.getControlTotal() ? 1 : 0) : null);
			query.setParameter("codeTypeVehicle",params.getCodeTypeVehicle());
			query.setParameter("idDepartment",params.getIdDepartment());
			
			response  = query.getSingleResult();
			
		}catch(Exception ex) {
			log.error("Error al realizar el count de getListPatentesClientes ERROR: "+ex.getMessage());
			ex.printStackTrace();
		}
		
		log.info("Response: "+response);
		
		return response;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MonitorVehiclesDTO> getListPatentesClientes(MonitorVehiculosRequestDTO params, Pageable paging) {
		TypedQuery<MonitorVehiclesDTO> query;
		List<MonitorVehiclesDTO> response = null;
		
		try {
			
			String sqlQuery = """
				    SELECT plate AS plate,
				           vehicleStatus AS vehicleStatus,
				           name AS name,
				           upi AS upi,
				           clientType AS clientType,
				           nickname AS nickname,
				           idClient AS idClient,
				           idVehicle AS idVehicle,
				           idCard AS idCard,
				           productCode AS productCode,
				           cardnum AS cardnum,
				           cardStatus AS cardStatus,
				           controlTotal AS controlTotal,
				           datapass AS datapass,
				           ctPosition AS ctPosition,
				           documentType AS documentType,
				           nombreDepto AS nombreDepto,
				           tipoVehiculo AS tipoVehiculo,
				           nombreProducto AS nombreProducto
				    FROM (
				        SELECT plate AS plate,
				               vehicleStatus AS vehicleStatus,
				               name AS name,
				               upi AS upi,
				               clientType AS clientType,
				               nickname AS nickname,
				               idClient AS idClient,
				               idVehicle AS idVehicle,
				               idCard AS idCard,
				               productCode AS productCode,
				               cardnum AS cardnum,
				               cardStatus AS cardStatus,
				               controlTotal AS controlTotal,
				               datapass AS datapass,
				               posicion AS ctPosition,
				               documentType AS documentType,
				               nombreDepto AS nombreDepto,
				               tipoVehiculo AS tipoVehiculo,
				               nombreProducto AS nombreProducto
				        FROM (
				            SELECT DISTINCT V.plate AS plate,
				                   V.vehicleStatus AS vehicleStatus,
				                   C.name AS name,
				                   C.upi AS upi,
				                   C.clientType AS clientType,
				                   V.nickname AS nickname,
				                   C.idClient AS idClient,
				                   V.idVehicle AS idVehicle,
				                   CC.idCard AS idCard,
				                   PP.productCode AS productCode,
				                   CC.cardnum AS cardnum,
				                   CC.cardStatus AS cardStatus,
				                   V.controlTotal AS controlTotal,
				                   V.datapass AS datapass,
				                   CC.ctPosition AS posicion,
				                   V.documentType AS documentType,
				                   D.name AS nombreDepto,
				                   VT.name AS tipoVehiculo,
				                   PP.name AS nombreProducto
				            FROM Vehicles V
				            JOIN Clients C ON V.client.idClient = C.idClient
				            LEFT JOIN Products PP ON V.product.productCode = PP.productCode
				            LEFT JOIN VehicleCard VC ON VC.vehicle.idVehicle = V.idVehicle
				            LEFT JOIN Card CC ON VC.card.idCard = CC.idCard
				            LEFT JOIN Departments D ON D.idDepartment = V.department.idDepartment
				            LEFT JOIN VehicleTypes VT ON VT.vehicleTypeCode = V.vehicleType.vehicleTypeCode
				            WHERE C.clientStatus <> 'E'
				              AND V.vehicleStatus <> 'E'
				              AND CC.cardStatus <> 'E'
				              AND PP.productStatus <> 'E'
				              AND D.departmentStatus <> 'E'
				              AND (:upi IS NULL OR C.upi ILIKE :upi)
				              AND (:idClient IS NULL OR C.idClient = :idClient)
				              AND (:legalName IS NULL OR C.legalName ILIKE :legalName)
				              AND (:plate IS NULL OR V.plate ILIKE :plate)
				              AND (:nickname IS NULL OR V.nickname ILIKE :nickname)
				              AND (:vehicleStatus IS NULL OR V.vehicleStatus ILIKE :vehicleStatus)
				              AND (:clientType IS NULL OR C.clientType ILIKE :clientType)
				              AND (:documentType IS NULL OR V.documentType ILIKE :documentType)
				              AND (:controlTotal IS NULL OR V.controlTotal = :controlTotal)
				              AND (:codeTypeVehicle IS NULL OR V.vehicleType.vehicleTypeCode = :codeTypeVehicle)
				              AND (:idDepartment IS NULL OR D.idDepartment = :idDepartment)
				        ) datos
				        UNION
				        SELECT DISTINCT V.plate AS plate,
				               V.vehicleStatus AS vehicleStatus,
				               C.name AS name,
				               C.upi AS upi,
				               C.clientType AS clientType,
				               V.nickname AS nickname,
				               C.idClient AS idClient,
				               V.idVehicle AS idVehicle,
				               COALESCE(null, 0) AS idCard,
				               PP.productCode AS productCode,
				               NULL AS cardnum,
				               NULL AS cardStatus,
				               V.controlTotal AS controlTotal,
				               V.datapass AS datapass,
				               NULL AS ctPosition,
				               V.documentType AS documentType,
				               D.name AS nombreDepto,
				               VT.name AS tipoVehiculo,
				               PP.name AS nombreProducto
				        FROM Vehicles V
				        JOIN Clients C ON V.client.idClient = C.idClient
				        LEFT JOIN Products PP ON V.product.productCode = PP.productCode
				        LEFT JOIN Departments D ON D.idDepartment = V.department.idDepartment
				        LEFT JOIN VehicleTypes VT ON VT.vehicleTypeCode = V.vehicleType.vehicleTypeCode
				        WHERE C.clientStatus <> 'E'
				          AND V.vehicleStatus <> 'E'
				          AND PP.productStatus <> 'E'
				          AND D.departmentStatus <> 'E'
				          AND V.idVehicle NOT IN (
				              SELECT V2.idVehicle
				              FROM Vehicles V2
				              JOIN Clients C2 ON V2.client.idClient = C2.idClient
				              LEFT JOIN Products PP2 ON V2.product.productCode = PP2.productCode
				              LEFT JOIN VehicleCard VC2 ON VC2.vehicle.idVehicle = V2.idVehicle
				              LEFT JOIN Card CC2 ON VC2.card.idCard = CC2.idCard
				              LEFT JOIN Departments D2 ON D2.idDepartment = V2.department.idDepartment
				              LEFT JOIN VehicleTypes VT2 ON VT2.vehicleTypeCode = V2.vehicleType.vehicleTypeCode
				              WHERE C2.clientStatus <> 'E'
				                AND V2.vehicleStatus <> 'E'
				                AND CC2.cardStatus <> 'E'
				                AND PP2.productStatus <> 'E'
				                AND D2.departmentStatus <> 'E'
				          )
				          AND (:upi IS NULL OR C.upi ILIKE :upi)
				          AND (:idClient IS NULL OR C.idClient = :idClient)
				          AND (:legalName IS NULL OR C.legalName ILIKE :legalName)
				          AND (:plate IS NULL OR V.plate ILIKE :plate)
				          AND (:nickname IS NULL OR V.nickname ILIKE :nickname)
				          AND (:vehicleStatus IS NULL OR V.vehicleStatus ILIKE :vehicleStatus)
				          AND (:clientType IS NULL OR C.clientType ILIKE :clientType)
				          AND (:documentType IS NULL OR V.documentType ILIKE :documentType)
				          AND (:controlTotal IS NULL OR V.controlTotal = :controlTotal)
				          AND (:codeTypeVehicle IS NULL OR V.vehicleType.vehicleTypeCode = :codeTypeVehicle)
				          AND (:idDepartment IS NULL OR D.idDepartment = :idDepartment)
				    ) datosPaginados
				""";



			
			if(paging.getSort() != null)
				sqlQuery += Functions.getSortString(paging.getSort(), MonitorVehiclesDTO.class);
			
			query = entityManager.createQuery(sqlQuery.toString(), MonitorVehiclesDTO.class)
					.unwrap(Query.class)
					.setTupleTransformer((tuple, aliases) -> DbUtils.objFromFields(tuple, aliases, MonitorVehiclesDTO.class));
			
			query.setParameter("upi", params.getUpi() == null ? null : "%"+params.getUpi()+"%");
			query.setParameter("idClient",params.getIdClient());
			query.setParameter("legalName",params.getLegalName() == null ? null : "%"+params.getLegalName()+"%");
			query.setParameter("nickname",params.getNickName() == null ? null :"%"+params.getNickName()+"%");
			query.setParameter("plate",params.getPlate() == null ? null :"%"+params.getPlate()+"%");
			query.setParameter("vehicleStatus",params.getVehicleStatus());
			query.setParameter("clientType",params.getClientType());
			query.setParameter("documentType",params.getDocumentType());
			query.setParameter("controlTotal",params.getControlTotal() != null ? (params.getControlTotal() ? 1 : 0) : null);
			query.setParameter("codeTypeVehicle",params.getCodeTypeVehicle());
			query.setParameter("idDepartment",params.getIdDepartment());
			
			query.setFirstResult(paging.isUnpaged() ? 0 : (int) paging.getOffset());
			query.setMaxResults(paging.isUnpaged() ? 100 : paging.getPageSize());
			
			response  = query.getResultList();
			
		}catch(Exception ex) {
			log.error("ERROR " + ex.getMessage());
			ex.printStackTrace();
		}
		
		return response;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MonitorCardDTO> getVehiclesByRestrictionTypeCard(Long idDepartment, Pageable paging) {
		TypedQuery<MonitorCardDTO> query;
		List<MonitorCardDTO> response = null;
		
		try {
			
			String sqlQuery = """
				SELECT
					C.idCard as idCard,
					D.idDepartment as idDepartment,
					D.name as nameDepto,
					C.restrictionType as restrictionType,
					D.typeBalance typeBalance,
					C.cardnum as cardnum,
					C.remainingAmount as remainingAmount,
					V.idVehicle as idVehicle,
					V.plate as plate,
					V.nickname as nickname,
					V.documentType as documentType,
					V.product.productCode as productCode,
					C.cardStatus as cardStatus,
					C.reqcardStatus as reqCardStatus
				FROM
				Vehicles V
				JOIN Departments D ON D.idDepartment = V.department.idDepartment
				JOIN VehicleCard VC ON VC.vehicle.idVehicle = V.idVehicle
				JOIN Card C ON C.idCard = VC.card.idCard and C.cardStatus <>  'E'
				WHERE
				D.departmentStatus = 'A'
				AND V.vehicleStatus = 'A'
				AND D.idDepartment = :idDepartment
				AND C.restrictionType = 'C'
				""";
			
			if(paging.getSort() != null)
				sqlQuery += Functions.getSortString(paging.getSort(), MonitorCardDTO.class);
			
			query = entityManager.createQuery(sqlQuery.toString(), MonitorCardDTO.class)
					.unwrap(Query.class)
					.setTupleTransformer((tuple, aliases) -> DbUtils.objFromFields(tuple, aliases, MonitorCardDTO.class));
			
			query.setParameter("idDepartment",idDepartment);
			
			query.setFirstResult(paging.isUnpaged() ? 0 : (int) paging.getOffset());
			query.setMaxResults(paging.isUnpaged() ? 100 : paging.getPageSize());
			
			response  = query.getResultList();
			
		}catch(Exception ex) {
			log.error("ERROR " + ex.getMessage());
			ex.printStackTrace();
		}
		
		return response;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<VehiclesJsonDTO> getListVehiclesJson(ListVehiclesJsonRequestDTO params, Pageable pageable) {

		TypedQuery<VehiclesJsonDTO> query;
		List<VehiclesJsonDTO> response = null;
		
		try {
			
			String sqlQuery = """
					select
						distinct
						v.plate								AS plate,
						c.name								AS clientName,
						c.idClient							AS idClient,
						v.idVehicle							AS idVehicle,
						v.controlTotal						AS controlTotal,
						v.datapass							AS datapass,
						v.documentType						AS documentType,
						d.name								AS deptoName,
						vt.name								AS vehicleType,
						v.product.productCode				AS productCode,
						pp.name								AS productName,
						v.nickname							AS nickname,
						cd.idCard							AS idCard
					from 
					Vehicles v
					join Clients c on v.client.idClient = c.idClient
					left join Products pp on v.product.productCode = pp.productCode
					left join Departments d on d.idDepartment = v.department.idDepartment
					left join VehicleTypes vt on vt.vehicleTypeCode = v.vehicleType.vehicleTypeCode
					left join VehicleCard vc on vc.vehicle.idVehicle = v.idVehicle
					left join Card cd on cd.idCard = vc.card.idCard
					where
						c.clientStatus <> 'E'
						AND v.vehicleStatus <> 'E'
						AND pp.productStatus <> 'E'
						AND d.departmentStatus <> 'E'
						AND v.vehicleStatus <> 'R'
						AND cd.cardStatus <> 'E'
						AND (:idClient IS NULL OR c.idClient = :idClient)
						AND (:cardStatus IS NULL OR (cd.cardStatus IN (:cardStatus)))
						AND (:plate IS NULL OR v.plate ilike :plate)
						AND (:nickname IS NULL OR v.nickname ilike :nickname)
						AND (:controlTotal IS NULL OR v.controlTotal = :controlTotal)
						AND (:vehicleTypeCode IS NULL OR v.vehicleType.vehicleTypeCode = :vehicleTypeCode)
						AND (:productCode IS NULL OR v.product.productCode = :productCode)
						AND (:idDepartment IS NULL OR d.idDepartment = :idDepartment)
						AND (:inicioAdblue IS NULL OR v.product.productCode IN ('DIESEL', 'DIESELAD'))
				""";

//						group by
//						v.plate	
			
			if(pageable.getSort() != null)
				sqlQuery += Functions.getSortString(pageable.getSort(), VehiclesJsonDTO.class);
			
			query = entityManager.createQuery(sqlQuery.toString(), VehiclesJsonDTO.class)
					.unwrap(Query.class)
					.setTupleTransformer((tuple, aliases) -> DbUtils.objFromFields(tuple, aliases, VehiclesJsonDTO.class));
			
			query.setParameter("idClient", params.getIdClient());
			query.setParameter("cardStatus", params.getCardStatus());
			query.setParameter("plate", Functions.iLikeOrNull(params.getPlate()));
			query.setParameter("nickname", Functions.iLikeOrNull(params.getNickname()));
			query.setParameter("controlTotal", params.getControlTotal());
			query.setParameter("vehicleTypeCode", params.getVehicleTypeCode());
			query.setParameter("productCode", params.getProductCode());
			query.setParameter("idDepartment", params.getIdDepartment());
			query.setParameter("inicioAdblue", params.getInicioAdblue());
			
			query.setFirstResult(pageable.isUnpaged() ? 0 : (int) pageable.getOffset());
			query.setMaxResults(pageable.isUnpaged() ? Integer.MAX_VALUE : pageable.getPageSize());
			
			response  = query.getResultList();
			
		}catch(Exception ex) {
			log.error("ERROR " + ex.getMessage());
			ex.printStackTrace();
		}
		
		return response;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Long getCOUNTListVehiclesJson(ListVehiclesJsonRequestDTO params) {
		
		TypedQuery<Long> query;
		Long response = null;
		
		try {
			
			String sqlQuery = """
					SELECT COUNT(*) FROM (
					select
						distinct
						v.plate								AS plate,
						c.name								AS clientName,
						c.idClient							AS idClient,
						v.idVehicle							AS idVehicle,
						v.controlTotal						AS controlTotal,
						v.datapass							AS datapass,
						v.documentType						AS documentType,
						d.name								AS deptoName,
						vt.name								AS vehicleType,
						v.product.productCode				AS productCode,
						pp.name								AS productName,
						v.nickname							AS nickname,
						cd.idCard							AS idCard
					from 
					Vehicles v
					join Clients c on v.client.idClient = c.idClient
					left join Products pp on v.product.productCode = pp.productCode
					left join Departments d on d.idDepartment = v.department.idDepartment
					left join VehicleTypes vt on vt.vehicleTypeCode = v.vehicleType.vehicleTypeCode
					left join VehicleCard vc on vc.vehicle.idVehicle = v.idVehicle
					left join Card cd on cd.idCard = vc.card.idCard
					where
						c.clientStatus <> 'E'
						AND v.vehicleStatus <> 'E'
						AND pp.productStatus <> 'E'
						AND d.departmentStatus <> 'E'
						AND v.vehicleStatus <> 'R'
						AND cd.cardStatus <> 'E'
						AND (:idClient IS NULL OR c.idClient = :idClient)
						AND (:cardStatus IS NULL OR (cd.cardStatus IN (:cardStatus)))
						AND (:plate IS NULL OR v.plate ilike :plate)
						AND (:nickname IS NULL OR v.nickname ilike :nickname)
						AND (:controlTotal IS NULL OR v.controlTotal = :controlTotal)
						AND (:vehicleTypeCode IS NULL OR v.vehicleType.vehicleTypeCode = :vehicleTypeCode)
						AND (:productCode IS NULL OR v.product.productCode = :productCode)
						AND (:idDepartment IS NULL OR d.idDepartment = :idDepartment)
						AND (:inicioAdblue IS NULL OR v.product.productCode IN ('DIESEL', 'DIESELAD'))
					) AS query
				""";
			
//						group by
//						v.plate	
			
			query = (TypedQuery<Long>) entityManager.createQuery(sqlQuery);
			
			query.setParameter("idClient", params.getIdClient());
			query.setParameter("cardStatus", params.getCardStatus());
			query.setParameter("plate", Functions.iLikeOrNull(params.getPlate()));
			query.setParameter("nickname", Functions.iLikeOrNull(params.getNickname()));
			query.setParameter("controlTotal", params.getControlTotal());
			query.setParameter("vehicleTypeCode", params.getVehicleTypeCode());
			query.setParameter("productCode", params.getProductCode());
			query.setParameter("idDepartment", params.getIdDepartment());
			query.setParameter("inicioAdblue", params.getInicioAdblue());
			
			response  = query.getSingleResult();
			
		}catch(Exception ex) {
			log.error("ERROR " + ex.getMessage());
			ex.printStackTrace();
		}
		
		return response;
	}

//	@Override
//	@SuppressWarnings("unchecked")
//	public List<MonitorVehiculosDTO> getListPatentesClientes(MonitorVehiculosDTO criterio, Pageable pageable) {
//		TypedQuery<MonitorVehiculosDTO> query;
//		List<MonitorVehiculosDTO> response = null;
//		
//		try {
//			
//			StringBuilder sqlQuery = new StringBuilder("""
//				WITH queryAnidada AS (
//			
//				SELECT 
//					DISTINCT
//					v.plate						AS plate,	
//					v.vehicleStatus				AS vehicleStatus,
//					c.name						AS name,
//					c.upi						AS upi,
//					c.clientType				AS clientType,
//					v.nickname					AS nickname,
//					c.idClient					AS idClient,
//					v.idVehicle					AS idVehicle,
//					cc.idCard					AS idCard,
//					pp.productCode				AS productCode,
//					cc.cardnum					AS cardnum,
//					cc.cardStatus 				AS cardStatus,
//					v.controlTotal				AS controlTotal,
//					v.datapass					AS datapass,
//					cc.ctPosition				AS ctPosition,
//					v.documentType				AS documentType,
//					d.name						AS nombreDepto,
//					vt.name						AS tipoVehiculo,
//					pp.name						AS nombreProducto
//				FROM Vehicles AS v
//				JOIN Clients c ON v.client.idClient = c.idClient
//				LEFT JOIN Products pp ON v.product.productCode = pp.productCode
//				LEFT JOIN VehicleCard vc ON vc.vehicle.idVehicle = v.idVehicle
//				LEFT JOIN Card cc ON vc.card.idCard = cc.idCard
//				LEFT JOIN Departments d ON d.idDepartment = v.department.idDepartment
//				LEFT JOIN VehicleTypes vt ON vt.vehicleTypeCode = v.vehicleType.vehicleTypeCode
//				WHERE
//					c.clientStatus <> 'E' AND v.vehicleStatus <> 'E' AND cc.cardStatus <> 'E' AND
//					pp.productStatus <> 'E' AND d.departmentStatus <> 'E'
//				""");
//			
//
//			
//			if(criterio.getLegalName() != null && !"".equals(criterio.getLegalName())) {
//				sqlQuery.append("AND c.legalName ILIKE :legalName ");
//				parameters.put("legalName", "%"+criterio.getLegalName()+"%");
//			}
//			
//			if(criterio.getIdClient() != null) {
//				sqlQuery.append("AND c.idClient = :idClient ");
//				parameters.put("idClient", criterio.getIdClient());
//			}
//			
//			if(criterio.getPlate() != null && !"".equals(criterio.getPlate())) {
//				sqlQuery.append("AND v.plate ILIKE :plate ");
//				parameters.put("plate", "%"+criterio.getPlate()+"%");
//			}
//			
//			if(criterio.getUpi()!= null && !"".equals(criterio.getUpi())) {
//				sqlQuery.append("AND c.upi ILIKE :upi ");
//				parameters.put("upi", "%"+criterio.getUpi()+"%");
//			}
//			
//			if(criterio.getNickname() != null && !"".equals(criterio.getNickname())) {
//				sqlQuery.append("AND v.nickname ILIKE :nickname ");
//				parameters.put("nickname", "%"+criterio.getNickname()+"%");
//			}
//			
//			if(criterio.getVehicleStatus() != null && !"".equals(criterio.getVehicleStatus())) {
//				sqlQuery.append("AND v.vehicleStatus = :vehicleStatus ");
//				parameters.put("vehicleStatus", criterio.getVehicleStatus());
//			}
//			
//			if(criterio.getClientType() != null && !"".equals(criterio.getClientType())) {
//				sqlQuery.append("AND c.clientType = :clientType ");
//				parameters.put("clientType", criterio.getClientType());
//			}
//			
//			if(criterio.getDocumentType() != null && !"".equals(criterio.getDocumentType())) {
//				sqlQuery.append("AND v.documentType = :documentType ");
//				parameters.put("documentType", criterio.getDocumentType());
//			}
//			
//			if(criterio.getControlTotal() != null && criterio.getControlTotal()) {
//				sqlQuery.append("AND v.controlTotal = :controlTotal ");
//				parameters.put("controlTotal", 1);
//			}
//
//			if(criterio.getCodeTypeVehicle() != null && !"".equals(criterio.getCodeTypeVehicle())) {
//				sqlQuery.append("AND v.vehicleType.vehicleTypeCode = :vehicleTypeCode ");
//				parameters.put("vehicleTypeCode", criterio.getCodeTypeVehicle());
//			}
//			
//			if(criterio.getIdDepartment() != null) {
//				sqlQuery.append("AND d.idDepartment = :idDepartment ");
//				parameters.put("idDepartment", criterio.getIdDepartment());
//			}
//			
//			//union
//			
//			sqlQuery.append("""
//							UNION
//				SELECT 
//					DISTINCT
//					v.plate						AS plate,	
//					v.vehicleStatus				AS vehicleStatus,
//					c.name						AS name,
//					c.upi						AS upi,
//					c.clientType				AS clientType,
//					v.nickname					AS nickname,
//					c.idClient					AS idClient,
//					v.idVehicle					AS idVehicle,
//					0							AS idCard,
//					pp.productCode				AS productCode,
//					''							AS cardnum,
//					'Y'		 					AS cardStatus,
//					v.controlTotal				AS controlTotal,
//					v.datapass					AS datapass,
//					''							AS ctPosition,
//					v.documentType				AS documentType,
//					d.name						AS nombreDepto,
//					vt.name						AS tipoVehiculo,
//					pp.name						AS nombreProducto
//				FROM Vehicles AS v
//				JOIN Clients c ON v.client.idClient = c.idClient
//				LEFT JOIN Products pp ON v.product.productCode = pp.productCode
//				LEFT JOIN Departments d ON d.idDepartment = v.department.idDepartment
//				LEFT JOIN VehicleTypes vt ON vt.vehicleTypeCode = v.vehicleType.vehicleTypeCode
//				WHERE
//					c.clientStatus <> 'E' AND v.vehicleStatus <> 'E' AND
//					pp.productStatus <> 'E' AND d.departmentStatus <> 'E'
//					AND v.idVehicle  NOT IN ( 
//							SELECT v.idVehicle 
//							FROM Vehicles v
//						JOIN Clients c ON v.client.idClient = c.idClient
//						LEFT JOIN Products pp on v.product.productCode = pp.productCode
//						LEFT JOIN VehicleCard vc on vc.vehicle.idVehicle = v.idVehicle
//						LEFT JOIN Card cc on vc.card.idCard = cc.idCard
//						LEFT JOIN Departments d on d.idDepartment = v.department.idDepartment
//						LEFT JOIN VehicleTypes vt on vt.vehicleTypeCode = v.vehicleType.vehicleTypeCode
//				        WHERE  c.clientStatus <> 'E' AND v.vehicleStatus  <> 'E' AND cc.cardStatus <> 'E' AND 
//				        pp.productStatus <> 'E'
//				        AND d.departmentStatus <> 'E'
//				    )
//			""");
//			
//			if(criterio.getLegalName() != null && !"".equals(criterio.getLegalName())) {
//				sqlQuery.append("AND c.legalName ILIKE :legalName ");
//			}
//			
//			if(criterio.getIdClient() != null) {
//				sqlQuery.append("AND c.idClient = :idClient ");
//			}
//			
//			if(criterio.getPlate() != null && !"".equals(criterio.getPlate())) {
//				sqlQuery.append("AND v.plate ILIKE :plate ");
//			}
//			
//			if(criterio.getUpi()!= null && !"".equals(criterio.getUpi())) {
//				sqlQuery.append("AND c.upi ILIKE :upi ");
//			}
//			
//			if(criterio.getNickname() != null && !"".equals(criterio.getNickname())) {
//				sqlQuery.append("AND v.nickname ILIKE :nickname ");
//			}
//			
//			if(criterio.getVehicleStatus() != null && !"".equals(criterio.getVehicleStatus())) {
//				sqlQuery.append("AND v.vehicleStatus = :vehicleStatus ");
//			}
//			
//			if(criterio.getClientType() != null && !"".equals(criterio.getClientType())) {
//				sqlQuery.append("AND c.clientType = :clientType ");
//			}
//			
//			if(criterio.getDocumentType() != null && !"".equals(criterio.getDocumentType())) {
//				sqlQuery.append("AND v.documentType = :documentType ");
//			}
//			
//			if(criterio.getControlTotal() != null && criterio.getControlTotal()) {
//				sqlQuery.append("AND v.controlTotal = :controlTotal ");
//			}
//
//			if(criterio.getCodeTypeVehicle() != null && !"".equals(criterio.getCodeTypeVehicle())) {
//				sqlQuery.append("AND v.vehicleType.vehicleTypeCode = :vehicleTypeCode ");
//			}
//			
//			if(criterio.getIdDepartment() != null) {
//				sqlQuery.append("AND d.idDepartment = :idDepartment ");
//			}
//
//			//cierre union
//			sqlQuery.append("""
//					) SELECT 
//					plate						AS plate,	
//					vehicleStatus				AS vehicleStatus,
//					name						AS name,
//					upi							AS upi,
//					clientType					AS clientType,
//					nickname					AS nickname,
//					idClient					AS idClient,
//					idVehicle					AS idVehicle,
//					idCard						AS idCard,
//					productCode					AS productCode,
//					cardnum						AS cardnum,
//					cardStatus					AS cardStatus,
//					controlTotal				AS controlTotal,
//					datapass					AS datapass,
//					ctPosition					AS ctPosition,
//					documentType				AS documentType,
//					nombreDepto					AS nombreDepto,
//					tipoVehiculo				AS tipoVehiculo,
//					nombreProducto				AS nombreProducto
//			FROM queryAnidada
//			""");
//			
//			sqlQuery.append(Functions.getSortString(pageable.getSort(), MonitorVehiculosDTO.class));
//
//			
//			query = entityManager.createQuery(sqlQuery.toString(), MonitorVehiculosDTO.class)
//			.unwrap(Query.class)
//			.setTupleTransformer((tuple, aliases) -> DbUtils.objFromFields(tuple, aliases, MonitorVehiculosDTO.class));
//			
//			if(!parameters.isEmpty()) {
//				parameters.entrySet().forEach(e -> query.setParameter(e.getKey(), e.getValue()));
//			}
//
//			query.setFirstResult((int) pageable.getOffset());
//			query.setMaxResults(pageable.getPageSize());
//			
//			response  = query.getResultList();
//			
//		} catch (Exception e) {
//			log.error("QUERY error: "+e.getMessage());
//		}
//		
//		log.debug("response: "+response);
//		return response;
//	}
//
//	@Override
//	@SuppressWarnings("unchecked")
//	public Long countListPatentesClientes(MonitorVehiculosDTO criterio) {
//		Map<String, Object> parameters = new HashMap<>();
//		TypedQuery<MonitorVehiculosDTO> query;
//		Long totalRegistros = null;
//		
//		try {
//			
//			StringBuilder sqlQuery = new StringBuilder("""
//				WITH queryAnidada AS (
//			
//				SELECT 
//					DISTINCT
//					v.plate						AS plate,	
//					v.vehicleStatus				AS vehicleStatus,
//					c.name						AS name,
//					c.upi						AS upi,
//					c.clientType				AS clientType,
//					v.nickname					AS nickname,
//					c.idClient					AS idClient,
//					v.idVehicle					AS idVehicle,
//					cc.idCard					AS idCard,
//					pp.productCode				AS productCode,
//					cc.cardnum					AS cardnum,
//					cc.cardStatus 				AS cardStatus,
//					v.controlTotal				AS controlTotal,
//					v.datapass					AS datapass,
//					cc.ctPosition				AS ctPosition,
//					v.documentType				AS documentType,
//					d.name						AS nombreDepto,
//					vt.name						AS tipoVehiculo,
//					pp.name						AS nombreProducto
//				FROM Vehicles AS v
//				JOIN Clients c ON v.client.idClient = c.idClient
//				LEFT JOIN Products pp ON v.product.productCode = pp.productCode
//				LEFT JOIN VehicleCard vc ON vc.vehicle.idVehicle = v.idVehicle
//				LEFT JOIN Card cc ON vc.card.idCard = cc.idCard
//				LEFT JOIN Departments d ON d.idDepartment = v.department.idDepartment
//				LEFT JOIN VehicleTypes vt ON vt.vehicleTypeCode = v.vehicleType.vehicleTypeCode
//				WHERE
//					c.clientStatus <> 'E' AND v.vehicleStatus <> 'E' AND cc.cardStatus <> 'E' AND
//					pp.productStatus <> 'E' AND d.departmentStatus <> 'E'
//				""");
//			
//
//			
//			if(criterio.getLegalName() != null && !"".equals(criterio.getLegalName())) {
//				sqlQuery.append("AND c.legalName ILIKE :legalName ");
//				parameters.put("legalName", "%"+criterio.getLegalName()+"%");
//			}
//			
//			if(criterio.getIdClient() != null) {
//				sqlQuery.append("AND c.idClient = :idClient ");
//				parameters.put("idClient", criterio.getIdClient());
//			}
//			
//			if(criterio.getPlate() != null && !"".equals(criterio.getPlate())) {
//				sqlQuery.append("AND v.plate ILIKE :plate ");
//				parameters.put("plate", "%"+criterio.getPlate()+"%");
//			}
//			
//			if(criterio.getUpi()!= null && !"".equals(criterio.getUpi())) {
//				sqlQuery.append("AND c.upi ILIKE :upi ");
//				parameters.put("upi", "%"+criterio.getUpi()+"%");
//			}
//			
//			if(criterio.getNickname() != null && !"".equals(criterio.getNickname())) {
//				sqlQuery.append("AND v.nickname ILIKE :nickname ");
//				parameters.put("nickname", "%"+criterio.getNickname()+"%");
//			}
//			
//			if(criterio.getVehicleStatus() != null && !"".equals(criterio.getVehicleStatus())) {
//				sqlQuery.append("AND v.vehicleStatus = :vehicleStatus ");
//				parameters.put("vehicleStatus", criterio.getVehicleStatus());
//			}
//			
//			if(criterio.getClientType() != null && !"".equals(criterio.getClientType())) {
//				sqlQuery.append("AND c.clientType = :clientType ");
//				parameters.put("clientType", criterio.getClientType());
//			}
//			
//			if(criterio.getDocumentType() != null && !"".equals(criterio.getDocumentType())) {
//				sqlQuery.append("AND v.documentType = :documentType ");
//				parameters.put("documentType", criterio.getDocumentType());
//			}
//			
//			if(criterio.getControlTotal() != null && criterio.getControlTotal()) {
//				sqlQuery.append("AND v.controlTotal = :controlTotal ");
//				parameters.put("controlTotal", 1);
//			}
//
//			if(criterio.getCodeTypeVehicle() != null && !"".equals(criterio.getCodeTypeVehicle())) {
//				sqlQuery.append("AND v.vehicleType.vehicleTypeCode = :vehicleTypeCode ");
//				parameters.put("vehicleTypeCode", criterio.getCodeTypeVehicle());
//			}
//			
//			if(criterio.getIdDepartment() != null) {
//				sqlQuery.append("AND d.idDepartment = :idDepartment ");
//				parameters.put("idDepartment", criterio.getIdDepartment());
//			}
//			
//			//union
//			
//			sqlQuery.append("""
//							UNION
//				SELECT 
//					DISTINCT
//					v.plate						AS plate,	
//					v.vehicleStatus				AS vehicleStatus,
//					c.name						AS name,
//					c.upi						AS upi,
//					c.clientType				AS clientType,
//					v.nickname					AS nickname,
//					c.idClient					AS idClient,
//					v.idVehicle					AS idVehicle,
//					0							AS idCard,
//					pp.productCode				AS productCode,
//					''							AS cardnum,
//					'Y'		 					AS cardStatus,
//					v.controlTotal				AS controlTotal,
//					v.datapass					AS datapass,
//					''							AS ctPosition,
//					v.documentType				AS documentType,
//					d.name						AS nombreDepto,
//					vt.name						AS tipoVehiculo,
//					pp.name						AS nombreProducto
//				FROM Vehicles AS v
//				JOIN Clients c ON v.client.idClient = c.idClient
//				LEFT JOIN Products pp ON v.product.productCode = pp.productCode
//				LEFT JOIN Departments d ON d.idDepartment = v.department.idDepartment
//				LEFT JOIN VehicleTypes vt ON vt.vehicleTypeCode = v.vehicleType.vehicleTypeCode
//				WHERE
//					c.clientStatus <> 'E' AND v.vehicleStatus <> 'E' AND
//					pp.productStatus <> 'E' AND d.departmentStatus <> 'E'
//					AND v.idVehicle  NOT IN ( 
//							SELECT v.idVehicle 
//							FROM Vehicles v
//						JOIN Clients c ON v.client.idClient = c.idClient
//						LEFT JOIN Products pp on v.product.productCode = pp.productCode
//						LEFT JOIN VehicleCard vc on vc.vehicle.idVehicle = v.idVehicle
//						LEFT JOIN Card cc on vc.card.idCard = cc.idCard
//						LEFT JOIN Departments d on d.idDepartment = v.department.idDepartment
//						LEFT JOIN VehicleTypes vt on vt.vehicleTypeCode = v.vehicleType.vehicleTypeCode
//				        WHERE  c.clientStatus <> 'E' AND v.vehicleStatus  <> 'E' AND cc.cardStatus <> 'E' AND 
//				        pp.productStatus <> 'E'
//				        AND d.departmentStatus <> 'E'
//				    )
//			""");
//			
//			if(criterio.getLegalName() != null && !"".equals(criterio.getLegalName())) {
//				sqlQuery.append("AND c.legalName ILIKE :legalName ");
//			}
//			
//			if(criterio.getIdClient() != null) {
//				sqlQuery.append("AND c.idClient = :idClient ");
//			}
//			
//			if(criterio.getPlate() != null && !"".equals(criterio.getPlate())) {
//				sqlQuery.append("AND v.plate ILIKE :plate ");
//			}
//			
//			if(criterio.getUpi()!= null && !"".equals(criterio.getUpi())) {
//				sqlQuery.append("AND c.upi ILIKE :upi ");
//			}
//			
//			if(criterio.getNickname() != null && !"".equals(criterio.getNickname())) {
//				sqlQuery.append("AND v.nickname ILIKE :nickname ");
//			}
//			
//			if(criterio.getVehicleStatus() != null && !"".equals(criterio.getVehicleStatus())) {
//				sqlQuery.append("AND v.vehicleStatus = :vehicleStatus ");
//			}
//			
//			if(criterio.getClientType() != null && !"".equals(criterio.getClientType())) {
//				sqlQuery.append("AND c.clientType = :clientType ");
//			}
//			
//			if(criterio.getDocumentType() != null && !"".equals(criterio.getDocumentType())) {
//				sqlQuery.append("AND v.documentType = :documentType ");
//			}
//			
//			if(criterio.getControlTotal() != null && criterio.getControlTotal()) {
//				sqlQuery.append("AND v.controlTotal = :controlTotal ");
//			}
//
//			if(criterio.getCodeTypeVehicle() != null && !"".equals(criterio.getCodeTypeVehicle())) {
//				sqlQuery.append("AND v.vehicleType.vehicleTypeCode = :vehicleTypeCode ");
//			}
//			
//			if(criterio.getIdDepartment() != null) {
//				sqlQuery.append("AND d.idDepartment = :idDepartment ");
//			}
//
//			//cierre union
//			sqlQuery.append("""
//					) SELECT COUNT(*) AS totalRegistros
//			FROM queryAnidada
//			""");
//			
//
//			
//			query = entityManager.createQuery(sqlQuery.toString(), MonitorVehiculosDTO.class)
//			.unwrap(Query.class)
//			.setTupleTransformer((tuple, aliases) -> DbUtils.objFromFields(tuple, aliases, MonitorVehiculosDTO.class));
//			
//			if(!parameters.isEmpty()) {
//				parameters.entrySet().forEach(e -> query.setParameter(e.getKey(), e.getValue()));
//			}
//
//			
//			totalRegistros  = query.getSingleResult().getTotalRegistros();
//			
//		} catch (Exception e) {
//			log.error("QUERY error: "+e.getMessage());
//		}
//		
//		log.debug("totalRegistros: "+totalRegistros);
//		return totalRegistros;
//	}

}
