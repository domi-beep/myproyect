package com.evertecinc.b2c.enex.client.model.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO implements Serializable {
	
	public ClientDTO(Long idClient, String name, String upi, String legalName, String commercialType,
			String contactName, String contactPhone, String contactEmail, String contactJob, String accountJdeClient,
			String accountJdeInvoice, String accountJdeTicket, String clientStatus, String clientType,
			LocalDateTime dateins, LocalDateTime dateupd, String addressStreetName, String addressStreetNumber,
			String addressDisStreetName, String legalUpi, String legalCivilStatus, String legalProfession,
			String legalAddress, String legalPhone, String legalNamePerson) {
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
		this.dateins = dateins;
		this.dateupd = dateupd;
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

	/**
	 * 
	 */
	private static final long serialVersionUID = -1765385904593764091L;
	
	private Long    idClient;
	private String  name;
	private String  upi;
	private String  legalName;
	private String  commercialType;
    private String  contactName;
    private String  contactPhone;
    private String  contactEmail;
    private String  contactJob;
    private String  accountJdeInvoice;
    private String  accountJdeTicket;
    private String  accountJdeClient;
    private String  codeOrpakClient;
    private String  codeOrpakInvoice;
    private String  codeOrpakTicket;
    private String  clientStatus;
    private String  clientType;
    private LocalDateTime    dateins;
    private LocalDateTime    dateupd;
    private String  addressStreetName;
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
	private Long idReporte;
	
	
	public boolean isCredit() {
		return "T".equals(this.isCredit) ? true : false;
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
		this.dateins = dateIns;
		this.dateupd = dateUpd;
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
		this.legalMail = legalEmail;
		this.codeOrpakTicket = codeOrpakTicket;
		this.codeOrpakInvoice = codeOrpakInvoice;
	}

	public ClientDTO(Long idClient) {
		super();
		this.idClient = idClient;
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
		this.dateins = dateIns;
		this.dateupd = dateUpd;
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
