package com.evertecinc.b2c.enex.client.model.dto2.restResponses;

import java.util.List;

import com.evertecinc.b2c.enex.client.model.dto2.SolicitudCuentaDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SolicitudesCuentaResponseDTO extends GenericResultDTO {

	private List<SolicitudCuentaDTO> listRetorno;
	private Long totalElementos;

}
