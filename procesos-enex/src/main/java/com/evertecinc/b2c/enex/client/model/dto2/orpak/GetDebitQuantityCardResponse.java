package com.evertecinc.b2c.enex.client.model.dto2.orpak;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GetDebitQuantityCardResponse {
	
	private String balanceType;
	private String fuel;
	private String fuel1;
	private String fuel2;
	private String fuel3;
	private Float fuelQuantity;
	private Float fuel1Quantity;
	private Float fuel2Quantity;
	private Float fuel3Quantity;
	private String plafond;
	private String plate;
	private String ruleSet;
	private String uri;

}
