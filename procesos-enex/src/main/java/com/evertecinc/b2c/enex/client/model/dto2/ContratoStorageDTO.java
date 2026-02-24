package com.evertecinc.b2c.enex.client.model.dto2;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ContratoStorageDTO {

	private Long idStoCliente;
	private Long idClient;
	private String codigoEstacion;
	private String metrosCubicos;
	private LocalDate fechaPersoneria;
	private String notariaPersoneria;
	private String notarioPersoneria;
	private LocalDate fechaIns;
	private LocalDate fechaUpd;
	private String usuarioIns;
	private String usuarioUpd;
	private String nombreAreaFile;
	private String nombreRegionFile;
	private String nombreEstacionFile;
	private String direccionCliente;
	private String nombreCalleCliente;
	private String numeroCalleCliente;
	private String nombreAreaCliente;
	private String nombreRegionCliente;
}
