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
@Table(name = "cards", schema = "public")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_card", nullable = false)
    private Long idCard;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_client")
    private Clients client;

    @Column(name = "cardnum", nullable = true)
    private String cardnum;

    @Column(name = "remaining_amount", nullable = true)
    private BigDecimal remainingAmount;

    @Column(name = "remaining_trx_load", nullable = true)
    private Long remainingTrxLoad;

    @Column(name = "remaining_period_amount", nullable = true)
    private BigDecimal remainingPeriodAmount;

    @Column(name = "crtdate", nullable = true)
    private LocalDateTime crtdate;

    @Column(name = "upddate", nullable = true)
    private LocalDateTime upddate;

    @Column(name = "card_status", nullable = true)
    private String cardStatus;

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

    @Column(name = "restr_hini", nullable = true)
    private Integer restrHini;

    @Column(name = "restr_hend", nullable = true)
    private Integer restrHend;

    @Column(name = "restr_amount_max", nullable = true)
    private BigDecimal restrAmountMax;

    @Column(name = "restr_daily_max_loads", nullable = true)
    private Integer restrDailyMaxLoads;

    @Column(name = "restr_daily_max_quantity", nullable = true)
    private Integer restrDailyMaxQuantity;

    @Column(name = "restriction_type", nullable = true)
    private String restrictionType;

    @Column(name = "version", nullable = true)
    private String version;

    @Column(name = "reqcard_status", nullable = true)
    private String reqcardStatus;

    @Column(name = "reqcard_reprint")
    private Integer reqcardReprint;

    @Column(name = "ct_position", nullable = true)
    private String ctPosition;

}
