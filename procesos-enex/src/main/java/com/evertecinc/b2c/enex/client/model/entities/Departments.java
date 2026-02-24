package com.evertecinc.b2c.enex.client.model.entities;

import java.math.BigDecimal;

import org.hibernate.annotations.DynamicInsert;

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

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@DynamicInsert
@Table(name = "departments", schema = "public")
public class Departments {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_department", nullable = false)
	private Long idDepartment;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_client")
    private Clients client;

	@Column(name = "name", nullable = true)
	private String name;

	@Column(name = "restr_type", nullable = true)
	private String restrType;

	@Column(name = "restr_l", nullable = true)
	private Integer restrL;

	@Column(name = "restr_m", nullable = true)
	private Integer restrM;

	@Column(name = "restr_x", nullable = true)
	private Integer restrX;

	@Column(name = "restr_j", nullable = true)
	private Integer restrJ;

	@Column(name = "restr_v", nullable = true)
	private Integer restrV;

	@Column(name = "restr_s", nullable = true)
	private Integer restrS;

	@Column(name = "restr_d", nullable = true)
	private Integer restrD;

	@Column(name = "restr_hinit", nullable = true)
	private Integer restrHinit;

	@Column(name = "restr_hend", nullable = true)
	private Integer restrHend;

	@Column(name = "restr_amount_max", nullable = true)
	private BigDecimal restrAmountMax;

	@Column(name = "restr_daily_max_loads", nullable = true)
	private Integer restrDailyMaxLoads;

	@Column(name = "restr_daily_max_quantity", nullable = true)
	private Integer restrDailyMaxQuantity;

	@Column(name = "department_status", nullable = true)
	private String departmentStatus;

	@Column(name = "warning_stock_channel", nullable = true)
	private String warningStockChannel;

	@Column(name = "warning_stock", nullable = true)
	private BigDecimal warningStock;

	@Column(name = "warning_stock_celular", nullable = true)
	private String warningStockCelular;

	@Column(name = "warning_stock_email", nullable = true)
	private String warningStockEmail;

	@Column(name = "warning_load_channel", nullable = true)
	private String warningLoadChannel;

	@Column(name = "warning_load_celular", nullable = true)
	private String warningLoadCelular;

	@Column(name = "warning_load_email", nullable = true)
	private String warningLoadEmail;

	@Column(name = "warning_load_fail_channel", nullable = true)
	private String warningLoadFailChannel;

	@Column(name = "warning_load_fail_celular", nullable = true)
	private String warningLoadFailCelular;

	@Column(name = "warning_load_fail_email", nullable = true)
	private String warningLoadFailEmail;

	@Column(name = "type_balance", nullable = true)
	private String typeBalance;

	@Column(name = "codeorpakinvoice", nullable = true)
	private String codeorpakinvoice;

	@Column(name = "codeorpakticket", nullable = true)
	private String codeorpakticket;

	@Column(name = "codeorpakclient", nullable = true)
	private String codeorpakclient;

	@Column(name = "gps", nullable = true)
	private String gps;

	@Column(name = "idpadre", nullable = true)
	private Integer idPadre;

}
