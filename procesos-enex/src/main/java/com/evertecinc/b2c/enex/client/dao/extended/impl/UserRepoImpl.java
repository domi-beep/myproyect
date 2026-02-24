package com.evertecinc.b2c.enex.client.dao.extended.impl;

import java.util.List;

import org.hibernate.query.Query;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.evertecinc.b2c.enex.classes.utils.db.DbUtils;
import com.evertecinc.b2c.enex.client.dao.extended.IUserRepo;
import com.evertecinc.b2c.enex.client.model.dto.UserDTO;
import com.evertecinc.b2c.enex.client.model.dto2.ClientMonitorDTO;
import com.evertecinc.b2c.enex.client.model.dto2.ConfiguracionDTO;
import com.evertecinc.b2c.enex.client.model.dto2.requests.BusquedaUserRequestDTO;
import com.evertecinc.b2c.enex.client.model.dto2.requests.ClientMonitorRequestDTO;
import com.evertecinc.b2c.enex.client.model.dto2.requests.ListConfiguracionesJsonRequestDTO;
import com.evertecinc.b2c.enex.client.model.dto2.requests.ListUserByDeptoJsonRequestDTO;
import com.evertecinc.b2c.enex.utils.functions.Functions;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Repository
@RequiredArgsConstructor
@Slf4j
public class UserRepoImpl implements IUserRepo {

