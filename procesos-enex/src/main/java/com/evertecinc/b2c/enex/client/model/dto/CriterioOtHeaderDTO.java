package com.evertecinc.b2c.enex.client.model.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CriterioOtHeaderDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3973525489347841429L;
	private Long idOt;
	private String razonSocial;
	private String rutCliente;
	private LocalDateTime dateIns;
	private String nombreTecnico;
	private String firstNameTecnico;
	private String lastNameTecnico;
	private String status;
	private Long idVehicle;
	private Long idUser;
	private Long idClient;

}
