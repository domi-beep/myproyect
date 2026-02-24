package com.evertecinc.b2c.enex.client.model.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "jde_informe_compras")
@Data
public class JdeInformeCompras {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_infcomp", nullable = false)
	private Long idInfcomp;

	@ManyToOne
    @JoinColumn(name = "id_client", referencedColumnName = "id_client")
    private Clients client;

	@Column(name = "patente", length = 45)
	private String patente;

	@Column(name = "fecha")
	private LocalDateTime fecha;

	@Column(name = "estacion", length = 45)
	private String estacion;

	@Column(name = "lugar", length = 45)
	private String lugar;

	@Column(name = "guia_despacho", length = 45)
	private String guiaDespacho;

	@Column(name = "cantidad_litros", precision = 13, scale = 3)
	private BigDecimal cantidadLitros;

	@Column(name = "odometro", precision = 13, scale = 3)
	private BigDecimal odometro;

	@Column(name = "kms_litro", precision = 13, scale = 3)
	private BigDecimal kmsLitro;

	@Column(name = "precio_venta", precision = 13, scale = 3)
	private BigDecimal precioVenta;

	@Column(name = "total_guia", precision = 13, scale = 3)
	private BigDecimal totalGuia;

	@Column(name = "precio_especial", precision = 13, scale = 3)
	private BigDecimal precioEspecial;

	@Column(name = "monto_descuento", precision = 13, scale = 3)
	private BigDecimal montoDescuento;

	@Column(name = "delta_precio", precision = 13, scale = 3)
	private BigDecimal deltaPrecio;

	@Column(name = "numero_factura", length = 45)
	private String numeroFactura;

	@Column(name = "producto", length = 45)
	private String producto;

	@Column(name = "tipo_documento", length = 45)
	private String tipoDocumento;

	@Column(name = "calculo_odometro", precision = 13, scale = 3)
	private BigDecimal calculoOdometro;

	@Column(name = "precio_orpak", precision = 13, scale = 3)
	private BigDecimal precioOrpak;

	@Column(name = "codigo_cliente", length = 15)
	private String codigoCliente;

	@Column(name = "flag_codigo_cliente", length = 1, columnDefinition = "character varying DEFAULT 'P'")
	private String flagCodigoCliente;

	@Column(name = "codigocliente", length = 15)
	private String codigocliente;

}