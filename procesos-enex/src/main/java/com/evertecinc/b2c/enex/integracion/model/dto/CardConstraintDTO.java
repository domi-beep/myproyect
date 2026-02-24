package com.evertecinc.b2c.enex.integracion.model.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardConstraintDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5788108274232705398L;
	private String customerCode;
	private String plate;
	private Boolean restrL;
	private Boolean restrM;
	private Boolean restrX;
	private Boolean restrJ;
	private Boolean restrV;
	private Boolean restrS;
	private Boolean restrD;
	private Long restrHini;
	private Long restrHend;
	private List<String> listaCardStationConstraint = new ArrayList<String>();
	private BigDecimal restrAmountMax;
	private Long restrDailyMaxLoads;
	private Long restrDailyMaxQuantity;
	private String restrType; // Tipo de restricci�n D: diaria S: semanal M:manual
	private String itemList;
	private String storeList;
	private Boolean gps;
	private Boolean amount;
	private Boolean tieneEstaciones = null;
	private List<String> stationsCodePersonalizadas;

}
