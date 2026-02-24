package com.evertecinc.b2c.enex.client.model.dto2.jde;

import java.util.Date;

import lombok.Data;

@Data
public class HistorialPagoDTO {

	private String idDoc;
	private String numDoc;
	private String tipoDoc;
	private String dataItem;
	private String seqFactura;
	private Date fechaEmision;
	private String estado;
	private Date fechaVencimiento;
	private String monto;
	private String idComprobantePago;
	private String idTx;
	private String numPedido;
	private String cuentaDespacho;
	private String idClasifProducto;
	private String ordenCompra;
	private String idcc;
	private String montoPendiente;
	private String tipoOrigen;

}
