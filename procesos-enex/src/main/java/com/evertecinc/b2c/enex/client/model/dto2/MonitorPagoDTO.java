package com.evertecinc.b2c.enex.client.model.dto2;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

import com.evertecinc.b2c.enex.client.model.dto2.restResponses.GenericResultDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class MonitorPagoDTO extends GenericResultDTO{
	
	@JsonProperty("upi")
    private String upi;

    @JsonProperty("legalName")
    private String legalName;

    @JsonProperty("idOrder")
    private Long idOrder;

    @JsonProperty("paydate")
    private Date payDate;

    @JsonProperty("totalOrder")
    private BigDecimal totalOrder;

    @JsonProperty("payType")
    private String payType;

    @JsonProperty("orderStatus")
    private String orderStatus;

    @JsonProperty("idClient")
    private Integer idClient;

    @JsonProperty("cantidad")
    private Integer cantidad;

    @JsonProperty("numComprobante")
    private String numComprobante;

    @JsonProperty("documentType")
    private String documentType;

    @JsonProperty("productName")
    private String productName;

    @JsonProperty("respuesta")
    public String respuesta;

    @JsonProperty("crtDate")
    public LocalDateTime crtDate;

    @JsonProperty("etapaPago")
    public String etapaPago;

    @JsonProperty("bancoDeposito")
    public String bancoDeposito;

	public MonitorPagoDTO(String upi, String legalName, Long idOrder, Date payDate, BigDecimal totalOrder, String payType,
			String orderStatus, Integer idClient, String numComprobante, String respuesta, LocalDateTime crtDate,
			String bancoDeposito) {
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
		this.bancoDeposito = bancoDeposito;
	}

    
    

}
