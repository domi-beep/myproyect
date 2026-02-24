package com.evertecinc.b2c.enex.audits;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.evertecinc.b2c.enex.audits.exceptions.AuditNotFoundException;
import com.evertecinc.b2c.enex.audits.exceptions.AuditsException;
import com.evertecinc.b2c.enex.audits.model.dto.AuditCriteriaDTO;
import com.evertecinc.b2c.enex.audits.model.dto.AuditDTO;
import com.evertecinc.b2c.enex.audits.model.entities.Audits;
import com.evertecinc.b2c.enex.audits.service.AuditsService;
import com.evertecinc.b2c.enex.classes.utils.db.LocalDateTimeAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class AuditsTest {
	
	@PersistenceContext
    private EntityManager entityManager;
	
	@Autowired
	private AuditsService servicio;

	private static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
            .create();
	


	@Test
	void contextLoadsTest() {
//		log.info("**************************************************************");
		log.info("corriendo contextLoadsTest");
//		log.info("**************************************************************");
	}
	
	@Test
    public void testConnection() {
        // Ejecuta una consulta simple para verificar la conexión a la base de datos
        long count = (long) entityManager.createQuery("SELECT COUNT(a) FROM Audits a").getSingleResult();

        // Verifica que la consulta se ejecutó correctamente
        assertNotNull(count);
        
//        log.info("+------------------------------------------------------------------------+");
        log.info("|Corriendo testConnection: Total de registros en Audits -> " + count);
//        log.info("+------------------------------------------------------------------------+");
    }
	
	@BeforeEach
    void setUp(TestInfo testInfo) {
        // Obtener el nombre del método de prueba
        String testMethodName = testInfo.getDisplayName();
        
        log.info("+--------------------------------------------------------------+");
        log.info("| Entrando a test: " + testMethodName);
    }
	
	@AfterEach
    void afterEach(TestInfo testInfo) {
        // Obtener el nombre del método de prueba
        String testMethodName = testInfo.getDisplayName();
        
        log.info("| Saliendo de test: " + testMethodName);
        log.info("+--------------------------------------------------------------+");
        
    }

    @Test
    @Disabled
    public void testCreateAudit() throws AuditsException, IllegalAccessException, InvocationTargetException {
        AuditDTO auditDTO = new AuditDTO();
        auditDTO.setIdAudit(1L);
        auditDTO.setAction("CREATE");
        auditDTO.setDescription("Test audit creation");

        // Ejecuta el método del servicio real
        Audits auditoria = servicio.addAudit(auditDTO);

        // Verifica el resultado
//        assertNotNull(result);
//        assertTrue(result > 0);

        // Log del resultado
//        log.info("+--------------------------------------------------------------+");
        log.info("|Corriendo testCreateAudit: Audit creado con ID -> " + auditoria);
//        log.info("+--------------------------------------------------------------+");
    }


    @Test
    @Disabled
    public void testUpdateAudit() throws AuditsException, IllegalAccessException, InvocationTargetException {
        // Crear una auditoría primero para luego actualizarla
        AuditDTO auditDTO = new AuditDTO();
        auditDTO.setAction("CREATE");
        auditDTO.setDescription("Audit for update test");

        // Crear la auditoría y obtener su ID
        Long createdAuditId = null;
        Audits auditoria = servicio.addAudit(auditDTO);
        createdAuditId = auditoria.getIdAudit();
        auditDTO.setIdAudit(auditoria.getIdAudit());

        // Actualizar la auditoría creada
        auditDTO.setDescription("Updated audit description");
        Long updatedAuditId = servicio.updateAudit(auditDTO);

        // Verifica que el ID retornado es el mismo que el de la auditoría creada
        assertEquals(createdAuditId, updatedAuditId);

        // Log del resultado
        log.info("+--------------------------------------------------------------+");
        log.info("|Corriendo testUpdateAudit: Auditoría actualizada con ID -> " + updatedAuditId);
        log.info("+--------------------------------------------------------------+");
    }


    @Test
    public void testGetAllAudits() {
        // Ejecuta el método para obtener todas las auditorías
        List<AuditDTO> result = servicio.getAllAudits();

        // Verifica que haya al menos 2 auditorías
//        assertNotNull(result);
//        assertTrue(result.size() >= 2);

        // Log del resultado
        log.info("+--------------------------------------------------------------+");
        log.info("|Corriendo testGetAllAudits: Total de auditorías -> " + result.size());
        log.info("|Corriendo testGetAllAudits: Auditorías -> " + gson.toJson(result));
        log.info("+--------------------------------------------------------------+");
    }
//
//    @Test
//    @Disabled
//    public void testDeleteAuditById() throws AuditsException {
//        // Crear una auditoría para luego eliminarla
//        AuditDTO auditDTO = new AuditDTO();
//        auditDTO.setAction("DELETE");
//        auditDTO.setDescription("Audit for delete test");
//
//        Long createdAuditId = servicio.createAudit(auditDTO);
//
//        // Elimina la auditoría
//        servicio.deleteAuditById(createdAuditId);
//
//        // Verifica que se eliminó correctamente (no se lanza excepción)
//        log.info("+--------------------------------------------------------------+");
//        log.info("|Corriendo testDeleteAuditById: Auditoría eliminada con ID -> " + createdAuditId);
//        log.info("+--------------------------------------------------------------+");
//    }

    @Test
    @Disabled
    public void testSearchAuditsByCriteria() throws AuditNotFoundException, AuditsException, IllegalAccessException, InvocationTargetException {
        // Crear auditorías para poblar la búsqueda
        AuditDTO auditDTO = new AuditDTO();
        auditDTO.setAction("SEARCH");
        auditDTO.setDescription("Audit for search test");

        servicio.addAudit(auditDTO);

        // Crea criterios de búsqueda
        AuditCriteriaDTO criteria = new AuditCriteriaDTO();
        criteria.setAction("SEARCH");

        // Ejecuta el método de búsqueda
        List<AuditDTO> result = servicio.searchAuditsByCriteria(criteria);

        // Verifica el resultado
        assertNotNull(result);
        assertTrue(result.size() > 0);

        // Log del resultado
        log.info("+--------------------------------------------------------------+");
        log.info("|Corriendo testSearchAuditsByCriteria: Total de auditorías encontradas -> " + result.size());
        log.info("+--------------------------------------------------------------+");
    }

}
