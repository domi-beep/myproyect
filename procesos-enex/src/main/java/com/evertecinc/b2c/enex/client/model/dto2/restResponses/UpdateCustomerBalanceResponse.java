package com.evertecinc.b2c.enex.client.model.dto2.restResponses;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class UpdateCustomerBalanceResponse {

	private String returnCode;
	private String message;
	private String uri;
}
