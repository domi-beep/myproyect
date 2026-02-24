package com.evertecinc.b2c.enex.client.model.dto2.requests;

import com.evertecinc.b2c.enex.client.model.dto2.criterio.CriterioBusquedaGenericoSessionDTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AddBannerRequestDTO extends CriterioBusquedaGenericoSessionDTO {

	private String codBanner;
	private String codigoPortal;
	private String nombreBanner;
	private String estadoBanner;

}
