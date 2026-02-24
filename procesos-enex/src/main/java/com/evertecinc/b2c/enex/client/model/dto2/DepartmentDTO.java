package com.evertecinc.b2c.enex.client.model.dto2;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DepartmentDTO {

	private Long idDepartment;
	private Long idClient;
	private String name;
	private Boolean restrL;
	private Boolean restrM;
	private Boolean restrX;
	private Boolean restrJ;
	private Boolean restrV;
	private Boolean restrS;
	private Boolean restrD;
	private Long restrHinit;
	private Long restrHend;
	private BigDecimal restrAmountMax;
	private Long restrDailyMaxLoads;
	private String departmentStatus;
	private Integer cantidad;
	private String warningStockChannel;
	private BigDecimal warningStock;
	private String warningStockCelular;
	private String warningStockMail;
	private String warningLoadChannel;
	private String warningLoadCelular;
	private String warningLoadMail;
	private String warningLoadFailChannel;
	private String warningLoadFailCelular;
	private String warningLoadFailEmail;
	private String typeBalance;
	private Integer constraint;
	private String codeOrpakTicket;
	private String codeOrpakInvoice;
	private String codeOrpakClient;
	private Boolean restrictionTypeCard;
	private Long restrDailyMaxQuantity;
	private String restrType;
	private Long totalTarjetas;
	private String gps;
	private Long idPadre;
	private Boolean departmentLeaf;
	private Boolean hasChild;
	private Boolean noUsuario;

	/**
	 * Contructor de REPO getDepartmentsByClient
	 * 
	 * @param idDepartment
	 * @param idClient
	 * @param name
	 * @param restrL
	 * @param restrM
	 * @param restrX
	 * @param restrJ
	 * @param restrV
	 * @param restrS
	 * @param restrD
	 * @param restrHinit
	 * @param restrHend
	 * @param restrAmountMax
	 * @param restrDailyMaxLoads
	 * @param departmentStatus
	 * @param warningStockChannel
	 * @param warningStock
	 * @param warningStockCelular
	 * @param warningStockMail
	 * @param warningLoadChannel
	 * @param warningLoadCelular
	 * @param warningLoadMail
	 * @param warningLoadFailChannel
	 * @param warningLoadFailCelular
	 * @param warningLoadFailEmail
	 * @param typeBalance
	 * @param codeOrpakClient
	 * @param restrDailyMaxQuantity
	 * @param restrType
	 * @param totalTarjetas
	 * @param idPadre
	 */
	public DepartmentDTO(Long idDepartment, Long idClient, String name, Boolean restrL, Boolean restrM, Boolean restrX,
			Boolean restrJ, Boolean restrV, Boolean restrS, Boolean restrD, Long restrHinit, Long restrHend,
			BigDecimal restrAmountMax, Long restrDailyMaxLoads, String departmentStatus, String warningStockChannel,
			BigDecimal warningStock, String warningStockCelular, String warningStockMail, String warningLoadChannel,
			String warningLoadCelular, String warningLoadMail, String warningLoadFailChannel,
			String warningLoadFailCelular, String warningLoadFailEmail, String typeBalance, String codeOrpakClient,
			Long restrDailyMaxQuantity, String restrType, Long totalTarjetas, Long idPadre) {
		super();
		this.idDepartment = idDepartment;
		this.idClient = idClient;
		this.name = name;
		this.restrL = restrL;
		this.restrM = restrM;
		this.restrX = restrX;
		this.restrJ = restrJ;
		this.restrV = restrV;
		this.restrS = restrS;
		this.restrD = restrD;
		this.restrHinit = restrHinit;
		this.restrHend = restrHend;
		this.restrAmountMax = restrAmountMax;
		this.restrDailyMaxLoads = restrDailyMaxLoads;
		this.departmentStatus = departmentStatus;
		this.warningStockChannel = warningStockChannel;
		this.warningStock = warningStock;
		this.warningStockCelular = warningStockCelular;
		this.warningStockMail = warningStockMail;
		this.warningLoadChannel = warningLoadChannel;
		this.warningLoadCelular = warningLoadCelular;
		this.warningLoadMail = warningLoadMail;
		this.warningLoadFailChannel = warningLoadFailChannel;
		this.warningLoadFailCelular = warningLoadFailCelular;
		this.warningLoadFailEmail = warningLoadFailEmail;
		this.typeBalance = typeBalance;
		this.codeOrpakClient = codeOrpakClient;
		this.restrDailyMaxQuantity = restrDailyMaxQuantity;
		this.restrType = restrType;
		this.totalTarjetas = totalTarjetas;
		this.idPadre = idPadre;
	}

	/**
	 * Contructor de REPO getDepartmentsByIdUser
	 * 
	 * @param idDepartment
	 * @param name
	 * @param typeBalance
	 * @param totalTarjetas
	 * @param idPadre
	 */
	public DepartmentDTO(Long idDepartment, String name, String typeBalance, Long totalTarjetas, Long idPadre) {
		super();
		this.idDepartment = idDepartment;
		this.name = name;
		this.typeBalance = typeBalance;
		this.totalTarjetas = totalTarjetas;
		this.idPadre = idPadre;
	}

	/**
	 * Contructor de REPO getDeptoHijosRolUser
	 * 
	 * @param idDepartment
	 * @param idClient
	 * @param name
	 * @param departmentStatus
	 * @param typeBalance
	 * @param idPadre
	 */
	public DepartmentDTO(Long idDepartment, Long idClient, String name, String departmentStatus, String typeBalance,
			Long idPadre) {
		super();
		this.idDepartment = idDepartment;
		this.idClient = idClient;
		this.name = name;
		this.departmentStatus = departmentStatus;
		this.typeBalance = typeBalance;
		this.idPadre = idPadre;
	}
	
}
