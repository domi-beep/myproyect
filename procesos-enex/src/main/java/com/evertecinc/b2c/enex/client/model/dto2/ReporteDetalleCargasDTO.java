package com.evertecinc.b2c.enex.client.model.dto2;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.evertecinc.b2c.enex.client.model.dto2.criterio.CriterioBusquedaGenericoDTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ReporteDetalleCargasDTO extends CriterioBusquedaGenericoDTO{

    private String nameDpto;
    private String idEstacion;
    private String rutCliente;
    private String idCliente;
    private String direccion;
    private String comuna;
    private String region;
    private LocalDateTime fecha;
    private String fechaSimple;
    private String hora;
    private String trx;
    private String tarjeta;
    private String patente;
    private String producto;
    private String codigoProducto;
    private String tipoCliente;
    private BigDecimal unitario;
    private BigDecimal litros;
    private BigDecimal precioTotal;
    private String driverRut;
    private BigDecimal odometer;
    private String documentNumber;
    private BigDecimal performance;
    private String documentType;

    
    public ReporteDetalleCargasDTO(
        String nameDpto,
        String idEstacion,
        String rutCliente,
        String idCliente,
        String direccion,
        String comuna,
        String region,
        LocalDateTime fecha,
        String fechaSimple,
        String hora,
        String trx,
        String tarjeta,
        String patente,
        String producto,
        String codigoProducto,
        String tipoCliente,
        BigDecimal unitario,
        BigDecimal litros,
        BigDecimal precioTotal,
        String driverRut,
        BigDecimal odometer,
        String documentNumber,
        BigDecimal performance,
        String documentType
    ) {
        this.nameDpto = nameDpto;
        this.idEstacion = idEstacion;
        this.rutCliente = rutCliente;
        this.idCliente = idCliente;
        this.direccion = direccion;
        this.comuna = comuna;
        this.region = region;
        this.fecha = fecha;
        this.fechaSimple = fechaSimple;
        this.hora = hora;
        this.trx = trx;
        this.tarjeta = tarjeta;
        this.patente = patente;
        this.producto = producto;
        this.codigoProducto = codigoProducto;
        this.tipoCliente = tipoCliente;
        this.unitario = unitario;
        this.litros = litros;
        this.precioTotal = precioTotal;
        this.driverRut = driverRut;
        this.odometer = odometer;
        this.documentNumber = documentNumber;
        this.performance = performance;
        this.documentType = documentType;
    }
}

