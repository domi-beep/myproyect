package com.evertecinc.b2c.enex.integracion;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import com.evertecinc.b2c.enex.integracion.model.dto.ChangeCardStatusRequest;
import com.evertecinc.b2c.enex.integracion.model.dto.ChangeCardStatusResponse;
import com.evertecinc.b2c.enex.integracion.service.OrpakRestClientService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class OrpakRestClientServiceTest {
	
	@PersistenceContext
    private EntityManager entityManager;
	
	@Autowired
	@Qualifier("OrpakRestClientServiceImpl")
	OrpakRestClientService service;
	
	@Test
	void contextLoadsTest() {
		log.info("**************************************************************");
		log.info("corriendo contextLoadsTest");
		log.info("**************************************************************");
	}
	
	@Test
    public void testChangeCardStatus() {
		
		ChangeCardStatusRequest ccsr = new ChangeCardStatusRequest();
		
		ccsr.setCardnumber("8099123000000125092");
		ccsr.setSender("");
		ccsr.setStatus("");
		
		ChangeCardStatusResponse respuesta = service.changeCardStatus(ccsr );
		
		
        
        assertNotNull(respuesta);
        
        log.info("**************************************************************");
        log.info("Corriendo testConnection: ChangeCardStatusResponse -> " + respuesta);
        log.info("**************************************************************");
    }	

}
