package com.evertecinc.b2c.enex.client.dao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.evertecinc.b2c.enex.client.model.entities.Cards;

@Repository
public interface CardsRepo extends JpaRepository<Cards, Long> {
	public Cards findByIdCard(Long idCard);

	public Long countByIdClientAndCardStatus(Long idCLient, String cardStatus);

	public Long countByIdClientAndCardStatusNot(Long idCLient, String cardStatus);
	
	public Cards findByCardNum(String cardNum);

}
