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
@Table(name = "mp_bestado", schema = "public")
public class MpBancoEstado {
	
	@Id
	@Column(name = "id_order", nullable = false)
	private Long idOrder;
	
	@Column(name = "enc_id_sesion", nullable = true, length = 32)
	private String encIdSesion;
	
	@Column(name = "enc_rut_dv_con", nullable = true, length = 9)
	private String encRutDvCon;
	
	@Column(name = "enc_conv_con", nullable = true)
	private Integer encConvCon;
	
	@Column(name = "enc_servicio", nullable = true, length = 10)
	private String encServicio;
	
	@Column(name = "enc_rut_dv_cliente", nullable = true, length = 9)
	private String encRutDvCliente;
	
	@Column(name = "enc_pag_ret", nullable = true, length = 45)
	private String encPagRet;
	
	@Column(name = "encTipoConf", nullable = true, length = 5)
	private String encTipoConf;
	
	@Column(name = "enc_metodo_rend", nullable = true, length = 4)
	private String encMetodoRend;
	
	@Column(name = "enc_pag_rend", nullable = true, length = 45)
	private String encPagRend;
	
	@Column(name = "enc_banco", nullable = true, length = 4)
	private String encBanco;
	
	@Column(name = "enc_cant_mpago", nullable = true)
	private Integer encCantMpago;
	
	@Column(name = "enc_total", nullable = true)
	private Long encTotal;
	
	@Column(name = "multi_glosa_mpago", nullable = true, length = 10)
	private String multiGlosaMpago;
	
	@Column(name = "multi_id_mpago", nullable = true, length = 10)
	private String multiIdMpago;
	
	@Column(name = "multi_rut_dv_emp", nullable = true, length = 9)
	private String multiRutDvEmp;
	
	@Column(name = "multi_num_conv", nullable = true)
	private Integer multiNumConv;
	
	@Column(name = "multi_fec_trx", nullable = true, length = 8)
	private String multiFecTrx;
	
	@Column(name = "multi_hora_trx", nullable = true, length = 6)
	private String multiHoraTrx;

	@Column(name = "multi_fec_venc", nullable = true, length = 8)
	private String multiFecVenc;
	
	@Column(name = "multi_glosa", nullable = true, length = 10)
	private String multiGlosa;
	
	@Column(name = "multi_cod_pago", nullable = true, length = 10)
	private String multiCodPago;
	
	@Column(name = "multi_monto", nullable = true)
	private BigDecimal multiMonto;
	
	@Column(name = "resultado_mpago", nullable = true, length = 3)
	private String resultadoMpago;
	
	@Column(name = "resultado_glosa_err", nullable = true, length = 150)
	private String resultadoGlosaErr;
	
	@Column(name = "resultado_trx_mpago", nullable = true, length = 10)
	private String resultadoTrxMpago;
	
	@Column(name = "resultado_fec_mpago", nullable = true, length = 8)
	private String resultadoFecMpago;
	
	@Column(name = "resultado_hora_mpago", nullable = true, length = 6)
	private String resultadoHoraMpago;
	
	@Column(name = "resultado_fec_cntbl_mpago", nullable = true, length = 8)
	private String resultadoFecCntblMpago;
}
