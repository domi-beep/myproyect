package com.evertecinc.b2c.enex.client.model.entities;

import org.hibernate.annotations.DynamicInsert;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@DynamicInsert
@Table(name = "banners")
public class Banner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idbanner")
    private Long idBanner;

    @Column(name = "idgrupobanner")
    private Integer idGrupoBanner;

    @Column(name = "codigo", length = 50)
    private String codigo;

    @Column(name = "orden", nullable = false)
    private Integer orden;

    @Column(name = "estado", length = 1, nullable = false)
    private String estado;

    @Column(name = "borrado", nullable = false)
    private Short borrado;

    @Column(name = "nombre", length = 500)
    private String nombre;

    @Column(name = "codigoportal", length = 5, nullable = false)
    private String codigoPortal;
}
