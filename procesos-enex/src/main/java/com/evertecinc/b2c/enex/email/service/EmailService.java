package com.evertecinc.b2c.enex.email.service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.evertecinc.b2c.constants.PropertiesConstants;
import com.evertecinc.b2c.enex.client.dao.repositories.ClientsUsersRelRepo;
import com.evertecinc.b2c.enex.client.dao.repositories.UsersRepo;
import com.evertecinc.b2c.enex.client.dao.repositories.VariablesRepo;
import com.evertecinc.b2c.enex.client.exceptions.ClientException;
import com.evertecinc.b2c.enex.client.model.dto2.ContactDTO;
import com.evertecinc.b2c.enex.client.model.dto2.EnvoltorioMailContactDTO;
import com.evertecinc.b2c.enex.client.model.dto2.restResponses.UsuarioClientPortalesResultDTO;
import com.evertecinc.b2c.enex.client.model.entities.Clients;
import com.evertecinc.b2c.enex.client.model.entities.ClientsUsersRel;
import com.evertecinc.b2c.enex.client.model.entities.ClientsUsersRelId;
import com.evertecinc.b2c.enex.client.model.entities.Users;
import com.evertecinc.b2c.enex.client.model.entities.Variables;
import com.evertecinc.b2c.enex.email.constants.NotificationConstants;
import com.evertecinc.b2c.enex.email.dao.repositories.EmailConfigRepository;
import com.evertecinc.b2c.enex.email.dao.repositories.MailQueueRepository;
import com.evertecinc.b2c.enex.email.exceptions.MailQueueException;
import com.evertecinc.b2c.enex.email.model.dto.AlertMailDTO;
import com.evertecinc.b2c.enex.email.model.dto.MailQUeueCriterio;
import com.evertecinc.b2c.enex.email.model.dto.SafCorreoBienvenida;
import com.evertecinc.b2c.enex.email.model.dto.SafCorreoReestablecerPassword;
import com.evertecinc.b2c.enex.email.model.dto.SendMessageDTO;
import com.evertecinc.b2c.enex.email.model.entities.EmailConfig;
import com.evertecinc.b2c.enex.email.model.entities.MailQueue;
import com.evertecinc.b2c.enex.model.dto.request.UsuarioClientePortalesRequestDTO;
import com.evertecinc.b2c.enex.utils.functions.Functions;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import jakarta.annotation.PostConstruct;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmailService {
	
	@Autowired
    private JavaMailSender emailSender;
	
	@Autowired
	private EmailConfigRepository emailConfigRepository;
	
	@Autowired
	private MailQueueRepository mailQueueRepository;
	
	@Autowired
    private Configuration freemarkerConfig;
	
	@Autowired
	private ClientsUsersRelRepo clientsUsersRelRepoJPA;

	@Autowired
	private UsersRepo usersRepoJPA;
	
	@Autowired
	private VariablesRepo variablesRepoJPA;
    
	private String imgContenido = "";
	
	
	@Value("${notification.mailfromname}")
	private String mailfromname;
	
	
	private HashMap<String, String> portalNameList = new HashMap<String, String>();

	@Value("${notification.mailfrom}")
	private String mailFrom;
	
	@PostConstruct
    public void init() {
        // Procesa la cadena para convertirla en un HashMap
        String[] pairs = mailfromname.split("#");
        for (String pair : pairs) {
            String[] keyValue = pair.split(";");
            if (keyValue.length == 2) {
            	portalNameList.put(keyValue[0], keyValue[1]);
            }
        }
    }

    /**
	 * @deprecated Use {@link #sendMessage(SendMessageDTO)} instead
	 */
	public void sendMessage(List<String> to, List<String> cc, List<String> bcc, String from, String replyTo, String subject, String text, List<String> attachmentsPaths, boolean isHtml) throws Exception {
		sendMessage(new SendMessageDTO(to, cc, bcc, from, replyTo, subject, text, attachmentsPaths, isHtml));
	}
	
	 @PostConstruct
	    public void checkMailSender() {
	        MimeMessage message = emailSender.createMimeMessage();
	        if (message == null) {
	            log.error("El método createMimeMessage() retornó null");
	        } else {
	            log.info("createMimeMessage() devolvió un MimeMessage válido: {}", message);
	        }
	    }

	public void sendMessage(SendMessageDTO emailDTO) throws Exception {
	    try {
	        // Crear el MimeMessage a través de emailSender
	        MimeMessage message = emailSender.createMimeMessage();
	        if (message == null) {
	            log.error("El objeto MimeMessage retornado por emailSender es nulo. Verifique la configuración de JavaMailSender.");
	            throw new IllegalStateException("MimeMessage es nulo");
	        }
	        
	        // Inicializar el helper para el mensaje, permitiendo contenido multipart
	        MimeMessageHelper helper = new MimeMessageHelper(message, true);
	        log.trace("MimeMessageHelper creado correctamente.");
	        
	        // Validar que la lista de destinatarios 'to' no esté vacía
	        if (emailDTO.to == null || emailDTO.to.isEmpty()) {
	            throw new IllegalArgumentException("El parámetro 'to' no puede ser nulo o vacío");
	        }
	        
	        // Configurar campos opcionales
	        if (emailDTO.cc != null && !emailDTO.cc.isEmpty()) {
	            helper.setCc(emailDTO.cc.toArray(new String[0]));
	            log.trace("Se estableció CC: {}", emailDTO.cc);
	        }
	        if (emailDTO.bcc != null && !emailDTO.bcc.isEmpty()) {
	            helper.setBcc(emailDTO.bcc.toArray(new String[0]));
	            log.trace("Se estableció BCC: {}", emailDTO.bcc);
	        }
	        if (emailDTO.from != null && !emailDTO.from.isEmpty()) {
	            helper.setFrom(emailDTO.from);
	            log.trace("Se estableció From: {}", emailDTO.from);
	        }
	        if (emailDTO.replyTo != null && !emailDTO.replyTo.isEmpty()) {
	            helper.setReplyTo(emailDTO.replyTo);
	            log.trace("Se estableció Reply-To: {}", emailDTO.replyTo);
	        }
	        
	        // Configurar destinatarios, asunto y cuerpo
	        helper.setTo(emailDTO.to.toArray(new String[0]));
	        helper.setSubject(emailDTO.subject);
	        helper.setText(emailDTO.text, emailDTO.isHtml);
	        log.trace("Mensaje configurado: subject={}, isHtml={}", emailDTO.subject, emailDTO.isHtml);
	        
	        // Si existen rutas de adjuntos, agregarlos
	        if (emailDTO.attachmentsPaths != null && !emailDTO.attachmentsPaths.isEmpty()) {
	            for (String attachmentsPath : emailDTO.attachmentsPaths) {
	                FileSystemResource file = new FileSystemResource(new File(attachmentsPath));
	                // Verificamos que el nombre del archivo no sea nulo
	                String filename = file.getFilename();
	                if (filename != null) {
	                    helper.addAttachment(filename, file);
	                    log.trace("Adjunto agregado: {}", filename);
	                } else {
	                    log.warn("El archivo en '{}' no tiene nombre y no se agregará como adjunto", attachmentsPath);
	                }
	            }
	        }
	        
	        // Agregar header de notificación (opcional)
	        message.addHeader("Disposition-Notification-To", "diego.rojas@evertecinc.com");
	        
	        // Enviar el mensaje
	        emailSender.send(message);
	        log.info("Correo enviado exitosamente a {}", String.join(", ", emailDTO.to));
	        
	    } catch (MailException | MessagingException | jakarta.mail.MessagingException ex) {
	        log.error("Error enviando correo", ex);
	        throw ex;
	    }
	}

	
	


	private void addToMailQueue(MailQueue mailQueue) throws MailQueueException {
		
		if (mailQueue == null) {
	        throw new MailQueueException("MailQueue no puede ser null");
	    }

	    if (mailQueue.getQueuedDate() == null) {
	    	mailQueue.setQueuedDate(LocalDateTime.now());
	    }

	    if (mailQueue.getStatus() == null || mailQueue.getStatus().isEmpty()) {
	    	mailQueue.setStatus("P");//MailQueueConstants.QMAIL_STATUS_PENDING);
	    }

	    if (mailQueue.getRecipient() == null || mailQueue.getRecipient().isEmpty()) {
	        throw new MailQueueException("Recipient no puede ser nulo o vacio");
	    }

	    if (mailQueue.getSubject() == null || mailQueue.getSubject().isEmpty()) {
	        throw new MailQueueException("Subject no puede ser nulo o vacio");
	    }

	    if (mailQueue.getBody() == null || mailQueue.getBody().isEmpty()) {
	        throw new MailQueueException("Body no puede ser nulo o vacio");
	    }

	    if (mailQueue.getFrom() == null || mailQueue.getFrom().isEmpty()) {
	        throw new MailQueueException("From no puede ser nulo o vacio");
	    }

	    if (mailQueue.getRecipientName() == null || mailQueue.getRecipientName().isEmpty()) {
	        throw new MailQueueException("Recipient no puede ser nulo o vacio");
	    }

	    if (mailQueue.getFromName() == null || mailQueue.getFromName().isEmpty()) {
	        throw new MailQueueException("From no puede ser nulo o vacio");
	    }

	    if (mailQueue.getAttempts() == null || mailQueue.getAttempts() < 0) {
	    	mailQueue.setAttempts(0);
	    }

	   
	    mailQueueRepository.save(mailQueue);
		
		
	}
	
	public List<MailQueue> getMailQueue(MailQUeueCriterio criterio){
		return this.mailQueueRepository.findByStatusAndAttemptsLessThan(criterio.getStatus(), criterio.getAttempts());
	}
	
	public MailQueue updMailQueue(MailQueue criterio){
		return this.mailQueueRepository.save(criterio);
	}
	
	
	public void sendAlertClient(AlertMailDTO alert) throws MailException {
	    
	    if (alert == null) {
	        log.error("Parametro de entrada null.");
	        throw new IllegalArgumentException("Parametro de entrada null.");
	    }

	    if (NotificationConstants.STOCK_CHANNEL_NONE.equals(alert.getChannel())) {
	        log.info("No se debe enviar por ningún canal.");
	        return;
	    }

	    if (alert.getEmail() == null) {
	        log.error("Parametro de entrada email es null.");
	        throw new IllegalArgumentException("Parametro de entrada email es null.");
	    }

	    if (NotificationConstants.STOCK_CHANNEL_ALL.equals(alert.getChannel()) || 
	        NotificationConstants.STOCK_CHANNEL_EMAIL.equals(alert.getChannel())) {
	        
	        String mailBody = null;
	        // Mapa con el modelo que se le pasará al template del cuerpo del mail
	        Map<String, Object> model = new HashMap<>();
	        model.put("alert", alert);
	        model.put("url", imgContenido + "-" + alert.getTipoCliente() + "/");
	        String portalName = portalNameList.get(alert.getTipoCliente());
			model.put("portal", portalName);

	        EmailConfig mailConfig = this.emailConfigRepository.findByIdEmail(alert.getIdMailConfig());
	        
	        model.put("alert", alert);
	        
	        if (mailConfig != null) {
	            // Procesamos el cuerpo del mail con FreeMarker
	            try {
	            	Template template = freemarkerConfig.getTemplate(alert.getTemplate());//"SCT-mail_alert_clientlocked.html");
	                mailBody = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
	            } catch (IOException | TemplateException e) {
	                log.error("Error al aplicar template FreeMarker: ", e);
	                mailBody = "Alerta: " + alert.toString();
	            }

	            // Se separan los correos de destino
	            String[] mailTo = alert.getEmail().split(",");
	            
	            ArrayList<String>  arrayMailTo = new ArrayList<>(Arrays.asList(mailTo));
	            
	            
	            //se deja acá en el mailqueue
	            
	            MailQueue mailQueue = new MailQueue();
	            
	            
	            mailQueue.setFrom(this.mailFrom);
	            
	            mailQueue.setRecipient(mailTo[0]);
	            mailQueue.setSubject(mailConfig.getSubject());
	            mailQueue.setBody(mailBody);
	            
	            mailQueue.setRecipientName(mailTo[0]);
	            
	            mailQueue.setFromName("Sender Name");
	            mailQueue.setAttempts(1);
	            
	            

	            // Agregamos mail a la cola
	            try {
	                this.addToMailQueue(mailQueue);
//
//	                // Si existe un segundo mail, se agrega a la cola
//	                if (mailTo.length > 1) {
//	                    AddMailQueueDTO addMail2 = new AddMailQueueDTO(addMail);
//	                    addMail2.setMailRecipent(mailTo[1]);
//	                    addMail2.setMailRecipentName(mailTo[1]);
//	                    mailQueueService.addToSendMailQueue(addMail2);
//	                }

	            } catch (Exception e) {
	                log.error("No se envió correctamente el mail. " + e.getMessage());
	            }

	            log.info("Notificación de Alerta enviada correctamente.");
	        } else {
	            log.warn("No hay datos de configuración del mail de alertas.");
	        }
	    }

	    if (NotificationConstants.STOCK_CHANNEL_ALL.equals(alert.getChannel()) || 
	        NotificationConstants.STOCK_CHANNEL_SMS.equals(alert.getChannel())) {
	        log.info("SMS Funcionalidad no implementada.");
	        return;
	    }
	}
	
	public void sendContact(EnvoltorioMailContactDTO envoltorio) throws MailException {
	    
		ContactDTO contact = envoltorio.getContact();
		
	    if (contact == null) {
	        log.error("Parametro de entrada null.");
	        throw new IllegalArgumentException("Parametro de entrada null.");
	    }

//	    if (NotificationConstants.STOCK_CHANNEL_NONE.equals(alert.getChannel())) {
//	        log.info("No se debe enviar por ningún canal.");
//	        return;
//	    }

	    if (contact.getMail() == null) {
	        log.error("Parametro de entrada email es null.");
	        throw new IllegalArgumentException("Parametro de entrada email es null.");
	    }

        String mailBody = null;
        // Mapa con el modelo que se le pasará al template del cuerpo del mail
        Map<String, Object> model = new HashMap<>();
        
//        model.put("url", "https://mi-servidor-de-imagenes.com/"); // Ruta base para imágenes
        model.put("nombre", contact.getNombre());
        model.put("apellidoPaterno", contact.getApellidoPaterno());
        model.put("apellidoMaterno", contact.getApellidoMaterno());
        model.put("mail", contact.getMail());
        model.put("empresaCliente", contact.getEmpresaCliente());
        model.put("rutEmpresa", contact.getRutEmpresa());
        model.put("idMotivo", contact.getIdMotivo());
        model.put("nombreMotivo", contact.getNombreMotivo());
        model.put("comentario", contact.getComentario());
        
        model.put("contact", contact);
        
        model.put("url", imgContenido + "http://www.tarjeta-empresa.cl/ptsf/img/mail-" + contact.getClientType() + "/");
        String portalName = portalNameList.get(contact.getClientType());
		model.put("portal", portalName);

        EmailConfig mailConfig = this.emailConfigRepository.findByIdEmail(contact.getIdMotivo());
        
        model.put("alert", contact);
        
        if (mailConfig != null) {
            // Procesamos el cuerpo del mail con FreeMarker
            try {
            	Template template = freemarkerConfig.getTemplate("mailContacto/"+ contact.getClientType() +"-mail_contacto.html");
                mailBody = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
            } catch (IOException | TemplateException e) {
                log.error("Error al aplicar template FreeMarker: ", e);
                mailBody = "Alerta: " + contact.toString();
            }

            // Se separan los correos de destino
            String[] mailTo = contact.getMail().split(",");
            
            ArrayList<String>  arrayMailTo = new ArrayList<>(Arrays.asList(mailTo));
            
            
            //se deja acá en el mailqueue
            
            MailQueue mailQueue = new MailQueue();
            
            
            mailQueue.setFrom(envoltorio.getMailFrom());
            
            mailQueue.setRecipient(envoltorio.getMailRecipent());
            mailQueue.setSubject(envoltorio.getMailSubject());
            mailQueue.setBody(mailBody);
            
            mailQueue.setRecipientName(envoltorio.getMailRecipentName());
            
            mailQueue.setFromName(envoltorio.getMailFromName());
            mailQueue.setAttempts(1);
            
            

            // Agregamos mail a la cola
            try {
                this.addToMailQueue(mailQueue);
//
//	                // Si existe un segundo mail, se agrega a la cola
//	                if (mailTo.length > 1) {
//	                    AddMailQueueDTO addMail2 = new AddMailQueueDTO(addMail);
//	                    addMail2.setMailRecipent(mailTo[1]);
//	                    addMail2.setMailRecipentName(mailTo[1]);
//	                    mailQueueService.addToSendMailQueue(addMail2);
//	                }

            } catch (Exception e) {
                log.error("No se envió correctamente el mail. " + e.getMessage());
            }

            log.info("Notificación de Alerta enviada correctamente.");
        } else {
            log.warn("No hay datos de configuración del mail de alertas.");
        }
	   
	}
	
	public Boolean SendMailBienvenidaKeycloak(SafCorreoBienvenida params) throws Exception {
	    
		log.info("Inicio servicio creacion email bienvenida Keycloak");
		log.info("Parametros entrada: "+params.toString());
		
		UsuarioClientePortalesRequestDTO paramsService = new UsuarioClientePortalesRequestDTO();
		
		paramsService.setIdCliente(params.getIdCliente());
		paramsService.setIdUsuario(params.getIdUsuario());
		paramsService.setPortalUsuario(params.getPortalUsuario());
		
		UsuarioClientPortalesResultDTO response = this.conseguirUsuarioClientePortales(paramsService);
		
		Users usuario = response.getUsuario();
		Clients cliente = response.getCliente();
		String portalUsuarioCompleto = response.getPortalUsuarioCompleto();
		String portalUsuario = response.getPortalUsuario();
		String colorPortal = response.getColorPortal();
		

        String mailBody = null;
        // Mapa con el modelo que se le pasará al template del cuerpo del mail
        Map<String, Object> model = new HashMap<>();
        
        model.put("imgPortal",response.getUrlImagenesS3()+"logo-"+response.getPortalUsuario().toLowerCase()+".png" ); 
        model.put("portalCreacionUsuario", portalUsuarioCompleto);        
        model.put("nombreUsuario", usuario.getName()+" "+usuario.getFirstName());
        model.put("colorPortal", colorPortal);        
        model.put("urlPortal", response.getUrlPortalFrontend());//
        model.put("imgPaso2", response.getUrlImagenesS3()+"01-"+response.getPortalUsuario().toLowerCase()+".png");     
        model.put("imgPaso3", response.getUrlImagenesS3()+"02-"+response.getPortalUsuario().toLowerCase()+".png");        
        model.put("imgPaso5", response.getUrlImagenesS3()+"03-"+response.getPortalUsuario().toLowerCase()+".png");   
        
        
        
        // Procesamos el cuerpo del mail con FreeMarker
        try {
        	Template template = freemarkerConfig.getTemplate("mailLogin/bienvenida.html");
            mailBody = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
        } catch (IOException | TemplateException e) {
            log.error("Error al aplicar template FreeMarker: ", e);
            throw e;
        }

        // Se separan los correos de destino
        String[] mailTo = usuario.getEmail().split(",");
        
        ArrayList<String>  arrayMailTo = new ArrayList<>(Arrays.asList(mailTo));
        
        //se deja acá en el mailqueue
        
        MailQueue mailQueue = new MailQueue();
        
        
        mailQueue.setFrom(this.mailFrom);
        
        mailQueue.setRecipient(mailTo[0]);
        mailQueue.setSubject("Creacion Cuenta en "+portalUsuarioCompleto);
        mailQueue.setBody(mailBody);
        
        mailQueue.setRecipientName(mailTo[0]);
        
        mailQueue.setFromName(portalUsuarioCompleto);
        mailQueue.setAttempts(1);
        
        

        // Agregamos mail a la cola
        try {
            this.addToMailQueue(mailQueue);

        } catch (Exception e) {
            log.error("No se envió correctamente el mail. " + e.getMessage());
            throw new Exception("No se envio correctamente el mail: "+e.getMessage());
        }

        log.info("correo enviado exitosamente");
        return true;
	   
	}
	
	public UsuarioClientPortalesResultDTO conseguirUsuarioClientePortales(UsuarioClientePortalesRequestDTO params) {
		
		UsuarioClientPortalesResultDTO response = new UsuarioClientPortalesResultDTO();
		log.info("Parametros de entrada: "+params.toString());
		
		if(params == null || params.getPortalUsuario() == null) {
	    	log.error("Portal usuario llego null");
	    	throw new IllegalArgumentException("Portal usuario llego null");
	    }else if(!"BO".equals(params.getPortalUsuario()) && (params.getIdCliente() == null || params.getIdUsuario() == null)) {
	    	log.error("Portal: {} es obligatorio el idCliente: {} y el parametro idUsuario: {}",params.getPortalUsuario(),params.getIdCliente(),params.getIdUsuario());
	    	throw new IllegalArgumentException("Para el portal "+params.getPortalUsuario()+" el parametro idUsuario: "+params.getIdUsuario()+" y el parametro idCliente: "+params.getIdCliente()
	    										+" son obligatorios");
	    }else if("BO".equals(params.getPortalUsuario()) && params.getIdUsuario() == null) {
	    	log.error("El parametro idUsuario: "+params.getIdUsuario()+" para el portal: "+params.getPortalUsuario()+" es obligatorio");
	    	throw new IllegalArgumentException("El parametro idUsuario: "+params.getIdUsuario()+" para el portal: "+params.getPortalUsuario()+" es obligatorio");
	    }else if(!Functions.existePortal(params.getPortalUsuario().toUpperCase())) {
	    	log.error("No existe el portal "+params.getPortalUsuario().toUpperCase());
	    	throw new IllegalArgumentException("No existe el portal "+params.getPortalUsuario().toUpperCase());
	    }
		
		
		String portalUsuario = params.getPortalUsuario().toUpperCase();
		String portalUsuarioCompleto = Functions.getPortalName(portalUsuario);
		Long idUsuario = params.getIdUsuario();
		Long idCliente = params.getIdCliente();
		Users usuario = new Users();
		Clients cliente = new Clients();
		
		log.info("Parametros validados con portal {} idUsuario {} y idCliente {}",portalUsuario,idUsuario,idCliente);
		
		if(!"BO".equals(portalUsuario)) {
			log.info("Al ser portal distinto de BackOffice, se busca la realcion entre el idCliente: {} y el idUsuario: {}",idCliente,idUsuario);
			// ****** USUARIO Y CLIENTE SESSION ******
			ClientsUsersRelId idRelacion = new ClientsUsersRelId();
			idRelacion.setIdClient(idCliente);
			idRelacion.setIdUser(idUsuario);
			Optional<ClientsUsersRel> optionalClientUserRel = this.clientsUsersRelRepoJPA.findById(idRelacion);
			
			if (optionalClientUserRel.isEmpty()) {
				log.error("No se encontro relacion para el usuario " + idUsuario + " y cliente "+ idCliente);
						throw new ClientException("No se encontro relacion para el usuario " + idUsuario + " y cliente "+ idCliente);
			}

			ClientsUsersRel clienteUsuarioRelacion = optionalClientUserRel.get();
			
			if(clienteUsuarioRelacion.getClient() == null || clienteUsuarioRelacion.getUser() == null) {
				log.error("No se encontro cliente o usuario en la relacion: "+clienteUsuarioRelacion.toString());
				throw new ClientException("No se encontro cliente o usuario en la relacion: "+clienteUsuarioRelacion.toString());
			}
			usuario = clienteUsuarioRelacion.getUser();
			cliente = clienteUsuarioRelacion.getClient();
			log.info("relacion encontrada con el usuario: {} y el cliente: {}",usuario.toString(),cliente.toString());
			// ****** USUARIO Y CLIENTE SESSION FIN ******
		}else {
			log.info("Al ser portal BackOffice buscamos al usuario con id: {}",idUsuario);
			
			usuario = this.usersRepoJPA.findByIdUser(idUsuario);
			
			cliente = null;
			
			if(usuario == null) {
				log.error("No se encontro al usuario con id: {}",idUsuario);
				throw new ClientException("No se encontro al usuario con id: "+idUsuario);
			}
			
		}
		
		try {
			List<Variables> listaVariables = this.variablesRepoJPA.findByCodigoPortal(PropertiesConstants.NOMBRE_VARIABLE_TODOS_PORTALES);
			
			if(listaVariables.isEmpty()) {
				log.error("No se encontraron variables con el codigo portal: "+PropertiesConstants.NOMBRE_VARIABLE_TODOS_PORTALES);
				throw new Exception("No se encontraron variables con el codigo portal: "+PropertiesConstants.NOMBRE_VARIABLE_TODOS_PORTALES);
			}
			List<Variables> listaRetorno = new ArrayList<Variables>();
			String host = "";
			String path = "";
			for(Variables variable :listaVariables ) {
				if(PropertiesConstants.PROPERTIES_HOST_S3.equals(variable.getNombre())) {
					host = variable.getValor();
				}else if(PropertiesConstants.PROPERTIES_EMAIL_PATH_S3.equals(variable.getNombre())) {
					path = variable.getValor();
				}
			}
			
			if(host.isBlank() || path.isBlank()) {
				log.error("Variable Host: "+host+" o Path: "+path+" llegaron vacias");
				throw new Exception("Variable Host: "+host+" o Path: "+path+" llegaron vacias");
			}
			
			String urlImagenes = host+"/"+portalUsuario+path;
			log.info("Url de imagenes: "+urlImagenes);
			response.setUrlImagenesS3(urlImagenes);
			
			
			List<Variables> variablePortal = this.variablesRepoJPA.findByCodigoPortalAndNombre(portalUsuario.toUpperCase(),PropertiesConstants.PROPERTIES_NOMBRE_URL_PORTAL);
			
			if(variablePortal.isEmpty()) {
				log.error("No se encontraron variables con el codigo portal: "+portalUsuario.toUpperCase());
				throw new Exception("No se encontraron variables con el codigo portal: "+PropertiesConstants.PROPERTIES_NOMBRE_URL_PORTAL);
			}
			
			response.setUrlPortalFrontend(variablePortal.getFirst().getValor());
			
		}catch(Exception e) {
			log.error("Ocurrio un error al extraer las variables de la base de datos, Error: "+e.getMessage());
			throw new ClientException("Ocurrio un error al extraer las variables de la base de datos, Error: "+e.getMessage());
		}
		
		
		
		String colorPortal = "#00479C";
		
		switch(portalUsuario) {
		case "BO": colorPortal = "#00479C";break;
		case "SCT":colorPortal = "#CE2027";break;
		case "SCE":colorPortal = "#707070";break;
		case "SCI":colorPortal = "#707070";break;
		case "SCS":colorPortal = "#008443";break;
		case "EPR":colorPortal = "#00479C";break;
		default:colorPortal = "#00479C";break;
		
		}
		
		response.setColorPortal(colorPortal);
		response.setCliente(cliente);
		response.setPortalUsuario(portalUsuario);
		response.setPortalUsuarioCompleto(portalUsuarioCompleto);
		response.setUsuario(usuario);
		
		log.info("Respuesta validada: "+response.toString());
		
		return response;
		
		
		
		
	}
	
	public Boolean SendMailReestablecerClaveKeycloak(SafCorreoReestablecerPassword params) throws Exception {
	    
		log.info("Inicio servicio creacion email bienvenida Keycloak");
		
		log.info("Parametros entrada: "+params.toString());
		
		UsuarioClientePortalesRequestDTO paramsService = new UsuarioClientePortalesRequestDTO();
		
		paramsService.setIdCliente(params.getIdCliente());
		paramsService.setIdUsuario(params.getIdUsuario());
		paramsService.setPortalUsuario(params.getPortalUsuario());
		
		UsuarioClientPortalesResultDTO response = this.conseguirUsuarioClientePortales(paramsService);
		
		Users usuario = response.getUsuario();
		Clients cliente = response.getCliente();
		String portalUsuarioCompleto = response.getPortalUsuarioCompleto();
		String portalUsuario = response.getPortalUsuario();
		String colorPortal = response.getColorPortal();

        String mailBody = null;
        // Mapa con el modelo que se le pasará al template del cuerpo del mail
        Map<String, Object> model = new HashMap<>();
        
        
        model.put("nombreUsuario", usuario.getName()+" "+usuario.getFirstName()); //Nombre y apellido usuario
        model.put("colorPortal", colorPortal);        
        model.put("portalCreacionUsuario", portalUsuarioCompleto);        
        model.put("urlPortal", response.getUrlPortalFrontend());//
        model.put("imgPortal",response.getUrlImagenesS3()+"logo-"+response.getPortalUsuario().toLowerCase()+".png" ); 
        model.put("imgPaso2", response.getUrlImagenesS3()+"01-"+response.getPortalUsuario().toLowerCase()+".png");     
        model.put("imgPaso3", response.getUrlImagenesS3()+"02-"+response.getPortalUsuario().toLowerCase()+".png");        
        model.put("imgPaso5", response.getUrlImagenesS3()+"03-"+response.getPortalUsuario().toLowerCase()+".png");      
        
        
        // Procesamos el cuerpo del mail con FreeMarker
        try {
        	Template template = freemarkerConfig.getTemplate("mailLogin/reestablecer-password.html");
            mailBody = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
        } catch (IOException | TemplateException e) {
            log.error("Error al aplicar template FreeMarker: ", e);
            throw e;
        }

        // Se separan los correos de destino
        String[] mailTo = usuario.getEmail().split(",");
        
        ArrayList<String>  arrayMailTo = new ArrayList<>(Arrays.asList(mailTo));
        
        //se deja acá en el mailqueue
        
        MailQueue mailQueue = new MailQueue();
        
        
        mailQueue.setFrom(this.mailFrom);
        
        mailQueue.setRecipient(mailTo[0]);
        mailQueue.setSubject("Reestablecer Cuenta en "+portalUsuarioCompleto);
        mailQueue.setBody(mailBody);
        
        mailQueue.setRecipientName(mailTo[0]);
        
        mailQueue.setFromName(portalUsuarioCompleto);
        mailQueue.setAttempts(1);
        
        

        // Agregamos mail a la cola
        try {
            this.addToMailQueue(mailQueue);

        } catch (Exception e) {
            log.error("No se envió correctamente el mail. " + e.getMessage());
            throw new Exception("No se envio correctamente el mail: "+e.getMessage());
        }

        log.info("correo enviado exitosamente");
        return true;
	   
	}

}