package com.evertecinc.b2c.enex.client.model.dto2.jde;

public class ConexionFormServiceDTO {
	
	private String token;
	private String deviceName;
	private String formName;
	private String version;
	private String outputType;
	private String formServiceAction;
	private String showActionControls;
	private String formServiceDemo;
	private String findOnEntry;
	
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
	public String getFindOnEntry() {
		return findOnEntry;
	}
	public void setFindOnEntry(String findOnEntry) {
		this.findOnEntry = findOnEntry;
	}
	
	@Override
	public String toString() {
		return "ConectionFormServiceDTO [token=" + token + ", deviceName="
				+ deviceName + ", formName=" + formName + ", version="
				+ version + ", outputType=" + outputType
				+ ", formServiceAction=" + formServiceAction
				+ ", showActionControls=" + showActionControls
				+ ", formServiceDemo=" + formServiceDemo + ", findOnEntry="
				+ findOnEntry + "]";
	}

}
