package com.evertecinc.b2c.enex.client.model.dto2.requests;

import org.springframework.web.multipart.MultipartFile;

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
public class SolicitarServicioRequestDTO extends CriterioBusquedaGenericoDTO {
	
//	@JsonProperty("idClient")
//	private Long idClient;
	
	@JsonProperty(value = "servicio")
	private String servicio;
	
	@JsonProperty(value = "contratoAdBlueUpload")
	private MultipartFile contratoAdBlueUpload;
	
	@JsonProperty("upi")
	private String upi;

	@JsonProperty("rutUsuario")
	private String rutUsuario;

	@JsonProperty("clientType")
	private String clientType;

	@JsonProperty("portal")
	private String portal;

}
