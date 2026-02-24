package com.evertecinc.b2c.enex.client.model.dto2;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CardNumberDTO {
	
	private String idMifare;
	private String xml;
	private String status;
	private LocalDateTime dateuse;
	private Long idVehicle;
	private String cardNumber;
	
	

}
