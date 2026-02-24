package com.evertecinc.b2c.enex.client.model.dto2.requests;

import com.evertecinc.b2c.enex.client.model.dto2.criterio.CriterioBusquedaGenericoDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ListCardRequestDTO extends CriterioBusquedaGenericoDTO {

	@JsonProperty("vehicleTypeCode")
	private String vehicleTypeCode;

	@JsonProperty("idDepartment")
	private Long idDepartment;

	@JsonProperty("idVehicle")
	private Long idVehicle;
	
	@JsonProperty("upi")
	private String upi;

	@JsonProperty("clientType")
	private String clientType;

	@JsonProperty("plate")
	private String plate;

	@JsonProperty("nickname")
	private String nickname;

	@JsonProperty("productoCode") // combustible
	private String productCode;

	@JsonProperty("documentType")
	private String documentType;

	@JsonProperty("idClient")
	private Long idClient;

	@JsonProperty("cardNum")
	private String cardnum;

	@JsonProperty("idCard")
	private Long idCard;

	@JsonProperty("sinTarjetas")
	private Boolean sinTarjetas;

	@JsonProperty("frontOffice")
	private Boolean frontOffice;

	@JsonProperty("quitarPendientes")
	private Boolean quitarPendientes;

	@JsonProperty("cardStatus")
	private String cardStatus;

}