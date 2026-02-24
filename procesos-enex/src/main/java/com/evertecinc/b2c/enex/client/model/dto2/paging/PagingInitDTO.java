package com.evertecinc.b2c.enex.client.model.dto2.paging;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PagingInitDTO(

        @JsonProperty("page_number")
        int pageNumber,
        @JsonProperty("page_size")
        int pageSize,
        @JsonProperty("paged")
        boolean isPaged,
        @JsonProperty("need_page_info")
        boolean needPageInfo) {

    public static PagingInitDTO getUnpaged() {
        return new PagingInitDTO(0, 0, false, false);
    }

}
