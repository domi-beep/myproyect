package com.evertecinc.b2c.enex.integracion.model.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateClientDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7237792504643426924L;

	private String customercode;
	private String customerdescription;
	private String status;
	private String alternativecode;
	private String address1;
	private String address2;
	private String disctrict;
	private String city;
	private String zip; 
	private String phone;
	private String fax;
	private String email;
	private String rut;
	private String giro;
	private String contactname;
	private String creditlimit;
	private String security="";
	private String warninglevel="";
	private String contractstartdate="";
	private String contractenddate="20351231";

    
}
