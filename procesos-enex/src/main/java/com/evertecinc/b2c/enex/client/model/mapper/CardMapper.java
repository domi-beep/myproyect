package com.evertecinc.b2c.enex.client.model.mapper;

import org.springframework.stereotype.Component;

import com.evertecinc.b2c.enex.client.model.dto.CardDTO;
import com.evertecinc.b2c.enex.client.model.entities.Cards;

@Component
public class CardMapper {

    public CardDTO toDTO(Cards card) {
        if (card == null) {
            return null;
        }

        CardDTO cardDTO = new CardDTO();
        cardDTO.setIdCard(card.getIdCard());
        cardDTO.setIdClient(card.getIdClient().longValue());
        cardDTO.setCardnum(card.getCardNum());
        cardDTO.setRemainingAmount(card.getRemainingAmount());
        cardDTO.setCrtdate(card.getCrtDate());
        cardDTO.setUpddate(card.getUpdDate());
        cardDTO.setCardStatus(card.getCardStatus());
        cardDTO.setRestrL(card.getRestrL() != 0);
        cardDTO.setRestrM(card.getRestrM() != 0);
        cardDTO.setRestrX(card.getRestrX() != 0);
        cardDTO.setRestrJ(card.getRestrJ() != 0);
        cardDTO.setRestrV(card.getRestrV() != 0);
        cardDTO.setRestrS(card.getRestrS() != 0);
        cardDTO.setRestrD(card.getRestrD() != 0);
        cardDTO.setRestrHini(card.getRestrHini().longValue());
        cardDTO.setRestrHend(card.getRestrHend().longValue());
        cardDTO.setRestrAmountMax(card.getRestrAmountMax());
        cardDTO.setRestrDailyMaxLoads(card.getRestrDailyMaxLoads().longValue());
        cardDTO.setRestrictionType(card.getRestrictionType());
        cardDTO.setVersion(card.getVersion());
        cardDTO.setRestrDailyMaxQuantity(card.getRestrDailyMaxQuantity().longValue());
        cardDTO.setRemainingTrxLoad(card.getRemainingTrxLoad().longValue());
        cardDTO.setRemainingPeriodAmount(card.getRemainingPeriodAmount());
        cardDTO.setRestrType(card.getRestrType());
        cardDTO.setReqcardStatus(card.getReqCardStatus());
        cardDTO.setReqCardReprint(card.getReqCardReprint() != 0);
        cardDTO.setCtPosition(card.getCtPosition());
        cardDTO.setPlate(null); 

        return cardDTO;
    }
}