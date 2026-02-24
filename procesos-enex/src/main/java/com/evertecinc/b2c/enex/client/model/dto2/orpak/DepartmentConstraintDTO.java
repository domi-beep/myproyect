package com.evertecinc.b2c.enex.client.model.dto2.orpak;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.evertecinc.b2c.enex.client.model.dto2.StationConstraintDTO;

public class DepartmentConstraintDTO {

	private Long    idDepartment;
	private Long    idClient;
	private String  name;
	private Boolean restrL;
	private Boolean restrM;
	private Boolean restrX;
	private Boolean restrJ;
	private Boolean restrV;
	private Boolean restrS;
	private Boolean restrD;
	private Long restrHinit;
	private Long restrHend;
	private BigDecimal  restrAmountMax;
	private Long restrDailyMaxLoads;
	private String  departmentStatus;
	private String  restrictionType;
	private Integer cantidad;
	private String  warningStockChannel;
    private BigDecimal warningStock;
    private String warningStockCelular;
    private String warningStockMail;
    private String warningLoadChannel;
    private String warningLoadCelular;
    private String warningLoadMail;
    private String warningLoadFailChannel;
    private String warningLoadFailCelular;
    private String warningLoadFailEmail;
    private String typeBalance;
    private List<StationConstraintDTO> listStationDepartmentConstraint= new ArrayList<StationConstraintDTO>();
	/**
	 * @return the idDepartment
	 */
	public Long getIdDepartment() {
		return idDepartment;
	}
	/**
	 * @param idDepartment the idDepartment to set
	 */
	public void setIdDepartment(Long idDepartment) {
		this.idDepartment = idDepartment;
	}
	/**
	 * @return the idClient
	 */
	public Long getIdClient() {
		return idClient;
	}
	/**
	 * @param idClient the idClient to set
	 */
	public void setIdClient(Long idClient) {
		this.idClient = idClient;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the restrL
	 */
	public Boolean getRestrL() {
		return restrL;
	}
	/**
	 * @param restrL the restrL to set
	 */
	public void setRestrL(Boolean restrL) {
		this.restrL = restrL;
	}
	/**
	 * @return the restrM
	 */
	public Boolean getRestrM() {
		return restrM;
	}
	/**
	 * @param restrM the restrM to set
	 */
	public void setRestrM(Boolean restrM) {
		this.restrM = restrM;
	}
	/**
	 * @return the restrX
	 */
	public Boolean getRestrX() {
		return restrX;
	}
	/**
	 * @param restrX the restrX to set
	 */
	public void setRestrX(Boolean restrX) {
		this.restrX = restrX;
	}
	/**
	 * @return the restrJ
	 */
	public Boolean getRestrJ() {
		return restrJ;
	}
	/**
	 * @param restrJ the restrJ to set
	 */
	public void setRestrJ(Boolean restrJ) {
		this.restrJ = restrJ;
	}
	/**
	 * @return the restrV
	 */
	public Boolean getRestrV() {
		return restrV;
	}
	/**
	 * @param restrV the restrV to set
	 */
	public void setRestrV(Boolean restrV) {
		this.restrV = restrV;
	}
	/**
	 * @return the restrS
	 */
	public Boolean getRestrS() {
		return restrS;
	}
	/**
	 * @param restrS the restrS to set
	 */
	public void setRestrS(Boolean restrS) {
		this.restrS = restrS;
	}
	/**
	 * @return the restrD
	 */
	public Boolean getRestrD() {
		return restrD;
	}
	/**
	 * @param restrD the restrD to set
	 */
	public void setRestrD(Boolean restrD) {
		this.restrD = restrD;
	}
	/**
	 * @return the restrHinit
	 */
	public Long getRestrHinit() {
		return restrHinit;
	}
	/**
	 * @param restrHinit the restrHinit to set
	 */
	public void setRestrHinit(Long restrHinit) {
		this.restrHinit = restrHinit;
	}
	/**
	 * @return the restrHend
	 */
	public Long getRestrHend() {
		return restrHend;
	}
	/**
	 * @param restrHend the restrHend to set
	 */
	public void setRestrHend(Long restrHend) {
		this.restrHend = restrHend;
	}
	/**
	 * @return the restrAmountMax
	 */
	public BigDecimal getRestrAmountMax() {
		return restrAmountMax;
	}
	/**
	 * @param restrAmountMax the restrAmountMax to set
	 */
	public void setRestrAmountMax(BigDecimal restrAmountMax) {
		this.restrAmountMax = restrAmountMax;
	}
	/**
	 * @return the restrDailyMaxLoads
	 */
	public Long getRestrDailyMaxLoads() {
		return restrDailyMaxLoads;
	}
	/**
	 * @param restrDailyMaxLoads the restrDailyMaxLoads to set
	 */
	public void setRestrDailyMaxLoads(Long restrDailyMaxLoads) {
		this.restrDailyMaxLoads = restrDailyMaxLoads;
	}
	/**
	 * @return the departmentStatus
	 */
	public String getDepartmentStatus() {
		return departmentStatus;
	}
	/**
	 * @param departmentStatus the departmentStatus to set
	 */
	public void setDepartmentStatus(String departmentStatus) {
		this.departmentStatus = departmentStatus;
	}
	/**
	 * @return the restrictionType
	 */
	public String getRestrictionType() {
		return restrictionType;
	}
	/**
	 * @param restrictionType the restrictionType to set
	 */
	public void setRestrictionType(String restrictionType) {
		this.restrictionType = restrictionType;
	}
	/**
	 * @return the cantidad
	 */
	public Integer getCantidad() {
		return cantidad;
	}
	/**
	 * @param cantidad the cantidad to set
	 */
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	/**
	 * @return the warningStockChannel
	 */
	public String getWarningStockChannel() {
		return warningStockChannel;
	}
	/**
	 * @param warningStockChannel the warningStockChannel to set
	 */
	public void setWarningStockChannel(String warningStockChannel) {
		this.warningStockChannel = warningStockChannel;
	}
	/**
	 * @return the warningStock
	 */
	public BigDecimal getWarningStock() {
		return warningStock;
	}
	/**
	 * @param warningStock the warningStock to set
	 */
	public void setWarningStock(BigDecimal warningStock) {
		this.warningStock = warningStock;
	}
	/**
	 * @return the warningStockCelular
	 */
	public String getWarningStockCelular() {
		return warningStockCelular;
	}
	/**
	 * @param warningStockCelular the warningStockCelular to set
	 */
	public void setWarningStockCelular(String warningStockCelular) {
		this.warningStockCelular = warningStockCelular;
	}
	/**
	 * @return the warningStockMail
	 */
	public String getWarningStockMail() {
		return warningStockMail;
	}
	/**
	 * @param warningStockMail the warningStockMail to set
	 */
	public void setWarningStockMail(String warningStockMail) {
		this.warningStockMail = warningStockMail;
	}
	/**
	 * @return the warningLoadChannel
	 */
	public String getWarningLoadChannel() {
		return warningLoadChannel;
	}
	/**
	 * @param warningLoadChannel the warningLoadChannel to set
	 */
	public void setWarningLoadChannel(String warningLoadChannel) {
		this.warningLoadChannel = warningLoadChannel;
	}
	/**
	 * @return the warningLoadCelular
	 */
	public String getWarningLoadCelular() {
		return warningLoadCelular;
	}
	/**
	 * @param warningLoadCelular the warningLoadCelular to set
	 */
	public void setWarningLoadCelular(String warningLoadCelular) {
		this.warningLoadCelular = warningLoadCelular;
	}
	/**
	 * @return the warningLoadMail
	 */
	public String getWarningLoadMail() {
		return warningLoadMail;
	}
	/**
	 * @param warningLoadMail the warningLoadMail to set
	 */
	public void setWarningLoadMail(String warningLoadMail) {
		this.warningLoadMail = warningLoadMail;
	}
	/**
	 * @return the warningLoadFailChannel
	 */
	public String getWarningLoadFailChannel() {
		return warningLoadFailChannel;
	}
	/**
	 * @param warningLoadFailChannel the warningLoadFailChannel to set
	 */
	public void setWarningLoadFailChannel(String warningLoadFailChannel) {
		this.warningLoadFailChannel = warningLoadFailChannel;
	}
	/**
	 * @return the warningLoadFailCelular
	 */
	public String getWarningLoadFailCelular() {
		return warningLoadFailCelular;
	}
	/**
	 * @param warningLoadFailCelular the warningLoadFailCelular to set
	 */
	public void setWarningLoadFailCelular(String warningLoadFailCelular) {
		this.warningLoadFailCelular = warningLoadFailCelular;
	}
	/**
	 * @return the warningLoadFailEmail
	 */
	public String getWarningLoadFailEmail() {
		return warningLoadFailEmail;
	}
	/**
	 * @param warningLoadFailEmail the warningLoadFailEmail to set
	 */
	public void setWarningLoadFailEmail(String warningLoadFailEmail) {
		this.warningLoadFailEmail = warningLoadFailEmail;
	}
	/**
	 * @return the typeBalance
	 */
	public String getTypeBalance() {
		return typeBalance;
	}
	/**
	 * @param typeBalance the typeBalance to set
	 */
	public void setTypeBalance(String typeBalance) {
		this.typeBalance = typeBalance;
	}
	/**
	 * @return the listStationDepartmentConstraint
	 */
	public List<StationConstraintDTO> getListStationDepartmentConstraint() {
		return listStationDepartmentConstraint;
	}
	/**
	 * @param listStationDepartmentConstraint the listStationDepartmentConstraint to set
	 */
	public void setListStationDepartmentConstraint(
			List<StationConstraintDTO> listStationDepartmentConstraint) {
		this.listStationDepartmentConstraint = listStationDepartmentConstraint;
	}
    
    
}
