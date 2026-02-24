package com.evertecinc.b2c.enex.email;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.evertecinc.b2c.enex.classes.utils.db.LocalDateTimeAdapter;
import com.evertecinc.b2c.enex.client.model.dto2.SugerenciaDTO;
import com.evertecinc.b2c.enex.email.model.dto.SendMessageDTO;
import com.evertecinc.b2c.enex.email.service.EmailService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
@ActiveProfiles("test")
class EmailTest {
	
	@PersistenceContext
    private EntityManager entityManager;
	
//	@MockBean
//    private JavaMailSender emailSender;
	
	@Autowired
    private EmailService servicio;

	private static final Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter()).create();
	
	
	@Disabled
	@Test
	void sendMessage_validInput_success() throws Exception {
	    // Arrange: Usar el JavaMailSender real (asegúrate de tener la configuración real en application.properties)
	    SendMessageDTO emailDTO = new SendMessageDTO(
	        List.of("dirc81@gmail.com"),  // Destinatario real
	        List.of("dirc81+cc@gmail.com"),           // CC (opcional)
	        List.of("dirc81+bcc@gmail.com"),          // BCC (opcional)
	        "drojas@bbr.cl",                          // From
	        "drojas@bbr.cl",                          // Reply-To
	        "Test Subject",                           // Asunto
	        "",                              		  // Cuerpo
	        null,                                     // Rutas de adjuntos (si aplica)
	        true                                     // Indica si es HTML o no
	    );

	    // Act: Enviar el mensaje con el servicio real
	    servicio.sendMessage(emailDTO);

	    // Assert: Aquí, al tratarse de un test de integración que envía correo real,
	    // puedes revisar manualmente la bandeja de entrada para confirmar el envío.
	    // También puedes imprimir un log de confirmación.
	    log.info("Email enviado correctamente con sendMessage.");
	}
	
	
	
	@Test
	void sendsendContact_success() throws Exception {
	    // Arrange: Usar el JavaMailSender real (asegúrate de tener la configuración real en application.properties)
		
		Gson gson = new Gson();
		String json = "{\"mailRecipent\":\"tarjeta@enex.cl\",\"mailSubject\":\"Sugerencia\",\"contact\":{\"nombre\":\"AndresBO\",\"apellidoPaterno\":\"Ricardo\",\"apellidoMaterno\":\"Arjona\",\"mail\":\"dirc81@gmail.com\",\"idMotivo\":10,\"nombreMotivo\":\"Sugerencia\",\"comentario\":\"Yerko, prueba el pan con palta con huevo, maldita sea\",\"clientType\":\"SCT\"},\"mailRecipentName\":\"tarjeta@enex.cl\",\"portal\":\"SCT\",\"mailFromName\":\"Portal Shellcard Transporte\",\"mailFrom\":\"tarjeta@enex.cl\",\"url\":\"http://www.tarjeta-empresa.cl/ptsf/img/mail-PRE/\"}";
		SugerenciaDTO emailDTO = gson.fromJson(json, SugerenciaDTO.class);

	    // Act: Enviar el mensaje con el servicio real
	    //servicio.sendContact(emailDTO.getContact());

	    // Assert: Aquí, al tratarse de un test de integración que envía correo real,
	    // puedes revisar manualmente la bandeja de entrada para confirmar el envío.
	    // También puedes imprimir un log de confirmación.
	    log.info("Email enviado correctamente con sendMessage.");
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
	
	
	

   

}
