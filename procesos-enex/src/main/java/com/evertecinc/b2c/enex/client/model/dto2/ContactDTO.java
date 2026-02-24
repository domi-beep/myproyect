package com.evertecinc.b2c.enex.client.model.dto2;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class ContactDTO {

	 @SerializedName("nombre")
	    private String nombre;

	    @SerializedName("apellidoPaterno")
	    private String apellidoPaterno;

	    @SerializedName("apellidoMaterno")
	    private String apellidoMaterno;

	    @SerializedName("mail")
	    private String mail;

	    @SerializedName("empresaCliente")
	    private String empresaCliente; // No viene en JSON, quedará null

	    @SerializedName("rutEmpresa")
	    private String rutEmpresa; // No viene en JSON, quedará null

	    @SerializedName("idMotivo")
	    private Long idMotivo;

	    @SerializedName("nombreMotivo")
	    private String nombreMotivo;

	    @SerializedName("comentario")
	    private String comentario;

	    @SerializedName("clientType")
	    private String clientType;

}


