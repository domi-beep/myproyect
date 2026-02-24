package com.evertecinc.b2c.enex.client.model.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cards")
public class Cards implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 8098621355223898974L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_card")
    private Long idCard;

    @Column(name = "id_client", nullable = false)
    private Integer idClient;

    @Column(name = "cardnum", length = 45)
    private String cardNum;

    @Column(name = "remaining_amount", precision = 13, scale = 3)
    private BigDecimal remainingAmount;

    @Column(name = "remaining_trx_load", columnDefinition = "int4 default 0")
    private Integer remainingTrxLoad;

    @Column(name = "remaining_period_amount", precision = 13, scale = 3)
    private BigDecimal remainingPeriodAmount;

    @Column(name = "crtdate")
    private LocalDateTime crtDate;

    @Column(name = "upddate")
    private LocalDateTime updDate;

    @Column(name = "card_status", length = 1, columnDefinition = "varchar(1) default 'A'")
    private String cardStatus;

    @Column(name = "restr_type", length = 1, columnDefinition = "bpchar(1) default 'D'")
    private String restrType;

    @Column(name = "restr_l", columnDefinition = "int2 default 1")
    private Short restrL;

    @Column(name = "restr_m", columnDefinition = "int2 default 1")
    private Short restrM;

    @Column(name = "restr_x", columnDefinition = "int2 default 1")
    private Short restrX;

    @Column(name = "restr_j", columnDefinition = "int2 default 1")
    private Short restrJ;

    @Column(name = "restr_v", columnDefinition = "int2 default 1")
    private Short restrV;

    @Column(name = "restr_s", columnDefinition = "int2 default 1")
    private Short restrS;

    @Column(name = "restr_d", columnDefinition = "int2 default 1")
    private Short restrD;

    @Column(name = "restr_hini", columnDefinition = "int2 default 0")
    private Short restrHini;

    @Column(name = "restr_hend", columnDefinition = "int2 default 24")
    private Short restrHend;

    @Column(name = "restr_amount_max", precision = 13, scale = 3)
    private BigDecimal restrAmountMax;

    @Column(name = "restr_daily_max_loads", columnDefinition = "int4 default 0")
    private Integer restrDailyMaxLoads;

    @Column(name = "restr_daily_max_quantity", columnDefinition = "int4 default 0")
    private Integer restrDailyMaxQuantity;

    @Column(name = "restriction_type", length = 1, columnDefinition = "varchar(1) default 'D'")
    private String restrictionType;

    @Column(name = "version", length = 45)
    private String version;

    @Column(name = "reqcard_status", columnDefinition = "bpchar(1) default 'N'")
    private String reqCardStatus;

    @Column(name = "reqcard_reprint", columnDefinition = "int2 default 0")
    private Short reqCardReprint;

    @Column(name = "ct_position", length = 10)
    private String ctPosition;
}