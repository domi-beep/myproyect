package com.evertecinc.b2c.enex.client;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.evertecinc.b2c.enex.client.model.dto.ClientDTO;
import com.evertecinc.b2c.enex.client.model.entities.Client;
import com.evertecinc.b2c.enex.client.service.ClientService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class ClientServiceTest {
	
	@PersistenceContext
    private EntityManager entityManager;
	
	@Autowired
//	@Qualifier("cardsServiceImpl")
	ClientService service;

	@Test
	void contextLoadsTest() {
		log.info("**************************************************************");
		log.info("corriendo contextLoadsTest");
		log.info("**************************************************************");
	}
	
	@Test
    public void testConnection() {
        // Ejecuta una consulta simple para verificar la conexión a la base de datos
        long count = (long) entityManager.createQuery("SELECT COUNT(a) FROM Client a").getSingleResult();

        // Verifica que la consulta se ejecutó correctamente
        assertNotNull(count);
        
        log.info("**************************************************************");
        log.info("Corriendo testConnection: Total de registros en Cards -> " + count);
        log.info("**************************************************************");
    }
	
	@Test
    public void testClientDTObyId() {
		
		 ClientDTO retorno = this.service.getClientById(92L);
        log.info("**************************************************************");
        log.info("Corriendo testCardsbyId: testClientDTObyId -> " + retorno.toString());
        log.info("**************************************************************");
    }
	
	@Test
    public void testClientbyId() {
		
		 Optional<Client> retorno = this.service.getClienteById(92L);
        log.info("**************************************************************");
        log.info("Corriendo testCardsbyId: testClientbyId -> " + retorno.toString());
        log.info("**************************************************************");
    }

}
