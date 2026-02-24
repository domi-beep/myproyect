package com.evertecinc.b2c.enex.client.model.dto2.restResponses;

import java.util.Optional;

import com.evertecinc.b2c.enex.client.model.dto.RegisterClientDTO;
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
public class MisDatosInfoResultDTO extends GenericResultDTO {

	@JsonProperty("datosCliente")
	public Optional<RegisterClientDTO> datosCliente;

}
