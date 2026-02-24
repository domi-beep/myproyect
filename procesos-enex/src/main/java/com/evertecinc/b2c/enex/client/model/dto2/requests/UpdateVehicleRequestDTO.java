package com.evertecinc.b2c.enex.client.model.dto2.requests;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class UpdateVehicleRequestDTO {
	
	@JsonProperty("upi")
	private String upi;
	
	@JsonProperty("rutUsuario")
	private String rutUsuario;
	
	@JsonProperty("clientType")
	private String clientType;
	
	@JsonProperty("portal")
	private String portal;	

	@JsonProperty("idCard")
	private Long idCard;

	@JsonProperty("idVehicle")
	private Long idVehicle;

	@JsonProperty("warningStockChannel")
	private String warningStockChannel;

	@JsonProperty("warningStock")
	private Double warningStock;

	@JsonProperty("warningStockCelular")
	private String warningStockCelular;

	@JsonProperty("warningStockEmail")
	private String warningStockEmail;

	@JsonProperty("warningLoadChannel")
	private String warningLoadChannel;

	@JsonProperty("warningLoadCelular")
	private String warningLoadCelular;

	@JsonProperty("warningLoadEmail")
	private String warningLoadEmail;

	@JsonProperty("warningLoadFailChannel")
	private String warningLoadFailChannel;

	@JsonProperty("warningLoadFailCelular")
	private String warningLoadFailCelular;

	@JsonProperty("warningLoadFailEmail")
	private String warningLoadFailEmail;

}
