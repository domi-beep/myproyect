package com.evertecinc.b2c.enex.client.model.dto2;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderConfirmedDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4351363517153178283L;
	private OrderDTO order;
	private InformationPaymentDTO informationPayment;
	private Long motivo;

}
