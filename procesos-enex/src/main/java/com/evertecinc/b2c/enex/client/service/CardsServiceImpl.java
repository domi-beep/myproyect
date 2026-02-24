package com.evertecinc.b2c.enex.client.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.evertecinc.b2c.enex.client.dao.repositories.CardsRepository;
import com.evertecinc.b2c.enex.client.model.dto.CardDTO;
import com.evertecinc.b2c.enex.client.model.entities.Cards;
import com.evertecinc.b2c.enex.client.model.mapper.CardMapper;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CardsServiceImpl implements CardsService {

	
	@Autowired
    private CardsRepository cardsRepository;
	
	@Autowired
	private CardMapper cardMapper;
	
	private Optional<Cards> findCardById(Long idCard) {
        return Optional.ofNullable(cardsRepository.findByIdCard(idCard));
    }
	
	@Override
	public CardDTO getCardById(Long idCard) {
		
		log.info("recuperando card con id " + idCard);
		
		Optional<CardDTO> card = this.findCardById(idCard).map(cardMapper::toDTO);
		
		if (card.isEmpty()) {
			//agregar log de error y retornar null, revisar el funcionamiento esperado
			return null;
		}
		
		CardDTO retorno = card.get();
		return retorno ;
	}

	

}
