package com.evertecinc.b2c.enex.client.model.entities;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "transbank", schema = "public")
public class Transbank {

	@Id
	@Column(name = "id_order", nullable = false)
	private Long idOrder;
	
	@Column(name = "authorization_code", nullable = true, length = 80)
	private String authorizationCode;
	
	@Column(name = "payment_type", nullable = true, length = 20)
	private String paymentType;
	
	@Column(name = "final_card_number", nullable = true)
	private Integer finalCardNumber;
	
	@Column(name = "number_shares", nullable = true)
	private Integer numberShares;
	
	@Column(name = "amount", nullable = true)
	private BigDecimal amount;
	
	@Column(name = "transaction_date", nullable = true)
	private Integer transactionDate;
	
	@Column(name = "transaction_hours", nullable = true)
	private Integer transactionHours;
	
	@Column(name = "id_transaction", nullable = true)
	private Long idTransaction;
	
	@Column(name = "return_code", nullable = true, length = 20)
	private String returnCode;
	
	@Column(name = "token", nullable = true, length = 1000)
	private String token;
	
}
