package com.evertecinc.b2c.enex.client.model.entities;

import java.math.BigDecimal;

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
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "oc_item", schema = "public")
public class OcItem {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_item", nullable = false, updatable = false)
    private Long idItem;

    @Column(name = "id_oc", nullable = false)
    private Long idOc;

    @Column(name = "id_department", nullable = false)
    private Long idDepartment;

    @Column(name = "product_code", length = 10, nullable = false)
    private String productCode;

    @Column(name = "amount", precision = 13, scale = 3)
    private BigDecimal amount;

    @Column(name = "doctype", length = 1)
    private String docType;

    // Relaciones con claves foráneas
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_oc", insertable = false, updatable = false)
    private OcHeader ocHeader;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_department", insertable = false, updatable = false)
    private Departments department;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_code", referencedColumnName = "product_code", insertable = false, updatable = false)
    private Products product;

}
