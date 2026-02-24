package com.evertecinc.b2c.enex.client.dao.extended.impl;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.query.Query;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.evertecinc.b2c.enex.classes.utils.db.DbUtils;
import com.evertecinc.b2c.enex.client.dao.extended.ISafRepo;
import com.evertecinc.b2c.enex.client.model.dto2.ClientMonitorDTO;
import com.evertecinc.b2c.enex.client.model.dto2.MonitorAdBlueDTO;
import com.evertecinc.b2c.enex.client.model.dto2.MonitorImpresionDTO;
import com.evertecinc.b2c.enex.client.model.dto2.MonitorTipoSafDTO;
import com.evertecinc.b2c.enex.client.model.dto2.criterio.CriterioMonitorTipoSafDTO;
import com.evertecinc.b2c.enex.client.model.dto2.requests.ClientMonitorRequestDTO;
import com.evertecinc.b2c.enex.client.model.dto2.requests.ListMonitorImpresionJsonRequestDTO;
import com.evertecinc.b2c.enex.client.model.dto2.requests.MonitorSolicitudesRequestDTO;
import com.evertecinc.b2c.enex.utils.functions.Functions;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Repository
@RequiredArgsConstructor
@Slf4j
public class SafRepoImpl implements ISafRepo {

	@PersistenceContext
	private final EntityManager entityManager;
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<MonitorAdBlueDTO> getListServices(MonitorSolicitudesRequestDTO params, Pageable paging) {
		
		TypedQuery<MonitorAdBlueDTO> query;
		List<MonitorAdBlueDTO> response = null;
		
		try {
			String sqlQuery = new String("""
				SELECT
					Q.idQueue as idQueue,
					Q2.upi as rut,
					Q2.idClient as idCliente,
					Q2.legalName as razonSocial,
					Q.type as tipoSaf,
					Q.data as data,
					Q.status as status,
					Q.dateIns as fechaIngreso,
					Q.dateSend as fechaFinal
			FROM SafQueue Q
			JOIN Clients Q2 ON CAST(Q2.idClient as text) = Q.idElement
			WHERE
			Q.status <> 'X'
				AND(:listaFO is NULL
						OR (:listaFO IS TRUE OR Q.type in ('VGR','GBR', 'CAR','TMR', 'SAD'))
						OR (:listaFO IS FALSE OR Q.type in ('VGR','GBR', 'CAR','TMR'))
				)
				AND (:tipoSaf IS NULL OR Q.type ILIKE :tipoSaf)
				AND (:idElement IS NULL OR Q.idElement ILIKE :idElement)
				AND (:rut IS NULL OR Q2.upi ILIKE :rut)
				AND (:legalName IS NULL OR Q2.legalName ILIKE :legalName)
				AND (:status IS NULL OR Q.status ILIKE :status)
				AND (CAST(:fechaIn as text) IS NULL or Q.dateIns >= CAST(CAST(:fechaIn as text) as date))  
				AND (CAST(:fechaEnd as text) IS NULL or Q.dateSend <= CAST(CAST(:fechaEnd as text) as date))
			  		
			""") ;
			
			
			if(paging.getSort() != null)
				sqlQuery += Functions.getSortString(paging.getSort(), MonitorAdBlueDTO.class);
			
			query = entityManager.createQuery(sqlQuery.toString(), MonitorAdBlueDTO.class)
			.unwrap(Query.class)
			.setTupleTransformer((tuple, aliases) -> DbUtils.objFromFields(tuple, aliases, MonitorAdBlueDTO.class));
			
			query.setParameter("listaFO", params.getListaFO());
			query.setParameter("idElement", params.getIdElement() == null ? null : '%'+params.getIdElement().toString()+'%');
			query.setParameter("rut", params.getRut() == null ? null : '%'+params.getRut()+'%');
			query.setParameter("legalName", params.getRazonSocial() == null ? null : '%'+params.getRazonSocial()+'%');
			query.setParameter("status", params.getStatus() == null ? null : '%'+params.getStatus()+'%');
			query.setParameter("tipoSaf", params.getTipoSaf() == null ? null : params.getStatus());
			query.setParameter("fechaIn", params.getFechaInicio());
			query.setParameter("fechaEnd", params.getFechaFinal());
			
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

	@Override
	@SuppressWarnings("unchecked")
	public List<MonitorTipoSafDTO> getListTipoSaf(CriterioMonitorTipoSafDTO params, Pageable pageable) {
	    List<MonitorTipoSafDTO> response = null;

	    try {
	        StringBuilder sqlQuery = new StringBuilder("""
	                SELECT 
	                    q.idQueue AS idQueue,
	                    q.numRetries AS reintentos,
	                    q.dateIns AS fechaIngreso,
	                    q.type AS tipoSaf,
	                    now() AS fechaUltimoIntento,
	                    now() AS ultimoError,
	                    q.task AS task
	                FROM SafQueue q
	                WHERE q.status = 'P'
	                """);

	        // Filtros condicionales
	        if (params.getTipo() != null) {
	            sqlQuery.append(" AND q.type = :tipo");
	        }
	        if (params.getIdQueue() != null) {
	            sqlQuery.append(" AND q.idQueue = :idQueue");
	        }
	        if (params.getIdElement() != null) {
	            sqlQuery.append(" AND q.idElement = :idElement");
	        }
	        if (params.getData() != null) {
	            sqlQuery.append(" AND q.data LIKE CONCAT(:data, ';%%')");
	        }
	        if (params.getTask() != null) {
	            sqlQuery.append(" AND q.task = :task");
	        }
	        if (params.isTaskisnotnull()) {
	            sqlQuery.append(" AND q.task IS NOT NULL");
	        }
	        if (params.getFechaInicio() != null && params.getFechaFin() != null) {
	            sqlQuery.append(" AND q.dateins BETWEEN :fechaInicio AND :fechaFin");
	        }
	        if (params.isLimitHistory()) {
	            sqlQuery.append(" AND q.history_flag = true"); // Ejemplo de campo adicional
	        }
	        if (params.getTipos() != null && !params.getTipos().isEmpty()) {
	            sqlQuery.append(" AND q.type IN :tipos");
	        }

	        // Ordenación
	        Sort sort = pageable.getSort();
	        if (sort.isSorted()) {
	            sqlQuery.append(" ORDER BY ");
	            sqlQuery.append(sort.stream()
	                    .map(order -> order.getProperty() + " " + order.getDirection().name())
	                    .collect(Collectors.joining(", ")));
	        }

	        // Paginación
//	        if (pageable.isPaged()) {
//	            sqlQuery.append(" LIMIT :limit OFFSET :offset");
//	        }

	        // Crear consulta
	        TypedQuery<MonitorTipoSafDTO> query = entityManager.createQuery(sqlQuery.toString(), MonitorTipoSafDTO.class)
	            .unwrap(Query.class)
	            .setTupleTransformer((tuple, aliases) -> DbUtils.objFromFields(tuple, aliases, MonitorTipoSafDTO.class));

	        // Asignar parámetros
	        if (params.getTipo() != null) {
	            query.setParameter("tipo", params.getTipo());
	        }
	        if (params.getIdQueue() != null) {
	            query.setParameter("idQueue", params.getIdQueue());
	        }
	        if (params.getIdElement() != null) {
	            query.setParameter("idElement", params.getIdElement());
	        }
	        if (params.getData() != null) {
	            query.setParameter("data", params.getData());
	        }
	        if (params.getTask() != null) {
	            query.setParameter("task", params.getTask());
	        }
	        if (params.getFechaInicio() != null && params.getFechaFin() != null) {
	            query.setParameter("fechaInicio", params.getFechaInicio());
	            query.setParameter("fechaFin", params.getFechaFin());
	        }
	        if (params.getTipos() != null && !params.getTipos().isEmpty()) {
	            query.setParameter("tipos", params.getTipos());
	        }
	        
        	query.setFirstResult(pageable.isUnpaged() ? 0 : (int) pageable.getOffset());
			query.setMaxResults(pageable.isUnpaged() ? Integer.MAX_VALUE : pageable.getPageSize());
        

	        // Obtener resultados
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
	public Long getListTipoSafCount(CriterioMonitorTipoSafDTO params, Pageable pageable) {
	    Long response = null;
	    TypedQuery<Long> query;

	    try {
	    	String sqlQuery = new String("""
	                SELECT 
	                    count(*) as totalRegistros
	                FROM SafQueue q
	                WHERE q.status = 'P'
	                """);

	        // Filtros condicionales
	        if (params.getTipo() != null) {
	            sqlQuery += (" AND q.type = :tipo");
	        }
	        if (params.getIdQueue() != null) {
	            sqlQuery += (" AND q.idQueue = :idQueue");
	        }
	        if (params.getIdElement() != null) {
	            sqlQuery += (" AND q.id_element = :idElement");
	        }
	        if (params.getData() != null) {
	            sqlQuery += (" AND q.data LIKE CONCAT(:data, ';%%')");
	        }
	        if (params.getTask() != null) {
	            sqlQuery += (" AND q.task = :task");
	        }
	        if (params.isTaskisnotnull()) {
	            sqlQuery += (" AND q.task IS NOT NULL");
	        }
	        if (params.getFechaInicio() != null && params.getFechaFin() != null) {
	            sqlQuery += (" AND q.dateins BETWEEN :fechaInicio AND :fechaFin");
	        }
	        if (params.isLimitHistory()) {
	            sqlQuery += (" AND q.history_flag = true"); // Ejemplo de campo adicional
	        }
	        if (params.getTipos() != null && !params.getTipos().isEmpty()) {
	            sqlQuery += (" AND q.type IN :tipos");
	        }

	        // Ordenación
//	        Sort sort = pageable.getSort();
//	        if (sort.isSorted()) {
//	            sqlQuery.append(" ORDER BY ");
//	            sqlQuery.append(sort.stream()
//	                    .map(order -> order.getProperty() + " " + order.getDirection().name())
//	                    .collect(Collectors.joining(", ")));
//	        }

	        // Paginación
//	        if (pageable.isPaged()) {
//	            sqlQuery.append(" LIMIT :limit OFFSET :offset");
//	        }

	        // Crear consulta
	        query = (TypedQuery<Long>) entityManager.createQuery(sqlQuery);

	        // Asignar parámetros
	        if (params.getTipo() != null) {
	            query.setParameter("tipo", params.getTipo());
	        }
	        if (params.getIdQueue() != null) {
	            query.setParameter("idQueue", params.getIdQueue());
	        }
	        if (params.getIdElement() != null) {
	            query.setParameter("idElement", params.getIdElement());
	        }
	        if (params.getData() != null) {
	            query.setParameter("data", params.getData());
	        }
	        if (params.getTask() != null) {
	            query.setParameter("task", params.getTask());
	        }
	        if (params.getFechaInicio() != null && params.getFechaFin() != null) {
	            query.setParameter("fechaInicio", params.getFechaInicio());
	            query.setParameter("fechaFin", params.getFechaFin());
	        }
	        if (params.getTipos() != null && !params.getTipos().isEmpty()) {
	            query.setParameter("tipos", params.getTipos());
	        }
	        
        	query.setFirstResult(pageable.isUnpaged() ? 0 : (int) pageable.getOffset());
			query.setMaxResults(pageable.isUnpaged() ? Integer.MAX_VALUE : pageable.getPageSize());
        

	        // Obtener resultados
			
			
			
	        response = query.getSingleResult();

	    } catch (Exception ex) {
	        log.error("ERROR " + ex.getMessage());
	        ex.printStackTrace();
	    }

	    log.debug("response: " + response);
	    return response;
	}

	@Override
	public Long getListTipoSafCount(CriterioMonitorTipoSafDTO params) {
		Long count = 0L;

	    try {
	        StringBuilder sqlQuery = new StringBuilder("""
	                SELECT COUNT(*) 
	                FROM SafQueue q
	                WHERE q.status = 'P'
	                """);

	        // Filtros condicionales
	        if (params.getTipo() != null) {
	            sqlQuery.append(" AND q.type = :tipo");
	        }
	        if (params.getIdQueue() != null) {
	            sqlQuery.append(" AND q.id_queue = :idQueue");
	        }
	        if (params.getIdElement() != null) {
	            sqlQuery.append(" AND q.id_element = :idElement");
	        }
	        if (params.getData() != null) {
	            sqlQuery.append(" AND q.data LIKE CONCAT(:data, ';%%')");
	        }
	        if (params.getTask() != null) {
	            sqlQuery.append(" AND q.task = :task");
	        }
	        if (params.isTaskisnotnull()) {
	            sqlQuery.append(" AND q.task IS NOT NULL");
	        }
	        if (params.getFechaInicio() != null && params.getFechaFin() != null) {
	            sqlQuery.append(" AND q.dateins BETWEEN :fechaInicio AND :fechaFin");
	        }
	        if (params.isLimitHistory()) {
	            sqlQuery.append(" AND q.history_flag = true");
	        }
	        if (params.getTipos() != null && !params.getTipos().isEmpty()) {
	            sqlQuery.append(" AND q.type IN :tipos");
	        }

	        // Crear consulta
	        TypedQuery<Long> query = entityManager.createQuery(sqlQuery.toString(), Long.class);

	        // Asignar parámetros
	        if (params.getTipo() != null) {
	            query.setParameter("tipo", params.getTipo());
	        }
	        if (params.getIdQueue() != null) {
	            query.setParameter("idQueue", params.getIdQueue());
	        }
	        if (params.getIdElement() != null) {
	            query.setParameter("idElement", params.getIdElement());
	        }
	        if (params.getData() != null) {
	            query.setParameter("data", params.getData());
	        }
	        if (params.getTask() != null) {
	            query.setParameter("task", params.getTask());
	        }
	        if (params.getFechaInicio() != null && params.getFechaFin() != null) {
	            query.setParameter("fechaInicio", params.getFechaInicio());
	            query.setParameter("fechaFin", params.getFechaFin());
	        }
	        if (params.getTipos() != null && !params.getTipos().isEmpty()) {
	            query.setParameter("tipos", params.getTipos());
	        }

	        // Ejecutar consulta y obtener resultado
	        count = query.getSingleResult();

	    } catch (Exception ex) {
	        log.error("ERROR " + ex.getMessage());
	        ex.printStackTrace();
	    }

	    log.debug("count: " + count);
	    return count;
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List<MonitorImpresionDTO> getListPendingPrintSaf(ListMonitorImpresionJsonRequestDTO params, Pageable paging) {

	    List<MonitorImpresionDTO> response = null;

	    try {
	        // Consulta base
	        String sqlQuery = """
	            SELECT
	                sq.id_queue AS idQueue,
	                c.upi AS rut,
	                c.legal_name AS razonSocial,
	                v.plate AS patente,
	                c.client_type AS tipoCliente,
	                sq.dateins AS fechaIngreso,
	                sq.type AS tipoSaf,
	                v.id_vehicle AS idVehicle,
	                card.version AS version,
	                CONCAT(u.name, ' ', u.first_name) AS usuarioAut,
	                vr.dateout AS dateOut,
	                sq.data AS data,
	                card.cardnum AS cardnum,
	                c.contact_phone AS contactPhone,
	                sq.status AS estado
	            FROM saf_queue sq
	            JOIN vehicles v ON 
	                CASE 
	                    WHEN sq.id_element ~ '^[0-9]+$' THEN CAST(sq.id_element AS INTEGER) 
	                    ELSE NULL 
	                END = v.id_vehicle
	            JOIN clients c ON c.id_client = v.id_client
	            LEFT JOIN cards card ON card.id_card = (
	                SELECT 
	                    CASE 
	                        WHEN SPLIT_PART(SPLIT_PART(subSq.data, ';', 1), ';', 1) ~ '^[0-9]+$' 
	                        THEN CAST(SPLIT_PART(SPLIT_PART(subSq.data, ';', 1), ';', 1) AS INTEGER) 
	                        ELSE NULL 
	                    END
	                FROM saf_queue subSq
	                WHERE subSq.id_queue = sq.id_queue
	            ) AND card.card_status NOT IN ('E', 'P')
	            LEFT JOIN vehicle_reqs vr ON vr.id_req = (
	                SELECT 
	                    CASE 
	                        WHEN SPLIT_PART(SPLIT_PART(subSq.data, ';', -2), ';', 1) ~ '^[0-9]+$' 
	                        THEN CAST(SPLIT_PART(SPLIT_PART(subSq.data, ';', -2), ';', 1) AS INTEGER) 
	                        ELSE NULL 
	                    END
	                FROM saf_queue subSq
	                WHERE subSq.id_queue = sq.id_queue
	            )
	            LEFT JOIN users u ON u.id_user = vr.id_user_aut
	            WHERE 1=1
	        """;

	        // Filtros dinámicos
	        if (params.getTipoSaf() != null) {
	            sqlQuery += " AND sq.type = :tipoSaf";
	        } else {
	            sqlQuery += " AND (sq.type = 'SIM' OR sq.type = 'SRM')";
	        }
	        if (params.getRut() != null) {
	            sqlQuery += " AND c.upi iLIKE :rut";
	        }
	        if (params.getPatente() != null) {
	            sqlQuery += " AND v.plate iLIKE :patente";
	        }
	        if (params.getTipoCliente() != null) {
	            sqlQuery += " AND c.client_type iLIKE :tipoCliente";
	        }
	        if (params.getEstado() != null) {
	            sqlQuery += " AND sq.status = :estado";
	        }
	        if (params.getFechaInicio() != null && params.getFechaFinal() != null) {
	            sqlQuery += " AND sq.dateins BETWEEN :fechaInicio AND :fechaFinal";
	        }

	        // **Agregar cláusulas de paginación al final de la consulta**
	        if (paging != null) {
	            // Utiliza paging.getPageSize() y paging.getOffset()
	            sqlQuery += " LIMIT " + paging.getPageSize() + " OFFSET " + paging.getOffset();
	        }
	        

	        // Crear consulta nativa con el mapeo
	        jakarta.persistence.Query nativeQuery = entityManager.createNativeQuery(sqlQuery);
	        
	     // Setear parámetros
	        if (params.getTipoSaf() != null) {
	            nativeQuery.setParameter("tipoSaf", params.getTipoSaf());
	        }
	        if (params.getRut() != null) {
	            nativeQuery.setParameter("rut", "%" + params.getRut() + "%");
	        }
	        if (params.getPatente() != null) {
	            nativeQuery.setParameter("patente", "%" + params.getPatente() + "%");
	        }
	        if (params.getTipoCliente() != null) {
	            nativeQuery.setParameter("tipoCliente", "%" + params.getTipoCliente() + "%");
	        }
	        if (params.getEstado() != null) {
	            nativeQuery.setParameter("estado", params.getEstado());
	        }
	        if (params.getFechaInicio() != null && params.getFechaFinal() != null) {
	            nativeQuery.setParameter("fechaInicio", params.getFechaInicio());
	            nativeQuery.setParameter("fechaFinal", params.getFechaFinal());
	        }

	        
	        // Configurar mapeo al DTO
	        List<Object[]> resultList = nativeQuery.getResultList();
	        response = resultList.stream().map(row -> MonitorImpresionDTO.builder()
	                .id_queue((Long) row[0])
	                .rut((String) row[1])
	                .razonSocial((String) row[2])
	                .patente((String) row[3])
	                .tipoCliente((String) row[4])
	                .fechaIngreso(row[5] != null ? ((Timestamp) row[5]).toLocalDateTime() : null)
	                .tipoSaf((String) row[6])
	                .id_vehicle((Long) row[7])
	                .version((String) row[8])
	                .usuarioAut((String) row[9])
	                .dateOut(row[10] != null ? ((Timestamp) row[10]).toLocalDateTime() : null)
	                .data((String) row[11])
	                .cardnum((String) row[12])
	                .contactPhone((String) row[13])
	                .estado((String) row[14])
	                .build()
	        ).collect(Collectors.toList());

	    } catch (Exception ex) {
	        log.error("Error ejecutando la consulta: " + ex.getMessage(), ex);
	    }

	    log.debug("response: {}", response);
	    return response;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Long getListPendingPrintSafCount(ListMonitorImpresionJsonRequestDTO params) {

	    Long count = 0L;

	    try {
	        // Consulta base para el count
	        String sqlQuery = """
	            SELECT
	                COUNT(*)
	            FROM saf_queue sq
	            JOIN vehicles v ON 
	                CASE 
	                    WHEN sq.id_element ~ '^[0-9]+$' THEN CAST(sq.id_element AS INTEGER) 
	                    ELSE NULL 
	                END = v.id_vehicle
	            JOIN clients c ON c.id_client = v.id_client
	            LEFT JOIN cards card ON card.id_card = (
	                SELECT 
	                    CASE 
	                        WHEN SPLIT_PART(SPLIT_PART(subSq.data, ';', 1), ';', 1) ~ '^[0-9]+$' 
	                        THEN CAST(SPLIT_PART(SPLIT_PART(subSq.data, ';', 1), ';', 1) AS INTEGER) 
	                        ELSE NULL 
	                    END
	                FROM saf_queue subSq
	                WHERE subSq.id_queue = sq.id_queue
	            ) AND card.card_status NOT IN ('E', 'P')
	            LEFT JOIN vehicle_reqs vr ON vr.id_req = (
	                SELECT 
	                    CASE 
	                        WHEN SPLIT_PART(SPLIT_PART(subSq.data, ';', -2), ';', 1) ~ '^[0-9]+$' 
	                        THEN CAST(SPLIT_PART(SPLIT_PART(subSq.data, ';', -2), ';', 1) AS INTEGER) 
	                        ELSE NULL 
	                    END
	                FROM saf_queue subSq
	                WHERE subSq.id_queue = sq.id_queue
	            )
	            LEFT JOIN users u ON u.id_user = vr.id_user_aut
	            WHERE 1=1
	        """;

	        // Filtros dinámicos
	        if (params.getTipoSaf() != null) {
	            sqlQuery += " AND sq.type = :tipoSaf";
	        } else {
	            sqlQuery += " AND (sq.type = 'SIM' OR sq.type = 'SRM')";
	        }
	        if (params.getRut() != null) {
	            sqlQuery += " AND c.upi LIKE :rut";
	        }
	        if (params.getPatente() != null) {
	            sqlQuery += " AND v.plate LIKE :patente";
	        }
	        if (params.getTipoCliente() != null) {
	            sqlQuery += " AND c.client_type LIKE :tipoCliente";
	        }
	        if (params.getEstado() != null) {
	            sqlQuery += " AND sq.status = :estado";
	        }
	        if (params.getFechaInicio() != null && params.getFechaFinal() != null) {
	            sqlQuery += " AND sq.dateins BETWEEN :fechaInicio AND :fechaFinal";
	        }

	        // Crear la consulta nativa
	        jakarta.persistence.Query nativeQuery = entityManager.createNativeQuery(sqlQuery);

	        // Asignar parámetros
	        if (params.getTipoSaf() != null) {
	            nativeQuery.setParameter("tipoSaf", params.getTipoSaf());
	        }
	        if (params.getRut() != null) {
	            nativeQuery.setParameter("rut", "%" + params.getRut() + "%");
	        }
	        if (params.getPatente() != null) {
	            nativeQuery.setParameter("patente", "%" + params.getPatente() + "%");
	        }
	        if (params.getTipoCliente() != null) {
	            nativeQuery.setParameter("tipoCliente", "%" + params.getTipoCliente() + "%");
	        }
	        if (params.getEstado() != null) {
	            nativeQuery.setParameter("estado", params.getEstado());
	        }
	        if (params.getFechaInicio() != null && params.getFechaFinal() != null) {
	            nativeQuery.setParameter("fechaInicio", params.getFechaInicio());
	            nativeQuery.setParameter("fechaFinal", params.getFechaFinal());
	        }

	        // Ejecutar la consulta y obtener el count
	        Object result = nativeQuery.getSingleResult();
	        // Convertir el resultado a Long (puede venir como BigInteger o Number según el dialecto)
	        if (result instanceof Number) {
	            count = ((Number) result).longValue();
	        }
	        
	    } catch (Exception ex) {
	        log.error("Error ejecutando la consulta: " + ex.getMessage(), ex);
	        throw ex; // O lanza una excepción propia según tu lógica
	    }

	    log.debug("Total count: {}", count);
	    return count;
	}


	

}
