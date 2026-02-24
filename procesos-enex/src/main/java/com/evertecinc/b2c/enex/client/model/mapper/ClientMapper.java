package com.evertecinc.b2c.enex.client.model.mapper;

import java.util.Collections;

import org.springframework.stereotype.Component;

import com.evertecinc.b2c.enex.client.model.dto.ClientDTO;
import com.evertecinc.b2c.enex.client.model.dto.RegisterClientDTO;
import com.evertecinc.b2c.enex.client.model.entities.Client;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ClientMapper {

    private final AreaMapper areaMapper;
    private final CityMapper cityMapper;
    private final RegionMapper regionMapper;
//    private final EjecutivoMapper ejecutivoMapper;
//    private final JefeZonaMapper jefeZonaMapper;

    public ClientDTO toDto(Client cliente) {
        if (cliente == null) {
            return null;
        }

//        ClientDTO clientDTO = new ClientDTO();
        
        ClientDTO dto = new ClientDTO();
		dto.setIdClient(cliente.getIdClient());
		dto.setClientStatus(cliente.getClientStatus());
		dto.setClientType(cliente.getClientType());
		dto.setContactEmail(cliente.getContactEmail());
		dto.setContactName(cliente.getContactName());
		dto.setContactPhone(cliente.getContactPhone());
		dto.setCommercialType(cliente.getCommercialType());
		dto.setDateins(cliente.getDateIns());
		dto.setDateupd(cliente.getDateUpd());
		dto.setLegalName(cliente.getLegalName());
		dto.setName(cliente.getName());
		dto.setUpi(cliente.getUpi());
		dto.setAccountJdeClient(cliente.getAccountJdeClient());
		dto.setAccountJdeInvoice(cliente.getAccountJdeInvoice());
		dto.setAccountJdeTicket(cliente.getAccountJdeTicket());
		dto.setAddressDisDoorNumber(cliente.getAddressDoornumber());
		dto.setAddressDisStreetName(cliente.getAddressDisStreetname());
		dto.setAddressDisStreetNumber(cliente.getAddressDisStreetnumber());
		dto.setAddressDoorNumber(cliente.getAddressDoornumber());
		dto.setAddressIdArea(cliente.getAddressIdArea());
		dto.setAddressStreetName(cliente.getAddressStreetname());
		dto.setAddressStreetNumber(cliente.getAddressStreetnumber());
		dto.setAddressDisIdArea(cliente.getAddressDisIdArea());
		dto.setAddressDisIdCity(cliente.getAddressDisIdCity());
		dto.setAddressDisIdRegion(cliente.getAddressDisIdRegion());
		dto.setAddressIdCity1(cliente.getAddressIdCity1());
		dto.setAddressIdRegion1(cliente.getAddressIdRegion1());
		dto.setIsCredit(cliente.getIsCredit());
		dto.setCreditLimit(cliente.getCreditLimit());
		dto.setLocked(cliente.getLocked());
		dto.setRemainingAmount(cliente.getRemainingAmount());
		dto.setWarningLockedChannel(cliente.getWarningLockedChannel());
		dto.setWarningLockedEmail(cliente.getWarningLockedEmail());
		dto.setWarningStock(cliente.getWarningStock());
		dto.setWarningStockCelular(cliente.getWarningStockCelular());
		dto.setWarningStockChannel(cliente.getWarningStockChannel());
		dto.setWarningStockEmail(cliente.getWarningStockEmail());
//		dto.setIdEjecutivo(cliente.getIdEjecutivo());
//		dto.setIdJefeZona(cliente.getIdJefeZona());

//        // Campos directamente mapeados desde la entidad
//        clientDTO.setIdClient(client.getIdClient());
//        clientDTO.setName(client.getName() != null ? client.getName() : "");
//        clientDTO.setUpi(client.getUpi() != null ? client.getUpi() : "");
//        clientDTO.setLegalName(client.getLegalName() != null ? client.getLegalName() : "");
//        clientDTO.setCommercialType(client.getCommercialType() != null ? client.getCommercialType() : "");
//        clientDTO.setContactName(client.getContactName() != null ? client.getContactName() : "");
//        clientDTO.setContactPhone(client.getContactPhone() != null ? client.getContactPhone() : "");
//        clientDTO.setContactEmail(client.getContactEmail() != null ? client.getContactEmail() : "");
//        clientDTO.setContactJob(client.getContactJob() != null ? client.getContactJob() : "");
//        clientDTO.setAccountJdeInvoice(client.getAccountJdeInvoice() );
//        clientDTO.setAccountJdeTicket(client.getAccountJdeTicket() );
//        clientDTO.setAccountJdeClient(client.getAccountJdeClient() );
//        clientDTO.setClientStatus(client.getClientStatus() != null ? client.getClientStatus() : "");
//        clientDTO.setClientType(client.getClientType() != null ? client.getClientType() : "");
//        clientDTO.setDateins(client.getDateIns());
//        clientDTO.setDateupd(client.getDateUpd());
//        clientDTO.setAddressStreetName(client.getAddressStreetname() != null ? client.getAddressStreetname() : "");
//        clientDTO.setAddressStreetNumber(client.getAddressStreetnumber() != null ? client.getAddressStreetnumber() : "");
//        clientDTO.setAddressDoorNumber(client.getAddressDoornumber() != null ? client.getAddressDoornumber() : "");
//
//        // Campo `creditLimit` asignado directamente
//        clientDTO.setCreditLimit(client.getCreditLimit());
//
//        // Campo `remainingAmount` asignado directamente
//        clientDTO.setRemainingAmount(client.getRemainingAmount());
//
//        // Campo `warningStock` asignado directamente
//        clientDTO.setWarningStock(client.getWarningStock());
//
//        // Campo `storeListOrPak` asignado directamente
//        clientDTO.setStorelistorpak(client.getStoreListOrPak() != null ? client.getStoreListOrPak() : "");
//
        // Campos de relaciones
		dto.setAreaDTO(areaMapper.toDto(cliente.getAddressDisArea()));
		dto.setCityDTO(cityMapper.toDto(cliente.getAddressDisCity()));
		dto.setRegionDTO(regionMapper.toDto(cliente.getAddressDisRegion()));

        // Campos adicionales que faltan en la entidad y requieren métodos o lógica adicional para obtener el valor.
        // Añadir métodos en `Client` para estos campos si son necesarios.

        // Campos relacionados con "codeorpakclient", "codeorpakinvoice", y "codeorpakticket".
        // Agregar métodos getCodeOrpakClient(), getCodeOrpakInvoice(), getCodeOrpakTicket() en `Client` o lógica alternativa.
		dto.setCodeOrpakClient(cliente.getCodeOrpakClient());
		dto.setCodeOrpakInvoice(cliente.getCodeOrpakInvoice());
		dto.setCodeOrpakTicket(cliente.getCodeOrpakTicket());

        // Campos relacionados con dirección adicional y otros datos legales, si son necesarios en el DTO:
        // Se debe crear métodos o lógica específica en `Client` para obtener esta información si fuera relevante.

        // Valores predeterminados de `String` y `Collections.emptyList()` en caso de listas no mapeadas actualmente.
		dto.setListDepartments(Collections.emptyList()); // Relación no definida en `Client`.
		dto.setListVehicles(Collections.emptyList());    // Relación no definida en `Client`.
		dto.setVehiculoReqCompleto(Collections.emptyList()); // Relación no definida en `Client`.

        // Retorno final del DTO construido
        return dto;
    }




    // Método para mapear de ClientDTO a Client
    public Client toEntity(ClientDTO clientDTO) {
        if (clientDTO == null) {
            return null;
        }

        Client client = new Client();
        client.setIdClient(clientDTO.getIdClient());
        client.setName(clientDTO.getName() != null ? clientDTO.getName() : "");
        client.setUpi(clientDTO.getUpi() != null ? clientDTO.getUpi() : "");
        client.setLegalName(clientDTO.getLegalName() != null ? clientDTO.getLegalName() : "");
        client.setCommercialType(clientDTO.getCommercialType() != null ? clientDTO.getCommercialType() : "");
        client.setContactName(clientDTO.getContactName() != null ? clientDTO.getContactName() : "");
        client.setContactPhone(clientDTO.getContactPhone() != null ? clientDTO.getContactPhone() : "");
        client.setContactEmail(clientDTO.getContactEmail() != null ? clientDTO.getContactEmail() : "");
        client.setContactJob(clientDTO.getContactJob() != null ? clientDTO.getContactJob() : "");
        client.setAccountJdeInvoice(clientDTO.getAccountJdeInvoice() != null ? clientDTO.getAccountJdeInvoice() : "");
        client.setAccountJdeTicket(clientDTO.getAccountJdeTicket() != null ? clientDTO.getAccountJdeTicket() : "");
        client.setAccountJdeClient(clientDTO.getAccountJdeClient() != null ? clientDTO.getAccountJdeClient() : "");
//        client.setCodeorpakclient(clientDTO.getCodeOrpakClient() != null ? clientDTO.getCodeOrpakClient() : "");
//        client.setCodeorpakinvoice(clientDTO.getCodeOrpakInvoice() != null ? clientDTO.getCodeOrpakInvoice() : "");
//        client.setCodeorpakticket(clientDTO.getCodeOrpakTicket() != null ? clientDTO.getCodeOrpakTicket() : "");
        client.setClientStatus(clientDTO.getClientStatus() != null ? clientDTO.getClientStatus() : "");
        client.setClientType(clientDTO.getClientType() != null ? clientDTO.getClientType() : "");
        client.setDateIns(clientDTO.getDateins());
        client.setDateUpd(clientDTO.getDateupd());
        client.setAddressStreetname(clientDTO.getAddressStreetName() != null ? clientDTO.getAddressStreetName() : "");
        client.setAddressStreetnumber(clientDTO.getAddressStreetNumber() != null ? clientDTO.getAddressStreetNumber() : "");
        client.setAddressDoornumber(clientDTO.getAddressDoorNumber() != null ? clientDTO.getAddressDoorNumber() : "");
        client.setRemainingAmount(clientDTO.getRemainingAmount());
        client.setCreditLimit(clientDTO.getCreditLimit());
        client.setStoreListOrPak(clientDTO.getStorelistorpak() != null ? clientDTO.getStorelistorpak() : "");
        return client;
    }
    
    
    /**
     * Convierte la entidad Client a RegisterClientDTO.
     */
    public static RegisterClientDTO entityToDto(Client entity) {
        if (entity == null) {
            return null;
        }

        RegisterClientDTO dto = new RegisterClientDTO();

        // IDs e información básica
        dto.setIdClient(entity.getIdClient());
        dto.setName(entity.getName());
        dto.setUpi(entity.getUpi());
        dto.setLegalName(entity.getLegalName());
        dto.setCommercialType(entity.getCommercialType());
        dto.setContactName(entity.getContactName());
        dto.setContactPhone(entity.getContactPhone());
        dto.setContactEmail(entity.getContactEmail());
        
        // JDE, estatus, etc.
        dto.setAccountJdeClient(entity.getAccountJdeClient());
        dto.setAccountJdeInvoice(entity.getAccountJdeInvoice());
        dto.setAccountJdeTicket(entity.getAccountJdeTicket());
        dto.setClientStatus(entity.getClientStatus());
        dto.setClientType(entity.getClientType());
        
        // Fechas
        dto.setDateins(entity.getDateIns());
        dto.setDateupd(entity.getDateUpd());
        
        // Direcciones
        dto.setAddressStreetName(entity.getAddressStreetname());
        dto.setAddressStreetNumber(entity.getAddressStreetnumber());
        dto.setAddressDoorNumber(entity.getAddressDoornumber());
        dto.setAddressDisStreetName(entity.getAddressDisStreetname());
        dto.setAddressDisStreetNumber(entity.getAddressDisStreetnumber());
        dto.setAddressDisDoorNumber(entity.getAddressDisDoornumber());
        
        dto.setAddressIdArea(entity.getAddressIdArea());
        dto.setAddressIdRegion1(entity.getAddressIdRegion1());
        dto.setAddressIdCity1(entity.getAddressIdCity1());
        
        dto.setAddressDisIdArea(entity.getAddressDisIdArea());
        dto.setAddressDisIdRegion(entity.getAddressDisIdRegion());
        dto.setAddressDisIdCity(entity.getAddressDisIdCity());
        
        // Códigos Orpak
        dto.setCodeOrpakClient(entity.getCodeOrpakClient());
        dto.setCodeOrpakInvoice(entity.getCodeOrpakInvoice());
        dto.setCodeOrpakTicket(entity.getCodeOrpakTicket());
        
        // Información financiera
        dto.setCreditLimit(entity.getCreditLimit());
        dto.setRemainingAmount(entity.getRemainingAmount());
        // amountDue (entity) no está en DTO -> se omite
        
        dto.setLocked(entity.getLocked());
        dto.setIsCredit(entity.getIsCredit());
        
        dto.setWarningStock(entity.getWarningStock());
        dto.setWarningStockChannel(entity.getWarningStockChannel());
        dto.setWarningStockCelular(entity.getWarningStockCelular());
        dto.setWarningStockEmail(entity.getWarningStockEmail());
        
        dto.setWarningLockedChannel(entity.getWarningLockedChannel());
        dto.setWarningLockedEmail(entity.getWarningLockedEmail());
        
        // factDepartment (Integer → Boolean)
        if (entity.getFactDepartment() != 0) {
            dto.setFactDepartment(true);
        } else {
            dto.setFactDepartment(false);
        }
        
        // factDepartmentDate (LocalDateTime → LocalDate)
        if (entity.getFactDepartmentDate() != null) {
            dto.setFactDepartmentDate(entity.getFactDepartmentDate().toLocalDate());
        }
        
        // storeListOrPak
        dto.setStorelistorpak(entity.getStoreListOrPak());
        
        // tipoReglamento
        dto.setTipoReglamento(entity.getTipoReglamento());
        
        // GPS
        dto.setGps(entity.getGps());
        
        // Manejo de adblue / adBlue -> Boolean
        // Convención: >0 = true, de lo contrario false
        boolean isAdblue = (entity.getAdblue() != null && entity.getAdblue() > 0);
        dto.setAdblue(isAdblue);
        
        // En el DTO hay más campos no mapeados (antifraude, etc.) que no existen en la entidad.
        
        // Retornamos el DTO
        return dto;
    }

    /**
     * Convierte un RegisterClientDTO a la entidad Client.
     */
    public static Client toEntity(RegisterClientDTO dto) {
        if (dto == null) {
            return null;
        }

        Client entity = new Client();

        // IDs e información básica
        entity.setIdClient(dto.getIdClient());
        entity.setName(dto.getName());
        entity.setUpi(dto.getUpi());
        entity.setLegalName(dto.getLegalName());
        entity.setCommercialType(dto.getCommercialType());
        entity.setContactName(dto.getContactName());
        entity.setContactPhone(dto.getContactPhone());
        entity.setContactEmail(dto.getContactEmail());
        
        // JDE, estatus, etc.
        entity.setAccountJdeClient(dto.getAccountJdeClient());
        entity.setAccountJdeInvoice(dto.getAccountJdeInvoice());
        entity.setAccountJdeTicket(dto.getAccountJdeTicket());
        entity.setClientStatus(dto.getClientStatus());
        entity.setClientType(dto.getClientType());
        
        // Fechas
        entity.setDateIns(dto.getDateins());
        entity.setDateUpd(dto.getDateupd());
        
        // Direcciones
        entity.setAddressStreetname(dto.getAddressStreetName());
        entity.setAddressStreetnumber(dto.getAddressStreetNumber());
        entity.setAddressDoornumber(dto.getAddressDoorNumber());
        entity.setAddressDisStreetname(dto.getAddressDisStreetName());
        entity.setAddressDisStreetnumber(dto.getAddressDisStreetNumber());
        entity.setAddressDisDoornumber(dto.getAddressDisDoorNumber());
        
        entity.setAddressIdArea(dto.getAddressIdArea());
        entity.setAddressIdRegion1(dto.getAddressIdRegion1());
        entity.setAddressIdCity1(dto.getAddressIdCity1());
        
        entity.setAddressDisIdArea(dto.getAddressDisIdArea());
        entity.setAddressDisIdRegion(dto.getAddressDisIdRegion());
        entity.setAddressDisIdCity(dto.getAddressDisIdCity());
        
        // Códigos ORPAK
        entity.setCodeOrpakClient(dto.getCodeOrpakClient());
        entity.setCodeOrpakInvoice(dto.getCodeOrpakInvoice());
        entity.setCodeOrpakTicket(dto.getCodeOrpakTicket());
        
        // Información financiera
        entity.setCreditLimit(dto.getCreditLimit());
        entity.setRemainingAmount(dto.getRemainingAmount());
        
        entity.setLocked(dto.getLocked());
        entity.setIsCredit(dto.getIsCredit());
        
        entity.setWarningStock(dto.getWarningStock());
        entity.setWarningStockChannel(dto.getWarningStockChannel());
        entity.setWarningStockCelular(dto.getWarningStockCelular());
        entity.setWarningStockEmail(dto.getWarningStockEmail());
        
        entity.setWarningLockedChannel(dto.getWarningLockedChannel());
        entity.setWarningLockedEmail(dto.getWarningLockedEmail());

        // factDepartment (Boolean → Integer)
        if (Boolean.TRUE.equals(dto.getFactDepartment())) {
            entity.setFactDepartment(1L);
        } else {
            entity.setFactDepartment(0L);
        }

        // factDepartmentDate (LocalDate → LocalDateTime)
        if (dto.getFactDepartmentDate() != null) {
            entity.setFactDepartmentDate(dto.getFactDepartmentDate().atStartOfDay());
        }
        
        // storeListOrPak
        entity.setStoreListOrPak(dto.getStorelistorpak());
        
        // tipoReglamento
        entity.setTipoReglamento(dto.getTipoReglamento());
        
        // GPS
        entity.setGps(dto.getGps());

        // Manejo de adblue / adBlue -> si es true => 1, false => 0
        if (Boolean.TRUE.equals(dto.getAdblue())) {
            entity.setAdblue(1L);
//            entity.setAdBlue(1);
        } else {
            entity.setAdblue(0L);
//            entity.setAdBlue(0);
        }

        // Se omiten los campos no existentes en la entidad.
        
        return entity;
    }
}
