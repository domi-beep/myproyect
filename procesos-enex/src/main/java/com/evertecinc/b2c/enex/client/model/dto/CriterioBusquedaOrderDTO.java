package com.evertecinc.b2c.enex.client.model.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CriterioBusquedaOrderDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2831373281669279478L;
	
	private Long idOrder;
	private Long idClient;
	private Long idUser;
	private String payType;
	private String orderStatus;

}
