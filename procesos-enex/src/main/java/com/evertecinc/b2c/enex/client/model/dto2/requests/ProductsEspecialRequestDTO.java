package com.evertecinc.b2c.enex.client.model.dto2.requests;

import java.util.List;
import java.util.Optional;

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
public class ProductsEspecialRequestDTO extends CriterioBusquedaGenericoDTO {

	@JsonProperty(value = "listProductCode", access = JsonProperty.Access.WRITE_ONLY)
	private Optional<List<String>> listProductCode = Optional.empty();

	@JsonProperty(value = "clientType", access = JsonProperty.Access.WRITE_ONLY)
	private String clientType;

	@JsonProperty(value = "upi", access = JsonProperty.Access.WRITE_ONLY)
	private String upi;
	
//	@JsonProperty(value = "productCode", access = JsonProperty.Access.WRITE_ONLY)
//	private String productCode;
//
//	@JsonProperty(value = "name", access = JsonProperty.Access.WRITE_ONLY)
//	private String name;
//
//	@JsonProperty(value = "productStatus", access = JsonProperty.Access.WRITE_ONLY)
//	private String productStatus;
	
}
