package com.evertecinc.b2c.enex.integracion.service;

import java.util.List;

import com.evertecinc.b2c.enex.client.model.dto2.orpak.CardConstraintRequest;
import com.evertecinc.b2c.enex.client.model.dto2.orpak.CardConstraintResponse;
import com.evertecinc.b2c.enex.client.model.dto2.orpak.CreateDepartmentRequest;
import com.evertecinc.b2c.enex.client.model.dto2.orpak.CreateDepartmentResponse;
import com.evertecinc.b2c.enex.client.model.dto2.orpak.GetDepartmentBalanceResponse;
import com.evertecinc.b2c.enex.client.model.dto2.orpakInterface.CreateVehicleCardRequest;
import com.evertecinc.b2c.enex.client.model.dto2.orpakInterface.CreateVehicleCardResponse;
import com.evertecinc.b2c.enex.client.model.dto2.orpakInterface.GetDepartmentBalanceRequest;
import com.evertecinc.b2c.enex.client.model.dto2.restResponses.UpdateCustomerBalanceResponse;
import com.evertecinc.b2c.enex.integracion.exception.OrpakException;
import com.evertecinc.b2c.enex.integracion.model.dto.ChangeCardStatusRequest;
import com.evertecinc.b2c.enex.integracion.model.dto.ChangeCardStatusResponse;
import com.evertecinc.b2c.enex.integracion.model.dto.ChangeDepartmentStatusRequest;
import com.evertecinc.b2c.enex.integracion.model.dto.ChangeDepartmentStatusResponse;
import com.evertecinc.b2c.enex.integracion.model.dto.CreateCustomerContractRequest;
import com.evertecinc.b2c.enex.integracion.model.dto.CreateCustomerContractResponse;
import com.evertecinc.b2c.enex.model.dto.request.UpdateCustomerBalanceRequest;

public interface OrpakRestClientService {

	ChangeCardStatusResponse changeCardStatus(ChangeCardStatusRequest ccsr) throws OrpakException;

	CreateCustomerContractResponse createClientContract(CreateCustomerContractRequest req);

	ChangeDepartmentStatusResponse changeDepartmentStatus(ChangeDepartmentStatusRequest request);

	CreateVehicleCardResponse createVehicleCard(CreateVehicleCardRequest req);

	List<GetDepartmentBalanceResponse> getDepartmentBalance(GetDepartmentBalanceRequest req);

	CreateDepartmentResponse createDepartment(CreateDepartmentRequest req);

	CardConstraintResponse cardConstraint(CardConstraintRequest req);

	UpdateCustomerBalanceResponse updateCustomerBalance(UpdateCustomerBalanceRequest req);

}
