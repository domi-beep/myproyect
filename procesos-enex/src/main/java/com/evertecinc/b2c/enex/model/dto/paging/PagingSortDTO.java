package com.evertecinc.b2c.enex.model.dto.paging;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Sort;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PagingSortDTO(
        @JsonProperty("orders")
        List<PagingOrderDTO> orders) implements Serializable {
    public static PagingSortDTO by(List<PagingOrderDTO> orders) {
        return new PagingSortDTO(orders);
    }

    public static PagingSortDTO by(PagingOrderDTO... orders) {
        return new PagingSortDTO(List.of(orders));
    }

    public static Sort getSFSort(PagingSortDTO sort) {
        if (sort != null) {
            List<Sort.Order> orders = new ArrayList<>();
            for (PagingOrderDTO order : sort.orders()) {
                Sort.Order sortOrder = new Sort.Order(Sort.Direction.fromString(order.direction()), order.property());
                orders.add(sortOrder);
            }
            return Sort.by(orders);
        }
        return null;
    }

    public static PagingSortDTO fromString(String sort) {
        if (sort != null && !sort.isEmpty()) {
            List<PagingOrderDTO> orders = new ArrayList<>();
            String[] sortArray = sort.split(";");
            for (String order : sortArray) {
                String[] orderArray = order.split(":");
                if (orderArray.length == 2) {
                    if (orderArray[1] == null || (!orderArray[1].equalsIgnoreCase(PagingOrderDTO.ASC) && !orderArray[1].equalsIgnoreCase(PagingOrderDTO.DESC))) {
                        throw new IllegalArgumentException("Invalid sort direction.");
                    }
                    orders.add(new PagingOrderDTO(orderArray[0], orderArray[1].toUpperCase()));
                } else if (orderArray.length == 1) {
                    orders.add(new PagingOrderDTO(orderArray[0], PagingOrderDTO.ASC));
                }
            }
            return new PagingSortDTO(orders);
        }
        return null;
    }
}
