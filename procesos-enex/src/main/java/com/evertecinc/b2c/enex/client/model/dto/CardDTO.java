package com.evertecinc.b2c.enex.client.model.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2279280379448876207L;
	private Long    idCard;
	private Long    idClient;
	private String  cardnum;
	private BigDecimal  remainingAmount;
	private LocalDateTime    crtdate;
	private LocalDateTime    upddate;
	private String  cardStatus;
	private Boolean restrL;
	private Boolean restrM;
	private Boolean restrX;
	private Boolean restrJ;
	private Boolean restrV;
	private Boolean restrS;
	private Boolean restrD;
	private Long restrHini;
	private Long restrHend;
	private BigDecimal  restrAmountMax;
	private Long restrDailyMaxLoads;
    private String restrictionType;
    private String version;
    private Long restrDailyMaxQuantity;
    private Long remainingTrxLoad;
    private BigDecimal  remainingPeriodAmount;
    private String restrType;
    private String reqcardStatus;
    private Boolean reqCardReprint;
    private String ctPosition;
    private String plate;

}
