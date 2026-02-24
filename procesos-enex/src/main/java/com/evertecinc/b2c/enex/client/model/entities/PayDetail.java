package com.evertecinc.b2c.enex.client.model.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "pay_detail", schema = "public")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class PayDetail {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idpay_detail", nullable = false)
	private Long idPayDetail;

	@Column(name = "id_order", nullable = true)
	private Integer idOrder;

	@Column(name = "upi", nullable = true)
	private String upi;

	@Column(name = "date_ins", nullable = true)
	private LocalDateTime dateIns;
	
	@Column(name = "pay_type", nullable = true)
	private String payType;
	
	@Column(name = "pay_detailcol", nullable = true)
	private String payDetailCol;
	
	@Column(name = "status", nullable = true)
	private String status;
	
	@Column(name = "pay_detailcol1", nullable = true)
	private String payDetailCol1;
	
	@Column(name = "data_out", nullable = true)
	private String dataOut;
	
	@Column(name = "data_in", nullable = true)
	private String dataIn;
	
	@Column(name = "response", nullable = true)
	private String response;
	
	@Column(name = "transaction_code", nullable = true)
	private String transactionCode;

}
