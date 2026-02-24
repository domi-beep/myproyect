package com.evertecinc.b2c.enex.client.model.dto2.paging;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PagingDataDTO implements Serializable {

	private static final long serialVersionUID = -4196680536213479538L;

	@JsonProperty("page_number")
    private int pageNumber;

    @JsonProperty("page_size")
    private int pageSize;

    @JsonProperty("total_rows")
    private long totalRows;

    @JsonProperty("paged")
    private boolean isPaged = true;

    @JsonProperty("need_page_info")
    private boolean needPageInfo = true;

    public static PagingDataDTO fromPagingInit(PagingInitDTO pagingInitDTO) {
        PagingDataDTO pagingDataDTO = new PagingDataDTO();

        pagingDataDTO.setPageNumber(pagingInitDTO.pageNumber());
        pagingDataDTO.setPageSize(pagingInitDTO.pageSize());
        pagingDataDTO.setPaged(pagingInitDTO.isPaged());
        pagingDataDTO.setNeedPageInfo(pagingInitDTO.needPageInfo());
        return pagingDataDTO;
    }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PagingDataDTO [pageNumber=");
		builder.append(pageNumber);
		builder.append(", pageSize=");
		builder.append(pageSize);
		builder.append(", totalRows=");
		builder.append(totalRows);
		builder.append(", isPaged=");
		builder.append(isPaged);
		builder.append(", needPageInfo=");
		builder.append(needPageInfo);
		builder.append("]");
		return builder.toString();
	}
    
    
    
    
}
