package com.evertecinc.b2c.enex.client.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.evertecinc.b2c.enex.client.dao.repositories.OrderRepository;
import com.evertecinc.b2c.enex.client.exceptions.OrderException;
import com.evertecinc.b2c.enex.client.exceptions.OrderNotFoundException;
import com.evertecinc.b2c.enex.client.model.dto.CriterioBusquedaOrderDTO;
import com.evertecinc.b2c.enex.client.model.dto.OrderConfirmedDTO;
import com.evertecinc.b2c.enex.client.model.dto.OrderDTO;
import com.evertecinc.b2c.enex.client.model.dto.OrderItemDTO;
import com.evertecinc.b2c.enex.client.model.entities.Order;
import com.evertecinc.b2c.enex.client.model.mapper.UserMapper;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class OrderService implements IOrderService {
	
	private final OrderRepository orderRepository;
	
	private final UserMapper userMapper;
	
	@Override
	public List<OrderItemDTO> getOrdersItemsByIdOrden(Long idOrden) throws OrderException, OrderNotFoundException {
		
		
		return null;
	}
	
	
	
	@Override
	public List<OrderDTO> getOrdersById(CriterioBusquedaOrderDTO criterio) throws OrderException, OrderNotFoundException {
		
		if( criterio == null ) {
			log.error("Los criterios no pueden ser nulos.");
            throw new IllegalArgumentException("Los criterios no pueden ser nulos.");
		}
		
		Order respuesta = orderRepository.getById(criterio.getIdOrder());
		
		OrderDTO orderRespuesta = new OrderDTO();
		
		orderRespuesta.setIdOrder(respuesta.getIdOrder());
		orderRespuesta.setIdClient(respuesta.getClient().getIdClient());
		orderRespuesta.setIdUser(respuesta.getUser().getIdUser());
		orderRespuesta.setTotalOrder(respuesta.getTotalOrder());
		orderRespuesta.setPayType(respuesta.getPayType());
		orderRespuesta.setOrderStatus(respuesta.getOrderStatus());
		orderRespuesta.setPayDate(respuesta.getPayDate());
		orderRespuesta.setCtrDate(respuesta.getCreateDate());
		orderRespuesta.setUsuario(userMapper.toDTO(respuesta.getUser()));
//		List<OrderItemDTO> listOrderItem = ;
//		orderRespuesta.setListOrderItem(listOrderItem);
		orderRespuesta.setOrderType(respuesta.getOrderType());
		
		
		return null;
	}
	
	
	@Override
	public List<OrderDTO> getOrdersByCriterio(CriterioBusquedaOrderDTO criterio) throws OrderException, OrderNotFoundException {
		
		if( criterio == null ) {
			log.error("Los criterios no pueden ser nulos.");
            throw new IllegalArgumentException("Los criterios no pueden ser nulos.");
		}
		
		
		
		return null;
	}



	@Override
	public OrderConfirmedDTO getOrderConfirmed(Long idOrder, Long idUser) {
		// TODO Auto-generated method stub
		return null;
	}

}
