package com.evertecinc.b2c.enex.client.model.dto2.orpak;

public class DepartmentWsDTO {

	Long idDepartment;
	
	String departmentCodeOrpak;
	
	String status;

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
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the departmentCodeOrpak
	 */
	public String getDepartmentCodeOrpak() {
		return departmentCodeOrpak;
	}

	/**
	 * @param departmentCodeOrpak the departmentCodeOrpak to set
	 */
	public void setDepartmentCodeOrpak(String departmentCodeOrpak) {
		this.departmentCodeOrpak = departmentCodeOrpak;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DepartmentWsDTO [idDepartment=" + idDepartment
				+ ", departmentCodeOrpak=" + departmentCodeOrpak + ", status="
				+ status + "]";
	}

	
}
