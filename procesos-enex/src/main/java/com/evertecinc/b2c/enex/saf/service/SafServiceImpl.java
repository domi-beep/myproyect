package com.evertecinc.b2c.enex.saf.service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.evertecinc.b2c.constants.Constantes;
import com.evertecinc.b2c.enex.saf.constants.SafConstants;
import com.evertecinc.b2c.enex.saf.dao.repositories.SafQueueHistoryRepository;
import com.evertecinc.b2c.enex.saf.dao.repositories.SafQueueRepository;
import com.evertecinc.b2c.enex.saf.exceptions.SafException;
import com.evertecinc.b2c.enex.saf.model.dto.MessageDTO;
import com.evertecinc.b2c.enex.saf.model.dto.MessageHistoryDTO;
import com.evertecinc.b2c.enex.saf.model.dto.SafCriterioDTO;
import com.evertecinc.b2c.enex.saf.model.entities.SafQueue;
import com.evertecinc.b2c.enex.saf.model.entities.SafQueueHistory;
import com.evertecinc.b2c.enex.saf.model.mapper.SafQueueHistoryMapper;
import com.evertecinc.b2c.enex.saf.model.mapper.SafQueueMapper;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Service("SafServiceImpl")
@RequiredArgsConstructor
@Transactional
@Slf4j
public class SafServiceImpl implements SafService {
	
	@PersistenceContext
    private EntityManager entityManager;
	
	@Autowired
    private SafQueueRepository safQueueRepository;

	@Autowired
	private SafQueueHistoryRepository safQueueHistoryRepository;
	
	@Override
	public void checkConexion() throws SafException {
		
		
		long count;
		try {
			count = (long) entityManager.createQuery("SELECT COUNT(e) FROM SafQueue e").getSingleResult();
		} catch (Exception e) {
			throw new SafException("error al conectar a la base de datos " + e.getMessage());
		}

        // Verifica que la consulta se ejecutó correctamente
        
        log.info("**************************************************************");
		log.info("corriendo testConnection " + count);
		log.info("**************************************************************");
		
	}



	@Override
	public List<SafQueue> getSaf(SafCriterioDTO dto)  throws SafException{
		 return safQueueRepository.findSafByDto(dto);
	}
	
	@Override
	public List<MessageDTO> getSafDTO(SafCriterioDTO criterio) throws SafException {
		
		if (criterio == null) {
			throw new SafException("parametro no puede ser nulo");
		}
		
		if (criterio.getType() == null || criterio.getType().isBlank()) {
			throw new SafException("parametro no puede ser nulo o vacio");
		}
		
		if (criterio.getStatus() == null || criterio.getStatus().isBlank()) {
			criterio.setStatus(SafConstants.SAF_STATUS_PENDING);
		}
		
	    // Llama al método existente para obtener la lista de entidades
	    List<SafQueue> safEntities = this.getSaf(criterio);

	    // Convierte las entidades a DTO usando el mapper
	    return safEntities.stream()
	            .map(SafQueueMapper::toDto)
	            .collect(Collectors.toList());
	}



//	@Override
//	public void save(SafQueue safQueue) {
//		safQueueRepository.save(safQueue);
//		
//	}



	@Override
	public void pushMessageHistory(MessageHistoryDTO message)  throws SafException{
		if (message == null) {
	        throw new IllegalArgumentException("MessageHistoryDTO no puede ser nulo");
	    }

		if(message.getMessage() != null && !"".equals(message.getMessage().trim())){
			int maxCaracteres = 2500;
			message.setMessage(message.getMessage().length() <= maxCaracteres ? message.getMessage() : message.getMessage().substring(0, maxCaracteres)); 
		}
		
	    SafQueueHistory safQueueHistory = SafQueueHistoryMapper.toEntity(message);
	    safQueueHistoryRepository.save(safQueueHistory);
	    log.info("Registro agregado a SafQueueHistory con id_queue: {}", safQueueHistory.getIdQueue());
		
	}



	private SafQueueHistory transformer(MessageHistoryDTO message)  throws SafException{
		SafQueueHistory retorno = new SafQueueHistory();
		
		retorno.setDateIns(message.getDateIns());
		retorno.setIdHistory(message.getIdHistory());
		retorno.setIdQueue(message.getIdQueue());
		retorno.setMessage(message.getMessage());
		retorno.setReturnCode(message.getReturnCode());
		retorno.setUri(message.getUri());
		
		return retorno;
	}



