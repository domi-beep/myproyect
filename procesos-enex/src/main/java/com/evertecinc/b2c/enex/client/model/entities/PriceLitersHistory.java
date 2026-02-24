package com.evertecinc.b2c.enex.client.model.entities;

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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "price_liters_history", schema = "public")
public class PriceLitersHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_history", nullable = false)
    private Long idHistory;

    @Column(name = "id_price_liter", nullable = false)
    private Long idPriceLiter;

    @Column(name = "username", length = 45)
    private String username;

    @Column(name = "date")
    private LocalDateTime date;

    @Column(name = "action", length = 255)
    private String action;

    @Column(name = "action_type", length = 1)
    private String actionType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_price_liter", insertable = false, updatable = false)
    private PriceLiters priceLiters;
}

