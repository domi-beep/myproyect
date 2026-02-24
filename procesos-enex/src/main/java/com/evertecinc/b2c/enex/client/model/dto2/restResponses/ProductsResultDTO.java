package com.evertecinc.b2c.enex.client.model.dto2.restResponses;

import java.util.List;
import java.util.Optional;

import com.evertecinc.b2c.enex.client.model.dto2.ProductsDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ProductsResultDTO extends GenericResultDTO {

	@JsonProperty("listProducts")
	private Optional<List<ProductsDTO>> listProductsDTO;

	@JsonProperty("countListProductsDTO")
	private Optional<Long> countListProductsDTO;

}
