package com.evertecinc.b2c.enex.client.model.dto2.orpakInterface;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class CreateVehicleCardRequest {
	
	private int operationtype;
	private String customercode;
	private String plate;
	private String name;
	private String surname;
	private String depatmentcode;
	private String cardnumber;
	private String fueltype;
	private String fuelcode;
	private int plafond;
	private String balancetype;
	private String cardtype;
	private String doctype;
	private Boolean datapass;
	private Boolean ct;
	private String vehiclerule;
	private String deviceusage;

}
