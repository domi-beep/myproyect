package com.evertecinc.b2c.enex.client.dao.extended;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.evertecinc.b2c.enex.client.model.dto2.PreciosLitrosDTO;
import com.evertecinc.b2c.enex.client.model.dto2.requests.ListPreciosLitrosRequestDTO;

public interface IPriceLitersRepo {

	public List<PreciosLitrosDTO> getListPriceLiters(ListPreciosLitrosRequestDTO params, Pageable paging);

}
