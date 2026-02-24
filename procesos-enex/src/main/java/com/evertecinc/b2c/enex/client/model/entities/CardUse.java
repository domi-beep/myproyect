package com.evertecinc.b2c.enex.client.model.entities;

import java.math.BigDecimal;
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
@Table(name = "card_use", schema = "public")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class CardUse {

	@Id
	@Column(name = "id_card_use")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idCardUse;
	
	@Column(name = "station")
	private String station;
	
	@Column(name = "stationrut")
	private String stationRut;
	
	@Column(name = "trx")
	private String trx;

	@Column(name = "datetrx")
	private LocalDateTime datetrx;

	@Column(name = "clientid")
	private String clientId;

	@Column(name = "clientrut")
	private String clientRut;

	@Column(name = "deptid")
	private String deptId;

	@Column(name = "cardnumber")
	private String cardNumber;

	@Column(name = "plate")
	private String plate;

	@Column(name = "productcode")
	private String productCode;

	@Column(name = "productname")
	private String productName;

	@Column(name = "unitprice")
	private BigDecimal unitPrice;

	@Column(name = "quantity")
	private BigDecimal quantity;

	@Column(name = "totalprice")
	private BigDecimal totalPrice;

	@Column(name = "returncode")
	private String returnCode;

	@Column(name = "driverrut")
	private String driverRut;

	@Column(name = "odometer")
	private String odometer;

	@Column(name = "amountbalance")
	private BigDecimal amountBalance;

	@Column(name = "remainingloads")
	private BigDecimal remainingLoads;

	@Column(name = "periodtype")
	private String periodType;

	@Column(name = "remainingamount")
	private BigDecimal remainingAmount;

	@Column(name = "documentnumber")
	private String documentNumber;
	
	@Column(name = "dp")
	private Byte dp;

	@Column(name = "ct")
	private Byte ct;

	@Column(name = "boardprice")
	private BigDecimal boardPrice;
}