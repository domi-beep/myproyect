package com.evertecinc.b2c.enex.client.model.dto2;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ReporteListadoVentasDTO {
    
    private Integer mes;
    private Integer anio;
    private BigDecimal monto;
    private String razonSocial;
    private String rut;
    private String producto;
    private String medioPago;
    private LocalDateTime fechaPago;

    public ReporteListadoVentasDTO(Integer mes, Integer anio, BigDecimal monto, String razonSocial, String rut,
            String producto, String medioPago, LocalDateTime fechaPago) {
        this.mes = mes;
        this.anio = anio;
        this.monto = monto;
        this.razonSocial = razonSocial;
        this.rut = rut;
        this.producto = producto;
        this.medioPago = medioPago;
        this.fechaPago = fechaPago;
    }

}
