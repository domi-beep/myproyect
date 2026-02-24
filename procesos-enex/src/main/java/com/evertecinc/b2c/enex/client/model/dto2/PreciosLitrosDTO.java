package com.evertecinc.b2c.enex.client.model.dto2;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class PreciosLitrosDTO {
    private Long idPriceLiter;
    private String productCode;
    private BigDecimal price;
    private LocalDateTime date;
    private String user;
    private String nameProduct;
    
    
	public PreciosLitrosDTO(Long idPriceLiter, String productCode, BigDecimal price, LocalDateTime date, String user,
			String nameProduct) {
		super();
		this.idPriceLiter = idPriceLiter;
		this.productCode = productCode;
		this.price = price;
		this.date = date;
		this.user = user;
		this.nameProduct = nameProduct;
	}
	
	public PreciosLitrosDTO() {}
}
