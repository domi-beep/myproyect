package com.evertecinc.b2c.enex.client.model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "mp_bbci_tbanc", schema = "public")
public class MpBbciTbanc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_order", nullable = false)
    private Integer idOrder;

    @Column(name = "estado", nullable = false, length = 3)
    private String estado;

    @Column(name = "fecha_trx", length = 8)
    private String fechaTrx;

    @Column(name = "monto", nullable = false)
    private Long monto;
}
