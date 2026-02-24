package com.evertecinc.b2c.enex.client.model.dto2.orpak;

public class UpdateDepartmentOfFleetDTO {

	private String operationtype;
	private String customercode;
	private String deptcode;
	private String stocktype;
	private String stockcode;
	private String balancetype;
	
	public String getOperationtype() {
		return operationtype;
	}
	public void setOperationtype(String operationtype) {
		this.operationtype = operationtype;
	}
	public String getCustomercode() {
		return customercode;
	}
	public void setCustomercode(String customercode) {
		this.customercode = customercode;
	}
	public String getDeptcode() {
		return deptcode;
	}
	public void setDeptcode(String deptcode) {
		this.deptcode = deptcode;
	}
	public String getStocktype() {
		return stocktype;
	}
	public void setStocktype(String stocktype) {
		this.stocktype = stocktype;
	}
	public String getStockcode() {
		return stockcode;
	}
	public void setStockcode(String stockcode) {
		this.stockcode = stockcode;
	}
	public String getBalancetype() {
		return balancetype;
	}
	public void setBalancetype(String balancetype) {
		this.balancetype = balancetype;
	}
	@Override
	public String toString() {
		return "CreateDepartmentOfFleetDTO [operationtype=" + operationtype
				+ ", customercode=" + customercode + ", deptcode=" + deptcode
				+ ", stocktype=" + stocktype + ", stockcode=" + stockcode
				+ ", balancetype=" + balancetype + "]";
	}

}
