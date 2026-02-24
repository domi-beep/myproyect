package com.evertecinc.b2c.enex.client.model.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "reporte_contraloria_clientes", schema = "public")
public class ReporteContraloriaClientes {

	
    @Id
    @Column(name = "idreporte", nullable = false)
    private Long idReporte;
    
    @ManyToOne
    @JoinColumn(name = "id_client", nullable = false)
    private Clients client;
	
	@Column(name = "tipo", nullable = true, length = 3)
	private String tipo;

	@Column(name = "estado", nullable = true, length = 1)
	private String estado;
	
	@Column(name = "dateins", nullable = true)
	private LocalDateTime dateIns;
	
	@Column(name = "dateupd", nullable = true)
	private LocalDateTime dateUpd;
	
}
