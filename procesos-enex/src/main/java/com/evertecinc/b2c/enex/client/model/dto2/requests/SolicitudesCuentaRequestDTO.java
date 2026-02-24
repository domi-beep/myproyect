package com.evertecinc.b2c.enex.client.model.dto2.requests;

import java.time.LocalDateTime;

import com.evertecinc.b2c.enex.client.model.dto2.criterio.CriterioBusquedaGenericoDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class SolicitudesCuentaRequestDTO extends CriterioBusquedaGenericoDTO {


	LocalDateTime fechaInicio;

	LocalDateTime fechaFin;

}
