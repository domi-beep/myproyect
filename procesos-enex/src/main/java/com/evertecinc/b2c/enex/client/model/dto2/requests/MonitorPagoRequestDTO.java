package com.evertecinc.b2c.enex.client.model.dto2.requests;

import java.util.Date;

import com.evertecinc.b2c.enex.client.model.dto2.criterio.CriterioBusquedaGenericoDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MonitorPagoRequestDTO extends CriterioBusquedaGenericoDTO{
	
	private String upi;
	private String legalName;
	private Long idOrder;
	private Date payDate;
	private Long totalOrder;
	private String payType;
	private String orderStatus;
	private Long idClient;
	private Date dateFirst;
	private Date dateEnd;
	private String  productCode;
	private Integer cantidad;
	private Long    idVehicle;
	private Long    idCard;
	private String numComprobante;
	private String estado;
	private String listaTipoElementos;

}
