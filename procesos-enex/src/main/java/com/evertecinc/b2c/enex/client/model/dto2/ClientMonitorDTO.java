package com.evertecinc.b2c.enex.client.model.dto2;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientMonitorDTO extends ClientDTO {
	
	private String direccionComercial;
	private String direccionDespacho;
	private String direccionDespachoCityName;
	private String direccionDespachoAreaName;
	private String direccionDespachoRegionName;
	private String direccionComercialCityName;
	private String direccionComercialAreaName;	
	private String direccionComercialRegionName;
	private Long tarjetasActivas;
	private Long totalTarjetas;
	private Long idReporte;
	private Boolean isPrivate;
	
	/**
	 * @param Constructor para el siguiente Repo:
	 * @param ClientRepoImpl getListClientByCriteria
	 */
	public ClientMonitorDTO(Long idClient, String name, String upi, String legalName, String commercialType,
			String contactName, String contactPhone, String contactEmail, String contactJob, String accountJdeClient,
			String accountJdeInvoice, String accountJdeTicket, String clientStatus, String clientType,
			LocalDateTime dateIns, LocalDateTime dateUpd, String addressStreetName, String addressStreetNumber,
			String addressDoorNumber, String addressDisStreetName, String addressDisStreetNumber,
			String addressDisDoorNumber, Long addressIdArea, Long addressDisIdArea, Long addressDisIdRegion,
			Long addressIdRegion1, Long addressDisIdCity, Long addressIdCity1, String legalUpi, String legalCivilStatus,
			String legalProfession, String legalAddress, String legalPhone, String legalNamePerson, String legalEmail,
			String codeorpakticket, String codeorpakinvoice, String direccionDespachoCityName,
			String direccionDespachoAreaName, String direccionDespachoRegionName, String direccionComercialCityName,
			String direccionComercialAreaName, String direccionComercialRegionName) {
		super(idClient, name, upi, legalName, commercialType, contactName, contactPhone, contactEmail, contactJob,
				accountJdeClient, accountJdeInvoice, accountJdeTicket, clientStatus, clientType, dateIns, dateUpd,
				addressStreetName, addressStreetNumber, addressDoorNumber, addressDisStreetName, addressDisStreetNumber,
				addressDisDoorNumber, addressIdArea, addressDisIdArea, addressDisIdRegion, addressIdRegion1,
				addressDisIdCity, addressIdCity1, legalUpi, legalCivilStatus, legalProfession, legalAddress, legalPhone,
				legalNamePerson, legalEmail, codeorpakticket, codeorpakinvoice);
		this.direccionDespachoCityName = direccionDespachoCityName;
		this.direccionDespachoAreaName = direccionDespachoAreaName;
		this.direccionDespachoRegionName = direccionDespachoRegionName;
		this.direccionComercialCityName = direccionComercialCityName;
		this.direccionComercialAreaName = direccionComercialAreaName;
		this.direccionComercialRegionName = direccionComercialRegionName;
	}
	
	
	
	
	
	
	

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ClientMonitorDTO [direccionComercial=");
		builder.append(direccionComercial);
		builder.append(", direccionDespacho=");
		builder.append(direccionDespacho);
		builder.append(", direccionDespachoCityName=");
		builder.append(direccionDespachoCityName);
		builder.append(", direccionDespachoAreaName=");
		builder.append(direccionDespachoAreaName);
		builder.append(", direccionDespachoRegionName=");
		builder.append(direccionDespachoRegionName);
		builder.append(", direccionComercialCityName=");
		builder.append(direccionComercialCityName);
		builder.append(", direccionComercialAreaName=");
		builder.append(direccionComercialAreaName);
		builder.append(", direccionComercialRegionName=");
		builder.append(direccionComercialRegionName);
		builder.append(", toStringClienteDTO()=");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}








	/**
	 * @implNote getContraloriaClientsByCriterio
	 * 
	 * @param name
	 * @param upi
	 * @param legalName
	 * @param commercialType
	 * @param contactName
	 * @param contactPhone
	 * @param contactEmail
	 * @param contactJob
	 * @param accountJdeClient
	 * @param accountJdeInvoice
	 * @param accountJdeTicket
	 * @param clientStatus
	 * @param clientType
	 * @param dateIns
	 * @param dateUpd
	 * @param addressStreetName
	 * @param addressStreetNumber
	 * @param addressDisStreetName
	 * @param legalUpi
	 * @param legalCivilStatus
	 * @param legalProfession
	 * @param legalAddress
	 * @param legalPhone
	 * @param legalNamePerson
	 * @param idClient
	 */
	public ClientMonitorDTO(Long idReporte,String name, String upi, String legalName, String commercialType, String contactName,
			String contactPhone, String contactEmail, String contactJob, String accountJdeClient,
			String accountJdeInvoice, String accountJdeTicket, String clientStatus, String clientType,
			LocalDateTime dateIns, LocalDateTime dateUpd, String addressStreetName, String addressStreetNumber,
			String addressDisStreetName, String legalUpi, String legalCivilStatus, String legalProfession,
			String legalAddress, String legalPhone, String legalNamePerson, Long idClient) {
		super(name, upi, legalName, commercialType, contactName, contactPhone, contactEmail, contactJob, accountJdeClient,
				accountJdeInvoice, accountJdeTicket, clientStatus, clientType, dateIns, dateUpd, addressStreetName,
				addressStreetNumber, addressDisStreetName, legalUpi, legalCivilStatus, legalProfession, legalAddress,
				legalPhone, legalNamePerson, idClient);
		this.idReporte = idReporte;
	}
	
	
	
	
	
	
	
	

	
	
}
