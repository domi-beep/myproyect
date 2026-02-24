package com.evertecinc.b2c.enex.client.model.dto2.orpakInterface;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class CreateVehicleCardResponse {
	
	private int returnCode;
	private String message;
	private String uri;

}
