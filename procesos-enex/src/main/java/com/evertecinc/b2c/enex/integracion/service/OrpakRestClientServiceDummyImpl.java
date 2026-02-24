package com.evertecinc.b2c.enex.integracion.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

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

import lombok.extern.slf4j.Slf4j;

@Service("OrpakRestClientServiceDummyImpl")
@Slf4j
public class OrpakRestClientServiceDummyImpl implements OrpakRestClientService {
	
	
	
	@Override
	public ChangeCardStatusResponse changeCardStatus(ChangeCardStatusRequest ccsr) throws OrpakException {
		
		log.debug("");

		ChangeCardStatusResponse resp = new ChangeCardStatusResponse();
		resp.setReturnCode(0);
		resp.setMessage("Success");
		//resp.setReturnCode(-2);
		//resp.setMessage("Error");
		resp.setUri("http://192.168.56.69:8081/api/values/ChangeCardStatus?cardnumber=809912300000050&sender=PTSF&satus=A");
		
		return resp;
	}

	@Override
	public CreateCustomerContractResponse createClientContract(CreateCustomerContractRequest req) throws OrpakException {
		
		CreateCustomerContractResponse resp = new CreateCustomerContractResponse();
		resp.setMessage("Success");
		resp.setReturnCode(1234);
		//resp.setMessage("Error");
		//resp.setReturnCode(-1);
		resp.setUri("http://192.168.56.69:8081/api/values/CreateClientContract?operationtype=1&customercode=9999000");
		
		return resp;
	}

	@Override
	public ChangeDepartmentStatusResponse changeDepartmentStatus(ChangeDepartmentStatusRequest request) {
		
		ChangeDepartmentStatusResponse resp = new ChangeDepartmentStatusResponse();
		resp.setReturnCode(0);
		resp.setMessage("Success");
		//resp.setReturnCode(-3);
		//resp.setMessage("Error");
		resp.setUri("http://192.168.56.69:8081/api/values/ChangeDepartmentStatus?customerCode=OCVAT");
		
		return resp;
	}

	@Override
	public CreateVehicleCardResponse createVehicleCard(CreateVehicleCardRequest req) {
		CreateVehicleCardResponse resp = new CreateVehicleCardResponse();
		resp.setReturnCode(0);
		resp.setMessage("Success");
		
		//resp.setReturnCode(-1);
		//resp.setMessage("Error");
		resp.setUri("http://192.168.56.69:8081/api/values/CreateVehicleCard?operationType=1&customercode=11");
		
		return resp;
	}
	
	@Override
	public List<GetDepartmentBalanceResponse> getDepartmentBalance(GetDepartmentBalanceRequest gdbr) throws OrpakException {

		GetDepartmentBalanceResponse resp = new GetDepartmentBalanceResponse();
		resp.setBalance(25000.0f);
		resp.setBalancetype("1");
		resp.setCode("DIESEL");
		resp.setDescription("DIESEL");
		resp.setMessage("ok");
		resp.setReturnCode(0);
		resp.setStockType("0");
		resp.setUri("uri");
		
//		resp.setBalance(100000.0f);
//		resp.setBalancetype("0");
//		resp.setCode("GAS97");
//		resp.setDescription("GAS 97");
//		resp.setMessage("ok");
//		resp.setReturnCode(0);
//		resp.setStockType("0");
//		resp.setUri("uri");
		
		List<GetDepartmentBalanceResponse> listado = new ArrayList<GetDepartmentBalanceResponse>();
		listado.add(resp);
		
		return listado;
		
	}

	@Override
	public CreateDepartmentResponse createDepartment(CreateDepartmentRequest req) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CardConstraintResponse cardConstraint(CardConstraintRequest req) {
		CardConstraintResponse resp = new CardConstraintResponse();
		resp.setReturnCode(0);
		resp.setMessage("Success");
		//resp.setReturnCode(-5);
		//resp.setMessage("Error");
		resp.setUri("http://192.168.56.69:8081/api/values/CardConstraint?operationtype=2&customercode=1962027&plate=GWZX10&time=1%7C1%3A00%7C23%3A00%7C2%7C1%3A00%7C23%3A00%7C3%7C1%3A00%7C23%3A00%7C4%7C1%3A00%7C23%3A00%7C5%7C1%3A00%7C23%3A00%7C6%7C1%3A00%7C23%3A00%7C7%7C1%3A00%7C23%3A00&store=&limit=3%7C1%7C0%7C10%7C3%7C1%7C2%7C150000");
		
		return resp;
	}

	@Override
	public UpdateCustomerBalanceResponse updateCustomerBalance(UpdateCustomerBalanceRequest req) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
