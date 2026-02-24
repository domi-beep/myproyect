package com.evertecinc.b2c.enex.client.model.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "card_ext_reqs", schema = "public")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class CardExtReqs {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_card_ext", nullable = false)
	private Long idCardExt;
	
	@Column(name = "plate")
	private String plate;
	
	@Column(name = "cardnum")
	private String cardnum;
	
	@Column(name = "rut")
	private String rut;
	
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "razonSocial")
	private String razonSocial;
	
	@Column(name = "tipoProducto")
	private String tipoProducto;
	
	@Column(name = "cuentaD")
	private String cuentaD;
	
	@Column(name = "file")
	private String file;
	
	@Column(name = "tipoTarjeta")
	private String tipoTarjeta;
	
	@Column(name = "idUsuario")
	private Long idUsuario;
	
	@Column(name = "estado")
	private String estado;
	
	@Column(name = "xml")
	private String xml;
	
	@Column(name = "fechaIns")
	private LocalDateTime fechaIns;
	

}
