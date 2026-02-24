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
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "oc_header", schema = "public")
public class OcHeader {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_oc", nullable = false, updatable = false)
    private Long idOc;

    @Column(name = "id_client", nullable = false)
    private Long idClient;

    @Column(name = "id_user", nullable = false)
    private Long idUser;

    @Column(name = "number", length = 45)
    private String number;

    @Column(name = "date")
    private LocalDateTime date;

    @Column(name = "total", precision = 13, scale = 3)
    private BigDecimal total;

    @Column(name = "status", length = 1)
    private String status;

    @Column(name = "crtdate")
    private LocalDateTime crtDate;

    @Column(name = "id_jefezona", nullable = false)
    private Long idJefeZona;

    @Column(name = "invoice", length = 45)
    private String invoice;

    // Relaciones con claves foráneas
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_client", insertable = false, updatable = false)
    private Clients client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user", insertable = false, updatable = false)
    private Users user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_jefezona", insertable = false, updatable = false)
    private JefeZona jefeZona;

}
