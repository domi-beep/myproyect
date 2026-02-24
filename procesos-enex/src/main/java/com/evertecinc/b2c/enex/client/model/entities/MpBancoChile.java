package com.evertecinc.b2c.enex.client.model.entities;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "mp_bchile", schema = "public")
@Getter
@Setter
@NoArgsConstructor
public class MpBancoChile {

	@Id
	@Column(name = "id_order", nullable = false)
	private Long idOrder;
	
	@Column(name = "mpini_idcom", nullable = true)
	private Long mpiniIdcom;
	
	@Column(name = "mpini_idtrx", nullable = true, length = 16)
	private String mpiniIdtrx;
	
	@Column(name = "mpini_total", nullable = true)
	private BigDecimal mpiniTotal;
	
	@Column(name = "mpini_nropagos", nullable = true)
	private Integer mpiniNropagos;
	
	@Column(name = "mpout_codret", nullable = true, length = 4)
	private String mpoutCodret;
	
	@Column(name = "mpout_descret", nullable = true, length = 60)
	private String mpoutDescret;
	
	@Column(name = "mpout_idcom", nullable = true)
	private Long mpoutIdcom;
	
	@Column(name = "mpout_idtrx", nullable = true, length = 16)
	private String mpoutIdtrx;
	
	@Column(name = "mpout_total", nullable = true)
	private BigDecimal mpoutTotal;
	
	@Column(name = "mpout_nropagos", nullable = true)
	private Integer mpoutNropagos;
	
	@Column(name = "mpout_fechatrx", nullable = true, length = 14)
	private String mpoutFechatrx;
	
	@Column(name = "mpout_fechaconta", nullable = true, length = 8)
	private String mpoutFechaconta;
	
	@Column(name = "mpout_numcomp", nullable = true, length = 20)
	private String mpoutNumcomp;
	
	@Column(name = "mpout_idreg", nullable = true)
	private Long mpoutIdreg;
	
	@Column(name = "notifica", nullable = true, length = 10)
	private String notifica;
	
	@Column(name = "mpfin_idtrx", nullable = true, length = 16)
	private String mpfinIdtrx;
	
	@Column(name = "mpfin_codret", nullable = true, length = 4)
	private String mpfinCodret;
	
	@Column(name = "mpfin_nropagos", nullable = true)
	private Integer mpfinNropagos;
	
	@Column(name = "mpfin_total", nullable = true)
	private BigDecimal mpfinTotal;
	
	@Column(name = "mpfin_indpago", nullable = true, length = 1)
	private String mpfinIndpago;
	
	@Column(name = "mpfin_idreg", nullable = true)
	private Long mpfinIdreg;
}
