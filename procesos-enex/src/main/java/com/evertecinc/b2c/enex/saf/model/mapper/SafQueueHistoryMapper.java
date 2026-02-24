package com.evertecinc.b2c.enex.saf.model.mapper;

import java.time.LocalDateTime;

import com.evertecinc.b2c.enex.saf.model.dto.MessageHistoryDTO;
import com.evertecinc.b2c.enex.saf.model.entities.SafQueueHistory;

public class SafQueueHistoryMapper {

    // Convertir de DTO a Entidad
    public static SafQueueHistory toEntity(MessageHistoryDTO dto) {
        if (dto == null) {
            return null;
        }
        SafQueueHistory entity = new SafQueueHistory();
        entity.setIdQueue(dto.getIdQueue() != null ? dto.getIdQueue().longValue() : null);
        entity.setDateIns(dto.getDateIns() != null ? dto.getDateIns() : LocalDateTime.now());
        entity.setReturnCode(dto.getReturnCode());
        entity.setMessage(dto.getMessage());
        entity.setUri(dto.getUri());
        return entity;
    }

    // Convertir de Entidad a DTO
    public static MessageHistoryDTO toDto(SafQueueHistory entity) {
        if (entity == null) {
            return null;
        }
        MessageHistoryDTO dto = new MessageHistoryDTO();
        dto.setIdQueue(entity.getIdQueue() != null ? Long.valueOf(entity.getIdQueue()) : null);
        dto.setDateIns(entity.getDateIns());
        dto.setReturnCode(entity.getReturnCode());
        dto.setMessage(entity.getMessage());
        dto.setUri(entity.getUri());
        return dto;
    }
}