package com.evertecinc.b2c.enex.saf;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.evertecinc.b2c.enex.saf.model.dto.SafCriterioDTO;
import com.evertecinc.b2c.enex.saf.model.entities.SafQueue;
import com.evertecinc.b2c.enex.saf.service.SafService;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class safTest {
	
	@Autowired
	private SafService safService;

	@Test
	void contextLoadsTest() {
		log.info("**************************************************************");
		log.info("corriendo contextLoadsTest");
		log.info("**************************************************************");
	}
	
	@Test
    public void testConnection() {
        // Ejecuta una consulta simple para verificar la conexión a la base de datos
        safService.checkConexion();
        
    }
	
	@Test
    public void testFindSafByDto() {
        // Setup initial data
        SafQueue safQueue = new SafQueue();
        safQueue.setIdElement("element1");
        safQueue.setType("type1");
        safQueue.setStatus("P");
        safQueue.setDateIns(LocalDateTime.now());
        safQueue.setDateSend(LocalDateTime.now().plusDays(1));
        safQueue.setNumRetries(0);
        safQueue.setData("some data");
        safQueue.setTask(1);
        safService.save(safQueue);

        // Create the DTO with criteria
        SafCriterioDTO criterio = new SafCriterioDTO();
        criterio.setIdQueue(safQueue.getIdQueue());
        criterio.setIdElement("element1");
        criterio.setType("type1");
        criterio.setStatus("P");
        criterio.setDateins(safQueue.getDateIns());
        criterio.setDatesend(safQueue.getDateSend());
        criterio.setNumRetries(0);
        criterio.setData("some data");
        criterio.setTask(1);

        // Execute the query
        List<SafQueue> result = safService.getSaf(criterio);
        
        log.info("resultado : " + result);

        // Verify the result
//        assertNotNull(result);
//        assertEquals(1, result.size());
//        SafQueue foundQueue = result.get(0);
////        assertEquals(safQueue.getIdQueue(), foundQueue.getIdQueue());
//        assertEquals("element1", foundQueue.getIdElement());
//        assertEquals("type1", foundQueue.getType());
//        assertEquals("P", foundQueue.getStatus());
////        assertEquals(safQueue.getDateins(), foundQueue.getDateins());
////        assertEquals(safQueue.getDatesend(), foundQueue.getDatesend());
//        assertEquals(0, foundQueue.getNumRetries());
//        assertEquals("some data", foundQueue.getData());
//        assertEquals(1, foundQueue.getTask());
    }

}