	@Override
	@Transactional
	public void updateMessage(MessageDTO message) throws SafException {
	    if (message == null || message.getIdQueue() == null) {
	        throw new IllegalArgumentException("MessageDTO o su idQueue no pueden ser nulos");
	    }

	    // Buscar el registro existente en la base de datos
	    SafQueue safQueue = safQueueRepository.findById(message.getIdQueue())
	            .orElseThrow(() -> new SafException("No se encontró el registro con idQueue: " + message.getIdQueue()));

	    try {
			// Actualizar los valores de la entidad con los datos del DTO
			safQueue.setIdElement(message.getIdElement() != null ? message.getIdElement().toString() : safQueue.getIdElement());
			safQueue.setType(message.getType() != null ? message.getType() : safQueue.getType());
			safQueue.setStatus(message.getStatus() != null ? message.getStatus() : safQueue.getStatus());
			safQueue.setDateIns(message.getDateIns() != null ? message.getDateIns() : safQueue.getDateIns());
			safQueue.setDateSend(message.getDateSend() != null ? message.getDateSend() : safQueue.getDateSend());
			safQueue.setNumRetries(message.getNumRetries() != null ? message.getNumRetries().intValue() : safQueue.getNumRetries());
			safQueue.setData(message.getData() != null ? message.getData() : safQueue.getData());
			if(message.getTask() != null) {
				safQueue.setTask(message.getTask().intValue());
			}else if(safQueue.getTask() != null){
				safQueue.setTask(safQueue.getTask());
			}

			// Guardar la entidad actualizada
			safQueueRepository.save(safQueue);
			log.info("Registro actualizado en SafQueue con idQueue: {}", safQueue.getIdQueue());
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
	}



	@Override
	public void save(SafQueue safQueue)  throws SafException{
		safQueueRepository.save(safQueue);
	}



	@Override
	public int getTotalSafConditionalsPendings(String type, Long idTask)  throws SafException{
		try {
            // Convertimos idTask (Long) a Integer según el mapeo en la entidad
            Integer taskAsInteger = (idTask != null) ? idTask.intValue() : null;

            long count = safQueueRepository.countByTypeAndStatusAndTask(
                    type,
                    Constantes.ORDER_STATUS_PENDING,  // "P" o el valor que uses para “pendiente”
                    taskAsInteger
            );

            return (int) count; // se convierte a int, como en el original
        } catch (Exception e) {
            throw new SafException("Error al obtener total de condicionados pendientes", e);
        }
	}



	@Override
	public void pushMessageUpdateDepartmentStatus(Long idDepartment)  throws SafException{
		MessageDTO message = new MessageDTO();
		message.setIdElement(idDepartment);
		message.setType(SafConstants.SAF_UPDATE_DEPARTMENT_STATUS);
		
		// 2) Convertimos el DTO a la Entidad
        SafQueue safQueue = new SafQueue();
        safQueue.setIdElement(message.getIdElement().toString());
        safQueue.setType(message.getType());
        safQueue.setStatus(message.getStatus());
        safQueue.setData(message.getData());
        
        // Si 'numRetries' en la entidad es un int, hacemos cast.
        safQueue.setNumRetries(
                message.getNumRetries() != null ? message.getNumRetries().intValue() : 0
        );
        
        // Dependiendo de cómo manejes 'task' (Long vs Integer):
        if (message.getTask() != null) {
            safQueue.setTask(message.getTask().intValue());
        }
        
		safQueueRepository.save(safQueue);
	}



	@Override
	public Long pushMessageSafConditional(MessageDTO message)  throws SafException{
		try {
            // 1) Ajustamos los valores iniciales
            message.setStatus(SafConstants.SAF_STATUS_PENDING);
            message.setNumRetries(0L);

            // 2) Convertimos el DTO a la Entidad
            SafQueue safQueue = new SafQueue();
            safQueue.setIdElement(message.getIdElement().toString());
            safQueue.setType(message.getType());
            safQueue.setStatus(message.getStatus());
            safQueue.setData(message.getData());
            
            // Si 'numRetries' en la entidad es un int, hacemos cast.
            safQueue.setNumRetries(
                    message.getNumRetries() != null ? message.getNumRetries().intValue() : 0
            );
            
            // Dependiendo de cómo manejes 'task' (Long vs Integer):
            if (message.getTask() != null) {
                safQueue.setTask(message.getTask().intValue());
            }
            
            // Opcionalmente setea dateIns, dateSend u otros campos si corresponden.

            // 3) Guardamos en la BD y obtenemos el registro creado
            SafQueue savedEntity = safQueueRepository.save(safQueue);

            // 4) Retornamos el ID generado
            return savedEntity.getIdQueue();

        } catch (Exception e) {
            log.error("Ha ocurrido un problema al agregar el mensaje a la cola. " + message, e);
            throw new SafException("Ha ocurrido un problema al agregar el mensaje a la cola. " + message, e);
        }
	}



	@Override
	public void pushMessageUpdateDepartmentBalanceConditional(Long idElement, BigDecimal saldoDepto, String producto,
			String tipoDoc, Long idUtb2)  throws SafException{


		// 1) Configuramos el formateador para que use punto como separador decimal
	    DecimalFormat decimalFormat = new DecimalFormat("#####0.####");
	    DecimalFormatSymbols decimalFormatSymbols = decimalFormat.getDecimalFormatSymbols();
	    decimalFormatSymbols.setDecimalSeparator('.');
	    decimalFormat.setDecimalFormatSymbols(decimalFormatSymbols);

	    // 2) Construimos el contenido de 'data'
	    String data = decimalFormat.format(saldoDepto)
	                  .concat(";")
	                  .concat(producto)
	                  .concat(";")
	                  .concat(tipoDoc);

	    // 3) Creamos el objeto MessageDTO
	    MessageDTO message = new MessageDTO();
	    message.setIdElement(idElement);
	    message.setData(data);
	    message.setType(SafConstants.SAF_UPDATE_DEPARTMENT_BALANCE);
	    message.setTask(idUtb2);

	    // 4) Invocamos el método que se encarga de enviar/almacenar el mensaje
	    SafQueue entity = SafQueueMapper.toEntity(message);
	    safQueueRepository.save(entity);;
		
	}



	@Override
	public void pushMessageUpdateCardNumberConditional(Long idVehicle, Long idTask)  throws SafException{

		MessageDTO message = new MessageDTO();
		message.setIdElement(idVehicle);
		message.setType(SafConstants.SAF_UPDATE_CARD_NUMBER);
		message.setTask(idTask);
		
		// 2) Convertimos el DTO a la Entidad
        SafQueue safQueue = new SafQueue();
        safQueue.setIdElement(message.getIdElement().toString());
        safQueue.setType(message.getType());
        safQueue.setStatus(message.getStatus());
        safQueue.setData(message.getData());
        
        // Si 'numRetries' en la entidad es un int, hacemos cast.
        safQueue.setNumRetries(
                message.getNumRetries() != null ? message.getNumRetries().intValue() : 0
        );
        
        // Dependiendo de cómo manejes 'task' (Long vs Integer):
        if (message.getTask() != null) {
            safQueue.setTask(message.getTask().intValue());
        }
        
		safQueueRepository.save(safQueue);
		
	}



	@Override
	public void pushMessageUpdateClient(Long idClient) throws SafException {

		MessageDTO message = new MessageDTO();
		message.setIdElement(idClient);
		message.setType(SafConstants.SAF_UPDATE_CLIENT_ORPAK);
//		message.setTask(idTask);
		
		// 2) Convertimos el DTO a la Entidad
        SafQueue safQueue = new SafQueue();
        safQueue.setIdElement(idClient.toString());
        safQueue.setType(SafConstants.SAF_UPDATE_CLIENT_ORPAK);
        safQueue.setStatus(SafConstants.SAF_STATUS_PENDING);
        safQueue.setData(message.getData());
        safQueue.setDateIns(LocalDateTime.now());
        
        // Si 'numRetries' en la entidad es un int, hacemos cast.
        safQueue.setNumRetries(0);
        
        // Dependiendo de cómo manejes 'task' (Long vs Integer):
//        if (message.getTask() != null) {
//            safQueue.setTask(message.getTask().intValue());
//        }
        
		safQueueRepository.save(safQueue);
		
	}



	@Override
	public void pushMessageUpdateCardConstraint(Long idCard, String data) throws SafException {
		
		
		SafQueue safQueue = new SafQueue();
        safQueue.setIdElement(idCard.toString());
        safQueue.setType(SafConstants.SAF_UPDATE_CARD_NUMBER);
        safQueue.setStatus("P");
        safQueue.setData(data);
        
        // Si 'numRetries' en la entidad es un int, hacemos cast.
        safQueue.setNumRetries(0);
        
                
		safQueueRepository.save(safQueue);
		
	}



	@Override
	public void updRetriesById(Long idQueue) {
		safQueueRepository.incrementRetriesById(idQueue);
	}
	

	

	

}
