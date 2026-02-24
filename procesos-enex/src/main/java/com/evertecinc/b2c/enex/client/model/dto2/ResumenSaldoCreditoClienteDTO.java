package com.evertecinc.b2c.enex.client.model.dto2;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ResumenSaldoCreditoClienteDTO {

	private String upi;
	private String clientType;
	private String clientStatus;
	private BigDecimal creditLimit;
	private BigDecimal remainingAmount;
	private BigDecimal amountDue;
	private BigDecimal orderGrossAmount;

}