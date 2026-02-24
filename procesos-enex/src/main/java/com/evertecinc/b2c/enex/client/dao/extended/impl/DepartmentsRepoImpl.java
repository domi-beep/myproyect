package com.evertecinc.b2c.enex.client.dao.extended.impl;

import java.util.List;

import org.hibernate.query.Query;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.evertecinc.b2c.enex.classes.utils.db.DbUtils;
import com.evertecinc.b2c.enex.client.dao.extended.IDepartmentsRepo;
import com.evertecinc.b2c.enex.client.model.dto.DepartmentDTO;
import com.evertecinc.b2c.enex.client.model.dto.ProductDepartmentDTO;
import com.evertecinc.b2c.enex.client.model.dto2.DepartmentMovementDTO;
import com.evertecinc.b2c.enex.client.model.dto2.DepartmentProductFinderDTO;
import com.evertecinc.b2c.enex.client.model.dto2.requests.DepartmentByClientRequestDTO;
import com.evertecinc.b2c.enex.client.model.dto2.requests.DepartmentRequestDTO;
import com.evertecinc.b2c.enex.client.model.dto2.requests.ListMovimientosDeptoCtrlRequestDTO;
import com.evertecinc.b2c.enex.client.model.dto2.requests.ProductDepartmentRequestDTO;
import com.evertecinc.b2c.enex.utils.functions.Functions;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Repository
@RequiredArgsConstructor
@Slf4j
public class DepartmentsRepoImpl implements IDepartmentsRepo {

