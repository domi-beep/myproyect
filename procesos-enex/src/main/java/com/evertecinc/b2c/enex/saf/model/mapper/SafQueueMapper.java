package com.evertecinc.b2c.enex.saf.model.mapper;

import com.evertecinc.b2c.enex.saf.model.dto.MessageDTO;
import com.evertecinc.b2c.enex.saf.model.entities.SafQueue;

public class SafQueueMapper {

    // Convertir de entidad a DTO
    public static MessageDTO toDto(SafQueue entity) {
        if (entity == null) {
            return null;
        }
        MessageDTO dto = new MessageDTO();
        dto.setIdQueue(entity.getIdQueue());
        dto.setIdElement(entity.getIdElement() != null ? Long.valueOf(entity.getIdElement()) : null);
        dto.setType(entity.getType());
        dto.setStatus(entity.getStatus());
        dto.setDateIns(entity.getDateIns());
        dto.setDateSend(entity.getDateSend());
        dto.setNumRetries((long) entity.getNumRetries());
        dto.setData(entity.getData());
        dto.setTask(entity.getTask() != null ? entity.getTask().longValue() : null);
        return dto;
    }

    // Convertir de DTO a entidad
    public static SafQueue toEntity(MessageDTO dto) {
        if (dto == null) {
            return null;
        }
        SafQueue entity = new SafQueue();
        entity.setIdQueue(dto.getIdQueue());
        entity.setIdElement(dto.getIdElement() != null ? dto.getIdElement().toString() : null);
        entity.setType(dto.getType());
        entity.setStatus(dto.getStatus());
        entity.setDateIns(dto.getDateIns());
        entity.setDateSend(dto.getDateSend());
        entity.setNumRetries(dto.getNumRetries() != null ? dto.getNumRetries().intValue() : 0);
        entity.setData(dto.getData());
        entity.setTask(dto.getTask() != null ? dto.getTask().intValue() : null);
        return entity;
    }
}