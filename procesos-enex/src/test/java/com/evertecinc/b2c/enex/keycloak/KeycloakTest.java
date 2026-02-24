package com.evertecinc.b2c.enex.keycloak;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.evertecinc.b2c.enex.classes.utils.db.LocalDateTimeAdapter;
import com.evertecinc.b2c.enex.keycloak.service.IKeycloakService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class KeycloakTest {

	@PersistenceContext
	private EntityManager entityManager;

	private @Autowired IKeycloakService keycloakService;

	private static final Gson gson = new GsonBuilder()
			.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
			.create();

	@Test
	void contextLoadsTest() {
		log.info("corriendo contextLoadsTest");
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

	@Disabled
	@Test
	public void pruebasUsuariosKeycloakTEST() throws Exception {
		log.info("************************* pruebasUsuariosKeycloakTEST *************************".toUpperCase());

		try {
			this.keycloakService.addOrUpdateUsersKeycloak();
//			this.keycloakService.removeUsersKeycloak();
		} catch (Exception e) {
			log.error("ERROR: " + e.getMessage());
		}

		log.info("************************* pruebasUsuariosKeycloakTEST END *************************".toUpperCase());
	}
	
	@Disabled
	@Test
	public void disableUsersKeycloakTEST() throws Exception {
		log.info("************************* disableUsersKeycloakTEST *************************".toUpperCase());

		try {
			this.keycloakService.disableUsersKeycloak();
		} catch (Exception e) {
			log.error("ERROR: " + e.getMessage());
		}

		log.info("************************* disableUsersKeycloakTEST END *************************".toUpperCase());
	}
	



}
