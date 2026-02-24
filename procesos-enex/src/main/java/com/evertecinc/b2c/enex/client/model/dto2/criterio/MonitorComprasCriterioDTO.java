package com.evertecinc.b2c.enex.client.model.dto2.criterio;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MonitorComprasCriterioDTO extends CriterioBusquedaGenericoDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3452053348546545959L;
	
	private String 	legalName;// razon social
	private Long 	idOrder;//numero de orden
	private Date 	paydate;
	private Long 	totalOrder;
	private String 	payType;
	private String 	orderStatus;
	private Long 	idClient;
	private Date 	dateFirst;
	private Date 	dateEnd;
	private String  productCode;
	private Long    idVehicle;
	private Long    idCard;
	private String	numComprobante;
	private String 	estado;
	private String 	upi;
	
}
