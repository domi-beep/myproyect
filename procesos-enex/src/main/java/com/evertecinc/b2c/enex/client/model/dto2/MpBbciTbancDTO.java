package com.evertecinc.b2c.enex.client.model.dto2;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MpBbciTbancDTO extends PayDTO{

    private Integer idOrder;
    private String estado;
    private String fechaTrx;
    private Double monto;
}