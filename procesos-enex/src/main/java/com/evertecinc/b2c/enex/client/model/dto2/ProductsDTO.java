package com.evertecinc.b2c.enex.client.model.dto2;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductsDTO {

	private String productCode;
	private String name;
	private String productStatus;

}