package com.evertecinc.b2c.enex.client.dao.extended;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.evertecinc.b2c.enex.client.model.dto2.CardUseDTO;
import com.evertecinc.b2c.enex.client.model.dto2.requests.CardUseTrxRequestDTO;

public interface ICardUseRepo {

	public List<CardUseDTO> getListCardUseTrx(CardUseTrxRequestDTO params, Pageable pageable);

	public Long getTotalLoadTrxByDay(); 
}
