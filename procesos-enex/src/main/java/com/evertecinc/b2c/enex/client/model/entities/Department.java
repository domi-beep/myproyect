package com.evertecinc.b2c.enex.client.model.entities;

import java.math.BigDecimal;

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

@Entity
@Table(name = "departments", schema = "public")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_department")
    private Long idDepartment;

    @Column(name = "id_client", nullable = false)
    private Long idClient;

    @Column(name = "name")
    private String name;

    @Column(name = "restr_type")
    private String restrType;

    @Column(name = "restr_l")
    private Boolean restrL;

    @Column(name = "restr_m")
    private Boolean restrM;

    @Column(name = "restr_x")
    private Boolean restrX;

    @Column(name = "restr_j")
    private Boolean restrJ;

    @Column(name = "restr_v")
    private Boolean restrV;

    @Column(name = "restr_s")
    private Boolean restrS;

    @Column(name = "restr_d")
    private Boolean restrD;

    @Column(name = "restr_hinit")
    private Integer restrHinit;

    @Column(name = "restr_hend")
    private Integer restrHend;

    @Column(name = "restr_amount_max", precision = 13, scale = 3)
    private BigDecimal restrAmountMax;

    @Column(name = "restr_daily_max_loads")
    private Integer restrDailyMaxLoads;

    @Column(name = "restr_daily_max_quantity")
    private Integer restrDailyMaxQuantity;

    @Column(name = "department_status")
    private String departmentStatus;

    @Column(name = "warning_stock_channel")
    private String warningStockChannel;

    @Column(name = "warning_stock", precision = 13, scale = 3)
    private BigDecimal warningStock;

    @Column(name = "warning_stock_celular")
    private String warningStockCelular;

    @Column(name = "warning_stock_email")
    private String warningStockMail;

    @Column(name = "warning_load_channel")
    private String warningLoadChannel;

    @Column(name = "warning_load_celular")
    private String warningLoadCelular;

    @Column(name = "warning_load_email")
    private String warningLoadMail;

    @Column(name = "warning_load_fail_channel")
    private String warningLoadFailChannel;

    @Column(name = "warning_load_fail_celular")
    private String warningLoadFailCelular;

    @Column(name = "warning_load_fail_email")
    private String warningLoadFailEmail;

    @Column(name = "type_balance")
    private String typeBalance;

    @Column(name = "codeorpakticket")
    private String codeOrpakTicket;

    @Column(name = "codeorpakinvoice")
    private String codeOrpakInvoice;

    @Column(name = "codeorpakclient")
    private String codeOrpakClient;

    @Column(name = "gps")
    private String gps;

    @Column(name = "idpadre")
    private Long idPadre;
}