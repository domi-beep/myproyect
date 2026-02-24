package com.evertecinc.b2c.enex.client.model.dto2.requests;

import com.evertecinc.b2c.enex.client.model.dto2.criterio.CriterioBusquedaGenericoDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ReporteClienteVentaRequestDTO extends CriterioBusquedaGenericoDTO {
    
    @JsonProperty("upi")
    private String upi;
    
    @JsonProperty("legalName")
    private String legalName;
    
    @JsonProperty("clientType")
    private String clientType;
    
    @JsonProperty("contar")
    private Boolean contar;
    
    @JsonProperty("resultadoOrdenado")
    private Boolean resultadoOrdenado;
    
    @JsonProperty("ordenAscendente")
    private Boolean ordenAscendente;
}
