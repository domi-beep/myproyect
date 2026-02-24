package com.evertecinc.b2c.enex.audits.service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import com.evertecinc.b2c.enex.audits.exceptions.AuditNotFoundException;
import com.evertecinc.b2c.enex.audits.exceptions.AuditsException;
import com.evertecinc.b2c.enex.audits.model.dto.AuditCriteriaDTO;
import com.evertecinc.b2c.enex.audits.model.dto.AuditDTO;
import com.evertecinc.b2c.enex.audits.model.entities.Audits;



public interface AuditsService {
    /**
     * Actualiza los datos de una auditoría existente.
     * 
     * @param auditDTO objeto con los datos actualizados de la auditoría
     * @return el número de auditorías actualizadas (debería ser 1 si la auditoría existe)
     * @throws AuditsException si ocurre un error durante la actualización
     */
    public Long updateAudit(AuditDTO auditDTO) throws AuditsException;
    
    /**
     * Obtiene una auditoría por su ID.
     * 
     * @param idAudit ID de la auditoría a buscar
     * @return el objeto {@link AuditDTO} con los datos de la auditoría
     * @throws AuditNotFoundException si no se encuentra la auditoría
     */
    public AuditDTO getAuditById(Long idAudit) throws AuditNotFoundException;
    
    /**
     * Obtiene todas las auditorías del sistema.
     * 
     * @return una lista con todas las auditorías
     */
    public List<AuditDTO> getAllAudits();
    
    /**
     * Elimina una auditoría por su ID.
     * 
     * @param idAudit ID de la auditoría a eliminar
     * @throws AuditsException si ocurre un error durante la eliminación
     */
    public void deleteAuditById(Long idAudit) throws AuditsException;
    
    /**
     * Busca auditorías basadas en ciertos criterios de búsqueda.
     * 
     * @param criteria objeto con los criterios de búsqueda
     * @return una lista con las auditorías que cumplen los criterios
     * @throws AuditNotFoundException si no se encuentran auditorías que cumplan los criterios
     */
    public List<AuditDTO> searchAuditsByCriteria(AuditCriteriaDTO criteria) throws AuditNotFoundException;

	public Audits addAudit(AuditDTO auditDto) throws AuditsException, IllegalAccessException, InvocationTargetException;

	
	
}