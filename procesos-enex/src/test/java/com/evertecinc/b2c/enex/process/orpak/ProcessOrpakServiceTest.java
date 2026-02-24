package com.evertecinc.b2c.enex.process.orpak;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.evertecinc.b2c.enex.process.orpak.exceptions.ProcessOrpakException;
import com.evertecinc.b2c.enex.process.orpak.service.IProcessOrpakService;
import com.evertecinc.b2c.enex.saf.model.dto.MessageDTO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class ProcessOrpakServiceTest {
	
	@PersistenceContext
    private EntityManager entityManager;
	
	@Autowired
	IProcessOrpakService processOrpakService;

	@Test
	void contextLoadsTest() {
		log.info("**************************************************************");
		log.info("corriendo contextLoadsTest");
		log.info("**************************************************************");
	}
	
//	@Test
//    public void testConnection() {
//        // Ejecuta una consulta simple para verificar la conexión a la base de datos
//        long count = (long) entityManager.createQuery("SELECT COUNT(a) FROM Audits a").getSingleResult();
//
//        // Verifica que la consulta se ejecutó correctamente
//        assertNotNull(count);
//        
//        log.info("**************************************************************");
//        log.info("Corriendo testConnection: Total de registros en Audits -> " + count);
//        log.info("**************************************************************");
//    }
	
	
	@Test
	void updPendingCardStatusChangesTest() {
		MessageDTO mensaje = new MessageDTO(null, null, null, null, null, null, null, null, null);
		try {
			this.processOrpakService.updPendingCardStatusChanges(mensaje);
		} catch (ProcessOrpakException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
