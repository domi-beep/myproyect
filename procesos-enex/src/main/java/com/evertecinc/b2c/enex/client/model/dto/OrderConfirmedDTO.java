package com.evertecinc.b2c.enex.client.model.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderConfirmedDTO implements Serializable{
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1209112091333293094L;
	
	private OrderDTO order;
	private InformationPaymentDTO informationPayment;
	private Long motivo;

}
