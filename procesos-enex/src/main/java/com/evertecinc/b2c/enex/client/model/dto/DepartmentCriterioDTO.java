package com.evertecinc.b2c.enex.client.model.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import com.evertecinc.b2c.enex.utils.functions.GeneralFunctions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentCriterioDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 616215871887518171L;
	private Long    idDepartment;
	private Long    idClient;
	private String  name;
	private Boolean restrL;
	private Boolean restrM;
	private Boolean restrX;
	private Boolean restrJ;
	private Boolean restrV;
	private Boolean restrS;
	private Boolean restrD;
	private Long restrHinit;
	private Long restrHend;
	private BigDecimal  restrAmountMax;
	private Long restrDailyMaxLoads;
	private String  departmentStatus;
	private Integer cantidad;
	private String  warningStockChannel;
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
    
    public String getCodeUniqueTicket() {
		return GeneralFunctions.nCeros(1, 15).concat(GeneralFunctions.nCeros(this.idDepartment.intValue(), 15));
	}
	
	public String getCodeUniqueClient() {
		return GeneralFunctions.nCeros(3, 15).concat(GeneralFunctions.nCeros(this.idDepartment.intValue(), 15));
	}
		 
	public String getCodeUniqueInvoice() {
		return GeneralFunctions.nCeros(2, 15).concat(GeneralFunctions.nCeros(this.idDepartment.intValue(), 15));
	}

}
