package com.evertecinc.b2c.enex.email.model.dto;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class MailQUeueCriterio {

	private String status;
	private Integer attempts = 100;
	
	
}
