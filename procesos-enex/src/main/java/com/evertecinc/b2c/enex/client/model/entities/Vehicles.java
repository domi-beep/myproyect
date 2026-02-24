package com.evertecinc.b2c.enex.client.model.entities;

import java.math.BigDecimal;

import org.hibernate.annotations.DynamicInsert;

import com.evertecinc.b2c.enex.client.model.converters.BooleanToSmallintConverter;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Represents a vehicle in the system.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@DynamicInsert
@Entity
@Table(name = "vehicles", schema = "public")
public class Vehicles {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_vehicle")
	private Long idVehicle;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_client", referencedColumnName = "id_client")
	private Clients client;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_department", referencedColumnName = "id_department")
	private Departments department;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_code", referencedColumnName = "product_code")
	private Products product;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "vehicle_type_code", referencedColumnName = "vehicle_type_code")
	private VehicleTypes vehicleType;

	@Column(name = "plate")
	private String plate;

	@Column(name = "document_type")
	private String documentType;

	@Column(name = "vehicle_status")
	private String vehicleStatus;

	@Column(name = "nickname")
	private String nickname;

	@Column(name = "warning_stock_channel")
	private String warningStockChannel;

	@Column(name = "warning_stock")
	private BigDecimal warningStock;

	@Column(name = "warning_stock_celular")
	private String warningStockCelular;

	@Column(name = "warning_stock_email")
	private String warningStockEmail;

	@Column(name = "warning_load_channel")
	private String warningLoadChannel;

	@Column(name = "warning_load_celular")
	private String warningLoadCelular;

	@Column(name = "warning_load_email")
	private String warningLoadEmail;

	@Column(name = "warning_load_fail_channel")
	private String warningLoadFailChannel;

	@Column(name = "warning_load_fail_celular")
	private String warningLoadFailCelular;

	@Column(name = "warning_load_fail_email")
	private String warningLoadFailEmail;

	@Column(name = "ct")
	private Integer controlTotal;

	@Column(name = "datapass")
	private Integer datapass;

	@Column(name = "datachip")
	@Convert(converter = BooleanToSmallintConverter.class)
	private Boolean datachip;

	@Column(name = "gps")
	private String gps;

}