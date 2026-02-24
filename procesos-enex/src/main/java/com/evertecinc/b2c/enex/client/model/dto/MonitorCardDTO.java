package com.evertecinc.b2c.enex.client.model.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MonitorCardDTO implements Serializable{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = -4575585204618884304L;

	/**
     * Id Tarjeta
     */
    private Long idCard;
    
    /**
     * Id departamento
     */
    private Long idDepartment;
    
    /**
     * Nombre de departamento
     */
    private String nameDepto;
    
    /**
     * Tipo de restricción
     */
    private String restrictionType;
    
    /**
     * Manejo de saldo
     * C: Tarjeta
     * D: Departamento
     */
    private String typeBalance;
    
    /**
     * Id vehiculo
     */
    private Long idVehicle;
    
    /*
     * A: activo; I:inactico; E:eliminado
     */
    private String vehicleStatus;
    
    /**
     * Número de tarjeta
     */
    private String numTarjeta;
    
    /**
     * Saldo tarjeta
     */
    private BigDecimal saldoTarjeta;
    
    /**
     * Patente del vehiculo
     */
    private String patente;
    
    /**
     * Nombre de usuario
     */
    private String username;
    
    /**
     * Estado tarjeta
     */
    private String estadoTarjeta;
    
    /**
     * Codigo combustible
     */
    private String codigoProducto;
    
    /**
     * Nombre combustible usado por vehiculo.
     */
    private String combustible;
    
    /**
     * Tipo de documento: Boleta, Factura
     */
    private String tipoDocumento;
    
    /**
     * Tipo de vehiculo
     */
    private String tipoVehiculo;
    
    /**
     * Cantidad de registros
     */
    private Integer cantidad;
    
    /**
     * Indica el estado se la solicitud de numero de tarjeta para el vehiculo
     */
    private String reqCardStatus;
    
    /**
     * Estado del departamento al que pertenece esta tarjeta
     */
    private String departmentStatus;
    
    private Boolean reqCardReprint;
    
    private String version;
    
    private Long restrDailyMaxQuantity;
    
    private Long remainingTransactionLoad;
    
    private BigDecimal remainingPeriodAmount;
    
    private Boolean controlTotal;
    
    private Boolean datapass;
    
    private BigDecimal litros;
    
    private String posicion;
}