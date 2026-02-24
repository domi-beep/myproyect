package com.evertecinc.b2c.enex.client.model.dto2.requests;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UpdateTarjetasVehiculoRequestDTO {

	@JsonProperty(value = "idCard")
	private Long idCard;
	
	@JsonProperty(value = "idVehicle")
	private Long idVehicle;
	
	@JsonProperty(value = "estadoEliminado")
	private String estadoEliminado;
	
	@JsonProperty(value = "estado")
	private String estado;
	
	@JsonProperty(value = "nickname")
	private String nickname;
	
	@JsonProperty(value = "idDepartment")
	private Long idDepartment;
	
	@JsonProperty(value = "datachip")
	private Boolean datachip;
	
	@JsonProperty(value = "productCode")
	private String productCode;
	
	@JsonProperty("upi")
	private String upi;

	@JsonProperty("rutUsuario")
	private String rutUsuario;

	@JsonProperty("clientType")
	private String clientType;
	
}
