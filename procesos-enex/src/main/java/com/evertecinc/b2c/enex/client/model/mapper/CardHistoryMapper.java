package com.evertecinc.b2c.enex.client.model.mapper;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.evertecinc.b2c.enex.client.model.dto.CardHistoryDTO;
import com.evertecinc.b2c.enex.client.model.entities.Cards;
import com.evertecinc.b2c.enex.client.model.entities.CardsHistory;
import com.evertecinc.b2c.enex.client.model.entities.User;

@Component
public class CardHistoryMapper {

    public CardsHistory toEntity(CardHistoryDTO dto, Cards card, User user) {
        return new CardsHistory(
                null, // El ID se genera automáticamente
                card,
                user,
                dto.getUsername(),
                dto.getDate() != null ? dto.getDate() : LocalDateTime.now(),
                dto.getAction(),
                dto.getActionType()
        );
    }
}