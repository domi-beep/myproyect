package com.evertecinc.b2c.enex.integracion.service;

import java.util.List;

import com.evertecinc.b2c.enex.client.model.dto.CardDTO;
import com.evertecinc.b2c.enex.client.model.dto.ClientDTO;
import com.evertecinc.b2c.enex.client.model.dto.DepartmentDTO;
import com.evertecinc.b2c.enex.client.model.dto.StatusCardDTO;
import com.evertecinc.b2c.enex.client.model.dto.VehicleDTO;
import com.evertecinc.b2c.enex.client.model.dto2.orpak.RequestBalanceDepartmentDTO;
import com.evertecinc.b2c.enex.client.model.dto2.orpak.ResponseBalanceDepartmentDTO;
import com.evertecinc.b2c.enex.integracion.exception.OrpakWSCardConstraintExistExceptions;
import com.evertecinc.b2c.enex.integracion.exception.OrpakWSExceptions;
import com.evertecinc.b2c.enex.integracion.exception.OrpakWSTrxCardBalanceExistExceptions;
import com.evertecinc.b2c.enex.integracion.exception.OrpakWSTrxCustomerBalanceExistExceptions;
import com.evertecinc.b2c.enex.integracion.exception.OrpakWSTrxDepartmentBalanceExistExceptions;
import com.evertecinc.b2c.enex.integracion.exception.OrpakWSVehicleCardExistExceptions;
import com.evertecinc.b2c.enex.integracion.model.dto.CardConstraintDTO;
import com.evertecinc.b2c.enex.integracion.model.dto.ChangeDepartmentStatusDTO;
import com.evertecinc.b2c.enex.integracion.model.dto.CreateClientDTO;
import com.evertecinc.b2c.enex.integracion.model.dto.CreateDepartmentDTO;
import com.evertecinc.b2c.enex.integracion.model.dto.CreateDepartmentOfFleetDTO;
import com.evertecinc.b2c.enex.integracion.model.dto.LoadQuantityCardDTO;
import com.evertecinc.b2c.enex.integracion.model.dto.ResponseDTO;
import com.evertecinc.b2c.enex.integracion.model.dto.UpdCardBalanceDTO;
import com.evertecinc.b2c.enex.integracion.model.dto.UpdCustomerBalanceDTO;
import com.evertecinc.b2c.enex.integracion.model.dto.UpdDepartmentBalanceDTO;
import com.evertecinc.b2c.enex.process.orpak.exceptions.ProcessOrpakException;
import com.evertecinc.b2c.enex.saf.model.dto.MessageDTO;

public interface OrpakWSService {
	
	public ResponseDTO updateCardStatus(StatusCardDTO datos, String sender) throws OrpakWSExceptions;

	public ResponseDTO updateClient(CreateClientDTO clientWs) throws OrpakWSExceptions;

	public ResponseDTO updateDepartmentStatus(ChangeDepartmentStatusDTO changeDepartmentStatus) throws OrpakWSExceptions;

	public void updChangeDepartmentStatusCond(MessageDTO msg) throws ProcessOrpakException;

	public void updPendingDepartmentStatusChange(MessageDTO msg) throws ProcessOrpakException;

	public void updTypeBalance1Litros(MessageDTO msg) throws ProcessOrpakException;

	public ResponseDTO deleteDepartmentOfFleet(CreateDepartmentOfFleetDTO dto);

	public ResponseDTO updateCardBalance(UpdCardBalanceDTO dto) throws OrpakWSTrxCardBalanceExistExceptions;

	public ResponseDTO updateCardConstraint(CardConstraintDTO cardConstraint) throws OrpakWSTrxCustomerBalanceExistExceptions;

	public ResponseDTO updateCustomerBalance(UpdCustomerBalanceDTO dto) throws OrpakWSTrxCustomerBalanceExistExceptions;

	public String createClient(CreateClientDTO clientWs) throws OrpakWSTrxCustomerBalanceExistExceptions;

	public String createDepartment(CreateDepartmentDTO dto) throws OrpakWSTrxCustomerBalanceExistExceptions;

	public ResponseDTO updateDepartmentBalance(UpdDepartmentBalanceDTO dto) throws OrpakWSTrxDepartmentBalanceExistExceptions;

	public ResponseDTO loadQuantityCard(LoadQuantityCardDTO dto) throws OrpakWSExceptions, OrpakWSTrxCardBalanceExistExceptions;

	public ResponseDTO createCardConstraint(CardConstraintDTO cardConstraint) throws OrpakWSCardConstraintExistExceptions;

	public ResponseDTO createVehicleCard(VehicleDTO vehicle, CardDTO card, DepartmentDTO depto, ClientDTO client) throws OrpakWSExceptions, OrpakWSVehicleCardExistExceptions;

	public ResponseDTO updateVehicleCard(VehicleDTO vehicle, CardDTO cardDTO, DepartmentDTO department, ClientDTO client) throws OrpakWSExceptions, OrpakWSVehicleCardExistExceptions;

	public ResponseDTO renameVehicleCard(VehicleDTO vehicle, CardDTO cardDTO, DepartmentDTO department, ClientDTO client) throws OrpakWSExceptions, OrpakWSVehicleCardExistExceptions;

	public List<ResponseBalanceDepartmentDTO> getDepartmentBalance(RequestBalanceDepartmentDTO requestBalanceDepartmentDTO);

}
