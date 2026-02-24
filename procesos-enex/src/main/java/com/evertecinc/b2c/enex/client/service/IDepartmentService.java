package com.evertecinc.b2c.enex.client.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.springframework.data.domain.Pageable;

import com.evertecinc.b2c.enex.client.exceptions.CardException;
import com.evertecinc.b2c.enex.client.exceptions.ClientException;
import com.evertecinc.b2c.enex.client.exceptions.ClientNotFoundException;
import com.evertecinc.b2c.enex.client.exceptions.CreditCardException;
import com.evertecinc.b2c.enex.client.exceptions.DepartmentException;
import com.evertecinc.b2c.enex.client.exceptions.DepartmentoUnicoException;
import com.evertecinc.b2c.enex.client.exceptions.DeptoTieneSaldoException;
import com.evertecinc.b2c.enex.client.exceptions.NuevoMontoException;
import com.evertecinc.b2c.enex.client.exceptions.PriceLiterNotFoundException;
import com.evertecinc.b2c.enex.client.exceptions.ProductDepartmentException;
import com.evertecinc.b2c.enex.client.exceptions.VehicleException;
import com.evertecinc.b2c.enex.client.model.dto.DepartmentDTO;
import com.evertecinc.b2c.enex.client.model.dto.ProductDepartmentDTO;
import com.evertecinc.b2c.enex.client.model.dto.RegisterClientDTO;
import com.evertecinc.b2c.enex.client.model.dto2.DepartmentMovementDTO;
import com.evertecinc.b2c.enex.client.model.dto2.DepartmentProductFinderDTO;
import com.evertecinc.b2c.enex.client.model.dto2.ReasignarMontoDeptDTO;
import com.evertecinc.b2c.enex.client.model.dto2.paging.PagingInitDTO;
import com.evertecinc.b2c.enex.client.model.dto2.paging.PagingSortDTO;
import com.evertecinc.b2c.enex.client.model.dto2.requests.AddDepartamentoJsonCtrlRequestDTO;
import com.evertecinc.b2c.enex.client.model.dto2.requests.DeleteDeptoClientJsonRequestDTO;
import com.evertecinc.b2c.enex.client.model.dto2.requests.DepartmentByClientRequestDTO;
import com.evertecinc.b2c.enex.client.model.dto2.requests.DepartmentRequestDTO;
import com.evertecinc.b2c.enex.client.model.dto2.requests.DoSetConstraintsByDepartmentRequestDTO;
import com.evertecinc.b2c.enex.client.model.dto2.requests.GetConstraintsByDepartmentRequestDTO;
import com.evertecinc.b2c.enex.client.model.dto2.requests.ListConstraintsByDepartmentJsonCtrlRequestDTO;
import com.evertecinc.b2c.enex.client.model.dto2.requests.ListDepartmentsByClient2JsonCtrlRequestDTO;
import com.evertecinc.b2c.enex.client.model.dto2.requests.ListDeptHistoryJsonCtrlRequestDTO;
import com.evertecinc.b2c.enex.client.model.dto2.requests.ListMovimientosDeptoCtrlRequestDTO;
import com.evertecinc.b2c.enex.client.model.dto2.requests.ProductsDepartmentsByCriteriaRequestDTO;
import com.evertecinc.b2c.enex.client.model.dto2.requests.UpdAlertDepartmentJsonCtrlRequestDTO;
import com.evertecinc.b2c.enex.client.model.dto2.requests.UpdConfigDepartmentJsonRequestDTO;
import com.evertecinc.b2c.enex.client.model.dto2.requests.UpdDepartmentCtrltRequestDTO;
import com.evertecinc.b2c.enex.client.model.dto2.requests.ViewEditDepartamentoCtrlRequestDTO;
import com.evertecinc.b2c.enex.client.model.dto2.restResponses.AddDepartamentoJsonCtrlResultDTO;
import com.evertecinc.b2c.enex.client.model.dto2.restResponses.DeleteDeptoClientJsonResultDTO;
import com.evertecinc.b2c.enex.client.model.dto2.restResponses.DepartmentsResultDTO;
import com.evertecinc.b2c.enex.client.model.dto2.restResponses.DoSetConstraintsByDepartmentResultDTO;
import com.evertecinc.b2c.enex.client.model.dto2.restResponses.GetConstraintsByDepartmentResultDTO;
import com.evertecinc.b2c.enex.client.model.dto2.restResponses.ListConstraintsByDepartmentJsonCtrlResultDTO;
import com.evertecinc.b2c.enex.client.model.dto2.restResponses.ListDepartmentsByClient2JsonCtrlResultDTO;
import com.evertecinc.b2c.enex.client.model.dto2.restResponses.ListDeptHistoryJsonCtrlResultDTO;
import com.evertecinc.b2c.enex.client.model.dto2.restResponses.ListMovimientosDeptoCtrlResultDTO;
import com.evertecinc.b2c.enex.client.model.dto2.restResponses.UpdAlertDepartmentJsonCtrlResultDTO;
import com.evertecinc.b2c.enex.client.model.dto2.restResponses.UpdConfigDepartmentJsonResultDTO;
import com.evertecinc.b2c.enex.client.model.dto2.restResponses.UpdDepartmentCtrlResultDTO;
import com.evertecinc.b2c.enex.client.model.dto2.restResponses.ViewEditDepartamentoCtrlResultDTO;
import com.evertecinc.b2c.enex.client.model.entities.CardsHistory;
import com.evertecinc.b2c.enex.client.model.entities.Departments;
import com.evertecinc.b2c.enex.integracion.exception.OrpakWSExceptions;
import com.evertecinc.b2c.enex.saf.exceptions.SafException;

