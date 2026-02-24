package com.evertecinc.b2c.enex.client.model.dto2.orpak;

public class ChangeDepartmentStatusDTO {

	private String customerCode;
	private String fleetDepartment;
	private String status;
	
	public String getCustomerCode() {
		return customerCode;
	}
	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}
	public String getFleetDepartment() {
		return fleetDepartment;
	}
	public void setFleetDepartment(String fleetDepartment) {
		this.fleetDepartment = fleetDepartment;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "ChangeDepartmentStatusDTO [customerCode=" + customerCode
				+ ", fleetDepartment=" + fleetDepartment + ", status=" + status
				+ "]";
	}
	
}
