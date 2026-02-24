package com.evertecinc.b2c.enex.client.model.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "price_liters", schema = "public")
public class PriceLiters {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_price_liter", nullable = false)
	private Long idPriceLiter;
	@Column(name = "product_code", nullable = false, length = 10)
	private String productCode;
	@Column(name = "price", precision = 13, scale = 3)
	private BigDecimal price;
	@Column(name = "date")
	private LocalDateTime date;
	
	@Column(name = "\"user\"")
	private String user;


}