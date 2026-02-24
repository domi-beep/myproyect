package com.evertecinc.b2c.enex.client.model.dto2;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SolicitudCuentaDTO {
	
    private Long idSolicitudCuenta;
    private String razonSocial;
    private String rutEmpresa;
    private String direccion;
    private String comuna;
    private String region;
    private String nombreContacto;
    private String telefono;
    private String mail;
    private String consumo;
    private LocalDateTime fechaIns;
    private String tipoCliente;
    

}
