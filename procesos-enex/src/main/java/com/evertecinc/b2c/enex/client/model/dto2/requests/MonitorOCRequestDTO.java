package com.evertecinc.b2c.enex.client.model.dto2.requests;

import java.time.LocalDateTime;

import com.evertecinc.b2c.enex.client.model.dto2.criterio.CriterioBusquedaGenericoDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MonitorOCRequestDTO extends CriterioBusquedaGenericoDTO{
	
	@JsonProperty("numFactura")
    private String numFactura;

    @JsonProperty("numOC")
    private String numOC;

    @JsonProperty("rutCliente")
    private String rutCliente;

    @JsonProperty("nombreCliente")
    private String nombreCliente;

    @JsonProperty("estadoDocT")
    private String estadoDocT;

    @JsonProperty("idClient")
    private Long idClient;

    @JsonProperty("tipoDoc")
    private String tipoDoc;

    @JsonProperty("dateFirst")
    private LocalDateTime dateFirst;

    @JsonProperty("dateEnd")
    private LocalDateTime dateEnd;

    @JsonProperty("nomonitorOC")
    private Boolean nomonitorOC;

}