	@PersistenceContext
	private final EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<DepartmentDTO> getDepartmentsByClient(DepartmentByClientRequestDTO params, Pageable pageable) {
		/* getListDepartmentByClient */
		
		TypedQuery<DepartmentDTO> query;
		
		List<DepartmentDTO> response = null;

		try {
			String sqlQuery = """
				SELECT
					D.idDepartment 							as idDepartment,
					D.client.idClient 						as idClient,
					D.name 									as name,
					D.restrType 							as restrType,
					CASE WHEN D.restrL = 1 THEN true ELSE false END 								as restrL,
					CASE WHEN D.restrM = 1 THEN true ELSE false END 								as restrM,
					CASE WHEN D.restrX = 1 THEN true ELSE false END 								as restrX,
					CASE WHEN D.restrJ = 1 THEN true ELSE false END 								as restrJ,
					CASE WHEN D.restrV = 1 THEN true ELSE false END 								as restrV,
					CASE WHEN D.restrS = 1 THEN true ELSE false END 								as restrS,
					CASE WHEN D.restrD = 1 THEN true ELSE false END 								as restrD,	
					D.restrHinit 							as restrHinit,
					D.restrHend 							as restrHend,
					D.restrAmountMax 						as restrAmountMax,
					D.restrDailyMaxLoads 					as restrDailyMaxLoads,
					D.restrDailyMaxQuantity 				as restrDailyMaxQuantity,
					D.departmentStatus 						as departmentStatus,
					D.warningStockChannel 					as warningStockChannel,
					D.warningStock 							as warningStock,
					D.warningStockCelular 					as warningStockCelular,
					D.warningStockEmail 					as warningStockMail,
					D.warningLoadChannel 					as warningLoadChannel,
					D.warningLoadCelular 					as warningLoadCelular,
					D.warningLoadEmail 						as warningLoadMail,
					D.warningLoadFailChannel 				as warningLoadFailChannel,
					D.warningLoadFailCelular 				as warningLoadFailCelular,
					D.warningLoadFailEmail 					as warningLoadFailEmail,
					D.typeBalance 							as typeBalance,
					D.codeorpakclient 						as codeOrpakClient,
					(
						SELECT
							COUNT(CD.idCard)
						FROM Vehicles V
						JOIN VehicleCard VC ON V.idVehicle = VC.vehicle.idVehicle
						JOIN Card CD ON VC.card.idCard = CD.idCard
						WHERE V.department.idDepartment = D.idDepartment
						AND V.client.idClient = C.idClient
						AND V.vehicleStatus <> 'E'
						AND CD.cardStatus <> 'E'
						AND (:plate IS NULL OR V.plate = :plate)
					) 										as totalTarjetas,
					D.idPadre 								as idPadre
				FROM Departments D
				JOIN Clients C ON D.client.idClient = C.idClient
				LEFT JOIN DepartmentsUsersRel DR ON DR.department.idDepartment = D.idDepartment AND (:idUser IS NOT NULL)
				WHERE D.departmentStatus <> 'E' AND
					(:idClient IS NULL OR C.idClient = :idClient) AND
					(:name IS NULL OR D.name ILIKE :name) AND
					(:idDepartment IS NULL OR D.idDepartment = :idDepartment) AND
					(:idUser IS NULL OR DR.user.idUser = :idUser) AND
					(:restrAmountMax IS NULL OR D.restrAmountMax = :restrAmountMax)
			""";

			if(pageable.getSort() != null)
				sqlQuery += Functions.getSortString(pageable.getSort(), DepartmentDTO.class);

			query = entityManager.createQuery(sqlQuery, DepartmentDTO.class)
			.unwrap(Query.class)
			.setTupleTransformer((tuple, aliases) -> DbUtils.objFromFields(tuple, aliases, DepartmentDTO.class));
			
			query.setParameter("idClient", params.getIdCliente());
			query.setParameter("name", Functions.iLikeOrNull(params.getName()));
			query.setParameter("idDepartment", params.getIdDepartment());
			query.setParameter("idUser", params.getIdUser());
			query.setParameter("restrAmountMax", params.getRestrAmountMax());
			query.setParameter("plate", params.getPlate());

			query.setFirstResult(pageable.isUnpaged() ? 0 : (int) pageable.getOffset());
			query.setMaxResults(pageable.isUnpaged() ? Integer.MAX_VALUE : pageable.getPageSize());
			
			response  = query.getResultList();

		} catch (Exception e) {
			log.error("ERROR " + e.getMessage());
			e.printStackTrace();
		}
		
		return response;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Long getCOUNTDepartmentsByClient(DepartmentByClientRequestDTO params) {
		TypedQuery<Long> query;
		
		Long response = null;

		try {
			String sqlQuery = """
				SELECT
					COUNT(*)
				FROM Departments D
				JOIN Clients C ON D.client.idClient = C.idClient
				LEFT JOIN DepartmentsUsersRel DR ON DR.department.idDepartment = D.idDepartment AND (:idUser IS NOT NULL)
				WHERE D.departmentStatus <> 'E' AND
					(:idClient IS NULL OR C.idClient = :idClient) AND
					(:name IS NULL OR D.name ILIKE :name) AND
					(:idDepartment IS NULL OR D.idDepartment = :idDepartment) AND
					(:idUser IS NULL OR DR.user.idUser = :idUser) AND
					(:restrAmountMax IS NULL OR D.restrAmountMax = :restrAmountMax)
			""";

			query = (TypedQuery<Long>) entityManager.createQuery(sqlQuery);

			query.setParameter("idClient", params.getIdCliente());
			query.setParameter("name", params.getName());
			query.setParameter("idDepartment", params.getIdDepartment());
			query.setParameter("idUser", params.getIdUser());
			query.setParameter("restrAmountMax", params.getRestrAmountMax());

			response  = query.getSingleResult();

		} catch (Exception e) {
			log.error("ERROR " + e.getMessage());
			e.printStackTrace();
		}
		
		return response;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DepartmentDTO> getDepartmentsByIdUser(DepartmentByClientRequestDTO params, Pageable pageable) {
		TypedQuery<DepartmentDTO> query;
		
		List<DepartmentDTO> response = null;

		try {
			String sqlQuery = """
				SELECT 
				    D.idDepartment 													AS idDepartment,
					D.name 															AS name,
					D.typeBalance 													AS typeBalance,
					(
						select
							count(*)
						from Vehicles v
						where v.department.idDepartment = D.idDepartment and
						v.vehicleStatus = 'A'
						AND (:plate IS NULL OR v.plate = :plate)
					) 																AS totalTarjetas,
					D.idPadre 														AS idPadre
				FROM Departments D, DepartmentsUsersRel R
				WHERE D.departmentStatus <> "E" AND
				R.department.idDepartment = D.idDepartment AND
				(:idUser IS NULL OR R.user.idUser = :idUser) AND
				(:idClient IS NULL OR D.client.idClient = :idClient)
			""";

			if(pageable.getSort() != null)
				sqlQuery += Functions.getSortString(pageable.getSort(), DepartmentDTO.class);

			query = entityManager.createQuery(sqlQuery, DepartmentDTO.class)
			.unwrap(Query.class)
			.setTupleTransformer((tuple, aliases) -> DbUtils.objFromFields(tuple, aliases, DepartmentDTO.class));
			
			query.setParameter("idClient", params.getIdCliente());
			query.setParameter("idUser", params.getIdUser());
			query.setParameter("plate", params.getPlate());

			query.setFirstResult(pageable.isUnpaged() ? 0 : (int) pageable.getOffset());
			query.setMaxResults(pageable.isUnpaged() ? Integer.MAX_VALUE : pageable.getPageSize());
			
			response  = query.getResultList();

		} catch (Exception e) {
			log.error("ERROR " + e.getMessage());
			e.printStackTrace();
		}
		
		return response;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Long getCOUNTDepartmentsByIdUser(DepartmentByClientRequestDTO params) {
		TypedQuery<Long> query;
		
		Long response = null;

		try {
			String sqlQuery = """
				SELECT COUNT(*) FROM (
					SELECT 
						D.idDepartment 													AS idDepartment,
						D.name 															AS name,
						D.typeBalance 													AS typeBalance,
						(
							select
								count(*)
							from Vehicles v
							where v.department.idDepartment = D.idDepartment and
							v.vehicleStatus = 'A'
							(:plate IS NULL OR v.plate = :plate)
						) 																AS totalTarjetas,
						D.idPadre 														AS idPadre
					FROM Departments D, DepartmentsUsersRel R
					WHERE D.departmentStatus <> "E" AND
					R.department.idDepartment = D.idDepartment AND
					(:idUser IS NULL OR R.user.idUser = :idUser) AND
					(:idClient IS NULL OR D.client.idClient = :idClient)
				) AS query
			""";

			query = (TypedQuery<Long>) entityManager.createQuery(sqlQuery);

			query.setParameter("idClient", params.getIdCliente());
			query.setParameter("idUser", params.getIdUser());

			response  = query.getSingleResult();

		} catch (Exception e) {
			log.error("ERROR " + e.getMessage());
			e.printStackTrace();
		}
		
		return response;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DepartmentDTO> getDeptoHijosRolUser(DepartmentRequestDTO params) {
		TypedQuery<DepartmentDTO> query;
		
		List<DepartmentDTO> response = null;

		try {
			String sqlQuery = """
				select
					d.idDepartment 						AS idDepartment,
					d.client.idClient 					AS idClient,
					d.name 								AS name,
					d.typeBalance 						AS typeBalance,
					d.idPadre 							AS idPadre, 
					d.departmentStatus 					AS departmentStatus
				from
				Departments d
				left join Users u on u.idUser = :idUser and :rolUser = 2
				left join DepartmentsUsersRel du on du.user.idUser = u.idUser and :rolUser = 2
				left join Clients c on c.idClient = d.client.idClient 
				WHERE d.idPadre = :idDepartment AND
				(:rolUser = 2 and du.department.idDepartment = d.idDepartment) and
				(:rolUser = 2 and u.status <> 'E')
				and d.departmentStatus <> 'E'
				and d.client.idClient = :idClient
			""";

			query = entityManager.createQuery(sqlQuery, DepartmentDTO.class)
			.unwrap(Query.class)
			.setTupleTransformer((tuple, aliases) -> DbUtils.objFromFields(tuple, aliases, DepartmentDTO.class));
			
			query.setParameter("idUser", params.getIdUser());
			query.setParameter("idClient", params.getIdClient());
			query.setParameter("rolUser", params.getRolUser());
			query.setParameter("idDepartment", params.getIdDepartment());

			response  = query.getResultList();

		} catch (Exception e) {
			log.error("ERROR " + e.getMessage());
			e.printStackTrace();
		}
		
		return response;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Long getCOUNTDeptoHijosRolUser(DepartmentRequestDTO params) {
		TypedQuery<Long> query;
		
		Long response = null;

		try {
			String sqlQuery = """
				SELECT
					COUNT(*)
				FROM
				Departments d
				left join Users u ON u.idUser = :idUser AND :rolUser = 2
				left join DepartmentsUsersRel du ON du.user.idUser = u.idUser AND :rolUser = 2
				left join Clients c ON c.idClient = d.client.idClient 
				WHERE d.idPadre = :idDepartment AND
				(:rolUser = 2 AND du.department.idDepartment = d.idDepartment) AND
				(:rolUser = 2 AND u.status <> 'E')
				AND d.departmentStatus <> 'E'
				AND d.client.idClient = :idClient
			""";

			query = (TypedQuery<Long>) entityManager.createQuery(sqlQuery);
			
			query.setParameter("idUser", params.getIdUser());
			query.setParameter("idClient", params.getIdClient());
			query.setParameter("rolUser", params.getRolUser());
			query.setParameter("idDepartment", params.getIdDepartment());

			response  = query.getSingleResult();

		} catch (Exception e) {
			log.error("ERROR " + e.getMessage());
			e.printStackTrace();
		}
		
		return response;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DepartmentMovementDTO> getListDepartmentMovement(ListMovimientosDeptoCtrlRequestDTO params,
			Pageable pageable) {
		
		TypedQuery<DepartmentMovementDTO> query;
		
		List<DepartmentMovementDTO> response = null;

		try {
			String sqlQuery = """
					SELECT
							d.idDepartment as idDepartment, 
							d.name as nameDepartment,
						    dm.idMov as idMov,
					        dm.movement as movement,
						    dm.dateIn as dateIn,
					        dm.documentType as documentType,
							dm.monto as monto,
							dm.username as username, 
							dm.productName as productName,
							dm.idRef as idRef
					FROM Departments d 
					INNER JOIN DepartmentMovement dm on d.idDepartment=dm.idDepartment
					WHERE
						(:idDepartment IS NULL OR d.idDepartment = :idDepartment) AND
						(:idClient IS NULL OR d.client.idClient = :idClient) AND
						(:movement IS NULL OR dm.movement ILIKE :movement) AND
						(:productName IS NULL OR dm.productName ILIKE :productName) AND
						(:username IS NULL OR dm.username ILIKE :username) AND
						(:documentType IS NULL OR dm.documentType ILIKE :documentType) AND
						(CAST(:dateFirstFormatted as text) IS NULL or dm.dateIn >= CAST(CAST(:dateFirstFormatted as text) as date)) AND 
						(CAST(:dateEndFormatted as text) IS NULL or dm.dateIn <= CAST(CAST(:dateEndFormatted as text) as date))
			""";

			query = entityManager.createQuery(sqlQuery, DepartmentMovementDTO.class)
			.unwrap(Query.class)
			.setTupleTransformer((tuple, aliases) -> DbUtils.objFromFields(tuple, aliases, DepartmentMovementDTO.class));
			
			query.setParameter("idDepartment", params.getIdDepartment());
			query.setParameter("idClient", params.getIdClient());
			query.setParameter("movement", Functions.iLikeOrNull(params.getMovement()));
			query.setParameter("productName", Functions.iLikeOrNull(params.getProductName()));
			query.setParameter("username", Functions.iLikeOrNull(params.getUsername()));
			query.setParameter("documentType", Functions.iLikeOrNull(params.getDocumentType()));
			query.setParameter("dateFirstFormatted", params.getDateFirstFormatted());
			query.setParameter("dateEndFormatted", params.getDateEndFormatted());
			
			query.setFirstResult(pageable.isUnpaged() ? 0 : (int) pageable.getOffset());
			query.setMaxResults(pageable.isUnpaged() ? Integer.MAX_VALUE : pageable.getPageSize());

			response  = query.getResultList();

		} catch (Exception e) {
			log.error("ERROR " + e.getMessage());
			e.printStackTrace();
		}
		
		return response;
		
		
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public Long countListDepartmentMovement(ListMovimientosDeptoCtrlRequestDTO params) {
		TypedQuery<Long> query;
		
		Long response = null;

		try {
			String sqlQuery = """
				SELECT
					COUNT(*)
				FROM Departments d 
					INNER JOIN DepartmentMovement dm on d.idDepartment=dm.idDepartment
					WHERE
						(:idDepartment IS NULL OR d.idDepartment = :idDepartment) AND
						(:idClient IS NULL OR d.client.idClient = :idClient) AND
						(:movement IS NULL OR dm.movement ILIKE :movement) AND
						(:productName IS NULL OR dm.productName ILIKE :productName) AND
						(:username IS NULL OR dm.username ILIKE :username) AND
						(:documentType IS NULL OR dm.documentType ILIKE :documentType) AND
						(CAST(:dateFirstFormatted as text) IS NULL or dm.dateIn >= CAST(CAST(:dateFirstFormatted as text) as date)) AND 
						(CAST(:dateEndFormatted as text) IS NULL or dm.dateIn <= CAST(CAST(:dateEndFormatted as text) as date))
			""";

			query = (TypedQuery<Long>) entityManager.createQuery(sqlQuery);
			
			query.setParameter("idDepartment", params.getIdDepartment());
			query.setParameter("idClient", params.getIdClient());
			query.setParameter("movement", Functions.iLikeOrNull(params.getMovement()));
			query.setParameter("productName", Functions.iLikeOrNull(params.getProductName()));
			query.setParameter("username", Functions.iLikeOrNull(params.getUsername()));
			query.setParameter("documentType", Functions.iLikeOrNull(params.getDocumentType()));
			query.setParameter("dateFirstFormatted", params.getDateFirstFormatted());
			query.setParameter("dateEndFormatted", params.getDateEndFormatted());

			response  = query.getSingleResult();

		} catch (Exception e) {
			log.error("ERROR " + e.getMessage());
			e.printStackTrace();
		}
		
		return response;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DepartmentProductFinderDTO> getListProductDepartment(ProductDepartmentRequestDTO params,
			Pageable pageable) {
		
		TypedQuery<DepartmentProductFinderDTO> query;
		
		List<DepartmentProductFinderDTO> response = null;

		try {
			
			StringBuilder sqlQuery = new StringBuilder("""
					
					SELECT  DISTINCT
			  		D.idDepartment as idDepartment,
			  		D.name as nameDepartment,
					V.documentType as documentType,
					P.name as nameProduct,
					P.productCode as productCode,
					P.productStatus as statusProduct,
					COALESCE(PD.remainingAmount, 0,PD.remainingAmount) as remainingAmount,
					D.typeBalance as typeBalance
		  		FROM Departments D
				JOIN Vehicles V ON D.idDepartment = V.department.idDepartment AND V.vehicleStatus = 'A'
				LEFT JOIN VehicleCard VC ON VC.vehicle.idVehicle = V.idVehicle
				LEFT JOIN Card CA ON CA.idCard = VC.card.idCard AND CA.cardStatus = 'A'
				JOIN Products P ON P.productCode = V.product.productCode AND P.productStatus = 'A'
				LEFT JOIN ProductsDepartmentsRel PD ON D.idDepartment = PD.idDepartment AND P.productCode = PD.productCode AND PD.documentType = V.documentType
		  		WHERE
		  		(:idClient IS NULL OR D.client.idClient = :idClient) AND
		  		(:idDepartment IS NULL OR PD.idDepartment = :idDepartment) AND
		  		(:productCode IS NULL OR PD.productCode = :productCode) AND
		  		(:documentType IS NULL OR V.documentType = :documentType) AND
		  		D.departmentStatus ILIKE 'A' 
			""") ;
			
			if(params.getIsPadre() != null && params.getIsPadre()) {
				sqlQuery.append("""
						GROUP BY
					D.idDepartment,
			  		D.name,
					V.documentType,
					P.name,
					P.productCode,
					P.productStatus,
					D.typeBalance
						""");
			}
			
			
//			String sqlQuery = """
//				SELECT  DISTINCT
//			  		D.idDepartment as idDepartment,
//			  		D.name as nameDepartment,
//					V.documentType as documentType,
//					P.name as nameProduct,
//					P.productCode as productCode,
//					P.productStatus as statusProduct,
//					COALESCE(PD.remainingAmount, 0,PD.remainingAmount) as remainingAmount,
//					D.typeBalance as typeBalance
//		  		FROM Departments D
//				JOIN Vehicles V ON D.idDepartment = V.department.idDepartment AND V.vehicleStatus = 'A'
//				LEFT JOIN VehicleCard VC ON VC.vehicle.idVehicle = V.idVehicle
//				LEFT JOIN Card CA ON CA.idCard = VC.card.idCard AND CA.cardStatus = 'A'
//				JOIN Products P ON P.productCode = V.product.productCode AND P.productStatus = 'A'
//				LEFT JOIN ProductsDepartmentsRel PD ON D.idDepartment = PD.idDepartment AND P.productCode = PD.productCode AND PD.documentType = V.documentType
//		  		WHERE
//		  		(:idClient IS NULL OR D.client.idClient = :idClient) AND
//		  		(:idDepartment IS NULL OR PD.idDepartment = :idDepartment) AND
//		  		(:productCode IS NULL OR PD.productCode = :productCode) AND
//		  		(:documentType IS NULL OR V.documentType = :documentType) AND
//		  		D.departmentStatus ILIKE 'A' AND
//		  		(:isPadre = TRUE OR 
//					 GROUP BY
//					D.idDepartment,
//			  		D.name,
//					V.documentType,
//					P.name,
//					P.productCode,
//					P.productStatus,
//					D.typeBalance
//		  		)
//					 
//			""";

			query = entityManager.createQuery(sqlQuery.toString(), DepartmentProductFinderDTO.class)
					.unwrap(Query.class)
					.setTupleTransformer((tuple, aliases) -> DbUtils.objFromFields(tuple, aliases, DepartmentProductFinderDTO.class));
					
			query.setParameter("idDepartment", params.getIdDepartment());
			query.setParameter("idClient", params.getIdClient());
			query.setParameter("productCode", params.getProductCode());
			query.setParameter("documentType", params.getDocumentType());
			
			query.setFirstResult(pageable.isUnpaged() ? 0 : (int) pageable.getOffset());
			query.setMaxResults(pageable.isUnpaged() ? Integer.MAX_VALUE : pageable.getPageSize());

			response  = query.getResultList();

		} catch (Exception e) {
			log.error("ERROR " + e.getMessage());
			e.printStackTrace();
		}
		
		return response;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DepartmentProductFinderDTO> getProductDepartmentTransf(ProductDepartmentDTO params) {
		TypedQuery<DepartmentProductFinderDTO> query;
		
		List<DepartmentProductFinderDTO> response = null;

		try {
			String sqlQuery = """
				select 
					a.idDepartment 		as idDepartment,
					a.productCode 		as productCode,
					a.remainingAmount 	as remainingAmount,
					a.documentType 		as documentType,
					b.name 				as nameProduct,
					b.productStatus 	as statusProduct
				from ProductsDepartmentsRel a 
				inner join Products b on a.productCode= b.productCode
				where b.productStatus = "A"
					 (:idDepartment IS NULL OR a.idDepartment = :idDepartment) AND
					 (:documentType IS NULL OR a.documentType ILIKE :documentType) AND
					 (:productCode IS NULL OR a.productCode = :productCode) 
				order by a.productCode
					
			""";


			query = entityManager.createQuery(sqlQuery, DepartmentProductFinderDTO.class)
			.unwrap(Query.class)
			.setTupleTransformer((tuple, aliases) -> DbUtils.objFromFields(tuple, aliases, DepartmentProductFinderDTO.class));
			
			query.setParameter("idDepartment", params.getIdDepartment());
			query.setParameter("documentType", Functions.iLikeOrNull(params.getDocumentType()));
			query.setParameter("productCode", params.getProductCode());

			query.setFirstResult(0);
			query.setMaxResults(Integer.MAX_VALUE);
			
			response  = query.getResultList();

		} catch (Exception e) {
			log.error("ERROR " + e.getMessage());
			e.printStackTrace();
		}
		
		return response;
	}

}