public interface IDepartmentService {

	public Optional<DepartmentsResultDTO> getDepartmentsByClient(DepartmentByClientRequestDTO params, PagingSortDTO sort, PagingInitDTO paging, Locale locale) throws Exception;

	public Optional<DepartmentsResultDTO> getDepartmentByIdUser(DepartmentByClientRequestDTO params, PagingSortDTO sort, PagingInitDTO paging, Locale locale) throws Exception;

	public Optional<DepartmentsResultDTO> getDeptoHijos(DepartmentRequestDTO params) throws Exception;
	
	public Optional <AddDepartamentoJsonCtrlResultDTO> AddDepartamentoJsonCtrlDTO (AddDepartamentoJsonCtrlRequestDTO params, PagingSortDTO sort, PagingInitDTO paging, Locale locale) throws Exception;

	public Optional<ViewEditDepartamentoCtrlResultDTO> viewEditDepartamentoCtrl(ViewEditDepartamentoCtrlRequestDTO params, PagingSortDTO sort, PagingInitDTO paging, Locale locale);

	public Boolean doBloqueoTypeBalanceLitros(Long idDepartment) throws SafException;

	public Boolean doBloqueoTypeBalance(Long idDepartment) throws SafException;
	
	public Optional<UpdDepartmentCtrlResultDTO> UpdDepartmentCtrl(UpdDepartmentCtrltRequestDTO params, PagingSortDTO sort, PagingInitDTO paging, Locale locale);

	public List<ProductDepartmentDTO> doListProductDepartmentById(Long idDepartment) throws ProductDepartmentException;

	public List<ProductDepartmentDTO> getListProductDepartmentById(Long idDepartment) throws ProductDepartmentException;
	
	public Optional<UpdAlertDepartmentJsonCtrlResultDTO> UpdDepartmentJsonCtrl(UpdAlertDepartmentJsonCtrlRequestDTO params,
			PagingSortDTO sort, PagingInitDTO paging, Locale locale);
	
	public Optional<UpdConfigDepartmentJsonResultDTO>  UpdConfigDepartmentJson(UpdConfigDepartmentJsonRequestDTO params,
			PagingSortDTO sort, PagingInitDTO paging, Locale locale);
	
	public Boolean updateServicesGpsDepartment(Long idDepto, String[] servicio) throws DepartmentException, ClientException, VehicleException;

