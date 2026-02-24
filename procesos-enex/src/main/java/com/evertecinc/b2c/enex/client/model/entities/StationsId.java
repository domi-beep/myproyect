package com.evertecinc.b2c.enex.client.model.entities;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StationsId implements Serializable {

	private static final long serialVersionUID = 4837183771232414418L;

	private String stationCode;
    private String clientType;

}