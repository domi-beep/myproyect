package com.evertecinc.b2c.enex.audits.model.mapper;

import org.springframework.stereotype.Component;

import com.evertecinc.b2c.enex.audits.model.dto.AuditDTO;
import com.evertecinc.b2c.enex.audits.model.entities.Audits;

@Component
public class AuditMapper {

    // De Entidad (Audits) a DTO (AuditDTO)
    public AuditDTO toDTO(Audits audits) {
        if (audits == null) {
            return null;
        }
        
        AuditDTO dto = new AuditDTO();
        dto.setIdAudit(audits.getIdAudit());
        dto.setInsertLogin(audits.getInsertLogin());
        dto.setInsertName(audits.getInsertName());
        dto.setDate(audits.getDate());
        dto.setAction(audits.getAction());
        dto.setDescription(audits.getDescription());
        dto.setSystem(audits.getSystem());

        return dto;
    }

    // De DTO (AuditDTO) a Entidad (Audits)
    public Audits toEntity(AuditDTO dto) {
        if (dto == null) {
            return null;
        }
        
        Audits audits = new Audits();
        audits.setIdAudit(dto.getIdAudit());
        audits.setInsertLogin(dto.getInsertLogin());
        audits.setInsertName(dto.getInsertName());
        audits.setDate(dto.getDate());
        audits.setAction(dto.getAction());
        audits.setDescription(dto.getDescription());
        audits.setSystem(dto.getSystem());

        return audits;
    }
}