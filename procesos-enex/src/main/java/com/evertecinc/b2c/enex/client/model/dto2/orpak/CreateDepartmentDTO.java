package com.evertecinc.b2c.enex.client.model.dto2.orpak;

public class CreateDepartmentDTO {

	private String customerCode;
	private String departmentDescription;
	
	public String getCustomerCode() {
		return customerCode;
	}
	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}
	public String getDepartmentDescription() {
		return departmentDescription;
	}
	public void setDepartmentDescription(String departmentDescription) {
		this.departmentDescription = departmentDescription;
	}
	@Override
	public String toString() {
		return "CreateDepartmentDTO [customerCode=" + customerCode
				+ ", departmentDescription=" + departmentDescription + "]";
	}
	
}
