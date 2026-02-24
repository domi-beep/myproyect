package com.evertecinc.b2c.enex.client.model.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "card_movement", schema = "public")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class CardMovement {

	@Id
	@Column(name = "id_mov")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idMov;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_card", nullable = false)
	private Card card;

	@Column(name = "date_in")
	private LocalDateTime dateIn;

	@Column(name = "movement")
	private String movement;

	@Column(name = "plate")
	private String plate;

	@Column(name = "monto")
	private BigDecimal monto;

	@Column(name = "username")
	private String username;

	@Column(name = "station_code")
	private String stationCode;

	@Column(name = "pay_type")
	private String payType;

	@Column(name = "product_code")
	private String productCode;

	@Column(name = "unitprice")
	private BigDecimal unitPrice;

	@Column(name = "quantity")
	private BigDecimal quantity;

	@Column(name = "clientidorpak")
	private String clientIdOrpak;

	@Column(name = "trx")
	private String trx;

	@Column(name = "driverrut")
	private String driverRut;

	@Column(name = "odometer")
	private String odometer;

	@Column(name = "documentnumber")
	private String documentNumber;

	@Column(name = "performance")
	private BigDecimal performance;

	@Column(name = "dp")
	private Byte dp;

	@Column(name = "ct")
	private Byte ct;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "station_code", referencedColumnName = "station_code", insertable = false, updatable = false)
	private Stations station;

	@Column(name = "clientrut")
	private String clientRut;

	@Column(name = "deptid")
	private String deptId;

	@Column(name = "cardnumber")
	private String cardNumber;

	@Column(name = "productname")
	private String productName;

	@Column(name = "returncode")
	private String returnCode;

	@Column(name = "amountbalance")
	private BigDecimal amountBalance;

	@Column(name = "remainingloads")
	private BigDecimal remainingLoads;

	@Column(name = "periodtype")
	private String periodType;

	@Column(name = "remainingamount")
	private BigDecimal remainingAmount;

	@Column(name = "idref")
	private Long idRef;

	@Column(name = "boardprice")
	private BigDecimal boardPrice;
	
	@Column(name = "idDepartment")
	private Long idDepartment;
	
	@Column(name = "validacionRut")
	private Integer validacionRut;
	
	@Column(name = "fechaValidacionRut")
	private LocalDateTime fechaValidacionRut;
}