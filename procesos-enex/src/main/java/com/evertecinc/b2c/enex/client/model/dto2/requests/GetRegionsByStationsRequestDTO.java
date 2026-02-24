package com.evertecinc.b2c.enex.client.model.dto2.requests;

import lombok.Data;

@Data
public class GetRegionsByStationsRequestDTO {
	public Long idCard;
	public String clientType;
	public boolean controlPass;
}