package com.evertecinc.b2c.enex.client.model.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OtHeaderDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1501217003575265632L;
	private Long idOt;
	private String rutCliente;
	private String razonSocial;
	private String patente;
	private String tipoCliente;
	private LocalDateTime dateIns;
	private LocalDateTime dateContact;
	private LocalDateTime dateAgree;
	private LocalDateTime dateReal;
	private String nombreTecnico;
	private String status;
	private Integer cantidad;
	private Long idClient;
	private Long idUser;
	private Long idVehicle;
	private String comments;
	private String cuentaC;

}
