package com.evertecinc.b2c.enex.client.model.dto2;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MpDepositoDTO extends PayDTO{

	private Long idOrder;
	private String comprobante;
	private String banco;
	private Double monto;

}
