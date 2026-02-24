package com.evertecinc.b2c.enex.client.model.dto2.orpak;

public class ResponseDTO {
	
	private String returnCode;
	private String uri;
	private String message;
	
	/**
	 * @return the returnCode
	 */
	public String getReturnCode() {
		return returnCode;
	}
	/**
	 * @param returnCode the returnCode to set
	 */
	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}
	
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "ResponseDTO [returnCode=" + returnCode + ", URI=" + uri
				+ ", message=" + message + "]";
	}
	
}
