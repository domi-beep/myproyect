package com.evertecinc.b2c.enex.client.model.dto2.requests;

import com.evertecinc.b2c.enex.client.model.dto2.criterio.CriterioBusquedaGenericoDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class VehiclesRequestDTO extends CriterioBusquedaGenericoDTO {

	@JsonProperty(value = "idVehicle")
	private Long idVehicle;
	
	@JsonProperty(value = "idClient")
	private Long idClient;
	
	@JsonProperty(value = "idDepartment")
	private Long idDepartment;
	
	@JsonProperty(value = "plate")
	private String plate;
	
	@JsonProperty(value = "nickname")
	private String nickname;
	
	@JsonProperty(value = "vehicleStatus")
	private String vehicleStatus;
	
	@JsonProperty(value = "documentType")
	private String documentType;
	
	@JsonProperty(value = "controlTotal")
	private Boolean controlTotal;
	
	@JsonProperty(value = "vehicleTypeCode")
	private String vehicleTypeCode;
	
	@JsonProperty(value = "productCode")
	private String productCode;

	@JsonProperty(value = "clienType")
	private String clientType;
	
}
