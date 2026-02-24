package com.evertecinc.b2c.enex.client.model.dto2.orpak;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class GetDebitAmountCardsResponse {
	
	private String cardnumber;
	private float amount;
	private float remainingCountLoad;
	private float remainingAmount;
	private String periodType; 
	private Integer code;
	private String message;
	private String uri;

}
