package com.evertecinc.b2c.enex.process.jde.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetalleCuentas {

	private String cuentaC;
	private String cuentaD;
	private String codDeptoOrpak;
	private String rutCliente;
	private String razonSocial;
	private String nombreDepto;
	private String fechaFact;
	
	
	public List<String> getRegistro(){
		return List.of(
		        codDeptoOrpak != null ? codDeptoOrpak : "",
		        rutCliente != null ? rutCliente : "",
		        razonSocial != null ? razonSocial : "",
		        cuentaC != null ? cuentaC : "",
		        cuentaD != null ? cuentaD : "",
		        nombreDepto != null ? nombreDepto : "",
		        fechaFact != null ? fechaFact : ""
		    );
	}
	
	
}
