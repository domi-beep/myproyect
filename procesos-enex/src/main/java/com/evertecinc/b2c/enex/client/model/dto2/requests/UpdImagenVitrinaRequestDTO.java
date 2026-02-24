package com.evertecinc.b2c.enex.client.model.dto2.requests;

import java.time.LocalDateTime;

import com.evertecinc.b2c.enex.client.model.dto2.criterio.CriterioBusquedaGenericoSessionDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UpdImagenVitrinaRequestDTO extends CriterioBusquedaGenericoSessionDTO{

	@JsonProperty("imagenWeb")
    private String imagenWeb;

    @JsonProperty("imagenMobile")
    private String imagenMobile;

    @JsonProperty("url")
    private String url;

    @JsonProperty("target")
    private String target;

    @JsonProperty("fechaInicio")
    private LocalDateTime fechaInicio;

    @JsonProperty("fechaFin")
    private LocalDateTime fechaFin;

    @JsonProperty("estado")
    private String estado;

    @JsonProperty("nombre")
    private String nombre;

    @JsonProperty("idImagen")
    private Long idImagen;

    @JsonProperty("orden")
    private Integer orden;

    @JsonProperty("timer")
    private Boolean timer;

    @JsonProperty("Web_V")
    private Integer Web_V;

    @JsonProperty("Mob_V")
    private Integer Mob_V;
}
