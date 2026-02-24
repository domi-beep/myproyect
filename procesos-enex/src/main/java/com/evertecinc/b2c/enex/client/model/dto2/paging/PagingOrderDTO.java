package com.evertecinc.b2c.enex.client.model.dto2.paging;

import java.io.Serializable;

public record PagingOrderDTO(String property, String direction) implements Serializable {
    public static final String ASC = "ASC";
    public static final String DESC = "DESC";
}
