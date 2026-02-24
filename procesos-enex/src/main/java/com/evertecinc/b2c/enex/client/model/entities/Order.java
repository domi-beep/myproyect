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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "orders", schema = "public")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_order", nullable = false)
    private Long idOrder;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_client", nullable = false)
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user", nullable = false)
    private User user;

    @Column(name = "total_order", precision = 13, scale = 3, nullable = false)
    private BigDecimal totalOrder;

    @Column(name = "pay_type", length = 1, nullable = false)
    private String payType;

    @Builder.Default
    @Column(name = "order_status", length = 1, nullable = false)
    private String orderStatus = "C";

    @Column(name = "paydate")
    private LocalDateTime payDate;

    @Column(name = "crtdate", nullable = false)
    private LocalDateTime createDate;

    @Column(name = "order_type", columnDefinition = "int default 0")
    private Integer orderType;
}

