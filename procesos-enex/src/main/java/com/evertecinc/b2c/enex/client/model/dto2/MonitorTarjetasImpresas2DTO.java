package com.evertecinc.b2c.enex.client.model.dto2;

public class MonitorTarjetasImpresas2DTO {
	
	private Long uniqueID;
	private String cardPlate;
	private String clientType;
	private String companyName;
	private String companyRUT;
	private String documentType;
	private String productCode;
	private String vehicleType;
	private Long idQueue;
	private String cardType;
	private String direccion;
	private String comuna;
	private String region;
	private String ciudad;
	private String tipoImpresion;
	private String tipoReglamento;
	private String clientStatus;
	private String contactName; 
	private String contactPhone;
	public Long getUniqueID() {
		return uniqueID;
	}
	public void setUniqueID(Long uniqueID) {
		this.uniqueID = uniqueID;
	}
	public String getCardPlate() {
		return cardPlate;
	}
	public void setCardPlate(String cardPlate) {
		this.cardPlate = cardPlate;
	}
	public String getClientType() {
		return clientType;
	}
	public void setClientType(String clientType) {
		this.clientType = clientType;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCompanyRUT() {
		return companyRUT;
	}
	public void setCompanyRUT(String companyRUT) {
		this.companyRUT = companyRUT;
	}
	public String getDocumentType() {
		return documentType;
	}
	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getVehicleType() {
		return vehicleType;
	}
	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}
	public Long getIdQueue() {
		return idQueue;
	}
	public void setIdQueue(Long idQueue) {
		this.idQueue = idQueue;
	}
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getComuna() {
		return comuna;
	}
	public void setComuna(String comuna) {
		this.comuna = comuna;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public String getTipoImpresion() {
		return tipoImpresion;
	}
	public void setTipoImpresion(String tipoImpresion) {
		this.tipoImpresion = tipoImpresion;
	}
	public String getTipoReglamento() {
		return tipoReglamento;
	}
	public void setTipoReglamento(String tipoReglamento) {
		this.tipoReglamento = tipoReglamento;
	}
	public String getClientStatus() {
		return clientStatus;
	}
	public void setClientStatus(String clientStatus) {
		this.clientStatus = clientStatus;
	}
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	public String getContactPhone() {
		return contactPhone;
	}
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}
	
	@Override
	public String toString() {
		return "MonitorTarjetasImpresas2DTO [uniqueID=" + uniqueID + ", cardPlate=" + cardPlate + ", clientType="
				+ clientType + ", companyName=" + companyName + ", companyRUT=" + companyRUT + ", documentType="
				+ documentType + ", productCode=" + productCode + ", vehicleType=" + vehicleType + ", idQueue="
				+ idQueue + ", cardType=" + cardType + ", direccion=" + direccion + ", comuna=" + comuna + ", region="
				+ region + ", ciudad=" + ciudad + ", tipoImpresion=" + tipoImpresion + ", tipoReglamento="
				+ tipoReglamento + ", clientStatus=" + clientStatus + ", contactName=" + contactName + ", contactPhone="
				+ contactPhone + "]";
	}
	public MonitorTarjetasImpresas2DTO(Long uniqueID, String cardPlate, String clientType, String companyName,
			String companyRUT, String documentType, String productCode, String vehicleType, Long idQueue,
			String cardType, String direccion, String comuna, String region, String ciudad, String tipoImpresion,
			String tipoReglamento, String clientStatus, String contactName, String contactPhone) {
		super();
		this.uniqueID = uniqueID;
		this.cardPlate = cardPlate;
		this.clientType = clientType;
		this.companyName = companyName;
		this.companyRUT = companyRUT;
		this.documentType = documentType;
		this.productCode = productCode;
		this.vehicleType = vehicleType;
		this.idQueue = idQueue;
		this.cardType = cardType;
		this.direccion = direccion;
		this.comuna = comuna;
		this.region = region;
		this.ciudad = ciudad;
		this.tipoImpresion = tipoImpresion;
		this.tipoReglamento = tipoReglamento;
		this.clientStatus = clientStatus;
		this.contactName = contactName;
		this.contactPhone = contactPhone;
	}


}
