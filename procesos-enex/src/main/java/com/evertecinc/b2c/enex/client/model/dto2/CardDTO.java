package com.evertecinc.b2c.enex.client.model.dto2;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CardDTO {
	private Long idCard;
	private Long idClient;
	private String cardNum;
	private BigDecimal remainingAmount;
	private Integer remainingTrxLoad;
	private BigDecimal remainingPeriodAmount;
	private LocalDateTime crtDate;
	private LocalDateTime updDate;
	private String cardStatus;
	private String restrType;
	private Integer restrL;
	private Integer restrM;
	private Integer restrX;
	private Integer restrJ;
	private Integer restrV;
	private Integer restrS;
	private Integer restrD;
	private Integer restrHini;
	private Integer restrHend;
	private BigDecimal restrAmountMax;
	private Integer restrDailyMaxLoads;
	private Integer restrDailyMaxQuantity;
	private String restrictionType;
	private String version;
	private String reqCardStatus;
	private Integer reqCardReprint;
	private String ctPosition;

	public CardDTO(Long idCard, Long idClient, String cardNum, BigDecimal remainingAmount, Integer remainingTrxLoad,
			BigDecimal remainingPeriodAmount, LocalDateTime crtDate, LocalDateTime updDate, String cardStatus,
			String restrType, Integer restrL, Integer restrM, Integer restrX, Integer restrJ, Integer restrV, Integer restrS,
			Integer restrD, Integer restrHini, Integer restrHend, BigDecimal restrAmountMax, Integer restrDailyMaxLoads,
			Integer restrDailyMaxQuantity, String restrictionType, String version, String reqCardStatus,
			Integer reqCardReprint, String ctPosition) {
		super();
		this.idCard = idCard;
		this.idClient = idClient;
		this.cardNum = cardNum;
		this.remainingAmount = remainingAmount;
		this.remainingTrxLoad = remainingTrxLoad;
		this.remainingPeriodAmount = remainingPeriodAmount;
		this.crtDate = crtDate;
		this.updDate = updDate;
		this.cardStatus = cardStatus;
		this.restrType = restrType;
		this.restrL = restrL;
		this.restrM = restrM;
		this.restrX = restrX;
		this.restrJ = restrJ;
		this.restrV = restrV;
		this.restrS = restrS;
		this.restrD = restrD;
		this.restrHini = restrHini;
		this.restrHend = restrHend;
		this.restrAmountMax = restrAmountMax;
		this.restrDailyMaxLoads = restrDailyMaxLoads;
		this.restrDailyMaxQuantity = restrDailyMaxQuantity;
		this.restrictionType = restrictionType;
		this.version = version;
		this.reqCardStatus = reqCardStatus;
		this.reqCardReprint = reqCardReprint;
		this.ctPosition = ctPosition;
	}
	
	public CardDTO(Long idCard, Long idClient, String cardNum, BigDecimal remainingAmount, LocalDateTime crtDate,
			LocalDateTime updDate, String cardStatus, String restrType, 
			Integer restrL, Integer restrM, Integer restrX,
			Integer restrJ, Integer restrV, Integer restrS, Integer restrD, Integer restrHini,
			Integer restrHend, BigDecimal restrAmountMax, Integer restrDailyMaxLoads,
			Integer restrDailyMaxQuantity, String restrictionType, String version) {
		this.idCard = idCard;
		this.idClient = idClient;
		this.cardNum = cardNum;
		this.remainingAmount = remainingAmount;
		this.crtDate = crtDate;
		this.updDate = updDate;
		this.cardStatus = cardStatus;
		this.restrType = restrType;
		this.restrL = restrL;
		this.restrM = restrM;
		this.restrX = restrX;
		this.restrJ = restrJ;
		this.restrV = restrV;
		this.restrS = restrS;
		this.restrD = restrD;
		this.restrHini = restrHini;
		this.restrHend = restrHend;
		this.restrAmountMax = restrAmountMax;
		this.restrDailyMaxLoads = restrDailyMaxLoads;
		this.restrDailyMaxQuantity = restrDailyMaxQuantity;
		this.restrictionType = restrictionType;
		this.version = version;
	}

}
