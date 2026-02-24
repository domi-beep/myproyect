package com.evertecinc.b2c.enex.client.model.entities;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "mp_deposito", schema = "public")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class MpDeposito {

	@Id
	@Column(name = "id_order", nullable = false)
	private Long idOrder;
	
	@Column(name = "comprobante", nullable = false, length = 45)
	private String comprobante;
	
	@Column(name = "banco", nullable = false, length = 45)
	private String banco;
	
	@Column(name = "monto", nullable = false)
	private BigDecimal monto;
	
}
