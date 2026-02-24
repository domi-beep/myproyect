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
import lombok.Data;

@Data
@Entity
@DynamicInsert
@Table(name = "shopcart", schema = "public")
public class ShopCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_shopcart")
    private Long idShopCart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_client", nullable = false)
    private Clients client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user", nullable = false)
    private Users user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_code")
    private Products product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_card")
    private Card card;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_department")
    private Departments department;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "document_type", length = 1)
    private String documentType;

    @Column(name = "id_documento", length = 45)
    private String idDocumento;

    @Column(name = "numero_documento", length = 45)
    private String numeroDocumento;

    @Column(name = "tipo_documento", length = 45)
    private String tipoDocumento;
}

