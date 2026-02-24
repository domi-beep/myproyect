package com.evertecinc.b2c.enex.client.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.evertecinc.b2c.constants.AuditoriaConstans;
import com.evertecinc.b2c.enex.audits.model.dto.AuditDTO;
import com.evertecinc.b2c.enex.audits.service.AuditsService;
import com.evertecinc.b2c.enex.client.dao.extended.IStationClientsRepo;
import com.evertecinc.b2c.enex.client.dao.repositories.CardsHistoryRepo;
import com.evertecinc.b2c.enex.client.dao.repositories.CardsRepository;
import com.evertecinc.b2c.enex.client.dao.repositories.ClientRepository;
import com.evertecinc.b2c.enex.client.dao.repositories.DepartmentRepository;
import com.evertecinc.b2c.enex.client.dao.repositories.PriceLitersRepo;
import com.evertecinc.b2c.enex.client.dao.repositories.ProductsDepartmentsRelRepo;
import com.evertecinc.b2c.enex.client.dao.repositories.StationCardConstraintRepo;
import com.evertecinc.b2c.enex.client.dao.repositories.StationDepartmentConstraintsRepo;
import com.evertecinc.b2c.enex.client.dao.repositories.UserRepository;
import com.evertecinc.b2c.enex.client.dao.repositories.VehiclesRepo;
import com.evertecinc.b2c.enex.client.exceptions.CardException;
import com.evertecinc.b2c.enex.client.exceptions.ClientException;
import com.evertecinc.b2c.enex.client.exceptions.ClientNotFoundException;
import com.evertecinc.b2c.enex.client.exceptions.ClientServiceException;
import com.evertecinc.b2c.enex.client.exceptions.DepartmentException;
import com.evertecinc.b2c.enex.client.exceptions.NuevoMontoException;
import com.evertecinc.b2c.enex.client.exceptions.OTException;
import com.evertecinc.b2c.enex.client.exceptions.PriceLiterNotFoundException;
import com.evertecinc.b2c.enex.client.exceptions.ProductDepartmentException;
import com.evertecinc.b2c.enex.client.exceptions.VehicleException;
import com.evertecinc.b2c.enex.client.model.dto.CardDTO;
import com.evertecinc.b2c.enex.client.model.dto.CardHistoryDTO;
import com.evertecinc.b2c.enex.client.model.dto.ClientDTO;
import com.evertecinc.b2c.enex.client.model.dto.CriterioOtHeaderDTO;
import com.evertecinc.b2c.enex.client.model.dto.DepartmentDTO;
import com.evertecinc.b2c.enex.client.model.dto.MonitorCardDTO;
import com.evertecinc.b2c.enex.client.model.dto.OtHeaderDTO;
import com.evertecinc.b2c.enex.client.model.dto.OtItemDTO;
import com.evertecinc.b2c.enex.client.model.dto.ProductDepartmentDTO;
import com.evertecinc.b2c.enex.client.model.dto.RegisterClientDTO;
import com.evertecinc.b2c.enex.client.model.dto.StationClientsCriterioDTO;
import com.evertecinc.b2c.enex.client.model.dto.StationsDTO;
import com.evertecinc.b2c.enex.client.model.dto.StatusCardDTO;
import com.evertecinc.b2c.enex.client.model.dto.VehicleDTO;
import com.evertecinc.b2c.enex.client.model.dto2.orpak.GetDepartmentBalanceResponse;
import com.evertecinc.b2c.enex.client.model.dto2.orpak.RequestBalanceDepartmentDTO;
import com.evertecinc.b2c.enex.client.model.dto2.orpak.ResponseBalanceDepartmentDTO;
import com.evertecinc.b2c.enex.client.model.dto2.orpakInterface.GetDepartmentBalanceRequest;
import com.evertecinc.b2c.enex.client.model.dto2.requests.StationsRequestDTO;
import com.evertecinc.b2c.enex.client.model.entities.Cards;
import com.evertecinc.b2c.enex.client.model.entities.CardsHistory;
import com.evertecinc.b2c.enex.client.model.entities.Client;
import com.evertecinc.b2c.enex.client.model.entities.Department;
import com.evertecinc.b2c.enex.client.model.entities.PriceLiters;
import com.evertecinc.b2c.enex.client.model.entities.ProductsDepartmentsRel;
import com.evertecinc.b2c.enex.client.model.entities.ProductsDepartmentsRel.ProductsDepartmentsRelId;
import com.evertecinc.b2c.enex.client.model.entities.StationCardConstraint;
import com.evertecinc.b2c.enex.client.model.entities.StationDepartmentConstraints;
import com.evertecinc.b2c.enex.client.model.entities.User;
import com.evertecinc.b2c.enex.client.model.entities.Vehicles;
import com.evertecinc.b2c.enex.client.model.mapper.CardHistoryMapper;
import com.evertecinc.b2c.enex.client.model.mapper.CardMapper;
import com.evertecinc.b2c.enex.client.model.mapper.ClientMapper;
import com.evertecinc.b2c.enex.client.model.mapper.DepartmentMapper;
import com.evertecinc.b2c.enex.client.model.mapper.ProductsDepartmentsRelMapper;
import com.evertecinc.b2c.enex.integracion.constants.ClientConstants;
import com.evertecinc.b2c.enex.integracion.constants.OrpakWSConstants;
import com.evertecinc.b2c.enex.integracion.exception.OrpakException;
import com.evertecinc.b2c.enex.integracion.exception.OrpakWSExceptions;
import com.evertecinc.b2c.enex.integracion.service.OrpakRestClientService;
import com.evertecinc.b2c.enex.process.jde.dto.CriterioBusquedaCardDTO;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
//import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ClientServiceImpl implements ClientService {
	
	@Autowired
    private ClientRepository clientRepository;
	
	@Autowired
	private DepartmentRepository departmentRepository;
	
	@Autowired
	private CardsRepository cardsRepository;
	
	@Autowired
    private UserRepository userRepository;
	
	@Autowired
    private CardsHistoryRepo cardsHistoryRepository;
	
	@Autowired
    private ProductsDepartmentsRelRepo productsDepartmentsRelRepo;
	
    @Autowired
    private VehiclesRepo vehiclesRepo;
    
    @Autowired
    private StationDepartmentConstraintsRepo stationDepartmentConstraintsRepo;
    
    @Autowired
    private StationCardConstraintRepo stationCardConstraintRepo;
	
	@Autowired
	@Qualifier("OrpakRestClientServiceDummyImpl")
	OrpakRestClientService orpakAdapter;
	
	private final PriceLitersRepo priceLiterJPA;	

    @Autowired
    private ClientMapper clientMapper; // Inyección del mapper para convertir Client a ClientDTO
    
    @Autowired
    private DepartmentMapper departmentMapper;
    
    @Autowired
    private CardMapper cardsMapper;
    
    @Autowired
    private CardHistoryMapper cardHistoryMapper;    
    
    @Autowired
    private AuditsService auditsService;
    

    @Autowired
	private IStationClientsRepo stationClientsRepoExtend;
    
    
    
    @Transactional // Garantiza que el contexto de sesión esté abierto durante la inicialización
    @Override
    public Optional<Client> getClienteById(Long idElement) throws ClientServiceException {
        return clientRepository.findById(idElement)
                .map(client -> {
                    // Inicializa relaciones Lazy
                    Hibernate.initialize(client.getAddressDisArea());
                    Hibernate.initialize(client.getAddressDisCity());
                    Hibernate.initialize(client.getAddressDisRegion());

                    // Inicializa relaciones en cascada
                    if (client.getAddressDisRegion() != null) {
                        Hibernate.initialize(client.getAddressDisRegion().getZone());
                    }

                    return client;
                });
    }

    @Override
    public ClientDTO getClientById(Long idElement) throws ClientServiceException {
        return getClienteById(idElement) // Reutiliza getClienteById
                .map(clientMapper::toDto) // Convierte a ClientDTO
                .orElseThrow(() -> new ClientServiceException("Client not found with id " + idElement));
    }

	@Override
	public DepartmentDTO getListDepartmentById(Long idElement) {
		return departmentRepository.findById(idElement)
	            .map(departmentMapper::toDto)
	            .orElseThrow(() -> new EntityNotFoundException("Department not found with id: " + idElement));
	}

	@Override
	public List<CardDTO> getCardsByDepartment(Long idElement, boolean b) {
		List<Cards> cards = cardsRepository.findByDepartment(idElement);
        return cards.stream()
                    .map(cardsMapper::toDTO)
                    .collect(Collectors.toList());
	}

	@Override
	public void updCardStatus(StatusCardDTO cardStatus) {
		if (cardStatus.getIdCard() == null) {
	        throw new IllegalArgumentException("El ID de la tarjeta no puede ser nulo.");
	    }

	    // Buscar la tarjeta en la base de datos
	    Cards card = cardsRepository.findById(cardStatus.getIdCard())
	            .orElseThrow(() -> new EntityNotFoundException("No se encontró la tarjeta con ID " + cardStatus.getIdCard()));

	    // Solo actualizamos si los valores no son null
	    if (cardStatus.getCardnum() != null) {
	        card.setCardNum(cardStatus.getCardnum());
	    }
	    if (cardStatus.getCardStatus() != null) {
	        card.setCardStatus(cardStatus.getCardStatus());
	    }

	    // Guardar la entidad actualizada
	    cardsRepository.save(card);
		
	}

	@Override
	public void addCardHistory(CardHistoryDTO cardHistory) {
		if (cardHistory.getIdCard() == null || cardHistory.getIdUser() == null) {
            throw new IllegalArgumentException("El ID de la tarjeta y el ID del usuario no pueden ser nulos.");
        }

        // Buscar la tarjeta en la base de datos
        Cards card = cardsRepository.findById(cardHistory.getIdCard())
                .orElseThrow(() -> new EntityNotFoundException("No se encontró la tarjeta con ID " + cardHistory.getIdCard()));

        // Buscar el usuario en la base de datos
        User user = userRepository.findById(cardHistory.getIdUser())
                .orElseThrow(() -> new EntityNotFoundException("No se encontró el usuario con ID " + cardHistory.getIdUser()));

        
		CardsHistory history = cardHistoryMapper.toEntity(cardHistory, card, user);
        cardsHistoryRepository.save(history);
		
	}

	@Override
	public List<MonitorCardDTO> getCardVehicleByDepartment(Long idDepartment) {
		if(idDepartment == null){
			log.error("Parametro de entrada id department es nulo");
			throw new IllegalArgumentException("Parametro de entrada id department es nulo");
		}
		return cardsRepository.getCardVehicleByDepartment(idDepartment);
	}

	@Override
	public List<ProductDepartmentDTO> doProductsDepartmentsByCriteria(ProductDepartmentDTO productDepartmentDTO) {
		if(productDepartmentDTO == null){
			log.error("Parametro de entrada criteria productsDepartments es nulo");
			throw new IllegalArgumentException("Parametro de entrada criteria productsDepartments es nulo");
		}
    	
		try {
			this.updDepartmentBalance( productDepartmentDTO.getIdDepartment() );
		} catch (DepartmentException  | NuevoMontoException e) {
			log.error("Ha ocurrido un error. " + e.getMessage());
			throw new DepartmentException( "Ha ocurrido un error al recuperar saldos desde Orpak." );
		}


		
		List<ProductDepartmentDTO> listado = getProductsDeparmentsByCriteria(productDepartmentDTO);
		
    	return listado;
	}

	private List<ProductDepartmentDTO> getProductsDeparmentsByCriteria(ProductDepartmentDTO productDepartmentDTO) {
		List<ProductsDepartmentsRel> listEntityProdDeptoRel = this.productsDepartmentsRelRepo.getProductsDepartmentsByCriteria(productDepartmentDTO);
		List<ProductDepartmentDTO> listado = new ArrayList<ProductDepartmentDTO>();
		for(ProductsDepartmentsRel pdr:listEntityProdDeptoRel){
			ProductDepartmentDTO pdDto = new ProductDepartmentDTO();
			pdDto.setIdDepartment(pdr.getIdDepartment());
			pdDto.setProductCode(pdr.getProductCode());
			pdDto.setDocumentType(pdr.getDocumentType());
			pdDto.setRemainingAmount(pdr.getRemainingAmount());
			listado.add(pdDto);
		}
		return listado;
	}
	
	@Override
	public void updDepartmentProductAmountSMov(ProductDepartmentDTO dto) throws NuevoMontoException {
		try {
	        // 1) Construimos la llave compuesta a partir de los datos del DTO.
	        ProductsDepartmentsRelId relId = 
	            new ProductsDepartmentsRelId(dto.getIdDepartment(), dto.getProductCode(), dto.getDocumentType());

	        // 2) Buscamos si ya existe el registro (id_department + product_code + document_type).
	        Optional<ProductsDepartmentsRel> optionalPdep = productsDepartmentsRelRepo.findById(relId);

	        if (optionalPdep.isPresent()) {
	            // 2a) Si existe, actualizamos el remainingAmount (o cualquier otro campo que haga falta).
	            ProductsDepartmentsRel pdep = optionalPdep.get();
	            pdep.setRemainingAmount(dto.getRemainingAmount());
	            productsDepartmentsRelRepo.save(pdep);
	        } else {
	            // 2b) Si no existe, creamos uno nuevo.
	            ProductsDepartmentsRel newRecord = new ProductsDepartmentsRel();
	            newRecord.setIdDepartment(dto.getIdDepartment());
	            newRecord.setProductCode(dto.getProductCode());
	            newRecord.setDocumentType(dto.getDocumentType());
	            newRecord.setRemainingAmount(dto.getRemainingAmount());
	            try {
	            	productsDepartmentsRelRepo.save(newRecord);
	            } catch (Exception e) {
	                log.error("No se pudo crear un nuevo registro de productsDepartmentsRel ", e);
	                throw new NuevoMontoException(
	                    "No se pudo crear un nuevo registro del producto para el departamento.", e
	                );
	            }
	        }

	        // 3) Agregamos auditoría si es que el monto no es nulo.
	        if (dto.getRemainingAmount() != null) {
	        	
	        	String mensaje = "DepartmentBalance Departamento " 
	                    + dto.getIdDepartment() 
	                    + " Producto " 
	                    + dto.getProductCode() 
	                    + " Monto " 
	                    + dto.getRemainingAmount();
	            
	            AuditDTO auditoria = new AuditDTO();
				auditoria.setInsertLogin(AuditoriaConstans.USUARIO_SISTEMA);
				auditoria.setInsertName(AuditoriaConstans.USUARIO_SISTEMA);
				auditoria.setAction(AuditoriaConstans.ACCION_CAMBIO_BALANCE);
				auditoria.setIdElemento(dto.getIdDepartment());
				auditoria.setTipoElemento(AuditoriaConstans.TIPO_ELEMENTO_DEPARTAMENTO);
				auditoria.setDescription(mensaje);
				auditoria.setSystem(AuditoriaConstans.USUARIO_PROCESO);

				try {
					this.auditsService.addAudit(auditoria);
				} catch (Exception au) {
					log.warn("Problema al guardar auditoria ", au);
				}
	            
	        }

	    } catch (Exception e) {
	        log.error("(BBR) Error al actualizar el monto del departamento que viene desde orpak", e);
	        throw new NuevoMontoException(
	            "(BBR) Error al actualizar el monto del departamento que viene desde orpak", e
	        );
	    }
	}
	
	private ProductDepartmentDTO saveProductDepartmentRel(ProductDepartmentDTO dto) {
        try {
            // 1) Convertir de DTO a Entidad
            ProductsDepartmentsRel entity = ProductsDepartmentsRelMapper.toEntity(dto);

            // 2) Guardar la entidad en la base de datos
            ProductsDepartmentsRel savedEntity = productsDepartmentsRelRepo.save(entity);

            // 3) Convertir la entidad guardada de vuelta a DTO
            return ProductsDepartmentsRelMapper.toDto(savedEntity);

        } catch (Exception e) {
            // Manejo de la excepción según tu lógica
            // (podrías lanzar una excepción custom, hacer logs, etc.)
            throw new RuntimeException("Error al guardar ProductDepartmentRel", e);
        }
    }

	private void updDepartmentBalance(Long idDepartment) throws DepartmentException{

		try {
			log.info("Recuperar departamento " + idDepartment);
			Optional<Department> departamento = this.departmentRepository.findById(idDepartment);
			DepartmentDTO depto = null;
			if (departamento.isPresent()) {
				depto = departmentMapper.toDto(departamento.get()); 
			}
			log.debug(""+depto);

			if( !ClientConstants.TYPE_BALANCE_DEPTO.equals(depto.getTypeBalance()) ) {
				log.warn("El departamento no tiene type balance a nivel de departamento, se sale del m�todo.");
				return;
			}
			
			log.info("Obtener saldos para id orpak factura " + depto.getCodeOrpakInvoice());
			if( depto.getCodeOrpakInvoice() != null ) {
				
				RequestBalanceDepartmentDTO requestBalanceDepartmentDTO = new RequestBalanceDepartmentDTO();
				requestBalanceDepartmentDTO.setIdOrpakDepartment( depto.getCodeOrpakInvoice() );
				requestBalanceDepartmentDTO.setIdClient( depto.getIdClient() );
				requestBalanceDepartmentDTO.setIdDepartment(idDepartment);
				
				ClientDTO cliente = null;
				Optional<Client> clienterepo = this.clientRepository.findById(depto.getIdClient());
				if(clienterepo.isPresent()) {
					cliente = clientMapper.toDto(clienterepo.get());
				}
				
				if(ClientConstants.CLIENT_SCS.equals(cliente.getClientType())){
					requestBalanceDepartmentDTO.setTypeBalance(OrpakWSConstants.ORPAK_WS_DEPARTMENT_BALANCE_QUANTITY);
				}else{
					requestBalanceDepartmentDTO.setTypeBalance(OrpakWSConstants.ORPAK_WS_DEPARTMENT_BALANCE_AMOUNT);
				}
				
				
				log.info("Recuperar saldos del departamento " + requestBalanceDepartmentDTO);
				List<ResponseBalanceDepartmentDTO> respuesta = new ArrayList<ResponseBalanceDepartmentDTO>();
				try {
					respuesta = this.getDepartmentBalance(requestBalanceDepartmentDTO);
				} catch (OrpakWSExceptions e2) {
					log.warn("Ha ocurrido un error al obtener los datos pero continua el proceso: " + e2.getMessage());
				}
				for (ResponseBalanceDepartmentDTO responseBalanceDepartmentDTO : respuesta) {
					log.info("Actualizar saldo para " + responseBalanceDepartmentDTO);
					
					// Se Actualiza posteriormente el Monto del departamento de origen
					ProductDepartmentDTO productDepartmentDTO = new ProductDepartmentDTO();
					productDepartmentDTO.setIdDepartment( responseBalanceDepartmentDTO.getIdDepartment() );
					productDepartmentDTO.setProductCode( responseBalanceDepartmentDTO.getCode() );
					productDepartmentDTO.setDocumentType( ClientConstants.DOCUMENT_TYPE_INVOICE );

					log.debug("Procesar: "+productDepartmentDTO);
					
					// Se valida que el producto exista en ese departamento y de no existir se debe crear la relaci�n
					List<ProductDepartmentDTO> listaProd = new ArrayList<ProductDepartmentDTO>();
					try {
						listaProd = getProductsDeparmentsByCriteria(productDepartmentDTO);
					} catch (DepartmentException e1) {
						log.error("Ha ocurrido un error al obtener los datos: " + e1.getMessage());
						throw new ProductDepartmentException("Ha ocurrido un error al obtener los datos: " + e1.getMessage());
					}
					productDepartmentDTO.setRemainingAmount( responseBalanceDepartmentDTO.getBalance() );
					if(listaProd.size()==0){
						log.info("El producto no existe para el departamento, se debe crear. " + productDepartmentDTO);
						ProductsDepartmentsRel productDepartmentRel = ProductsDepartmentsRelMapper.toEntity(productDepartmentDTO);
						this.productsDepartmentsRelRepo.save(productDepartmentRel);
//						this.clientsMgr.addProductDepartmentRel(productDepartmentDTO);
					} else { 
						try {
							log.info("Actualizar saldo del departamento/producto con la respuesta de Orpak.");
							this.updDepartmentProductAmountSMov(productDepartmentDTO);
						} catch (NuevoMontoException e) {
							log.error("(BBR) Error al actualizar el departamento con el Monto de orpak"+ e.getMessage());
							throw new ProductDepartmentException("(BBR) Error al actualizar el departamento con el Monto de orpak"+ e.getMessage());
						}
					}
						
				}				
				
			}
			
			log.info("Obtener saldos para id orpak boleta " + depto.getCodeOrpakTicket());
			if( depto.getCodeOrpakTicket() != null ) {
				
				RequestBalanceDepartmentDTO requestBalanceDepartmentDTO = new RequestBalanceDepartmentDTO();
				requestBalanceDepartmentDTO.setIdOrpakDepartment( depto.getCodeOrpakTicket() );
				requestBalanceDepartmentDTO.setIdClient( depto.getIdClient() );
				requestBalanceDepartmentDTO.setIdDepartment(idDepartment);
				
				
				RegisterClientDTO cliente = null;
				Optional<Client> clientEntity = this.clientRepository.findById(depto.getIdClient());
				
				if (clientEntity.isPresent()) {
					cliente = ClientMapper.entityToDto(clientEntity.get());
				}
				
				
				if(ClientConstants.CLIENT_SCS.equals(cliente.getClientType())){
					requestBalanceDepartmentDTO.setTypeBalance(OrpakWSConstants.ORPAK_WS_DEPARTMENT_BALANCE_QUANTITY);
				}else{
					requestBalanceDepartmentDTO.setTypeBalance(OrpakWSConstants.ORPAK_WS_DEPARTMENT_BALANCE_AMOUNT);
				}
				
				log.info("Recuperar saldos del departamento " + requestBalanceDepartmentDTO);
				List<ResponseBalanceDepartmentDTO> respuesta = new ArrayList<ResponseBalanceDepartmentDTO>();
				try {
					respuesta = this.getDepartmentBalance(requestBalanceDepartmentDTO);
				} catch (OrpakWSExceptions e2) {
					log.warn("Ha ocurrido un error al obtener los datos pero continua el proceso: " + e2.getMessage());
				}
				for (ResponseBalanceDepartmentDTO responseBalanceDepartmentDTO : respuesta) {
					log.info("Actualizar saldo para " + responseBalanceDepartmentDTO);
					
					// Se Actualiza posteriormente el Monto del departamento de origen
					ProductDepartmentDTO productDepartmentDTO = new ProductDepartmentDTO();
					productDepartmentDTO.setIdDepartment( responseBalanceDepartmentDTO.getIdDepartment() );
					productDepartmentDTO.setProductCode( responseBalanceDepartmentDTO.getCode() );
					productDepartmentDTO.setDocumentType( ClientConstants.DOCUMENT_TYPE_TICKET );

					log.debug("Procesar: "+productDepartmentDTO);
					
					// Se valida que el producto exista en ese departamento y de no existir se debe crear la relaci�n
					List<ProductDepartmentDTO> listaProd = new ArrayList<ProductDepartmentDTO>();
					try {
						listaProd = getProductsDeparmentsByCriteria(productDepartmentDTO);
					} catch (DepartmentException e1) {
						log.error("Ha ocurrido un error al obtener los datos: " + e1.getMessage());
						throw new ProductDepartmentException("Ha ocurrido un error al obtener los datos: " + e1.getMessage());
					}
					productDepartmentDTO.setRemainingAmount( responseBalanceDepartmentDTO.getBalance() );
					if(listaProd.size()==0){
						log.info("El producto no existe para el departamento, se debe crear. " + productDepartmentDTO);
						saveProductDepartmentRel(productDepartmentDTO);
					} else {		
						try {
							log.info("Actualizar saldo del departamento/producto con la respuesta de Orpak.");
							this.updDepartmentProductAmountSMov(productDepartmentDTO);
						} catch (NuevoMontoException e) {
							log.error("(BBR) Error al actualizar el departamento con el Monto de orpak"+ e.getMessage());
							throw new ProductDepartmentException("(BBR) Error al actualizar el departamento con el Monto de orpak"+ e.getMessage());
						}
					}
					
				}	
				
			}			
			
			log.info("Obtener saldos para id orpak cliente " + depto.getCodeOrpakClient());
			if( depto.getCodeOrpakClient() != null ) {
				ClientDTO cliente;
				try {
					cliente = this.getClientById(depto.getIdClient());
				
					RequestBalanceDepartmentDTO requestBalanceDepartmentDTO = new RequestBalanceDepartmentDTO();
					requestBalanceDepartmentDTO.setIdOrpakDepartment( depto.getCodeOrpakClient() );
					requestBalanceDepartmentDTO.setIdClient( depto.getIdClient() );
					requestBalanceDepartmentDTO.setIdDepartment(idDepartment);
					if (ClientConstants.CLIENT_SCS.equals(cliente.getClientType())){
						requestBalanceDepartmentDTO.setTypeBalance(OrpakWSConstants.ORPAK_WS_DEPARTMENT_BALANCE_QUANTITY);
					}else{
						requestBalanceDepartmentDTO.setTypeBalance(OrpakWSConstants.ORPAK_WS_DEPARTMENT_BALANCE_AMOUNT);
					}
					
					log.info("Recuperar saldos del departamento " + requestBalanceDepartmentDTO);
					List<ResponseBalanceDepartmentDTO> respuesta = new ArrayList<ResponseBalanceDepartmentDTO>();
					try {
						respuesta = this.getDepartmentBalance(requestBalanceDepartmentDTO);
					} catch (OrpakWSExceptions e2) {
						log.warn("Ha ocurrido un error al obtener los datos pero continua el proceso: " + e2.getMessage());
					}
					for (ResponseBalanceDepartmentDTO responseBalanceDepartmentDTO : respuesta) {
						log.info("Actualizar saldo para " + responseBalanceDepartmentDTO);
						
						// Se Actualiza posteriormente el Monto del departamento de origen
						ProductDepartmentDTO productDepartmentDTO = new ProductDepartmentDTO();
						productDepartmentDTO.setIdDepartment( responseBalanceDepartmentDTO.getIdDepartment() );
						productDepartmentDTO.setProductCode( responseBalanceDepartmentDTO.getCode() );
						productDepartmentDTO.setDocumentType( ClientConstants.DOCUMENT_TYPE_INVOICE );
	
						log.debug("Procesar: "+productDepartmentDTO);
						
						// Se valida que el producto exista en ese departamento y de no existir se debe crear la relaci�n
						List<ProductDepartmentDTO> listaProd = new ArrayList<ProductDepartmentDTO>();
						try {
							listaProd = getProductsDeparmentsByCriteria(productDepartmentDTO);
						} catch (DepartmentException e1) {
							log.error("Ha ocurrido un error al obtener los datos: " + e1.getMessage());
							throw new ProductDepartmentException("Ha ocurrido un error al obtener los datos: " + e1.getMessage());
						}
						productDepartmentDTO.setRemainingAmount( responseBalanceDepartmentDTO.getBalance() );
						if(listaProd.size()==0){
							log.info("El producto no existe para el departamento, se debe crear. " + productDepartmentDTO);
							this.saveProductDepartmentRel(productDepartmentDTO);
						} else {		
							try {
								log.info("Actualizar saldo del departamento/producto con la respuesta de Orpak.");
								this.updDepartmentProductAmountSMov(productDepartmentDTO);
							} catch (NuevoMontoException e) {
								log.error("(BBR) Error al actualizar el departamento con el Monto de orpak"+ e.getMessage());
								throw new ProductDepartmentException("(BBR) Error al actualizar el departamento con el Monto de orpak"+ e.getMessage());
							}
						}
						
					}	
				} catch (ClientException e3) {
					log.error("Problemas al obtener saldo departamento desde orpak. " + e3.getMessage());
					throw new DepartmentException( "Ha ocurrido un problema al actualizar los saldos del departamento." );
				}
				
			}	

		} catch ( ClientNotFoundException | ProductDepartmentException e) {
			log.error("Problemas al obtener saldo departamento desde orpak. " + e.getMessage());
			throw new DepartmentException( "Ha ocurrido un problema al actualizar los saldos del departamento." );
		}
		
	}

	@Override
	public void updDepartmentProductAmount(ProductDepartmentDTO criterio) {
		this.updDepartmentProductAmountSMov(criterio);
		
	}

	@Override
	public void addProductDepartmentRel(ProductDepartmentDTO criterio) {
		this.saveProductDepartmentRel(criterio);
		
	}

	@Override
	public VehicleDTO getVehicleDeviceById(Long idVehicle, String posicion) throws VehicleException {
		if(idVehicle == null){
			log.error("Parametro de entrada id de vehiculo es nulo");
			throw new IllegalArgumentException("Parametro de entrada id de vehiculo es nulo");
		}
		
		VehicleDTO vehicle = this.getVehicleById(idVehicle);
		
		vehicle.setCard(this.getDeviceByVehicle(idVehicle, posicion));
		
		return vehicle;
	}

	@Override
	public boolean existOTHeader(CriterioOtHeaderDTO criterio) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<OtHeaderDTO> getListOtHeaderByCriterio(CriterioOtHeaderDTO criterio) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OtItemDTO> getDetalleByidOT(Long idOt, String data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long addItemOT(OtItemDTO nuevoDetalle) throws OTException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long addOT(OtHeaderDTO nuevaOT) throws OTException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CardDTO getCardById(Long idElement) throws CardException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VehicleDTO getVehicleByIdCard(Long idCard) throws VehicleException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long updCardBalance(Long idCard, String cardnum) throws CardException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean stationClientsExist(StationClientsCriterioDTO criterioSC) throws ClientServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<StationsDTO> getStationClientsByCriterio(StationClientsCriterioDTO criterio)
			throws ClientServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isEESSPrivadas(Long idClient) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<String> getListStationConstraintByCard(Long idCard) {
		
		log.info("Consultar listado de estaciones para la restricción del departamento {}", idCard);

	    List<StationCardConstraint> constraints = stationCardConstraintRepo.findByCardIdCard(idCard);
	    
	    List<String> retorno = constraints.stream()
	        .map(StationCardConstraint::getStationCode)
	        .toList();

	    log.debug("Listado de estaciones con restricción departamento {}: {}", idCard, retorno);

	    return retorno;
	}

	@Override
	public BigDecimal getLitroPrecio(String productCode, BigDecimal amount) throws PriceLiterNotFoundException {
		
		log.info("convertir litros a precio " + productCode + " cantidad " + amount);
		
		List<PriceLiters> lista = this.priceLiterJPA.findByProductCode(productCode);
		if( lista.size() > 0 ) {
			
			PriceLiters preciosLitrosDTO = lista.get(0);
			
			log.info("convertir precio " + productCode + " cantidad " + amount + " precio " + preciosLitrosDTO.getPrice());
			
			BigDecimal result = amount.multiply(preciosLitrosDTO.getPrice());
			
			log.info("resultado conversi�n : " + result);
			
			return result;
		} else {
			log.warn("No existe conversi�n");
			throw new PriceLiterNotFoundException("No existe conversi�n.");
		}
	}

	@Override
	public List<String> getListStationConstraintByDept(Long idDepartment) {
	    log.info("Consultar listado de estaciones para la restricción del departamento {}", idDepartment);

	    List<StationDepartmentConstraints> constraints = stationDepartmentConstraintsRepo.getByIdDepartment(idDepartment);
	    
	    List<String> retorno = constraints.stream()
	        .map(StationDepartmentConstraints::getStationCode)
	        .toList();

	    log.debug("Listado de estaciones con restricción departamento {}: {}", idDepartment, retorno);

	    return retorno;
	}

	@Override
	public void updDeparmentsProcessSendClient(DepartmentDTO departamentDto) {
		if(departamentDto==null){
			log.error("Parametro de entrada objeto departamento es nulo");
			throw new IllegalArgumentException("Parametro de entrada objeto departamento es nulo");
		}
		
		Optional<Department> optionalDepartamento = departmentRepository.findById(departamentDto.getIdDepartment());

	    if (!optionalDepartamento.isPresent()) {
	        log.error("Departamento con ID " + departamentDto.getIdDepartment() + " no encontrado");
	        throw new EntityNotFoundException("Departamento con ID " + departamentDto.getIdDepartment() + " no encontrado");
	    }

	    Department departamento = optionalDepartamento.get();

	    // Actualiza solo los campos no nulos
	    if (departamentDto.getIdClient() != null) {
	        departamento.setIdClient(departamentDto.getIdClient());
	    }
	    if (departamentDto.getCodeOrpakTicket() != null) {
	        departamento.setCodeOrpakTicket(departamentDto.getCodeOrpakTicket());
	    }
	    if (departamentDto.getCodeOrpakInvoice() != null) {
	        departamento.setCodeOrpakInvoice(departamentDto.getCodeOrpakInvoice());
	    }
	    if (departamentDto.getCodeOrpakClient() != null) {
	        departamento.setCodeOrpakClient(departamentDto.getCodeOrpakClient());
	    }

	    departmentRepository.save(departamento);
	    log.info("Departamento con ID " + departamento.getIdDepartment() + " actualizado correctamente.");
		
	}

	@Override
	public void updClientByIDProcessSendClient(RegisterClientDTO registerClientdto) {
		
		if (registerClientdto == null) {
	        log.error("Parámetro de entrada objeto cliente es nulo");
	        throw new IllegalArgumentException("Parámetro de entrada objeto cliente es nulo");
	    }

	    Optional<Client> optionalClient = clientRepository.findById(registerClientdto.getIdClient());

	    if (!optionalClient.isPresent()) {
	        log.error("Cliente con ID " + registerClientdto.getIdClient() + " no encontrado");
	        throw new EntityNotFoundException("Cliente con ID " + registerClientdto.getIdClient() + " no encontrado");
	    }

	    Client client = optionalClient.get();

	    // Actualiza solo campos no nulos
	    if (registerClientdto.getCodeOrpakClient() != null) {
	        client.setCodeOrpakClient(registerClientdto.getCodeOrpakClient());
	    }
	    if (registerClientdto.getCodeOrpakTicket() != null) {
	        client.setCodeOrpakTicket(registerClientdto.getCodeOrpakTicket());
	    }
	    if (registerClientdto.getCodeOrpakInvoice() != null) {
	        client.setCodeOrpakInvoice(registerClientdto.getCodeOrpakInvoice());
	    }
	    if (registerClientdto.getClientStatus() != null) {
	        client.setClientStatus(registerClientdto.getClientStatus());
	    }

	    clientRepository.save(client);
	    log.info("Cliente con ID " + client.getIdClient() + " actualizado correctamente.");
		
	}

	@Override
	public void updQuantityCard(Long idCard, String cardnum) throws CardException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public VehicleDTO getVehicleById(Long idVehicle) throws VehicleException {
		
		if (idVehicle == null) {
            log.error("Parametro de entrada id de vehiculo es nulo");
            throw new IllegalArgumentException("Parametro de entrada id de vehiculo es nulo");
        }

        Vehicles record = vehiclesRepo.findById(idVehicle)
            .orElseThrow(() -> {
                log.error("El vehiculo {} no se encuentra.", idVehicle);
                return new VehicleException("El Vehiculo " + idVehicle + " no se encuentra.");
            });

        VehicleDTO dto = new VehicleDTO();
        dto.setIdClient(record.getClient().getIdClient());
        dto.setIdDepartment(record.getDepartment().getIdDepartment());
        dto.setIdVehicle(record.getIdVehicle());
        dto.setDocumentType(record.getDocumentType());
        dto.setPlate(record.getPlate());
        dto.setProductCode(record.getProduct().getProductCode());
        dto.setVehicleStatus(record.getVehicleStatus());
        dto.setVehicleTypeCode(record.getVehicleType().getVehicleTypeCode());
        dto.setNickname(record.getNickname());
        dto.setWarningLoadChannel(record.getWarningLoadChannel());
        dto.setWarningLoadCelular(record.getWarningLoadCelular());
        dto.setWarningLoadMail(record.getWarningLoadEmail());
        dto.setWarningLoadFailChannel(record.getWarningLoadFailChannel());
        dto.setWarningLoadFailCelular(record.getWarningLoadFailCelular());
        dto.setWarningLoadFailEmail(record.getWarningLoadFailEmail());
        dto.setWarningStock(record.getWarningStock());
        dto.setWarningStockChannel(record.getWarningStockChannel());
        dto.setWarningStockCelular(record.getWarningStockCelular());
        dto.setWarningStockMail(record.getWarningStockEmail());
        dto.setControlTotal(record.getControlTotal() != null ? record.getControlTotal() != 0 : null);
        dto.setDatapass(record.getDatapass() != null ? record.getDatapass() != 0 : null);
        dto.setDatachip(record.getDatachip());
        dto.setGps(record.getGps());

     // Descripciones si existen
        dto.setNombreDepto(record.getDepartment() != null ? record.getDepartment().getName() : null);
        dto.setNombreProducto(record.getProduct() != null ? record.getProduct().getName() : null);
        dto.setTipoVehiculo(record.getVehicleType() != null ? record.getVehicleType().getName() : null);

        // Tarjetas
        List<Cards> cards = cardsRepository.findCardsByVehicle(idVehicle);
        
        List<CardDTO> cardList = cards.stream().map(c -> {
            CardDTO cardDto = new CardDTO();
            cardDto.setIdCard(c.getIdCard());
            cardDto.setIdClient(c.getIdClient() != null ? c.getIdClient().longValue() : null);
            cardDto.setCardnum(c.getCardNum());
            cardDto.setRemainingAmount(c.getRemainingAmount());
            cardDto.setRemainingTrxLoad(c.getRemainingTrxLoad() != null ? c.getRemainingTrxLoad().longValue() : null);
            cardDto.setRemainingPeriodAmount(c.getRemainingPeriodAmount());
            cardDto.setCrtdate(c.getCrtDate());
            cardDto.setUpddate(c.getUpdDate());
            cardDto.setCardStatus(c.getCardStatus());
            cardDto.setRestrType(c.getRestrType());
            cardDto.setRestrL(c.getRestrL() != null && c.getRestrL() != 0);
            cardDto.setRestrM(c.getRestrM() != null && c.getRestrM() != 0);
            cardDto.setRestrX(c.getRestrX() != null && c.getRestrX() != 0);
            cardDto.setRestrJ(c.getRestrJ() != null && c.getRestrJ() != 0);
            cardDto.setRestrV(c.getRestrV() != null && c.getRestrV() != 0);
            cardDto.setRestrS(c.getRestrS() != null && c.getRestrS() != 0);
            cardDto.setRestrD(c.getRestrD() != null && c.getRestrD() != 0);
            cardDto.setRestrHini(c.getRestrHini() != null ? c.getRestrHini().longValue() : null);
            cardDto.setRestrHend(c.getRestrHend() != null ? c.getRestrHend().longValue() : null);
            cardDto.setRestrAmountMax(c.getRestrAmountMax());
            cardDto.setRestrDailyMaxLoads(c.getRestrDailyMaxLoads() != null ? c.getRestrDailyMaxLoads().longValue() : null);
            cardDto.setRestrDailyMaxQuantity(c.getRestrDailyMaxQuantity() != null ? c.getRestrDailyMaxQuantity().longValue() : null);
            cardDto.setRestrictionType(c.getRestrictionType());
            cardDto.setVersion(c.getVersion());
            cardDto.setReqcardStatus(c.getReqCardStatus());
            cardDto.setReqCardReprint(c.getReqCardReprint() != null && c.getReqCardReprint() != 0);
            cardDto.setCtPosition(c.getCtPosition());
            return cardDto;
        }).toList();
        
        
        dto.setCard(cardList);

        log.info("Vehiculo obtenido: {}", dto.getIdVehicle());
        return dto;
	}

	@Override
	public Optional<List<ClientDTO>> findByFactDepartment(Integer factDepartment) throws ClientServiceException{
		
		// Recuperar lista de entidades desde el repositorio
	    return clientRepository.findByFactDepartmentOld(factDepartment)
	                     .filter(clients -> !clients.isEmpty()) // Filtrar listas vacías
	                     .map(clients -> {
	                         // Inicializa relaciones Lazy
	                         clients.forEach(client -> {
	                             Hibernate.initialize(client.getAddressDisArea());
	                             Hibernate.initialize(client.getAddressDisCity());
	                             Hibernate.initialize(client.getAddressDisRegion());
	                             if (client.getAddressDisRegion() != null) {
	                                 Hibernate.initialize(client.getAddressDisRegion().getZone());
	                             }
	                         });

	                         // Convertir a DTO
	                         return clients.stream()
	                                       .map(clientMapper::toDto)
	                                       .collect(Collectors.toList());
	                     })
	                     .or(() -> {
	                         throw new ClientServiceException("Client not found by factDepartment " + factDepartment);
	                     });
		
	}

	@Override
	public List<DepartmentDTO> findByIdClient(Long idClient) {
		List<Department> departments = departmentRepository.findByIdClient(idClient);

        // Mapea la lista de entidades a DTOs
        return departments.stream()
                .map(departmentMapper::toDto)
                .collect(Collectors.toList());
	}



	@Override
	public List<MonitorCardDTO> getListCardsByCriterio(CriterioBusquedaCardDTO criterio) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	@Override
	public List<ResponseBalanceDepartmentDTO> getDepartmentBalance(
			RequestBalanceDepartmentDTO requestBalanceDepartmentDTO) {
		if(requestBalanceDepartmentDTO==null){
			log.error("(BBR) El parametro de entrada requestBalanceDepartmentDTO se encuentra nulo");
			throw new IllegalArgumentException("(BBR) El parametro de entrada requestBalanceDepartmentDTO se encuentra nulo");
		}

		log.debug("Consultar saldo del departamento en Orpak " + requestBalanceDepartmentDTO);

		List<ResponseBalanceDepartmentDTO> lista = new ArrayList<ResponseBalanceDepartmentDTO>();
		
        try {

        	log.info("Recuperar saldos desde orpak para el departamento cod orpak " + requestBalanceDepartmentDTO.getIdOrpakDepartment());
        	
        	GetDepartmentBalanceRequest req = new GetDepartmentBalanceRequest();
        	req.setDepartmentid(requestBalanceDepartmentDTO.getIdOrpakDepartment());
        	req.setBalanceType(requestBalanceDepartmentDTO.getTypeBalance());
        	
			List<GetDepartmentBalanceResponse> listado = orpakAdapter.getDepartmentBalance(req);
			for (GetDepartmentBalanceResponse getDepartmentBalanceResponse : listado) {
				log.info("Return Code: " + getDepartmentBalanceResponse.getReturnCode()+ " " + getDepartmentBalanceResponse.getMessage() + " " + getDepartmentBalanceResponse.getUri());

				// lanzamos excepcion si se retorna otro valor distinto a OK
				if(getDepartmentBalanceResponse.getReturnCode()!= OrpakWSConstants.ORPAK_WS_GET_DEPARTMENT_BALANCE_STATUS_OK){
					log.error("Ha ocurrido un error al recuperar el saldo del departamento. " + getDepartmentBalanceResponse.getReturnCode());
					throw new OrpakWSExceptions(getDepartmentBalanceResponse.getMessage());
				}
				
				ResponseBalanceDepartmentDTO response = new ResponseBalanceDepartmentDTO();
				response.setIdDepartment(requestBalanceDepartmentDTO.getIdDepartment());
				response.setIdOrpakDepartment(requestBalanceDepartmentDTO.getIdOrpakDepartment());
				response.setBalance( BigDecimal.valueOf(getDepartmentBalanceResponse.getBalance()) );
				response.setBalanceType( getDepartmentBalanceResponse.getBalancetype() ); // 0 precio 1 cantidad
				response.setCode( getDepartmentBalanceResponse.getCode() );
				response.setDescription( getDepartmentBalanceResponse.getDescription() );
				response.setStockType( getDepartmentBalanceResponse.getStockType() ); // 0 Stoklar.stokident 1 Mg_StklistEvr.LstEvrId ????
				
				log.debug("Datos a retornar: "+response);
				
				lista.add(response);
				
			}

			
		} catch (OrpakException e) {
			log.error("Ha ocurrido un error. " + e.getMessage());
			throw new OrpakWSExceptions(e);
		}

		return lista;
	}

	@Override
	public Boolean clienteTieneEstacionesAsignadas(Long idClient) throws ClientServiceException {
		Boolean tiene = null;
		StationsRequestDTO criterio = new StationsRequestDTO();
		criterio.setIdClient(idClient);
		List<StationsDTO> encontrados = this.stationClientsRepoExtend.getStationClientsTerritoryByCriterio(criterio);
		tiene = (encontrados != null && encontrados.size() > 0) ? true : false;
		return tiene;
	}



	@Override
	public List<CardDTO> getDeviceByVehicle(Long idVehicle, String posicion) {
		List<CardDTO> listadoDevice = new ArrayList<CardDTO>();
		
		listadoDevice = this.cardsRepository.getDeviceByVehicle(idVehicle, posicion);
		
		return listadoDevice;
	}



	@Override
	public List<ClientDTO> getClientPendingOrpak() {
		List<Client> listado = clientRepository.findByClientStatus(ClientConstants.CLIENT_STATUS_PENDING);
		
		List<ClientDTO> listClientes = listado.stream()
									        .map(clientMapper::toDto)
									        .collect(Collectors.toList());

	    return listClientes;
	}
	


}
