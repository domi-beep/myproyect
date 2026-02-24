package com.evertecinc.b2c.enex.client.model.dto2.orpak;

public class RequestDepartmentDTO {
	
	private Long    idDepartment;
	private Long    idClient;
	private String  typeBalance;
	
	/**
	 * @return the idDepartment
	 */
	public Long getIdDepartment() {
		return idDepartment;
	}
	/**
	 * @param idDepartment the idDepartment to set
	 */
	public void setIdDepartment(Long idDepartment) {
		this.idDepartment = idDepartment;
	}
	/**
	 * @return the idClient
	 */
	public Long getIdClient() {
		return idClient;
	}
	/**
	 * @param idClient the idClient to set
	 */
	public void setIdClient(Long idClient) {
		this.idClient = idClient;
	}
	/**
	 * @return the typeBalance
	 */
	public String getTypeBalance() {
		return typeBalance;
	}
	/**
	 * @param typeBalance the typeBalance to set
	 */
	public void setTypeBalance(String typeBalance) {
		this.typeBalance = typeBalance;
	}
	
	
	
}
