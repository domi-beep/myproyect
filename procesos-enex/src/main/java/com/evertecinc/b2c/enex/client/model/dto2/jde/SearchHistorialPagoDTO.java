package com.evertecinc.b2c.enex.client.model.dto2.jde;

public class SearchHistorialPagoDTO{
	
	private String fechaInicio;
	private String fechaFin;
	private String idClienteBoleta;
	private String idClienteFactura;
	private String tabla;
	private String tablaUnion;
	private String tipoDocumento;
	private String numeroDocumento;
	private Long cuentaC;
	private Long monto;
	

	public String getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public String getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}
	/**
	 * @return the idClienteBoleta
	 */
	public String getIdClienteBoleta() {
		return idClienteBoleta;
	}
	/**
	 * @param idClienteBoleta the idClienteBoleta to set
	 */
	public void setIdClienteBoleta(String idClienteBoleta) {
		this.idClienteBoleta = idClienteBoleta;
	}
	/**
	 * @return the idClienteFactura
	 */
	public String getIdClienteFactura() {
		return idClienteFactura;
	}
	/**
	 * @param idClienteFactura the idClienteFactura to set
	 */
	public void setIdClienteFactura(String idClienteFactura) {
		this.idClienteFactura = idClienteFactura;
	}
	/**
	 * @return the tabla
	 */
	public String getTabla() {
		return tabla;
	}
	/**
	 * @param tabla the tabla to set
	 */
	public void setTabla(String tabla) {
		this.tabla = tabla;
	}
	
	public String getTablaUnion() {
		return tablaUnion;
	}
	public void setTablaUnion(String tablaUnion) {
		this.tablaUnion = tablaUnion;
	}
	/**
	 * @return the tipoDocumento
	 */
	public String getTipoDocumento() {
		return tipoDocumento;
	}
	/**
	 * @param tipoDocumento the tipoDocumento to set
	 */
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	/**
	 * @return the numeroDocumento
	 */
	public String getNumeroDocumento() {
		return numeroDocumento;
	}
	/**
	 * @param numeroDocumento the numeroDocumento to set
	 */
	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}
	/**
	 * @return the cuentaC
	 */
	public Long getCuentaC() {
		return cuentaC;
	}
	/**
	 * @param cuentaC the cuentaC to set
	 */
	public void setCuentaC(Long cuentaC) {
		this.cuentaC = cuentaC;
	}
	/**
	 * @return the monto
	 */
	public Long getMonto() {
		return monto;
	}
	/**
	 * @param monto the monto to set
	 */
	public void setMonto(Long monto) {
		this.monto = monto;
	}
	
	@Override
	public String toString() {
		return "SearchHistorialPagoDTO [fechaInicio=" + fechaInicio
				+ ", fechaFin=" + fechaFin + ", idClienteBoleta="
				+ idClienteBoleta + ", idClienteFactura=" + idClienteFactura
				+ ", tabla=" + tabla + ", tablaUnion=" + tablaUnion
				+ ", tipoDocumento=" + tipoDocumento + ", numeroDocumento="
				+ numeroDocumento + ", cuentaC=" + cuentaC + ", monto=" + monto
				+ "]";
	}
	
}
