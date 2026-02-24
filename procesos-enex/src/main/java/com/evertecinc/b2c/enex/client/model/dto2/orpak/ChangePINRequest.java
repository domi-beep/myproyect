package com.evertecinc.b2c.enex.client.model.dto2.orpak;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class ChangePINRequest {
	
	private String cardnumber;
	private String oldpin;
	private String newpin;

}
