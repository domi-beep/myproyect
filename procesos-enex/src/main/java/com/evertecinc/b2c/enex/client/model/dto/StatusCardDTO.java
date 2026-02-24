package com.evertecinc.b2c.enex.client.model.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatusCardDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1765385904593764091L;
	
	private Long    idCard;
	private String  cardnum;
	private String  cardStatus;

}
