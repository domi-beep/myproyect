package com.evertecinc.b2c.enex.client.model.dto2;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MpBancoEstadoDTO extends PayDTO{
	
	private Long idOrder;
    private String encIdSesion;
    private String encRutDvCon;
    private Integer encConvCon;
    private String encServicio;
    private String encRutDvCliente;
    private String encPagRet;
    private String encTipoConf;
    private String encMetodoRend;
    private String encPagRend;
    private String encBanco;
    private Integer encCantMpago;
    private Long encTotal;
    private String multiGlosaMpago;
    private String multiIdMpago;
    private String multiRutDvEmp;
    private Integer multiNumConv;
    private String multiFecTrx;
    private String multiHoraTrx;
    private String multiFecVenc;
    private String multiGlosa;
    private String multiCodPago;
    private BigDecimal multiMonto;
    private String resultadoMpago;
    private String resultadoGlosaErr;
    private String resultadoTrxMpago;
    private String resultadoFecMpago;
    private String resultadoHoraMpago;
    private String resultadoFecCntblMpago;

}
