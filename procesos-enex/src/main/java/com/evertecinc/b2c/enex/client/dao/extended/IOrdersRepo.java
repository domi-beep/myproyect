package com.evertecinc.b2c.enex.client.dao.extended;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.evertecinc.b2c.enex.client.model.dto.OrderItemDTO;
import com.evertecinc.b2c.enex.client.model.dto2.MonitorComprasDTO;
import com.evertecinc.b2c.enex.client.model.dto2.MonitorComprasExpDetalleOrdenes;
import com.evertecinc.b2c.enex.client.model.dto2.MonitorPagoDTO;
import com.evertecinc.b2c.enex.client.model.dto2.ReporteClienteVentaDTO;
import com.evertecinc.b2c.enex.client.model.dto2.ShopCartDTO;
import com.evertecinc.b2c.enex.client.model.dto2.requests.ListShopCartRequestDTO;
import com.evertecinc.b2c.enex.client.model.dto2.requests.MonitorComprasRequestDTO;
import com.evertecinc.b2c.enex.client.model.dto2.requests.MonitorPagoRequestDTO;
import com.evertecinc.b2c.enex.client.model.dto2.requests.ReporteClienteVentaRequestDTO;

public interface IOrdersRepo {
	
	public List<MonitorComprasDTO> getMonitorComprasByCriterio(MonitorComprasRequestDTO params, Pageable paging);
	public Long countMonitorComprasByCriterio(MonitorComprasRequestDTO params, Pageable paging);
	
	public List<MonitorComprasExpDetalleOrdenes> getExpMonitorComprasDetalleOrdenes(MonitorComprasRequestDTO params, Pageable paging);
	
	public Long countExpMonitorComprasDetalleOrdenes(MonitorComprasRequestDTO params, Pageable paging);
	
	public List<MonitorPagoDTO> getListPagosMonitor(MonitorPagoRequestDTO params, Pageable paging);
	public Long countListPagosMonitor(MonitorPagoRequestDTO params, Pageable paging);
	
	public List<ReporteClienteVentaDTO> getListadoClienteVentas(ReporteClienteVentaRequestDTO params, Pageable paging);
	public Long countListadoClienteVentas(ReporteClienteVentaRequestDTO params, Pageable paging);
	
	public List<OrderItemDTO> getListOrderItemConfirm(Long idOrder, Pageable paging);
	
	public List<ShopCartDTO> getListShopCart(ListShopCartRequestDTO params, Pageable paging);
	
	public List<OrderItemDTO> getListOrderItem(Long idOrder);
}
