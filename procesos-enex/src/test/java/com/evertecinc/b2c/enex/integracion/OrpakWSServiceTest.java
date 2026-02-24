package com.evertecinc.b2c.enex.integracion;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.evertecinc.b2c.enex.client.model.dto.StatusCardDTO;
import com.evertecinc.b2c.enex.integracion.model.dto.ResponseDTO;
import com.evertecinc.b2c.enex.integracion.service.OrpakWSService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class OrpakWSServiceTest {
	
	@PersistenceContext
    private EntityManager entityManager;
	
	@Autowired
	OrpakWSService service;

	@Test
	void contextLoadsTest() {
		log.info("**************************************************************");
		log.info("corriendo contextLoadsTest");
		log.info("**************************************************************");
	}
	
	
//	@Disabled
	@Test
    public void testConnection() {
		
		StatusCardDTO statusCard = new StatusCardDTO(null, null, null);
		ResponseDTO respuesta = service.updateCardStatus(statusCard , "test");
        
        assertNotNull(respuesta);
        
        log.info("**************************************************************");
        log.info("Corriendo testConnection: ChangeCardStatusResponse -> " + respuesta);
        log.info("**************************************************************");
    }	

}
