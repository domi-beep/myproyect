package com.evertecinc.b2c.enex.client.model.dto2.criterio;



import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CriterioBusquedaGenericoDTO {

	@JsonProperty(value = "p", access = JsonProperty.Access.WRITE_ONLY)
	private int pageNumber = 1;
	
	@JsonProperty(value = "ps", access = JsonProperty.Access.WRITE_ONLY)
	private int pageSize = 10;

	@JsonProperty(value = "paged", access = JsonProperty.Access.WRITE_ONLY)
	/**
	 * Valida si se paginara o no el resultado
	 */
	private boolean paged = true;

	@JsonProperty(value = "need_page_info", access = JsonProperty.Access.WRITE_ONLY)
	private boolean need_page_info = true;

	@JsonProperty(value = "lg", access = JsonProperty.Access.WRITE_ONLY)
	@Schema(hidden = true)
	private String lg = "es";

	@JsonProperty(value = "sort", access = JsonProperty.Access.WRITE_ONLY)
	private String sort;

}
