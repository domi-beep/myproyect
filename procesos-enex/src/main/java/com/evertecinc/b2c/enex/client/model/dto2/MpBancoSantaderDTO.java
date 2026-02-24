package com.evertecinc.b2c.enex.client.model.dto2;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MpBancoSantaderDTO extends PayDTO{
	
	private Long idOrder;
    private Long mpiniIdcom;
    private String mpiniIdtrx;
    private BigDecimal mpiniTotal;
    private Integer mpiniNropagos;
    private String mpoutCodret;
    private String mpoutDescret;
    private Long mpoutIdcom;
    private String mpoutIdtrx;
    private BigDecimal mpoutTotal;
    private Integer mpoutNropagos;
    private String mpoutFechatrx;
    private String mpoutFechaconta;
    private String mpoutNumcomp;
    private Long mpoutIdreg;
    private String notifica;
    private String mpfinIdtrx;
    private String mpfinCodret;
    private Integer mpfinNropagos;
    private BigDecimal mpfinTotal;
    private String mpfinIndpago;
    private Long mpfinIdreg;

}
