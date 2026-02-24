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
public class ClientCriterioDTO implements Serializable {
	
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
    private Integer addressIdArea;
    private Integer addressDisIdArea;
    private Integer addressDisIdRegion;
    private Integer addressIdRegion1;
    private Integer addressDisIdCity;
    private Integer addressIdCity1;
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

}
