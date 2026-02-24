package com.evertecinc.b2c.enex.client.model.dto2.jde;

public class CriterioConsultaDTO {
	
	private String tabla;
	private String filtroFecha;
	private String sumariza;
	
	public String getTabla() {
		return tabla;
	}
	public void setTabla(String tabla) {
		this.tabla = tabla;
	}
	public String getFiltroFecha() {
		return filtroFecha;
	}
	public void setFiltroFecha(String filtroFecha) {
		this.filtroFecha = filtroFecha;
	}
	public String getSumariza() {
		return sumariza;
	}
	public void setSumariza(String sumariza) {
		this.sumariza = sumariza;
	}
	
	@Override
	public String toString() {
		return "CriterioConsultaDTO [tabla=" + tabla + ", filtroFecha="
				+ filtroFecha + ", sumariza=" + sumariza + "]";
	}

}