	@PersistenceContext
	private final EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public List<ClientMonitorDTO> getListClientByCriteria(ClientMonitorRequestDTO params, Pageable paging) {
		TypedQuery<ClientMonitorDTO> query;
		List<ClientMonitorDTO> response = null;
		
		try {
			String sqlQuery = new String("""
					SELECT
							c.idClient											AS idClient,
							c.name												AS name,
							c.upi												AS upi,
							c.legalName											AS legalName,
							c.commercialType									AS commercialType,
							c.contactName										AS contactName,
							c.contactPhone										AS contactPhone,
							c.contactEmail										AS contactEmail,
							c.contactJob										AS contactJob,
							c.accountJdeClient									AS accountJdeClient,
							c.accountJdeInvoice									AS accountJdeInvoice,
							c.accountJdeTicket									AS accountJdeTicket,
							c.clientStatus										AS clientStatus,
							c.clientType										AS clientType,
							c.dateIns											AS dateIns,
							c.dateUpd											AS dateUpd,
							c.addressStreetName									AS addressStreetName,
							c.addressStreetNumber								AS addressStreetNumber,
							c.addressDoorNumber									AS addressDoorNumber,
							c.addressDisStreetName								AS addressDisStreetName,
							c.addressDisStreetNumber							AS addressDisStreetNumber,
							c.addressDisDoorNumber								AS addressDisDoorNumber,
							c.addressIdArea										AS addressIdArea,
							c.addressIdRegion1									AS addressIdRegion1,
							c.addressDisIdArea									AS addressDisIdArea,
							c.addressDisIdRegion								AS addressDisIdRegion,
							c.addressDisIdCity									AS addressDisIdCity,
							c.addressIdCity1									AS addressIdCity1,
							c.codeorpakticket									AS codeorpakticket,
							c.codeorpakinvoice									AS codeorpakinvoice,
							c.legalUpi											AS legalUpi,
							c.legalCivilStatus									AS legalCivilStatus,
							c.legalProfession									AS legalProfession,
							c.legalAddress										AS legalAddress,
							c.legalPhone										AS legalPhone,
							c.legalNamePerson									AS legalNamePerson,
							c.legalEmail										AS legalEmail,
							ci2.name											AS direccionDespachoCityName,
							a2.name												AS direccionDespachoAreaName,
							r2.name												AS direccionDespachoRegionName,
							ci1.name											AS direccionComercialCityName,
							a1.name												AS direccionComercialAreaName,
							r1.name												AS direccionComercialRegionName
					FROM Clients c
					JOIN Area a1 ON a1.idArea = c.addressIdArea 
					JOIN Area a2 ON a2.idArea = c.addressDisIdArea
					JOIN Region r1 ON r1.idRegion = c.addressIdRegion1 
					JOIN Region r2 ON  r2.idRegion = c.addressDisIdRegion
					JOIN City ci1 ON ci1.idCity = c.addressIdCity1 
					JOIN City ci2 ON ci2.idCity = c.addressDisIdCity
					WHERE (c.clientStatus <> 'E') AND
					(:upi IS NULL OR c.upi ILIKE :upi) AND
					(:legalName IS NULL OR c.legalName ILIKE :legalName) AND
					(:clientStatus IS NULL OR c.clientStatus = :clientStatus) AND
					(:clientType IS NULL OR c.clientType = :clientType)
			""") ;
			
			if(paging.getSort() != null)
				sqlQuery += Functions.getSortString(paging.getSort(), ClientMonitorDTO.class);
			
			query = entityManager.createQuery(sqlQuery.toString(), ClientMonitorDTO.class)
			.unwrap(Query.class)
			.setTupleTransformer((tuple, aliases) -> DbUtils.objFromFields(tuple, aliases, ClientMonitorDTO.class));
			
			query.setParameter("upi", params.getUpi() == null ? null : "%"+params.getUpi()+"%");
			query.setParameter("legalName", params.getLegalName() == null ? null : "%"+params.getLegalName()+"%");
			query.setParameter("clientStatus", params.getClientStatus());
			query.setParameter("clientType", params.getClientType());

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
	public Long getCountListClientByCriteria(ClientMonitorRequestDTO params, Pageable paging) {
		TypedQuery<Long> query;
		Long response = null;
		
		try {
			String sqlQuery = new String("""
					SELECT COUNT(*) FROM Clients c
					JOIN Area a1 ON a1.idArea = c.addressIdArea
					JOIN Area a2 ON a2.idArea = c.addressDisIdArea
					JOIN Region r1 ON r1.idRegion = c.addressIdRegion1
					JOIN Region r2 ON r2.idRegion = c.addressDisIdRegion
					JOIN City ci1 ON ci1.idCity = c.addressIdCity1
					JOIN City ci2 ON ci2.idCity = c.addressDisIdCity
					WHERE c.clientStatus <> 'E'
					AND (:upi IS NULL OR c.upi ILIKE :upi)
					AND (:legalName IS NULL OR c.legalName ILIKE :legalName)
					AND (:clientStatus IS NULL OR c.clientStatus = :clientStatus)
					AND (:clientType IS NULL OR c.clientType = :clientType)
			""");

			query = (TypedQuery<Long>) entityManager.createQuery(sqlQuery);
			
			query.setParameter("upi", params.getUpi() == null ? null : "%"+params.getUpi()+"%");
			query.setParameter("legalName", params.getLegalName() == null ? null : "%"+params.getLegalName()+"%");
			query.setParameter("clientStatus", params.getClientStatus());
			query.setParameter("clientType", params.getClientType());
			
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
	public List<UserDTO> getListadoUsersByClient(BusquedaUserRequestDTO params, Pageable paging) {
		
		TypedQuery<UserDTO> query;
		List<UserDTO> response = null;
		
		try {
			StringBuilder sqlQuery = new StringBuilder("""
					
					SELECT
						U.idUser as idUser,
						U.rut as rut,
						C.clientType as tipoCliente,
						U.username as username,
						U.name as name,
						U.firstName as firstName,
						U.lastName as lastName,
						U.role as role,
						U.email as email,
						U.status as status,
						U.password as password,
						R.client.idClient AS idClient
						FROM Users U
						JOIN ClientsUsersRel R ON R.id.idUser = U.idUser
						JOIN Clients C ON C.idClient = R.id.idClient
							
			""") ;
			
			if(params.getIdDepartamento() != null) {
				sqlQuery.append("""
						LEFT JOIN DepartmentsUsersRel DU ON DU.id.idUser = R.id.idUser
						""");
			}
			
			sqlQuery.append("""
					WHERE C.clientStatus <> 'E' 
						AND U.status <> 'E'
						AND (U.role < 3 OR U.role = 15)
						AND (:idClient IS NULL OR C.idClient = :idClient)
						AND (:upi IS NULL OR C.upi ILIKE :upi)
						AND (:ClientType IS NULL OR C.clientType ILIKE :ClientType)
						AND (:idUser IS NULL OR U.idUser = :idUser)
						AND (:rut IS NULL OR U.rut ILIKE :rut)
						AND (:name IS NULL OR U.name ILIKE :name)
						AND (:firstName IS NULL OR U.firstName ILIKE :firstName)
						AND (:lastName IS NULL OR U.lastName ILIKE :lastName)
						AND (:email IS NULL OR U.email ILIKE :email)
						AND (:status IS NULL OR U.status ILIKE :status)
						AND (:role IS NULL OR U.role = :role)
						
					""");
			
			if(params.getIdDepartamento() != null) {
				sqlQuery.append("""
						AND (:idDepartment IS NULL OR DU.id.idDepartment = :idDepartment)
						""");
			}
			
			if(paging.getSort() != null)
				sqlQuery.append(Functions.getSortString(paging.getSort(), UserDTO.class));
			
			query = entityManager.createQuery(sqlQuery.toString(), UserDTO.class)
			.unwrap(Query.class)
			.setTupleTransformer((tuple, aliases) -> DbUtils.objFromFields(tuple, aliases, UserDTO.class));
			
			query.setParameter("idClient", params.getIdClient() == null ? null : params.getIdClient());
			query.setParameter("upi", params.getUpi() == null ? null : "%"+params.getUpi()+"%");
			query.setParameter("ClientType", params.getTipoCliente() == null ? null : "%"+params.getTipoCliente()+"%");
			query.setParameter("idUser", params.getIdUser() == null ? null : params.getIdUser());
			query.setParameter("rut", params.getRutUsuario() == null ? null : "%"+params.getRutUsuario()+"%");
			query.setParameter("name", params.getNombre() == null ? null : "%"+params.getNombre()+"%");
			query.setParameter("firstName", params.getApellidoPaterno() == null ? null : "%"+params.getApellidoPaterno()+"%");
			query.setParameter("lastName", params.getApellidoMaterno() == null ? null : "%"+params.getApellidoMaterno()+"%");
			query.setParameter("email", params.getEmail() == null ? null : "%"+params.getEmail()+"%");
			query.setParameter("status", params.getEstado() == null ? null : params.getEstado());
			query.setParameter("role", params.getRole() == null ? null : params.getRole());
			
			if(params.getIdDepartamento() != null) {
				query.setParameter("idDepartment", params.getIdDepartamento() == null ? null : params.getIdDepartamento());
			}
			
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
	public Long countGetListadoUsersByClient(BusquedaUserRequestDTO params, Pageable paging) {
		TypedQuery<Long> query;
		Long response = null;
		
		try {
			StringBuilder sqlQuery = new StringBuilder("""
					
					SELECT COUNT(*)
						FROM Users U
						JOIN ClientsUsersRel R ON R.id.idUser = U.idUser
						JOIN Clients C ON C.idClient = R.id.idClient
							
			""") ;
			
			if(params.getIdDepartamento() != null) {
				sqlQuery.append("""
						LEFT JOIN DepartmentsUsersRel DU ON DU.id.idUser = R.id.idUser
						""");
			}
			
			sqlQuery.append("""
					WHERE C.clientStatus <> 'E' 
						AND U.status <> 'E'
						AND (U.role < 3 OR U.role = 15)
						AND (:idClient IS NULL OR C.idClient = :idClient)
						AND (:upi IS NULL OR C.upi ILIKE :upi)
						AND (:ClientType IS NULL OR C.clientType ILIKE :ClientType)
						AND (:idUser IS NULL OR U.idUser = :idUser)
						AND (:rut IS NULL OR U.rut ILIKE :rut)
						AND (:name IS NULL OR U.name ILIKE :name)
						AND (:firstName IS NULL OR U.firstName ILIKE :firstName)
						AND (:lastName IS NULL OR U.lastName ILIKE :lastName)
						AND (:email IS NULL OR U.email ILIKE :email)
						AND (:status IS NULL OR U.status ILIKE :status)
						AND (:role IS NULL OR U.role = :role)
						
					""");
			
			if(params.getIdDepartamento() != null) {
				sqlQuery.append("""
						AND (:idDepartment IS NULL OR DU.id.idDepartment = :idDepartment)
						""");
			}
			
			query = (TypedQuery<Long>) entityManager.createQuery(sqlQuery.toString());
			
			query.setParameter("idClient", params.getIdClient() == null ? null : params.getIdClient());
			query.setParameter("upi", params.getUpi() == null ? null : "%"+params.getUpi()+"%");
			query.setParameter("ClientType", params.getTipoCliente() == null ? null : "%"+params.getTipoCliente()+"%");
			query.setParameter("idUser", params.getIdUser() == null ? null : params.getIdUser());
			query.setParameter("rut", params.getRutUsuario() == null ? null : "%"+params.getRutUsuario()+"%");
			query.setParameter("name", params.getNombre() == null ? null : "%"+params.getNombre()+"%");
			query.setParameter("firstName", params.getApellidoPaterno() == null ? null : "%"+params.getApellidoPaterno()+"%");
			query.setParameter("lastName", params.getApellidoMaterno() == null ? null : "%"+params.getApellidoMaterno()+"%");
			query.setParameter("email", params.getEmail() == null ? null : "%"+params.getEmail()+"%");
			query.setParameter("status", params.getEstado() == null ? null : params.getEstado());
			query.setParameter("role", params.getRole() == null ? null : params.getRole());
			
			if(params.getIdDepartamento() != null) {
				query.setParameter("idDepartment", params.getIdDepartamento() == null ? null : params.getIdDepartamento());
			}
			
			response  = query.getSingleResult();
			
		}catch(Exception ex) {
			log.error("ERROR " + ex.getMessage());
			ex.printStackTrace();
		}
		
		
		return response;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserDTO> getListUserByDepartment(ListUserByDeptoJsonRequestDTO params, Pageable paging) {
		TypedQuery<UserDTO> query;
		List<UserDTO> response = null;
		
		try {
			String sqlQuery = new String("""
					SELECT 
					 b.idUser as idUser,
					 b.name as name,
					 b.lastName as lastName,
					 b.firstName as firstName,
					 b.role as role
		    FROM Users b
			INNER JOIN DepartmentsUsersRel d on b.idUser = d.id.idUser
			INNER JOIN Departments a on  d.id.idDepartment = a.idDepartment
			WHERE 		
					a.departmentStatus <> 'E' AND 
					b.status <> 'E' AND 
					(:idDepartment IS NULL OR d.id.idDepartment = :idDepartment)
			""") ;
			
			if(paging.getSort() != null)
				sqlQuery += Functions.getSortString(paging.getSort(), UserDTO.class);
			
			query = entityManager.createQuery(sqlQuery.toString(), UserDTO.class)
			.unwrap(Query.class)
			.setTupleTransformer((tuple, aliases) -> DbUtils.objFromFields(tuple, aliases, UserDTO.class));
			
			query.setParameter("idDepartment", params.getIdDepartment());

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
	public Long countListUserByDepartment(ListUserByDeptoJsonRequestDTO params, Pageable pages) {
		TypedQuery<Long> query;
		Long response = null;
		
		try {
			String sqlQuery = new String("""
					SELECT COUNT(*)
		    FROM Users b
			INNER JOIN DepartmentsUsersRel d on b.idUser = d.id.idUser
			INNER JOIN Departments a on  d.id.idDepartment = a.idDepartment
			WHERE 		
					a.departmentStatus <> 'E' AND 
					b.status <> 'E' AND 
					(:idDepartment IS NULL OR d.id.idDepartment = :idDepartment)
			""");

			query = (TypedQuery<Long>) entityManager.createQuery(sqlQuery);
			
			query.setParameter("idDepartment", params.getIdDepartment());
			
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
	public List<UserDTO> getListUsuariosByDeptosAndTipoCliente(BusquedaUserRequestDTO params, Pageable paging) {

		TypedQuery<UserDTO> query;
		List<UserDTO> response = null;

		try {
			String sqlQuery = """
					SELECT
						U.idUser 				AS idUser,
						U.rut 					AS rut,
						U.username 				AS username,
						U.name 					AS name,
						U.firstName 			AS firstName,
						U.lastName 				AS lastName,
						U.role 					AS role,
						U.email 				AS email,
						U.status 				AS status,
						U.password 				AS password,
						C.clientType 			AS tipoCliente
					FROM Users U
					INNER JOIN DepartmentsUsersRel DU ON U.idUser = DU.user.idUser
					INNER JOIN Departments D on DU.department.idDepartment = D.idDepartment
					JOIN ClientsUsersRel R on U.idUser = R.user.idUser
					JOIN Clients C on C.idClient = R.client.idClient and C.idClient = D.client.idClient
					WHERE D.departmentStatus <> 'E'
					AND U.status <> 'E'
					AND (U.role < 3 OR U.role = 15)
					AND (:idUser IS NULL OR (DU.department.idDepartment IN (
						SELECT
							D.idDepartment AS idDepartment
						FROM Departments D, DepartmentsUsersRel R
						WHERE D.departmentStatus <> 'E' AND
						R.department.idDepartment = D.idDepartment AND
						R.user.idUser = :idUser
					))) 
					AND (:rut IS NULL OR (REPLACE(U.rut, '-' ,'') ILIKE :rut)) 
					AND (:upi IS NULL OR (C.upi ILIKE :upi)) 
					AND (:name IS NULL OR (U.name ILIKE :name)) 
					AND (:firstName IS NULL OR (U.firstName ILIKE :firstName)) 
					AND (:lastName IS NULL OR (U.lastName ILIKE :lastName)) 
					AND (:email IS NULL OR (U.email ILIKE :email)) 
					AND (:status IS NULL OR (U.status = :status)) 
					AND (:role IS NULL OR (U.role = :role)) 
					AND (:clientType IS NULL OR (C.clientType = :clientType)) 
					AND (:idDepartment IS NULL OR (DU.department.idDepartment = :idDepartment)) 
					""";

			if(paging.getSort() != null)
				sqlQuery += Functions.getSortString(paging.getSort(), UserDTO.class);

			query = entityManager.createQuery(sqlQuery.toString(), UserDTO.class)
					.unwrap(Query.class)
					.setTupleTransformer((tuple, aliases) -> DbUtils.objFromFields(tuple, aliases, UserDTO.class));

			query.setParameter("idDepartment", params.getIdDepartamento());
			query.setParameter("upi", Functions.iLikeOrNull(params.getUpi()));
			query.setParameter("clientType", params.getTipoCliente());
			query.setParameter("idUser", params.getIdUser());
			query.setParameter("rut", Functions.iLikeOrNull(params.getRutUsuario()));
			query.setParameter("name", Functions.iLikeOrNull(params.getNombre()));
			query.setParameter("firstName", Functions.iLikeOrNull(params.getApellidoPaterno()));
			query.setParameter("lastName", Functions.iLikeOrNull(params.getApellidoMaterno()));
			query.setParameter("email", Functions.iLikeOrNull(params.getEmail()));
			query.setParameter("status", params.getEstado());
			query.setParameter("role", params.getRole());

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
	public Long getCOUNTListUsuariosByDeptosAndTipoCliente(BusquedaUserRequestDTO params) {
		
		TypedQuery<Long> query;
		Long response = null;
		
		try {
			String sqlQuery = """
					SELECT COUNT(*) FROM (
						SELECT
							U.idUser 				AS idUser,
							U.rut 					AS rut,
							U.username 				AS username,
							U.name 					AS name,
							U.firstName 			AS firstName,
							U.lastName 				AS lastName,
							U.role 					AS role,
							U.email 				AS email,
							U.status 				AS status,
							U.password 				AS password,
							C.clientType 			AS tipoCliente
						FROM Users U
						INNER JOIN DepartmentsUsersRel DU ON U.idUser = DU.user.idUser
						INNER JOIN Departments D on DU.department.idDepartment = D.idDepartment
						JOIN ClientsUsersRel R on U.idUser = R.user.idUser
						JOIN Clients C on C.idClient = R.client.idClient and C.idClient = D.client.idClient
						WHERE D.departmentStatus <> 'E'
						AND U.status <> 'E'
						AND (U.role < 3 OR U.role = 15)
						AND (:idUser IS NULL OR (DU.department.idDepartment IN (
							SELECT
								D.idDepartment AS idDepartment
							FROM Departments D, DepartmentsUsersRel R
							WHERE D.departmentStatus <> 'E' AND
							R.department.idDepartment = D.idDepartment AND
							R.user.idUser = :idUser
						))) 
						AND (:rut IS NULL OR (REPLACE(U.rut, '-' ,'') ILIKE :rut)) 
						AND (:upi IS NULL OR (C.upi ILIKE :upi)) 
						AND (:name IS NULL OR (U.name ILIKE :name)) 
						AND (:firstName IS NULL OR (U.firstName ILIKE :firstName)) 
						AND (:lastName IS NULL OR (U.lastName ILIKE :lastName)) 
						AND (:email IS NULL OR (U.email ILIKE :email)) 
						AND (:status IS NULL OR (U.status = :status)) 
						AND (:role IS NULL OR (U.role = :role)) 
						AND (:clientType IS NULL OR (C.clientType = :clientType)) 
						AND (:idDepartment IS NULL OR (DU.department.idDepartment = :idDepartment))
					) AS query
					""";
			
			query = (TypedQuery<Long>) entityManager.createQuery(sqlQuery);
			
			query.setParameter("idDepartment", params.getIdDepartamento());
			query.setParameter("upi", Functions.iLikeOrNull(params.getUpi()));
			query.setParameter("clientType", params.getTipoCliente());
			query.setParameter("idUser", params.getIdUser());
			query.setParameter("rut", Functions.iLikeOrNull(params.getRutUsuario()));
			query.setParameter("name", Functions.iLikeOrNull(params.getNombre()));
			query.setParameter("firstName", Functions.iLikeOrNull(params.getApellidoPaterno()));
			query.setParameter("lastName", Functions.iLikeOrNull(params.getApellidoMaterno()));
			query.setParameter("email", Functions.iLikeOrNull(params.getEmail()));
			query.setParameter("status", params.getEstado());
			query.setParameter("role", params.getRole());
			
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
	public List<ConfiguracionDTO> getListConfiguracionesByCriterio(ListConfiguracionesJsonRequestDTO params,
			Pageable paging) {

		if (Functions.hasEmptyOrNull(params.getIdClient())) {
			log.error("Los parametros idClient no pueden ser nulos o vacios.");
			throw new IllegalArgumentException("Los parametros idClient no pueden ser nulos o vacios.");
		}

		TypedQuery<ConfiguracionDTO> query;
		List<ConfiguracionDTO> response = null;

		try {
			String sqlQuery = """
					SELECT 
					    c.idCard                               AS idCard,
					    c.cardnum                              AS cardnum,
					    c.version                               AS version,
					    v.plate                                 AS plate,
					    c.cardStatus                            AS cardStatus,
					    c.restrictionType                       AS restrictionType,
					    v.department.idDepartment                AS idDepartment,
					    v.idVehicle                             AS idVehicle,
					    CASE WHEN c.restrictionType = 'D' THEN d.restrType 
					         ELSE c.restrType 
					    END                                     AS restrType,
					    CASE WHEN c.restrictionType = 'D' THEN d.restrHinit 
					         ELSE c.restrHini
					    END                                     AS restrHini,
					    CASE WHEN c.restrictionType = 'D' THEN d.restrHend 
					         ELSE c.restrHend 
					    END                                     AS restrHend,
					    CASE WHEN c.restrictionType = 'D' THEN d.restrDailyMaxQuantity 
					         ELSE c.restrDailyMaxQuantity 
					    END                                     AS restrDailyMaxQuantity,
					    CASE WHEN c.restrictionType = 'D' THEN d.restrDailyMaxLoads 
					         ELSE c.restrDailyMaxLoads 
					    END                                     AS restrDailyMaxLoads,
					    CASE WHEN c.restrictionType = 'D' THEN d.restrAmountMax 
					         ELSE c.restrAmountMax 
					    END                                     AS restrAmountMax,
					    CASE WHEN c.restrictionType = 'D' THEN (
					        SELECT COUNT(sdc.department.idDepartment) 
					        FROM StationDepartmentConstraints sdc
					        WHERE sdc.department.idDepartment = v.department.idDepartment
					    ) ELSE (
					        SELECT COUNT(scc.card.idCard) 
					        FROM StationCardConstraint scc
					        WHERE scc.card.idCard = c.idCard
					    ) END                                   AS eds
					FROM Vehicles v
					LEFT JOIN VehicleCard vc ON v.idVehicle = vc.vehicle.idVehicle
					LEFT JOIN Card c ON vc.card.idCard = c.idCard
					LEFT JOIN Departments d ON v.department.idDepartment = d.idDepartment
					WHERE v.client.idClient = :idClient
					AND c.cardStatus NOT IN ('E', 'R', 'P')
					AND (:idDepartment IS NULL OR v.department.idDepartment = :idDepartment)
					AND (:deptos IS NULL OR v.department.idDepartment IN (:deptos))
					AND (:cardStatus IS NULL OR c.cardStatus = :cardStatus)
					AND (:configuracion IS NULL OR c.restrictionType = :configuracion)
					AND (
						:restriccion IS NULL OR
						(c.restrictionType = 'D' AND d.restrType = :restriccion) OR
						(c.restrictionType <> 'D' AND c.restrType = :restriccion)
					)
					""";

			if(paging.getSort() != null)
				sqlQuery += Functions.getSortString(paging.getSort(), ConfiguracionDTO.class);

			query = entityManager.createQuery(sqlQuery.toString(), ConfiguracionDTO.class)
					.unwrap(Query.class)
					.setTupleTransformer((tuple, aliases) -> DbUtils.objFromFields(tuple, aliases, ConfiguracionDTO.class));

			query.setParameter("idClient", params.getIdClient());
			query.setParameter("idDepartment", params.getIdDepartment());
			query.setParameter("deptos", params.getDeptos());
			query.setParameter("cardStatus", params.getCardStatus());
			query.setParameter("configuracion", params.getConfiguracion());
			query.setParameter("restriccion", params.getRestriccion());

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
	public Long getCOUNTListConfiguracionesByCriterio(ListConfiguracionesJsonRequestDTO params) {
		if (Functions.hasEmptyOrNull(params.getIdClient())) {
			log.error("Los parametros idClient no pueden ser nulos o vacios.");
			throw new IllegalArgumentException("Los parametros idClient no pueden ser nulos o vacios.");
		}

		TypedQuery<Long> query;
		Long response = null;

		try {
			String sqlQuery = """
					SELECT COUNT(*) FROM (
					SELECT 
					    c.idCard                               AS idCard,
					    c.cardnum                              AS cardnum,
					    c.version                               AS version,
					    v.plate                                 AS plate,
					    c.cardStatus                            AS cardStatus,
					    c.restrictionType                       AS restrictionType,
					    v.department.idDepartment                AS idDepartment,
					    v.idVehicle                             AS idVehicle,
					    CASE WHEN c.restrictionType = 'D' THEN d.restrType 
					         ELSE c.restrType 
					    END                                     AS restrType,
					    CASE WHEN c.restrictionType = 'D' THEN d.restrHinit 
					         ELSE c.restrHini
					    END                                     AS restrHini,
					    CASE WHEN c.restrictionType = 'D' THEN d.restrHend 
					         ELSE c.restrHend 
					    END                                     AS restrHend,
					    CASE WHEN c.restrictionType = 'D' THEN d.restrDailyMaxQuantity 
					         ELSE c.restrDailyMaxQuantity 
					    END                                     AS restrDailyMaxQuantity,
					    CASE WHEN c.restrictionType = 'D' THEN d.restrDailyMaxLoads 
					         ELSE c.restrDailyMaxLoads 
					    END                                     AS restrDailyMaxLoads,
					    CASE WHEN c.restrictionType = 'D' THEN d.restrAmountMax 
					         ELSE c.restrAmountMax 
					    END                                     AS restrAmountMax,
					    CASE WHEN c.restrictionType = 'D' THEN (
					        SELECT COUNT(sdc.department.idDepartment) 
					        FROM StationDepartmentConstraints sdc
					        WHERE sdc.department.idDepartment = v.department.idDepartment
					    ) ELSE (
					        SELECT COUNT(scc.card.idCard) 
					        FROM StationCardConstraint scc
					        WHERE scc.card.idCard = c.idCard
					    ) END                                   AS eds
					FROM Vehicles v
					LEFT JOIN VehicleCard vc ON v.idVehicle = vc.vehicle.idVehicle
					LEFT JOIN Card c ON vc.card.idCard = c.idCard
					LEFT JOIN Departments d ON v.department.idDepartment = d.idDepartment
					WHERE v.client.idClient = :idClient
					AND c.cardStatus NOT IN ('E', 'R', 'P')
					AND (:idDepartment IS NULL OR v.department.idDepartment = :idDepartment)
					AND (:deptos IS NULL OR v.department.idDepartment IN (:deptos))
					AND (:cardStatus IS NULL OR c.cardStatus = :cardStatus)
					AND (:configuracion IS NULL OR c.restrictionType = :configuracion)
					AND (
						:restriccion IS NULL OR
						(c.restrictionType = 'D' AND d.restrType = :restriccion) OR
						(c.restrictionType <> 'D' AND c.restrType = :restriccion)
					)
					) AS query
					""";

			query = (TypedQuery<Long>) entityManager.createQuery(sqlQuery);

			query.setParameter("idClient", params.getIdClient());
			query.setParameter("idDepartment", params.getIdDepartment());
			query.setParameter("deptos", params.getDeptos());
			query.setParameter("cardStatus", params.getCardStatus());
			query.setParameter("configuracion", params.getConfiguracion());
			query.setParameter("restriccion", params.getRestriccion());

			response  = query.getSingleResult();

		} catch(Exception ex) {
			log.error("ERROR " + ex.getMessage());
			ex.printStackTrace();
		}

		log.debug("response: "+response);
		return response;
	}

}
