package com.evertecinc.b2c.enex.audits.service;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.evertecinc.b2c.constants.AuditoriaConstans;
import com.evertecinc.b2c.enex.audits.dao.repositories.AuditsRepository;
import com.evertecinc.b2c.enex.audits.exceptions.AuditNotFoundException;
import com.evertecinc.b2c.enex.audits.exceptions.AuditsException;
import com.evertecinc.b2c.enex.audits.model.dto.AuditCriteriaDTO;
import com.evertecinc.b2c.enex.audits.model.dto.AuditDTO;
import com.evertecinc.b2c.enex.audits.model.entities.Audits;
import com.evertecinc.b2c.enex.audits.model.mapper.AuditMapper;
import com.evertecins.b2c.enex.utils.IgnoreNullBeanUtilsBean;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Service
@RequiredArgsConstructor
@Slf4j
public class AuditsServiceImpl implements AuditsService {
	
	@Autowired
    private AuditsRepository auditsRepository;
	
	@Autowired
    private AuditMapper auditMapper;

	@Override
	public Audits addAudit(AuditDTO auditoria)
			throws AuditsException, IllegalAccessException, InvocationTargetException {
		if(auditoria == null) {
			log.error("La auditoria no puede ser nula ni vacia");
			throw new IllegalArgumentException("La auditoria no puede ser nula ni vacia");
		}
		
		if(auditoria.getIdElemento() == null) {
			log.error("El id elemento no puede ser nulo ni vacio");
			throw new IllegalArgumentException("El id elemento no puede ser nulo ni vacio");
		}
		
		if(auditoria.getTipoElemento() == null || "".equals(auditoria.getTipoElemento().trim())) {
			log.error("El tipo elemento no puede ser nulo ni vacio");
			throw new IllegalArgumentException("El tipo elemento no puede ser nulo ni vacio");
		}
		
		if(auditoria.getInsertLogin() == null || "".equals(auditoria.getInsertLogin().trim())) {
			log.error("El login del usuario no puede ser nulo ni vacio");
			throw new IllegalArgumentException("El login del usuario no puede ser nulo ni vacio");
		}
		
		if(auditoria.getAction() == null || "".equals(auditoria.getAction().trim())) {
			log.error("La accion no puede ser nula ni vacia");
			throw new IllegalArgumentException("La accion no puede ser nula ni vacia");
		}
		
		if(auditoria.getDate() == null) {
			auditoria.setDate(LocalDateTime.now());
		}
		
		if(auditoria.getSystem() == null || auditoria.getSystem().isBlank()) {
			auditoria.setSystem(AuditoriaConstans.USUARIO_PROCESO);
		}
		
		if(auditoria.getDescription() != null && !auditoria.getDescription().isBlank()) {
			int maxCaracteres = 1500;
			auditoria.setDescription(auditoria.getDescription().length() <= maxCaracteres ? auditoria.getDescription() : auditoria.getDescription().substring(0, maxCaracteres)); 

		}
		Audits auditoriaSave = new Audits();
		IgnoreNullBeanUtilsBean ignoreNullBeanUtilsBean = new IgnoreNullBeanUtilsBean();
		ignoreNullBeanUtilsBean.copyProperties(auditoriaSave, auditoria);
		
		Audits auditoriaResult = this.auditsRepository.save(auditoriaSave);
		
		log.debug("Auditoria guardada: "+auditoriaResult);
		return auditoriaResult;

		
	}

	@Override
	public Long updateAudit(AuditDTO auditDTO) throws AuditsException {
		try {
	        // Verificar si la auditoría existe en la base de datos
	        Audits existingAudit = auditsRepository.findById(auditDTO.getIdAudit())
	                .orElseThrow(() -> new AuditsException("Audit not found with ID: " + auditDTO.getIdAudit()));

	        // Actualizar los campos de la auditoría existente con los datos del DTO
	        existingAudit.setInsertLogin(auditDTO.getInsertLogin());
	        existingAudit.setInsertName(auditDTO.getInsertName());
	        existingAudit.setDate(auditDTO.getDate());
	        existingAudit.setAction(auditDTO.getAction());
	        existingAudit.setDescription(auditDTO.getDescription());
	        existingAudit.setSystem(auditDTO.getSystem());

	        // Guardar la auditoría actualizada
	        Audits updatedAudit = auditsRepository.save(existingAudit);

	        // Retornar el ID de la auditoría actualizada
	        return updatedAudit.getIdAudit();

	    } catch (Exception e) {
	        throw new AuditsException("Error while updating audit with ID: " + auditDTO.getIdAudit(), e);
	    }
	}

	@Override
    public AuditDTO getAuditById(Long idAudit) throws AuditNotFoundException {
        // Buscar la entidad en la base de datos
        Audits audits = auditsRepository.findById(idAudit)
                .orElseThrow(() -> new AuditNotFoundException("Audit not found with ID: " + idAudit));

        // Convertir de entidad a DTO y retornar
        return auditMapper.toDTO(audits);
    }

	@Override
	public List<AuditDTO> getAllAudits() {
		return auditsRepository.findAll().stream().map(auditMapper::toDTO).collect(Collectors.toList());
	}

	@Override
	public void deleteAuditById(Long idAudit) throws AuditsException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<AuditDTO> searchAuditsByCriteria(AuditCriteriaDTO criteria) throws AuditNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}
}
