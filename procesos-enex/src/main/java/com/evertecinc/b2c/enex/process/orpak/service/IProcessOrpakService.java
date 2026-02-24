package com.evertecinc.b2c.enex.process.orpak.service;

import com.evertecinc.b2c.enex.client.model.dto.ClientDTO;
import com.evertecinc.b2c.enex.process.orpak.exceptions.ProcessOrpakException;
import com.evertecinc.b2c.enex.saf.model.dto.MessageDTO;

public interface IProcessOrpakService {
	
	public void updPendingCardStatusChanges( MessageDTO msg ) throws ProcessOrpakException;
	public void updateClient(MessageDTO msg) throws ProcessOrpakException;
	public void updPendingDepartmentCardStatusChange( MessageDTO msg ) throws ProcessOrpakException;
	public void updChangeDepartmentStatusCond(MessageDTO message) throws ProcessOrpakException;
	public void updPendingDepartmentStatusChange(MessageDTO message) throws ProcessOrpakException;
	public void updTypeBalance1Litros(MessageDTO message) throws ProcessOrpakException;
	
	public void updTypeBalance1(MessageDTO msg) throws ProcessOrpakException;
	public void updTypeBalance2Litros(MessageDTO msg) throws ProcessOrpakException;
	public void updTypeBalance2(MessageDTO msg) throws ProcessOrpakException;
	public void updOT(MessageDTO msg) throws ProcessOrpakException;
	public void deleteDepartmentOfFleet(MessageDTO msg) throws ProcessOrpakException;
	public void updPendingCardBalance(MessageDTO msg) throws ProcessOrpakException;
	public void updCardConstraint(MessageDTO msg) throws ProcessOrpakException;
	public void updPendingCustomerBalance(MessageDTO msg) throws ProcessOrpakException;
	public void sendClient(ClientDTO cliente) throws ProcessOrpakException;
	public void updPendingDepartmentBalance(MessageDTO msg) throws ProcessOrpakException;
	public void updQuantityCard(MessageDTO msg) throws ProcessOrpakException;
	public void addPendingVehiceCardChange(MessageDTO msg) throws ProcessOrpakException;
	public void updPendingVehiceCardChange(MessageDTO msg) throws ProcessOrpakException;
	public void renamePendingVehicleCardChange(MessageDTO msg) throws ProcessOrpakException;
	
	
	

}
