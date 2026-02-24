package com.evertecinc.b2c.enex.integracion.model.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChangeCardStatusRequest implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 27310626064293705L;
	private String cardnumber;
	private String sender;
	private String status;

}
