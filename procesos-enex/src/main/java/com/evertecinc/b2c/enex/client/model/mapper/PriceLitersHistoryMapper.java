package com.evertecinc.b2c.enex.client.model.mapper;

import org.springframework.stereotype.Component;

import com.evertecinc.b2c.enex.client.model.dto2.PreciosLitrosHistoryDTO;
import com.evertecinc.b2c.enex.client.model.entities.PriceLitersHistory;

@Component
public class PriceLitersHistoryMapper {

    public PreciosLitrosHistoryDTO toDTO(PriceLitersHistory entity) {
        if (entity == null) {
            return null;
        }

        return new PreciosLitrosHistoryDTO(
                entity.getIdHistory(),
                entity.getIdPriceLiter() != null ? entity.getIdPriceLiter().longValue() : null,
                entity.getUsername(),
                entity.getDate(),
                entity.getAction(),
                entity.getActionType()
        );
    }

    public PriceLitersHistory toEntity(PreciosLitrosHistoryDTO dto) {
        if (dto == null) {
            return null;
        }

        return PriceLitersHistory.builder()
                .idHistory(dto.getIdHistory())
                .idPriceLiter(dto.getIdPriceLiter() != null ? dto.getIdPriceLiter().longValue() : null)
                .username(dto.getUsername())
                .date(dto.getDate())
                .action(dto.getAction())
                .actionType(dto.getActionType())
                .build();
    }
}

