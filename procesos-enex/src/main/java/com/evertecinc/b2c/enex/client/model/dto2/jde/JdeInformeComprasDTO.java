package com.evertecinc.b2c.enex.client.model.dto2.jde;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@lombok.Data
public class JdeInformeComprasDTO {
	
	private Long idInfcomp;
	private Long idClient;
	private String patente;
	private LocalDateTime fecha;
	private String fechaSt;
	private String hora;
	private String estacion;
	private String lugar;
	private String guiaDespacho;
	private BigDecimal cantidadLitros;
	private BigDecimal odometro;
	private BigDecimal kmsLitro;
	private BigDecimal precioVenta;
	private BigDecimal totalGuia;
	private BigDecimal precioEspecial;
	private BigDecimal montoDescuento;
	private BigDecimal deltaPrecio;
	private String numeroFactura;
	private String producto;
	private String tipoDocumento;
	private BigDecimal calculoOdometro;
	private BigDecimal precioOrpak;
	private String rutCliente;

}
