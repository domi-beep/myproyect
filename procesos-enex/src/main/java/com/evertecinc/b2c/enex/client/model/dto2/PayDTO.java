package com.evertecinc.b2c.enex.client.model.dto2;

import lombok.Data;

@Data
public class PayDTO {
	
	private Long ordenCompra;
	private Double monto;
	private String codigoAutorizacion;

}
