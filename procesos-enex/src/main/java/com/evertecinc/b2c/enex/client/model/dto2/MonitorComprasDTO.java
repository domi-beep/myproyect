package com.evertecinc.b2c.enex.client.model.dto2;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.evertecinc.b2c.enex.client.model.dto2.criterio.CriterioBusquedaGenericoDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MonitorComprasDTO extends CriterioBusquedaGenericoDTO {
	
	@JsonProperty("upi")
	private String upi;
	
	@JsonProperty("legalName")
	private String legalName;
	
	@JsonProperty("idOrder")
	private Long idOrder;
	
	@JsonProperty("payDate")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime payDate;
	
	@JsonProperty("totalOrder")
	private BigDecimal totalOrder;
	
	@JsonProperty("payType")
	private String payType;
	
	@JsonProperty("payTypeDescription")
	private String payTypeDescription;
	
	@JsonProperty("orderStatus")
	private String orderStatus;
	
	@JsonProperty("idClient")
	private Long idClient;
	
	@JsonProperty("numComprobante")
	private String numComprobante;
	
	@JsonProperty("documentType")
	private String documentType;
	
	@JsonProperty("productName")
	private String productName;
	
	@JsonProperty("respuesta")
	private String respuesta;
	
	@JsonProperty("crtDate")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime crtDate;
	
	@JsonProperty("etapaPago")
	private String etapaPago;
	
	@JsonProperty("bancoDeposito")
	private String bancoDeposito;
	
	//filtro fecha
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonProperty(value="dateIn", access = JsonProperty.Access.WRITE_ONLY)
	@JsonSetter(nulls = Nulls.SKIP)
	private LocalDateTime dateIn;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonProperty(value="dateEnd", access = JsonProperty.Access.WRITE_ONLY)
	@JsonSetter(nulls = Nulls.SKIP)
	private LocalDateTime dateEnd;
	
	@JsonProperty("totalRegistros")
	private Long totalRegistros;
	
	public MonitorComprasDTO() {super();}
	
	public MonitorComprasDTO(String upi, String legalName, Long idOrder, LocalDateTime payDate, BigDecimal totalOrder,
			String payType,String payTypeDescription, String orderStatus, Long idClient, String numComprobante,
			String documentType, String productName, String respuesta, LocalDateTime crtDate,  String etapaPago, String bancoDeposito) {

		super();
		this.upi = upi;
		this.legalName = legalName;
		this.idOrder = idOrder;
		this.payDate = payDate;
		this.totalOrder = totalOrder;
		this.payType = payType;
		this.payTypeDescription = payTypeDescription;
		this.orderStatus = orderStatus;
		this.idClient = idClient;
		this.numComprobante = numComprobante;
		this.documentType = documentType;
		this.productName = productName;
		this.respuesta = respuesta;
		this.crtDate = crtDate;
		this.etapaPago = etapaPago;
		this.bancoDeposito = bancoDeposito;

	}
	
	public MonitorComprasDTO(String upi, 
			String legalName, 
			Long idOrder, 
			LocalDateTime payDate, 
			BigDecimal totalOrder,
			String payType, 
			Long idClient, 
			String numComprobante,
			String respuesta, 
			LocalDateTime crtDate, 
			String bancoDeposito, 
			String orderStatus) {

		super();
		this.upi = upi;
		this.legalName = legalName;
		this.idOrder = idOrder;
		this.payDate = payDate;
		this.totalOrder = totalOrder;
		this.payType = payType;
		this.idClient = idClient;
		this.numComprobante = numComprobante;
		this.respuesta = respuesta;
		this.crtDate = crtDate;
		this.orderStatus = orderStatus;
		this.bancoDeposito = bancoDeposito;

	}
	
	public MonitorComprasDTO(Long totalRegistros) {
		super();
		this.totalRegistros = totalRegistros;
	}

	
	/* getMonitorComprasByCriterio */
	public MonitorComprasDTO(String upi, String legalName, Long idOrder, LocalDateTime payDate, BigDecimal totalOrder,
			String payType, String orderStatus, Long idClient, String numComprobante, String respuesta,
			LocalDateTime crtDate, String etapaPago) {
		super();
		this.upi = upi;
		this.legalName = legalName;
		this.idOrder = idOrder;
		this.payDate = payDate;
		this.totalOrder = totalOrder;
		this.payType = payType;
		this.orderStatus = orderStatus;
		this.idClient = idClient;
		this.numComprobante = numComprobante;
		this.respuesta = respuesta;
		this.crtDate = crtDate;
		this.etapaPago = etapaPago;
	}


	
	

}
