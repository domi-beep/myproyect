package com.evertecinc.b2c.enex.client.dao.extended.impl;

import java.util.List;

import org.hibernate.query.Query;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.evertecinc.b2c.enex.classes.utils.db.DbUtils;
import com.evertecinc.b2c.enex.client.dao.extended.IOcHeaderRepo;
import com.evertecinc.b2c.enex.client.model.dto2.ClientMonitorDTO;
import com.evertecinc.b2c.enex.client.model.dto2.MonitorOcHeaderDTO;
import com.evertecinc.b2c.enex.client.model.dto2.requests.ClientMonitorRequestDTO;
import com.evertecinc.b2c.enex.client.model.dto2.requests.MonitorOCRequestDTO;
import com.evertecinc.b2c.enex.utils.functions.Functions;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Repository
@RequiredArgsConstructor
@Slf4j
public class OcHeaderRepoImpl implements IOcHeaderRepo {

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
	public List<MonitorOcHeaderDTO> getListOCHeader(MonitorOCRequestDTO params, Pageable paging) {
		TypedQuery<MonitorOcHeaderDTO> query;
		List<MonitorOcHeaderDTO> response = null;
		
		try {
			String sqlQuery = new String("""
					SELECT 
						och.idOc as idOc, 
						och.number as numeroOc, 
						c.idClient as idClient, 
						c.upi as rutCliente, 
						c.name as nombre, 
						och.status as estado,
						och.crtDate as fechaIngreso,
						och.total as monto,
						och.invoice as numFactura,
						oci.docType as tipoDoc,
						concat(u.name, ' ', u.firstName) as nombreUsuario
							from OcHeader och
							join Clients c on och.idClient = c.idClient
							join OcItem oci on oci.idOc = och.idOc
							join Users u on u.idUser = och.idUser
							WHERE
							(:nomonitorOC IS NULL OR c.clientType ILIKE 'SCS')
							AND (:idClient IS NULL OR och.idClient = :idClient)
							AND (:numOC IS NULL OR och.number ILIKE :numOC)
							AND (:estadoDocT IS NULL OR och.status ILIKE :estadoDocT)
							AND (:rutCliente IS NULL OR c.upi ILIKE :rutCliente)
							AND (:nombreCliente IS NULL OR c.name ILIKE :nombreCliente)
							AND (:numFactura IS NULL OR och.invoice = :numFactura)
							group by
								och.idOc, 
								och.number, 
								c.idClient, 
								c.upi, 
								c.name, 
								och.status, 
								och.crtDate, 
								och.total, 
								och.invoice, 
								oci.docType, 
								u.name, 
								u.firstName
			""") ;
			
			if(paging.getSort() != null)
				sqlQuery += Functions.getSortString(paging.getSort(), MonitorOcHeaderDTO.class);
			
			query = entityManager.createQuery(sqlQuery.toString(), MonitorOcHeaderDTO.class)
			.unwrap(Query.class)
			.setTupleTransformer((tuple, aliases) -> DbUtils.objFromFields(tuple, aliases, MonitorOcHeaderDTO.class));
			
			query.setParameter("nomonitorOC", params.getNomonitorOC() == null ? null : params.getNomonitorOC());
			query.setParameter("idClient", params.getIdClient() == null ? null : params.getIdClient());
			query.setParameter("numOC", params.getNumOC() == null ? null : "%"+params.getNumOC()+"%");
			query.setParameter("estadoDocT", params.getEstadoDocT() == null ? null : "%"+params.getEstadoDocT()+"%");
			query.setParameter("rutCliente", params.getRutCliente() == null ? null : "%"+params.getRutCliente()+"%");
			query.setParameter("nombreCliente", params.getNombreCliente() == null ? null : "%"+params.getNombreCliente()+"%");
			query.setParameter("numFactura", params.getNumFactura() == null ? null : "%"+params.getNumFactura()+"%");

			query.setFirstResult(paging.isUnpaged() ? 0 : (int) paging.getOffset());
			query.setMaxResults(paging.isUnpaged() ? Integer.MAX_VALUE : paging.getPageSize());
			
			response  = query.getResultList();
			
		}catch(Exception ex) {
			log.error("ERROR " + ex.getMessage());
			ex.printStackTrace();
		}
		
		return response;
	}
	@SuppressWarnings("unchecked")
	@Override
	public Long countGetListOCHeader(MonitorOCRequestDTO params, Pageable paging) {
		TypedQuery<Long> query;
		Long response = null;
		
		try {
			String sqlQuery = new String("""
					SELECT COUNT(*) FROM
					(
					SELECT 
						och.idOc as idOc, 
						och.number as numeroOc, 
						c.idClient as idClient, 
						c.upi as rutCliente, 
						c.name as nombre, 
						och.status as estado,
						och.crtDate as fechaIngreso,
						och.total as monto,
						och.invoice as numFactura,
						oci.docType as tipoDoc,
						concat(u.name, ' ', u.firstName) as nombreUsuario
							from OcHeader och
							join Clients c on och.idClient = c.idClient
							join OcItem oci on oci.idOc = och.idOc
							join Users u on u.idUser = och.idUser
							WHERE
							(:nomonitorOC IS NULL OR c.clientType ILIKE 'SCS')
							AND (:idClient IS NULL OR och.idClient = :idClient)
							AND (:numOC IS NULL OR och.number ILIKE :numOC)
							AND (:estadoDocT IS NULL OR och.status ILIKE :estadoDocT)
							AND (:rutCliente IS NULL OR c.upi ILIKE :rutCliente)
							AND (:nombreCliente IS NULL OR c.name ILIKE :nombreCliente)
							AND (:numFactura IS NULL OR och.invoice = :numFactura)
							group by
								och.idOc, 
								och.number, 
								c.idClient, 
								c.upi, 
								c.name, 
								och.status, 
								och.crtDate, 
								och.total, 
								och.invoice, 
								oci.docType, 
								u.name, 
								u.firstName
						)datos
			""") ;
			
			query = (TypedQuery<Long>) entityManager.createQuery(sqlQuery);
			
			query.setParameter("nomonitorOC", params.getNomonitorOC() == null ? null : params.getNomonitorOC());
			query.setParameter("idClient", params.getIdClient() == null ? null : params.getIdClient());
			query.setParameter("numOC", params.getNumOC() == null ? null : "%"+params.getNumOC()+"%");
			query.setParameter("estadoDocT", params.getEstadoDocT() == null ? null : "%"+params.getEstadoDocT()+"%");
			query.setParameter("rutCliente", params.getRutCliente() == null ? null : "%"+params.getRutCliente()+"%");
			query.setParameter("nombreCliente", params.getNombreCliente() == null ? null : "%"+params.getNombreCliente()+"%");
			query.setParameter("numFactura", params.getNumFactura() == null ? null : "%"+params.getNumFactura()+"%");

			response  = query.getSingleResult();
			
		}catch(Exception ex) {
			log.error("ERROR " + ex.getMessage());
			ex.printStackTrace();
		}
		
		return response;
	}


}
