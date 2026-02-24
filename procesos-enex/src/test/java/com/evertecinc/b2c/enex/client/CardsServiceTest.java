package com.evertecinc.b2c.enex.client;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.evertecinc.b2c.enex.client.model.dto.CardDTO;
import com.evertecinc.b2c.enex.client.service.CardsService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class CardsServiceTest {
	
	@PersistenceContext
    private EntityManager entityManager;
	
	@Autowired
//	@Qualifier("cardsServiceImpl")
	CardsService service;

	@Test
	void contextLoadsTest() {
		log.info("**************************************************************");
		log.info("corriendo contextLoadsTest");
		log.info("**************************************************************");
	}
	
	@Test
    public void testConnection() {
        // Ejecuta una consulta simple para verificar la conexión a la base de datos
        long count = (long) entityManager.createQuery("SELECT COUNT(a) FROM Cards a").getSingleResult();

        // Verifica que la consulta se ejecutó correctamente
        assertNotNull(count);
        
        log.info("**************************************************************");
        log.info("Corriendo testConnection: Total de registros en Cards -> " + count);
        log.info("**************************************************************");
    }
	
	@Test
    public void testCardsbyId() {
		
		CardDTO retorno = this.service.getCardById(94L);        
        log.info("**************************************************************");
        log.info("Corriendo testCardsbyId: CardDTO -> " + retorno.toString());
        log.info("**************************************************************");
    }

}
