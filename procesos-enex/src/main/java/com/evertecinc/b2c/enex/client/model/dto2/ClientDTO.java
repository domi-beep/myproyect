package com.evertecinc.b2c.enex.client.model.dto2;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.evertecinc.b2c.enex.client.model.dto.AreaDTO;
import com.evertecinc.b2c.enex.client.model.dto.CityDTO;
import com.evertecinc.b2c.enex.client.model.dto.RegionDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ClientDTO {
	
	private Long idClient;
	private String name;
	private String upi;
	private String legalName;
	private String commercialType;
	private String contactName;
	private String contactPhone;
	private String contactEmail;
	private String contactJob;
	private String accountJdeClient;
	private String accountJdeInvoice;
	private String accountJdeTicket;
	private String clientStatus;
	private String clientType;
	private LocalDateTime dateIns;
	private LocalDateTime dateUpd;
	private String addressStreetName;
	private String addressStreetNumber;
	private String addressDoorNumber;
	private String addressDisStreetName;
	private String addressDisStreetNumber;
	private String addressDisDoorNumber;
	private Long addressIdArea;
	private Long addressDisIdArea;
	private Long addressDisIdRegion;
	private Long addressIdRegion1;
	private Long addressDisIdCity;
	private Long addressIdCity1;
	private String legalUpi;
	private String legalCivilStatus;
	private String legalProfession;
	private String legalAddress;
	private String legalPhone;
	private String legalNamePerson;
	private String legalEmail;
	private String codeOrpakTicket;
	private String codeOrpakInvoice;
	
	private String codeOrpakClient;
	private String legalMail;
	private List<DepartmentDTO> listDepartments = new ArrayList<DepartmentDTO>();
	private List<VehicleDTO> listVehicles = new ArrayList<VehicleDTO>();
	private List<VehicleReqsDTO> vehiculoReqCompleto = new ArrayList<VehicleReqsDTO>();
	private AreaDTO areaDTO;
	private CityDTO cityDTO;
	private RegionDTO regionDTO;
	private AreaDTO areaFDTO;
	private CityDTO cityFDTO;
	private RegionDTO regionFDTO;
	private BigDecimal remainingAmount;
	private BigDecimal creditLimit;
	private String locked;
	private String isCredit;
	private String warningStockChannel;
	private BigDecimal warningStock;
	private String warningStockCelular;
	private String warningStockEmail;
	private String warningLockedChannel;
	private String warningLockedEmail;
	private Long idEjecutivo;
	private Long idJefeZona;
	private EjecutivoDTO ejecutivo;
	private JefeZonaDTO jefeZona;
	private Boolean factDepartment;
	private LocalDateTime factDepartmentDate;
	private Boolean isPrivate;
	private String storelistorpak;
	private Boolean controlPass;
	private Boolean adblue;
	private String gps;
	private String tipoReglamento;
	private Long idReporte;
	
	
	/**
	 * @apiNote Repositorio que utilizan este Constructor:
	 * @apiNote ClientRepoImpl getListClientByCriteria
	 */
	public ClientDTO(Long idClient, String name, String upi, String legalName, String commercialType,
			String contactName, String contactPhone, String contactEmail, String contactJob, String accountJdeClient,
			String accountJdeInvoice, String accountJdeTicket, String clientStatus, String clientType,
			LocalDateTime dateIns, LocalDateTime dateUpd, String addressStreetName, String addressStreetNumber,
			String addressDoorNumber, String addressDisStreetName, String addressDisStreetNumber,
			String addressDisDoorNumber, Long addressIdArea, Long addressDisIdArea, Long addressDisIdRegion,
			Long addressIdRegion1, Long addressDisIdCity, Long addressIdCity1, String legalUpi, String legalCivilStatus,
			String legalProfession, String legalAddress, String legalPhone, String legalNamePerson, String legalEmail,
			String codeOrpakTicket, String codeOrpakInvoice) {
		super();
		this.idClient = idClient;
		this.name = name;
		this.upi = upi;
		this.legalName = legalName;
		this.commercialType = commercialType;
		this.contactName = contactName;
		this.contactPhone = contactPhone;
		this.contactEmail = contactEmail;
		this.contactJob = contactJob;
		this.accountJdeClient = accountJdeClient;
		this.accountJdeInvoice = accountJdeInvoice;
		this.accountJdeTicket = accountJdeTicket;
		this.clientStatus = clientStatus;
		this.clientType = clientType;
		this.dateIns = dateIns;
		this.dateUpd = dateUpd;
		this.addressStreetName = addressStreetName;
		this.addressStreetNumber = addressStreetNumber;
		this.addressDoorNumber = addressDoorNumber;
		this.addressDisStreetName = addressDisStreetName;
		this.addressDisStreetNumber = addressDisStreetNumber;
		this.addressDisDoorNumber = addressDisDoorNumber;
		this.addressIdArea = addressIdArea;
		this.addressDisIdArea = addressDisIdArea;
		this.addressDisIdRegion = addressDisIdRegion;
		this.addressIdRegion1 = addressIdRegion1;
		this.addressDisIdCity = addressDisIdCity;
		this.addressIdCity1 = addressIdCity1;
		this.legalUpi = legalUpi;
		this.legalCivilStatus = legalCivilStatus;
		this.legalProfession = legalProfession;
		this.legalAddress = legalAddress;
		this.legalPhone = legalPhone;
		this.legalNamePerson = legalNamePerson;
		this.legalEmail = legalEmail;
		this.codeOrpakTicket = codeOrpakTicket;
		this.codeOrpakInvoice = codeOrpakInvoice;
	}

	public ClientDTO(Long idClient) {
		super();
		this.idClient = idClient;
	}
	
	

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ClientDTO [idClient=");
		builder.append(idClient);
		builder.append(", name=");
		builder.append(name);
		builder.append(", upi=");
		builder.append(upi);
		builder.append(", legalName=");
		builder.append(legalName);
		builder.append(", commercialType=");
		builder.append(commercialType);
		builder.append(", contactName=");
		builder.append(contactName);
		builder.append(", contactPhone=");
		builder.append(contactPhone);
		builder.append(", contactEmail=");
		builder.append(contactEmail);
		builder.append(", contactJob=");
		builder.append(contactJob);
		builder.append(", accountJdeClient=");
		builder.append(accountJdeClient);
		builder.append(", accountJdeInvoice=");
		builder.append(accountJdeInvoice);
		builder.append(", accountJdeTicket=");
		builder.append(accountJdeTicket);
		builder.append(", clientStatus=");
		builder.append(clientStatus);
		builder.append(", clientType=");
		builder.append(clientType);
		builder.append(", dateIns=");
		builder.append(dateIns);
		builder.append(", dateUpd=");
		builder.append(dateUpd);
		builder.append(", addressStreetName=");
		builder.append(addressStreetName);
		builder.append(", addressStreetNumber=");
		builder.append(addressStreetNumber);
		builder.append(", addressDoorNumber=");
		builder.append(addressDoorNumber);
		builder.append(", addressDisStreetName=");
		builder.append(addressDisStreetName);
		builder.append(", addressDisStreetNumber=");
		builder.append(addressDisStreetNumber);
		builder.append(", addressDisDoorNumber=");
		builder.append(addressDisDoorNumber);
		builder.append(", addressIdArea=");
		builder.append(addressIdArea);
		builder.append(", addressDisIdArea=");
		builder.append(addressDisIdArea);
		builder.append(", addressDisIdRegion=");
		builder.append(addressDisIdRegion);
		builder.append(", addressIdRegion1=");
		builder.append(addressIdRegion1);
		builder.append(", addressDisIdCity=");
		builder.append(addressDisIdCity);
		builder.append(", addressIdCity1=");
		builder.append(addressIdCity1);
		builder.append(", legalUpi=");
		builder.append(legalUpi);
		builder.append(", legalCivilStatus=");
		builder.append(legalCivilStatus);
		builder.append(", legalProfession=");
		builder.append(legalProfession);
		builder.append(", legalAddress=");
		builder.append(legalAddress);
		builder.append(", legalPhone=");
		builder.append(legalPhone);
		builder.append(", legalNamePerson=");
		builder.append(legalNamePerson);
		builder.append(", legalEmail=");
		builder.append(legalEmail);
		builder.append(", codeOrpakTicket=");
		builder.append(codeOrpakTicket);
		builder.append(", codeOrpakInvoice=");
		builder.append(codeOrpakInvoice);
		builder.append("]");
		return builder.toString();
	}
	
	public boolean isCredit() {
		return "T".equals(this.isCredit) ? true : false;
	}

	/**
	 * @param idClient
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
	 */
	public ClientDTO( String name, String upi, String legalName, String commercialType,
			String contactName, String contactPhone, String contactEmail, String contactJob, String accountJdeClient,
			String accountJdeInvoice, String accountJdeTicket, String clientStatus, String clientType,
			LocalDateTime dateIns, LocalDateTime dateUpd, String addressStreetName, String addressStreetNumber,
			String addressDisStreetName, String legalUpi, String legalCivilStatus, String legalProfession,
			String legalAddress, String legalPhone, String legalNamePerson,Long idClient) {
		super();
		this.idClient = idClient;
		this.name = name;
		this.upi = upi;
		this.legalName = legalName;
		this.commercialType = commercialType;
		this.contactName = contactName;
		this.contactPhone = contactPhone;
		this.contactEmail = contactEmail;
		this.contactJob = contactJob;
		this.accountJdeClient = accountJdeClient;
		this.accountJdeInvoice = accountJdeInvoice;
		this.accountJdeTicket = accountJdeTicket;
		this.clientStatus = clientStatus;
		this.clientType = clientType;
		this.dateIns = dateIns;
		this.dateUpd = dateUpd;
		this.addressStreetName = addressStreetName;
		this.addressStreetNumber = addressStreetNumber;
		this.addressDisStreetName = addressDisStreetName;
		this.legalUpi = legalUpi;
		this.legalCivilStatus = legalCivilStatus;
		this.legalProfession = legalProfession;
		this.legalAddress = legalAddress;
		this.legalPhone = legalPhone;
		this.legalNamePerson = legalNamePerson;
	}
	
	public String getCustomerCodeClient() {
		return this.accountJdeClient;
	}
	
	public String getCustomerCodeTicket() {
		//String retorno = "01"+GeneralFunctions.nCeros(Integer.parseInt(this.idClient+""),8);
		return this.accountJdeTicket;
	}
	
	public String getCustomerCodeInvoice() {
		//String retorno = "02"+GeneralFunctions.nCeros(Integer.parseInt(this.idClient+""),8);
		return this.accountJdeInvoice;
	}

	public ClientDTO(Long idReporte, Long idClient, String name, String upi, String legalName, 
            String commercialType, String contactName, String contactPhone, String contactEmail, 
            String contactJob, String accountJdeClient, String accountJdeInvoice, 
            String accountJdeTicket, String clientStatus, String clientType, 
            LocalDateTime dateIns, LocalDateTime dateUpd, String addressStreetName, 
            String addressStreetNumber, String addressDisStreetName, String legalUpi, 
            String legalCivilStatus, String legalProfession, String legalAddress, 
            String legalPhone, String legalNamePerson) {
		this.idReporte = idReporte;
		this.idClient = idClient;
		this.name = name;
		this.upi = upi;
		this.legalName = legalName;
		this.commercialType = commercialType;
		this.contactName = contactName;
		this.contactPhone = contactPhone;
		this.contactEmail = contactEmail;
		this.contactJob = contactJob;
		this.accountJdeClient = accountJdeClient;
		this.accountJdeInvoice = accountJdeInvoice;
		this.accountJdeTicket = accountJdeTicket;
		this.clientStatus = clientStatus;
		this.clientType = clientType;
		this.dateIns = dateIns;
		this.dateUpd = dateUpd;
		this.addressStreetName = addressStreetName;
		this.addressStreetNumber = addressStreetNumber;
		this.addressDisStreetName = addressDisStreetName;
		this.legalUpi = legalUpi;
		this.legalCivilStatus = legalCivilStatus;
		this.legalProfession = legalProfession;
		this.legalAddress = legalAddress;
		this.legalPhone = legalPhone;
		this.legalNamePerson = legalNamePerson;
	}


}