	public void updDeparmentsConstraints(Departments dto, String tarjetas, Boolean modificadoGps, CardsHistory cardHistory) throws DepartmentException, Exception;

	public Optional<ListDeptHistoryJsonCtrlResultDTO> ListDeptHistoryJsonCtrl(ListDeptHistoryJsonCtrlRequestDTO params,
			PagingSortDTO sort, PagingInitDTO paging, Locale locale);
	
	public Optional<ListMovimientosDeptoCtrlResultDTO> ListMovimientosDeptoCtrl(ListMovimientosDeptoCtrlRequestDTO params,
			PagingSortDTO sort, PagingInitDTO paging, Locale locale);
	
	public List<DepartmentMovementDTO> getListDepartmentMovement(ListMovimientosDeptoCtrlRequestDTO params, Pageable pages) throws Exception;
	
	public Optional<ListConstraintsByDepartmentJsonCtrlResultDTO> ListConstraintsByDepartmentJsonCtrl(ListConstraintsByDepartmentJsonCtrlRequestDTO params,
			PagingSortDTO sort, PagingInitDTO paging, Locale locale);

	public Optional<DeleteDeptoClientJsonResultDTO> DeleteDeptoClientJson(DeleteDeptoClientJsonRequestDTO params, PagingSortDTO sort,
			PagingInitDTO paging, Locale locale) throws ClientException;

	public void delDepartment(DepartmentDTO depto, String portal)
			throws OrpakWSExceptions, CreditCardException, SafException, ClientNotFoundException,
			DeptoTieneSaldoException, DepartmentException, DepartmentoUnicoException, VehicleException, ClientException;

	public void delDepartment(DepartmentDTO depto) throws DepartmentException, OrpakWSExceptions, CreditCardException,
			SafException, ClientNotFoundException, DeptoTieneSaldoException, DepartmentoUnicoException, ClientException, NuevoMontoException;

	public double updQuantityCard(Long idCard, String cardNumber) throws CardException;

	public void updDepartmentProductAmount(ProductDepartmentDTO dto) throws NuevoMontoException;

	public void addDepartmentMovement(DepartmentMovementDTO dto) throws DepartmentException;

	public long updCardBalance(Long idCard, String cardNumber) throws CardException;

	public BigDecimal getPrecioLitro(String productCode, BigDecimal amount) throws PriceLiterNotFoundException;

	public void updClientByID(RegisterClientDTO dto) throws ClientException;

	public void updDepartmentBalance(Long idDepartment) throws DepartmentException, NuevoMontoException;

	/**
	 * @param dto
	 * @throws NuevoMontoException
	 */
	public void addProductDepartmentRel(ProductsDepartmentsByCriteriaRequestDTO dto) throws NuevoMontoException;

	public void updDepartmentProductAmountSMov(ProductDepartmentDTO dto) throws NuevoMontoException;

	public Optional<ListDepartmentsByClient2JsonCtrlResultDTO> ListDepartmentsByClient2JsonCtrl(ListDepartmentsByClient2JsonCtrlRequestDTO params,
			PagingSortDTO sort, PagingInitDTO paging, Locale locale);

	public Optional<DoSetConstraintsByDepartmentResultDTO> DoSetConstraintsByDepartmentJsonCtrl(
			DoSetConstraintsByDepartmentRequestDTO params) throws DepartmentException;

	public Optional<GetConstraintsByDepartmentResultDTO> getConstraintsByDepartment(
			GetConstraintsByDepartmentRequestDTO params, PagingSortDTO sort, PagingInitDTO paging, Locale locale) throws DepartmentException;
	
	public List<ProductDepartmentDTO> doProductsDepartmentsByCriteria(ProductDepartmentDTO productDepartmentDTO) throws DepartmentException;
	
	public DepartmentDTO getListDepartmentById(Long idDepartment);
	
	public void updDepartmentProductAmountSum(ReasignarMontoDeptDTO dto) throws NuevoMontoException;
	
	public List<DepartmentProductFinderDTO> getProductDepartmentTransf(ProductDepartmentDTO dto);
	
}
