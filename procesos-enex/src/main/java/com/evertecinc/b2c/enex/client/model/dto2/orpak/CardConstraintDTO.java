package com.evertecinc.b2c.enex.client.model.dto2.orpak;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CardConstraintDTO {

	private String customerCode;
	private String plate;
	private Boolean restrL;
	private Boolean restrM;
	private Boolean restrX;
	private Boolean restrJ;
	private Boolean restrV;
	private Boolean restrS;
	private Boolean restrD;
	private Long restrHini;
	private Long restrHend;
	private List<String> listaCardStationConstraint = new ArrayList<String>();
	private BigDecimal restrAmountMax;
	private Long restrDailyMaxLoads;
	private Long restrDailyMaxQuantity;
	private String restrType; // Tipo de restricci�n D: diaria S: semanal M:manual
	private String itemList;
	private String storeList;
	private Boolean gps;
	private Boolean amount;
	private Boolean tieneEstaciones = null;
	private List<String> stationsCodePersonalizadas;

	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	public String getPlate() {
		return plate;
	}

	public void setPlate(String plate) {
		this.plate = plate;
	}

	public Boolean getRestrL() {
		return restrL;
	}

	public void setRestrL(Boolean restrL) {
		this.restrL = restrL;
	}

	public Boolean getRestrM() {
		return restrM;
	}

	public void setRestrM(Boolean restrM) {
		this.restrM = restrM;
	}

	public Boolean getRestrX() {
		return restrX;
	}

	public void setRestrX(Boolean restrX) {
		this.restrX = restrX;
	}

	public Boolean getRestrJ() {
		return restrJ;
	}

	public void setRestrJ(Boolean restrJ) {
		this.restrJ = restrJ;
	}

	public Boolean getRestrV() {
		return restrV;
	}

	public void setRestrV(Boolean restrV) {
		this.restrV = restrV;
	}

	public Boolean getRestrS() {
		return restrS;
	}

	public void setRestrS(Boolean restrS) {
		this.restrS = restrS;
	}

	public Boolean getRestrD() {
		return restrD;
	}

	public void setRestrD(Boolean restrD) {
		this.restrD = restrD;
	}

	public Long getRestrHini() {
		return restrHini;
	}

	public void setRestrHini(Long restrHini) {
		this.restrHini = restrHini;
	}

	public Long getRestrHend() {
		return restrHend;
	}

	public void setRestrHend(Long restrHend) {
		this.restrHend = restrHend;
	}

	public Boolean getAmount() {
		return amount;
	}

	public void setAmount(Boolean amount) {
		this.amount = amount;
	}

	public List<String> getListaCardStationConstraint() {
		return listaCardStationConstraint;
	}

	public void setListaCardStationConstraint(List<String> listaCardStationConstraint) {
		this.listaCardStationConstraint = listaCardStationConstraint;
	}

	public BigDecimal getRestrAmountMax() {
		return restrAmountMax;
	}

	public void setRestrAmountMax(BigDecimal restrAmountMax) {
		this.restrAmountMax = restrAmountMax;
	}

	public Long getRestrDailyMaxLoads() {
		return restrDailyMaxLoads;
	}

	public void setRestrDailyMaxLoads(Long restrDailyMaxLoads) {
		this.restrDailyMaxLoads = restrDailyMaxLoads;
	}

	public String getItemList() {
		return itemList;
	}

	public void setItemList(String itemList) {
		this.itemList = itemList;
	}

	public String getStoreList() {
		return storeList;
	}

	public void setStoreList(String storeList) {
		this.storeList = storeList;
	}

	public Long getRestrDailyMaxQuantity() {
		return restrDailyMaxQuantity;
	}

	public void setRestrDailyMaxQuantity(Long restrDailyMaxQuantity) {
		this.restrDailyMaxQuantity = restrDailyMaxQuantity;
	}

	public String getRestrType() {
		return restrType;
	}

	public void setRestrType(String restrType) {
		this.restrType = restrType;
	}

	public Boolean getGps() {
		return gps;
	}

	public void setGps(Boolean gps) {
		this.gps = gps;
	}

	public Boolean getTieneEstaciones() {
		return tieneEstaciones;
	}

	public void setTieneEstaciones(Boolean tieneEstaciones) {
		this.tieneEstaciones = tieneEstaciones;
	}

	public List<String> getStationsCodePersonalizadas() {
		return stationsCodePersonalizadas;
	}

	public void setStationsCodePersonalizadas(List<String> stationsCodePersonalizadas) {
		this.stationsCodePersonalizadas = stationsCodePersonalizadas;
	}

	@Override
	public String toString() {
		return "CardConstraintDTO [customerCode=" + customerCode + ", plate=" + plate + ", restrL=" + restrL
				+ ", restrM=" + restrM + ", restrX=" + restrX + ", restrJ=" + restrJ + ", restrV=" + restrV
				+ ", restrS=" + restrS + ", restrD=" + restrD + ", restrHini=" + restrHini + ", restrHend=" + restrHend
				+ ", listaCardStationConstraint=" + listaCardStationConstraint + ", restrAmountMax=" + restrAmountMax
				+ ", restrDailyMaxLoads=" + restrDailyMaxLoads + ", restrDailyMaxQuantity=" + restrDailyMaxQuantity
				+ ", restrType=" + restrType + ", itemList=" + itemList + ", storeList=" + storeList + ", gps=" + gps
				+ ", amount=" + amount + ", tieneEstaciones=" + tieneEstaciones + ", stationsCodePersonalizadas="
				+ stationsCodePersonalizadas + "]";
	}

}
