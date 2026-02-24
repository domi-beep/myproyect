package com.evertecinc.b2c.enex.client.model.dto2.restResponses;

import java.util.Optional;

import com.evertecinc.b2c.enex.client.model.dto2.BannerDTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GetBannerByIdResultDTO extends GenericResultDTO{
	
	private Optional<BannerDTO> banner;
	

}
