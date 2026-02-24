package com.evertecinc.b2c.enex.client.model.entities;

import org.hibernate.annotations.DynamicInsert;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "variables", schema = "public")
@Data
@DynamicInsert
@NoArgsConstructor
@AllArgsConstructor
public class Variables {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idvariable", nullable = false, updatable = false)
    private Long idVariable;

    @Column(name = "codigoportal", length = 3, nullable = false)
    private String codigoPortal;

    @Column(name = "nombre", length = 100, nullable = false)
    private String nombre;

    @Column(name = "valor", length = 1000, nullable = false)
    private String valor;
}
