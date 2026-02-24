package com.evertecinc.b2c.enex.client.dao.extended.impl;

import java.util.List;

import org.hibernate.query.Query;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.evertecinc.b2c.enex.classes.utils.db.DbUtils;
import com.evertecinc.b2c.enex.client.dao.extended.IClientRepo;
import com.evertecinc.b2c.enex.client.model.dto2.ClientMonitorDTO;
import com.evertecinc.b2c.enex.client.model.dto2.ReporteCargasUltMesesDTO;
import com.evertecinc.b2c.enex.client.model.dto2.ReporteComprasAndCargasDTO;
import com.evertecinc.b2c.enex.client.model.dto2.ReporteDetalleCargasDTO;
import com.evertecinc.b2c.enex.client.model.dto2.ReporteDetalleComprasCargasDTO;
import com.evertecinc.b2c.enex.client.model.dto2.ReporteListadoUsoEESSDTO;
import com.evertecinc.b2c.enex.client.model.dto2.ReporteUsoEESSDTO;
import com.evertecinc.b2c.enex.client.model.dto2.requests.ClientMonitorRequestDTO;
import com.evertecinc.b2c.enex.client.model.dto2.requests.ContraloriaClientsRequestDTO;
import com.evertecinc.b2c.enex.client.model.dto2.requests.ListCantidadClientesRequestDTO;
import com.evertecinc.b2c.enex.utils.functions.Functions;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ClientRepoImpl implements IClientRepo {

	@PersistenceContext
	private final EntityManager entityManager;

	@Override
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
	@Override
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

	
	
	@Override
	@SuppressWarnings("unchecked")
	public List<ClientMonitorDTO> getContraloriaClientsByCriterio(ContraloriaClientsRequestDTO params, Pageable paging) {
		TypedQuery<ClientMonitorDTO> query;
		List<ClientMonitorDTO> response = null;
		
		try {
			String sqlQuery = new String("""
				SELECT 
					rcc.idReporte 					AS idReporte,
					c.idClient 						AS idClient,
					c.name 							AS name,
					c.upi 							AS upi,
					c.legalName	    				AS legalName,
					c.commercialType 				AS commercialType,
					c.contactName					AS contactName,
					c.contactPhone					AS contactPhone, 
					c.contactEmail					AS contactEmail,
					c.contactJob					AS contactJob,
					c.accountJdeClient				AS accountJdeClient,
					c.accountJdeInvoice				AS accountJdeInvoice,
					c.accountJdeTicket				AS accountJdeTicket,
					c.clientStatus					AS clientStatus,
					c.clientType					AS clientType,
					c.dateIns						AS dateIns,
					c.dateUpd						AS dateUpd,
					c.addressStreetName				AS addressStreetName,
					c.addressStreetNumber			AS addressStreetNumber,
					c.addressDisStreetName			AS addressDisStreetName,
					c.legalUpi						AS legalUpi,
					c.legalCivilStatus				AS legalCivilStatus,
					c.legalProfession				AS legalProfession,
					c.legalAddress					AS legalAddress,
					c.legalPhone					AS legalPhone,
					c.legalNamePerson				AS legalNamePerson
				FROM ReporteContraloriaClientes rcc 
				JOIN Clients c ON rcc.client.idClient = c.idClient
				WHERE rcc.tipo = 'CON'
				AND (:idClient IS NULL OR c.idClient = :idClient)
			""") ;
			
			if(paging.getSort() != null)
				sqlQuery += Functions.getSortString(paging.getSort(), ClientMonitorDTO.class);
			
			query = entityManager.createQuery(sqlQuery.toString(), ClientMonitorDTO.class)
			.unwrap(Query.class)
			.setTupleTransformer((tuple, aliases) -> DbUtils.objFromFields(tuple, aliases, ClientMonitorDTO.class));
			
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
	public Long countContraloriaClientsByCriterio(ContraloriaClientsRequestDTO params, Pageable pageable) {
		TypedQuery<Long> query;
		Long response = null;
		
		try {
			String sqlQuery = new String("""
				SELECT COUNT(*)
				FROM ReporteContraloriaClientes rcc 
				JOIN Clients c ON rcc.client.idClient = c.idClient
				WHERE rcc.tipo = 'CON'
				AND (:idClient IS NULL OR c.idClient = :idClient)
			""");

			query = (TypedQuery<Long>) entityManager.createQuery(sqlQuery);
			
			query.setParameter("idClient", params.getIdClient());
			
			response  = query.getSingleResult();
			
		}catch(Exception ex) {
			log.error("ERROR " + ex.getMessage());
			ex.printStackTrace();
		}
		
		log.debug("response: "+response);
		return response;
	}
	
	@Override
	public Long countListClientByCriteria(ClientMonitorDTO criterio) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	@SuppressWarnings("unchecked")
	public List<ReporteUsoEESSDTO> getEESSUtilizadas(ListCantidadClientesRequestDTO params, Pageable paging) {
		TypedQuery<ReporteUsoEESSDTO> query;
		List<ReporteUsoEESSDTO> response = null;
		
		try {
			String sqlQuery = """
				    SELECT 
				        COALESCE(re.name, '') AS region, 
				        cm.stationCode AS codigo, 
				        COALESCE(st_subquery.nameAlias, '') AS direccion, 
				        COUNT(cm.card.idCard) AS cantidad, 
				        ABS(SUM(cm.monto)) AS monto, 
				        COALESCE(SUM(cm.quantity), 0) AS litros
				    FROM 
				        CardMovement cm
				    LEFT JOIN 
				        Card cd ON cd.idCard = cm.card.idCard
				    LEFT JOIN 
				        (SELECT DISTINCT stationCode AS stationCodeAlias, name AS nameAlias, area.idArea AS areaAlias FROM Stations) st_subquery 
				        ON st_subquery.stationCodeAlias = cm.stationCode
				    LEFT JOIN 
				        Area ar ON ar.idArea = st_subquery.areaAlias
				    LEFT JOIN 
				        Region re ON re.idRegion = ar.region.idRegion
				    LEFT JOIN 
				        Clients cl ON cd.client.idClient = cl.idClient
				    WHERE 
				        EXTRACT(MONTH FROM cm.dateIn) = EXTRACT(MONTH FROM CURRENT_DATE)
				        AND (:movimiento IS NULL OR cm.movement = :movimiento)
				        AND (:clientType IS NULL OR cl.clientType = :clientType)
				    GROUP BY 
				        cm.stationCode, re.name, st_subquery.nameAlias
				    ORDER BY 
				        cantidad DESC
				    LIMIT :limite
				""";
			
			if(paging.getSort() != null)
				sqlQuery += Functions.getSortString(paging.getSort(), ReporteUsoEESSDTO.class);
			
			query = entityManager.createQuery(sqlQuery.toString(), ReporteUsoEESSDTO.class)
			.unwrap(Query.class)
			.setTupleTransformer((tuple, aliases) -> DbUtils.objFromFields(tuple, aliases, ReporteUsoEESSDTO.class));
			
			query.setParameter("movimiento", params.getMovimiento());
			query.setParameter("clientType", params.getTipoCliente());
			query.setParameter("limite", params.getLimite());

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
	public List<ReporteListadoUsoEESSDTO> getListadoEESSUtilizadas(ListCantidadClientesRequestDTO params, Pageable paging) {
	    TypedQuery<ReporteListadoUsoEESSDTO> query;
	    List<ReporteListadoUsoEESSDTO> response = null;

	    try {
	        String sqlQuery = """
	            SELECT 
	                EXTRACT(MONTH FROM cm.dateIn) AS mes, 
	                EXTRACT(YEAR FROM cm.dateIn) AS anio, 
	                COALESCE(rg.name, '') AS region, 
	                cm.stationCode AS codigo, 
	                COALESCE(ar.name, '') AS comuna, 
	                COALESCE(st_subquery.nameAlias, '') AS estacion, 
	                COALESCE(ABS(SUM(cm.monto)), 0) AS monto, 
	                COUNT(cm.card.idCard) AS cantidad, 
	                COALESCE(SUM(cm.quantity), 0) AS litros, 
	                cm.productCode AS producto
	            FROM 
	                CardMovement cm
	            LEFT JOIN 
	        		(SELECT DISTINCT stationCode AS stationCodeAlias, name AS nameAlias, area.idArea AS areaAlias FROM Stations) st_subquery 
	                ON st_subquery.stationCodeAlias = cm.stationCode
	            LEFT JOIN 
	                Area ar ON ar.idArea = st_subquery.areaAlias
	            LEFT JOIN 
	                Region rg ON rg.idRegion = ar.region.idRegion
	            LEFT JOIN 
	                Card cd ON cd.idCard = cm.card.idCard
	            LEFT JOIN 
	                Clients cl ON cl.idClient = cd.client.idClient
	            WHERE 
	        		(CAST(:fechaIni as text) IS NULL or cm.dateIn >= CAST(CAST(:fechaIni as text) as date)) AND 
					(CAST(:fechaFin as text) IS NULL or cm.dateIn <= CAST(CAST(:fechaFin as text) as date))
	                AND cm.movement = :movimiento
	                AND cm.returnCode = '0'
	                AND (:tipoCliente IS NULL OR cl.clientType = :tipoCliente)
	            GROUP BY 
	                cm.stationCode, cm.productCode, mes, anio, rg.name, ar.name, st_subquery.nameAlias
	            ORDER BY 
	                cm.stationCode, mes, anio, cantidad DESC
	        """;

	        if (paging.getSort() != null) {
	            sqlQuery += Functions.getSortString(paging.getSort(), ReporteListadoUsoEESSDTO.class);
	        }

	        query = entityManager.createQuery(sqlQuery, ReporteListadoUsoEESSDTO.class)
	                .unwrap(Query.class)
	                .setTupleTransformer((tuple, aliases) -> DbUtils.objFromFields(tuple, aliases, ReporteListadoUsoEESSDTO.class));

	        query.setParameter("fechaIni", params.getFechaIni());
	        query.setParameter("fechaFin", params.getFechaFin());
	        query.setParameter("movimiento", params.getMovimiento());
	        query.setParameter("tipoCliente", params.getTipoCliente());

	        query.setFirstResult(paging.isUnpaged() ? 0 : (int) paging.getOffset());
	        query.setMaxResults(paging.isUnpaged() ? Integer.MAX_VALUE : paging.getPageSize());

	        response = query.getResultList();

	    } catch (Exception ex) {
	        log.error("Error ejecutando consulta: " + ex.getMessage(), ex);
	    }

	    log.debug("response: " + response);
	    return response;
	}

	
	@Override
	@SuppressWarnings("unchecked")
	public List<ReporteComprasAndCargasDTO> getComprasAndCargas(ListCantidadClientesRequestDTO params, Pageable paging) {
		TypedQuery<ReporteComprasAndCargasDTO> query;
		List<ReporteComprasAndCargasDTO> response = null;
		
		try {
			String sqlQuery = """
				    SELECT 
				        EXTRACT(MONTH FROM COALESCE(payDate, CURRENT_DATE)) AS mes, 
				        COALESCE(SUM(ord.totalOrder), 0) AS compras, 
				        COALESCE(cargas.monto, 0) AS cargas
				    FROM 
				        Orders ord
				    JOIN 
				        Clients cl ON ord.idClient = cl.idClient
				    RIGHT JOIN (
				        SELECT 
				            ABS(SUM(cm.monto)) AS monto, 
				            EXTRACT(MONTH FROM cm.dateIn) AS mes
				        FROM 
				            CardMovement cm
				        JOIN 
				            Card cd ON cm.card.idCard = cd.idCard
				        JOIN 
				            Clients cl ON cd.client.idClient = cl.idClient
				        WHERE 
				            TO_CHAR(cm.dateIn, 'MM-YYYY') = :fecha
				            AND cm.movement = :movimiento
				            AND (:tipoCliente IS NULL OR cl.clientType = :tipoCliente)
				        GROUP BY 
				            mes
				    ) cargas ON cargas.mes = EXTRACT(MONTH FROM payDate)
				    WHERE 
				        ord.orderStatus = 'X'
				        AND (:tipoCliente IS NULL OR cl.clientType = :tipoCliente)
				        AND TO_CHAR(payDate, 'MM-YYYY') = :fecha
				    GROUP BY 
				        mes, cargas.monto
				    """;
			
			if(paging.getSort() != null)
				sqlQuery += Functions.getSortString(paging.getSort(), ReporteComprasAndCargasDTO.class);
			
			query = entityManager.createQuery(sqlQuery.toString(), ReporteComprasAndCargasDTO.class)
			.unwrap(Query.class)
			.setTupleTransformer((tuple, aliases) -> DbUtils.objFromFields(tuple, aliases, ReporteComprasAndCargasDTO.class));
			
			query.setParameter("movimiento", params.getMovimiento());
			query.setParameter("tipoCliente", params.getTipoCliente());
			query.setParameter("fecha", params.getFecha());

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
	public List<ReporteDetalleComprasCargasDTO> getDetalleComprasAndCargas(ListCantidadClientesRequestDTO params, Pageable paging) {
		TypedQuery<ReporteDetalleComprasCargasDTO> query;
		List<ReporteDetalleComprasCargasDTO> response = null;
		
		try {
			String sqlQuery = """
					SELECT 
					    cl.legalName AS razonSocial, 
					    cl.upi AS rut, 
					    CONCAT_WS(' ', us.name, us.firstName) AS administrador,
					    COALESCE(compras.monto, 0) AS compras, 
					    COALESCE(cargas.monto, 0) AS cargas, 
					    COALESCE(cargas.litros, 0) AS litros,
					    (SELECT COUNT(cd.idCard) FROM Card cd WHERE cd.client.idClient = cl.idClient AND cd.cardStatus = 'A') AS tarjetasActivas,
					    (SELECT COUNT(cd.idCard) FROM Card cd WHERE cd.client.idClient = cl.idClient AND cd.cardStatus <> 'E') AS totalTarjetas,
					    compras.mes AS mes,
					    compras.anio AS anio
					FROM 
					    Clients cl
					LEFT JOIN (
					    SELECT 
					        SUM(totalOrder) AS monto, 
					        ord.idClient AS ordClient, 
					        EXTRACT(MONTH FROM ord.payDate) AS mes, 
					        EXTRACT(YEAR FROM ord.payDate) AS anio 
					    FROM Orders ord 
					    LEFT JOIN Clients cl ON cl.idClient = ord.idClient 
					    WHERE ord.orderStatus = 'X'
					    AND (:tipoCliente IS NULL OR cl.clientType = :tipoCliente)
					    AND TO_CHAR(ord.payDate, 'MM-YYYY') = :fecha
					    GROUP BY ord.idClient, EXTRACT(MONTH FROM ord.payDate), EXTRACT(YEAR FROM ord.payDate)
					) compras ON compras.ordClient = cl.idClient
					LEFT JOIN (
					    SELECT 
					        ABS(SUM(cm.monto)) AS monto, 
					        cl.upi AS rut, 
					        EXTRACT(MONTH FROM cm.dateIn) AS mes, 
					        EXTRACT(YEAR FROM cm.dateIn) AS anio, 
					        SUM(cm.quantity) AS litros
					    FROM CardMovement cm
					    JOIN Card cd ON cd.idCard = cm.card.idCard
					    LEFT JOIN Clients cl ON cl.idClient = cd.client.idClient
					    WHERE TO_CHAR(cm.dateIn, 'MM-YYYY') = :fecha
					    AND (:tipoCliente IS NULL OR cl.clientType = :tipoCliente)
					    AND cm.movement = :movimiento
					    GROUP BY cl.upi, EXTRACT(MONTH FROM cm.dateIn), EXTRACT(YEAR FROM cm.dateIn)
					) cargas ON cargas.rut = cl.upi
					JOIN ClientsUsersRel rel ON rel.client.idClient = cl.idClient
					JOIN Users us ON us.idUser = rel.user.idUser AND us.status = 'A' AND us.role = 1
					WHERE compras.monto IS NOT NULL OR cargas.monto IS NOT NULL
					AND (:tipoCliente IS NULL OR cl.clientType = :tipoCliente)
					AND (compras.mes IS NOT NULL AND compras.anio IS NOT NULL)
					AND (cargas.mes IS NOT NULL AND cargas.anio IS NOT NULL)
					GROUP BY cl.upi, cl.idClient, cl.legalName, CONCAT_WS(' ', us.name, us.firstName), compras.monto, cargas.monto, cargas.litros, compras.mes, compras.anio
					""";
			
			if(paging.getSort() != null)
				sqlQuery += Functions.getSortString(paging.getSort(), ReporteDetalleComprasCargasDTO.class);
			
			query = entityManager.createQuery(sqlQuery.toString(), ReporteDetalleComprasCargasDTO.class)
			.unwrap(Query.class)
			.setTupleTransformer((tuple, aliases) -> DbUtils.objFromFields(tuple, aliases, ReporteDetalleComprasCargasDTO.class));
			
			query.setParameter("movimiento", params.getMovimiento());
			query.setParameter("tipoCliente", params.getTipoCliente());
			query.setParameter("fecha", params.getFecha());

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
	public List<ReporteCargasUltMesesDTO> getCargasUltimosMeses(ListCantidadClientesRequestDTO params, Pageable paging) {
	    TypedQuery<ReporteCargasUltMesesDTO> query;
	    List<ReporteCargasUltMesesDTO> response = null;

	    try {
	        String sqlQuery = """
	                SELECT 
	                    EXTRACT(MONTH FROM cm.dateIn) AS mes, 
	                    ABS(SUM(cm.monto)) AS monto, 
	                    SUM(cm.quantity) AS litros
	                FROM 
	                    CardMovement cm
	                LEFT JOIN 
	                    Card cd ON cd.idCard = cm.card.idCard
	                LEFT JOIN 
	                    Clients cl ON cl.idClient = cd.client.idClient
	                LEFT JOIN 
	                    VehicleCard vc ON vc.card.idCard = cd.idCard
	                LEFT JOIN 
	                    Vehicles v ON v.idVehicle = vc.vehicle.idVehicle
	                WHERE 
	                    (CAST(:fechaIni as text) IS NULL or cm.dateIn >= CAST(CAST(:fechaIni as text) as date)) AND 
						(CAST(:fechaFin as text) IS NULL or cm.dateIn <= CAST(CAST(:fechaFin as text) as date))
						AND (:tipoCliente IS NULL OR cl.clientType = :tipoCliente)
	                    AND (:documentType IS NULL OR v.documentType = :documentType)
	                    AND cm.movement = :movimiento
	                GROUP BY 
	                    mes
	                """;

	        if (paging.getSort() != null)
	            sqlQuery += Functions.getSortString(paging.getSort(), ReporteCargasUltMesesDTO.class);

	        query = entityManager.createQuery(sqlQuery.toString(), ReporteCargasUltMesesDTO.class)
	                .unwrap(Query.class)
	                .setTupleTransformer((tuple, aliases) -> DbUtils.objFromFields(tuple, aliases, ReporteCargasUltMesesDTO.class));
	        
	        query.setParameter("fechaIni", params.getDateIniFormatted());
	        query.setParameter("fechaFin", params.getDateEndFormatted());
	        query.setParameter("tipoCliente", params.getTipoCliente());
	        query.setParameter("documentType", params.getDocumentType());
	        query.setParameter("movimiento", params.getMovimiento());

	        query.setFirstResult(paging.isUnpaged() ? 0 : (int) paging.getOffset());
	        query.setMaxResults(paging.isUnpaged() ? Integer.MAX_VALUE : paging.getPageSize());

	        response = query.getResultList();

	    } catch (Exception ex) {
	        log.error("ERROR " + ex.getMessage());
	        ex.printStackTrace();
	    }

	    log.debug("response: " + response);
	    return response;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<ReporteDetalleCargasDTO> getDetalleCargas(ListCantidadClientesRequestDTO params, Pageable paging) {
	    TypedQuery<ReporteDetalleCargasDTO> query;
	    List<ReporteDetalleCargasDTO> response = null;

	    try {
	    	String sqlQuery = """
	    		    SELECT 
					    COALESCE(d1.name, COALESCE(d2.name, d3.name)) AS nameDpto,
					    cm.stationCode AS idEstacion,
					    cl.upi AS rutCliente,
					    COALESCE(cm.clientIdOrpak, '') AS idCliente,
					    st.name AS direccion,
					    ar.name AS comuna,
					    rg.name AS region,
					    cm.dateIn AS fecha,
					    TO_CHAR(cm.dateIn, 'DD-MM-YYYY') AS fechaSimple,
					    TO_CHAR(cm.dateIn, 'HH24:MI') AS hora,
					    COALESCE(cm.trx, '') AS trx,
					    cd.cardnum AS tarjeta,
					    cm.plate AS patente,
					    cm.productName AS producto,
					    cm.productCode AS codigoProducto,
					    cl.clientType AS tipoCliente,
					    COALESCE(cm.unitPrice, 0) AS unitario,
					    COALESCE(cm.quantity, 0) AS litros,
					    COALESCE(ABS(cm.monto), 0) AS precioTotal,
					    cm.driverRut AS driverRut,
					    COALESCE(cm.odometer, '') AS odometer,
					    cm.documentNumber AS documentNumber,
					    COALESCE(cm.performance, 0) AS performance,
					    v.documentType AS documentType
	    		    FROM CardMovement cm
	    		    LEFT JOIN Stations st ON st.stationCode = cm.stationCode
	    		    LEFT JOIN Area ar ON ar.idArea = st.area.idArea
	    		    LEFT JOIN Region rg ON rg.idRegion = ar.region.idRegion
	    		    LEFT JOIN Card cd ON cd.idCard = cm.card.idCard
	    		    LEFT JOIN Clients cl ON cl.idClient = cd.client.idClient
	    		    LEFT JOIN Departments d1 ON cm.deptId = d1.codeorpakclient
	    		    LEFT JOIN Departments d2 ON cm.deptId = d2.codeorpakinvoice
	    		    LEFT JOIN Departments d3 ON cm.deptId = d3.codeorpakticket
	    		    LEFT JOIN VehicleCard vc ON vc.card.idCard = cd.idCard
	    		    LEFT JOIN Vehicles v ON v.idVehicle = vc.vehicle.idVehicle
	    		    WHERE 
	    		    (CAST(:fechaIni as text) IS NULL or cm.dateIn >= CAST(CAST(:fechaIni as text) as date)) AND 
					(CAST(:fechaFin as text) IS NULL or cm.dateIn <= CAST(CAST(:fechaFin as text) as date))
	    		    AND cm.movement = :movimiento
	    		    AND (:tipoCliente IS NULL OR cl.clientType = :tipoCliente)
	    		    AND (:documentType IS NULL OR v.documentType = :documentType)
	    		""";

	        if (paging.getSort() != null)
	            sqlQuery += Functions.getSortString(paging.getSort(), ReporteDetalleCargasDTO.class);

	        query = entityManager.createQuery(sqlQuery.toString(), ReporteDetalleCargasDTO.class)
	                .unwrap(Query.class)
	                .setTupleTransformer((tuple, aliases) -> DbUtils.objFromFields(tuple, aliases, ReporteDetalleCargasDTO.class));
	        
	        query.setParameter("fechaIni", params.getDateIni());
	        query.setParameter("fechaFin", params.getDateEnd());
	        query.setParameter("tipoCliente", params.getTipoCliente());
	        query.setParameter("documentType", params.getDocumentType());
	        query.setParameter("movimiento", params.getMovimiento());

	        query.setFirstResult(paging.isUnpaged() ? 0 : (int) paging.getOffset());
	        query.setMaxResults(paging.isUnpaged() ? Integer.MAX_VALUE : paging.getPageSize());

	        response = query.getResultList();

	    } catch (Exception ex) {
	        log.error("ERROR " + ex.getMessage());
	        ex.printStackTrace();
	    }

	    log.debug("response: " + response);
	    return response;
	}

}
