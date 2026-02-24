package com.evertecinc.b2c.enex.client.model.dto2.orpak;

import java.math.BigDecimal;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class ResponseBalanceCardDTO {

	private Long    idCard;
	private String  cardNumber;
	private BigDecimal  remainingAmount;
	private BigDecimal  remainingTrxLoad;
	private BigDecimal  remainingPeriodAmount;
	
}
