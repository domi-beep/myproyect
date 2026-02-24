package com.evertecinc.b2c.enex.client.model.dto2;

import lombok.Data;

@Data
public class SearchHistorialPagoDTO {
	
	private String fechaInicio;
	private String fechaFin;
	private String idClienteBoleta;
	private String idClienteFactura;
	private String tabla;
	private String tablaUnion;
	private String tipoDocumento;
	private String numeroDocumento;
	private Long cuentaC;
	private Long monto;

}
