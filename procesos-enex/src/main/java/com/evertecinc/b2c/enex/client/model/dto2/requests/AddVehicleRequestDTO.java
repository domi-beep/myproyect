package com.evertecinc.b2c.enex.client.model.dto2.requests;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.evertecinc.b2c.enex.client.model.dto.VehicleReqsDTO;
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
public class AddVehicleRequestDTO extends CriterioBusquedaGenericoDTO {

	@JsonProperty(value = "vehicleList")
	private List<VehicleReqsDTO> vehicleList;

	@JsonProperty(value = "contract")
	private MultipartFile contract;

	@JsonProperty(value = "upi")
	private String upi;

	@JsonProperty(value = "rutUsuario")
	private String rutUsuario;
	
	@JsonProperty("clientType")
	private String clientType;

	@JsonProperty("portal")
	private String portal;
}
