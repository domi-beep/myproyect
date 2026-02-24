package com.evertecinc.b2c.enex.client.model.entities;

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

@Entity
@Table(name = "solicitud_cuenta")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SolicitudCuenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_solicitud_cuenta")
    private Long idSolicitudCuenta;

    @Column(name = "razon_social", nullable = false, length = 100)
    private String razonSocial;

    @Column(name = "rut_empresa", nullable = false, length = 45)
    private String rutEmpresa;

    @Column(name = "direccion", length = 300)
    private String direccion;

    @Column(name = "comuna", length = 45)
    private String comuna;

    @Column(name = "region", length = 80)
    private String region;

    @Column(name = "nombre_contacto", length = 150)
    private String nombreContacto;

    @Column(name = "telefono", length = 45)
    private String telefono;

    @Column(name = "mail", length = 150)
    private String mail;

    @Column(name = "consumo", length = 45)
    private String consumo;

    @Column(name = "fecha_ins")
    private LocalDateTime fechaIns;

    @Column(name = "tipo_cliente", length = 10)
    private String tipoCliente;
}
