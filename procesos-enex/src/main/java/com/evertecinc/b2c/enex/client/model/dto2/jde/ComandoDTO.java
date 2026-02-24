package com.evertecinc.b2c.enex.client.model.dto2.jde;

public class ComandoDTO {
	
	private String command;
	private String controlID;
	private String value;
	
	public String getCommand() {
		return command;
	}
	public void setCommand(String command) {
		this.command = command;
	}
	public String getControlID() {
		return controlID;
	}
	public void setControlID(String controlID) {
		this.controlID = controlID;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	@Override
	public String toString() {
		return "ComandoDTO [command=" + command + ", controlID=" + controlID
				+ ", value=" + value + "]";
	}
}
