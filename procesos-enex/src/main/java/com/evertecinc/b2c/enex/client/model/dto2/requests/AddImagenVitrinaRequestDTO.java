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
public class AddImagenVitrinaRequestDTO extends CriterioBusquedaGenericoSessionDTO{

	@JsonProperty("imgWeb")
    private String imgWeb;

    @JsonProperty("imgMobile")
    private String imgMobile;

    @JsonProperty("linkUrl")
    private String linkUrl;

    @JsonProperty("target")
    private String target;

    @JsonProperty("fechaInicio")
    private LocalDateTime fechaInicio;

    @JsonProperty("fechaTermino")
    private LocalDateTime fechaTermino;

    @JsonProperty("estado")
    private String estado;

    @JsonProperty("idVitrina")
    private Long idVitrina;

    @JsonProperty("nombre")
    private String nombre;

    @JsonProperty("timer")
    private Boolean timer;

    @JsonProperty("Web_V")
    private Long Web_V;

    @JsonProperty("Mob_V")
    private Long Mob_V;
}
