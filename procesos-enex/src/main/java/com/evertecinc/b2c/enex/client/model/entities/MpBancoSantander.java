package com.evertecinc.b2c.enex.client.model.entities;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "mp_bsantander", schema = "public")
@Getter
@Setter
public class MpBancoSantander {
	
	@Id
	@Column(name = "id_order", nullable = false)
	private Long idOrder;
	
	@Column(name = "mpini_idcom", nullable = false)
	private Long mpiniIdcom;
	
	@Column(name = "mpini_idtrx", nullable = false, length = 16)
	private String mpiniIdtrx;
	
	@Column(name = "mpini_total", nullable = false)
	private BigDecimal mpini_total;
	
	@Column(name = "mpini_nropagos", nullable = false)
	private Integer mpiniNropagos;
	
	@Column(name = "mpout_codret", nullable = false, length = 4)
	private String mpoutCodret;
	
	@Column(name = "mpout_descret", nullable = false, length = 60)
	private String mpoutDescret;
	
	@Column(name = "mpout_idcom", nullable = false)
	private Long mpout_idcom;

	@Column(name = "mpout_idtrx", nullable = false, length = 16)
	private String mpoutIdtrx;
	
	@Column(name = "mpout_total", nullable = false)
	private BigDecimal mpout_total;
	
	@Column(name = "mpout_nropagos", nullable = false)
	private Integer mpoutNropagos;
	
	@Column(name = "mpout_fechatrx", nullable = false, length = 14)
	private String mpoutFechatrx;
	
	@Column(name = "mpout_fechaconta", nullable = false, length = 8)
	private String mpoutFechaconta;
	
	@Column(name = "mpout_numcomp", nullable = false, length = 20)
	private String mpoutNumcomp;
	
	@Column(name = "mpout_idreg", nullable = false)
	private Long mpoutIdreg;
	
	@Column(name = "notifica", nullable = false, length = 10)
	private String notifica;
	
	@Column(name = "mpfin_idtrx", nullable = false, length = 16)
	private String mpfinIdtrx;
	
	@Column(name = "mpfin_codret", nullable = false, length = 4)
	private String mpfinCodret;
	
	@Column(name = "mpfin_nropagos", nullable = false)
	private Integer mpfinNropagos;
	
	@Column(name = "mpfin_total", nullable = false)
	private BigDecimal mpfinTotal;
	
	@Column(name = "mpfin_indpago", nullable = false, length = 1)
	private String mpfinIndpago;
	
	@Column(name = "mpfin_idreg", nullable = false)
	private Long mpfinIdreg;
}
