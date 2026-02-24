package com.evertecinc.b2c.enex.client.model.entities;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "mp_bbci_tbanc", schema = "public")
public class MpBancoBci {
	
	@Id
	@Column(name = "id_order", nullable = false)
	private Long idOrder;

	@Column(name = "estado", nullable = false, length = 3)
	private String estado;
	
	@Column(name = "fecha_trx", nullable = true, length = 8)
	private String fechaTrx;
	
	@Column(name = "monto", nullable = false)
	private BigDecimal monto;
	
}
