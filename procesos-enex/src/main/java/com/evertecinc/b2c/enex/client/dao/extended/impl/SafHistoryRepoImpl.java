package com.evertecinc.b2c.enex.client.dao.extended.impl;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.evertecinc.b2c.enex.client.dao.extended.ISafHistoryRepo;
import com.evertecinc.b2c.enex.client.model.dto2.MonitorSafHistoryDTO;
import com.evertecinc.b2c.enex.client.model.dto2.criterio.CriterioMonitorTipoSafDTO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Repository
@RequiredArgsConstructor
@Slf4j
public class SafHistoryRepoImpl implements ISafHistoryRepo {

	@PersistenceContext
	private final EntityManager entityManager;
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<MonitorSafHistoryDTO> getListSafHistory(CriterioMonitorTipoSafDTO params, Pageable paging) {
	    List<MonitorSafHistoryDTO> response = null;
	    try {
	        StringBuilder jpql = new StringBuilder();
	        
	        // Utilizamos el constructor de MonitorSafHistoryDTO
	        jpql.append("SELECT new com.evertecinc.b2c.enex.client.model.dto2.MonitorSafHistoryDTO(")
	            .append("QH.dateIns, QH.returnCode, QH.message, QH.uri, null) ")
	            .append("FROM SafQueueHistory QH ")
	            .append("JOIN SafQueue Q ON QH.idQueue = Q.idQueue ")
	            .append("WHERE Q.status = 'P' ");
	        
	        // Filtro: si se proporciona idQueue
	        if (params.getIdQueue() != null) {
	            jpql.append("AND QH.idQueue = :idQueue ");
	        }
	        // Filtro: si se proporcionan ambas fechas (rango)
	        if (params.getFechaInicio() != null && params.getFechaFin() != null) {
	            jpql.append("AND QH.dateIns BETWEEN :fechaInicio AND :fechaFin ");
	        }
	        
	        // Agregar ordenamiento
	        if (paging.getSort() != null && paging.getSort().isSorted()) {
	            jpql.append("ORDER BY ");
	            paging.getSort().forEach(order -> {
	                jpql.append("QH.").append(order.getProperty()).append(" ");
	                jpql.append(order.isAscending() ? "ASC " : "DESC ");
	            });
	        } else {
	            jpql.append("ORDER BY QH.dateIns DESC ");
	        }
	        
	        String queryStr = jpql.toString();
	        log.debug("JPQL generado: {}", queryStr);
	        
	        // Crear la consulta tipada
	        TypedQuery<MonitorSafHistoryDTO> query = entityManager.createQuery(queryStr, MonitorSafHistoryDTO.class);
	        
	        // Asignar parámetros
	        if (params.getIdQueue() != null) {
	            query.setParameter("idQueue", params.getIdQueue());
	        }
	        if (params.getFechaInicio() != null && params.getFechaFin() != null) {
	            query.setParameter("fechaInicio", params.getFechaInicio());
	            query.setParameter("fechaFin", params.getFechaFin());
	        }
	        
	        // Aplicar paginación
	        query.setFirstResult(paging.isUnpaged() ? 0 : (int) paging.getOffset());
	        query.setMaxResults(paging.isUnpaged() ? Integer.MAX_VALUE : paging.getPageSize());
	        
	        response = query.getResultList();
	    } catch(Exception ex) {
	        log.error("Error en getListSafHistory: {}", ex.getMessage(), ex);
	    }
	    
	    log.debug("response: {}", response);
	    return response;
	}

	
	@Override
	public Long getListSafHistoryCount(CriterioMonitorTipoSafDTO params) {
	    Long count = 0L;
	    try {
	        StringBuilder jpql = new StringBuilder();
	        jpql.append("SELECT COUNT(QH) ")
	            .append("FROM SafQueueHistory QH ")
	            .append("JOIN SafQueue Q ON QH.idQueue = Q.idQueue ")
	            .append("WHERE Q.status = 'P' ");
	        
	        // Filtros dinámicos
	        if (params.getIdQueue() != null) {
	            jpql.append("AND QH.idQueue = :idQueue ");
	        }
	        if (params.getFechaInicio() != null && params.getFechaFin() != null) {
	            jpql.append("AND QH.dateIns BETWEEN :fechaInicio AND :fechaFin ");
	        }
	        
	        String queryStr = jpql.toString();
	        log.debug("JPQL Count generado: {}", queryStr);
	        
	        TypedQuery<Long> query = entityManager.createQuery(queryStr, Long.class);
	        
	        // Asignar parámetros
	        if (params.getIdQueue() != null) {
	            query.setParameter("idQueue", params.getIdQueue());
	        }
	        if (params.getFechaInicio() != null && params.getFechaFin() != null) {
	            query.setParameter("fechaInicio", params.getFechaInicio());
	            query.setParameter("fechaFin", params.getFechaFin());
	        }
	        
	        count = query.getSingleResult();
	    } catch(Exception ex) {
	        log.error("Error en getListSafHistoryCount: {}", ex.getMessage(), ex);
	    }
	    
	    log.debug("Count obtenido: {}", count);
	    return count;
	}




}
