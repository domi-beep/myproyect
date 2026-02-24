package com.evertecinc.b2c.enex.client.dao.extended;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.evertecinc.b2c.enex.client.model.dto2.ImagenDTO;
import com.evertecinc.b2c.enex.client.model.dto2.requests.ImagenCriterioDTO;

public interface IContentsRepo {
	
	public List<ImagenDTO> getImagenesBannerByCriterio(ImagenCriterioDTO params, Pageable pageable);
}
