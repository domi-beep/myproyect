package com.evertecinc.b2c.enex.client.model.dto2;

public class ReturnDTO {
	
	public String status;
	
	public String message;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "ReturnDTO [status=" + status + ", message=" + message + "]";
	}

	
}
