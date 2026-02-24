package com.evertecinc.b2c.enex.client.service;

import java.util.List;

import com.evertecinc.b2c.enex.client.exceptions.OrderException;
import com.evertecinc.b2c.enex.client.exceptions.OrderNotFoundException;
import com.evertecinc.b2c.enex.client.model.dto.CriterioBusquedaOrderDTO;
import com.evertecinc.b2c.enex.client.model.dto.OrderConfirmedDTO;
import com.evertecinc.b2c.enex.client.model.dto.OrderDTO;
import com.evertecinc.b2c.enex.client.model.dto.OrderItemDTO;

public interface IOrderService {

	List<OrderDTO> getOrdersByCriterio(CriterioBusquedaOrderDTO criterio) throws OrderException, OrderNotFoundException;

	List<OrderDTO> getOrdersById(CriterioBusquedaOrderDTO criterio) throws OrderException, OrderNotFoundException;

	List<OrderItemDTO> getOrdersItemsByIdOrden(Long idOrden) throws OrderException, OrderNotFoundException;

	OrderConfirmedDTO getOrderConfirmed(Long idOrder, Long idUser);

}
