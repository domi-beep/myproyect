package com.evertecinc.b2c.enex.process.service;

import com.evertecinc.b2c.enex.process.exceptions.ProcessJdeException;

public interface IProcessService {

	public void ChangeCardStatusOrpakProcess() throws ProcessJdeException;
	public void ChangeClientOrpakProcess() throws ProcessJdeException;
	public void ChangeDepartmentCardStatusProcess() throws ProcessJdeException;
	public void ChangeDepartmentStatusCondProcess() throws ProcessJdeException;
	public void ChangeDepartmentStatusOrpakProcess() throws ProcessJdeException;
	public void ChangeTypeBalance1LitrosProcess() throws ProcessJdeException;
	public void ChangeTypeBalance1Process() throws ProcessJdeException;
	public void ChangeTypeBalance2LitrosProcess() throws ProcessJdeException;
	public void ChangeTypeBalance2Process() throws ProcessJdeException;
	public void CreateUpdateOTProcess() throws ProcessJdeException;
	public void DeleteDepartmentFleetOrpakProcess() throws ProcessJdeException;
	public void SendCardBalanceOrpakProcess() throws ProcessJdeException;
	public void SendCardConstraintOrpakProcess() throws ProcessJdeException;
	public void SendClientOrpakProcess() throws ProcessJdeException;
	public void SendCustomerBalanceOrpakProcess() throws ProcessJdeException;
	public void SendDepartmentBalanceOrpakProcess() throws ProcessJdeException;
	public void SendQuantityCardOrpakProcess() throws ProcessJdeException;
	public void SendUpdateCardGPSOrpakProcess() throws ProcessJdeException;
	public void VehicleCardOrpakProcess() throws ProcessJdeException;
	public void ChangeFactDepartmentProcess() throws ProcessJdeException;
	public void SearchDocumentsJDEProcess() throws ProcessJdeException;
	public void SendNotificationsDailyProcess() throws ProcessJdeException;
	public void SendNotificationsProcess() throws ProcessJdeException;
	public void SendSalesNoteToJDEProcess() throws ProcessJdeException;
	public void CargaPrecioPizarraProcess() throws ProcessJdeException;

	
	
}
