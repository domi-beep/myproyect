package com.evertecinc.b2c.enex.client.model.dto2;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class ChangePinDTO {
	
	private String newPin;
	private String oldPin;
	private String cardNumber;
	private Long idCard;
}
