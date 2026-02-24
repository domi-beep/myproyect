package com.evertecinc.b2c.enex.client.model.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.evertecinc.b2c.enex.client.model.dto.OrderDTO;
import com.evertecinc.b2c.enex.client.model.entities.Client;
import com.evertecinc.b2c.enex.client.model.entities.Order;
import com.evertecinc.b2c.enex.client.model.entities.User;

public class OrderMapper {
	
	 public OrderDTO toDTO(Order order) {
	        if (order == null) return null;

	        OrderDTO orderDTO = new OrderDTO();
	        orderDTO.setIdOrder(order.getIdOrder());
	        orderDTO.setIdClient(order.getClient() != null ? order.getClient().getIdClient() : null);
	        orderDTO.setIdUser(order.getUser() != null ? order.getUser().getIdUser() : null);
	        orderDTO.setTotalOrder(order.getTotalOrder());
	        orderDTO.setPayType(order.getPayType());
	        orderDTO.setOrderStatus(order.getOrderStatus());
	        orderDTO.setPayDate(order.getPayDate());
	        orderDTO.setCtrDate(order.getCreateDate());
//	        orderDTO.setOrderType(order.getOrderType() != null ? order.getOrderType().longValue() : null);
	        orderDTO.setUsuario(order.getUser() != null ? new UserMapper().toDTO(order.getUser()) : null);
//	        orderDTO.setListOrderItem(order.getItems() != null 
//	            ? order.getItems().stream().map(item -> new OrderItemMapper().toDTO(item)).collect(Collectors.toList())
//	            : null);

	        return orderDTO;
	    }

	    public Order toEntity(OrderDTO orderDTO) {
	        if (orderDTO == null) return null;

	        Order order = new Order();
	        order.setIdOrder(orderDTO.getIdOrder());
//	        order.setClient(orderDTO.getIdClient() != null ? new Client(orderDTO.getIdClient()) : null);
//	        order.setUser(orderDTO.getIdUser() != null ? new User(orderDTO.getIdUser()) : null);
	        order.setTotalOrder(orderDTO.getTotalOrder());
	        order.setPayType(orderDTO.getPayType());
	        order.setOrderStatus(orderDTO.getOrderStatus());
	        order.setPayDate(orderDTO.getPayDate());
	        order.setCreateDate(orderDTO.getCtrDate());
	        order.setOrderType(orderDTO.getOrderType() != null ? orderDTO.getOrderType().intValue() : null);
	        // Aquí debes agregar la lógica para mapear usuario e items si son entidades relacionadas.

	        return order;
	    }

	    public List<OrderDTO> toDTOList(List<Order> orders) {
	        if (orders == null) return null;
	        return orders.stream().map(this::toDTO).collect(Collectors.toList());
	    }

	    public List<Order> toEntityList(List<OrderDTO> orderDTOs) {
	        if (orderDTOs == null) return null;
	        return orderDTOs.stream().map(this::toEntity).collect(Collectors.toList());
	    }

}
