package com.evertecinc.b2c.enex.client.model.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1192609345390086813L;
	
	private Long idOrderItem;
	private Long idOrder;
	private Long idClient;
	private Long idUser;
	private Long idCard;
	private Long idDepartment;
	private Long idVehicle;
	private String productCode;
	private BigDecimal amount;
	private String documentType;
	private String nombreDepto;
	private String nombreProducto;
	private String nickname;
	private String patente;
	private String name;
	private String firstName;
	private String idDocumento;
	private String numeroDocumento;
	private String tipoDocumento;


}
