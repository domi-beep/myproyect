package com.evertecinc.b2c.enex.client.model.dto2.requests;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
@Setter
public class BusquedaOrderRequestDTO {

	private Long idOrder;
	private Long idClient;
	private Long idUser;
	private String payType;
	private String orderStatus;
}
