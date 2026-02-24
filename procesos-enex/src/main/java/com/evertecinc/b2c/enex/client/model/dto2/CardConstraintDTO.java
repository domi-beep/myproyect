package com.evertecinc.b2c.enex.client.model.dto2;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class CardConstraintDTO {

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
	private String restrType; // Tipo de restricción D: diaria S: semanal M:manual
	private String itemList;
	private String storeList;
	private Boolean gps;
	private Boolean amount;
	private Boolean tieneEstaciones = null;
	private List<String> stationsCodePersonalizadas;

}
