package com.evertecinc.b2c.enex.client.dao.extended;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.evertecinc.b2c.enex.client.model.dto2.CardsHistoryDTO;
import com.evertecinc.b2c.enex.client.model.dto2.requests.ListCardHistoryJsonRequestDTO;

public interface ICardsHistoryRepo {

	public List<CardsHistoryDTO> getListCardHistoryByCriteria(ListCardHistoryJsonRequestDTO params, Pageable pageable);

	public Long getCOUNTListCardHistoryByCriteria(ListCardHistoryJsonRequestDTO params);

}
