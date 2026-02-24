package com.evertecinc.b2c.enex.client.model.dto2;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MonitorImpresionDTO {
	
	public MonitorImpresionDTO(Long idQueue, String rut, String razonSocial, String patente, String tipoCliente,
			LocalDateTime fechaIngreso, String tipoSaf, Long idVehicle, String version, String usuarioAut,
			LocalDateTime dateOut, String data, String cardnum, String contactPhone, String estado) {
		super();
		this.id_queue = idQueue;
		this.rut = rut;
		this.razonSocial = razonSocial;
		this.patente = patente;
		this.tipoCliente = tipoCliente;
		this.fechaIngreso = fechaIngreso;
		this.tipoSaf = tipoSaf;
		this.id_vehicle = idVehicle;
		this.version = version;
		this.usuarioAut = usuarioAut;
		this.dateOut = dateOut;
		this.cardnum = cardnum;
		this.data = data;
		this.contactPhone = contactPhone;
		this.estado = estado;
	}
	private Long id_queue;
	private String rut;
	private String razonSocial;
	private String patente;
	private String tipoCliente;
	private LocalDateTime fechaIngreso;
	private String tipoSaf;
	private Long id_vehicle;
	private String version;
	private String usuarioAut;
	private LocalDateTime dateOut;
	private Integer cantidad;
	private Long idCard;
	private String cardnum;
	private String data;
	private String nombreReceptor;
	private String direccion;
	private String comuna;
	private Long idComuna;
	private Long idRegion;
	private String contactPhone;
	private String estado;

}
