package com.evertecinc.b2c.enex.client.model.dto2.orpak;

public class CreateClientDTO {
	
	private String customercode;
	private String customerdescription;
	private String status;
	private String alternativecode;
	private String address1;
	private String address2;
	private String disctrict;
	private String city;
	private String zip; 
	private String phone;
	private String fax;
	private String email;
	private String rut;
	private String giro;
	private String contactname;
	private String creditlimit;
	private String security="";
	private String warninglevel="";
	private String contractstartdate="";
	private String contractenddate="20351231";
	
	public String getCustomercode() {
		return customercode;
	}
	public void setCustomercode(String customercode) {
		this.customercode = customercode;
	}
	public String getCustomerdescription() {
		return customerdescription;
	}
	public void setCustomerdescription(String customerdescription) {
		this.customerdescription = customerdescription;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAlternativecode() {
		return alternativecode;
	}
	public void setAlternativecode(String alternativecode) {
		this.alternativecode = alternativecode;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getDisctrict() {
		return disctrict;
	}
	public void setDisctrict(String disctrict) {
		this.disctrict = disctrict;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRut() {
		return rut;
	}
	public void setRut(String rut) {
		this.rut = rut;
	}
	public String getGiro() {
		return giro;
	}
	public void setGiro(String giro) {
		this.giro = giro;
	}
	public String getContactname() {
		return contactname;
	}
	public void setContactname(String contactname) {
		this.contactname = contactname;
	}
	public String getCreditlimit() {
		return creditlimit;
	}
	public void setCreditlimit(String creditlimit) {
		this.creditlimit = creditlimit;
	}
	public String getSecurity() {
		return security;
	}
	public void setSecurity(String security) {
		this.security = security;
	}
	public String getWarninglevel() {
		return warninglevel;
	}
	public void setWarninglevel(String warninglevel) {
		this.warninglevel = warninglevel;
	}
	public String getContractstartdate() {
		return contractstartdate;
	}
	public void setContractstartdate(String contractstartdate) {
		this.contractstartdate = contractstartdate;
	}
	public String getContractenddate() {
		return contractenddate;
	}
	public void setContractenddate(String contractenddate) {
		this.contractenddate = contractenddate;
	}
	@Override
	public String toString() {
		return "CreateClientDTO [customercode=" + customercode
				+ ", customerdescription=" + customerdescription + ", status="
				+ status + ", alternativecode=" + alternativecode
				+ ", address1=" + address1 + ", address2=" + address2
				+ ", disctrict=" + disctrict + ", city=" + city + ", zip="
				+ zip + ", phone=" + phone + ", fax=" + fax + ", email="
				+ email + ", rut=" + rut + ", giro=" + giro + ", contactname="
				+ contactname + ", creditlimit=" + creditlimit + ", security="
				+ security + ", warninglevel=" + warninglevel
				+ ", contractstartdate=" + contractstartdate
				+ ", contractenddate=" + contractenddate + "]";
	}
	
	
}
