package com.evertecinc.b2c.enex.client.model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "conductores", schema = "public")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Conductores {
	
	@Id
	@Column(name = "id_conductor")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idConductor;
	
	@ManyToOne
    @JoinColumn(name = "id_client")
    private Clients client;
	
	@Column(name = "nombre")
    private String nombre;
	
	@Column(name = "rut")
    private String rut;
	
	@Column(name = "telefono")
    private String telefono;
	
	@Column(name = "estado")
    private String estado;

}
