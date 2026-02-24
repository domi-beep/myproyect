package com.evertecinc.b2c.enex.client.model.dto2;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.evertecinc.b2c.enex.client.model.dto2.criterio.CriterioBusquedaGenericoDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MonitorComprasExpDetalleOrdenes extends CriterioBusquedaGenericoDTO {
	
	@JsonProperty("idOrder")
	private Long idOrder;
	
	@JsonProperty("idClient")
	private Long idClient;
	
	@JsonProperty("payDate")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime payDate;
	
	@JsonProperty("upi")
	private String upi;
	
	@JsonProperty("legalName")
	private String legalName;
	
	@JsonProperty("totalOrder")
	private BigDecimal totalOrder;
	
	@JsonProperty("payType")
	private String payType;

	@JsonProperty("nickname")
	private String nickname;
	
	@JsonProperty("cardnum")
	private String cardnum;
	
	@JsonProperty("numComprobante")
	private String numComprobante;
	
	@JsonProperty("tipoCompra")
	private String tipoCompra;
	
	@JsonProperty("nombreDepto")
	private String nombreDepto;
	
	@JsonProperty("producto")
	private String producto;
	
	@JsonProperty("montoPorItem")
	private String montoPorItem;
	
	@JsonProperty("tipoDocumento")
	private String tipoDocumento;
	
	@JsonProperty("respuesta")
	private String respuesta;
	
	@JsonProperty("bancoDeposito")
	private String bancoDeposito;

	public MonitorComprasExpDetalleOrdenes(Long idOrder, Long idClient, LocalDateTime payDate, String upi,
			String legalName, BigDecimal totalOrder, String payType, String nickname,
			String numComprobante, String tipoCompra, String nombreDepto, String producto, String montoPorItem,
			String tipoDocumento, String respuesta, String bancoDeposito, String cardnum) {
		super();
		this.idOrder = idOrder;
		this.idClient = idClient;
		this.payDate = payDate;
		this.upi = upi;
		this.legalName = legalName;
		this.totalOrder = totalOrder;
		this.payType = payType;
		this.nickname = nickname;
		this.cardnum = cardnum;
		this.numComprobante = numComprobante;
		this.tipoCompra = tipoCompra;
		this.nombreDepto = nombreDepto;
		this.producto = producto;
		this.montoPorItem = montoPorItem;
		this.tipoDocumento = tipoDocumento;
		this.respuesta = respuesta;
		this.bancoDeposito = bancoDeposito;
	}
	
	
	

}
