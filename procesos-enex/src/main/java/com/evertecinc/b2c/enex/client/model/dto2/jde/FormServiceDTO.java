package com.evertecinc.b2c.enex.client.model.dto2.jde;

import java.util.ArrayList;
import java.util.List;


public class FormServiceDTO {
	private String token;
	private String deviceName;
	private String formName;
	private String version;
	private String outputType;
	private String formServiceAction;
	private String showActionControls;
	private String formServiceDemo;
	private String findOnEntry;
	private String returnControlIDs;
	private List<ComandoDTO> formActions = new ArrayList<ComandoDTO>();
	private String setBypassFormServiceEREvent;
	private String autoClear;
	private Integer maxPageSize;
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	public String getFormName() {
		return formName;
	}
	public void setFormName(String formName) {
		this.formName = formName;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getOutputType() {
		return outputType;
	}
	public void setOutputType(String outputType) {
		this.outputType = outputType;
	}
	public String getFormServiceAction() {
		return formServiceAction;
	}
	public void setFormServiceAction(String formServiceAction) {
		this.formServiceAction = formServiceAction;
	}
	public String getShowActionControls() {
		return showActionControls;
	}
	public void setShowActionControls(String showActionControls) {
		this.showActionControls = showActionControls;
	}
	public String getFormServiceDemo() {
		return formServiceDemo;
	}
	public void setFormServiceDemo(String formServiceDemo) {
		this.formServiceDemo = formServiceDemo;
	}
	public String getReturnControlIDs() {
		return returnControlIDs;
	}
	public void setReturnControlIDs(String returnControlIDs) {
		this.returnControlIDs = returnControlIDs;
	}
	public String getFindOnEntry() {
		return findOnEntry;
	}
	public void setFindOnEntry(String findOnEntry) {
		this.findOnEntry = findOnEntry;
	}
	public List<ComandoDTO> getFormActions() {
		return formActions;
	}
	public void setFormActions(List<ComandoDTO> formActions) {
		this.formActions = formActions;
	}
	public String getSetBypassFormServiceEREvent() {
		return setBypassFormServiceEREvent;
	}
	public void setSetBypassFormServiceEREvent(String setBypassFormServiceEREvent) {
		this.setBypassFormServiceEREvent = setBypassFormServiceEREvent;
	}
	public String getAutoClear() {
		return autoClear;
	}
	public void setAutoClear(String autoClear) {
		this.autoClear = autoClear;
	}
	public Integer getMaxPageSize() {
		return maxPageSize;
	}
	public void setMaxPageSize(Integer maxPageSize) {
		this.maxPageSize = maxPageSize;
	}
	@Override
	public String toString() {
		return "FormServiceDTO [token=" + token + ", deviceName=" + deviceName
				+ ", formName=" + formName + ", version=" + version
				+ ", outputType=" + outputType + ", formServiceAction="
				+ formServiceAction + ", showActionControls="
				+ showActionControls + ", formServiceDemo=" + formServiceDemo
				+ ", findOnEntry=" + findOnEntry + ", returnControlIDs="
				+ returnControlIDs + ", formActions=" + formActions
				+ ", setBypassFormServiceEREvent="
				+ setBypassFormServiceEREvent + ", autoClear=" + autoClear
				+ ", maxPageSize=" + maxPageSize + "]";
	}
	

}
